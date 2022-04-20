<template>
    <div class='scheduleCls'>
        <!--线路信息-->
        <div class='productInfoCls'>
            <div style='margin-left:10px;padding-top:20px;color:#218fd5'>线路信息</div>
            <el-divider style='background-color:#218fd5'></el-divider>
              <div style='margin-bottom:10px'>
                    <span style='margin-left:10px'>景区名称: </span>
                    <span>
                         <el-input style='width:30%;font-size:20px' disabled v-model="scheduleSpot.name" ></el-input>
                    </span>
                    <span style='margin-left:10px'>景区地点: </span>
                    <span>
                         <el-input style='width:30%;font-size:20px' disabled v-model="scheduleSpot.address" ></el-input>
                    </span>
              </div>
               <div style='margin-bottom:10px;'>
                    <span style='margin-left:10px'>开放时间: </span>
                    <span style='font-size:20px'>
                        <el-input style='width:30%;font-size:20px' disabled v-model="scheduleSpot.opentime" ></el-input>
                    </span>
                    <span style='margin-left:10px'>景区电话: </span>
                        <span >
                         <el-input style='width:30%;font-size:20px' disabled v-model="scheduleSpot.tel"></el-input>
                        </span>
                </div>
                 <div style='margin-bottom:10px;'>
                    <span style='margin-left:10px'>出行日期: </span>
                    <span class='cxrqCls' style='font-size:20px'>
                         <el-date-picker
                            type="date"
                            v-model="cfDate"
                            placeholder="选择日期"
                            format="yyyy-MM-dd"
                            value-format="yyyy-MM-dd"
                            style="width:30%;">
                            </el-date-picker>
                    </span>
                    <span style='margin-left:10px'>成人数量: </span>
                        <span >
                         <el-input-number v-model="num" style="width:15%;margin-left:1px" @change="dynChange"  :min="1" :max="100" label="描述文字">
                         </el-input-number>
                          × <span style="color:red">¥{{scheduleSpot.price}}</span>
                    </span>
                </div>

                <div style='margin-bottom:10px;'>
                    <span style='margin-left:10px'>儿童数量: </span>
                    <span style='font-size:20px'>
                        <el-input-number v-model="num1" @change="dynChange" style="width:15%;margin-left:1px"  :min="0" :max="100" label="描述文字">
                         </el-input-number>
                      × <span style="color:red">¥{{scheduleSpot.price}} x 0.5</span>
                    </span>

                </div>
        </div>

        <!--联系人信息-->
        <div class='linkmanInfoCls'>
            <div style='margin-left:10px;padding-top:20px;color:#218fd5'>联系人信息</div>
            <el-divider style='background-color:#218fd5'></el-divider>
              <div style='margin-bottom:10px'>
                    <span style='margin-left:10px'>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名: </span>
                    <span>
                         <el-input style='width:30%;font-size:20px'  v-model="scheduleOrder.linkname" ></el-input>
                    </span>
                    <span style='margin-left:10px'>联系电话: </span>
                    <span>
                          <el-input style='width:30%;font-size:20px' v-model="scheduleOrder.linktel"  ></el-input>
                    </span>
              </div>
               <div style='margin-bottom:10px;'>
                    <span style='margin-left:10px'>电子邮箱: </span>
                    <span style='font-size:20px'>
                         <el-input style='width:30%;font-size:20px' v-model="scheduleOrder.linkemail"  ></el-input>
                    </span>

                </div>
        </div>

        <!--游客信息-->
        <div class='userInfoCls' id='userInfoDivId' ref='userInfoDivRef' style='height:150px;'>
            <div style='margin-left:10px;padding-top:20px;color:#218fd5'>旅客信息</div>
            <el-divider style='background-color:#218fd5'></el-divider>
              <!-- 根据用户选择多少个 增加几行. -->

                <div  style='margin-bottom:10px' :key='i' v-for="(item,i) in (num+num1)">
                        <span style='margin-left:10px'>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名: </span>
                        <span>
                            <el-input style='width:30%;font-size:20px' v-model="travelNames[i]"   ></el-input>
                        </span>
                        <span style='margin-left:10px'>证件号码(身份证): </span>
                        <span>
                            <el-input style='width:30%;font-size:20px'  v-model="cardNums[i]"  ></el-input>
                        </span>
                </div>
        </div>

        <!--酒店信息-->
        <div class='hotelInfoCls'>
            <div style='margin-left:10px;padding-top:20px;color:#218fd5'>酒店信息:(备注:不入住可以不填写)</div>
            <el-divider ></el-divider>
              <div style='margin-bottom:10px'>
                    <span style='margin-left:10px'>入住酒店: </span>
                    <span>
                         <el-select style='width:30%;font-size:20px' v-model="scheduleOrder.hotelid" @change="changeHotel()" placeholder="请选择入住酒店">
                            <el-option
                                v-for="item in hotels"
                                :key="item.id"
                                :label="item.hotelname"
                                :value="item.id">
                                <span style="float: left">{{ item.hotelname }}</span>

                            </el-option>
                        </el-select>
                    </span>
                    <span style='margin-left:10px'>联系电话: </span>
                    <span>
                          <el-input style='width:30%;font-size:20px' v-model="scheduleOrder.hotellinktel"   ></el-input>
                    </span>
              </div>
               <div style='margin-bottom:10px;'>
                    <span style='margin-left:10px'>入住人数: </span>
                    <span style='font-size:20px'>
                         <el-input style='width:30%;font-size:20px' v-model="scheduleOrder.hotelnum"   ></el-input>
                    </span>
                     <span style='margin-left:10px'>每客/每人价格: </span>
                    <span style="color:red">
                          ¥{{hotelprice}}
                    </span>
                </div>
        </div>

        <!-- 总价格 -->
        <div class='totalPriceAreaCls'>
             <div style='margin-left:10px;padding-top:20px;color:#218fd5'>总价格: 总价格 = (成人数*门票价格+儿童数*门票价格*0.5+酒店入住人数*酒店单价)</div>

            <el-divider ></el-divider>
             <div >
                 <span style='margin-left:10px'>
                     总  计:
                     <span style='color:red'>¥{{this.num*this.scheduleSpot.price+this.num1*this.scheduleSpot.price*0.5+this.hotelprice*this.scheduleOrder.hotelnum}}</span>元
                 </span>
                 <el-button style='
                    margin-left:350px;
                    cursor:
                    pointer;
                    background-color: #ff6e5c;
                    color: white;
                    line-height:30px;
                    margin-bottom:20px;
                    display: inline-block;
                    font-size: 15px;'
                            @click="saveSchedule"
                    >提交订单</el-button>
             </div>


        </div>



    </div>
</template>
<script>
export default {
        data() {
            return {
                scheduleSpotid:'',
                travelNames:[],
                cardNums:[],
                scheduleSpot:{
                   id:'',
                   name:'',
                   address:'',
                   opentime:'',
                   tel:'',
                   price:''
                },
                scheduleOrder:{
                  spotsid:'',
                  spotsname:'',
                  traveltime:'',
                  spotstel:'',
                  adultsnum:0,
                  childnum:0,
                  linkname:'',
                  linktel:'',
                  linkemail:'',
                  hotelid:'',
                  hotelnum:0,
                  hotellinktel:'',
                  hotelprice:0,
                  totalprice:'',
                  creatorid:0,
                  travels:[{
                    travelerName:'',
                    cardNum:''
                  }]
                },
                cfDate:'',
                num:1,
                num1:0,
                hotelVal:'',
                hotelprice:0.00,
                 hotels: []
            }
        },
        methods:{
            dynChange(value){
                let userInfoDivEle = document.getElementById("userInfoDivId");
                let numVal = this.num + this.num1;
                userInfoDivEle.style.height =  (120 + numVal*50)+'px';
                //清空数组
                this.travelNames = []
                this.cardNums = []
            },
           getScheduleSpotById(){
            this.$store.dispatch('spots/getSpotById', {id:this.scheduleSpotid}).then((data) => {
              this.scheduleSpot = data;
            })
              .catch((err) => {
                console.log('失败' + err)
              })
            },
            saveSchedule(){
              //需要提交到后台的数据
              this.scheduleOrder.spotsid = this.scheduleSpotid;
              this.scheduleOrder.spotsname = this.scheduleSpot.name;
              this.scheduleOrder.spotstel = this.scheduleSpot.tel;
              this.scheduleOrder.traveltime = this.cfDate;
              this.scheduleOrder.adultsnum = this.num;
              this.scheduleOrder.childnum = this.num1;
              this.scheduleOrder.hotelprice = this.hotelprice;
              this.scheduleOrder.totalprice =  this.num* this.scheduleSpot.price
                                 +this.num1*this.scheduleSpot.price*0.5
                                 +this.hotelprice*this.scheduleOrder.hotelnum;

              this.scheduleOrder.creatorid = this.$store.state.user.userMessage.id;
              this.scheduleOrder.travels = [];
              for(let i=0;i<this.travelNames.length;i++) {
                let traveler = {
                   travelerName:this.travelNames[i],
                   cardNum: this.cardNums[i]
                }
                this.scheduleOrder.travels.push(traveler)
              }
              //提交订单
              this.$store.dispatch('schedule/makeScheduleOrder',
                this.scheduleOrder).then((res) => {
                    if(res.code == 50000) {
                      this.$confirm(data.data, '温馨提示:', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning',
                        customClass: 'myClass'
                      });
                    }else{
                      this.$confirm('订单已生成,去个人中心查看', '温馨提示:', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning',
                        customClass: 'myClass'
                      }).then(() => {
                        this.$router.push("/user/my")
                      });
                    }
              })
                .catch((err) => {
                  console.log('失败' + err)
                })

            },
            getAllHotels(){
              this.$store.dispatch('schedule/getAllHotels').then((data) => {
                this.hotels = data;
              })
                .catch((err) => {
                  console.log('失败' + err)
                })
            },
            changeHotel(){
              let hotelid = this.scheduleOrder.hotelid;
              let selectedHotel = this.hotels.filter(hotel => {
                if(hotel.id== hotelid){
                  return hotel;
                }
              });
               this.hotelprice = selectedHotel[0].hotelprice;
            }
        },
        mounted(){
          let id = this.$route.query.id;
          this.scheduleSpotid = id;
          this.getScheduleSpotById();
          this.getAllHotels();
        }
    }
</script>
<style lang="less">
    .scheduleCls{
        margin-left:@lrwith;
        margin-right:@lrwith;
        .productInfoCls{
            margin-top: 20px;
            font-size:20px;
            background-color:#f6f6f6;
            height:300px;
            margin-bottom:10px;
            color:#666666;
        }
        .linkmanInfoCls{
            margin-top: 20px;
            font-size:20px;
            background-color:#f6f6f6;
            height:200px;
            margin-bottom:10px;
            color:#666666;
        }
        .userInfoCls{
            margin-top: 20px;
            font-size:20px;
            background-color:#f6f6f6;

            margin-bottom:10px;
            color:#666666;
        }

         .hotelInfoCls{
            margin-top: 20px;
            font-size:20px;
            background-color:#f6f6f6;

            margin-bottom:10px;
            color:#666666;
        }
        .totalPriceAreaCls{
             margin-top: 20px;
            font-size:20px;
            background-color:#f6f6f6;

            margin-bottom:10px;
            color:#666666;
        }
    }



</style>
