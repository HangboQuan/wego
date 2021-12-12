<template>
  <div id="login">
    <div class="container">
      <div class="banner">登陆账号</div>
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
              <el-form-item label="密码" prop="passWord">
                <el-input
                  type="password"
                  v-model="loginInfo.passWord"
                  autocomplete="off"
                  placeholder="8位密码，字母、数字加英文符号（不包含空格）"
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
            </el-form>
          </div>
          <div
            class="txt"
            style="display: flex; justify-content: space-between"
          >
            <p style="margin-left: 95px; cursor: pointer" @click="goIndex()">
              <i class="el-icon-back"></i> 返回
            </p>
            <p style="cursor: pointer" @click="goLogin()">
              验证码登录<i class="el-icon-right"></i>
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
        passWord: "",
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
        passWord: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            validator: function (rule, value, callback) {
              var res = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
              if (!res.test(value)) {
                callback(new Error("请输入正确的密码格式~"));
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
    goLogin() {
      this.$router.push({ name: "login" });
    },
    submitForm(formName) {
      const _self = this;
      const userId = _self.loginInfo.phoneNumber;
      const password = _self.loginInfo.passWord;
      const url = "http://38617112yi.zicp.vip/login/loginPassword";
      this.$refs[formName].validate((valid) => {
        if (valid) {
          _self.$axios
            .get(url, {
              //将对象 序列化成URL的形式，以&进行拼接
              params: { userId: userId, password: password },
            })
            .then((res) => {
              console.log(res);
              if (res.status == 200) {
                if (res.data.code == 0) {
                  _self.$message.success("登录成功");
                  setTimeout(() => {
                    _self.$router.push({ name: "index" });
                  }, 500);
                } else if (res.data.code == 100002) {
                  _self.$message.success(res.data.msg);
                } else if (res.data.code == 100003) {
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

