<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

    <div layoutH="0">
<table>
    <tr>
        <td>
            <div class="structureServerInfo">
                <input type="hidden" value="${serverInfo.freeMB}" name="structureFreeMB"/> <input
                    type="hidden" value="${serverInfo.totalMB}" name="structureTotalMB"/> <input
                    type="hidden" value="${serverInfo.ipAddr}" name="structureIp"/>

            </div>
        </td>
        <td>
            <div class="structureServerInfoForperformance">
                <input type="hidden" value="${serverInfo.ipAddr}" name="structureIpForperformance"/>
            </div>
        </td>

    </tr>
    <tr>

        <td colspan="2">
            <div class="structureServerInfoFornetTraffic">
                <input type="hidden" value="${serverInfo.curStatus}" name="structureStatus"/>
                <input type="hidden" value="${serverInfo.ipAddr}" name="structureIpFornetTrafic"/>
                <input type="hidden" value="${start}" name="structure-net-traffic-start-time"/>
                <input type="hidden" value="${end}" name="structure-net-traffic-end-time"></div>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <div class="structureServerInfoForStorage">
                <input type="hidden" value="${serverInfo.ipAddr}" name="structureIpForFile"/>
                <input type="hidden" value="${serverInfo.curStatus}" name="structureFileStatus"/>
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <div class="structureServerInfoForFileCount">
                <input type="hidden" value="${serverInfo.ipAddr}" name="structureIpForFileCount"/>
                <input type="hidden" value="${serverInfo.curStatus}" name="structureFileStatusCount"/>
            </div>
        </td>
    </tr>
</table>
    </div>


<script type="text/javascript">
function structureCreateChart(id, totalMB, freeMB, name, ip) {
    chart = new Highcharts.Chart({
        chart:{
            renderTo:id,
            plotBackgroundColor:null,
            plotBorderWidth:null,
            plotShadow:false,
            height:200,
            width:280
        },
        title:{
            text:name,
            style:{
                fontSize:'13px'
            }
        },
        tooltip:{
            formatter:function () {
                return '<b>' + this.point.name + '</b> ' + this.percentage.toFixed(2) + ' %';
            }
        },
        plotOptions:{
            pie:{
                allowPointSelect:true,
                cursor:'pointer',
                dataLabels:{
                    enabled:true,
                    color:'#000000',
                    connectorColor:'#000000',
                    formatter:function () {
                        return this.percentage.toFixed(2) + ' %';
                    }
                }
            }
        },
        series:[
            {
                type:'pie',
                name:'Browser share',
                data:[
                    {
                        name:'已使用:' + ((totalMB - freeMB) / 1024).toFixed(2) + "G",
                        y:1 - freeMB / totalMB,
                        sliced:true,
                        selected:true,
                        color:'red'
                    },
                    {
                        name:'未使用:' + ((freeMB) / 1024).toFixed(2) + "G",
                        y:freeMB / totalMB,
                        color:'green'
                    }
                ]
            }
        ],
        credits:{
            href:'javascript:void(0)',
            text:'vivame.cn'
        }
    });
}

function structureFun() {
    var toRender= $("div.structureServerInfo");
    var freeMB =toRender.find('input[name=structureFreeMB]').val();
    var totalMB =  toRender.find('input[name=structureTotalMB]').val();
    var ip =  toRender.find('input[name=structureIp]').val()
    var name = 'IP:' + ip + ' 容量:' + (totalMB / 1024).toFixed(2) + 'G';
    var id =toRender[0];
    structureCreateChart(id, totalMB, freeMB, name, ip);
}
structureFun();
function structureDrawNetTrafficChart(obj, title, data) {
    new Highcharts.Chart({
        chart:{
            renderTo:obj,
            height:190,
            width:800
        },
        title:{
            text:title,
            style:{
                fontSize:'13px'
            }
        },
        xAxis:{
            type:'datetime',
            minPadding:0.05,
            maxPadding:0.05
        },
        tooltip:{
            xDateFormat:'%Y-%m-%d %H:%M',
            shared:true
        },
        yAxis:{
            title:{
                text:'字节数 (byte)'
            }
        },
        legend:{
            enabled:false
        },
        series:data
    });
}
function structureNetTraffic() {
    var toRender= $("div.structureServerInfoFornetTraffic");
    var ip = toRender.find('input[name=structureIpFornetTrafic]').val();
    var title = ip + " " + toRender.find('input[name=structureStatus]').val();
    var id =toRender[0];
    var params = {
        ip:ip,
        start:toRender.find('input[name=structure-net-traffic-start-time]').val(),
        end: toRender.find('input[name=structure-net-traffic-end-time ]').val()
    };
    $.getJSON('monitor/getNetTrafficLine.shtml', params, function (data) {
        structureDrawNetTrafficChart(id, title, data);
    });
}
structureNetTraffic();
function createPerformanceChartByIpydm(id, data) {
    new Highcharts.Chart({
        chart:{
            renderTo:id,
            height:240,
            width:500,
            type:'line'
        },
        title:{
            text:"",
            style:{
                fontSize:'13px'
            }
        },
        xAxis:{
            type:'datetime',
            dateTimeLabelFormats:{
                day:'%e of %b'
            },
            minPadding:0.05,
            maxPadding:0.05
        },
        yAxis:{
            title:{
                text:'MEM使用率 (%)'
            },
            plotLines:[
                {
                    value:0,
                    width:10,
                    color:'#808080'
                }
            ]
        },
        tooltip:{  // 表示为 鼠标放在报表图中数据点上显示的数据信息
            formatter:function () {
                return '<b>' + '日期:' + '</b>'
                        + Highcharts.dateFormat('%Y-%m-%d %H:%M', this.x) + '<br/>'
                        + this.series.name + '</b>' + ': ' + this.y + '%';
            }
        },
        legend:{
            layout:'vertical',
            align:'left',
            floating:true,
            verticalAlign:'top',
            x:100,
            y:4,
            borderWidth:1
        },
        series:data
    });

}
function structureForMem() {
    var toRender = $("div.structureServerInfoForperformance");
    var ip = toRender.find('input[name=structureIpForperformance]').val();
    var id = toRender[0];
    $.getJSON('structure/getForperformanceByIp.shtml', {ip:ip}, function (data) {
        createPerformanceChartByIpydm(id, data);
    });
}
structureForMem();
function createStructureForStorage(id, name, data) {
    new Highcharts.Chart({
        chart:{
            renderTo:id,
            height:190,
            width:800
        },
        title:{
            text:name,
            style:{
                fontSize:'13px'
            }
        },
        xAxis:{
            type:'datetime',
            minPadding:0.05,
            maxPadding:0.05
        },
        tooltip:{
            xDateFormat:'%Y-%m-%d %H:%M',
            shared:true
        },
        yAxis:{
            title:{
                text:'剩余容量，单位（MB）'
            }
        },
        legend:{ enabled:false},
        series:data
    });
}
function structureForStorage() {
    var toRender =  $("div.structureServerInfoForStorage");
    var ip = toRender.find('input[name=structureIpForFile]').val();
    var id =toRender[0];
    var name = ip + " " +  toRender.find('input[name=structureFileStatus]').val();
    $.getJSON('monitor/capactityStorage.shtml?ip=' + ip, function (data) {
        createStructureForStorage(id, name, data);
    })

}
structureForStorage();
function createStructureForFileCount(id,name,data){
    new Highcharts.Chart({
        chart: {
            renderTo: id,
            height:190,
            width: 800
        },
        title: {
            text: name,
            style: {
                fontSize:'13px'
            }
        },
        xAxis: {
            type:'datetime',
            minPadding: 0.05,
            maxPadding: 0.05
        },
        tooltip: {
            xDateFormat: '%Y-%m-%d %H:%M',
            shared: true
        },
        yAxis: {
            title: {
                text: '数量（个）'
            }
        },
        legend:{enabled:false},
        series:data
    });
}
function structureForFileCount() {
    var toRender =$("div.structureServerInfoForFileCount");
    var ip =  toRender.find('input[name=structureIpForFileCount]').val();
    var id = toRender[0];
    var name = ip + " " +toRender.find('input[name=structureFileStatusCount]').val();
    $.getJSON('monitor/fileCountStorage.shtml?ip='+ip,function(data){
          createStructureForFileCount(id,name,data);
    });
}

structureForFileCount();
</script>