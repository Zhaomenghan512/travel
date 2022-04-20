<template>
  <div class="app-container">
    <div class="filter-container" style="float:left">
      <el-form :model="queryParams" :inline="true">
        <el-form-item label="游记标题" prop="title">
          <el-input
            v-model="queryParams.title"
            placeholder="请输入游记标题"
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

    <el-table v-loading="loading" :data="notesList" highlight-current-row style="width: 100%;">
      <el-table-column label="序号"  type="index" align="center" width="60"/>
      <el-table-column prop="title" align="center" label="游记标题" />
      <el-table-column prop="departureTime" align="center" label="出行时间" />
         <el-table-column prop="traveldays" align="center" label="旅游天数" />
         <el-table-column prop="travelnums" align="center" label="旅游人数" />
         <el-table-column prop="hotelname" align="center" label="入住酒店" />
         <el-table-column prop="precost" align="center" label="人均消费" />
          <el-table-column prop="viewnum" align="center" label="浏览次数" />
        <el-table-column prop="custorm.username" align="center" label="发布人" />
        <el-table-column prop="fmUrl" align="center" label="游记封面" >
             <template slot-scope="scope">
           <img :src="`http://localhost/upload/notes/${scope.row.fmUrl}`"    width="100" height="100" class="head_pic"/>
        </template>
        </el-table-column>
      <el-table-column label="操作" align="center" >
        <template slot-scope="scope">
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

   
  </div>
</template>

<script>
  import {getToken} from '@/common/util/tokenUtil'
  import authorize from '@/direct/authorize/index.js' // 权限判断指令
  import Pagination from '@/common/components/Pagination'
  export default {
    name: 'Notes',
    directives: { authorize },
    components: {
      Pagination
    },
    data() {
      return {
      
        // 弹出层标题
        title: '',
        // 遮罩层
        loading: true,
        // 订单数据
        notesList: [],
        // 总数量
        total: 0,
        // 查询参数
        queryParams: {
          currentPage: 1,
          pageSize: 10,
          title:''
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
        this.$store.dispatch('notes/getNotesList', this.queryParams)
          .then((data) => {
            this.notesList = data.rows
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
