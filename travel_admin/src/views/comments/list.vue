<template>
  <div class="app-container">
    <div class="filter-container" style="float:left">
      <el-form :model="queryParams" :inline="true">
        <el-form-item label="评论人名称" prop="account">
          <el-input
            v-model="queryParams.username"
            placeholder="请输名称"
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

    <el-table v-loading="loading" :data="commentsList" highlight-current-row style="width: 100%;">
      <el-table-column label="序号"  type="index" align="center" width="60"/>
      <el-table-column prop="custorm.username" align="center" label="评论人" />
      <el-table-column prop="spots.name" align="center" label="景区名称" />
      <el-table-column prop="content" align="center" label="评论内容" />
      <el-table-column prop="transportsfeel" :formatter="formatterTranSportsFeel" align="center" label="交通情况" />
      <el-table-column prop="hotelfeel" :formatter="formatterHotelFeel" align="center" label="酒店情况" />
      <el-table-column prop="playfeel" :formatter="formatterPlayFeel" align="center" label="是否玩爽" />
      <el-table-column prop="createtime" align="center" label="评论时间" />

      <el-table-column label="操作" align="center" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="delComments(scope.row)"
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
    name: 'custorm',
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
        // 评论数据
        commentsList: [],
        // 总数量
        total: 0,
        // 查询参数
        queryParams: {
          currentPage: 1,
          pageSize: 10,
          username:''
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
        this.$store.dispatch('comments/getCommentsList', this.queryParams)
          .then((data) => {

            this.commentsList = data.rows
            this.total = data.total
            this.loading = false
          })
          .catch((err) => {
            console.log('失败' + err)
          })
      },
      formatterTranSportsFeel(row, column){
        let star = row.transportsfeel;
        return this.formatterFeel(star);

      },
      formatterHotelFeel(row, column){
        let star = row.hotelfeel;
        return this.formatterFeel(star);

      },
      formatterPlayFeel(row, column){
        let star = row.playfeel;
        return this.formatterFeel(star);

      },
      formatterFeel(star){
        if(star == '1'){
          return "极差";
        }else if(star == '2'){
          return "失望";
        }else if(star == '3'){
          return "一般";
        }else if(star == '4'){
          return "满意";
        }else if(star == '5'){
          return "惊喜";
        }
      },
      delComments(row){
        //删除评论
        this.$store.dispatch('comments/delComments',row)
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
