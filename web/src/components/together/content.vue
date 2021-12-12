<template>
  <div id="togetherContent">
    <div class="content">
      <div class="list" v-for="(item, index) in listData" :key="index">
        <el-avatar
          :size="55"
          :src="item.avatar"
          style="display: inline-block; position: absolute; left: 0; top: 20px"
        ></el-avatar>
        <p class="tit">{{ item.articleTitle }}</p>
        <p class="name">{{ item.nickname }}</p>
        <p class="time">发布时间：{{ item.createdTime }}</p>
        <p class="huifu">回复 {{ item.articleCommentCount }}</p>
        <p class="zan">赞 {{ item.articleLikeCount }}</p>
        <p class="liulan">浏览量 {{ item.articleViewCount }}</p>
      </div>
      <!-- <div class="list"></div>
      <div class="list"></div> -->
    </div>
  </div>
</template>
<script>
export default {
  name: "indexContent",
  data() {
    return {
      circleUrl: require("@/assets/img1.webp"),
      listData: [],
      id: "5",
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    getData() {
      var _self = this;
      var url = "http://38617112yi.zicp.vip/category/";
      var categoryId = this.id;
      _self.$axios
        .get(url + categoryId, { params: { categoryId: categoryId } })
        .then((res) => {
          console.log(res.data.data);
          if (res.status == 200) {
            if (res.data.code == 0) {
              // console.log(res.data.data.articleTitle);
              let da = res.data.data;
              _self.listData = da;
            }
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
<style scoped lang="scss">
#togetherContent {
  width: 100%;
  .content {
    width: 96%;
    height: 480px;
    margin: 20px auto 0;
    .list {
      width: 100%;
      height: 90px;
      margin-bottom: 5px;
      border-bottom: 2px solid #eee;
      //   background: #eee;
      position: relative;
      .tit {
        position: absolute;
        left: 65px;
        top: 25px;
        font-size: 15px;
        font-weight: 500;
        color: rgb(83, 82, 82);
      }
      .name {
        position: absolute;
        left: 65px;
        top: 60px;
        font-size: 14px;
        color: rgb(241, 45, 45);
      }
      .time {
        position: absolute;
        left: 200px;
        top: 60px;
        font-size: 14px;
        color: rgb(82, 80, 80);
      }
      .huifu {
        width: 80px;
        position: absolute;
        right: 180px;
        top: 60px;
        font-size: 14px;
        border-right: 2px solid rgb(126, 125, 125);
        color: rgb(82, 80, 80);
      }
      .zan {
        width: 80px;
        border-right: 2px solid rgb(126, 125, 125);
        position: absolute;
        right: 100px;
        top: 60px;
        font-size: 14px;
        color: rgb(82, 80, 80);
      }
      .liulan {
        position: absolute;
        right: 0px;
        top: 60px;
        font-size: 14px;
        color: rgb(82, 80, 80);
      }
    }
  }
}
</style>