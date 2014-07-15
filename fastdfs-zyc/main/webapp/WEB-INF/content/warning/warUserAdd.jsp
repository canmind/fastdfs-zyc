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

    <form method="post" action="warning/saveWarUser.shtml" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
        <input type="hidden" name="wuid" value="${id}"/>
        <div class="pageFormContent nowrap" layoutH="56">
            <p>
                <label>用户名：</label>
                <input n class="required" name="wuname" type="text" value="${name}"  alt="请输入用户名"/>
            </p>
            <p>
                <label>电话：</label>
                <input  name="wuphone" class="required phone" type="text"  value="${phone}" alt="请输入您的电话"/>
            </p>
            <p>
                <label>邮箱：</label>
                <input  name="wuemail"  class="required email" type="text" value="${email}" alt="请输入您的电子邮件"/>
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
         /*  if (json.navTabId){
               navTab.reload(json.forwardUrl, {}, json.navTabId);
           }
           $.pdialog.closeCurrent();*/
           $.pdialog.closeCurrent();
           navTab.reload();
       }  else{
           alertMsg.error("电话号码长于11位")  ;
       }
   }
</script>

