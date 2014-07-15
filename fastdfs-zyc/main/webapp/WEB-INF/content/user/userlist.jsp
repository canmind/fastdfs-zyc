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

<form id="pagerForm" method="post" action="#rel#">
    <input type="hidden" name="pageNum" value="1" />
    <input type="hidden" name="numPerPage" value="${model.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />
    <input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="user/userlist.shtml" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>用户名：</label>
                    <input type="text" name="username" value="${name}"/>
                </li>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
            </ul>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="user/useradd.shtml" target="dialog" mask="true" rel="useradd"><span>添加</span></a></li>
            <li><a title="确实要删除这些记录吗?" target="selectedTodo" postType="string"  rel="ids" href="user/deluser.shtml" class="delete"><span>批量删除</span></a></li>
            <li class="line">line</li>
        </ul>
    </div>
    <table class="table" width="1200" layoutH="138">
        <thead>
        <tr>
            <th><input type="checkbox" group="ids" class="checkboxCtrl"></th>
            <th>ID</th>
            <th>用户名</th>
            <th>密码</th>
            <th>权限</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userlist}" var="user">
            <tr target="wid" rel="${user.id}">
                <td><input name="ids"  class="checkboxCtrl" value="${user.id}" type="checkbox"></td>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.psword}</td>
                <td>${user.power=="1"?"普通":"高级"}</td>
                <td>
                    <a title="确实要删除吗？" target="ajaxTodo" href="user/deluser.shtml?ids=${user.id}" class="btnDel">删除</a>
                   <!-- <a title="编辑" href="user/updatePsword.shtml?ids=${user.id}"  target="dialog"  rel="updatePsword" class="btnEdit">修改密码</a>    -->
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--<div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                <option value="20">20</option>
                <option value="50">50</option>
                <option value="100">100</option>
                <option value="200">200</option>
            </select>
            <span>条，共${totalCount}条</span>
        </div>

        <div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>--%>

    </div>
</div>