<template>
    <div class='spotsContainerCls'>
       
        <!-- 列表搜索区 start-->
        <div class='listCdCls' >
            <div class='listCdCls_1'>
                    <span>目的地方:</span>
                    <span>
                        <a href="javascript:;" :class='{active: 0 == isActive1}' @click="setActive1(0)">不限</a>

                        <a href="javascript:;" style="margin-left: 10px" v-for="(city,index) in cityList" :key="index"
                           :class='{active: city.id == isActive1}'
                           @click="setActive1(city.id)">{{city.name}}</a>
<!--                        <a href="javascript:;" :class='{active: 2 == isActive1}' @click="setActive1(2)"> 保定</a>-->
<!--                        <a href="javascript:;" :class='{active: 3 == isActive1}' @click="setActive1(3)"> 邢台</a>-->
<!--                        <a href="javascript:;" :class='{active: 4 == isActive1}' @click="setActive1(5)"> 邯郸</a>-->
<!--                        <a href="javascript:;" :class='{active: 5 == isActive1}' @click="setActive1(5)"> 沧州</a>-->
                   </span>
            </div>
             <div class='listCdCls_1'>
                    <span>风景等级:</span>
                    <span>
                        <a href="javascript:;" :class='{active: 0 == isActive2}' @click="setActive2(0)">不限</a>

                       <a href="javascript:;" style="margin-left: 10px" v-for="(level,index) in levelList" :key="index"
                          :class='{active: level.id == isActive2}'
                          @click="setActive2(level.id)">{{level.levelname}}</a>

                   </span>
            </div>
             <div class='listCdCls_1'>
                    <span>主题游玩:</span>
                    <span>
                        <a href="javascript:;" :class='{active: 0 == isActive3}' @click="setActive3(0)">不限</a>
                       <a href="javascript:;" style="margin-left: 10px" v-for="(theme,index) in themeList" :key="index"
                          :class='{active: theme.id == isActive3}'
                          @click="setActive3(theme.id)">{{theme.themename}}</a>
                   </span>
            </div>

             <div class='listCdCls_1'>
                    <span>价格区间:</span>
                    <span>
                        <a href="javascript:;" :class='{active: 0 == isActive4}' @click="setActive4(0)">不限</a>
                       <a href="javascript:;" style="margin-left: 10px" v-for="(pricefw,index) in pricefwList" :key="index"
                          :class='{active: pricefw.id == isActive4}'
                          @click="setActive4(pricefw.id)">{{pricefw.pricefw}}</a>
                   </span>
            </div>

        </div>
        <!-- 列表搜索区 end-->

        <!-- 排序区域 start-->
        <div class='orderCls'>
            <span style="margin-left: 20px"><a href="javascript:;" @click="getSpotsSortPage('id')">发布时间排序</a></span>
            <span ><a href="javascript:;" @click="getSpotsSortPage('price')">价格升序排序</a></span>
        </div>
        <!-- 排序区域 end-->
        <!-- 景区分页列表start -->
        <div class='spotsCls' v-for="(spot,index) in spotsList" :key="index">
            <div class='spotsLeftCls'>
                <img style="padding:10px;width:300px;height:200px"
                  :src='`http://localhost/upload/spots/${spot.fmUrl}`'>
            </div>
            <div class='spotsRightCls'  >
                <div style="margin-top:30px;width: 100%;display:flex;justify-content: space-between;">
                    <div style="font-size:25px">{{spot.name}}</div>
                    <div style="font-size:23px;margin-right:20px">门票价 <span style="color:red">¥{{spot.price}}</span> 元</div>
                </div>

                <div style="margin-top:25px;width: 100%;display:flex;justify-content: space-between;">
                    <div>
                        <div style="margin-top:10px">
                            <i class="el-icon-location" style="color:orange;font-size:20px"></i>
                            <span class='font-size:18px'>{{spot.address}}</span>
                        </div>
                        <div style="margin-top:10px">
                            <i class="el-icon-alarm-clock" style="color:orange;font-size:20px"></i>
                            <span class='font-size:18px'>{{spot.opentime}}</span>
                        </div>
                    </div>
                    <div style='width: 170px;
                                height: 56px;
                                margin-right:20px;
                                background-color: #ff7362;
                                color: #fff;
                                font-size: 18px;
                                text-align: center;
                                line-height: 56px;' @click="scheduleSpots(spot)">立即预定</div>
                </div>

            </div>
        </div>


        <div class="pageContainerCls">

        <!-- 景区分页列表end -->
          <pagination style="float:right" :total="total" :page.sync="queryParams.currentPage" :limit.sync="queryParams.pageSize" @pagination="getAllSpots" />
        </div>
   </div>
</template>
<script>
  import Pagination from '@/components/Pagination'

export default {
  components: {
    Pagination
  },
    data(){
        return {
            cityList:[],
            levelList:[],
            themeList:[],
            pricefwList:[],
            isActive1:0,
            isActive2:0,
            isActive3:0,
            isActive4:0,
            spotsList:[],
             total:0,
             queryParams:{
              currentPage:1,
              pageSize:5,
               cityid:0,
               levelid:0,
               themeid:0,
               pricefwid:0,
               orderByType:'id'
            }
        }
    },
    methods:{
        scheduleSpots(spot){
           // this.$router.push({name:'spots-detail',params:{spot:spot}})
          this.$router.push({name:'spots-detail',query:{id:spot.id}})
        },
        setActive1(index){
            this.isActive1 = index;
            this.queryParams.cityid = index;
            this.getAllSpots();
        },
      setActive2(index){
          this.isActive2= index;
          this.queryParams.levelid = index;
        this.getAllSpots();
      },
      setActive3(index){
        this.isActive3= index;
        this.queryParams.themeid = index;
        this.getAllSpots();
      },
        setActive4(index){
        this.isActive4= index;
        this.queryParams.pricefwid = index;
        this.getAllSpots();
      },
      //获取首页所有市
      getAllCity(){
        this.$store.dispatch('getAllCity').then((data) => {
          this.cityList =  data;
          //首页带过来的查询参数
          let cityName = this.$route.query.q;
          console.log(cityName)
          this.cityList.forEach(city=>{
             if(city.name == cityName){
               this.setActive1(city.id)
             }
          })
        })
          .catch((err) => {
            console.log('失败' + err)
          })
      },
      //获取所有的等级
      getAllLevel(){
        this.$store.dispatch('getAllLevel').then((data) => {
          this.levelList =  data;


        })
          .catch((err) => {
            console.log('失败' + err)
          })
      },
      //获取所有的主题
      getAllTheme(){
        this.$store.dispatch('getAllTheme').then((data) => {
          this.themeList = data
        })
          .catch((err) => {
            console.log('失败' + err)
          })
      },
      //获取所有的价格区间
      getAllPriceFw(){
        this.$store.dispatch('getAllPriceFw').then((data) => {
          this.pricefwList = data
        })
          .catch((err) => {
            console.log('失败' + err)
          })
      },
      //获取所有的目的地 分页获取
      getAllSpots(){
        this.$store.dispatch('spots/getAllSpots', this.queryParams).then((data) => {
          console.log(data);
          this.spotsList = data.rows;
          this.total = data.total;
        })
          .catch((err) => {
            console.log('失败' + err)
          })
      },
      getSpotsSortPage(orderByType){
          this.queryParams.orderByType = orderByType;
          this.getAllSpots();
      }
    },
    mounted() {
      this.getAllCity();
      this.getAllLevel();
      this.getAllTheme();
      this.getAllPriceFw();
      this.getAllSpots();
    }
}
</script>
<style lang="less">
    .active{
        color:white;
        background-color: #ff8c28;
        padding:5px;
    }

    .spotsContainerCls{
        margin-left:@lrwith;
        margin-right:@lrwith;
        background-color: #fafafa;
         min-width: 1024px !important;

            /** 查询条件 */
        .listCdCls{
            background-color:white;
            margin-top:10px;
            margin-bottom:10px;
            .listCdCls_1{
                margin-top:10px;
                margin-bottom:10px;
                font-size:20px;
                line-height:38px;
                span{
                        margin-left:20px;
                        a{
                            text-decoration: none;
                        }
                        a:visited{
                            color: orange;
                        }
                }
            }


        }

        /** 排序区域 */
        .orderCls{
            line-height: 100px;
            margin-bottom: 10px;
            background-color:white;
            span{
                font-size:20px;
                height: 50px;
                line-height: 50px;
                margin-right: 20px;
                padding-right: 20px;
                border-right: 1px solid #eee;
            }
        }

        /** 景区分页样式 */
        .spotsCls{
            display: flex;
            height: 200px;
            margin-bottom: 10px;
            background-color:white;
            .spotsRightCls{
                margin-left:20px;
                margin-right: 10px;
                width: 100%;
            }
        }


   }
</style>
