<template>
  <div class="container">
    <!-- 首页轮播开始-->
    <div class='lbcls'>
        <el-carousel height="500px" :interval="5000" arrow="always">
          <el-carousel-item v-for="(item, index) in banners" :key="index">
            <img class="imgCls" :src='item.url' />
          </el-carousel-item>
        </el-carousel>

      <!-- 图片上文字 -->
      <div class="indexLbTextCls">
        <div >20/Dec.2020</div>
        <div >山水人文,特色美食,民族风情,细细品味</div>
      </div>

         <!-- 搜索框 -->
      <div class="search">
        <span>景区目的地</span>
        <input  v-model="searchInfo" placeholder="搜索您想去的地方" type="text">
        <!-- 点击搜索图标时触发事件 -->
        <i @click='goSpotsByConditon'  class="el-icon-search icon-search" />
      </div>

      <!-- 图片上文字 -->
      <div class="indexLbTextCls1">
        <div>所有图片源自网络,如果侵权,联系删除</div>
      </div>

    </div>
    <!-- 首页轮播结束-->
    <!-- 主题开始-->
    <div class='tpCls'>
          <div style="display:flex;justify-content:space-between;width:20%;margin-right:10px;color:#9d9d9d;border: 1px solid;height: 100px">
              <span style="margin-left: 10px;line-height: 100px;text-align: center">当地游</span>
            <span style="margin:10px 10px;">
                 <img height="80px" width="80px"
                         src="../static/img/index/jq1.png">
              </span>
          </div>
      <div style="display:flex;justify-content:space-between;width:20%;margin-right:10px;color:#9d9d9d;border: 1px solid;height: 100px">
        <span style="margin-left: 10px;line-height: 100px;text-align: center">自由行</span>
        <span style="margin:10px 10px;">
                 <img height="80px" width="80px"
                      src="../static/img/index/jq2.png">
              </span>
      </div>
      <div style="display:flex;justify-content:space-between;width:20%;margin-right:10px;color:#9d9d9d;border: 1px solid;height: 100px">
        <span style="margin-left: 10px;line-height: 100px;text-align: center">订酒店</span>
        <span style="margin:10px 10px;">
                 <img height="80px" width="80px"
                      src="../static/img/index/jq3.png">
              </span>
      </div>
      <div style="display:flex;justify-content:space-between;width:20%;margin-right:10px;color:#9d9d9d;border: 1px solid;height: 100px">
        <span style="margin-left: 10px;line-height: 100px;text-align: center">主题游</span>
        <span style="margin:10px 10px;">
                 <img height="80px" width="80px"
                      src="../static/img/index/jq4.png">
              </span>
      </div>
      <div style="display:flex;justify-content:space-between;width:20%;margin-right:10px;color:#9d9d9d;border: 1px solid;height: 100px">
        <span style="margin-left: 10px;line-height: 100px;text-align: center">跟团游</span>
        <span style="margin:10px 10px;">
                 <img height="80px" width="80px"
                      src="../static/img/index/jq5.png">
              </span>
      </div>
   </div>
   <!-- 主题结束-->
   <!-- 带你游览河北start-->
   <div class='ylhbCls'>
      <div class="ylhbCls_l">
        <div>
          <span style='font-size:28px'>带你游览四川省</span>
          <span style='font-size:20px;color:grey'>了解从这里开始</span>
          </div>

      </div>
      <div class='ylhbCls_r'>
          <div style="text-align:center;background:#84b9f4;border-radius:20px;width:160px;line-height:40px;">
            <a href="javascript:;"  @click="goSpots" style='color:#fff;text-decoration: none;'>查看更多内容</a>
          </div>
      </div>
   </div>
   <div class='areaCls'>
      <div class='areaCls_l'>
          <div class="areaCls_l_city">
              <ul>
                  <li v-for="(city,index) in cityList"  :key="index">
                    <a  href="javascript:;" @click="goSpotsByConditon1(city.name)">{{city.name}}</a>
                  </li>

              </ul>
            </div>
      </div>
<!--       <div class='areaCls_r' style='margin-left:10px'>-->
<!--          <div class='areaCls_r1' style='width:100%'>-->
<!--              <img src='../static/img/spots/sjz_xpp.jpg' style="width:32.6%;height:200px">-->
<!--              <img src='../static/img/spots/bd_ysp.jpg' style="width:32.6%;height:200px">-->
<!--              <img src='../static/img/spots/cd_bssz.jpg' style="width:32.6%;height:200px">-->
<!--          </div>-->
<!--           <div class='areaCls_r2' style='width:100%'>-->
<!--              <img src='../static/img/spots/qhd_bdh.jpg' style="width:32.6%;height:200px">-->
<!--              <img src='../static/img/spots/xt_ths.jpg' style="width:32.6%;height:200px">-->
<!--              <img src='../static/img/spots/zjk_zb.jpg' style="width:32.6%;height:200px">-->
<!--          </div>-->
<!--      </div>-->
   </div>
   <!-- 带你游览河北end-->

    <div class='cqdfCls'>
      <div class="cqdfCls_l">
        <div>
          <span style='font-size:28px'>常去目的地</span>
          <span style='font-size:20px;color:grey'>世界很大想出去看看</span>
        </div>
      </div>
      <div class='cqdfCls_r'>
        <div style="text-align:center;background:#84b9f4;border-radius:20px;width:160px;line-height:40px;">
          <a href="javascript:;" target="_blank" @click='goSpots' style='color:#fff;text-decoration: none;'>查看更多内容</a>
        </div>
      </div>
    </div>

    <div class='cqdf_list_Cls'>
      <div style='background:#f6f6f6;width:32%'>
        <img src='../static/img/index/spot/spot1.png'>
        <div style='font-weight: 400;
        font-size:20px;margin-left:10px;
        margin-top:10px;
        margin-bottom:10px'>九寨沟</div>
      </div>

      <div style='background:#f6f6f6;width:32%'>
        <img src='../static/img/index/spot/spot2.png'>
        <div style='font-weight: 400;font-size:20px;margin-left:10px;margin-top:10px;margin-bottom:10px'>稻城亚丁</div>

      </div>

      <div style='background:#f6f6f6;width:32%'>
        <img src='../static/img/index/spot/spot3.png'>
        <div style='font-weight: 400;font-size:20px;margin-left:10px;margin-top:10px;margin-bottom:10px'>色达</div>
      </div>
    </div>
    <div class='cqdf_list_Cls'>
      <div style='background:#f6f6f6;width:32%'>
        <img src='../static/img/index/spot/spot4.png'>
        <div style='font-weight: 400;
        font-size:20px;margin-left:10px;
        margin-top:10px;
        margin-bottom:10px'>四姑娘山</div>
      </div>

      <div style='background:#f6f6f6;width:32%'>
        <img src='../static/img/index/spot/spot5.png'>
        <div style='font-weight: 400;font-size:20px;margin-left:10px;margin-top:10px;margin-bottom:10px'>峨嵋山</div>

      </div>

      <div style='background:#f6f6f6;width:32%'>
        <img src='../static/img/index/spot/spot6.png'>
        <div style='font-weight: 400;font-size:20px;margin-left:10px;margin-top:10px;margin-bottom:10px'>宽窄巷子</div>
      </div>
    </div>

    <!-- 带你游览河北start-->
    <div class='ylhbCls'>
      <div class="ylhbCls_l">
        <div>
          <span style='font-size:28px'>欢乐游玩</span>
          <span style='font-size:20px;color:grey'>用图文记录您的瞬间,成为永恒</span>
        </div>

      </div>
      <div class='ylhbCls_r'>
        <div style="text-align:center;background:#84b9f4;border-radius:20px;width:160px;line-height:40px;">
          <a href="javascript:;"  @click="goNotes" style='color:#fff;text-decoration: none;'>查看更多内容</a>
        </div>
      </div>
    </div>
    <div class='ywCls'>
      <div class='ywCls_l'>
        <div class="ywCls_l_city">
          <ul>
            <li  >

                 <img src="../static/img/index/yw/i1.png"
                    style="height: 80px;width: 80px;border-radius:50%;border:solid 1px;color:#50b400">
            </li>
            <li  >

              <img src="../static/img/index/yw/i2.png"
                   style="height: 80px;width: 80px;border-radius:50%;border:solid 1px;color:#50b400">
            </li>
            <li  >

              <img src="../static/img/index/yw/i3.png"
                   style="height: 80px;width: 80px;border-radius:50%;border:solid 1px;color:#50b400">
            </li>
            <li  >

              <img src="../static/img/index/yw/i4.png"
                   style="height: 80px;width: 80px;border-radius:50%;border:solid 1px;color:#50b400">
            </li>


          </ul>
        </div>
      </div>
       <div class='ywCls_r' style='margin-left:10px'>
          <div class='ywCls_r1' style='width:100%'>
              <img src='../static/img/index/psxz/ps1.png' style="width:49%;height:200px">
              <img src='../static/img/index/psxz/ps2.png' style="width:49%;height:200px">

          </div>
         <div class='ywCls_r1' style='width:100%'>
           <img src='../static/img/index/psxz/xz1.png' style="width:49%;height:200px">
           <img src='../static/img/index/psxz/xz2.png' style="width:49%;height:200px">

         </div>
      </div>
    </div>
    <!-- 带你游览河北end-->
  </div>
</template>

<script>

export default {
   data () {
    return {
      searchInfo: '',
       fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
        url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      banners:[
      {url:require('../static/img/lb1.jpg')},
      {url:require('../static/img/lb2.jpg')},
      {url:require('../static/img/lb3.jpg')}],
       currentDate:'2020-10-21',
       cityList:[]
     }
    },
    methods:{
      //获取首页所有市
      getAllSpots(){
          this.$store.dispatch('getAllCity').then((data) => {
           this.cityList =  data;
          })
         .catch((err) => {
           console.log('失败' + err)
         })
      },
      //去目的地
      goSpots(){
         this.$store.commit('selectSpots')
        this.$router.push("/spots")

      },
      goSpotsByConditon(){
         this.$store.commit('selectSpots')
         this.$router.push({name:'spots',query:{q:this.searchInfo}})

      },
      goSpotsByConditon1(cityName){
        this.$store.commit('selectSpots')
        this.$router.push({name:'spots',query:{q:cityName}})
      },
      //去游记
      goNotes(){
        this.$store.commit('selectJpyj')
         this.$router.push("/notes")
      }
    },
    mounted(){
      this.getAllSpots()
    }
}
</script>

<style lang="less">
.container{
   width:100%;
    min-width: 1024px !important;
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

  .lbcls{
    height: 500px;
  }



  .imgCls{
    height: 500px;
    width: 100%;
  }

  .tpCls{
    margin-left: 160px;
    margin-right: 160px;
    margin-top:20px;
    display: flex;
    justify-content: space-between;
  }
  .ylhbCls{
     margin-left:@lrwith;
     margin-right:@lrwith;
     display: flex;
     justify-content: space-between;
     .ylhbCls_l{
       margin-top:20px;
     }
      .ylhbCls_r{
       margin-top:20px;
     }
  }

  .areaCls{
      margin-left:@lrwith;
      margin-right:@lrwith;
      margin-top:20px;
      display: flex;
      .areaCls_l{
            width: 100%;
            height: 200px;
            background: #f6f6f6;
            .areaCls_l_city{
               text-align: center;
               ul{
                 margin: 0;
                 padding: 0;
                 border: 0;
               }
               li {
                  float: left;
                  width: 120px;
                  height: 25px;
                  line-height: 25px;
                  margin-bottom: 15px;
                  margin-left: 15px;
                  margin-top: 15px;
                   a {
                      display: block;
                      font-size: 14px;
                      text-align: center;
                      background: #fff;
                      border: 1px solid #e2e2e2;
                      border-radius: 15px;
                      line-height: 35px;
                      color: #666;
                  }
                  a:link, a:visited {
                      color: #666;
                      text-decoration: none;
                  }
                  a:hover {  background-color : orange ; color:#fff }
              }
            }
      }
       ol, ul {
          padding-left: 0;
          list-style-type: none;
      }




  }

  .ywCls{
    margin-left:@lrwith;
    margin-right:@lrwith;
    margin-top:20px;
    display: flex;
    .ywCls_l{
      width: 30%;
      height: 400px;
      background: #f6f6f6;
      .ywCls_l_city{
        text-align: center;
        ul{
          margin: 0;
          padding: 0;
          border: 0;
        }
        li {
          float: left;
          width: 120px;
          height: 25px;
          line-height: 55px;
          margin-bottom: 35px;
          margin-left: 35px;
          margin-top: 30px;
          a {
            display: block;
            font-size: 14px;
            text-align: center;
            background: #fff;
            border: 1px solid #e2e2e2;
            border-radius: 15px;
            line-height: 35px;
            color: #666;
          }
          a:link, a:visited {
            color: #666;
            text-decoration: none;
          }
          a:hover {  background-color : orange ; color:#fff }
        }
      }
    }
    ol, ul {
      padding-left: 0;
      list-style-type: none;
    }
  }
  /** 常去地方样式 */
  .cqdfCls{
    margin-left:@lrwith;
    margin-right:@lrwith;
    display: flex;
    justify-content: space-between;
    .cqdfCls_l{
      margin-top:20px;
    }
    .cqdfCls_r{
      margin-top:20px;
    }
  }
  /** 常去地方列表 */
  .cqdf_list_Cls{
    margin-left:@lrwith;
    margin-right:@lrwith;
    display: flex;
    justify-content: space-between;
    margin-top:20px;
    margin-bottom:20px;

    img{
      width: 100%;
      height: 150px;
    }

  }

  /** 精品游记样式 */
  .jpyjCls{
    margin-left:@lrwith;
    margin-right:@lrwith;
    display: flex;
    justify-content: space-between;
    .jpyjCls_l{
      margin-top:20px;
    }
    .jpyjCls_r{
      margin-top:20px;

    }
  }



  /** 精品游记样式 */
    .jpyjCls{
     margin-left:@lrwith;
     margin-right:@lrwith;
     display: flex;
     justify-content: space-between;
     .jpyjCls_l{
       margin-top:20px;
     }
      .jpyjCls_r{
       margin-top:20px;

     }
  }

  /** 精品游记列表 */
  .jpyj_list_Cls{
     margin-left:@lrwith;
     margin-right:@lrwith;
     display: flex;
     justify-content: space-between;
     margin-top:20px;
     margin-bottom:20px;

     img{
       width: 100%;
       height: 300px;
     }

  }

  /** 封面文本part2 */
  .indexLbTextCls{
    position: absolute;
    width: 650px;
    height:130px;
    left:10%;
    top:10%;
    z-index: 10;
    font-size:30px;
    color:white;
    margin-left: 20px;
    margin-top:20px;
  }

  /** 封面文本part2 */
  .indexLbTextCls1{
    position: absolute;
    width: 650px;
    height:130px;
    left:35%;
    top:33%;
    z-index: 10;
    font-size:30px;
    color:white;
    margin-left: 20px;
    margin-top:20px;
  }

  /** 搜索框 */
// @media (max-width: 1440px){
 .search {
    position:absolute;
    width: 50%;
    height:20%;
    left: 50%;
    top: 45%;
    z-index: 10;
    margin-top: -6%;
    transform: translate(-50%, -50%);
    background-color: rgba(0,0,0,.5);
     min-width: 486px !important;


    span {
      display: block;
      float: left;
      width: 100%;
      height: 40px;
      line-height: 40px;
      margin-left: 30px;
      color: #fff;
      cursor: pointer;
      ::after {
        transform: scale(1.1, 1.3) perspective(0.5em) rotateX(2.2deg);
      }
    }
    input {
      width: 90%;
      height: 46px;
      margin-left:30px;
      background: #fff;
      border-radius: 0 4px 4px 4px;
      border: 1px rgba(255, 255, 255, 0.2) solid;
      border-top: none;
      box-sizing: unset;
      color: #757575;
      font-size: 16px;
      text-indent: 20px;
    }
    .icon-search{
      position: absolute;
      top: 40px;
      left: 85%;
      padding: 10px;
      font-size: 30px;
      cursor: pointer;
    }
  }
}

// }

</style>
