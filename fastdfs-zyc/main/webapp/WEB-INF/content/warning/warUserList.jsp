<%--
  Created by IntelliJ IDEA.
  User: wanglt
  Date: 12-8-29
  Time: 下午12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="pagerForm" action="warning/warUserList.shtml" method="post">
    <input type="hidden" name="numPerPage" value=" ${pageInfo.numPerPage}" />
    <input type="hidden" name="pageNum"  value=" ${pageInfo.pageNum}" />

</form>

<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="warning/warUserList.shtml" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>用户名：</label>
                    <input type="text" name="wusername" value="${wusername}"/>
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
            <li><a class="add" href="warning/warUserAdd.shtml" target="dialog" mask="true" rel="warUserAdd"><span>添加</span></a></li>
            <li><a title="确实要删除这些记录吗?" target="selectedTodo" postType="string"  rel="ids" href="warning/delWarUser.shtml" class="delete"><span>批量删除</span></a></li>
            <li class="line">line</li>
        </ul>
    </div>
    <table class="table" width="1200" layoutH="138">
        <thead>
        <tr>
            <th><input type="checkbox" group="ids" class="checkboxCtrl"></th>
            <th>ID</th>
            <th>用户名</th>
            <th>手机号</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${warUserLists}" var="wul">
            <tr target="wid" rel="${wul.id}">
                <td><input name="ids"  class="checkboxCtrl" value="${wul.id}" type="checkbox"></td>
                <td>${wul.id}</td>
                <td>${wul.name}</td>
                <td>${wul.phone}</td>
                <td>${wul.email}</td>
                <td>
                    <a title="确定要删除吗?" target="ajaxTodo" href="warning/delWarUser.shtml?ids=${wul.id}" class="btnDel">删除</a>
                    <a title="编辑" href="warning/warUserAdd.shtml?id=${wul.id}"  target="dialog"  rel="warUserAdd" class="btnEdit">编辑</a>
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