<template>
  <div id="banner">
    <el-row>
      <el-col :span="4">
        <div class="logo">
          <img src="../assets/logo.jpg" alt="" />
          <p>社区</p>
        </div>
      </el-col>
      <el-col :span="3"
        ><div
          class="tlq"
          @click="goTlq()"
          style="
            font-size: 15px;
            margin-top: 15px;
            text-align: left;
            cursor: pointer;
          "
        >
          讨论区
        </div></el-col
      >
      <el-col :span="8" style="position: relative; padding-right: 50px">
        <el-input
          v-model="inputSearch"
          prefix-icon="el-icon-search"
          @keyup.enter.native="onEnterSearch"
          placeholder="请输入"
          style="padding-right: 180px"
        >
        </el-input>
        <el-button
          type="primary"
          style="position: absolute; right: 139px; top: 30px"
          @click="onEnterSearch()"
        >
          搜索</el-button
        >
      </el-col>
      <el-col :span="3">
        <el-button type="primary" round @click="check()">
          <i class="el-icon-s-home"></i> 切换学校</el-button
        >
      </el-col>

      <el-col :span="6">
        <div class="right">
          <div class="btn">
            <el-button type="primary" round @click="goWrite()"
              ><i class="el-icon-edit"></i>写文章</el-button
            >
          </div>
          <el-avatar
            :size="40"
            :src="imgSrc"
            style="
              display: inline-block;
              margin-top: 22px;
              margin-left: 20px;
              margin-right: 10px;
              cursor: pointer;
            "
            @click="goPersonal()"
          ></el-avatar>
          <div class="denglu" v-if="display == true">
            <span @click="goRes()">注册</span>|<span @click="goLogin()"
              >登录</span
            >
          </div>
          <div class="person" v-else-if="!store" @click="goPersonal()">
            <el-badge :value="0" class="item">
              <p style="height: 30px; line-height: 30px">个人中心</p>
            </el-badge>
            <span @click="goReturn()">退出</span>
          </div>
          <div class="person" v-else @click="goPersonal()">
            <el-badge :value="1" class="item">
              <p style="height: 30px; line-height: 30px">个人中心</p>
            </el-badge>
            <span @click="goReturn()">退出</span>
            
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Banner",
  components: {},
  data() {
    return {
      display: true,
      activeIndex: "1",
      inputSearch: "",
      imgSrc: require("@/assets/img1.webp"),
      form: {
        region: "",
      },
      formLabelWidth: "120px",
      hasRed:false,
      store: null
    };
  },
  watch: {
    $store : {
      handler: function(pre) {
        console.log('红点变了')
        console.log('红点',pre)
        this.store = pre
      },
      deep: true
    }
  },
  mounted() {
    var user = localStorage.getItem("user");
    console.log(user, "111");
    if (user) {
      this.display = false;
    } else {
      this.display = true;
    }
  },
  created() {
    this.activeIndex = this.$route.query.url;
    this.store = this.$store.state.type.isRed // 获取状态值
    //console.log('状态',this.$store.state.type.isRed)
  },
  // created () {
    
  // },
  methods: {
    // handleSelect(key) {
    //   console.log(key);
    // },
    // toLink(data) {
    //   console.log(data);
    //   this.$router.push({ name: data.url, query: { url: data.index } });
    // },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(() => {
          done();
          // this.$router.push({ name: "writeArticle" });
        })
        .catch(() => {});
    },
    goReturn() {
      this.$router.push({ name: "index" });
      localStorage.removeItem("password");
      this.display = true;
    },
    goPersonal() {
      // console.log(111);
      this.$router.push({ name: "personal" });
    },
    goTlq() {
      this.$router.push({ name: "index" });
    },
    check() {
      this.$router.push({ name: "checkout" });
    },
    goWrite() {
      this.$router.push({ name: "writeArticle" });
    },
    goLogin() {
      this.$router.push({ name: "login" });
    },
    goRes() {
      this.$router.push({ name: "register" });
    },
    research() {
      this.$router.push({ name: "searchDetail" });
      console.log("search:" + this.inputSearch);
      // const inputSearch = this.inputSearch;
      // if (inputSearch) {
      //   this.$http
      //     .post("/api/路径")
      //     .then((res) => {
      //       console.log(res.data);
      //       this.piles = res.data;
      //     })
      //     .catch((err) => {
      //       console.log(err);
      //     });
      // }
    },
    onEnterSearch() {
     
      const inputSearch = this.inputSearch;
      var _self = this;
      
      var url = "http://38617112yi.zicp.vip/article/search";

      _self.$axios
        .get(url, { params: { keyword: inputSearch } })
        .then((res) => {
          console.log("搜索的数据", res);
          this.$router.push({
            name:'searchDetail',
            params: {
              search:res.data.data
            }
          })
        })
        .catch((err) => {
          console.log(err);
        });   
    },
  },
};
</script>

<style lang="scss" scoped>
#banner {
  height: 80px;
  background: #fff;
  box-shadow: 0 2px 7px rgba(0, 0, 0, 0.11);
  .logo {
    width: 160px;
    height: 60px;
    line-height: 20px;
    display: flex;
    margin-right: 100px;
    margin-top: 10px;
    img {
      width: 70px;
      margin-left: 10px;
    }
    p {
      font-size: 22px;
      margin-top: 25px;
      color: rgb(63, 61, 61);
    }
  }
  .el-col-6 {
    width: 25%;
    height: 80px;
  }
  .el-menu {
    list-style: none;
    position: relative;
    margin: 0;
    padding-left: 0;
    height: 80px;
    background-color: #fff;
  }
  .el-menu--horizontal > .el-menu-item {
    float: left;
    height: 60px;
    line-height: 100px;
    margin: 0;
  }
  .el-menu-item {
    font-size: 15px;
    color: #303133;
    line-height: 100px;
    height: 80px !important;
    padding: 0 10px;
    margin-right: 120px;
    cursor: pointer;
    transition: border-color 0.3s, background-color 0.3s, color 0.3s;
    box-sizing: border-box;
    &:hover {
      color: #613aee;
    }
  }

  .el-button.is-round {
    border-radius: 20px;
    padding: 10px 20px;
  }

  .right {
    line-height: 80px;
    display: flex;
    .btn {
      .el-button {
        font-size: 16px;
      }
    }
    .denglu {
      // margin-left: 60px;
      span {
        font-size: 15px;
        margin-right: 5px;
        margin-left: 5px;
        cursor: pointer;
      }
    }
    .person {
      line-height: 90px;
      font-size: 16px;
      cursor: pointer;
      span {
        font-size: 15px;
        margin-left: 25px;
        cursor: pointer;
      }
    }
  }
}
#banner .right .btn .el-button[data-v-37aed33c] {
  font-size: 14px;
}
#banner .right[data-v-37aed33c] {
  line-height: 100px;
  display: flex;
}
.el-col-7 {
  width: 29.16667%;
  height: 80px;
  line-height: 100px;
}
/deep/.el-input {
  position: relative;
  font-size: 14px;
  display: inline-block;
  width: 70% !important;
}
/deep/.el-input__inner {
  -webkit-appearance: none;
  background-color: #fff;
  background-image: none;
  border-radius: 4px;
  border: 1px solid #dcdfe6;
  box-sizing: border-box;
  color: #606266;
  display: inline-block;
  font-size: inherit;
  height: 40px;
  line-height: 40px;
  outline: 0;
  padding: 0 30px;
  transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
  width: 100%;
  margin-top: 30px !important;
}
/deep/.el-input__prefix {
  left: 5px;
  top: 16px;
  transition: all 0.3s;
}
/deep/.el-col-3 {
  width: 12.5%;
  margin-top: 31px !important;
}
.tlq {
  &:hover {
    // border-bottom: 1px#83adec solid;
    color: #83adec;
  }
}
.bk {
  text-align: left;
  margin-left: 30px;
  font-size: 16px;
  &:before {
    content: "";
    display: inline-block;
    width: 8px;
    height: 8px;
    background: red;
    margin-right: 5px;
  }
}
</style>

