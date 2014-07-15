<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="pagerForm" method="post" action="testModule/testDownLoad.shtml">
    <input type="hidden" value="${pageNum}" id="pageNum" name="pageNum"/>
    <input type="hidden" value="${pageSize}" id="pageSize" name="pageSize"/>

</form>
<div class="subBar">
    <ul>
        <li>fileId:
            <input type="text" id="keyForSearch" value="${keySearch}"/>
            <button type="button" onclick="searchForKey()">检索</button>
        </li>

    </ul>
</div>
<table class="table" width="100%">
    <thead>
    <tr>
        <th>文件ID</th>
        <th>fileId</th>
        <th>创建时间</th>
        <th>文件名称</th>
        <th>文件类型</th>
        <th>文件大小</th>
        <th>下载测试链接</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${testFileList}" var="testFileListYdm">
        <tr>
            <td>${testFileListYdm.id}</td>
            <td>${testFileListYdm.file_id}</td>
            <td>${testFileListYdm.created}</td>
            <td>${testFileListYdm.file_name}</td>
            <td>${testFileListYdm.type}</td>

            <td><fmt:formatNumber value="${testFileListYdm.fileSize/1024}" pattern="#,###.##" />KB
            </td>
            <td><a href="testModule/downloadByApi.shtml?fieldId=${testFileListYdm.file_id}&fileName=${testFileListYdm.file_name}" target="_blank">下载文件</a></td>

        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="panelBar">
    <div class="pages">
        <span>显示</span>
        <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
            <option value="20">20</option>

        </select>
        <span>条，${testFileCount}条</span>
    </div>

    <div class="pagination" targetType="navTab" totalCount="${testFileCount}" numPerPage="${pageSize}" pageNumShown="10"
         currentPage="${pageNum}"></div>

</div>
<script type="text/javascript">
    function searchForKey() {
        var key = $("#keyForSearch").val();
        var pageNum =${pageNum};
        var pageSize =${pageSize};
        navTab.openTab("testDownLoad", 'testModule/testDownLoad.shtml?pageNum=' + pageNum + '&&pageSize=' + pageSize + '&&keyForSearch=' + key, { title:'下载测试'});


    }
</script>