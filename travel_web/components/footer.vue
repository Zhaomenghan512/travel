<template>
  <div class="footer">
      <!-- 网站底部 开始-->
      <div class="container">
          <div class="descript">
                  <dl>
                  <dt>凤凰旅游网</dt>
                  <dd><span>0 </span>多个全球旅游目的地</dd>
                  <dd><span>0</span>个细分目的地新玩法</dd>
                  <dd><span>0</span> 次攻略下载</dd>
                  <dd><span>0</span> 家旅游产品供应商</dd>
                </dl>
                <dl>
                  <dt>关于我们</dt>
                  <dd>隐私政策 商标声明</dd>
                  <dd>服务协议 游记协议</dd>
                  <dd>商城平台服务协议</dd>
                  <dd>网络信息侵权通知指引</dd>
                  <dd>凤凰旅游网服务监督员</dd>
                  <dd>网站地图加入凤凰旅游</dd>
                </dl>
                <dl>
                  <dt>旅行服务</dt>
                  <dd><a href="">旅游特价</a><a href="">国际租车</a> </dd>
                  <dd><a href="">旅游问答</a><a href="">旅游保险</a> </dd>
                  <dd><a href="">旅游指南</a><a href="">订火车票</a> </dd>
                  <dd><a href="">旅游资讯</a><a href="">APP下载</a> </dd>
                  <dd><a href="">旅游攻略</a><a href="">酒店预订</a> </dd>
                </dl>
              <div class="erweima">
                
              </div>
          </div>

          <div class="footer-reg">
            <p>凤凰旅游网 Copyright © 2021-2022 凤凰旅游网 All Rights Reserved</p>
          </div>
      </div>
      <!-- 网站底部 结束-->
  </div>
</template>

<script>
export default {

}
</script>

<style lang='less' scoped>
.footer{
  height: 300px;
  padding-top: 30px;
  background-color: #333;
  display: flex;
  flex-direction: column;
   overflow: hidden;
   min-width: 1345px;
  .container{

    width:100%;
    display: flex;
    flex-direction: column;

    .descript{
      margin-left:@lrwith;
      margin-right:@lrwith;
      float: left;
      padding-bottom: 20px;

      dl{
        float: left;
        color: #ccc;
        dt{

                font-size: 16px;
                padding-bottom: 15px;
        }
        dd{
          font-size: 12px;
          line-height: 20px;
        }
        &:nth-child(1){
          margin-right: 80px;
          span{
            color: #ff8c28;
          }
        }
        &:nth-child(2){
          margin-right: 80px;
        }
        &:nth-child(3){
          dd{
            // width: 30px;
            a{
              font-size: 12px;
              color: #ccc;
              &:nth-child(1){
                float: left;
                margin-right: 5px;
              }
              &:nth-child(2){
                float: right;
              }
            }
          }
        }
      }
    }

    .erweima{
      float: right;
      text-align: center;
      img{
        width: 140px;
        display: block;
        margin: 0 auto;
      }
      span{
        margin-top: 5px;
        color:white;
      }
    }
    .footer-reg{
      margin-left:@lrwith;
      margin-right:@lrwith;

      line-height: 60px;
      text-align: center;
      font-size: 12px;
      color: #fff;
            border-top: 1px solid #666;
      }
  }
}

</style>
