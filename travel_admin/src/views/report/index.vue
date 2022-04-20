<template>
  <div class="app-container">
    <div id="main" style="margin-bottom:10px;width: 600px;height:400px;"></div>

    <div id="main1" style="margin-bottom:10px;width: 600px;height:400px;"></div>
  </div>
</template>
<script>

  export default {
    name: 'reports',
    data() {
      return {
        // avgReportsList:[{"name":"IT技术课程","avgScore":89},
        //            {"name":"导演课程","avgScore":99}
        //           ,{"name":"艺术视觉课程","avgScore":96}]
        avgReportsList:[]
      }
    },
    mounted(){
      this.getAvgReports();

    },
    methods:{
      getAvgReports(){
        this.$store.dispatch('score/getAvgReports')
          .then((data) => {
            this.avgReportsList = data.courseAvgData
            this.showAvgReports();
            this.showAvgReports1()
          })
          .catch((err) => {
            console.log('失败' + err)
          })
      },
      showAvgReports(){
        // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById('main'));

        // 绘制图表
        myChart.setOption({
          title: {
            text: '课程的平均成绩排行报表'
          },
          tooltip: {},
          xAxis: {
            data: this.avgReportsList.map(item=>item.name)
          },
          yAxis: {},
          series: [{
            name: '销量',
            type: 'bar',
            data: this.avgReportsList.map(item => item.avgScore)
          }]
        });
      },
      showAvgReports1(){
        // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById('main1'));

        // 绘制图表
        myChart.setOption({
          title: {
            text: '课程的平均成绩排行报表',
            subtext: '数据来自学生的测试情况'
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          legend: {
            data: ['2020年']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
          },
          yAxis: {
            type: 'category',
            data: this.avgReportsList.map(item=>item.name)
          },
          series: [
            {
              name: '2020年',
              type: 'bar',
              data: this.avgReportsList.map(item => item.avgScore)
            }
          ]
        });
      }
    }
  }
</script>

<style scoped>

</style>
