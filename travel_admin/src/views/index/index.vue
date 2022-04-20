<template>
  <div class="app-container" >
    <div id="main1" style="margin-bottom:10px;width: 1000px;height:600px;"></div>
  </div>
</template>

<script>

  export default {
    name: 'reports',
    data() {
      return {
        avgReportsList:[{"name":"往年旅游人数评分","avgScore":89}],
        avgReportsList1:[{"name":"往年旅游人数评分","avgScore":92}],
        avgReportsList2:[{"name":"往年旅游人数评分","avgScore":99}]
      }
    },
    mounted(){
      this.getAvgReports();

    },
    methods:{
      beforeCreate () {
        document.querySelector('body').setAttribute('style','width:100%;height:100%;background-image:url("../../../static/bg/bg3.jpg")'
        );
      },
      getAvgReports(){
            this.showAvgReports1();
      },
      
      showAvgReports1(){
        // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById('main1'));

        // 绘制图表
        myChart.setOption({
          title: {
            text: '往年旅游人数评分',
            textStyle:{
              color:'black'
            }
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          legend: {
            data: ['2020年','2019年','2018年']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01],
            axisLine:{
              lineStyle:{
                color:'black'
              }
            }
          },
          yAxis: {
            type: 'category',
            axisLine:{
              lineStyle:{
                color:'black'
              }
            },
            data: this.avgReportsList.map(item=>item.name)
          },
          series: [
            {
              name: '2020年',
              type: 'bar',
              data: this.avgReportsList2.map(item => item.avgScore)
            },
            {
              name: '2019年',
              type: 'bar',
              data: this.avgReportsList1.map(item => item.avgScore)
            },
            {
              name: '2018年',
              type: 'bar',
              data: this.avgReportsList.map(item => item.avgScore)
            }
          ]
        });
      },
      
      
    }
  }
</script>

<style scoped>
 >>> .el-main{
    background-color: #1439391c !important;
  }
</style>
