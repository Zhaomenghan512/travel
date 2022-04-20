<template>
    <div class="myCls">

        <el-container style="margin-top:10px;margin-bottom:10px;height: 800px; border: 1px solid #eee">
        <el-aside width="200px" >
            <el-menu :default-openeds="['1']">
                <el-menu-item index="1">
                    <i class="el-icon-user"></i>
                    <span slot="title" @click="changeTag(1)">个人资料</span>
                </el-menu-item>
                <el-menu-item index="2">
                    <i class="el-icon-search"></i>
                    <span slot="title" @click="changeTag(2)">个人积分</span>
                </el-menu-item>
                  <el-menu-item index="3">
                    <i class="el-icon-edit"></i>
                    <span slot="title"  @click="changeTag(3)">修改密码</span>
                </el-menu-item>
                  <el-menu-item index="4">
                    <i class="el-icon-setting"></i>
                    <span slot="title" @click="changeTag(4)">设置头像</span>
                </el-menu-item>
                 <el-menu-item index="5">
                    <i class="el-icon-tickets"></i>
                    <span slot="title" @click="changeTag(5)">个人订单</span>
                </el-menu-item>

                 <el-menu-item index="6">
                    <i class="el-icon-tickets"></i>
                    <span slot="title" @click="changeTag(6)">发表游记</span>
                </el-menu-item>
              <el-menu-item index="7">

                <span slot="title" @click="changeTag(7)"></span>
              </el-menu-item>
              <el-menu-item index="7">

                <span slot="title" @click="changeTag(7)"></span>
              </el-menu-item>
              <el-menu-item index="8">

                <span slot="title" @click="changeTag(8)"></span>
              </el-menu-item>
              <el-menu-item index="9">

                <span slot="title" @click="changeTag(9)"></span>
              </el-menu-item>
              <el-menu-item index="10">

                <span slot="title" @click="changeTag(9)"></span>
              </el-menu-item>
              <el-menu-item index="11">

                <span slot="title" @click="changeTag(9)"></span>
              </el-menu-item>

              <el-menu-item index="12">

                <span slot="title" @click="changeTag(9)"></span>
              </el-menu-item>
              <el-menu-item index="13">
                <span slot="title" @click="changeTag(9)"></span>
              </el-menu-item>
            </el-menu>
        </el-aside>

        <el-container>


            <el-main>
                <div class='infoCls' v-if='showCount==1' style="diplay:none">

                            <div>
                                 <span style="font-size:22px">账号信息</span>
                                 <span><el-divider style=" width: 50%;"></el-divider></span>
                                <div>
                                    <span style="font-size:22px">用户名称</span>
                                    <span style='margin-left:20px'>
                                        <el-input style='width:30%' type='text' v-model="loginUser.username"></el-input>
                                    </span>

                                    <span style="margin-left:20px;font-size:22px">电话号码</span>
                                    <span style='margin-left:20px'>
                                        <el-input style='width:30%' type='text' v-model="loginUser.tel"></el-input>
                                    </span>
                                </div>

                                 <div style="margin-top:10px">
                                    <span style="font-size:22px">电子邮箱</span>
                                    <span style='margin-left:20px'>
                                        <el-input style='width:30%' type='text' v-model="loginUser.email"></el-input>
                                    </span>
                                </div>

                                <div style="margin-top:10px">
                                   <el-button style='float:right;margin-right:170px;' type="primary" @click="editSaveUserInfo">编辑保存</el-button>
                                </div>

                            </div>

                </div>
                 <div class='checkPointCls' v-if='showCount==2' style="diplay:none">
                        <div>
                            <span style="font-size:22px">我的积分信息</span>
                            <span><el-divider style=" width: 50%;"></el-divider></span>
                              <div>
                                    <span style="font-size:22px">总积分:{{loginUser.totalpoints}}</span>

                              </div>
                        </div>

                </div>
                <div class='updatePwdCls' v-if='showCount==3' style="diplay:none">
                    <div>
                            <span style="font-size:22px">修改账号密码</span>
                            <span><el-divider style=" width: 50%;"></el-divider></span>
<!--                              <div style='margin-bottom:10px'>-->
<!--                                    <span style="font-size:22px">原来密码</span>-->
<!--                                    <span style='margin-left:20px'>-->
<!--                                        <el-input style='width:30%' type='text' placeholder="输入原来密码"></el-input>-->
<!--                                    </span>-->
<!--                              </div>-->
<!--                              <div style='margin-bottom:10px'>-->
<!--                                    <span style="font-size:22px">密&emsp;&emsp;码</span>-->
<!--                                    <span style='margin-left:20px'>-->
<!--                                        <el-input style='width:30%' type='text' placeholder="输入现在密码"></el-input>-->
<!--                                    </span>-->
<!--                              </div>-->
                               <div style='margin-bottom:10px'>
                                    <span style="font-size:22px">重置密码</span>
                                    <span style='margin-left:20px'>
                                        <el-input style='width:30%' type='text' v-model="newpassword" placeholder="输入新密码" ></el-input>
                                    </span>
                              </div>
                              <div style="margin-top:10px">
                                   <el-button style='margin-left:110px;' type="primary" @click="updatePwd">提交保存</el-button>
                                   <el-button style='margin-left:20px;' type="danger" @click="newpassword == ''">重置清空</el-button>
                             </div>
                    </div>
                </div>
                <div class='setImgCls' v-if='showCount==4' style="diplay:none">
                      <div>
                            <span style="font-size:22px">设置头像</span>
                            <span><el-divider style=" width: 50%;"></el-divider></span>
                            <el-upload
                                class="avatar-uploader"
                                action="http://localhost/user/file/uploadFile"
                                :show-file-list="false"
                                :on-success="handleAvatarSuccess"
                                :before-upload="beforeAvatarUpload">
                                <img v-if="loginUser.headImg" :src="`http://localhost/upload/headimg/${loginUser.headImg}`" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                            </el-upload>
                            <el-button style='margin-top:10px;width:180px' type="primary" @click="updateHeadImg">确认提交</el-button>
                      </div>
                </div>
                <div class='orderCls' v-if='showCount==5' style="diplay:none">
                    <div>
                            <span style="font-size:22px">个人订单信息</span>
                            <span><el-divider style=" width: 50%;"></el-divider></span>
                            <el-table
                                :data="myTableData"
                                height="550"

                                style="width: 100%">
                                <el-table-column
                                prop="createtime"
                                label="下单日期"
                                width="160">
                                </el-table-column>
                                <el-table-column
                                prop="ordernum"
                                label="订单号"
                                width="180"
                                :show-overflow-tooltip="true"
                                >

                                </el-table-column>
                                <el-table-column
                                prop="spotsname"
                                label="景区名称"
                                width="180">
                                </el-table-column>
                                 <el-table-column
                                prop="totalprice"
                                label="总价格"
                                width="100">
                                </el-table-column>
                              <el-table-column
                                prop="orderstatus"
                                label="订单状态"
                                width="100">
                                <template slot-scope="scope">
                                  <span style="color:red" v-if="scope.row.orderstatus===0">0-未付款</span>
                                  <span style="color:deeppink" v-if="scope.row.orderstatus===1">1-已付款</span>
                                </template>
                              </el-table-column>
                                  <el-table-column
                                prop="adultsnum"
                                label="旅游成人数"
                                width="100">
                                </el-table-column>
                                <el-table-column
                                prop="childnum"
                                label="旅客儿童数"
                                width="100">
                                </el-table-column>

                               <el-table-column
                                fixed="right"
                                label="操作"
                                width="180">
                                <template slot-scope="scope">
                                    <el-button @click="deleteOrderById(scope.row.id)" v-if="scope.row.orderstatus===0" type="danger" size="small">删除</el-button>
                                    <el-button @click="goPayOrder(scope.row.id)" v-if="scope.row.orderstatus===0" type="warning" size="small">去付款</el-button>
                                    <el-button @click="goComment(scope.row)" v-if="scope.row.orderstatus===1" type="primary" size="small">发表评论</el-button>
                                </template>
                                </el-table-column>
                            </el-table>
                    </div>
                </div>

                <div class='articleCls' v-if='showCount==6' style="diplay:none">

                       <div>
                                 <span style="font-size:22px">游记基本信息</span>
                                 <span><el-divider style=" width: 50%;"></el-divider></span>
                                <div>
                                    <span style="font-size:22px">标题名称</span>
                                    <span style='margin-left:20px'>
                                        <el-input style='width:30%' type='text' v-model='note.title' placeholder="XXXX景区风景真美"></el-input>
                                    </span>

                                    <span style="margin-left:20px;font-size:22px">出发时间</span>
                                    <span style='margin-left:20px'>
                                        <el-input style='width:30%' type='text' v-model='note.departureTime' placeholder="20180808"></el-input>
                                    </span>
                                </div>



                                 <div style="margin-top:10px">
                                    <span style="font-size:22px">出游天数</span>
                                    <span style='margin-left:20px'>
                                        <el-input style='width:30%' type='text' v-model='note.traveldays' placeholder="6天"></el-input>
                                    </span>
                                     <span style="margin-left:20px;font-size:22px">人均费用</span>
                                    <span style='margin-left:20px'>
                                        <el-input style='width:30%' type='text' v-model='note.precost' placeholder="3000RMB"></el-input>
                                    </span>
                                </div>

                                 <div style="margin-top:10px">
                                    <span style="font-size:22px">出游人数</span>
                                    <span style='margin-left:20px'>
                                        <el-input style='width:30%' type='text' v-model='note.travelnums' placeholder=""></el-input>
                                    </span>
                                     <span style="margin-left:20px;font-size:22px">入住酒店</span>
                                    <span style='margin-left:20px'>
                                        <el-input style='width:30%' type='text' v-model='note.hotelname'  placeholder="XXXX酒店"></el-input>
                                    </span>
                                </div>

                              <div style="margin-top:10px">
                                    <span style="font-size:22px;line-height:50px;">出游简介</span>
                                    <span style='margin-left:20px'>
                                       <el-input style='width:80%;margin-top:20px'  type="textarea" v-model="note.notesdesc"></el-input>
                                        <!-- <el-input style='width:30%' type='text' v-model='note.notesdesc' placeholder=""></el-input> -->
                                    </span>
                                     
                                </div>


                                 <span><el-divider style=" width: 50%;"></el-divider></span>

                                 <div style="margin-top:10px">
                                    <span style="font-size:22px">游记封面(建议1303 x 400)</span>
                                    <span style='margin-left:20px;margin-top:10px'>
                                         <el-upload
                                            class="avatar-uploader"
                                            action="http://localhost/notes/file/uploadFile"
                                            :show-file-list="false"
                                            :on-success="handleNotesSuccess"
                                            :before-upload="beforeNotesUpload">
                                            <img v-if="notesFmUrl" :src="`http://localhost/upload/notes/${this.notesFmUrl}`" class="avatar">
                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                        </el-upload>
                                    </span>
                                </div>

                                <span style="font-size:22px">游记内容信息:</span>
                                <span><el-divider style=" width: 50%;"></el-divider></span>
                                <span style='margin-left:20px;margin-top:10px'>
                                <section class="quill-container">
                                  <div class="quill-editor"
                                      :content="content"
                                      @change="onEditorChange($event)"
                                      @blur="onEditorBlur($event)"
                                      @focus="onEditorFocus($event)"
                                      @ready="onEditorReady($event)"
                                      v-quill:myQuillEditor="editorOption">
                                  </div>
                                </section>
                                </span>

                                <div style="margin-top:10px">
                                   <el-button style='float:right;margin-right:170px;' type="primary" @click="saveNotes">确认提交</el-button>
                                </div>

                            </div>

                  </div>
            </el-main>
        </el-container>
        </el-container>

    </div>
</template>
<script>

  // 工具栏配置
    const toolbarOptions = [
      ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
      ['blockquote', 'code-block'],

      [{'header': 1}, {'header': 2}],               // custom button values
      [{'list': 'ordered'}, {'list': 'bullet'}],
      [{'script': 'sub'}, {'script': 'super'}],      // superscript/subscript
      [{'indent': '-1'}, {'indent': '+1'}],          // outdent/indent
      [{'direction': 'rtl'}],                         // text direction

      [{'size': ['small', false, 'large', 'huge']}],  // custom dropdown
      [{'header': [1, 2, 3, 4, 5, 6, false]}],

      [{'color': []}, {'background': []}],          // dropdown with defaults from theme
      [{'font': []}],
      [{'align': []}],
      ['link', 'image', 'video'],
      ['clean']                                         // remove formatting button
    ]
  export default {
    data() {

      return {
          showCount:1,
          imageUrl: '',
          newpassword:'',
          notesFmUrl:'',
          loginUser:{
            id:'',
            username:'',
            password:'',
            tel:'',
            email:'',
            totalpoints:0,
            headImg:''
          },
          note:{
            title:'',
            notesdesc:'',
            departureTime:'',
            traveldays:0,
            precost:'',
            content:'',
            viewnum:0,
            creatorid:0,
            fmUrl:'',
            travelnums:0,
            hotelname:''
          },
          myTableData: [],
          content: '<p>写下您的体验,记录下来</p>',
          editorOption: {
          // some quill options
          modules: {
            toolbar: {
                        container: toolbarOptions,  // 工具栏
                        handlers: {
                            'image': function (value) {
                                if (value) {
                                    alert('自定义图片')
                                } else {
                                    this.quill.format('image', false);
                                }
                            }
                        }
                        }
          }
        }
      }
    },
    methods:{
      onSubmit(){},
      changeTag(index){
           this.showCount = index;
        },
      handleAvatarSuccess(res, file) {
        this.loginUser.headImg = res.data.fileUrl;

      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
      handleNotesSuccess(res, file) {
        this.notesFmUrl = res.data.fileUrl;
        this.note.fmUrl = res.data.fileUrl;

      },
      beforeNotesUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
      onEditorBlur(editor) {
        console.log('editor blur!', editor)
      },
      onEditorFocus(editor) {
        console.log('editor focus!', editor)
      },
      onEditorReady(editor) {
        console.log('editor ready!', editor)
      },
      onEditorChange({ editor, html, text }) {
        console.log('editor change!', editor, html, text)
        this.content = html
      },
      getMyOrderList(){
         let store= JSON.parse(localStorage.getItem('store'))
         let id = store.user.userMessage.id ;
         this.$store.dispatch('user/getMyOrderList',{id:id}).then((data) => {
            this.myTableData = data;
            console.log(data)
          }).catch((err) => {
            console.log('失败' + err)
          })
      },
      deleteOrderById(orderid){
        this.$store.dispatch('user/deleteOrderById',{id:orderid}).then((data) => {
          this.$msgbox.alert('删除成功','温馨提示:');
          this.getMyOrderList();
        }).catch((err) => {
          this.$msgbox.alert('删除失败'+err,'温馨提示:');
        })
      },
      goPayOrder(orderid){
        this.$store.dispatch('user/goPayOrder',{id:orderid}).then((data) => {
          this.$msgbox.alert('支付成功','温馨提示:');
          this.getMyOrderList();
        }).catch((err) => {
          this.$msgbox.alert('支付失败'+err,'温馨提示:');
        })
      },
      getMyInfo(){
        let store= JSON.parse(localStorage.getItem('store'))
        this.loginUser = store.user.userMessage;
      },
      editSaveUserInfo(){
        this.$store.dispatch('user/editSaveUserInfo',this.loginUser)
          .then((data) => {
          this.$msgbox.alert('保存成功','温馨提示:');
        }).catch((err) => {
          this.$msgbox.alert('保存失败'+err,'温馨提示:');
        })
      },
      updatePwd(){
        this.loginUser.password=this.newpassword;
        this.$store.dispatch('user/updatePwd',this.loginUser)
          .then((data) => {
            this.$msgbox.alert('重置成功','温馨提示:');
          }).catch((err) => {
          this.$msgbox.alert('重置失败'+err,'温馨提示:');
        })
      },
      updateHeadImg(){
        this.$store.dispatch('user/updateHeadImg',this.loginUser)
          .then((data) => {
            this.$msgbox.alert('更新成功','温馨提示:');
          }).catch((err) => {
          this.$msgbox.alert('更新失败'+err,'温馨提示:');
        })
      },
      goComment(order){
        let params = {
          id:order.spotsid,
          selectedTab:'tab4',
          orderid:order.id}
        this.$router.push({path:'/spots/detail',query:params})
      },
      saveNotes(){

        //设置用户id
        this.note.creatorid = this.$store.state.user.userMessage.id;
        //设置内容
        this.note.content = this.content;

        console.log(this.note);
        this.$store.dispatch('user/saveNotes',this.note)
          .then((data) => {
          this.$msgbox.alert('保存成功','温馨提示:');
          this.$router.push('/notes')
        }).catch((err) => {
          this.$msgbox.alert('保存失败'+err,'温馨提示:');
        })
      },
    },
    mounted() {
      this.getMyOrderList();
      this.getMyInfo();
    }
  };
</script>

<style lang="less" >
  .quill-container {
    width: 100%;

    .quill-editor {
      min-height: 200px;
      max-height: 400px;
      overflow-y: auto;
    }
  }
</style>

<style lang="less">
   .myCls{
       margin-left: @lrwith;
       margin-right: @lrwith;
   }

   .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
