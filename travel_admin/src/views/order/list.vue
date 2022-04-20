<template>
  <div class="app-container">
    <div class="filter-container" style="float:left">
      <el-form :model="queryParams" :inline="true">
        <el-form-item label="订单编号" prop="ordernum">
          <el-input
            v-model="queryParams.ordernum"
            placeholder="请输入订单编号"
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

    <el-table v-loading="loading" :data="orderList" highlight-current-row style="width: 100%;">

      <el-table-column label="序号"  type="index" align="center" width="60"/>
      <el-table-column prop="ordernum" align="center" label="订单编号" show-overflow-tooltip />
      <el-table-column prop="spotsname" align="center" label="景区名称" />
      <el-table-column prop="traveltime" align="center" label="出行时间" />
      <el-table-column prop="linkname" align="center" label="联系人" />
      <el-table-column prop="linktel" align="center" label="联系电话" />
      <el-table-column prop="linkemail" align="center" label="联系人邮件" />
      <el-table-column prop="hotel.hotelname" align="center" label="酒店名称" />
      <el-table-column prop="hotelprice" align="center" label="酒店价格" />
      <el-table-column prop="custorm.username" align="center" label="下单人" />
      <el-table-column prop="totalprice" align="center" label="总价格" />
      <el-table-column prop="createtime" align="center" label="创建时间" />
      <el-table-column prop="orderstatus" align="center"  label="订单状态">
        <template slot-scope="scope">
          <el-tag type="danger" v-if="scope.row.orderstatus==0">未支付</el-tag>
          <el-tag type="danger" v-if="scope.row.orderstatus==1">已支付</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center"   width="120">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-search"
            @click="showOrderInfo(scope.row)"
          >查看详情</el-button>

        </template>
      </el-table-column>
    </el-table>
    <pagination style="float:right" :total="total" :page.sync="queryParams.currentPage" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <el-dialog  width="80%" style="height: auto" :visible.sync="orderInfoDialogVisible">

      <div class='scheduleCls'>
        <!--线路信息-->
        <div class='productInfoCls'>
          <div style='color:#218fd5;margin-top:10px;margin-left:10px;text-align: left'>订单信息</div>
          <el-divider style='background-color:#218fd5'></el-divider>
          <div style='margin-left:10px;text-align: left;margin-bottom:10px'>
            <span >景区名称: </span>
            <span>
                 <el-input style='width:30%;font-size:20px' disabled v-model="this.orderInfo.spotsname" ></el-input>
            </span>
            <span >订单号码: </span>
            <span>
              <el-input style='width:30%;font-size:20px' disabled v-model="this.orderInfo.ordernum" ></el-input>
            </span>
          </div>

          <div style='margin-bottom:10px;margin-left:10px;text-align: left;'>
            <span >出行日期: </span>
            <span >
              <el-input style='width:30%;font-size:20px' disabled v-model="this.orderInfo.traveltime"></el-input>
            </span>
            <span >成人数量: </span>
            <span>
             <el-input style='width:30%;font-size:20px' disabled v-model="this.orderInfo.adultsnum"></el-input>
            </span>
          </div>

          <div style='margin-bottom:10px;margin-left:10px;text-align: left;'>
            <span>儿童数量: </span>
            <span >
             <el-input style='width:30%;font-size:20px' disabled v-model="this.orderInfo.childnum"></el-input>
            </span>
            <span >订单总价: </span>
            <span >
             <el-input style='width:30%;font-size:20px' disabled v-model="this.orderInfo.totalprice"></el-input>
            </span>
          </div>
<!--            <div style='margin-bottom:10px;margin-left:10px;text-align: left;'>-->
<!--              &lt;!&ndash;            <span >开放时间: </span>&ndash;&gt;-->
<!--              &lt;!&ndash;            <span style='font-size:20px'>&ndash;&gt;-->
<!--              &lt;!&ndash;              <el-input style='width:30%;font-size:20px' disabled v-model="this.orderInfo.opentime" ></el-input>&ndash;&gt;-->
<!--              &lt;!&ndash;            </span>&ndash;&gt;-->
<!--              <span >景区电话: </span>-->
<!--              <span >-->
<!--              <el-input style='width:30%;font-size:20px' disabled v-model="this.orderInfo.spotstel"></el-input>-->
<!--            </span>-->
<!--            </div>-->

        </div>

        <!--联系人信息-->
        <div class='linkmanInfoCls'>
          <div style='padding-top:20px;color:#218fd5;margin-left:10px;text-align: left'>联系人信息</div>
          <el-divider style='background-color:#218fd5'></el-divider>
          <div style='margin-bottom:10px;margin-left:10px;text-align: left;'>
            <span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名: </span>
            <span>
              <el-input style='width:30%;font-size:20px' disabled  v-model="this.orderInfo.linkname" ></el-input>
            </span>
            <span >联系电话: </span>
            <span>
              <el-input style='width:30%;font-size:20px' disabled v-model="this.orderInfo.linktel"  ></el-input>
            </span>
          </div>
          <div style='margin-bottom:10px;margin-left:10px;text-align: left;'>
            <span >电子邮箱: </span>
            <span style='font-size:20px'>
              <el-input style='width:30%;font-size:20px' disabled v-model="this.orderInfo.linkemail"  ></el-input>
            </span>

          </div>
        </div>



        <!--酒店信息-->
        <div class='hotelInfoCls'>
          <div style='margin-left:10px;padding-top:20px;color:#218fd5;margin-left:10px;text-align: left'>酒店信息:</div>
          <el-divider ></el-divider>
          <div style='margin-bottom:10px;margin-left:10px;text-align: left;'>
            <span >入住酒店: </span>
            <span>
                        <el-input style='width:30%;font-size:20px' disabled v-model="this.orderInfo.hotel.hotelname"  ></el-input>
                    </span>
            <span >联系电话: </span>
            <span>
               <el-input style='width:30%;font-size:20px' disabled v-model="this.orderInfo.hotellinktel"   ></el-input>
            </span>
          </div>
          <div style='margin-bottom:10px;margin-left:10px;text-align: left;'>
            <span >入住人数: </span>
            <span style='font-size:20px'>
                 <el-input style='width:30%;font-size:20px' disabled v-model="this.orderInfo.hotelnum"   ></el-input>
            </span>
            <span >每客/每人价格: </span>
            <span style="color:red">
                  ¥{{this.orderInfo.hotelprice}}
            </span>
          </div>
        </div>


        <!--游客信息-->
        <div class='userInfoCls' id='userInfoDivId' ref='userInfoDivRef' >
          <div style='margin-left:10px;padding-top:20px;color:#218fd5;margin-left:10px;text-align: left'>旅客信息</div>
          <el-divider style='background-color:#218fd5'></el-divider>
          <!-- 根据用户选择多少个 增加几行. -->
          <div style='margin-bottom:10px;margin-left:10px;text-align: left;' v-for="(traveler,index) in this.orderInfo.travels">
            <span>旅行游客: </span>
            <span >
             <el-input style='width:30%;font-size:20px' disabled v-model="traveler.travelerName"></el-input>
            </span>
            <span >证件号码: </span>
            <span >
             <el-input style='width:30%;font-size:20px' disabled v-model="traveler.cardNum"></el-input>
            </span>
          </div>
        </div>


      </div>

    </el-dialog>

  </div>
</template>

<script>
  import {getToken} from '@/common/util/tokenUtil'
  import authorize from '@/direct/authorize/index.js' // 权限判断指令
  import Pagination from '@/common/components/Pagination'
  export default {
    name: 'Order',
    directives: { authorize },
    components: {
      Pagination
    },
    data() {


      return {
        // 弹出层标题
        title: '',
        orderInfoDialogVisible:false,
        orderInfo:{
          hotel:{
            hotelname:''
          }
        },
        // 遮罩层
        loading: true,
        // 订单数据
        orderList: [],
        // 总数量
        total: 0,
        // 查询参数
        queryParams: {
          currentPage: 1,
          pageSize: 10,
          ordernum:''
        }
      }
    },
    created() {
      this.getList()
    },
    computed: {
      headers () {
        return {
          'Authorization': 'Bearer ' + getToken()
        }
      }
    },
    methods: {

      /** 查询角色列表 */
      getList() {
        this.$store.dispatch('order/getOrderList', this.queryParams)
          .then((data) => {
            this.orderList = data.rows
            this.total = data.total
            this.loading = false
          })
          .catch((err) => {
            console.log('失败' + err)
          })
      },
      showOrderInfo(row){
        this.orderInfoDialogVisible = true;
        this.orderInfo = row;
      }

    }


  }
</script>

<style lang="scss">
     .el-dialog{
       height: auto;
     }
    .scheduleCls{
      height: auto;
    }

    .productInfoCls{
      font-size:20px;
      background-color:#f6f6f6;
      height:230px;
      margin-bottom:10px;
      color:#666666;
    }
    .linkmanInfoCls{
      font-size:20px;
      background-color:#f6f6f6;
      height:200px;
      margin-bottom:10px;
      color:#666666;
    }
    .userInfoCls{
      font-size:20px;
      background-color:#f6f6f6;
      margin-bottom:10px;
      color:#666666;
    }

    .hotelInfoCls{
      font-size:20px;
      background-color:#f6f6f6;

      margin-bottom:10px;
      color:#666666;
    }
    .totalPriceAreaCls{
      font-size:20px;
      background-color:#f6f6f6;

      margin-bottom:10px;
      color:#666666;
    }





</style>
