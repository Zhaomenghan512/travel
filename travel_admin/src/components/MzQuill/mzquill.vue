<template>
  <div class="edit_container">
    <quill-editor
      v-model="content"
      ref="myQuillEditor"
      :options="editorOption"
      @blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
      @change="onEditorChange($event)">
    </quill-editor>
  </div>
</template>

<script>

  export default {
    name: 'App',
    data(){
      return {
        content: "",
        editorOption: {
          modules:{
            toolbar:[
              ['bold', 'italic', 'underline', 'strike'], //加粗，斜体，下划线，删除线
              ['blockquote', 'code-block'],  //引用，代码块
              [{ 'header': 1 }, { 'header': 2 }],  // 标题，键值对的形式；1、2表示字体大小
              [{ 'list': 'ordered'}, { 'list': 'bullet' }],  //列表
              [{ 'script': 'sub'}, { 'script': 'super' }], // 上下标
              [{ 'indent': '-1'}, { 'indent': '+1' }],  // 缩进
              [{ 'direction': 'rtl' }],    // 文本方向
              [{ 'size': ['small', false, 'large', 'huge'] }], // 字体大小
              [{ 'header': [1, 2, 3, 4, 5, 6, false] }],  //几级标题
              [{ 'color': [] }, { 'background': [] }],  // 字体颜色，字体背景颜色
              [{ 'font': [] }],  //字体
              [{ 'align': [] }], //对齐方式
              ['clean'], //清除字体样式
              ['image','video'] //上传图片、上传视频
            ]
          },
          theme:'snow',
          placeholder: "请输入内容..."
        }
      }
    },
    computed: {
      editor() {
        return this.$refs.myQuillEditor.quill;
      },
    },
    methods: {
      onEditorReady(editor) { // 准备编辑器

      },
      onEditorBlur(){}, // 失去焦点事件
      onEditorFocus(){}, // 获得焦点事件
      onEditorChange(){}, // 内容改变事件
      getHtml:function(){
         return this.content
      },
      setHtml(content){
        this.content = content
      }
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
  .edit_container {
    margin: 40px 0;
  }
  .quill-editor {
    height: 260px;
  }
</style>

