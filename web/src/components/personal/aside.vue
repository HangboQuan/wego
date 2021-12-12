<template>
  <div class="aside">
    <div
      style="
        width: 100%;
        height: 50px;
        text-align: left;
        padding-left: 15px;
        line-height: 50px;
        font-size: 15px;
      "
    >
      <i class="el-icon-user-solid"></i> 个人中心
    </div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="vertical"
      @select="handleSelect"
    >
      <el-menu-item
        v-for="(item, index) in listData"
        :key="index"
        :index="item.asideIndex.toString()"
        ><i :class="item.icon"></i>
        <el-badge  class="item"
          >{{ item.name }}
        </el-badge></el-menu-item
      >
    </el-menu>
  </div>
</template>
<script>
export default {
  name: "Aside",
  data() {
    return {
      activeIndex: "1",
      listData: [
        {
          asideIndex: 1,
          name: "我的资料",
          icon: "el-icon-user-solid",
          url: "personal",
        },
        {
          asideIndex: 2,
          name: "我的关注",
          icon: "el-icon-star-on",
          url: "my_fav",
        },
        {
          asideIndex: 3,
          name: "我的粉丝",
          icon: "el-icon-lollipop",
          url: "my_fans",
        },
        {
          asideIndex: 4,
          name: "我的消息",
          icon: "el-icon-s-home",
          url: "my_info",
        },
        {
          asideIndex: 5,
          name: "成就值排行榜",
          icon: "el-icon-s-home",
          url: "ranking",
        },
        {
          asideIndex: 6,
          name: "我的私信",
          icon: "el-icon-s-home",
          url: "chat",
        },
      ],
    };
  },
  methods: {
    handleSelect(value) {
      var url = this.listData[value - 1].url;
      this.$router.push({ name: url,
        params: {
          userId: '18392710807'
        }
      });
    },
  },
};
</script>
<style lang="scss" scoped>
.aside {
  width: 230px;
  height: 500px;
  background: #fff;
  border-radius: 4px;
}
/deep/.el-menu {
  border-right: 0 !important;
  list-style: none;
  position: relative;
  margin: 0;
  padding-left: 0;
  background-color: #fff;
}
</style>