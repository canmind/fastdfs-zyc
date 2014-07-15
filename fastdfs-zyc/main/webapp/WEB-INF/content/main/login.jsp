<%--
  Created by IntelliJ IDEA.
  User: Chen
  Date: 12-8-30
  Time: 下午10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>fastdfs-zyc 分布式监控管理系统</title>
    <link href="${basePath }/css/themes/css/login.css" rel="stylesheet" type="text/css" />

    <script src="${basePath }/js/speedup.js" type="text/javascript"></script>
    <script src="${basePath }/js/jquery.min.js" type="text/javascript"></script>
    <script src="${basePath }/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="${basePath }/js/jquery.validate.js" type="text/javascript"></script>
    <script src="${basePath }/js/jquery.bgiframe.js" type="text/javascript"></script>
    <script src="${basePath }/js/jsHashMap.js" type="text/javascript"></script>
    <script src="${basePath }/js/dwz.min.js" type="text/javascript"></script>
    <script src="${basePath }/js/dwz.regional.zh.js" type="text/javascript"></script>
    <script src="${basePath }/js/highcharts.js" type="text/javascript"></script>
</head>

<body>
<div id="login">
    <div id="login_header">
        <h1 class="login_logo">
            <!-- logo -->  <img src="${basePath }/images/login_logo.png" />
        </h1>
        <div class="login_headerContent">
            <div class="navList">

            </div>
            <h2 class="login_title"><img src="${basePath }/images/login_title.png" /></h2>
        </div>
    </div>
    <div id="login_content">
        <div class="loginForm">
            <form id="myform" action="loginDo.shtml" rel="loginDo"  class=""   onSubmit="return validateCallback(this,loginAjaxDone);">
                <p>
                    <label>用户名：</label>
                    <input type="text"  name="username" id="username"  style="width:60%"  required="required" title="用户名不能为空"/>
                </p>
                <p>
                    <label>密码：</label>
                    <input type="password"  name="password" id="password"  style="width:60%" required="required" title="密码不能为空"/>
                </p>
                <div class="login_bar">
                    <input class="sub" type="submit"  value=" " />
                </div>
            </form>
        </div>
        <div class="login_banner"><img src="${basePath }/images/login_banner.png" /></div>
        <div class="login_main">
            <ul class="helpList">

            </ul>
            <div class="login_inner">

            </div>
        </div>
    </div>
    <div id="login_footer">
        Copyright &copy; 2012-2013 有偿服务请联系: QQ 14776405 定制化开发，公司培训，技术支持
    </div>
</div>
<script>
var url= window.location.href;
    if(url.indexOf("login")==-1){
          window.location.reload();
    }
function loginAjaxDone(json){

     if(json.statusCode=="200"){
    window.location.href="index.shtml"
     } else{
         alert(json.message);
     }
}
</script>
</body>
</html>