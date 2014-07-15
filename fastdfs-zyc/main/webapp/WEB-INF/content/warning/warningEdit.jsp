<%--
  Created by IntelliJ IDEA.
  User: wanglt
  Date: 12-8-28
  Time: 上午9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="pageContent">
    <form method="" action="warning/saveWarning.shtml" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
        <input type="hidden" name="warningdataid" value="${id}"/>
        <div class="pageFormContent" layoutH="56">
            <p>
                <label>服务器IP：</label>

                <input type="text" id="ips" class="required" readonly="true" value="${wdIpAddr}" ${empty wdIpAddr?"suggestUrl='warning/selectIp.shtml'": "readonly='true' style='border:0'"} name="ips"     suggestFields="ips"/>
                <!--<a class="btnLook">查找带回</a>-->
            </p>
            <p>
                <label>CPU预警值：</label>
                <input name="wdCpu" class="required number" type="text" size="30" value="${wdCpu}" alt="请设定最大值"/> %
            </p>
            <p>
                <label>内存预警值：</label>
                <input name="wdMem" class="required number" type="text" size="30" value="${wdMem}" alt="请设定最大值"/> %
            </p>
            <p>
                <label>容量预警值：</label>
                <input name="wdFreeMB" class="required digits" type="text" size="30" value="${wdFreeMB}" alt="请设定最小值"/>  GB
            </p>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
                <li>
                    <div class="button"><div class="buttonContent"><button type="button" class="close" id="close">取消</button></div></div>
                </li>
            </ul>
        </div>
    </form>
</div>
<script type="text/javascript">
function navTabAjaxDone(json){
    DWZ.ajaxDone(json);
    if (json.statusCode == DWZ.statusCode.ok){
       /* if (json.navTabId){ //把指定navTab页面标记为需要“重新载入”。注意navTabId不能是当前navTab页面的
            navTab.reloadFlag(json.navTabId);
        } else { //重新载入当前navTab页面
            navTabPageBreak();
        }
        if ("closeCurrent" == json.callbackType) {
            setTimeout(function(){$.pdialog.closeCurrent();}, 100);
        } else if ("forward" == json.callbackType) {
            navTab.reload(json.forwardUrl);
        }*/
        $.pdialog.closeCurrent();
       navTab.reload();
    }
}
</script>
