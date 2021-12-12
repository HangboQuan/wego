<template>
  <div class="menu">
    <div class="menu_bt"><h3>沃车港油卡</h3></div>
    <el-menu :default-active="menuactive" class="el-menu-vertical-demo">
      <el-menu-item
        v-for="(item, index) in menudata"
        :key="index"
        :index="item.num"
        @click="menuan(item.link, item.num)"
      >
        <em></em>
        <i><img :src="item.icon" /></i>
        <span slot="title">{{ item.tittle }}</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>
<style scoped lang="less"  type="text/less">
.menu {
  width: 100%;
  height: 100vh;
  background: url("../../assets/images/menu_bg.png") left bottom #23b8ff
    no-repeat;
  background-size: auto 320px;
  .el-menu {
    background: none !important;
    border-right: 0px;
    span {
      color: #fff;
    }
  }
  .el-menu .el-menu-item {
    /* text-align: left;*/
    position: relative;
  }
  .el-menu .el-menu-item em {
    position: absolute;
    left: 0px;
    top: 0px;
    float: left;
    width: 3px;
    height: 100%;
    z-index: 10;
    display: none;
    background: #ffc479;
  }
  .el-menu .el-menu-item:hover {
    background: #45c3ff;
  }
  .el-menu .el-menu-item:hover em {
    display: block;
  }
  .el-menu .el-menu-item.is-active {
    background: #45c3ff;
  }
  .el-menu .el-menu-item.is-active em {
    display: block;
  }
  .el-menu-item:focus,
  .el-menu-item:hover {
    background: none;
  }
  .menu_bt {
    width: 100%;
    h3 {
      font-size: 30px;
      color: #fff;
      font-weight: bold;
      padding: 50px 0px;
      display: block;
    }
  }
}
</style>
<script>
export default {
  name: "menulist",
  data() {
    return {
      menuactive: "0",
      menudata: [
        {
          icon: require("../../assets/images/menu_tb1.png"),
          num: "0",
          tittle: "站点首页",
          link: "/",
        },
        {
          icon: require("../../assets/images/menu_tb2.png"),
          num: "1",
          tittle: "加油记录",
          link: "/pullrecord",
        },
        {
          icon: require("../../assets/images/menu_tb3.png"),
          num: "2",
          tittle: "维修记录",
          link: "/repairrecord",
        },
        {
          icon: require("../../assets/images/menu_tb4.png"),
          num: "3",
          tittle: "油站设置",
          link: "/pullset",
        },
        {
          icon: require("../../assets/images/menu_tb5.png"),
          num: "4",
          tittle: "油站优惠设置",
          link: "/discountset",
        },
        {
          icon: require("../../assets/images/menu_tb6.png"),
          num: "5",
          tittle: "维修站设置",
          link: "/repairset",
        },
        {
          icon: require("../../assets/images/menu_tb7.png"),
          num: "6",
          tittle: "订单核销",
          link: "/wiped",
        },
      ],
    };
  },
  computed: {
    getmenueobj() {
      return this.$store.state.menueobj;
    },
  },
  created() {
    this.menuan(this.$route.path, this.$route.query.urlid);
  },
  watch: {
    getmenueobj(data) {
      this.menuactive = data.menuemun;
    },
    // $route(to, from) {
    //    //console.log(to.path);
    //    if(to.path=='/'){
    //     this.menuactive='0'
    //      this.$router.push({path:"/",query:{urlid:'0'}});
    //    }
    // }
  },
  mounted() {},
  methods: {
    //导航跳转
    menuan(url, num) {
      if (url) {
        this.$router.push({ path: url, query: { urlid: num } });
        this.$store.commit("savemenueobj", { menueurl: url, menuemun: num });
      } else {
      }
    },
  },
};
</script>