<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="pagerForm" action="warning/warningValue.shtml" method="post">
    <input type="hidden" name="numPerPage" value=" ${pageInfo.numPerPage}" />
    <input type="hidden" name="pageNum"  value=" ${pageInfo.pageNum}" />

</form>
<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="warning/warningValue.shtml" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>服务器：</label>
                    <input type="text" name="wdIpAddr" value="${wdIpAddr}"/>
                </li>
            </ul>
            <div class="subBar">
                <ul>
                    <li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
<div class="panelBar">
    <ul class="toolBar">
        <li><a class="add" href="warning/warningEdit.shtml" target="dialog" mask="true" rel="warningEdit"><span>添加</span></a></li>
        <li><a title="确实要删除这些记录吗?" target="selectedTodo" postType="string"  rel="ids" href="warning/delWarning.shtml" class="delete"><span>批量删除</span></a></li>
        <li class="line">line</li>
    </ul>
</div>
<table class="table" width="1200" layoutH="138">
<thead>
<tr>
    <th><input type="checkbox" group="ids" class="checkboxCtrl"></th>
    <th>ID</th>
    <th>组名</th>
    <th>服务器IP</th>
    <th>预警CPU</th>
    <th>预警内存</th>
    <th>预警容量</th>
    <th>操作</th>
</tr>
</thead>
<tbody>
<c:forEach items="${warningValues}" var="wvs">
    <tr target="wid" rel="${wvs.id}">
       <td><input name="ids"  class="checkboxCtrl" value="${wvs.id}" type="checkbox"></td>
        <td>${wvs.id}</td>
        <td>${wvs.wdGroupName}</td>
        <td>${wvs.wdIpAddr}</td>
        <td>${wvs.wdCpu}%</td>
        <td>${wvs.wdMem}%</td>
        <td>${wvs.wdFreeMB}GB</td>
        <td>
           <a title="确定要删除吗?" target="ajaxTodo" href="warning/delWarning.shtml?ids=${wvs.id}" class="btnDel">删除</a>
            <%--<a title="删除" id="delWarning" temp="${wvs.id}">删除</a>--%>
            <a title="编辑" href="warning/warningEdit.shtml?id=${wvs.id}"  target="dialog"  rel="warningEdit" class="btnEdit">编辑</a>
        </td>
    </tr>
</c:forEach>
</tbody>
</table>
<div class="panelBar">
    <div class="pages">
        <span>显示</span>
        <select>
            <option value="20">20</option>
        </select>
        <span>条，共${pageInfo.totalCount}条</span>
    </div>

    <div class="pagination" targetType="navTab" totalCount="${pageInfo.totalCount}" numPerPage="${pageInfo.numPerPage}" pageNumShown="10" currentPage="${pageInfo.pageNum}"></div>

</div>
</div>