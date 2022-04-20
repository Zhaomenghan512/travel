<template>
  <div class="app-container">
    <el-form :inline="true" style="float:left">
      <el-form-item label="菜单名称">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入菜单名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button v-authorize="['admin_sysmenu_view']" type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button v-authorize="['admin_sysmenu_add']"  type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增一级目录</el-button>
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      :data="menuList"
      row-key="id"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column prop="name" label="菜单名称" align="center" />
      <el-table-column prop="icon" label="图标" align="center" >
        <template slot-scope="scope">
          <i  :class="scope.row.icon" />
        </template>
      </el-table-column>
      <el-table-column prop="sort" label="排序"  />
      <el-table-column prop="type" label="类型" :formatter="typeFormat" />
      <el-table-column prop="permission" label="权限标识" />
      <el-table-column prop="target" label="组件路径"  />
      <el-table-column prop="hasShow" label="状态" :formatter="showFormat" />
      <el-table-column label="更新时间" align="center" prop="updateDate">
        <template slot-scope="scope">
          <span>{{ scope.row.updateDate | dateFormat('YYYY-MM-DD HH:mm') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center"  width="180">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-authorize="['admin_sysmenu_edit']"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            v-authorize="['admin_sysmenu_add']"
            @click="handleSTAdd(scope.row)"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            v-authorize="['admin_sysmenu_del']"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加一级菜单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>

          <el-col :span="12">
            <el-form-item label="菜单名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入菜单名称" />
            </el-form-item>
          </el-col>



          <el-col :span="12">
            <el-form-item label="显示" prop="hasShow">
              <el-select v-model="form.hasShow" placeholder="请选择">
                <el-option
                  v-for="item in showDict"
                  :key="item.value"
                  :label="item.name"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="显示排序" prop="sort">
              <el-input-number v-model="form.sort" :min="0" />
            </el-form-item>
          </el-col>

           <el-col :span="24">
            <el-form-item label="图标" prop="icon">
              <!-- <el-input v-model="form.icon" placeholder="图标" /> -->
               <e-icon-picker v-model="form.icon" />
            </el-form-item>

          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitOneForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加二级对话框-->
    <el-dialog :title="title" :visible.sync="secondOpen" width="850px" append-to-body>
      <el-form ref="stform" :model="stform" :rules="stRules" label-width="120px">
        <el-row>

          <el-col :span="12">
            <el-form-item label="父级菜单" prop="parentName">
              <el-input :disabled="true"  v-model="stform.parentName" placeholder="请输入菜单名称" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="子菜单名称" prop="name">
              <el-input v-model="stform.name" placeholder="请输入子菜单名称" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="访问路径"  prop="target">
              <el-input v-model="stform.target" placeholder="请输入页面路径" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="显示" prop="hasShow">
              <el-select v-model="stform.hasShow" placeholder="请选择">
                <el-option
                  v-for="item in showDict"
                  :key="item.value"
                  :label="item.name"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="显示排序" prop="sort">
              <el-input-number v-model="stform.sort" :min="0" />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="图标" prop="icon">
              <!-- <el-input v-model="form.icon" placeholder="图标" /> -->
              <e-icon-picker v-model="stform.icon" />
            </el-form-item>

          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitStForm">确 定</el-button>
        <el-button @click="cancelSt">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加三级对话框-->
    <el-dialog :title="title" :visible.sync="threeOpen" width="850px" append-to-body>
      <el-form ref="thform" :model="thform" :rules="thRules" label-width="100px">
        <el-row>

          <el-col :span="12">
            <el-form-item label="父级菜单" prop="parentName">
              <el-input :disabled="true"  v-model="thform.parentName" placeholder="请输入菜单名称" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="按钮名称" prop="name">
              <el-input v-model="thform.name" placeholder="如:新增订单" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="权限值"  prop="permission">
              <el-input v-model="thform.permission" placeholder="如:admin_order_add" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="显示" prop="hasShow">
              <el-select v-model="thform.hasShow" placeholder="请选择">
                <el-option
                  v-for="item in showDict"
                  :key="item.value"
                  :label="item.name"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="显示排序" prop="sort">
              <el-input-number v-model="thform.sort" :min="0" />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="图标" prop="icon">
              <e-icon-picker v-model="thform.icon" />
            </el-form-item>

          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitThForm">确 定</el-button>
        <el-button @click="cancelTh">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 修改对话框-->
    <el-dialog :title="title" :visible.sync="editMenuOpen" width="850px" append-to-body>
      <el-form ref="editform" :model="editform" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="editform.name" placeholder="如:新增订单" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="访问路径" v-if="editform.type == 1"  prop="target">
              <el-input v-model="editform.target" placeholder="请输入页面路径" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="权限值" v-if="editform.type == 3"  prop="permission">
              <el-input v-model="editform.permission" placeholder="如:admin_order_add" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="显示" prop="hasShow">
              <el-select v-model="editform.hasShow" placeholder="请选择">
                <el-option
                  v-for="item in showDict"
                  :key="item.value"
                  :label="item.name"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="显示排序" prop="sort">
              <el-input-number v-model="editform.sort" :min="0" />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="图标" prop="icon">
              <e-icon-picker v-model="editform.icon" />
            </el-form-item>

          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editSubmitForm">确 定</el-button>
        <el-button @click="cancelEditDlg">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import authorize from '@/direct/authorize/index.js' // 权限判断指令

  export default {
    name: 'Menu',
    directives: { authorize },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 菜单表格树数据
      menuList: [],
      menuProps: { checkStrictly: true, value: 'id', label: 'name' },
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      secondOpen:false,
      threeOpen:false,
      editMenuOpen: false,
      // 查询参数
      queryParams: {
        name: ''
      },
      // 表单参数
      form: {},
      stform: {},
      thform: {},
      editform:{},
      menuOptions: [],
      // 显示状态数据字典
      showDict: [{
        value: 1,
        name: '是'
      },
      {
        value: 0,
        name: '否'
      }],
       typeDict: [{
        value: '0',
        name: '一级目录'
      },
      {
        value: '1',
        name: '二级组件'
      },{
        value: '3',
        name: '三级按钮'
      }],
      // 表单校验
      rules: {
        name: [
          { required: true, message: '菜单名称不能为空', trigger: 'blur' }
        ],
        sort: [
          { required: true, message: '菜单顺序不能为空', trigger: 'blur' }
        ],
        hasShow: [
          { required: true, message: '显示不能为空', trigger: 'blur' }
        ]
      },
      stRules:{
        name: [
          { required: true, message: '菜单名称不能为空', trigger: 'blur' }
        ],
        sort: [
          { required: true, message: '菜单顺序不能为空', trigger: 'blur' }
        ],
        hasShow: [
          { required: true, message: '显示不能为空', trigger: 'blur' }
        ]
      }
      ,
      thRules:{
        name: [
          { required: true, message: '菜单名称不能为空', trigger: 'blur' }
        ],
        sort: [
          { required: true, message: '菜单顺序不能为空', trigger: 'blur' }
        ],
        hasShow: [
          { required: true, message: '显示不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    handleTree(data) {
      let idstr = 'id'
      let pidstr = 'parentId'
      let children = 'children'
      let result = [],temp = {} ;
      for(let i = 0; i < data.length; i++){
        //将data数组转成对象类型
        temp[data[i][idstr]]=data[i];
      }
      for(let j=0; j<data.length; j++){
        //获取每一个子对象的父对象
        let tempVp = temp[data[j][pidstr]];
        //判断父对象是否存在，如果不存在直接将对象放到第一层
        if(tempVp){
          if(!tempVp[children]) {
            //如果父元素的data对象不存在，则创建数组
            tempVp[children] = [];
          }
          tempVp[children].push(data[j]);//将本对象压入父对象的data数组
        }else{
          result.push(data[j]);//将不存在父对象的对象直接放入一级目录
        }
      }
      return result;
    },
     // 字典翻译
    showFormat(row, column) {
      let obj = this.showDict.find(dict => dict.value == row.hasShow)
      return obj.name
    },
     // 字典翻译
    typeFormat(row, column) {
      let obj = this.typeDict.find(dict => dict.value == row.type)
      return obj.name
    },
    /** 查询菜单列表 */
    getList() {

      this.$store.dispatch('menu/getMenuList', this.queryParams)
        .then((data) => {
          this.menuList = this.handleTree(data.allMenusList)
          this.loading = false
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
    cancelSt() {
      this.secondOpen = false
      this.resetSt()
    },
    cancelTh() {
      this.threeOpen = false
      this.resetTh()
    },
    cancelEditDlg(){
      this.editMenuOpen = false
      this.resetEditForm()
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        pIds: ['0'],
        parentId: 0,
        parentName:'',
        parentIds:'0,',
        name: undefined,
        type: '0',
        target: undefined,
        icon: undefined,
        hasShow: 1,
        permission: undefined,
        sort: 30
      }
      this.$nextTick(()=>{
        this.$refs['form'].resetFields();
      })
    },
    resetSt() {
      this.stform = {
        id: undefined,
        pIds: ['0'],
        parentId: 0,
        parentName:'',
        parentIds:'0,',
        name: undefined,
        type: '0',
        target: undefined,
        icon: undefined,
        hasShow: 1,
        permission: undefined,
        sort: 30
      }
      this.$nextTick(()=>{
        this.$refs['stform'].resetFields();
      })
    },
  resetTh() {
    this.thform = {
      id: undefined,
      pIds: ['0'],
      parentId: 0,
      parentName:'',
      parentIds:'0,',
      name: undefined,
      type: '0',
      target: undefined,
      icon: undefined,
      hasShow: 1,
      permission: undefined,
      sort: 30
    }
    this.$nextTick(()=>{
      this.$refs['thform'].resetFields();
    })
  },
    resetEditForm() {
      this.editform = {
        id: undefined,
        pIds: ['0'],
        parentId: 0,
        parentName:'',
        parentIds:'0,',
        name: undefined,
        type: '0',
        target: undefined,
        icon: undefined,
        hasShow: 1,
        permission: undefined,
        sort: 30
      }
      this.$nextTick(()=>{
        this.$refs['editform'].resetFields();
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList()
    },
    /** 新增一级按钮操作 */
    handleAdd(row) {
      this.reset()
      this.open = true
      this.title = '添加菜单'
    },
    /**打开二级或者二级操作**/
    handleSTAdd(row){

      if(row.type == 0) {
        this.secondOpen = true;
        this.resetSt()
        this.stform.parentId = row.id;
        this.stform.parentName = row.name;
        this.stform.pIds = row.parentIds.substring(0, row.parentIds.length - 1).split(',')
      }else if(row.type == 1){
        this.threeOpen = true;
        this.resetTh()
        this.thform.parentId = row.id;
        this.thform.parentName = row.name;
        this.thform.pIds = row.parentIds.substring(0, row.parentIds.length - 1).split(',')
      }

      this.$forceUpdate();
    },
    /** 删除按钮操作 */
    delMenu(menuId) {
      this.$store.dispatch('menu/delMenu', menuId).then((res) => {
        console.log(res)
        this.getList()
      })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.resetEditForm()
      //修改弹出表单
      this.editform = row;
      console.log(this.editform)
      this.editMenuOpen = true;
    },
    /** 保存一级菜单 */
    submitOneForm: function() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.form.parentId = this.form.pIds[this.form.pIds.length-1]
            this.$store.dispatch('menu/saveMenu', this.form).then(res => {
              this.open = false
              this.getList()
            })

        }
      })
    },
    /**保存二级**/
    submitStForm: function() {
      this.$refs['stform'].validate(valid => {
        if (valid) {
          this.stform.type = 1 //二级菜单
          this.stform.parentIds = this.stform.parentIds +this.stform.parentId+","
          console.log(this.stform)
          this.$store.dispatch('menu/saveMenu', this.stform).then(res => {
            this.secondOpen = false
            this.getList()
          })

        }
      })
    },
    /**保存三级级**/
    submitThForm: function() {
      this.$refs['thform'].validate(valid => {
        if (valid) {
          this.thform.type = 3 //三级菜单
          this.thform.parentIds = this.thform.parentIds +this.thform.parentId+","
          console.log(this.thform)
          this.thform.hasShow = 0
          this.$store.dispatch('menu/saveMenu', this.thform).then(res => {
            this.threeOpen = false
            this.getList()
          })

        }
      })
    },
    /*editSubmitForm修改提交form*/
    editSubmitForm: function() {
      this.$refs['editform'].validate(valid => {
        if (valid) {
          this.$store.dispatch('menu/updateMenu', this.editform).then(res => {
            this.editMenuOpen = false
            this.getList()
          })

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
        this.delMenu(row.id)
      })
    }
  }
}
</script>
