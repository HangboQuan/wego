<template>
  <div class="info">
    <el-container>
      <el-header style="padding: 0; height: 80px">
        <Header></Header>
      </el-header>
      <el-container>
        <el-aside width="252px" style="margin-left: 40px; margin-top: 20px">
          <Aside></Aside
        ></el-aside>
        <el-main style="padding: 20px 0; height: 500px; overflow: none"
          ><Main></Main
        ></el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import Header from "../components/banner.vue";
import Aside from "../components/personal/aside.vue";
import Main from "../components/infoContent.vue";
export default {
  name: "info",
  components: { Header, Aside, Main },
};
</script>
<style lang="scss" scoped>
.info {
  width: 1278px;
  height: 610px;
  background: #f3f1f1;
}
/deep/.el-main {
  display: block;
  flex: 1;
  flex-basis: auto;
  overflow: none !important;
  box-sizing: border-box;
  padding: 20px;
}
</style>