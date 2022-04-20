<template>
    <div class='notesContainerCls'>
        <!-- 游记封面 -->
        <div class='notesDetailsFmCls'>
            <img src='../../static/img/article/yjfm2.jpeg'>
            <div class='notesDetailsFmCls-title'>{{this.notes.title}} </div>
             <img class='notesDetails-tx' :src='`http://localhost/upload/headimg/${this.notes.custorm.headImg}`'> 
            <div style='margin-left:32%;margin-top:3%;'>
               <span style='color:#ff7200'> {{this.notes.custorm.username}}</span> 
               <span style='margin-left:10px;color: #acacac;'>{{this.notes.createtime}} </span>
               <span style='margin-left:10px;color: #acacac;'> <i class="el-icon-view"></i>  {{this.notes.viewnum}} </span>
            </div>  
        </div>
        <el-divider></el-divider>

        <!-- 游记信息 -->
        <div class='notesInfoCls'>
             <div><i style='color:#ff9e00' class='el-icon-time'></i> 出发时间 <span style='color: #ff9d00;margin: 0 5px;'>/</span> {{this.notes.departureTime}} </div> 
             <div><i style='color:#ff9e00' class='el-icon-sunny'></i>出行天数 <span style='color: #ff9d00;margin: 0 5px;'>/</span> {{this.notes.traveldays}} 天    </div>
             <div> <i style='color:#ff9e00' class='el-icon-user'></i>人均费用 <span style='color: #ff9d00;margin: 0 5px;'>/</span> {{this.notes.precost}}</div>
       
        </div>
       

        <!-- 游记文章开始 -->
        <div class='notesActicleCls' v-html='this.notes.content'>       
        
        </div>
          <!-- 游记文章结束 -->


    </div>
</template>

<script>
export default {

    data(){
        return{
            notes:{
                custorm:{
                    username:'',
                    headImg:''
                }
            }
        }
    },
    methods:{
        getNotesById(noteid){
             this.$store.dispatch('notes/getNotesById', {id:noteid}).then((data) => {
             this.notes = data;
             console.log(this.notes);
          })
            .catch((err) => {
              console.log('失败' + err)
            })
        }
    },
    created(){
        let noteid = this.$route.query.id;
        this.getNotesById(noteid);
    }
    
}
</script>
<style lang="less">
.notesContainerCls{
    
    .notesDetailsFmCls{
        margin-bottom: 10px;
        img{
            width:100%;
            height:400px;
        }
        .notesDetailsFmCls-title{
            position: absolute;
            width: 650px;
            height:130px;
            left:28%;
            top:20%;
            z-index: 10;
            font-size:30px;
            color:white;
            margin-left: 20px;
            margin-top:20px;
        }

        .notesDetails-tx{
            position: absolute;
            width: 10%;
            height:20%;
            left:18%;
            top:15%;
            border-radius: 50%;
            z-index: 11;
            font-size:30px;
            color:white;
         
        }

    }

    .notesInfoCls{
        margin-left:@lrwith;
        margin-right:@lrwith;
        display: flex;
        justify-content: space-between;
        border:1px dashed #d7d7d7;
        line-height:50px;
    }

    .notesActicleCls{
       margin-left:@lrwith;
        margin-right:@lrwith;
        margin-top:10px; 
    }



}
</style>