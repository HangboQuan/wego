<template>
  <div>
    <!-- <div class="header" style="display: flex"> -->
    <el-row style="border-bottom: 1px solid #ebeef5; margin-bottom: 10px">
      <el-col :span="4">
        <div class="logo">
          <img src="../assets/logo.jpg" alt="" />
          <p>社区</p>
        </div>
      </el-col>
      <el-col :span="12">
        <div
          class="write"
          style="
            text-align: left;
            font-size: 20px;
            margin-top: 30px;
            color: #999;
          "
        >
          <i class="el-icon-edit"></i> 写文章
        </div>
      </el-col>
      <el-col :span="8" style="text-align: right">
        <el-button type="primary" style="margin-top: 20px" @click="Return()"
          >取消</el-button
        ><el-button v-if="!isEdit" type="primary" style="margin-right: 20px" @click="Publish()"
          >发布</el-button
        >
        <el-button v-else type="primary" style="margin-right: 20px" @click="mode()"
          >编辑完成</el-button
        >
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" style="border-bottom: 1px solid #ebeef5">
        <el-input
          v-model="input"
          placeholder="文章标题：一句话说明你遇到的问题或你想分享的经验"
        ></el-input>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="19" style="margin-left: 10px">
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
          style="margin-top: 10px"
        >
          <el-form-item label="文章分类：" prop="resource">
            <el-radio-group @change="radioChange" v-model="ruleForm.resource">
              <el-radio label="考研交流"></el-radio>
              <el-radio label="找工作交流"></el-radio>
              <el-radio label="日常学习"></el-radio>
              <el-radio label="寻物启事"></el-radio>
              <el-radio label="拼单拼车"></el-radio>
              <el-radio label="表白墙"></el-radio>
              <el-radio label="生活趣事"></el-radio>
              <el-radio label="竞赛组队"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <!-- </div> -->
    <el-card style="height: 610px">
      <quill-editor
        v-model="content"
        ref="myQuillEditor"
        style="height: 500px"
        :options="editorOption"
      >
        <!-- 自定义toolar -->
        <div id="toolbar" slot="toolbar">
          <!-- Add a bold button -->
          <button class="ql-bold" title="加粗">Bold</button>
          <button class="ql-italic" title="斜体">Italic</button>
          <button class="ql-underline" title="下划线">underline</button>
          <button class="ql-strike" title="删除线">strike</button>
          <button class="ql-blockquote" title="引用"></button>
          <button class="ql-code-block" title="代码"></button>
          <button class="ql-header" value="1" title="标题1"></button>
          <button class="ql-header" value="2" title="标题2"></button>
          <!--Add list -->
          <button class="ql-list" value="ordered" title="有序列表"></button>
          <button class="ql-list" value="bullet" title="无序列表"></button>
          <!-- Add font size dropdown -->
          <select class="ql-header" title="段落格式">
            <option selected>段落</option>
            <option value="1">标题1</option>
            <option value="2">标题2</option>
            <option value="3">标题3</option>
            <option value="4">标题4</option>
            <option value="5">标题5</option>
            <option value="6">标题6</option>
          </select>
          <select class="ql-size" title="字体大小">
            <option value="10px">10px</option>
            <option value="12px">12px</option>
            <option value="14px">14px</option>
            <option value="16px" selected>16px</option>
            <option value="18px">18px</option>
            <option value="20px">20px</option>
          </select>
          <select class="ql-font" title="字体">
            <option value="SimSun">宋体</option>
            <option value="SimHei">黑体</option>
            <option value="Microsoft-YaHei">微软雅黑</option>
            <option value="KaiTi">楷体</option>
            <option value="FangSong">仿宋</option>
            <option value="Arial">Arial</option>
          </select>
          <!-- Add subscript and superscript buttons -->
          <select class="ql-color" value="color" title="字体颜色"></select>
          <select
            class="ql-background"
            value="background"
            title="背景颜色"
          ></select>
          <select class="ql-align" value="align" title="对齐"></select>
          <button class="ql-clean" title="还原"></button>
          <!-- You can also add your own -->
        </div>
      </quill-editor>
    </el-card>
  </div>
</template>

<script>
import { Quill, quillEditor } from "vue-quill-editor";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";

//引入font.css
import "@/assets/css/font.css";

// 自定义字体大小
let Size = Quill.import("attributors/style/size");
Size.whitelist = ["10px", "12px", "14px", "16px", "18px", "20px"];
Quill.register(Size, true);

// 自定义字体类型
var fonts = [
  "SimSun",
  "SimHei",
  "Microsoft-YaHei",
  "KaiTi",
  "FangSong",
  "Arial",
  "Times-New-Roman",
  "sans-serif",
  "宋体",
  "黑体",
];
var Font = Quill.import("formats/font");
Font.whitelist = fonts;
Quill.register(Font, true);

export default {
  name: "FuncFormsEdit",
  components: {
    quillEditor,
  },
  data() {
    return {
      content: null,
      input: "",
      ruleForm: {
        resource: "",
      },
      isEdit: false,
      editorOption: {
        placeholder: "请输入",
        theme: "snow", // or 'bubble'
        modules: {
          toolbar: {
            container: "#toolbar",
          },
        },
      },
      rules: {},
      myCookie: '',
      articleId: ''
    };
  },
  mounted() {
    this.getCookie();
    console.log('页面传参',this.$route)

    this.input = this.$route.params.articleTitle;
    this.content = this.$route.params.articleContent;
    this.ruleForm.resource = this.$route.params.articleCategoryName;
    this.isEdit = this.$route.params.isEdit;
    this.articleId = this.$route.params.articleId;
  },
  methods: {
    radioChange() {
      //console.log(this.ruleForm.resource)
    },
    getCookie() {
      let cookie = document.cookie;
     
      let myCookie = cookie.split(';');
      let cookieObj = {}
      for(let i = 0;i < myCookie.length;i++) {
        cookieObj[myCookie[i].split('=')[0]] = myCookie[i].split('=')[1]
      }
      console.log(11123132,cookieObj)
      this.myCookie = cookieObj['Admin-Token']
    },
    Return() {
      // console.log(this.input);
      this.$router.go(-1);
    },
    mode() {
      var reg = /<\/?.+?\/?>/g;
        // console.log(this.content.replace(reg, ""));
        var url = "http://38617112yi.zicp.vip/article/edit";
        // var url = "/insert";
        var _self = this;
        let articleTitle = this.input;
        let articleContent = this.content.replace(reg, "");
        // let articleCategoryId = "1";
        var obj = {
          articleTitle: articleTitle,
          articleContent: articleContent,
          articleCategoryName: _self.ruleForm.resource,
          //cookie: this.myCookie
          articleId: _self.articleId
        };
        var article = JSON.stringify(obj);
       
        
         _self.$axios.defaults.withCredentials = true 
        _self.$axios
          .post(url ,article, {
            headers: { "Content-Type": "application/json;charset=utf-8" ,
              //"Cookie": this.myCookie
            },
            withCredentials: true,
            //baseURL: '/apis'
          })
          .then((res) => {
            console.log(res.data);
            if (res.status == 200) {
              if (res.data.code == 0) {
                 _self.input = '';
                 _self.content = '';
                _self.$message.success("发布成功～");
              }
              this.$router.push({ 
              name:'index',
            })
            }
          })
          .catch((err) => {
            console.log(err);
          });
    },
    Publish() {
      if (this.input && this.content) {
        var reg = /<\/?.+?\/?>/g;
        // console.log(this.content.replace(reg, ""));
        var url = "http://38617112yi.zicp.vip/article/insert";
        // var url = "/insert";
        var _self = this;
        let articleTitle = this.input;
        let articleContent = this.content.replace(reg, "");
        // let articleCategoryId = "1";
        var obj = {
          articleTitle: articleTitle,
          articleContent: articleContent,
          articleCategoryName: _self.ruleForm.resource,
          //cookie: this.myCookie
        };
        var article = JSON.stringify(obj);
        // let cookieParams = JSON.stringify({
        //     cookie: this.myCookie
        // })
        
         _self.$axios.defaults.withCredentials = true 
        _self.$axios
          .post(url ,article, {
            headers: { "Content-Type": "application/json;charset=utf-8" ,
              //"Cookie": this.myCookie
            },
            withCredentials: true,
            //baseURL: '/apis'
          })
          .then((res) => {
            console.log(res.data);
            if (res.status == 200) {
              if (res.data.code == 0) {
                 _self.input = '';
                 _self.content = '';
                _self.$message.success("发布成功～");
              }
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        this.$message.error("发布内容不能为空");
      }
      // console.log("11111");
    },
  },
};
</script>

<style scoped lang="scss">
.logo {
  width: 160px;
  height: 60px;
  line-height: 20px;
  display: flex;
  margin-right: 100px;
  margin-top: 10px;
  img {
    width: 70px;
    margin-left: 10px;
  }
  p {
    font-size: 22px;
    margin-top: 25px;
    color: rgb(63, 61, 61);
  }
}
/deep/.el-input__inner {
  -webkit-appearance: none;
  background-color: #fff;
  background-image: none;
  border-radius: 4px;
  border: 0 !important;
  box-sizing: border-box;
  color: #606266;
  display: inline-block;
  font-size: 24px;
  height: 60px;
  // line-height: 100px;
  outline: 0;
  padding: 0 45px;
  transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
  width: 100%;
  // border-bottom: 1px solid #ebeef5;
}
/deep/.el-card {
  // border: 0 !important;
  border: 1px solid #ebeef5;
  background-color: #fff;
  color: #303133;
  transition: 0.3s;
}
/deep/.el-card__body {
  padding: 10px;
}
/deep/.el-form-item {
  margin-bottom: 12px !important;
}
</style>

