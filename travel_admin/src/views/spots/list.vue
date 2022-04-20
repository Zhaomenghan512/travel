<template>
  <div class="app-container">
    <div class="filter-container" style="float:left">
      <el-form :model="queryParams" :inline="true">
        <el-form-item label="景区名称" prop="name">
          <el-input
            v-model="queryParams.name"
            placeholder="请输入景区名称"
            clearable
            size="small"
            style="width: 240px"
            @keyup.enter.native="getList"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary"   icon="el-icon-search" size="mini" @click="getList">搜索</el-button>
          <el-button type="primary"   icon="el-icon-plus" size="mini" @click="handleAdd">景区发布</el-button>

        </el-form-item>
      </el-form>
    </div>

    <el-table v-loading="loading" :data="spotsList" highlight-current-row style="width: 100%;">
      <el-table-column label="序号"  type="index" align="center" width="60"/>
      <el-table-column prop="name" align="center" label="景区名称" />
      <el-table-column prop="fmUrl" align="center" label="景区封面" >
        <template slot-scope="scope">
           <img :src="`http://localhost/upload/spots/${scope.row.fmUrl}`"    width="100" height="100" class="head_pic"/>
        </template>
      </el-table-column>
      <el-table-column prop="price" align="center" label="景区价格" />
      <el-table-column prop="address" align="center" label="景区地点" />
      <el-table-column prop="level.levelname" align="center" label="景区等级" />
      <el-table-column prop="theme.themename" align="center" label="景区主题" />
      <el-table-column prop="opentime" align="center" label="景区开放时间" />
      <el-table-column prop="spotsDesc" align="center" label="景区描述" :show-overflow-tooltip="true" />

     <el-table-column label="操作" align="center" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination style="float:right" :total="total" :page.sync="queryParams.currentPage" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 新增景区对话框-->
    <!-- 添加或修改用户配置对话框 -->
    <el-dialog :title="title" :visible.sync="editSpotsDlg"  width="800px" append-to-body>
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
            <el-form-item label="景区封面图" prop="fmUrl">
              <el-upload
                class="avatar-uploader"
                action="http://localhost/spots/file/uploadFile"
                :show-file-list="false"
                :headers="headers"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
              >
                <img style="width:100px;height: 100px"  v-if="form.fmUrl"  :src="`http://localhost/upload/spots/${form.fmUrl}`"  class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon" />
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
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editSubmitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {getToken} from '@/common/util/tokenUtil'
  import authorize from '@/direct/authorize/index.js' // 权限判断指令
  import Pagination from '@/common/components/Pagination'
  import VueQuillEditor from '@/components/MzQuill/mzquill';
  export default {
    name: 'User',
    directives: { authorize },
    components: {
      "vue-quill-editor": VueQuillEditor,         //富文本编辑器
      Pagination
    },
    data() {

      return {
        // 弹出层标题
        title: '',
        // 遮罩层
        loading: true,
        isLoadCategory:true,
        editSpotsDlg:false,
        isShowSelect:false,
        // 产品数据
        spotsList: [],
        // 总数量
        total: 0,
        // 查询参数
        queryParams: {
          currentPage: 1,
          pageSize: 10
        },
        cityList:[],
        levelList:[],
        themeList:[],
        // 表单参数
        form: {
          id:undefined,
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
          spotsName: [
            { required: true, message: '产品名称不能为空', trigger: 'blur' }
            ]
        },
        optionProps: {
          value: 'id',
          label: 'cateName',
          children: 'childrens'
        },
        cateGoryList: []
      }
    },
    created() {
      this.getList()
    },
    computed: {
      editor() {
        return this.$refs.myQuillEditor.quill;
      },
      //computed里面可以设置头部信息，如增加tooken
      headers () {
        return {
          'Authorization': 'Bearer ' + getToken()
        }
      }
    },
    mounted() {
      this.getAllCity();
      this.getAllLevel();
      this.getAllTheme();
    },
    methods: {
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
        this.$router.push({ path: '/spots/add' })
      },
      handleUpdate(row){
        this.editSpotsDlg =true
        //数据回显
        this.form.id = row.id
        this.form.name = row.name
        this.form.price=row.price
        this.form.address=row.address
        this.form.opentime=row.opentime
        this.form.tel=row.tel
        this.form.spotsid=row.spotsid
        this.form.levelid=row.levelid
        this.form.themeid=row.themeid
        this.form.fmUrl=row.fmUrl
        this.form.spotsDesc = row.spotsDesc

        setTimeout(() => {
          this.$refs.mychild1.setHtml(row.info)
          this.$refs.mychild2.setHtml(row.purchaseinfo)
          this.$refs.mychild3.setHtml(row.features)
        }, 0)


      },
      setHtml(content){

      },

      /** 查询角色列表 */
      getList() {
        this.$store.dispatch('spots/getSpotsList', this.queryParams)
          .then((data) => {
            this.spotsList = data.rows
            this.total = data.total
            this.loading = false
          })
          .catch((err) => {
            console.log('失败' + err)
          })
      },
      handleDelete(row){
        this.$store.dispatch('spots/deleteSpots',row.id)
          .then((data) => {
            this.getList()
          })
          .catch((err) => {
            console.log('失败' + err)
          })
      },

      // 取消按钮
      cancel() {
        this.editSpotsDlg = false
      },
      editSubmitForm(){
            //获取富文本框的值
            this.form.info = this.$refs.mychild1.getHtml();
            this.form.purchaseinfo = this.$refs.mychild2.getHtml();
            this.form.features = this.$refs.mychild3.getHtml();

            this.$store.dispatch('spots/updateSaveSpots',this.form)
              .then((data) => {
                this.editSpotsDlg = false
                this.getList()
              })
              .catch((err) => {
                console.log('失败' + err)
              })

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
      }

    }


  }
</script>

<style scoped>

</style>
