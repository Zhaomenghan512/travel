<template>
    <div class='aboutsCls'>
        <el-collapse v-model="activeNames" @change="handleChange">
  <el-collapse-item  title="关于我们" name="1">
    <div >凤凰旅游网是一个在线旅游平台，是一家本地综合性旅游服务平台.</div>
    <div >带您领略四川的每一处风景！我们相信，通过我们的不断努力和追求，一定能够让旅行变的更加自在有趣！</div>
  </el-collapse-item>

  <el-collapse-item  title="商务合作" name="3">
    <div >合作联系人：赵梦涵</div>
    <div >联系邮箱：9426797@qq.com</div>
    <div >联系QQ：9426797</div>
  </el-collapse-item>
</el-collapse>
</div>
</template>
<script>
  export default {
    data() {
      return {
        activeNames: ['1','2','3']
      };
    },
    methods: {
      handleChange(val) {
        console.log(val);
      }
    }
  }
</script>
<style lang="less">
    .aboutsCls{
        margin-left:@lrwith;
        margin-right: @lrwith;
        margin-top:30px;
        margin-bottom:30px;
        .el-collapse-item__header{
             font-size:20px;
             color:black;
        }
        div{
             font-size:18px;

        }

    }
</style>
