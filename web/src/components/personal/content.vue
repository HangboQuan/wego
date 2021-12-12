<template>
  <div class="content">
    <div class="info">基本信息</div>
    <el-row>
      <el-col :span="12" style="border-right: 1px #eee solid">
        <div style="display: flex; margin-bottom: 20px">
          <p style="font-size: 14px; color: #333; margin-left: 80px">
            我的昵称：
          </p>
          <p style="font-size: 14px; color: #999; margin-left: 10px">
            {{userObj.nickname}}
          </p>
        </div>
        <div style="display: flex; margin-bottom: 30px">
          <p style="font-size: 14px; color: #333; margin-left: 80px">
            我的图像：
          </p>
          <el-avatar
            :size="40"
            :src="userObj.avatar"
            style="font-size: 14px; color: #999; margin-left: 10px"
          ></el-avatar>
          <!-- <p style="font-size: 14px; color: #999; margin-left: 10px">
            15019689912
          </p> -->
        </div>
        <!-- <div style="display: flex; margin-bottom: 30px">
          <p style="font-size: 14px; color: #333; margin-left: 80px">
            真实姓名：
          </p>
          <p style="font-size: 14px; color: #999; margin-left: 10px">喵喵</p>
        </div> -->
        <div style="display: flex; margin-bottom: 30px">
          <p style="font-size: 14px; color: #333; margin-left: 80px">
            我的性别：
          </p>
          <p style="font-size: 14px; color: #999; margin-left: 10px">女</p>
        </div>
        <div style="display: flex; margin-bottom: 30px">
          <p style="font-size: 14px; color: #333; margin-left: 80px">
            我的学校：
          </p>
          <p style="font-size: 14px; color: #999; margin-left: 10px">
            {{userObj.school}}
          </p>
        </div>
        <!-- <div style="display: flex; margin-bottom: 30px">
          <p style="font-size: 14px; color: #333; margin-left: 80px">
            毕业年份：
          </p>
          <p style="font-size: 14px; color: #999; margin-left: 10px">
            2021-07-01
          </p>
        </div> -->
        <div style="display: flex; margin-bottom: 30px">
          <p style="font-size: 14px; color: #333; margin-left: 80px">
            个性签名：
          </p>
          <p style="font-size: 14px; color: #999; margin-left: 10px">嘿嘿嘿</p>
        </div>
        <el-button
          style="margin-top: 62px"
          type="primary"
          @click="changeStatus()"
          >编辑</el-button
        >
      </el-col>
      <el-col :span="12" v-if="display == true">
        <el-form ref="form" :model="form" label-width="100px" size="small">
          <el-form-item label="图像" prop="pic" class="changeImg">
            <el-upload
              class="avatar-uploader"
              action="http://38617112yi.zicp.vip/upload/images"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              name="file"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="学校">
            <el-input v-model="form.school"></el-input>
          </el-form-item>
          <!-- <el-form-item label="真实姓名">
            <el-input
              v-model="form.name"
              placeholder="请输入你的真实姓名"
            ></el-input>
          </el-form-item> -->
          <el-form-item label="性别">
            <el-radio-group v-model="form.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <!-- <el-form-item label="毕业年份">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="form.date"
              style="width: 100%"
            ></el-date-picker>
          </el-form-item> -->
          <el-form-item label="个性签名">
            <el-input
              type="textarea"
              v-model="form.signature"
              placeholder="懒的连签名都没有...."
            ></el-input>
          </el-form-item>

          <el-button
            style="margin-top: 20px"
            size="max"
            type="primary"
            @click="submit()"
            >提交</el-button
          >
        </el-form>
      </el-col>
      <el-col :span="12" v-else>
        <div></div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  name: "Content",
  data() {
    return {
      display: false,
      imgSrc: require("@/assets/img1.webp"),
      form: {
        pic: "",
        title: "",
        name: "",
        sex: "",
        date: "",
        signature: "",
        school: "",
      },
      imageUrl: "",
      userObj: {},
      userId: '',
      img1: '',
    };
  },
  mounted() {
    this.getUserInfo();
   // this.isRed()
   this.newLikes()
    
  },
  methods: {
    newLikes() {
      var _self = this;
      var url = "http://38617112yi.zicp.vip/notice/list";
      _self.$axios
        .get(url)
        .then((res) => {
          if (res.status == 200) {
            
              console.log('新消息',res.data)
              //let args = res.data.data;
              // let list = []
              // args.forEach(item => {
              //   list.push(item.userId)
              // });
              //_self.followList = res.data.data;
            //    _self.userObj = res.data.data;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getUserInfo() {

      var _self = this;
      var url = "http://38617112yi.zicp.vip/userInfo";
      _self.$axios
        .get(url)
        .then((res) => {
          if (res.status == 200) {
            
              console.log('用户信息',res.data)
              //let args = res.data.data;
              // let list = []
              // args.forEach(item => {
              //   list.push(item.userId)
              // });
              //_self.followList = res.data.data;
              _self.userObj = res.data.data;
              localStorage.setItem('myuserId',res.data.data.userId)
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    changeStatus() {
      this.display = true;
    },
    submitForm() {
      console.log("提交修改内容");
      this.display = false;
    },
    handleAvatarSuccess(res, file) {
      //console.log('图片路径',res)
      this.img1 = res.data;
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    submit() {
      // let config = {
      //   timeout: 30000,
      //   headers: {
      //     "Content-Type": "multipart/form-data", //设置headers
      //   },
      // };
      const formData = new FormData();
      var that = this;
      // 首先判断是否上传了图片，如果上传了图片，将图片存入到formData中
      console.log(this.imageUrl);
      if (this.dataList) {
        that.dataList.forEach((item, index) => {
          // console.log(item)
          formData.append(index, item);
        });
      }
      // console.log(formData.get(0));
      // that.$axios
      //   .post(
      //     'http://38617112yi.zicp.vip/upload/images', //请求后端的url
      //     formData,
      //     config
      //   )
      //   .then((res) => {
      //     console.log(res);
      //     if (res.data.status == "ok") {
      //       //上传成功
      //       console.log("上传成功");
      //       this.$router.push({
      //         path: "./",
      //       });
      //     } else {
      //       alert("上传失败");
      //     }
      //   })
      //   .catch((error) => {
      //     console.log("请求失败");
      //   });

      let sex = that.form.sex == '男' ? 1 : 0;
        let user = {
          nickname:that.form.title, 
          sex: sex,
          school: that.form.school,
          signature: that.form.signature,
          userId: that.userObj.userId,
          avatar: that.img1,
        }
        let url = 'http://38617112yi.zicp.vip/update/userInfo'
        that.$axios
          .post(url ,user, {
            headers: { "Content-Type": "application/json;charset=utf-8" ,
              //"Cookie": this.myCookie
            },
            withCredentials: true,
            //baseURL: '/apis'
          })
          .then((res) => {
            console.log(res.data);
            if (res.status == 200) {
              if (res.data.code == 0) {
                 that.input = '';
                 that.content = '';
                that.$message.success("编辑信息成功");
              }
              this.$router.push({ 
              name:'index',
            })
            }
          })
          .catch((err) => {
            console.log(err);
          });
      //用户可以在上传完成之后将数组给清空，这里直接跳转到首页了，没有做清空的操作
    },
  },
};
</script>
<style lang="scss" scoped>
.content {
  width: 950px;
  height: 500px;
  background: #fff;
  border-radius: 4px;
  .info {
    width: 100%;
    height: 70px;
    line-height: 70px;
    text-align: left;
    padding-left: 30px;
    font-size: 16px;
    &:before {
      content: "";
      display: inline-block;
      width: 8px;
      height: 8px;
      border-radius: 50%;
      background: #409eff;
      margin-right: 10px;
      margin-bottom: 2px;
    }
  }
}
.el-input {
  position: relative;
  font-size: 14px;
  display: inline-block;
  width: 90%;
  height: 40px;
  line-height: 40px;
}

.el-form-item__label {
  text-align: right;
  vertical-align: middle;
  float: left;
  font-size: 14px;
  color: #606266;
  line-height: 40px;
  padding: 0 12px 0 0;
  box-sizing: border-box;
  width: 120px;
}
.avatar-uploader {
  // margin-left: -100px;
  padding-left: -200px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 50%;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 24px;
  color: #8c939d;
  width: 50px;
  height: 50px;
  line-height: 50px;
  text-align: center;
  border-radius: 4px;
  border: 1px #eee dotted;
  //   text-align: left;
}
.avatar {
  width: 50px;
  height: 50px;
  display: block;
}
.el-form-item--mini.el-form-item,
.el-form-item--small.el-form-item {
  margin-bottom: 15px;
}
.el-form-item__label {
  width: 80px !important;
  font-weight: 500px;
  font-size: 12px;
}
el-date-editor,
.el-date-editor--date {
  width: 90% !important;
}
.el-input--small {
  width: 90% !important;
}
.el-input__inner {
  -webkit-appearance: none;
  background-color: #fff;
  background-image: none;
  border-radius: 4px;
  border: 1px solid #dcdfe6;
  box-sizing: border-box;
  color: #606266;
  display: inline-block;
  font-size: 11px;
  height: 40px;
  line-height: 40px;
  outline: 0;
  padding: 0 15px;
  transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
  width: 100%;
}
/deep/.el-form-item--small.el-form-item[data-v-087e46ba] {
  margin-bottom: 17px !important;
}
</style>
