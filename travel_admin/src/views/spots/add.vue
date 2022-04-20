<template>

  <div>
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-row>

        <el-col :span="24">
          <el-form-item label="景区名称" prop="name">
            <el-input style="float:left;width:50%" v-model="form.name" placeholder="请输入景区名称" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="景区地址" prop="address">
            <el-input style="float:left;width:50%" v-model="form.address" placeholder="请输入景区地址" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="景区封面(建议480*360)" label-width="200px" prop="fmUrl" >
            <el-upload style="float:left;width: 50%;"
                       list-type="picture-card"
                       class="avatar-uploader"
                       action="http://localhost/spots/file/uploadFile"
                       :show-file-list="false"
                       :headers="headers"
                       :on-success="handleAvatarSuccess"
                       :before-upload="beforeAvatarUpload"
            >
              <img style="width:200px;height: 200px"  v-if="form.fmUrl" :src="`http://localhost/upload/spots/${form.fmUrl}`"    class="avatar">
<!--              <i style="float:left;width:50%" v-else class="el-icon-plus avatar-uploader-icon" />-->
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="景区开放时间" prop="opentime">
            <el-input style="float:left;width:50%" v-model="form.opentime" placeholder="请输入景区开放时间" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="景区门票价格" prop="price">
            <el-input style="float:left;width:50%" v-model="form.price" type="text" placeholder="请输入门票价格" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="景区联系电话" prop="tel">
            <el-input style="float:left;width:50%" v-model="form.tel" placeholder="请输入景区联系电话" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="景区所在地" prop="spotsid">
            <el-select style="float:left" v-model="form.spotsid" placeholder="请选择">
              <el-option
                v-for="city in cityList"
                :key="city.id"
                :label="city.name"
                :value="city.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="景区等级" prop="levelid">
            <el-select style="float:left" v-model="form.levelid" placeholder="请选择">
              <el-option
                v-for="level in levelList"
                :key="level.id"
                :label="level.levelname"
                :value="level.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="景区主题" prop="themeid">
            <el-select style="float:left" v-model="form.themeid" placeholder="请选择">
              <el-option
                v-for="theme in themeList"
                :key="theme.id"
                :label="theme.themename"
                :value="theme.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="景区描述" prop="spotsDesc">
            <el-input   style="float:left;width: 50%;" type="textarea" v-model="form.spotsDesc"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="24" style="height: 400px">
          <el-form-item label="景区详细描述" prop="info" >
            <vue-quill-editor style="float:left;margin-top:10px;height:100%;width: 80%;"  ref="mychild1"></vue-quill-editor>
          </el-form-item>
        </el-col>

        <el-col :span="24" style="height: 400px">
          <el-form-item label="购买须知" prop="purchaseinfo"  style="height: 300px">
            <vue-quill-editor style="float:left;margin-top:10px;width: 80%;height:100%;"  ref="mychild2"></vue-quill-editor>
          </el-form-item>
        </el-col>

        <el-col :span="24" style="height:400px">
          <el-form-item label="景区特点" prop="features" >
            <vue-quill-editor style="float:left;margin-top:10px;width: 80%;height:100%;"  ref="mychild3"></vue-quill-editor>
          </el-form-item>
        </el-col>
        <el-col :span="24">
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" style="float:left;margin-top:60px;width: 50%;" class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </div>

</template>

<script>
  import {getToken} from '@/common/util/tokenUtil'
  import VueQuillEditor from '@/components/MzQuill/mzquill'
  import { MessageBox, Message } from 'element-ui'
    export default {
        name: "add.vue",
      components: {
        "vue-quill-editor": VueQuillEditor
      },
        data(){

          return {
            cityList:[],
            levelList:[],
            themeList:[],
            // 表单参数
            form: {
              name:'',
              address:'',
              opentime:'',
              price:'',
              tel:'',
              spotsDesc:'',
              info:'',
              purchaseinfo:'',
              features:'',
              spotsid:'',
              levelid:'',
              themeid:'',
              fmUrl:''
            },
            // 表单校验
            rules: {
              name: [
                { required: true, message: '产品名称不能为空', trigger: 'blur' }
              ]
            }
          }
        },
      created() {

      },
      computed: {
        //computed里面可以设置头部信息，如增加tooken
        headers () {
          return {
            'Authorization': 'Bearer ' + getToken()
          }
        }
      },
        methods:{
          getImgUrl(img) {
            return "http://localhost:8081/static/img/" + img;
          },
          handleAvatarSuccess(res, file) {
            console.log(res)
            this.form.fmUrl = res.data.fileUrl
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
          handleAdd() {
            this.addSpotsDlg = true
          },
          getAllCity(){
            this.$store.dispatch('common/getAllCity')
              .then((data) => {
                console.log(data);
                this.cityList = data
                this.loading = false
              })
              .catch((err) => {
                console.log('失败' + err)
              })
          },
          getAllLevel(){
            this.$store.dispatch('common/getAllLevel')
              .then((data) => {
                this.levelList = data
                this.loading = false
              })
              .catch((err) => {
                console.log('失败' + err)
              })
          },
          getAllTheme(){
            this.$store.dispatch('common/getAllTheme')
              .then((data) => {
                this.themeList = data
                this.loading = false
              })
              .catch((err) => {
                console.log('失败' + err)
              })
          },

          // 取消按钮
          cancel() {
            this.$router.push('/product/list')
          },
          submitForm(){
            //获取富文本框的值
            this.form.info = this.$refs.mychild1.getHtml();
            this.form.purchaseinfo = this.$refs.mychild2.getHtml();
            this.form.features = this.$refs.mychild3.getHtml();
            this.$store.dispatch('spots/saveSpots',this.form)
              .then((data) => {
                  MessageBox.alert('保存成功')
                  this.$router.push({ path: '/spots/list' })
              })
              .catch((err) => {
                MessageBox.alert('保存失败'+err)
              })
          }
        },
         mounted() {
            this.getAllCity();
            this.getAllLevel();
            this.getAllTheme();
         }
    }
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 378px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 378px;
    height: 378px;
    display: block;
  }
</style>
