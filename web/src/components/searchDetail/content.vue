<template>
  <div class="content">
    <div class="left">
      <div class="search">
        <!-- <el-input
          v-model="inputSearch"
          @keyup.enter.native="onEnterSearch"
          placeholder="请输入搜索内容"
          style=""
        >
        </el-input> -->
        <!-- {{listData}} -->
        <!-- <el-button
          icon="el-icon-back"
          circle
          @click="Return()"
          style="
            position: absolute;
            left: 0px;
            top: 12px;
            font-size: 19px;
            padding: 8px;
          "
        ></el-button> -->
        <!-- <el-button
          style="position: absolute; right: 179px; top: 12px; padding: 9px 20px"
          type="primary"
          plain
          icon="el-icon-search"
        ></el-button> -->
      </div>
      <div class="con">
        <div class="list" v-for="(item, index) in listData" :key="index" @click="goDetail(item.articleId)">  
          <el-avatar
            :size="55"
            :src="item.imgSrc"
            style="position: absolute; left: 10px; top: 12px"
          ></el-avatar>
          <p style="font-size: 15px; position: absolute; left: 75px; top: 15px">
            {{ item.articleTitle }}
          </p>
          <p
            style="
              font-size: 14px;
              color: red;
              position: absolute;
              left: 75px;
              top: 50px;
            "
          >
            {{ item.avatar }}
          </p>
          <p
            style="
              font-size: 14px;
              position: absolute;
              left: 195px;
              top: 50px;
              color: #999;
            "
          >
            发表时间：{{ item.updateTime }}
          </p>
          <p
            style="
              font-size: 14px;
              position: absolute;
              right: 195px;
              top: 50px;
              color: #999;
            "
          >
            回复 {{ item.articleCommentCount }}
          </p>
          <p style="position: absolute; right: 180px; top: 50px; color: #999">
            |
          </p>
          <p style="position: absolute; right: 105px; top: 50px; color: #999">
            |
          </p>
          <p
            style="
              font-size: 14px;
              position: absolute;
              right: 120px;
              top: 50px;
              color: #999;
            "
          >
            赞 {{ item.articleLikeCount }}
          </p>
          <p
            style="
              font-size: 14px;
              position: absolute;
              right: 30px;
              top: 50px;
              color: #999;
            "
          >
            浏览 {{ item.articleViewCount }}
          </p>
        </div>
      </div>
    </div>
    <!-- <div class="right">
      <div class="head">Wego 热搜</div>
      <div
        style="
          display: flex;
          width: 100%;
          height: 45px;
          line-height: 50px;
          border-bottom: 1px #eee solid;
        "
        v-for="(item, index) in hotSearch"
        :key="index"
      >
        <p style="font-size: 15px; color: #333; margin-left: 20px">
          {{ item.content }}
        </p>
        <img
          :src="item.imgSrc"
          alt=""
          style="width: 20px; height: 20px; margin-left: 15px; margin-top: 12px"
        />
      </div>
    </div> -->
  </div>
</template>
<script>
export default {
  name: "searchDetail",
  data() {
    return {
      inputSearch: "",
      imgSrc: require("@/assets/img1.webp"),
      listData: [
      
      ],
    };
  },
  mounted() {
    console.log('到了搜索详情')
    console.log(this.$route.params)
    this.listData = this.$route.params.search;
    console.log(this.listData)
  },
  methods: {
    goDetail(id) {
      this.$router.push({ name: "Detail", query: { url: id } });
    },
    Return() {
      this.$router.push({ name: "index" });
    },
  },
};
</script>
<style lang="scss" scoped>
.content {
  width: 1100px;
  height: 450px;

  margin: 30px auto;
  display: flex;
  justify-content: space-between;
  .left {
    width: 69%;
    height: 470px;
    border: 1px solid #d4d2d2;
    border-radius: 5px;
    .search {
      width: 100%;
      height: 60px;
      border-bottom: 1px #d4d2d2 solid;
      position: relative;
      .el-input {
        position: absolute;
        left: 200px;
        top: 12px;
        font-size: 14px;
        display: inline-block;
        width: 50%;
      }
      /deep/.el-input__inner {
        -webkit-appearance: none;
        background-color: #fff;
        background-image: none;
        border-radius: 0px;
        border: 1px solid #dcdfe6 !important;
        box-sizing: border-box;
        color: #606266;
        display: inline-block;
        font-size: inherit;
        height: 35px;
        line-height: 35px;
        outline: 0;
        padding: 0 30px;
        transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
        width: 100%;
      }
    }
    .con {
      background: #fff;
      width: 100%;
      height: 410px;
      overflow-y: scroll;
      .list {
        width: 99%;
        height: 80px;
        margin-bottom: 10px;
        border-bottom: 1px #d1cccc solid;
        position: relative;
      }
    }
  }
  .right {
    width: 29%;
    height: 470px;
    border: 1px solid #d4d2d2;
    border-radius: 5px;
    background: #fff;
    .head {
      width: 100%;
      height: 50px;
      font-size: 16px;
      color: #807d7d;
      font-weight: 700;
      //   border-bottom: 1px solid #eee;
      line-height: 55px;
      //   margin-left: 10px;
      text-align: left;
      padding-left: 10px;
      &:before {
        content: "";
        width: 8px;
        height: 8px;
        display: inline-block;
        background: red;
        margin-right: 10px;
        margin-top: 20px;
      }
    }
  }
}
</style>