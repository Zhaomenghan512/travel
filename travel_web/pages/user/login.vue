<template>
 <div class='loginContiner'>
        <div class='loginAreaCls'>
            <el-tabs type="border-card" v-model="activeName">
            <el-tab-pane name="loginPanel">
                <span slot="label"> 登录</span>
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="账号" prop="account">
                         <el-row :gutter="24">
                            <el-col :span="19">
                                <el-input placeholder="请输入用户名/手机号/邮箱" prefix-icon="el-icon-user"  type="text" v-model="ruleForm.account" autocomplete="off"></el-input>
                              </el-col>
                          </el-row>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                         <el-row :gutter="24">
                            <el-col :span="19">
                            <el-input placeholder="请输入密码" prefix-icon="el-icon-unlock"  type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                              </el-col>
                          </el-row>
                    </el-form-item>
                     <el-form-item label="验证码" prop="captchaVal">
                        <el-row :gutter="24">
                            <el-col :span="10">
                            <el-input  v-model="ruleForm.captchaVal" placeholder="验证码">
                            </el-input>
                            </el-col>
                            <el-col :span="12" class="login-captcha" >
                               <img ref="codeImg" id="codeImageId" style="height: 40px"  @click="getCaptcha()" alt="">
                            </el-col>
                        </el-row>
                    </el-form-item>
                    <el-form-item>
                        <el-button style="margin-left:10px;" type="primary" @click="loginCustorm()">登录</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                    <hr>
                    <el-form-item style="margin-top: 10px">
                      <img src="../../static/img/login/wx.png" style="height: 50px;width: 50px;margin-right: 10px">
                      <img src="../../static/img/login/wb.png" style="height: 50px;width: 50px;margin-right: 10px">
                      <img src="../../static/img/login/qq.png" style="height: 50px;width: 50px;margin-right: 10px">
                      <img src="../../static/img/login/zfb.png" style="height: 50px;width: 50px;margin-right: 10px">
                    </el-form-item>

                      <div style="margin-left: 30px;color:#999">登录即代表您已阅读并同意《服务协议及隐私政策》</div>

                    </el-form>
            </el-tab-pane>
            <el-tab-pane label="注册" name="regPanel">
                     <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="用户名" prop="username">
                        <el-input placeholder="请输入用户名" style="width:80%" type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
                    </el-form-item>
                     <el-form-item label="手机号" prop="tel">
                       <el-input placeholder="请输入手机号" style="width:80%" type="text" v-model="ruleForm.tel" autocomplete="off"></el-input>
                     </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input placeholder="请输入密码" style="width:80%" type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                    </el-form-item>
                     <el-form-item label="确认密码" prop="repassword">
                        <el-input placeholder="请输入确认密码" style="width:80%" type="password" v-model="ruleForm.repassword" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="email">
                        <el-input placeholder="请输入邮箱" style="width:80%" type="text" v-model="ruleForm.email" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="regUser">注册</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                    </el-form>
            </el-tab-pane>

            </el-tabs>
        </div>
  </div>
</template>
<script>
  import {MessageBox} from 'element-ui'
  export default {
    data() {
      return {
        activeName: 'loginPanel',
        captcha: 'http://localhost/front/getImageCode',

        ruleForm: {
          username: '',
          tel:'',
          password: '',
          repassword:'',
          email:'',
          captchaVal:'',
          captchaToken:'',
          account:''
        },
        rules: {
          account: [
            { required: true, message: '请输入用户名或者邮箱或者电话号码', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ],
          captchaVal: [
            { required: true, message: '请输入验证码', trigger: 'blur' }
          ]}
      };
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      },
      // 获取验证码
      getCaptcha () {

        this.$axios.get('/front/getImageCode', { responseType: 'blob' }).then(res => {
          var img = this.$refs.codeImg;
          let url = window.URL.createObjectURL(res.data);
          img.src = url;
          this.ruleForm.captchaToken = res.headers['access_token'];

        });

      },
      loginCustorm(){
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
             //提交后台进行验证
            this.$store.dispatch('user/loginCustorm', this.ruleForm)
              .then((data) => {
                    if(data.code == 50000) {
                      this.$confirm(data.data, '温馨提示:', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning',
                        customClass: 'myClass'
                      });
                    }else{
                      this.$router.push("/")
                    }
              })
              .catch((err) => {
                MessageBox.alert('注册失败'+err);
              })

          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      //注册信息
      regUser(){
        this.$store.dispatch('user/regCustorm', this.ruleForm)
          .then((data) => {

            if(data.code == 50000) {
              this.$confirm(data.message, '温馨提示:', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                customClass: 'myClass'
              });
            }else{
              this.$alert('恭喜,注册成功,去登录', '温馨提示', {
                confirmButtonText: '确定',
                callback: action => {
                  this.activeName = 'loginPanel';
                  this.ruleForm.username= '';
                  this.ruleForm.password='';
                }
              });
            }

          })
          .catch((err) => {
            MessageBox.alert('注册失败'+err);
          })
      }
    },
    created () {
      this.getCaptcha()
    },
  };
</script>

<style lang="less">
   .loginContiner{
        background-image: url('../../static/img/bj.jpg');

        height:700px;
        .loginAreaCls{
           margin-left:@lrwith;
           margin-right:@lrwith;
                left:80%;
                width: 450px;
                height:50%;
                margin-left:50%;
                padding-top:100px;
		        transition: translateX(-50%);
         }
   }


    .myClass {
      width: 350px;
    }


</style>
