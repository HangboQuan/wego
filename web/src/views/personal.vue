<template>
  <div class="personal">
    <el-container>
      <el-header style="padding: 0; height: 80px">
        <Header></Header>
      </el-header>
      <el-container>
        <el-aside width="252px" style="margin-left: 40px; margin-top: 20px">
          <Aside></Aside
        ></el-aside>
        <el-main style="padding: 20px 0px"><Main></Main></el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import Header from "../components/banner.vue";
import Aside from "../components/personal/aside.vue";
import Main from "../components/personal/content.vue";
export default {
  name: "personal",
  components: { Header, Aside, Main },
};
</script>
<style lang="scss" scoped>
.personal {
  width: 1278px;
  height: 610px;
  background: #f3f1f1;
}
</style>