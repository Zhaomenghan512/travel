<template>
  <div>
    <div class="register-wrapper">
      <div id="register">
        <p class="title">注册账号</p>
        <el-form
          :model="ruleForm2"
          status-icon
          :rules="rules2"
          ref="ruleForm2"
          label-width="80"
          class="demo-ruleForm"
          width="100%"
        >
          <el-row :gutter="10">
            <el-col :span="12">
              <el-form-item prop="username" label="用户账号">
                <el-input v-model="ruleForm2.username" auto-complete="off" placeholder="请输入账号"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="email" label="用户邮箱">
                <el-input v-model="ruleForm2.email" auto-complete="off" placeholder="请输入邮箱"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="12">
          <el-form-item prop="tel" label="手机号码">
            <el-input v-model="ruleForm2.tel" auto-complete="off" placeholder="请输入手机号"></el-input>
          </el-form-item>
            </el-col>
            <el-col :span="12">
          <el-form-item prop="stunum" label="学生学号(学生填写)">
            <el-input v-model="ruleForm2.stunum" auto-complete="off" placeholder="请输入学生学号"></el-input>
          </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="12">
              <el-form-item  prop="classesid" label="隶属班级">
                <el-select style="width:100%" v-model="ruleForm2.classesid" placeholder="请选择">
                    <el-option  v-for="item in classes" :key="item.id" :value="item.id" :label="item.name">
                    </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item  prop="sex" label="性别" >
                <el-radio-group v-model="ruleForm2.sex" style="width:100%;text-align:left">
                  <el-radio :label="0">女</el-radio>
                  <el-radio :label="1">男</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="10">
            <el-col :span="12">
              <el-form-item prop="password" label="密码">
                <el-input type="password" v-model="ruleForm2.password" auto-complete="off" placeholder="输入密码"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">

              <el-form-item prop="checkPass" label="确认密码">
                <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="确认密码"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="10">


            <el-col :span="12">
              <el-form-item  prop="headImg" label="用户头像">
                <el-upload
                  class="upload-demo"
                  action="http://localhost/user/file/uploadFile"
                  :on-success="handleAvatarSuccess"
                  :before-upload="beforeAvatarUpload"
                  :file-list="fileList"
                  list-type="picture">
                  <el-button size="small" type="primary">点击上传</el-button>
                  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                </el-upload>
              </el-form-item>
            </el-col>
            <el-col :span="12">
            <el-form-item  prop="type" label="账号类型" >
              <el-radio-group v-model="ruleForm2.type" style="width:100%;text-align:left">
                <el-radio :label="2">我是老师</el-radio>
                <el-radio :label="3">我是学生</el-radio>
              </el-radio-group>
            </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="12">
              <el-form-item prop="address" label="住址">
                <el-input type="address" v-model="ruleForm2.address" auto-complete="off" placeholder="输入地址"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm2')" style="width:100%;">注册</el-button>
            <p class="login" @click="gotoLogin">已有账号？立即登录</p>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: "Register",
    data() {
      // <!--验证手机号是否合法-->
      let checkTel = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入手机号码'))
        } else if (!this.checkMobile(value)) {
          callback(new Error('手机号码不合法'))
        } else {
          callback()
        }
      }
      // <!--验证密码-->
      let validatePass = (rule, value, callback) => {
        if (value === "") {
          callback(new Error("请输入密码"))
        } else {
          if (this.ruleForm2.checkPass !== "") {
            this.$refs.ruleForm2.validateField("checkPass");
          }
          callback()
        }
      }
      // <!--二次验证密码-->
      let validatePass2 = (rule, value, callback) => {
        if (value === "") {
          callback(new Error("请再次输入密码"));
        } else if (value !== this.ruleForm2.password) {
          callback(new Error("两次输入密码不一致!"));
        } else {
          callback();
        }
      };
      return {
        fileList: [],
        classes:[],
        ruleForm2: {
          username:"",
          password: "",
          email:'',
          checkPass: "",
          tel: "",
          classesid: "",
          stunum:'',
          icon:'',
          sex:1,
          type:2,
          address:''
        },
        rules2: {
          username: [{ required: true,message: '请输入用户账号', trigger: 'blur'}],
          email: [{ required: true, message: '请输入邮箱', trigger: 'change' }],
          password: [{ validator: validatePass, trigger: 'change' }],
          checkPass: [{ validator: validatePass2, trigger: 'change' }],
          tel: [{ validator: checkTel, trigger: 'change' }],
        }
      }
    },
    beforeCreate () {
      document.querySelector('body').setAttribute('style',' width: 100%; background:#263445;height: 100%;background: url(../../../static/bg/bg4.jpg)');
    },
    methods: {
    handleAvatarSuccess(res, file) {
      this.ruleForm2.icon = res.data.fileUrl
    },
    beforeAvatarUpload(file) {
      console.log(file.type)
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG/PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
      // <!--提交注册-->
      submitForm(formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            this.$store.dispatch('user/regUser', this.ruleForm2).then((data) => {
                this.$message({
                  message: data.msg,
                  type: 'success'
                });
                //跳转登录页面
                 this.gotoLogin()
              })
              .catch((err) => {
                console.log('失败' + err)
              })
          } else {
            console.log("error submit!!");
            return false;
          }
        })
      },
      // <!--进入登录页-->
      gotoLogin() {
        this.$router.push({
          path: "/login"
        });
      },
      // 验证手机号
      checkMobile(str) {
        let re = /^1\d{10}$/
        if (re.test(str)) {
          return true;
        } else {
          return false;
        }
      },
       getClasses(){
         this.$axios({
           method:'post',
           url:"http://localhost/common/queryClasses"
         }).then(res=>{
            this.classes =  res.data
         }).catch((err)=>{
           console.log('失败' + err)
         })
       }
    },
    mounted(){
      this.getClasses();
    }
  };
</script>

<style scoped>

  .register-wrapper img {
    position: absolute;
    z-index: 1;
    margin-top: 30px;
    margin-bottom: 30px;
  }
  .register-wrapper {

  }
  #register {
    max-width:800px;
    margin-top: 30px;
    margin-left: 400px;
    background: #fff;
    padding: 0px 40px;
    border-radius: 10px;

  }
  .title {
    font-size: 26px;
    line-height: 40px;
    font-weight: bold;
    margin: 10px;
    text-align: center;
  }

  .login {
    margin-top: 10px;
    font-size: 14px;
    line-height: 22px;
    color: #1ab2ff;
    cursor: pointer;
    text-align: left;
    text-indent: 8px;
    width: 160px;
  }
  .login:hover {
    color: #2c2fd6;
  }

</style>
