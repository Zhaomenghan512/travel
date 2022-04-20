<template>
    <div>
        <!-- 轮播图start -->
        <div class='notesFmCls'> 
             <el-carousel  direction="vertical" :autoplay="true">
                <el-carousel-item v-for="(item,index) in notesImgs" :key="index">
                     <img class="imgCls" :src='item.url' />
                </el-carousel-item>
                 
            </el-carousel>
              <!-- 图片上文字 -->
                <div class="notesFmTextCls">
                    <div >23/Oct.2020</div>
                    <div >大自然的美好,等着您来捕捉,记录点滴,不负好时光</div>
                </div>
        </div>
        <!-- 轮播图end -->
        <!-- 游记列表-->
        <div class='notesIndexCls'>
            <!-- 左侧列表-->
            <div class='notesListCls'>
                <div style='font-size:30px;
                            font-weight:600;
                            color:#ff8a00;
                            display:flex;
                            justify-content:space-between;'>
                    <span>热门游记</span>
                  
                </div>
                <el-divider></el-divider>
                <!-- 左侧游记开始-->
                <div class='notesArticleCls' v-for="(note,index) in notesList" :key='index'>
                    <div class="notesArticleCls1" @click='goNotesDetail(note.id)'>
                        {{note.title}}
                    </div>
                    <div class="notesArticleCls2">
                        <div class="notesArticleCls2-1">
                            <img style='height:131;width:300px;object-fit: cover; ' 
                            :src='`http://localhost/upload/notes/${note.fmUrl}`' />
                        </div>
                        <div class="notesArticleCls2-2">
                            <div class='notesArticleCls2-2-1' v-html="note.notesdesc"
                            style='height:150px;margin-left:10px;font-size:18px;color:#666; width:350px;
                            overflow: hidden;text-overflow: ellipsis;'>
                               ...
                            </div>
                            <div style='margin-left:10px;'>
                                <div style='width:100%;'>
                                    <span>By</span>
                                    <span style='color:#ff8a00'>{{note.custorm.username}}</span>  
                                    <span style='margin-left:10px;'>{{note.createtime}}</span>
                                    <span style='float: right;margin-right:10px;'>
                                        <i class="el-icon-view"></i>
                                        <span style='margin-left:10px'>{{note.viewnum}}</span>
                                    </span>
                                </div>
                            </div>

                        </div>
                    </div>
                   
                </div>
                  <el-divider></el-divider>
                  <!-- 景区分页列表end -->
                    <pagination style="float:right" :total="total" 
                    :page.sync="queryParams.currentPage" 
                    :limit.sync="queryParams.pageSize" @pagination="getAllNotes" /> 
                <!-- 左侧游记结束-->
            </div>  
             <!-- 右侧作者-->
            <div class='notesAuthorCls'>
                <div style='font-size:30px;font-weight:600;color:#ff8a00'>推荐作者</div>
                <el-divider></el-divider>
                <!-- 左右结构-->
                <div class="notesAuthorCls1">
                    <div class='notesAuthorCls1-1'>
                        <img src='../../static/img/tx1.jpg'> 
                    </div>
                    <div class='notesAuthorCls1-2'>
                        <span style='margin-left:30px;line-height:80px;font-size:20px'>张无忌</span>
                    </div>
                </div>

                 <div class="notesAuthorCls1">
                    <div class='notesAuthorCls1-1'>
                        <img src='../../static/img/tx2.jpg'> 
                    </div>
                    <div class='notesAuthorCls1-2'>
                        <span style='margin-left:30px;line-height:80px;font-size:20px'>张三丰</span>
                    </div>
                </div>

                 <div class="notesAuthorCls1">
                    <div class='notesAuthorCls1-1'>
                        <img src='../../static/img/tx3.jpg'> 
                    </div>
                    <div class='notesAuthorCls1-2'>
                        <span style='margin-left:30px;line-height:80px;font-size:20px'>张翠山</span>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
</template>
<script>
import Pagination from '@/components/Pagination'
     export default {
     data() {
        return {
            notesList:[],
              total:0,
             queryParams:{
              currentPage:1,
              pageSize:5
            },
            notesImgs:[
                {url:require('../../static/img/notes1.jpeg')},
                {url:require('../../static/img/notes2.jpg')},
                {url:require('../../static/img/notes3.jpg')}],
        };
    },
    methods: {

        goNotesDetail(noteid){
            this.$router.push({name:'notes-detail',query:{id:noteid}})
        },
         getAllNotes(){
        this.$store.dispatch('notes/getAllNotes', this.queryParams).then((data) => {
            console.log(data);
          this.notesList = data.rows;
          this.total = data.total;
        })
          .catch((err) => {
            console.log('失败' + err)
          })
      },
     
    },
    mounted() {
     
      this.getAllNotes();
    }
}
</script>
<style lang="less">
   /** 游记封面 */
   .notesFmCls{

         /** 封面图片part1 */
       img{
           width: 100%;
           height:400px;
       }
         /** 封面文本part2 */
       .notesFmTextCls{
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
   }

   /** 游记列表index */
   .notesIndexCls{
       margin-left:@lrwith;
       margin-right:@lrwith;
       margin-top:20px;
       min-width: 1024px !important;
       
       display: flex;
       justify-content: space-between;
       flex-wrap: nowrap;
       overflow: hidden;
       /** 左侧游记列表 */
       .notesListCls{
             width: 75%;
             display:flex;
             height: 1600px;
             flex-direction: column;
             /** 左侧文章样式 */
             .notesArticleCls{
                 margin-top:  10px;
                 margin-bottom:  10px;
                 height: 250px;
                .notesArticleCls1{
                    font-size:20px;
                    color:#666;
                    cursor: pointer;
                   
                }
                .notesArticleCls2{
                    margin-top: 20px;
                    display: flex;
                }
             }


           
       }
       /** 右侧作者列表 */
        .notesAuthorCls{
              width: 20%;
              display:flex;
              flex-direction: column;
              
               overflow: hidden;
              .notesAuthorCls1{
                  display:flex;
                  margin-bottom: 10px;
                  img{
                      height: 80px;
                      width:  80px;
                      border-radius: 50%;
                  }
              }  
       }
   }
</style>

<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
    
  }



</style>