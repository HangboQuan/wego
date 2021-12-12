<template>
  <div id="login">
    <div class="container">
      <div class="banner">登录账号</div>
      <div class="main">
        <div class="phone">
          <div class="sjh">
            <el-form
              :model="loginInfo"
              status-icon
              :rules="rules"
              ref="ruleForm"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item label="手机号" prop="phoneNumber">
                <el-input
                  v-model="loginInfo.phoneNumber"
                  autocomplete="off"
                  placeholder="请输入手机号"
                ></el-input>
              </el-form-item>
              <el-form-item label="验证码" prop="checkPass">
                <el-input
                  v-model="loginInfo.checkPass"
                  autocomplete="off"
                  placeholder="请输入验证码"
                ></el-input>
              </el-form-item>
              <el-form-item>
                <el-button
                  type="primary"
                  @click="submitForm('ruleForm')"
                  class="reg"
                  >登录</el-button
                >
              </el-form-item>
              <div class="images">
                <a href="http://38617112yi.zicp.vip/oauth/login">
                  <img src="@/assets/1.png" alt="" href="">
                </a>
                <a href="http://38617112yi.zicp.vip/oauth/qq">
                  <img src="@/assets/2.png" alt="" href="">
                </a>
                
              </div>
              
              <el-button type="primary" class="btn" @click="getCheckPass()"
                >获取验证码</el-button
              >
            </el-form>
          </div>
          <div
            class="txt"
            style="display: flex; justify-content: space-between"
          >
            <p style="margin-left: 95px; cursor: pointer" @click="goIndex()">
              <i class="el-icon-back"></i> 返回
            </p>
            <p style="cursor: pointer" @click="goLoginPass()">
              密码登录<i class="el-icon-right"></i>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  components: {},
  data() {
    return {
      loginInfo: {
        phoneNumber: "",
        checkPass: "",
      },
      rules: {
        phoneNumber: [
          { required: true, message: "请输入手机号", trigger: "blur" },
          {
            validator: function (rule, value, callback) {
              var MobileRegex = /^1[3|4|5|7|8][0-9]{9}$/;
              if (!MobileRegex.test(value)) {
                callback(new Error("请输入正确的手机号码！"));
              } else {
                callback();
              }
            },
            trigger: "blur",
          },
        ],
        checkPass: [
          { required: true, message: "请输入验证码", trigger: "blur" },
          {
            validator: function (rule, value, callback) {
              if (!/^[0-9]{6}$/.test(value)) {
                callback(new Error("验证码必须是六位数字"));
              } else {
                callback();
              }
            },
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    goIndex() {
      this.$router.push({ name: "index" });
    },
    goLoginPass() {
      this.$router.push({ name: "loginPass" });
    },
    getCheckPass() {
      var _self = this;
      var url = "http://38617112yi.zicp.vip/login/sendSMSCode";
      var userId = _self.loginInfo.phoneNumber;
      _self.$message.success("验证码已发送到您的手机，请您注意查收~");
      _self.$axios
        .get(url, {
          params: { 
            userId: userId,
          },
          withCredentials:true
        })
        .then((res) => {
          console.log(res.data);
          if (res.status == 200) {
            if (res.data.code == 0) {
              //_self.$message.success("验证码已发送到您的手机，请您注意查收~");
            }
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    submitForm(formName) {
      console.log(11111);
      const _self = this;
      const userId = _self.loginInfo.phoneNumber;
      const code = _self.loginInfo.checkPass;
      const url = "http://38617112yi.zicp.vip/login/verifyLoginInfo";
      this.$refs[formName].validate((valid) => {
        if (valid) {
          _self.$axios
            .get(url, {
              //将对象 序列化成URL的形式，以&进行拼接
              params: { userId: userId, code: code },
            })
            .then((res) => {
              console.log(res);
              if (res.status == 200) {
                if (res.data.code == 0) {
                  _self.$message.success("登录成功");
                  setTimeout(() => {
                    _self.$router.push({ name: "index" });
                    localStorage.setItem("user", userId);
                  }, 500);
                } else if (res.data.code == 100000) {
                  _self.$message.success(res.data.msg);
                } else if (res.data.code == 100002) {
                  _self.$message.success(res.data.msg);
                }
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.images {
  img {
    width:40px;
    height:40px;
    margin-left:20px;
  }
}
.container {
  width: 1000px;
  height: 700px;
  background: #fff;
  margin: 0 auto;
  box-shadow: 0 15px 20px rgba(0, 0, 0, 0.11);
  .banner {
    width: 1000px;
    height: 100px;
    background: #409eff;
    line-height: 100px;
    font-size: 20px;
    color: #fff;
  }
  .main {
    width: 1000px;
    height: 600px;
    .phone {
      width: 50%;
      height: 250px;
      // border: 1px #eee solid;
      margin: 150px auto;
      position: relative;
      .txt {
        font-size: 12px;
        color: rgb(148, 144, 144);
      }
    }
  }
}
.btn {
  position: absolute;
  right: 0;
  top: 72px;
}
.reg {
  width: 100%;
}
.el-form-item {
  margin-bottom: 32px;
}
</style>>

