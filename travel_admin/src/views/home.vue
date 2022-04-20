<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <!-- 后台侧边栏-->
    <el-aside id="asideId" width="250px" class="asideCls">
      <div >
             <!--<img  class="sidebarCls"  :src="logoImg"/>-->
            <h1 class="sidebar-title">旅游网后台管理系统</h1>
      </div>

      <el-menu :router="true" class="elMenuCls" id="leftMenuId" v-show="!isCollapse">
        <el-submenu :index="route.path"  v-for="route in newRoutes" :key="route.path" :item="route"  >
          <template slot="title">

              <i :class="route.meta && route.meta.icon"></i>{{route.name}}

          </template>
            <el-menu-item class="itemMenuCls"
                           v-for="(child,index) in route.children"
                           :key="child.path"
                           :index="child.path"
                           :route="{name:child.name}" >
              <i :class="child.meta && child.meta.icon"></i>
               {{child.name}}
            </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <!-- 后台头部-->
      <el-header style="text-align: right; font-size: 12px">
        <el-radio-group style="float:left;margin-left:0px;margin-top:4px" v-model="isCollapse" >
          <i v-show="!isCollapse" class="el-icon-s-fold expandBtn" @click="closeSideBar"></i>
          <i v-show="isCollapse" class="el-icon-s-fold expandBtn" @click="openSideBar"></i>
        </el-radio-group>

        <el-dropdown style="height: 50px">
          <!--<img id="showAvatarId" :src="getImage(avatar)"  class="user-avatar">-->
          <img id="showAvatarId"
                     :src="getImage()"
               class="avatarCls"/>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>

      <el-main>
        <!--路由渲染-->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<style lang="scss">
  @import '../styles/home.scss';

  .el-radio-group {
    font-size: 20px;
    line-height: 60px;
    margin-top: 20px;
    margin-left: 10px;

  }

  .sidebar-title{
     color:white;
     line-height: 60px;
  }



</style>

<script>

  import { getToken } from '@/common/util/tokenUtil'
  import {  resetRouter  } from '../router'
  export default {
    data() {
      return {
        logoImg:'../../static/logo/mzlogo1.png',
        isActive:true,
        newRoutes:[],
        curTagsIndex:1,
        avatar:'',
        isCollapse:false
      }
    },

    methods:{
      async logout() {
        resetRouter ()
        await this.$store.dispatch('sysuser/logout')
        sessionStorage.removeItem("isHasLoadMenu")
        this.$router.push(`/login?redirect=${this.$route.fullPath}`)
      },
      //隐藏侧边栏
      closeSideBar(){
         this.isCollapse = true
         let sideBarWidth =  document.getElementById("leftMenuId")
         if(sideBarWidth.offsetWidth === 250 ){
            sideBarWidth.style.width='0px';
         }
        document.getElementById("asideId").style.display = 'none';
      },
      //展示侧边栏
      openSideBar(){
        this.isCollapse = false
        let sideBarWidth =  document.getElementById("leftMenuId")
        if(sideBarWidth.offsetWidth === 0 ){
           sideBarWidth.style.width='250px';
        }
        document.getElementById("asideId").style.display = 'block';
      },
      getImage(){
        console.log('头像:'+this.avatar)
        if(this.avatar){
            this.$axios({
              method:'post',
              url:"http://localhost/user/showimage",
              headers:{
                authorization:'Bearer ' + getToken()
              },
              data:{image_name:this.avatar},
              responseType: 'blob'
            }).then(res=>{
              const blob = new Blob([res.data])
              let url = window.URL.createObjectURL(blob,{type: 'image/jpeg'})
              let imgEle = document.getElementById("showAvatarId")
              imgEle.src = url
              imgEle.onload = function () {
                window.URL.revokeObjectURL(url)
              }
            }).catch((err)=>{
              console.log('失败' + err)
            })

        }
      }
    },
    beforeCreate () {
      document.querySelector('body').setAttribute('style','position:fixed;margin:0px;padding:0px;overflow:-Scroll;overflow-y:hidden;width: 100%;height: 100%;'
       );
    },
    mounted(){
      const route =  JSON.parse(sessionStorage.getItem('newRouters'));
      const avatar =  sessionStorage.getItem('avatar');
      this.newRoutes = route.filter(item => !item.hidden)
      this.avatar = avatar
    }
  };
</script>
