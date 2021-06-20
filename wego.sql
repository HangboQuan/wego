-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: wego
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `article_title` varchar(40) NOT NULL COMMENT '文章标题',
  `article_summary` varchar(255) DEFAULT NULL COMMENT '文章摘要',
  `article_content` mediumtext COMMENT '文章内容',
  `article_view_count` int(11) DEFAULT NULL COMMENT '文章浏览量',
  `article_like_count` int(11) DEFAULT NULL COMMENT '文章点赞量',
  `article_comment_count` int(11) DEFAULT NULL COMMENT '文章评论数量',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '逻辑删除0表示未删除，1表示删除',
  `article_category_id` int(11) DEFAULT NULL COMMENT '外键，对应category_id',
  `article_user_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '外键，对应user_id',
  `article_category_name` varchar(20) DEFAULT NULL COMMENT '分类表中对应category_name',
  PRIMARY KEY (`article_id`),
  KEY `article_category_id` (`article_category_id`),
  KEY `article_user_id` (`article_user_id`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`article_category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `article_ibfk_2` FOREIGN KEY (`article_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'how to join Google','study hard','1）如果你的员工就像在《软件公司的两种管理》中所说的，像Widget Factories那样，净是些X型的人的话，那么，你也只有使用加班和加人这种方式，就像长城和金字塔的建设过程一样，就像富士康一样，你的团队本质是不会思考只能用鞭子去抽他们的方式去管理。于是，你也只能用“狼性”来呼唤你的员工像那些低智商的野兽一样的行事。\r\n\r\n2）有时候，我们需要去“卡位”，需要很快地去实现一个东西占领市场，这需要加班。就像Win95和Intel的奔腾芯片的浮点数问题一样。但是千万不要忘了，你在卡完位后，得马上把你产品的质量搞上去，不然，你一样会死得很难看。（Windows是有两个团队的，一个团队是用来占领市场的，另一个团队是安心搞发展的）注意：“卡位”从某种程度上来说应该是一种有价值的事，但我们依然要思考是否在用蛮力行事。\r\n\r\n3）另外，有的人工作就是生活，生活就是工作，所以，对他来说，这不是一种工作，而是一种事业。我认可这样的精神和热情，但是，我还是想让这样的人反思一下自己，有没有用一种更为聪明的方式来从事自己的事业？而不是用蛮力。',232,1,9,'2021-04-14 00:00:00','2021-04-14 00:00:00',0,1,'18392710807','找工作交流'),(2,'有人认识王欣颖吗',NULL,'求王欣颖的联系方式？？',10,1,0,'2021-04-19 00:00:00','2021-04-19 00:00:00',1,1,'18392710807','找工作交流'),(3,'如何才能进入Google','努力奋斗','加入Google，你需要坚实的算法基础，C++，Java，操作系统，数据库，计算机网络；\r\n加油吧，孩子！',99,1,12,'2021-04-21 00:00:00','2021-04-21 00:00:00',0,1,'18392710807','找工作交流'),(4,'找到理想中的大厂，我是这样操作的！！','打工人','打工是不可能打工的',222,0,18,'2021-04-21 00:00:00','2021-04-21 00:00:00',0,2,'17795795191','找工作交流'),(5,'求去年高数期末复习卷子，带价来','hello','高价求高数试题，代价来',8,0,26,'2021-04-21 00:00:00','2021-04-21 00:00:00',0,3,'17795795191','找工作交流'),(6,'3教101丢了一把雨伞，在第一排左侧抽屉，好心人帮帮孩子',NULL,'今天在3-303上课，伞忘带了，褐色的，请问有没有好心人看到',4,0,1,'2021-04-21 00:00:00','2021-04-21 00:00:00',0,4,'18392710807','找工作交流'),(7,'我是中国人','','测试ES的中文分词是否被应用,搜索我是中国人，按照中文分词器的处理的话，那会将其分解为，我，中国，中国人等分词',0,0,0,'2021-04-25 23:07:37','2021-04-25 23:07:41',0,7,'18392710807','找工作交流'),(8,'hello',NULL,'welcome to beijing',0,0,0,NULL,NULL,0,NULL,NULL,'找工作交流'),(11,'你好，世界',NULL,'加油，奥利给',0,0,0,'2021-05-09 23:05:21','2021-05-09 23:05:21',0,2,NULL,'找工作交流'),(12,'123',NULL,'123312',2,0,0,'2021-05-10 14:57:35','2021-05-10 14:57:35',0,2,'17643537768','找工作交流'),(13,'12312',NULL,'321',31,0,0,'2021-05-10 15:01:32','2021-05-10 15:01:32',0,1,'17643537768','找工作交流'),(14,'表白王欣颖',NULL,'hello，王欣颖',3,0,0,'2021-05-10 18:13:00','2021-05-10 18:13:00',0,6,'17643537768','找工作交流'),(15,'表白王欣颖',NULL,'hello，王欣颖',1,0,0,'2021-05-10 18:13:03','2021-05-10 18:13:03',0,6,'17643537768','找工作交流'),(16,'有人需要参加2022届ACM吗，求组队？',NULL,'大家好，我是黄鑫，想参加2022届ACM，目前一人，语言使用C++，熟练掌握基本的数据结构和算法，只是英文能力稍差，现在还差2个人，求组队',1,0,0,'2021-05-18 10:58:54','2021-05-18 10:58:54',0,8,'18392710807','竞赛组队'),(18,'梦想，是什么？',NULL,'北京，是一个尊重梦想的城市!',24,1,0,'2021-05-18 11:33:42','2021-05-18 11:33:42',0,1,'18392710807','考研交流'),(19,'写个文章phr',NULL,'写个文章',0,0,0,'2021-05-20 19:26:08','2021-05-20 19:26:08',0,3,'17643537768','日常学习'),(20,'怎么考研?????',NULL,'123123123123123',7,0,2,'2021-05-20 19:28:25','2021-05-20 19:28:25',0,1,'17643537768','考研交流'),(21,'清华大学，距离我们远吗？',NULL,'	清华大学（Tsinghua University），简称“清华”，是中华人民共和国教育部直属的全国重点大学，位列国家“双一流”A类、“985工程”、“211工程”，入选“2011计划”、“珠峰计划”、“强基计划”、“111计划”，为九校联盟（C9）、松联盟、中国大学校长联谊会、亚洲大学联盟、环太平洋大学联盟、清华—剑桥—MIT低碳大学联盟成员、中国高层次人才培养和科学技术研究的基地，被誉为“红色工程师的摇篮”。 [1-3]&nbsp;学校前身清华学堂始建于1911年，校名“清华”源于校址“清华园”地名，是清政府设立的留美预备学校，其建校的资金源于1908年美国退还的部分庚子赔款。1912年更名为清华学校。1928年更名为国立清华大学。1937年抗日战争全面爆发后，南迁长沙，与国立北京大学、私立南开大学组建国立长沙临时大学，1938年迁至昆明改名为国立西南联合大学。1946年迁回清华园。1949年中华人民共和国成立，清华大学进入新的发展阶段。1952年全国高等学校	院系调整后成为多科性工业大学。1978年以来逐步恢复和发展为综合性的研究型大学。 [4]&nbsp;截至2020年12月，清华大学校园面积442.12公顷，建筑面积287.64万平方米；设有21个学院、59教学系，开设有82个本科专业；有博士后科研流动站50个，一级学科国家重点学科22个，一级学科博士、硕士学位授权点60个；有教职工15772人，在校生53302人。 [5]&nbsp;',8,1,1,'2021-05-25 20:10:22','2021-05-25 20:10:22',0,7,'15389605725','生活趣事'),(22,'欢迎来到西安科技大学',NULL,'西安科技大学，是一所非常优秀的高校，欢迎报考西安科技大学。',8,1,0,'2021-05-30 11:30:39','2021-05-30 11:30:39',0,1,'18392710807','考研交流'),(23,'北京你好',NULL,'你好，北京；加油!',9,1,0,'2021-05-31 18:56:50','2021-05-31 18:56:50',0,1,'18392710807','考研交流'),(24,'物联网工程',NULL,'hello,world',2,0,0,'2021-05-31 22:39:05','2021-05-31 22:39:05',0,2,'18392710807','找工作交流'),(25,'物联网工程',NULL,'你好，物联网',10,0,1,'2021-05-31 22:39:21','2021-05-31 22:39:21',0,2,'18392710807','找工作交流'),(26,'hello',NULL,'西安，hello',3,0,0,'2021-06-01 09:56:02','2021-06-01 09:56:02',0,3,'18392710807','日常学习'),(27,'hello',NULL,'你在教我做事？？hello',1,0,0,'2021-06-01 16:04:46','2021-06-01 16:04:46',0,2,'18392710807','找工作交流'),(28,'欢迎报考西安科技大学研究生',NULL,'西安科技大学，是一所非常优秀的学校。',2,1,0,'2021-06-01 22:44:04','2021-06-01 22:44:04',0,1,'18392710807','考研交流'),(29,'6.30号有人去北京吗',NULL,'6.30号有人去北京吗，能不能拼个车？',6,1,0,'2021-06-04 20:36:43','2021-06-04 20:36:43',0,5,'42888075','拼单拼车'),(30,'hello,西安科技大学',NULL,'欢迎报考西安科技大学研究生，欢迎加入物联网工程系',4,1,0,'2021-06-07 08:30:06','2021-06-07 08:30:06',0,1,'18392710807','考研交流'),(31,'hello,西安科技大学',NULL,'欢迎报考西安科技大学，欢迎来到物联网工程专业',3,1,0,'2021-06-07 08:34:48','2021-06-07 08:34:48',0,1,'18392710807','考研交流'),(32,'马上就要放假了',NULL,'你好，西安科技大学，物联网工程',4,1,1,'2021-06-07 08:40:13','2021-06-07 08:40:13',0,1,'18392710807','考研交流'),(33,'we donot talk anymore',NULL,'欣颖，你懂我意思吧',0,0,0,'2021-06-11 00:27:17','2021-06-11 00:27:17',0,6,'18392710807','表白墙'),(34,'加油，奥利给',NULL,'你在教我做事？',0,0,0,'2021-06-19 13:12:22','2021-06-19 13:12:22',0,3,'18392710807','日常学习'),(35,'helo',NULL,'big',0,0,0,'2021-06-19 13:15:29','2021-06-19 13:15:29',0,4,'18392710807','寻物启事'),(36,'你在教我做事',NULL,'hello,world',0,0,0,'2021-06-19 13:18:51','2021-06-19 13:18:51',0,6,'18392710807','表白墙');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，分类表id',
  `category_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'考研交流'),(2,'找工作交流'),(3,'日常学习'),(4,'寻物启事'),(5,'拼单拼车'),(6,'表白墙'),(7,'生活趣事'),(8,'竞赛组队');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，评论主键',
  `comment_article_id` int(11) NOT NULL COMMENT '外键，对应article_id',
  `comment_user_id` varchar(11) NOT NULL COMMENT '外键，对应user_id',
  `comment_content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `comment_like_count` int(11) DEFAULT NULL COMMENT '评论点赞数',
  `comment_count` int(11) DEFAULT NULL COMMENT '评论的评论数',
  `comment_created_time` datetime DEFAULT NULL COMMENT '评论的创建时间',
  PRIMARY KEY (`comment_id`),
  KEY `comment_article_id` (`comment_article_id`),
  KEY `comment_user_id` (`comment_user_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`comment_article_id`) REFERENCES `article` (`article_id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`comment_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,'18392710807','这篇文章给82，剩下的以666给你',0,0,'2021-04-17 20:17:11'),(2,1,'18392710807','这篇文章给82，剩下的以666给你',0,0,'2021-04-17 20:19:18'),(3,1,'18392710807','这篇文章给82，剩下的以666给你',0,0,'2021-04-17 20:25:12'),(4,1,'18392710807','这篇文章给82，剩下的以666给你',0,0,'2021-04-17 21:24:13'),(5,1,'18392710807','你的回答,如何比得上天空之皓月',0,0,'2021-05-09 12:51:33'),(6,1,'18392710807','你的回答,如何比得上天空之皓月',0,0,'2021-05-09 13:00:55'),(7,1,'18392710807','123123',0,0,'2021-05-09 13:09:47'),(8,1,'18392710807','我评论下',0,0,'2021-05-09 13:10:40'),(9,1,'18392710807','hello',0,0,'2021-05-18 09:53:00'),(10,1,'18392710807','你在教我做事？',0,0,'2021-05-18 09:56:55'),(11,1,'18392710807','嘿嘿嘿',0,0,'2021-05-18 09:57:14'),(12,20,'18392710807','别考了，考不上',0,0,'2021-05-20 19:42:56'),(13,20,'18392710807','你在教我做事',0,0,'2021-05-20 19:43:09'),(14,1,'17643537768','你在教我做事？',0,0,'2021-05-25 14:54:19'),(15,1,'15389605725','你好',0,0,'2021-05-25 20:06:33'),(16,1,'18392710807','天才啊',0,0,'2021-05-26 16:49:37'),(17,1,'18392710807','天才啊',0,0,'2021-05-26 16:49:37'),(18,1,'18392710807','天才啊',0,0,'2021-05-26 16:49:39'),(19,25,'17643537768','hello',0,0,'2021-05-31 22:48:04'),(20,5,'18392710807','hello',0,0,'2021-06-02 22:58:14'),(21,32,'17643537768','hello',0,0,'2021-06-07 08:41:30'),(22,1,'17643537768','宝强同志，俺看好你，你是俺的粉丝，祝你前程似锦、霸气外露',0,0,'2021-06-19 13:10:49'),(23,21,'17643537768','你好',0,0,'2021-06-20 17:25:40'),(24,5,'17643537768','你在教我做事',0,0,'2021-06-20 17:26:34'),(25,5,'17643537768','你在教我做事',0,0,'2021-06-20 17:26:37'),(26,5,'17643537768','你在教我做事',0,0,'2021-06-20 17:26:37'),(27,5,'17643537768','你在教我做事',0,0,'2021-06-20 17:26:39');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fans`
--

DROP TABLE IF EXISTS `fans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fans` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，粉丝表id',
  `user_id` varchar(11) DEFAULT NULL COMMENT '用户id',
  `fans_id` varchar(11) DEFAULT NULL COMMENT '粉丝id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fans`
--

LOCK TABLES `fans` WRITE;
/*!40000 ALTER TABLE `fans` DISABLE KEYS */;
INSERT INTO `fans` VALUES (3,'18392710807','15709160159','2021-04-22 13:22:16'),(4,'18392710807','17795795191','2021-04-27 17:26:52'),(5,'18392710807','15091689912','2021-04-27 17:29:43'),(23,'18392710807','15389605725','2021-05-25 20:08:16'),(35,'18392710807','17643537768','2021-06-11 12:58:03'),(36,'18392710807','17643537768','2021-06-19 13:11:09');
/*!40000 ALTER TABLE `fans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，关注表id',
  `user_id` varchar(11) DEFAULT NULL COMMENT '用户id',
  `follow_id` varchar(11) DEFAULT NULL COMMENT '关注的id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (6,'15709160159','18392710807','2021-04-22 13:22:16'),(7,'17795795191','18392710807','2021-04-27 17:26:52'),(26,'15389605725','18392710807','2021-05-25 20:08:16'),(39,'17643537768','18392710807','2021-06-19 13:11:09');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，消息id',
  `from_id` varchar(11) DEFAULT NULL COMMENT '发送方id',
  `to_id` varchar(11) DEFAULT NULL COMMENT '接收方id',
  `message_content` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `has_read` tinyint(1) DEFAULT NULL COMMENT '是否已读0表示未读，1表示已读',
  `conversation_id` varchar(255) DEFAULT NULL COMMENT '点对点对话id',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '通知表id',
  `from_id` varchar(11) DEFAULT NULL COMMENT '发送方id',
  `to_id` varchar(11) DEFAULT NULL COMMENT '接收方id',
  `content` varchar(512) DEFAULT NULL COMMENT '内容',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `has_read` bigint(1) DEFAULT NULL COMMENT '是否已读，1表示已读，0表示未读',
  `conversation_id` varchar(64) DEFAULT NULL COMMENT '对话id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (131,'17643537768','18392710807','逄鸿瑞关注了用户你','2021-06-11 12:58:03',1,'17643537768_18392710807'),(132,'17643537768','18392710807','逄鸿瑞点赞了您的文章：how to join Google','2021-06-19 12:37:51',1,'17643537768_18392710807'),(133,'17643537768','18392710807','逄鸿瑞点赞了您的文章：梦想，是什么？','2021-06-19 12:46:19',1,'17643537768_18392710807'),(134,'17643537768','18392710807','逄鸿瑞点赞了您的文章：how to join Google','2021-06-19 12:56:52',1,'17643537768_18392710807'),(135,'17643537768','18392710807','逄鸿瑞点赞了您的文章：how to join Google','2021-06-19 13:01:22',1,'17643537768_18392710807'),(136,'17643537768','18392710807','逄鸿瑞点赞了您的文章：how to join Google','2021-06-19 13:05:57',1,'17643537768_18392710807'),(137,'17643537768','18392710807','逄鸿瑞点赞了您的文章：how to join Google','2021-06-19 13:09:47',1,'17643537768_18392710807'),(138,'17643537768','18392710807','逄鸿瑞评论您的文章：how to join Google,评论的内容为：宝强同志，俺看好你，你是俺的粉丝，祝你前程似锦、霸气外露','2021-06-19 13:10:50',1,'17643537768_18392710807'),(139,'17643537768','18392710807','逄鸿瑞关注了用户你','2021-06-19 13:11:09',1,'17643537768_18392710807'),(140,'17643537768','18392710807','逄鸿瑞点赞了您的文章：how to join Google','2021-06-19 13:15:11',1,'17643537768_18392710807'),(141,'17643537768','18392710807','逄鸿瑞点赞了您的文章：how to join Google','2021-06-19 13:18:24',1,'17643537768_18392710807'),(142,'17643537768','18392710807','逄鸿瑞点赞了您的文章：欢迎来到西安科技大学','2021-06-20 17:21:28',1,'17643537768_18392710807'),(143,'17643537768','15389605725','逄鸿瑞点赞了您的文章：清华大学，距离我们远吗？','2021-06-20 17:25:25',0,'17643537768_15389605725'),(144,'17643537768','15389605725','逄鸿瑞评论您的文章：清华大学，距离我们远吗？,评论的内容为：你好','2021-06-20 17:25:41',0,'17643537768_15389605725'),(145,'17643537768','42888075','逄鸿瑞点赞了您的文章：6.30号有人去北京吗','2021-06-20 17:25:59',0,'17643537768_42888075'),(146,'17643537768','17795795191','逄鸿瑞评论您的文章：求去年高数期末复习卷子，带价来,评论的内容为：你在教我做事','2021-06-20 17:26:35',0,'17643537768_17795795191'),(147,'17643537768','17795795191','逄鸿瑞评论您的文章：求去年高数期末复习卷子，带价来,评论的内容为：你在教我做事','2021-06-20 17:26:37',0,'17643537768_17795795191'),(148,'17643537768','17795795191','逄鸿瑞评论您的文章：求去年高数期末复习卷子，带价来,评论的内容为：你在教我做事','2021-06-20 17:26:38',0,'17643537768_17795795191'),(149,'17643537768','17795795191','逄鸿瑞评论您的文章：求去年高数期末复习卷子，带价来,评论的内容为：你在教我做事','2021-06-20 17:26:39',0,'17643537768_17795795191'),(150,'17643537768','18392710807','逄鸿瑞点赞了您的文章：北京你好','2021-06-20 17:27:35',1,'17643537768_18392710807');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` varchar(11) NOT NULL COMMENT '主键，手机号',
  `nickname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(40) NOT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '加强密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `achieve_value` int(11) DEFAULT NULL COMMENT '成就值',
  `school` varchar(40) DEFAULT NULL COMMENT '学校',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登录ip地址',
  `create_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_type` varchar(20) DEFAULT NULL COMMENT '登录类型',
  `sex` int(1) DEFAULT NULL COMMENT '用户性别',
  `signature` varchar(30) DEFAULT NULL COMMENT '个性签名',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('13572797966','用户57487号','7caee6b8eb5b32dd4284d9f38cab8be2','hbycyu','http://wegocoder.top/FggcekLfLIZVrXgFgl0IhH0rZ1T3',0,NULL,'phone','2021-05-29 19:24:35',NULL,0,NULL),('15091689912','用户80884号','7b084899bc59b87e4d4ac81676509305','pjqegr','https://avatars.githubusercontent.com/u/42888075?v=4',0,'西安科技大学',NULL,'2021-04-05 17:15:56',NULL,NULL,NULL),('15389605725','用户45482号','ccf3229653a570ea711694d9cff3e72b','xupsfl','http://wegocoder.top/FhWl3ceEALvH8kQMZy_zs62G6WIy',15,NULL,'phone','2021-05-25 20:04:02',NULL,0,NULL),('15619280181','用户97378号','4e4dcfb8d096378e3dada49d740e5ab3','gcbkfs','http://wegocoder.top/FkceH-hLmYU6mgqYvw1czYbhoDBV',0,NULL,'phone','2021-05-20 19:49:08',NULL,NULL,NULL),('15709160159','用户50223号','06fe4b72cdce07b915682fb6e9e1ae13','tntshg','http://wegocoder.top/FkhZ36GgP-CH2tuWIwmk0ywuyWPr',0,NULL,'phone','2021-04-22 12:37:34',NULL,NULL,NULL),('17643537768','逄鸿瑞','a78e882e8aa0d360e373ffa94edf111c','npdcov','http://wegocoder.top/FkceH-hLmYU6mgqYvw1czYbhoDBV',0,'西安科技大学','phone','2021-05-05 22:15:42',NULL,1,'你好'),('17795795191','用户28768号','83817df791905f99584474f7f94803da','rppejw','http://wegocoder.top/FpdGcd87Js55AaTcwE33rFbKbnRO',30,NULL,'phone','2021-05-20 19:48:59',NULL,0,NULL),('18392710807','代码，出来受死','7481861f2b68de877c6a1fb0e5772570','rlkwtj','http://wegocoder.top/FnJqie7ULD3vZ7xTfrCk1exQsBXL',10,'清华大学',NULL,'2021-05-20 19:49:04',NULL,1,'你在教我做事'),('42888075','ProgramMonkeyquan','MDQ6VXNlcjQyODg4MDc1',NULL,'https://avatars.githubusercontent.com/u/42888075?v=4',5,NULL,'github','2021-05-26 11:02:47',NULL,0,NULL),('49876844','douxpang','MDQ6VXNlcjQ5ODc2ODQ0',NULL,'https://avatars.githubusercontent.com/u/49876844?v=4',0,NULL,'github','2021-05-25 03:02:11',NULL,0,NULL),('52988156','LoverITer','MDQ6VXNlcjUyOTg4MTU2',NULL,'https://avatars.githubusercontent.com/u/52988156?v=4',0,'西安科技大学',NULL,'2021-03-31 04:54:18',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-20 21:40:34
