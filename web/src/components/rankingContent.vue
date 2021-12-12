<template>
  <div class="content">
    <div class="con">
      <div
        style="
          width: 100%;
          height: 60px;
          border-bottom: 1px solid #eee;
          position: relative;
          margin-top: 5px;
          margin-bottom: 5px;
        "
        v-for="(item, index) in rankingList"
        :key="index"
      >
        <el-avatar
          :size="45"
          :src="item.avatar"
          style="
            display: inline-block;
            position: absolute;
            left: 30px;
            top: 10px;
            cursor: pointer;
          "
        ></el-avatar>
        <p
          style="
            display: inline-block;
            position: absolute;
            left: 85px;
            top: 20px;
            font-size: 15px;
            cursor: pointer;
            color: #e8ca2d;
            font-weight: 600;
          "
        >
          {{ item.nickname }}
         
        </p>
        <p
          style="
            display: inline-block;
            position: absolute;
            right: 25px;
            top: 20px;
            font-size: 15px;
            cursor: pointer;
            color: #999;
          "
        >
          成就值{{ item.achieveValue }}
        </p>
      </div>
    </div>
    <!-- {{rankingList}} -->
      <!-- <div class="rankingItem"  v-for="(item,index) in rankingList" :key="index">
          {{item.nickname}}
          <img :src="item.avatar" alt="">
          成就值：{{item.achieveValue}}
      </div> -->
  </div>
</template>
<script>
export default {
  name: "Content",
  props: [
    'rankingList'
  ],
  data() {
    return {
      activeIndex: "1",
      display: true,
      listData: [
        {
          name: "大大大菠萝🍍",
          content: "如何30天完成专业课复习？学姐笔记等你来call!!!",
        },
      ],
    };
  },
  mounted() {
      console.log('排行榜111')
      //this.getRanking()
  },
  methods: {
      
    handleSelect(value) {
      console.log(value);
      if (value == "1") {
        this.display = true;
      } else if (value == "2") {
        this.display = false;
      }
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
  // position: absolute;
  // right: 20px;
  // bottom: 0px;
  .info_header {
    width: 100%;
    height: 60px;
  }

  .rankingItem {
    img {
      width:30px;
      height:30px;
    }
  }
}
/deep/.el-menu-item {
  font-size: 14px;
  color: #303133;
  padding: 0 207px !important;
  cursor: pointer;
  transition: border-color 0.3s, background-color 0.3s, color 0.3s;
  box-sizing: border-box;
}
</style>