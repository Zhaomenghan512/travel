<template>
    <div class='spotsDetailCls'>
        <div class='spotsDetailCls-title'>
            <span>{{this.spot.name}}</span>
        </div>
        <el-divider></el-divider>
         <div class='spotsDetailCls-info'>
             <div class='spotsDetailCls-info-img'>
                  <img style='width:95%;height:360px' :src='`http://localhost/upload/spots/${this.spot.fmUrl}`'>
             </div>
             <div class='spotsDetailCls-info-content'>
                 <p>
                     <span style='font-size:20px'>简介<i class="el-icon-document"></i>:</span>
                   {{this.spot.spotsDesc}}
                </p>
                <el-divider></el-divider>
                 <p>
                     <span style='font-size:20px'>地址<i class="el-icon-location"></i>:</span>
                   {{this.spot.address}}
                </p>
                <p style="margin-top:10px">
                     <span style='font-size:20px'>开放时间:<i class="el-icon-alarm-clock"></i>:</span>
                  {{this.spot.opentime}}
                </p>
                <p style="margin-top:10px">
                     <span style='font-size:20px'>联系电话:<i class="el-icon-phone-outline"></i>:</span>
                  {{this.spot.tel}}
                </p>
                <el-divider></el-divider>
                 <p style="margin-top:10px">
                     <span style='font-size:20px'>价格:</span>
                     <span style='font-size:20px;color:red'>¥{{this.spot.price}}/人</span>

                     <span  >
                         <el-button style='margin-left:20px;
                         cursor: pointer;
                         background-color: #ff6e5c;
                         color: white;
                         line-height:30px;
                         padding:10px 20px;
                         display: inline-block;
                         font-size: 15px;'
                         @click="scheduleSpots">我要预定</el-button>
                     </span>
                </p>
             </div>
        </div>

             <div class='spotsDetailCls-info-cd'>
                    <el-tabs type="border-card" v-model="selectedTab">
                        <el-tab-pane label="景区简介" name="tab1">
                        <div class="spotsDetailCls-info-cd-1">
                            <p style="white-space: normal; text-align: justify;">
                              <span style="font-size: 14px;" v-html="this.spot.info">

                              </span>
                            </p>
                        </div>
                        </el-tab-pane>
                        <el-tab-pane label="购买须知" name="tab2">
                             <p v-html='this.spot.purchaseinfo'>

                               </p>
                        </el-tab-pane>
                        <el-tab-pane label="产品特色" name="tab3">
                           <p v-html='this.spot.features'></p>
                        </el-tab-pane>
                        <el-tab-pane label="用户点评" name="tab4">
                             <div style='display:flex;margin-bottom: 10px' v-for="(comment,index) in comments">
                                 <div style="display:flex;flex-direction:column;">
                                     <img :src='`http://localhost/upload/headimg/${comment.custorm.headImg}`'
                                          style='height:80px;width:80px;border-radius:50%;'/>
                                     <span style='margin-left:10px;margin-top:10px;text-align: center;width:80px'>{{comment.custorm.username}}</span>
                                 </div>
                                 <div style='margin-left:30px;'>
                                       <div>
                                           <span style='font-weight:800;margin-right:10px'>旅游交通:</span><span>{{formaterCommentStar(comment.transportsfeel)}}</span>
                                           <span style='font-weight:800;margin-left:30px;margin-right:10px'>酒店住宿:</span><span>{{formaterCommentStar(comment.hotelfeel)}}</span>
                                           <span style='font-weight:800;margin-left:30px;margin-right:10px'>是否玩爽:</span><span>{{formaterCommentStar(comment.playfeel)}}</span>
                                           <span style='margin-left:100px'>{{comment.createtime}}</span>
                                       </div>
                                       <div style='margin-top:30px;font-size:23px;color:#666'>
                                         {{comment.content}}
                                       </div>
                                 </div>
                             </div>
                             <el-divider></el-divider>

<!--                             <div style='display:flex'>-->
<!--                                 <div style="display:flex;flex-direction: column;">-->
<!--                                     <img src='../../static/img/tx3.jpg' style='height:100px;height:100px;border-radius:50%;'/>-->
<!--                                     <span style='margin-left:30px;margin-top:10px'>李四</span>-->
<!--                                 </div>-->
<!--                                 <div style='margin-left:30px;'>-->
<!--                                       <div>-->
<!--                                           <span style='font-weight:800;margin-right:10px'>旅游交通:</span><span>满意</span>-->
<!--                                           <span style='font-weight:800;margin-left:30px;margin-right:10px'>酒店住宿:</span><span>一般</span>-->
<!--                                           <span style='font-weight:800;margin-left:30px;margin-right:10px'>是否玩爽:</span><span>满意</span>-->
<!--                                           <span style='margin-left:500px'>2020-10-15</span>-->
<!--                                       </div>-->
<!--                                       <div style='margin-top:30px;font-size:23px;color:#666'>-->
<!--                                            价格实惠，酒店一般,床不是很大,热水来的很慢.-->
<!--                                       </div>-->
<!--                                 </div>-->
<!--                             </div>-->
<!--                             <el-divider></el-divider>-->

                            旅游交通:<el-rate v-model="transportsfeel" show-text></el-rate>
                            酒店住宿:<el-rate v-model="hotelfeel" show-text> </el-rate>
                            是否玩爽:<el-rate v-model="playfeel" show-text> </el-rate>

                            <el-input type="textarea" placeholder="来了就说点什么吧" v-model='content' ></el-input>
                            <el-button type="warning" @click="submitComments" >发表评论</el-button>
                        </el-tab-pane>

                    </el-tabs>
             </div>
    </div>
</template>
<script>
  export default {
    data() {
      return {
          transportsfeel: null,
          hotelfeel: null,
          playfeel: null,
          spotsid:'',
          ordernum:'',
          orderid:'',
          spot:{},
          content:'',
          selectedTab:'tab1',
          comments:[]
      }
    },
    methods:{
        scheduleSpots(){
            //是否已经登过 如果没有登录 需要先登录
            if(!this.$store.state.user.userMessage.username){
              this.$router.push("/user/login")
            }else {
              this.$router.push({name:'spots-schedule',query:{id:this.spotsid}})
            }
        },
        getSpotById(){
          this.$store.dispatch('spots/getSpotById', {id:this.spotsid}).then((data) => {
             this.spot = data;
          })
            .catch((err) => {
              console.log('失败' + err)
            })
        },
        submitComments(){

          if(this.$store.state.user.userMessage.username) {
              let params = {
                transportsfeel: this.transportsfeel,
                hotelfeel: this.hotelfeel,
                playfeel: this.playfeel,
                spotsid: this.spotsid,
                content: this.content,
                orderid:this.orderid,
                userid: this.$store.state.user.userMessage.id
              }
              this.$store.dispatch('spots/saveComments', params)
                .then((data) => {
                 if(data.code == 20000){
                   this.$msgbox.alert('评论成功',"温馨提示");
                    this.queryAllCommentsBySpotsid();
                 }else{
                   this.$msgbox.alert(data.message,"温馨提示");
                 }
              }).catch((err) => {
                console.log('失败' + err)
              })

          }else{
            this.$router.push("/user/login")
          }

        },
        queryAllCommentsBySpotsid(){
          console.log('spotsid:'+this.spotsid)
          this.$store.dispatch('spots/queryAllCommentsBySpotsid', {spotsid:this.spotsid}).then((data) => {
              this.comments = data;
              console.log(this.comments)
            })
            .catch((err) => {
              console.log('失败' + err)
            })
        },
        formaterCommentStar(star){
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
        }
         ,
         initData(){

           //初始化参数信息
           let id = this.$route.query.id;
           let selectedTab = this.$route.query.selectedTab;
           let orderid = this.$route.query.orderid;
           if(selectedTab){
             this.selectedTab = selectedTab;
           }
           if(orderid){
             this.orderid = orderid;
           }

           this.spotsid = id;
           //查询该景区所有的评论
           this.queryAllCommentsBySpotsid();
           this.getSpotById();
         }
    },
    mounted(){
       this.initData();
    }
  }
</script>
<style lang="less">
    .spotsDetailCls{
            margin-left:@lrwith;
            margin-right: @lrwith;
        .spotsDetailCls-title{
            margin-top:20px;
            margin-bottom:20px;
            span{
                font-size:30px
            }
        }
        .spotsDetailCls-info{
             margin-top:20px;
             margin-bottom:20px;
             display: flex;
            .spotsDetailCls-info-img{
                margin-right:20px;
                width: 50%;
                height: 400px;
            }
            .spotsDetailCls-info-content{
                 width: 50%;
                height: 400px;
            }

        }
        .spotsDetailCls-info-cd{
            margin-top:20px;
            margin-bottom:100px;
           .el-tabs__item{
               font-size:20px !important;
           }

        }

        .el-textarea__inner{
            min-height: 120px !important;
            margin-top:10px;
            margin-bottom:10px;
        }
        .el-button{
            float: right!important;
        }
    }


</style>
