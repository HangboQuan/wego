<template>
  <div id="wall">
    <el-container style="background-color: #f7f8f9">
      <el-header style="height: 80px; padding: 0">
        <Banner></Banner>
      </el-header>
      <el-container style="width: 95%; margin: 15px auto 0; background: #fff">
        <el-aside width="250px">
          <Aside></Aside>
        </el-aside>
        <el-main style="padding: 0"><Content></Content></el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Banner from "../components/banner.vue";
import Aside from "../components/discuss/aside.vue";
import Content from "../components/wall/content.vue";
export default {
  name: "discuss",
  components: {
    Banner,
    Aside,
    Content,
  },
};
</script>

<style lang="scss" scoped>
</style>>

