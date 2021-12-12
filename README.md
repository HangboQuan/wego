# wego
2021校园社区论坛毕设项目
## 1.项目简介
  本次项目是我毕业设计的题目，最终毕业设计的也获得了优秀的成绩，毕业设计的课题是基于web的校园论坛系统，下面主要来介绍下我的毕业设计的项目的需求：  
  （1）系统实现手机号+验证码、手机号+密码的注册方式，实现手机号+验证码、手机号+密码、Github登录、QQ登录的登录方式，并使用cookie保存用户登录信息。  
  （2）系统实现用户可以根据不同的文章分类来发表文章、编辑文章、删除文章、搜索文章、对文章进行点赞、评论、查看热点文章等。  
  （3）系统实现用户的个人中心，用户可以查看个人信息、更新个人信息、关注/取消关注别人、关注/粉丝列表、我的消息、我的私信、用户成就值排行榜等。  
  （4）系统实现类似于QQ一对一的私信。  
  该项目主要采用前后端分离的开发方式，本人主要负责后端所有功能实现。  
  后端技术栈为：SpringBoot+SpringMVC+Mybatis+Redis+ELK+Quartz+websocket  
  前端技术栈为：Vue.js+ElementUI+axios  
### 项目部分功能设计思路  
#### 1.1 手机号+验证码的实现思路
  验证码服务使用是腾讯云提供的短信验证码服务，腾讯云会有200条的免费短信进行测试，首先需要在腾讯云中进行实名认证，发送短信的话，需要在腾讯云中创建签名、创建模板等，[参考链接](https://console.cloud.tencent.com/smsv2)，后端主要需要完成的就是随机的生成6位的验证码，将验证码存在redis中，设置3min的过期时间，在登录注册的时候，只需将用户在前端对话框中输入的验证码和redis存储的验证码进行对比，如果匹配则可进行登录成功，否则提示用户验证码输入错误.
  使用腾讯云手机验证码的Java的SDK参考代码为：[链接](https://cloud.tencent.com/document/product/382/43194)  
#### 1.2 第三方登录（Github/QQ）实现思路（以Github实现为例）  
   * 第三方登录的原理：  
   * 1.A网站让用户跳转到Github  
   * 2.Github要求用户登录，然后询问A网站要求获取XX权限，你是否同意？  
   * 3.用户同意，Github就会重定向会A网站，同时返回一个授权码code(这里去请求回调地址的接口)  
   * 4.A网站使用授权码，想Github请求令牌(在回调地址的接口中继续想Github中发送请求，请求获取令牌，令牌即TOKEN)  
   * 5.Github返回令牌  
   * 6.A网站使用令牌，想Github请求用户数据  
#### 1.3 查看热点文章实现思路
  热点文章设计为：根据文章的浏览量降序排列，取TOP10进行展示  
#### 1.4 上传图片（使用七牛云服务器存储图片）实现思路  
  上传用户头像、修改用户头像操作将图片上传至七牛云服务器上，自己上传了一张照片，返回的[在线地址](http://wegocoder.top/FggcekLfLIZVrXgFgl0IhH0rZ1T3)，这样就减少数据库存储图片带来的性能开销，在使用七牛云服务器的时候，需要在七牛云上进行实名认证，以及获取对应的accessKey、secretKey信息，自己设置bucket、domain信息，具体参考官网给出Java的SDK参考代码为：[链接](https://developer.qiniu.com/kodo/1239/java)  
#### 1.5 关注/取消关注别人，关注/粉丝列表实现思路
  基于Redis的Set数据结构实现，原因是该结构类似于Java中的Hashset，可以实现去重功能，用sadd、scard来实现即可.  
#### 1.6 用户成就值排行榜实现思路
  成就值加分规则：  
   * 当用户发表一篇文章的时候，成就值+10分  
   * 当用户的文章被别人点赞一次之后，成就值+5分  
   * 当用户的文章被别人评论一次之后，成就值+5分  
   * 当用户被一个人关注后，成就值+10分  
实现思路：使用Quartz定时器框架，每一个月将用户的成就值清零，在这一个月之内根据用户的成就值降序排列，取成就值TOP10.  
### 项目重难点功能设计思路
#### 1.1 异步通知功能设计
一般我们在购物或者博客的网站上都会收到系统推送的通知消息，而对应于校园论坛网站的开发中，我自己设计为当用户A对用户B发表的文章点赞、评论、以及用户A关注了用户B，都用到了异步通知的设计。
异步通知和同步通知(以点赞业务为例)的区别是：  
  * 同步通知，点赞之后必须等到该操作执行完所有操作（更新点赞数等业务），才能执行后面的代码；  
  * 异步通知，点赞之后启动一个新的线程去处理异步通知的业务逻辑，主线程执行后面的代码，互不影响，提升用户体验；  
  ![images](https://github.com/ProgramMonkeyquan/wego/blob/master/images/asynNotice.png)    
   如上图所示，异步通知的设计思路如下，分为业务代码、生产者、消费者、消息队列、统一的接口以及实现类，即：业务代码（如点赞业务）首先将点赞的事件封装成一个eventModel对象，然后将其传给生产者，生产者将该事件推到Redis的list的消息队列中，然后消费者监听有哪些实现类实现了统一的接口，并将其保存至Map中，然后开启一个新的线程不断的从消息队列中消费事件，如果有事件则根据事件的类型（点赞/评论/我的关注），去执行对应的实现类。在实现类中的实现是将事件的具体的内容（如用户A点赞了用户B的文章）保存至数据库中，并且设置有一个属性为has_read属性，默认为0（表示有未读消息），然后前端的实现是启用3秒的定时器轮询的调用接口（这个接口的作用是给前端返回有没有未读数据），如果有未读消息，就会给用户提示，当用户查看了提示的内容之后，将所有通知的has_read属性设置为1（表示没有未读消息），这样即实现了异步通知的功能。  
#### 1.2 ELK（ElasticSearch+Logstash+Kibana）同步MySQL以及ES搜索和MySQL模糊查询的搜索耗时对比
项目中考虑到文章的数据量只会越来越大，由于在大数据量的情况下，使用mysql的模糊查询的耗时十分巨大，如果给mysql中添加索引这又会带来新的开销，为此采用ES来搜索文章。首先则需要将MySQL的数据和ES的数据进行同步，采用Lostash工具就可以实现同步数据，kibana的作用是提供一个可视化的界面，方便开发人员搜索数据。同步完成之后，需要只需要集成Spring-data-elasticsearch,根据其api接口就可以实现按照文章标题和文章内容模糊查询，并实现了将关键字高亮展示（高亮展示是由前端来完成的，具体的思路为：前端通过搜索框拿到用户搜索的关键词，然后使用正则表达式匹配后端返回数据中的关键字，匹配之后将其样式改为红色标亮展示）。
![images](https://github.com/ProgramMonkeyquan/wego/blob/master/images/es-mysql.png)  
在指导老师的督促下，要拿数据说话为什么在大数量的情况下ES的搜索耗时要低于MySQL的搜索耗时？为此，自己写程序随机生成了100、1000、10000、100000、600000条数据来进行对比，对比结果如上图所示，得出结论：在数据量大于100000的时候，ES的搜索耗时已经优于MySQL了，数据量越大时，差距更加明显。  
#### 1.3 点赞在高并发环境下的设计思路
  * 业务场景：在高并发环境下用户频繁的给某一篇文章点赞，而点赞之后需要将点赞数更新至数据库中，这样就可能严重影响数据库的性能，甚至会导致数据的宕机。
  * 设计思路：将文章的点赞信息全部缓存在redis的set类型的数据结构中，读的时候直接从redis中返回点赞数据，然后使用Quartz每隔两个小时将Redis的数据同步至MySQL中，保证Redis和MySQL的数据一致性。  
#### 1.4 私信实现
  * 私信的实现主要是基于websocket实现，分为三步：第一步双方建立websocket连接，第二步后端负责消息的转发，第三步双方关闭websocket连接。  
## 2. 项目效果展示
### 2.1 首页页面展示
![images](https://github.com/ProgramMonkeyquan/wego/blob/master/images/index.png)
### 2.2 登录页面展示
![images](https://github.com/ProgramMonkeyquan/wego/blob/master/images/login.png)
### 2.3 搜索文章并对关键字进行高亮展示
![images](https://github.com/ProgramMonkeyquan/wego/blob/master/images/es.png)
### 2.4 异步通知功能效果展示
![images](https://github.com/ProgramMonkeyquan/wego/blob/master/images/async1.png)
![images](https://github.com/ProgramMonkeyquan/wego/blob/master/images/async2.png)
### 2.5 用户成就值排行榜展示
![images](https://github.com/ProgramMonkeyquan/wego/blob/master/images/achieveValue.png)
### 2.6 粉丝列表展示
![images](https://github.com/ProgramMonkeyquan/wego/blob/master/images/fansList.png)
### 2.7 一对一私信效果展示
![images](https://github.com/ProgramMonkeyquan/wego/blob/master/images/message.png)
## 3. 项目中遇到的问题
### 3.1 SpringBoot整合ES
  初始开发项目用到的SpringBoot版本为2.2.1，ES版本为ElasticSearch7.0.0，但是在整合的时候会出现报错，提示版本不兼容，后在Spring官网中查到如下图的版本问题  
![images](https://github.com/ProgramMonkeyquan/wego/blob/master/images/es-version.png)    
经分析可知，SpringBoot2.2.1支持是的ES6.8.12版本不支持ES7.0.0版本，所以有两种解决方案：一种是降低ES的版本，下载ES6.8.12版本，另外一种则是将SpringBoot的版本升级为2.3.x这样就可以使用ES7.0.0的版本了，于是本课题采用第二种方案解决了问题。
### 3.2 使用ES查询数据时报错
  在调用ES的查询接口，根据关键字查询数据的时候，项目报错：No converter found capable of converting from type [java.lang.Boolean] to type [int]，经过查阅各种资料进行分析，发现问题的原因为：使用Logstash把MySQL中的文章表的数据同步到ES时，文章表有一个is_deleted属性，在MySQL数据库is_deleted的数据类型是tinyint类型，但是同步完成之后，在ES中is_deleted的数据类型为boolean类型，而本项目中的处理是按照int类型来处理is_deleted属性，因此报错无法将boolean类型转化为int类型。
  解决方案为：使用Logstash同步MySQL文章表数据的时候，保证ES和MySQL中类型相对应即可，在同步数据的配置文件中，配置为jdbc_connection_string=>”jdbc:mysql://localhost:3306/wego?tinyInt1isBit=false”，tinyInt1isBit=false就可以避免tinyint(1)类型数据自动转为boolean类型，问题得以解决。
### 3.3 前后端联调中图片上传问题
点击上传图片之后，服务端报错：java.lang.NullPointerException，经分析，首先定位到问题是：前端给后端传递过来的图片为null，最终定位到问题再前端和后端传递值的命名不一致造成。
解决方案为：将前端要传递的参数和后端接受的参数保持一致即可，在本课题下也就将前端el-upload下的name设置为file，后端将接收的参数名也改为file，如下图，修改完成之后上传图片成功。
![images](https://github.com/ProgramMonkeyquan/wego/blob/master/images/ui-upload.png)
![images](https://github.com/ProgramMonkeyquan/wego/blob/master/images/sever-upload.png)
## 4. 项目中需改进的地方
  (1)项目中仍然存在着很多未知的bug以及由于前端同学的不给力，导致一些问题没有得到解决，包括：导航栏中用户的头像不正确、异步通知前端并没有实现真正意义的异步通知（但是后端实现了）、前端对评论、我的消息的页面实现太差劲、私信功能目前未完全实现；  
  (2)系统应该增加权限管理，分为管理员和普通用户，管理员可以对普通用户发表的文章有权限更改、删除；  
  (3)系统应该实现敏感词处理，将敏感词（如涉及政治问题、色情信息等违法国家法律法规）的信息直接用星号屏蔽；  
  (4)在实现的私信功能是基于双方登录态的情况下，实现一对一通信，只能发送文字消息，后续可以继续完全实现私信的功能，以及能支持离线消息发送、双方客户端都可以保存消息、支持发送文字消息、图片消息和短视频信息等；  
  (5)时间和实力允许的情况下，我将对本项目的前端推到重写(原因是：参与前端开发的同学过于敷衍，不认真实现具体的页面，好多页面很丑)；  
## 5. 项目部署
### 5.1 所需环境和软件要求：
  * MySQL8.0版本(5.0的需要更改jdbc连接配置的代码)、
  * Redis(由于在本地开发，需下载windows版的，版本不限，我自己用的是3.0.7)、
  * ELK(即ElasticSearch+Logstash+kibana，这三个软件必须保证版本一致，本次开发使用的是7.0.0版本)、
  * 花生壳(由于是前后端分离，每次写完代码上线到服务器十分麻烦，因此使用花生壳服务，将自己本机localhost:8081的服务映射到线上地址http://38617112yi.zicp.vip)  
  * 安装node.js环境，用来运行代码，[这是前端代码地址](https://github.com/ProgramMonkeyquan/wego/tree/master/web)  
### 5.2 部署注意事项：
  * 数据库名字为wego，可将db文件直接导入MySQL中
  * 运行代码之前，必须启动Redis服务、ELK服务(如果不启动无法使用ES相关服务)、花生壳(不启动，前后端无法进行交互，或者自己可以把我刚才所写的线上地址全部替换为localhost:8080，这样本地就可以直接访问了)、同时要启动运行前端代码



  码字不易，期待您的star，谢谢。


  
                
