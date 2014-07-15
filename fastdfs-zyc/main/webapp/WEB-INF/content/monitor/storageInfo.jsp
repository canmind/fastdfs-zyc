<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent pageFormContent">
				<li><label style="width: 63px">开始时间：</label> <input type="text"
					id="storage-start-time" class="date" format="yyyy-MM-dd HH:mm"
					value="${start}" readonly="true" /> <a class="inputDateButton"
					href="javascript:;">选择</a>
				</li>
				<li><label style="width: 63px">结束时间：</label> <input type="text"
					id="storage-end-time" class="date" format="yyyy-MM-dd HH:mm"
					value="${end}" readonly="true" /> <a class="inputDateButton"
					href="javascript:;">选择</a>
				</li>
			</ul>

			<div class="subBar">
				<ul>
					<li style="margin-right: 20px"><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="a()">检索</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="panel">
		<h1>FASTDFS组:${storage.groupName} 容量线型图</h1>
		<div class="capactityStorage"></div>
		<input type="hidden" value="${storage.ipAddr}" class="sip" /> <input
			type="hidden" value="${storage.curStatus}" class="sstatus" />
	</div>
	<div class="panel">
		<h1>FASTDFS组:${storage.groupName} 文件数量线型图</h1>
		<div class="fileCountStorage"></div>
		<div class="pageContent sortDrag" selector="h1" layoutH="42">
			<div class="panel"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
function drawCapactityStorage(obj,title,data){
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
                text: '剩余容量，单位（MB）'
            }
        },
        legend:{ align: 'right'},
        series:data,
        credits:{
                href:'javascript:void(0)',
                text:'vivame.cn'
            }
    });
}
	function a(){
	var toRender=$(".capactityStorage");
    var ip = $('.sip').val();
    var title = ip +" "+$('.sstatus').val();
    var startTime1 = $('#storage-start-time').val() ; 
    var endTime1 = $('#storage-end-time').val();
    $.getJSON("monitor/capactityStorage.shtml?ip="+ip+"&startTime="+startTime1+"&endTime="+endTime1,function(data){
        drawCapactityStorage(toRender[0],title,data);
	});
    b(startTime1,endTime1);
	}
function drawFileCountStorage(obj,title,data){
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
                text: '数量（个）'
            }
        },
        legend:{ align: 'right'},
        series:data,
         credits:{
                href:'javascript:void(0)',
                text:'vivame.cn'
            }
    });
}
	function b(startTime1,endTime1){
	var toRender1=$(".fileCountStorage");
    var fip = $('.sip').val();
    var ftitle = fip +" "+$('.sstatus').val();
    $.getJSON("monitor/fileCountStorage.shtml?ip="+fip+"&startTime="+startTime1+"&endTime="+endTime1,function(data){
        drawFileCountStorage(toRender1[0],ftitle,data);
    });
    }
	a();
</script>
</html>