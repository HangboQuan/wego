<template>
  <div id="aside">
    <div class="aside">
      <h4>
        <i style="font-size: 18px" class="el-icon-chat-dot-square"></i> 讨论区
      </h4>
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        @select="handleSelect"
        mode="horizontal "
        style="width: 249px"
      >
        <el-menu-item
          v-for="(item, index) in asideData"
          :key="index"
          :index="item.asideIndex.toString()"
          @click="toLink(item)"
          style="width: 250px"
          ><i :class="item.icon"></i>{{ item.name }}
        </el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      activeIndex: "1",
      asideData: [
        {
          asideIndex: 1,
          name: "考研交流",
          icon: "el-icon-sunny",
          url: "index",
        },
        {
          asideIndex: 2,
          name: "找工作交流",
          icon: "el-icon-sunny",
          url: "JobDiscuss",
        },
        {
          asideIndex: 3,
          name: "日常学习问题",
          icon: "el-icon-sunny",
          url: "study_day",
        },
        {
          asideIndex: 4,
          name: "寻物启事",
          icon: "el-icon-sunny",
          url: "find",
        },
        {
          asideIndex: 5,
          name: "拼单拼车",
          icon: "el-icon-sunny",
          url: "put_together",
        },
        {
          asideIndex: 6,
          name: "表白墙",
          icon: "el-icon-sunny",
          url: "wall",
        },
        {
          asideIndex: 7,
          name: "生活趣事",
          icon: "el-icon-sunny",
          url: "life_intersting",
        },
        {
          asideIndex: 8,
          name: "竞赛组队",
          icon: "el-icon-sunny",
          url: "other",
        },
      ],
    };
  },
  components: {},
  mounted() {
    this.activeIndex = this.$route.params.url;
  },
  methods: {
    toLink(num) {
      console.log(num.url);
      //   this.activeIndex = num.asideIndex;
      this.$router.push({ name: num.url, query: { url: num.asideIndex } });
    },
    handleSelect(value) {
      console.log(value);
      //   this.activeIndex = value;
    },
  },
};
</script >

<style lang="scss" scoped>
#aside {
  width: 250px;
  height: 500px;
  border-right: 1px solid rgb(189, 187, 187);
  box-sizing: border-box;
  .aside {
    position: relative;
    h4 {
      font-size: 16px;
      position: absolute;
      left: 25px;
      top: 15px;
      font-weight: 500;
      color: rgb(83, 82, 82);
      z-index: 999;
    }

    ul {
      padding-top: 55px;
      li {
        height: 55px;
        font-size: 14px;
        text-align: left;
        line-height: 55px;
        padding-left: 25px;
        /deep/.el-menu-item.is-active {
          border-right: 2px solid #409eff !important;
          background: #f0faff;
          color: #2d8cf0;
        }
        /deep/.el-menu-item.is-active {
          border-right: 2px solid #409eff !important;
          background: #f0faff !important;
          color: #2d8cf0 !important;
        }
        /deep/.el-menu.el-menu--horizontal {
          border-bottom: 0;
        }
        i {
          margin-right: 15px;
          font-size: 20px;
        }
        &:hover {
          color: #2d8cf0;
          background: #f0faff;
          border-right: 2px solid #2d8cf0 !important;
        }
      }
    }
  }
}
</style>
