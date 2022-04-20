<template>
    <div style="headCls">
        <el-menu :default-active="activeIndex" :active="activeIndex"  class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1" @click='selectMenu("1")' >
            <nuxt-link to="/">
              首页
            </nuxt-link>
        </el-menu-item>
        <el-menu-item index="2"  @click='selectMenu("2")'>
             <nuxt-link to="/spots">
              目的地
            </nuxt-link>
        </el-menu-item>

          <el-menu-item index="3" @click='selectMenu("3")'>
             <nuxt-link to="/notes">
              景点游记
            </nuxt-link>
         </el-menu-item>
          <el-menu-item index="4" @click='selectMenu("4")'>
             <nuxt-link to="/qa">
              社区问答
            </nuxt-link>
        </el-menu-item>
         <!-- <el-menu-item index="5" @click='selectMenu("5")'>
             <nuxt-link to="/abouts">
              商务合作
            </nuxt-link>
        </el-menu-item> -->


         <el-menu-item index="6" style="float:right;overflow:hidden" v-if="!$store.state.user.userMessage.username">
             <nuxt-link to="/user/login">
              登录/注册
            </nuxt-link>

        </el-menu-item>

        <el-menu-item v-if="$store.state.user.userMessage.username" index="7" style="float:right;line-height:50px;overflow:hidden">

            <el-dropdown>
              <span class="el-dropdown-link">
                <img :src="`http://localhost/upload/headimg/${$store.state.user.userMessage.headImg}`" style="width:46px;height:46px;border-radius:50%;" alt="">
                <span>{{$store.state.user.userMessage.username}}</span>
                <i class="el-icon-caret-bottom el-icon--right" />
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <div >
                    <nuxt-link to="/user/my">
                    个人中心
                    </nuxt-link>
                  </div>
                </el-dropdown-item>
                <el-dropdown-item>
                  <div @click="handleLogout">
                    退出
                  </div>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>

        </el-menu-item>



        </el-menu>
         <div class="line"></div>

    </div>
</template>

<script>

export default {
     data() {

      return {
        activeIndex: '1',
        activeIndex2: '1',
        loginUserName: null
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      selectMenu(index){
        this.activeIndex=index
        if(index == 1){
          this.$store.commit('selectIndex')
        }else if(index == 2){
          this.$store.commit('selectSpots')
        }else if(index == 3){
          this.$store.commit('selectJpyj')
        }
      },
      getActivedMenu(){
        this.activeIndex=this.$store.state.show;
      },
      handleLogout(){
        this.$store.commit('user/clearUserMessage')
      }
    },
      watch: {
          // 监测store.state
          '$store.state.show': 'getActivedMenu'
      },
     mounted() {

     }
}
</script>

<style lang="less">
    a{
      text-decoration: none;
      color: #666
    }
    .el-menu-demo{
        margin-left: 160px;
        margin-right: 160px;


    }
    .el-menu-demo{
        min-width: 1024px !important;


    }
    .el-menu-item{
      font-size:18px !important;
    }

    .headCls{

      min-width: 0;
      margin-left: 0;
      margin-right: 0;
      padding-left: 0;
      padding-right: 0;

    }
</style>
