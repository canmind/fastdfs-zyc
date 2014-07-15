<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="${basePath }" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>fastdfs-zyc 分布式监控管理系统</title>
    <link href="css/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="css/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="css/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
    <!--[if IE]>
    <link href="css/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
    <![endif]-->
    <script src="js/speedup.js" type="text/javascript"></script>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/jquery.cookie.js" type="text/javascript"></script>
    <script src="js/jquery.validate.js" type="text/javascript"></script>
    <script src="js/jquery.bgiframe.js" type="text/javascript"></script>
	<script src="js/jsHashMap.js" type="text/javascript"></script>
    <script src="js/dwz.min.js" type="text/javascript"></script>
    <script src="js/dwz.regional.zh.js" type="text/javascript"></script>
    <script src="js/highcharts.js" type="text/javascript"></script>
    <script src="js/highcharts-more.js" type="text/javascript"></script>
    <script type="text/javascript">
        Highcharts.setOptions({
            global:{
                useUTC: false
            },
            credits:{
                href:'javascript:void(0)',
                text: "Chen.H"
            }
        });
        $(function(){
            DWZ.init("js/dwz.frag.xml", {
                loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
                statusCode:{ok:200, error:300, timeout:301}, //【可选】
                pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
                debug:false,	// 调试模式 【true|false】
                callback:function(){
                    initEnv();
                    $("#themeList").theme({themeBase:"css/themes"}); // themeBase 相对于index页面的主题base路径
                }
            });
        });

        $(function () {
            /**
             * Get the current time
             */
            function getNow () {
                var now = new Date();

                return {
                    hours: now.getHours() + now.getMinutes() / 60,
                    minutes: now.getMinutes() * 12 / 60 + now.getSeconds() * 12 / 3600,
                    seconds: now.getSeconds() * 12 / 60
                };
            };

            var now = getNow();

            // Create the chart
            var chart = new Highcharts.Chart({

                        chart: {
                            renderTo:'containterYdm11',
                            type: 'gauge',
                            plotBackgroundColor: null,
                            plotBackgroundImage: null,
                            plotBorderWidth: 0,
                            plotShadow: false,
                            height: 400
                        },

                        credits: {
                            enabled: false
                        },

                        title: {
                            text: '现在是北京时间'
                        },



                        yAxis: {
                            labels: {
                                distance: -20
                            },
                            min: 0,
                            max: 12,
                            lineWidth: 0,
                            showFirstLabel: false,

                            minorTickInterval: 'auto',
                            minorTickWidth: 1,
                            minorTickLength: 5,
                            minorTickPosition: 'inside',
                            minorGridLineWidth: 0,
                            minorTickColor: '#666',

                            tickInterval: 1,
                            tickWidth: 2,
                            tickPosition: 'inside',
                            tickLength: 10,
                            tickColor: '#666',
                            title: {
                                text: '',
                                style: {
                                    color: '#BBB',
                                    fontWeight: 'normal',
                                    fontSize: '8px',
                                    lineHeight: '10px'
                                },
                                y: 10
                            }
                        },
                        tooltip:{
                            enabled:false
                        },
                        series: [{
                            data: [{
                                id: 'hour',
                                y: now.hours,
                                dial: {
                                    radius: '60%',
                                    baseWidth: 4,
                                    baseLength: '95%',
                                    rearLength: 0
                                }
                            }, {
                                id: 'minute',
                                y: now.minutes,
                                dial: {
                                    baseLength: '95%',
                                    rearLength: 0
                                }
                            }, {
                                id: 'second',
                                y: now.seconds,
                                dial: {
                                    radius: '100%',
                                    baseWidth: 1,
                                    rearLength: '20%'
                                }
                            }],
                            animation: false,
                            dataLabels: {
                                enabled: false
                            }
                        }]
                    },

                    // Move
                    function (chart) {
                        setInterval(function () {
                            var hour = chart.get('hour'),
                                    minute = chart.get('minute'),
                                    second = chart.get('second'),
                                    now = getNow(),
                            // run animation unless we're wrapping around from 59 to 0
                                    animation = now.seconds == 0 ?
                                            false :
                                    {
                                        easing: 'easeOutElastic'
                                    };

                            hour.update(now.hours, true, animation);
                            minute.update(now.minutes, true, animation);
                            second.update(now.seconds, true, animation);

                        }, 1000);

                    });
        });

        // Extend jQuery with some easing (copied from jQuery UI)
        $.extend($.easing, {
            easeOutElastic: function (x, t, b, c, d) {
                var s=1.70158;var p=0;var a=c;
                if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
                if (a < Math.abs(c)) { a=c; var s=p/4; }
                else var s = p/(2*Math.PI) * Math.asin (c/a);
                return a*Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2*Math.PI)/p ) + c + b;
            }
        });

    </script>
</head>
<body scroll="no">
<div id="layout">
    <div id="header">
        <div class="headerNav">
            <a class="logo" href="javascript:void(0)" style="height: 50px;margin-left:10px;margin-top:10px;">标志</a>
            <ul class="nav">
                <li><a href="javascript:">${username}  ,   您好！</a></li>
                <%--<li id="switchEnvBox"><a href="javascript:">（<span>北京</span>）切换城市</a>
                    <ul>
                        <li><a href="sidebar_1.html">北京</a></li>
                        <li><a href="sidebar_2.html">上海</a></li>
                        <li><a href="sidebar_2.html">南京</a></li>
                        <li><a href="sidebar_2.html">深圳</a></li>
                        <li><a href="sidebar_2.html">广州</a></li>
                        <li><a href="sidebar_2.html">天津</a></li>
                        <li><a href="sidebar_2.html">杭州</a></li>
                    </ul>
                </li>
                <li><a href="https://me.alipay.com/dwzteam" target="_blank">捐赠</a></li>
                <li><a href="changepwd.html" target="dialog" width="600">设置</a></li>
                <li><a href="http://www.cnblogs.com/dwzjs" target="_blank">博客</a></li>
                <li><a href="http://weibo.com/dwzui" target="_blank">微博</a></li>
                <li><a href="http://bbs.dwzjs.com" target="_blank">论坛</a></li>--%>
                <li><a href="main/loginout.shtml">退出</a></li>
            </ul>

        </div>

        <!-- navMenu -->
    </div>

    <div id="leftside">
        <div id="sidebar_s">
            <div class="collapse">
                <div class="toggleCollapse"><div></div></div>
            </div>
        </div>
        <div id="sidebar">
            <div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
            <div class="accordion" fillSpace="sidebar">
                <div class="accordionHeader">
                    <h2><span>Folder</span>系统监控</h2>
                </div>
                <div class="accordionContent">
                    <ul class="tree">
                        <li><a href="monitor/performance.shtml" target="navTab"  rel="performance">性能监控</a></li>
                        <li><a href="monitor/netTraffic.shtml" target="navTab"  rel="netTraffic">流量监控</a></li>
                        <li><a href="monitor/capacity.shtml" target="navTab"  rel="capacity">容量监控</a></li>

                    </ul>
                </div>
                <div class="accordionHeader">
                    <h2><span>Folder</span>预警设置</h2>
                </div>
                <div class="accordionContent">
                    <ul class="tree">
                        <li><a href="warning/warningValue.shtml" target="navTab" rel="warningValue">预警设置</a></li>
                        <li><a href="warning/warUserList.shtml" target="navTab" rel="warUserList">通知管理</a></li>
                    </ul>
                </div>
                <div class="accordionHeader">
                    <h2><span>Folder</span>预测系统</h2>
                </div>
                <div class="accordionContent">
                    <ul class="tree">
                        <li><a href="forecast/bottleneck.shtml" target="navTab" rel="bottleneck">瓶颈预测</a></li>
                        <li><a href="forecast/dilatation.shtml" target="navTab" rel="dilatation">扩容预测</a></li>
                    </ul>
                </div>
                <div class="accordionHeader">
                    <h2><span>Folder</span>拓扑结构</h2>
                </div>
                <div class="accordionContent">
                    <ul class="tree">
                        <li><a href="structure/netStructure.shtml" target="navTab" rel="netStructure">网络结构图</a></li>
                        <li><a href="dataStructure/fileData.shtml" target="navTab" rel="fileData">数据结构图</a></li>
                        <li><a href="testModule/accessFile.shtml" target="navTab" rel="accessFile">访问拓扑图</a></li>
                    </ul>
                </div>

                <div class="accordionHeader"  STYLE="${sessionScope.userpower!='2'?'display:none':''}">
                    <h2><span>Folder</span>权限管理</h2>
                </div>
                <div class="accordionContent" STYLE="${sessionScope.userpower!='2'?'display:none':''}">
                    <ul class="tree">
                        <li><a href="user/userlist.shtml" target="navTab" rel="userlist">用户列表</a></li>
                    </ul>
                </div>
                <div class="accordionHeader">
                    <h2><span>Folder</span>测试模块</h2>
                </div>
                <div class="accordionContent">
                    <ul class="tree">
                        <li><a href="testModule/testDownLoad.shtml?pageNum=1&&pageSize=20" target="navTab" rel="testDownLoad">下载测试</a></li>
                    </ul>
                    <%--<ul class="tree">--%>
                        <%--<li><a href="monitor/testUpload.shtml" target="navTab" rel="testFileUpload">上传测试</a></li>--%>
                    <%--</ul>   ˜--%>
                </div>
            </div>
        </div>
    </div>
    <div id="container">
        <div id="navTab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
                    <ul class="navTab-tab">
                        <li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
                <div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
                <div class="tabsMore">more</div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">我的主页</a></li>
            </ul>
            <div class="navTab-panel tabsPageContent layoutBox">
                <div class="page unitBox">
                    <div class="accountInfo">
                        <p><span>欢迎使用fastdfs-zyc监控平台</span></p>

                    </div>
                    <div id="containterYdm11" style="margin-left: 300px;margin-top:100px;width: 400px;height: 600px;"></div>
                </div>
            </div>

        </div>
    </div>

</div>

<div id="footer">Copyright &copy; 2012-2013 有偿服务请联系: QQ 14776405 定制化开发，公司培训，技术支持</div>

</body>
</html>