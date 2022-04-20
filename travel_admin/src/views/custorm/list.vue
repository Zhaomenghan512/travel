<template>
  <div class="app-container">
    <div class="filter-container" style="float:left">
      <el-form :model="queryParams" :inline="true">
        <el-form-item label="客户名称" prop="account">
          <el-input
            v-model="queryParams.account"
            placeholder="请输客户名称/电话号码/邮箱"
            clearable
            size="small"
            style="width: 240px"
            @keyup.enter.native="getList"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary"   icon="el-icon-search" size="mini" @click="getList">搜索</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-table v-loading="loading" :data="custormList" highlight-current-row style="width: 100%;">
      <el-table-column label="序号"  type="index" align="center" width="60"/>
      <el-table-column prop="username" align="center" label="客户名称" />
      <el-table-column prop="tel" align="center" label="联系电话" />
      <el-table-column prop="email" align="center" label="电子邮件" />
      <el-table-column prop="headImg" align="center" label="头像" >
        <template slot-scope="scope">
            <img style="width: 80px;height: 80%;border-radius: 50%"  :src="`http://localhost/upload/headimg/${scope.row.headImg}`">
        </template>
      </el-table-column>
      <el-table-column prop="createtime" align="center" label="注册时间" />
    </el-table>
    <pagination style="float:right" :total="total" :page.sync="queryParams.currentPage" :limit.sync="queryParams.pageSize" @pagination="getList" />


  </div>
</template>

<script>
  import {getToken} from '@/common/util/tokenUtil'
  import authorize from '@/direct/authorize/index.js' // 权限判断指令
  import Pagination from '@/common/components/Pagination'
  export default {
    name: 'custorm',
    directives: { authorize },
    components: {
      Pagination
    },
    data() {
      return {

        custormForm:{
          id:0,
          username:'',
          password:'',
          tel:'',
          email:'',
          headImg:''
        },
        // 弹出层标题
        title: '',
        // 遮罩层
        loading: true,
        // 客户数据
        custormList: [],
        // 总数量
        total: 0,
        // 查询参数
        queryParams: {
          currentPage: 1,
          pageSize: 10,
          account:''
        }
      }
    },
    created() {
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
        this.$store.dispatch('custorm/getCustormList', this.queryParams)
          .then((data) => {

            this.custormList = data.rows
            this.total = data.total
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
