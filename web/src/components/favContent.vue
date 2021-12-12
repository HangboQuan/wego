<template>
  <div class="content">
    <div class="favHeader">
      <div
        style="
          width: 170px;
          height: 40px;
          background: #409eff;
          color: #fff;
          line-height: 45px;
        "
      >
        <i class="el-icon-star-on" style="margin-right: 10px"></i>关注用户：
      </div>
    </div>
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
        v-for="(item, index) in followList"
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
        <!-- <p
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
          {{ item.createTime }}
        </p> -->
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Content",
  data() {
    return {
      imgSrc: require("@/assets/img2.jpg"),
      listData: [
        {
          imgSrc: require("@/assets/img2.jpg"),
          name: "大大大菠萝🍍",
          time: "2021-2-1 10:30",
        },
        {
          imgSrc: require("@/assets/img2.jpg"),
          name: "大大大菠萝🍍",
          time: "2021-2-1 10:30",
        },
        {
          imgSrc: require("@/assets/img2.jpg"),
          name: "大大大菠萝🍍",
          time: "2021-2-1 10:30",
        },
        {
          imgSrc: require("@/assets/img2.jpg"),
          name: "大大大菠萝🍍",
          time: "2021-2-1 10:30",
        },
        {
          imgSrc: require("@/assets/img2.jpg"),
          name: "大大大菠萝🍍",
          time: "2021-2-1 10:30",
        },
        {
          imgSrc: require("@/assets/img2.jpg"),
          name: "大大大菠萝🍍",
          time: "2021-2-1 10:30",
        },
        {
          imgSrc: require("@/assets/img2.jpg"),
          name: "大大大菠萝🍍",
          time: "2021-2-1 10:30",
        },
      ],
      followList: []
    };
  },
  mounted() {
    this.guanzhuList()
  },
  methods: {
    guanzhuList() {
      var _self = this;
      var url = "http://38617112yi.zicp.vip/follow/list";
      //var categoryId = this.id;

      _self.$axios
        .get(url)
        .then((res) => {
          if (res.status == 200) {
            
              console.log('关注列表',res.data)
              //let args = res.data.data;
              // let list = []
              // args.forEach(item => {
              //   list.push(item.userId)
              // });
              _self.followList = res.data.data;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  }

};
</script>
<style lang="scss" scoped>
.content {
  width: 950px;
  height: 500px;
  background: #fff;
  border-radius: 4px;
  .favHeader {
    font-size: 15px;
    width: 100%;
    height: 40px;
    border-bottom: 2px solid #409eff;
  }
  .con {
    width: 100%;
    height: 460px;
    overflow-y: scroll;
  }
}
</style>