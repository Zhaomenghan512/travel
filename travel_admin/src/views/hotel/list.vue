<template>
  <div class="app-container">
    <div class="filter-container" style="float:left">
      <el-form :model="queryParams" :inline="true">
        <el-form-item label="酒店名称" prop="hotelname">
          <el-input
            v-model="queryParams.hotelname"
            placeholder="请输入酒店名称"
            clearable
            size="small"
            style="width: 240px"
            @keyup.enter.native="getList"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary"   icon="el-icon-search" size="mini" @click="getList">搜索</el-button>
          <el-button type="primary"   icon="el-icon-plus" size="mini" @click="addHotel">新增</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-table v-loading="loading" :data="hotelList" highlight-current-row style="width: 100%;">
      <el-table-column label="序号"  type="index" align="center" width="60"/>
      <el-table-column prop="hotelname" align="center" label="酒店名称" />
      <el-table-column prop="hotelprice" align="center" label="酒店价格" />

      <el-table-column label="操作" align="center" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="editHotel(scope.row)"
          >编辑</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="delHotel(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination style="float:right" :total="total" :page.sync="queryParams.currentPage" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 新增对话框 -->
    <el-dialog title="酒店信息" :visible.sync="hotelDialogFormVisible">
      <el-form :model="hotelForm">
        <el-form-item label="酒店名称" label-width="120px">
          <el-input v-model="hotelForm.hotelname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="酒店价格" label-width="120px">
          <el-input v-model="hotelForm.hotelprice" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="hotelDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveHotel">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改对话框 -->
    <el-dialog title="编辑酒店信息" :visible.sync="hotelEditDialogFormVisible">
      <el-form :model="hotelForm">
        <el-form-item label="酒店名称" label-width="120px">
          <el-input v-model="hotelForm.hotelname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="酒店价格" label-width="120px">
          <el-input v-model="hotelForm.hotelprice" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="hotelEditDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editSaveHotel">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {getToken} from '@/common/util/tokenUtil'
  import authorize from '@/direct/authorize/index.js' // 权限判断指令
  import Pagination from '@/common/components/Pagination'
  export default {
    name: 'Hotel',
    directives: { authorize },
    components: {
      Pagination
    },
    data() {
      return {
        hotelDialogFormVisible:false,
        hotelEditDialogFormVisible:false,
        hotelForm:{
          id:0,
          hotelname:'',
          hotelprice:''
        },
        // 弹出层标题
        title: '',
        // 遮罩层
        loading: true,
        // 订单数据
        hotelList: [],
        // 总数量
        total: 0,
        // 查询参数
        queryParams: {
          currentPage: 1,
          pageSize: 10,
          hotelname:''
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

      /** 查询角色列表 */
      getList() {
        this.$store.dispatch('hotel/getHotelList', this.queryParams)
          .then((data) => {
            this.hotelList = data.rows
            this.total = data.total
            this.loading = false
          })
          .catch((err) => {
            console.log('失败' + err)
          })
      },
      addHotel(){
          this.hotelDialogFormVisible = true;
          this.hotelForm.id=0;
          this.hotelForm.hotelname='';
          this.hotelForm.hotelprice='';
      },
      saveHotel(){
        this.$store.dispatch('hotel/addSaveHotel', this.hotelForm)
          .then((data) => {
            this.hotelDialogFormVisible = false;
            this.$alert('保存成功', '温馨提示:', {
              type: 'success',
              confirmButtonText: '确定',
              callback: action => {
                this.getList();
              }
            });
          })
          .catch((err) => {
            this.$alert('保存失败'+err, '温馨提示:', {
              type: 'danger',
              confirmButtonText: '确定',
              callback: action => {
                this.getList();
              }
            });

          })
      },
      editHotel(row){
        this.hotelEditDialogFormVisible = true;
        this.hotelForm.hotelname = row.hotelname;
        this.hotelForm.hotelprice = row.hotelprice;
        this.hotelForm.id = row.id;
      },
      editSaveHotel(){
        //修改酒店
        this.$store.dispatch('hotel/editSaveHotel',this.hotelForm)
          .then((data) => {
            this.hotelEditDialogFormVisible = false;
            this.$alert('保存成功', '温馨提示:', {
              type: 'success',
              confirmButtonText: '确定',
              callback: action => {
                this.getList();
              }
            });
          })
          .catch((err) => {
            this.$alert('保存失败'+err, '温馨提示:', {
              type: 'danger',
              confirmButtonText: '确定',
              callback: action => {
                this.getList();
              }
            });

          })
      },
      delHotel(row){
        //删除酒店
        this.$store.dispatch('hotel/delHotel',row)
          .then((data) => {
            this.$alert('删除成功', '温馨提示:', {
              type: 'success',
              confirmButtonText: '确定',
              callback: action => {
                this.getList();
              }
            });
          })
          .catch((err) => {
            this.$alert('删除失败'+err, '温馨提示:', {
              type: 'danger',
              confirmButtonText: '确定',
              callback: action => {
                this.getList();
              }
            });
          })
      }

    }


  }
</script>

<style scoped>

</style>
