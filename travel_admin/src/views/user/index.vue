<template>
  <div class="app-container">
    <div class="filter-container" style="float:left">
      <el-form :model="queryParams" :inline="true">
        <el-form-item label="用户名称" prop="name">
          <el-input
            v-model="queryParams.name"
            placeholder="请输入用户名称"
            clearable
            size="small"
            style="width: 240px"
            @keyup.enter.native="getList"
          />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" v-authorize="['admin_sysuser_view']"  icon="el-icon-search" size="mini" @click="getList">搜索</el-button>
          <el-button type="primary" v-authorize="['admin_sysuser_add']"  icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-table v-loading="loading" :data="userList" highlight-current-row style="width: 100%;">
      <el-table-column label="序号"  type="index" align="center" width="60"/>
      <el-table-column prop="username" align="center" label="姓名" />
<!--      <el-table-column prop="tel" align="center" label="电话号码" />-->
      <el-table-column prop="email" align="center" label="邮箱" />
      <el-table-column prop="icon"  align="center" label="头像" >
        <template slot-scope="scope">
          <img :id="`tImageId${scope.row.id}`" style="height:100px;width:100px"
               :src='getImage(scope.row.icon,`tImageId${scope.row.id}`)'>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime | dateFormat('YYYY-MM-DD HH:mm') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="所属角色" align="center" :formatter="roleListFormatter" prop="userRolesList">
      </el-table-column>

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
            icon="el-icon-document-add"
            @click="handleAddRole(scope.row)"
          >设置角色</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination :total="total" :page.sync="queryParams.currentPage" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改用户配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :inline="true"  @submit.native.prevent :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="用户头像" prop="icon">
              <el-upload
                class="avatar-uploader"
                action="http://localhost/user/file/uploadFile"
                :show-file-list="false"
                :headers="headers"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
              >
                <img style="width:100px;height: 100px"  v-if="form.icon" id="showImgId"  :src="getImage(form.icon,'showImgId')"  class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon" />
              </el-upload>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="用户名称" prop="username">
              <el-input @keyup.enter.native='submit' v-model="form.username" placeholder="请输入用户名称" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="用户昵称" prop="nickName">
              <el-input v-model="form.nickName" placeholder="请输入用户昵称" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="密码" prop="password">
              <el-input v-model="form.password" type="password" placeholder="请输入密码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱地址" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <!--添加修改角色 -->
    <el-dialog :title="title" :visible.sync="roleDlgVisable" width="800px" append-to-body>
      <el-form :inline="true"   ref="roleForm" :model="roleForm" :rules="roleFormRules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="用户名称" prop="username">
              <el-input disabled v-model="roleForm.username" placeholder="请输入用户名称" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="选择角色" prop="role">
              <div style="margin-top: 20px">
                <el-checkbox-group v-model="checkedRolesList" size="mini">
                  <el-checkbox     :label="role.id" :key="role.id"
                                   v-for="role in roleList" border size="medium">{{role.name}}
                  </el-checkbox>
                </el-checkbox-group>
              </div>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRoleForm">确 定</el-button>
        <el-button @click="cancelRoleDlg">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import {getToken} from '@/common/util/tokenUtil'
  import authorize from '@/direct/authorize/index.js' // 权限判断指令
  import Pagination from '@/common/components/Pagination'
  export default {
    name: 'User',
    components: { Pagination },
    directives: { authorize },
    data() {

      return {
        // 弹出层标题
        title: '',
        // 遮罩层
        loading: true,
        // 用户数据
        userList: [],
        roleList: [],
        checkedRolesList:[],
        // 总数量
        total: 0,
        // 是否显示弹出层
        open: false,
        roleDlgVisable:false,
        // 查询参数
        queryParams: {
          currentPage: 1,
          pageSize: 10
        },
        defaultProps: {
          children: 'children',
          label: 'name'
        },

        // 表单参数
        form: {
          username:'',
          nickName:'',
          password:'',
          email:'',
          icon:''
        },
        roleForm:{
          userid:undefined,
          username:undefined

        },
        // 表单校验
        rules: {
          username: [
            { required: true, message: '用户名称不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          email: [
            {
              type: "email",
              message: "请输入正确的邮箱地址",
              trigger: ["blur", "change"]
            }]
        },
        roleFormRules:{}
      }
    },
    created() {
      //this.getOfficeAll()
      this.getList()
    },
    computed: {
      //computed里面可以设置头部信息，如增加tooken
      headers () {
        return {
          'Authorization': 'Bearer ' + getToken()
        }
      }
    },
    methods: {

      roleListFormatter(row, column){
        let userRolesList = row.userRolesList
        if(userRolesList.length>0){
          let userRoleStr = "";
          for(let i=0;i<userRolesList.length;i++){
            let userRole = userRolesList[i];
            userRoleStr += userRole.name +","
          }
          return userRoleStr.substring(0,userRoleStr.length-1);
        }
      },
      handleAddRole(row,index){
        //用户添加角色
        this.roleDlgVisable = true
        this.roleForm.userid = row.id
        this.roleForm.username = row.username
        //查询所有的角色
        this.getAllRoleList();
        //回显示数据
        this.checkedRolesList = []
        this.checkedRolesList =  row.userRolesList.map(item=>{
          return item.id
        })

      },
      getImage(fileUrl,idEle){
        console.log(idEle);
        this.$axios({
          method:'post',
          url:"http://localhost/user/showimage",
          headers:{
            'authorization':'Bearer ' + getToken()
          },
          data:{image_name:fileUrl},
          responseType: 'blob'
        }).then(res=>{
          const blob = new Blob([res.data])
          let url = window.URL.createObjectURL(blob,{type: 'image/jpeg'})
          let imgEle = document.getElementById(idEle)
          imgEle.src = url
          imgEle.onload = function () {
            window.URL.revokeObjectURL(url)
          }
        }).catch((err)=>{
          console.log('失败' + err)
        })
      },
      handleAvatarSuccess(res, file) {
        console.log(res)
        this.form.icon = res.data.fileUrl
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
      /** 查询用户列表 */
      getList() {
        this.$store.dispatch('sysuser/getUserList', this.queryParams)
          .then((data) => {
            this.userList = data.rows
            this.total = data.total
            this.loading = false
          })
          .catch((err) => {
            console.log('失败' + err)
          })
      },
      /** 查询所有角色 */
      getAllRoleList() {
        this.$store.dispatch('role/getAllRole', this.queryParams)
          .then((data) => {
            this.roleList = data
          })
          .catch((err) => {
            console.log('失败' + err)
          })
      },
      // 取消按钮
      cancel() {
        this.open = false
        this.reset()
      },
      cancelRoleDlg(){
        this.roleDlgVisable = false
      },
      // 表单重置
      reset() {
        this.form = {
          id: undefined,
          username: undefined,
          password: undefined,
          icon: undefined,
          email: undefined,
          no: undefined,
          email: undefined,
          nickName: undefined,
          note: undefined,
          status: 0
        }

      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.getList()
      },
      /** 新增按钮操作 */
      handleAdd(row) {
        this.reset()
        this.open = true
        this.title = '添加用户'
      },
      /** 删除按钮操作 */
      delUser(id) {
        this.$store.dispatch('sysuser/delUser', id).then(() => {
          this.getList()
          this.msg('删除成功')
        })
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        this.open = true
        this.form = Object.assign({}, row);
        console.log(this.form)

      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            if (this.form.id !== undefined) {
              this.$store.dispatch('sysuser/updateUser', this.form).then(data => {
                this.open = false
                this.getList()
              })
            } else {
              this.$store.dispatch('sysuser/saveUser', this.form).then(res => {
                console.log(res)
                this.open = false
                this.getList()
              })
            }
          }
        })
      },
      submitRoleForm(){
        let userId = this.roleForm.userid;
        //封装参数
        let rolesMenuList = this.checkedRolesList.map(function(idVal){
          return {"roleId":idVal,"userId":userId};
        })
        this.$store.dispatch('sysuser/saveUserRole', rolesMenuList).then(res => {
          this.roleDlgVisable = false
          this.getList()
        })

      },
      /** 删除按钮操作 */
      handleDelete(row) {
        this.$confirm('是否确认删除名称为"' + row.username + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.delUser(row.id)
        })
      }
    }
  }
</script>
