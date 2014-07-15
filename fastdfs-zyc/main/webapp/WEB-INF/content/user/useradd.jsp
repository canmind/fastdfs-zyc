<!--
Created by IntelliJ IDEA.
User: wanglt
Date: 12-8-29
Time: 下午12:05
To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="pageContent">

    <form method="post" action="user/saveuser.shtml" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
        <input type="hidden" name="uid" value="${id}"/>
        <div class="pageFormContent nowrap" layoutH="56">
            <p>
                <label>用户名：</label>
                <input  class="required" name="name" type="text" value="${name}"/>
            </p>
            <p>
                <label>密码：</label>
                <input id="w_validation_pwd" type="password" minlength="6" maxlength="8" name="password" class="required alphanumeric" minlength="6" maxlength="20" alt="字母、数字、下划线 6-20位"/>
            </p>
            <p>
                <label>确认密码：</label>
                <input type="password" name="psword" class="required" minlength="6" maxlength="8" equalto="#w_validation_pwd"/>
            </p>
            <p>
                <label>权限：</label>
                <input type="radio" name="power" checked value="1" /> 普通
                <input type="radio" name="power"value="2"/> 高级
            </p>
            <div class="divider"></div>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
    </form>

</div>
<script type="text/javascript">
    function dialogAjaxDone(json){
        DWZ.ajaxDone(json);
        if (json.statusCode == DWZ.statusCode.ok){
            $.pdialog.closeCurrent();
            navTab.reload();
        }
    }
</script>