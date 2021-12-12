<template>
  <div class="header">
    <div class="line"></div>
    <div class="text">
      <p>Wego 社区</p>
      <h4>专注校园信息</h4>
    </div>
  </div>
</template>
<script>
export default {
  name: "checkout",
  data() {
    return {};
  },
  components: {},
};
</script>
<style lang="scss" scoped>
.header {
  width: 100%;
  height: 100px;
  background-color: #fff;
  box-shadow: 2px 2px 4px 4px rgb(238, 232, 232);
  box-sizing: border-box;
  position: relative;
  .line {
    width: 80%;
    height: 4px;
    border-radius: 100% 100%;
    position: absolute;
    left: 10%;
    top: 49px;
    z-index: 1;
    background: #666666;
  }
  .text {
    position: absolute;
    left: 40%;
    top: 0;
    width: 300px;
    height: 100px;
    background: #fff;
    z-index: 999;

    p {
      font-size: 34px;
      text-align: center;
      margin-top: 10px;
    }
    h4 {
      //   margin-top: 10px;
      text-align: center;
      font-size: 20px;
      color: rgb(247, 194, 96);
    }
  }
}
</style>