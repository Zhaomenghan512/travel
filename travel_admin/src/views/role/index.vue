<template>
  <div class="app-container">
    <div class="filter-container" style="float:left">
      <el-form :model="queryParams" :inline="true">
        <el-form-item label="角色名称" prop="name">
          <el-input
            v-model="queryParams.name"
            placeholder="请输入角色名称"
            clearable
            size="small"
            style="width: 240px"
            @keyup.enter.native="getList"
          />
        </el-form-item>

        <el-form-item>
          <el-button type="primary"   icon="el-icon-search" size="mini" @click="getList">搜索</el-button>
          <el-button type="primary"   icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-table v-loading="loading" :data="roleList" highlight-current-row style="width: 100%;">
       <el-table-column label="序号"  type="index" align="center" width="60"/>
      <el-table-column prop="name" align="center" label="角色名称" />
      <el-table-column prop="enname" align="center" label="角色编号" />

      <el-table-column label="对应的菜单权限" align="center" :formatter="meunListFormatter" prop="menuList">
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
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination :total="total" :page.sync="queryParams.currentPage" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改用户配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>


          <el-col :span="12">
            <el-form-item label="角色名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入角色名称" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="角色编号" prop="enname">
              <el-input v-model="form.enname" placeholder="请输入角色编号" />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="菜单权限" prop="enname">
              <div style="margin-top: 20px">
                <el-checkbox-group v-model="checkedMenusList" size="mini">
                  <el-checkbox   @change="handleCheckedChange(menu,index)"  :label="menu.id" :key="menu.id"
                                v-for="(menu,index) in allMenusList" border size="medium">{{menu.name}}
                  </el-checkbox>
                </el-checkbox-group>
              </div>
            </el-form-item>
          </el-col>

        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
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
      // 角色数据
      roleList: [],
      //菜单数据
      allMenusList:[],
      //已经选中的菜单权限
      checkedMenusList:[],
      // 总数量
      total: 0,
      // 是否显示弹出层
      open: false,
      checked5:false,
      checked6:false,
      // 查询参数
      queryParams: {
        currentPage: 1,
        pageSize: 10
      },
      // 表单参数
      form: {
        name:'',
        enname:''
      },
      // 表单校验
      rules: {
        name: [
          { required: true, message: '角色不能为空', trigger: 'blur' }
        ],
        enname: [
          { required: true, message: '角色编号不能为空', trigger: 'blur' }
        ]
      }
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
    handleCheckedChange(data,index){
    },
    meunListFormatter(row, column){
      let menuList = row.menuList
      if(menuList.length>0){
         let menuStr = "";
         for(let i=0;i<menuList.length;i++){
           let menu = menuList[i];
            menuStr += menu.name +","
         }
         return menuStr.substring(0,menuStr.length-1);
      }
    },

    /** 查询角色列表 */
    getList() {
      this.$store.dispatch('role/getRoleList', this.queryParams)
        .then((data) => {
          this.roleList = data.rows
          this.total = data.total
          this.loading = false
        })
        .catch((err) => {
          console.log('失败' + err)
        })
    },
    getAllMenu(){
      //得到所有的菜单
      this.$store.dispatch('menu/getMenuList', this.queryParams)
        .then((data) => {
          this.allMenusList =data.allMenusList
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
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        name: undefined,
        enname:undefined
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
      this.title = '添加角色'
      this.checkedMenusList = []
      //获取所有的角色
      this.getAllMenu();
    },
    /** 删除按钮操作 */
    delRole(id) {
      this.$store.dispatch('role/delRole', id).then(() => {
        this.getList()
        this.msg('删除成功')
      })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      this.open = true
      this.form = Object.assign({}, row);
      //获取所有的角色
      this.getAllMenu();
      this.checkedMenusList = []
      this.checkedMenusList =  row.menuList.map(item=>{
         return item.id
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          //封装参数
          let menuList = this.checkedMenusList.map(function(idVal){
            return {"id":idVal};
          })
          this.form.menuList = menuList

          if (this.form.id !== undefined) {
            this.$store.dispatch('role/updateRole', this.form).then(data => {
              this.open = false
              this.getList()
            })
          } else {
              this.$store.dispatch('role/saveRole', this.form).then(res => {
                this.open = false
                this.getList()
              })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm('是否确认删除名称为"' + row.name + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.delRole(row.id)
      })
    }
  }
}
</script>
