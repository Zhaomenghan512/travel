<template>
  <div >
    <div class="login-wrapper">
      <div id="login">
        <p class="title">旅游后台管理系统</p>
        <el-form
          :model="loginForm"
          status-icon
          :rules="rules2"
          ref="ruleForm2"
          label-width="80"
          class="demo-ruleForm"
          width="100%"
        >
          <el-row :gutter="10">
            <el-col :span="24">
              <el-form-item prop="username" label="账号">
                <el-input v-model="loginForm.username" auto-complete="off" placeholder="请输入账号"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="10">
            <el-col :span="24">
              <el-form-item prop="password" label="密码">
                <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="请输入密码"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item >
            <el-button :loading="loading"  type="primary" @click.native.prevent="doLogin" style="width:100%;">登录</el-button>
            <p></p>
            <p></p>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>
  import { MessageBox, Message } from 'element-ui'
  export default {
    name: "Login",
    data() {
      return {
        loginForm: {
          username:"",
          password: ""
        },
        rules2: {
          username: [{ required: true, trigger: 'blur',message:'用户名不能为空'}],
          password: [{ required: true, trigger: 'blur',message:'密码不能为空'}]
        },
         loading:false
     }
    },
    beforeCreate () {
      document.querySelector('body').setAttribute('style',' width: 100%; background:#663399;height: 100%;background:url(../../../static/bg/bg.jpg)');
    },
    methods: {
      gotoReg(){
        this.$router.push({
          path: "/reg"
        });
      },
      doLogin() {
        this.loading = true
        this.$store.dispatch('sysuser/login',this.loginForm).then(()  => {
          this.$router.push({ path: '/' })
          this.loading = false
        }).catch(() => {
          this.loading = false
          Message.error('用户名或者密码不正确!')
        })
      }
    },
    mounted(){
      //this.getClasses();
    }
  };
</script>

<style scoped>

  .login-wrapper img {
    position: absolute;
    z-index: 1;
    margin-top: 30px;
    margin-bottom: 30px;
  }
  .login-wrapper {

  }
  #login {
    position:absolute;
    max-width:460px;
    left:0;
    right:0;
    top:200px;
    bottom: auto;
    margin:auto;
    background: #fff;
    padding: 0px 40px;
    border-radius: 10px;

  }
  .title {
    padding-top: 20px;
    margin-bottom: 20px;
    font-size: 26px;
    line-height: 40px;
    font-weight: bold;
    text-align: center;
  }

  .reg {
    margin-top: 10px;
    font-size: 14px;
    line-height: 22px;
    color: #1ab2ff;
    cursor: pointer;
    text-align: left;
    text-indent: 8px;
    width: 160px;
  }
  .reg:hover {
    color: #2c2fd6;
  }

</style>
