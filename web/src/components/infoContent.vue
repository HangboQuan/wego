<template>
  <div class="content">
    <div class="info_header">
      <!-- <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
      >
        <el-menu-item index="1"
          ><i class="el-icon-star-on"></i> 点赞</el-menu-item
        >
        <el-menu-item index="2"
          ><i class="el-icon-chat-line-round"></i> 回复</el-menu-item
        >
      </el-menu> -->
    </div>
    <div class="zan" v-if="display == true">
      <div
        class="list"
        style="
          width: 100%;
          height: 45px;
          margin-top: 10px;
          position: relative;
          line-height: 45px;
          font-size: 14px;
          border-bottom: 1px solid #eee;
        "
        v-for="(item, index) in listData"
        :key="index"
      >
        <p style="position: absolute; left: 20px">
          <span style="color: #e8ca2d; font-size: 15px; cursor: pointer">{{
            item.name
          }}</span>
          <span style="font-weight: 700; cursor: pointer"
            >“{{ item.content }}”
          </span>
        </p>
        <!-- <p style="position: absolute; right: 20px">2021-2-21 10:30</p> -->
      </div>
    </div>
    <div class="huifu" v-else>
      <div
        class="list"
        style="
          width: 100%;
          height: 45px;
          margin-top: 10px;
          position: relative;
          line-height: 45px;
          font-size: 14px;
          border-bottom: 1px solid #eee;
        "
        v-for="(t, i) in huifu"
        :key="i"
      >
        <p style="position: absolute; left: 20px">
          <span style="color: #e8ca2d; font-size: 15px; cursor: pointer">{{
            t.name
          }}</span>
          刚刚回复了您的帖子<span style="font-weight: 700; cursor: pointer"
            >“{{ t.content }}”
          </span>
        </p>
        <p style="position: absolute; right: 20px">2021-4-21 10:30</p>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Content",
  data() {
    return {
      activeIndex: "1",
      display: true,
      listData: []
    };
  },
  mounted() {
    this.newLikes()
  },
  methods: {
    newLikes() {
      var _self = this;
      var url = "http://38617112yi.zicp.vip/notice/list";
      _self.$axios
        .get(url)
        .then((res) => {
          if (res.status == 200) {
            
              console.log('新消息',res.data)
              _self.listData = res.data.data;
              //let args = res.data.data;
              // let list = []
              // args.forEach(item => {
              //   list.push(item.userId)
              // });
              //_self.followList = res.data.data;
            //    _self.userObj = res.data.data;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
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
  .zan {
    width: 100%;
    height: 440px;
    overflow-y: scroll;
  }
  .huifu {
    width: 100%;
    height: 440px;
    overflow-y: scroll;
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