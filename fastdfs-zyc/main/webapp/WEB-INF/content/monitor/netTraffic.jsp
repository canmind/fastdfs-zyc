<%--
  Created by IntelliJ IDEA.
  User: devuser
  Date: 12-8-22
  Time: 下午9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="pageHeader">
        <div class="searchBar">
            <ul class="searchContent pageFormContent">
                <li>
                    <label style="width: 63px">开始时间：</label>
                    <input type="text" id="net-traffic-start-time" class="date" format="yyyy-MM-dd HH:mm" value="${start}" readonly="true"/>
                    <a class="inputDateButton" href="javascript:;">选择</a>
                </li>
                <li>
                    <label style="width: 63px">结束时间：</label>
                    <input type="text" id="net-traffic-end-time" class="date" format="yyyy-MM-dd HH:mm" value="${end}" readonly="true"/>
                    <a class="inputDateButton" href="javascript:;">选择</a>
                </li>

                    <li style="margin-right: 20px"><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="searchNetTraffic()">检索</button></div></div></li>
            </span>
            </ul>


        </div>
</div>
<div class="pageContent sortDrag" selector="h1" layoutH="60">

<c:forEach items="${groupInfo}" var="group">
    <div class="panel" defH="${fn:length(group.storageList)*200}">
        <h1>FASTDFS组:${group.groupName}</h1>
        <div>
            <c:forEach items="${group.storageList}" var="storage" varStatus="status">

                <div class="netTrafficStorage" style="margin-bottom: 5px">
                    <div class="chart"></div>
                    <div>
                        <input type="hidden" value="${storage.ipAddr}" name="ip" />
                        <input type="hidden" value="${storage.curStatus}" name="status" />
                    </div>
                </div>


            </c:forEach>
        </div>
    </div>
    </c:forEach>
</div>
<script type="text/javascript">
function drawNetTrafficChart(obj,title,data){
    new Highcharts.Chart({
        chart: {
            renderTo: obj,
            height:190,
            width: 800
        },
        title: {
            text: title,
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
                text: 'MB'
            }
        },
        legend:{
            align: 'right',
            layout:'vertical'
        },
        series:data
    });
}
function searchNetTraffic(){
    $(".netTrafficStorage").each(function(){
        var toRender = $(this);
        var ip = toRender.find('input[name=ip]').val();
        var title = ip +" "+toRender.find('input[name=status]').val();
        var params = {
            ip:ip,
            start:$('#net-traffic-start-time').val(),
            end:$('#net-traffic-end-time').val()
        };
        $.getJSON('monitor/getNetTrafficLine.shtml',params,function(data){
            drawNetTrafficChart(toRender.find('.chart')[0],title,data);
        });
    });
};
searchNetTraffic();

</script>
