<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
  created() {
    console.log('浏览器',navigator.userAgent)
    let dev = String(navigator.userAgent)

    if(dev.indexOf('Mozilla') > -1) {
      this.$cookies.set(
        "token",
        "7c6d8601ba874dd7aa74d4bfc4d22b1c"
      );
    } else {
      this.$cookies.set(
      "token",
      "6ec7c49f758a4296bf17b7410f2182cb"
    );
    }
    

    
  }
};
</script>

<style lang='scss'>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #000;
  // background: #f5f5f5;
  margin: 0;
  padding: 0;
  border: 0;
  box-sizing: border-box;
  width: 100vw;
  height: 100vh;
  overflow: -Scroll;
  overflow-x: hidden;
}
// html,
// body,
// #app {
//   width: 100%;
//   height: 100%;
// }
// >>> .el-form-item__label {
//   color: #000 !important;
// }
</style>
