<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div layoutH="0">
    <c:forEach items="${groups}" var="group" varStatus="status">
        <div class="panel" >
            <h1>FASTDFS组:${group.groupName}</h1>
            <c:forEach items="${group.storageList}" var="storageFile">
                <div class="storageForColumn">
                    <input type="hidden" value="${storageFile.ipAddr}" name="ipStorageForTen">
                    <input type="hidden" value="${storageFile.totalDownloadCount}" name="totalDownloadCountByIp"/>
                    <table>
                        <tr>
                            <td>
                                <div class="chart">
                                </div>
                            </td>
                            <td>
                                <div class="pieCharts"></div>
                            </td>
                        </tr>
                    </table>


                </div>
            </c:forEach>
        </div>
    </c:forEach>
</div>
<script type="text/javascript">
    function drawColumnForTenFile(id, ip, x,y, total) {
        new Highcharts.Chart({
            chart:{
                renderTo:id,
                height:280,
                width:880,
                type:'column'
            },
            title:{
                text:ip + '服务器被访问最多的前十个文件',

                style:{
                    fontSize:'13px'
                }
            },
            subtitle:{
                text:"本服务器一共被访问文件:" + total,
                style:{
                    fontSize:'12px'
                }
            },
            plotOptions:{
                column:{

                    dataLabels:{
                        color:'red',
                        enabled:true,
                        style:{
                            fontWeight:'bold'

                        },
                        formatter:function () {
                            return this.y + '次';
                        }
                    }

                }
            },
            xAxis:{

                 categories:x,
                labels: {
              rotation: -15,
              align: 'right',
              style: {
                   font: 'normal 13px',
                   fontWeight:'bold',
                   color: 'green'
              }
          }


                },
            yAxis:{
                title:false,
                plotLines:[
                    {

                        width:10,
                        color:'#808080'

                    }
                ]
            },
            legend:{
                enabled:false
            },
              tooltip:{
                    formatter:function () {
                        return '<b>' + this.point.name + '</b> ' ;
                    }
                },
//            legend : {
//                layout : 'vertical',
//                align : 'left',
//                floating: true,
//                verticalAlign : 'top',
//                x : 100,
//                y : 4,
//                borderWidth : 1
//            },
            series:y
        });
    }
    function drawPieForAllFile(id,data,ip){
        new Highcharts.Chart({
            chart:{
                renderTo:id,
                height:280,
                width:300,
                type:'pie'
            },
            title:{
                text:ip + '文件访问前10名分布图',

                style:{
                    fontSize:'13px'
                }
            },
            xAxis:{



            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: false
                }
            },
            yAxis:{

                plotLines:[
                    {

                        width:10,
                        color:'#808080'

                    }
                ]
            },

            series:data
        });
    }
    $("div.storageForColumn").each(function () {
        var toRender = $(this);
        var ip = toRender.find('input[name=ipStorageForTen]').val();

        $.getJSON('testModule/tenFileDownLoad.shtml', {ip:ip}, function (data) {
            var x=data.x;
            var y=data.y;
            var  total=data.sum;
            drawColumnForTenFile(toRender.find('.chart')[0], ip, x,y, total);
        });
        $.getJSON('testModule/allFilePie.shtml',{ip:ip},function(data){
            drawPieForAllFile(toRender.find('.pieCharts')[0],data,ip);
        })
    });
</script>