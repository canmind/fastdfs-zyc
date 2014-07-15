<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 为图像创建一个容器 -->
<table id="graphContainer">
    <tr>
        <td style="width: 40%;">
            <c:forEach items="${groupInfo}" var="group">
                <div class="panel" defH="200">
                    <h1>FASTDFS:${group.groupName}</h1>

                    <div id="${group.groupName}">
                        <c:forEach items="${group.storageList}" var="storage"
                                   varStatus="status">
                            <table>
                                <tr>
                                    <td style="width: 50%">
                                        <div id="${group.groupName}${status.index}" class="storage"
                                             style="float: left; margin-right:50px">
                                            <a href=" javascript:;"
                                               onclick="navTab.openTab('serverInfo','structure/serverInfo.shtml?ip=${storage.ipAddr}',{title:'服务器详细信息'})">
                                                <img src="${basePath}/images/storage.png" alt="${storage.ipAddr}"></a>


                                        </div>
                                    </td>
                                    <td width="80%">
                                        <table width="200px;" border="2" style="border-color:white;">
                                            <tr><td colspan="2" style="text-align: center;font-size: 15px;font-weight: 500">服务器相关参数</td></tr>
                                            <tr>
                                                <td style="font-weight: 800;color: #1327ff">IP地址</td>
                                                <td>${storage.ipAddr}</td>
                                            </tr>

                                            <tr>
                                                <td style="font-weight: 800;color: #1327ff">总容量</td>
                                                <td>${storage.totalMB}MB</td>
                                            </tr>
                                            <tr>
                                                <td style="font-weight: 800;color: #1327ff">状态</td>
                                                <c:if test="${storage.curStatus=='ACTIVE'}">
                                                <td style="color: green;">${storage.curStatus}</td>
                                                </c:if>
                                                  <c:if test="${storage.curStatus=='OFFLINE'}">
                                                      <td style="color: red;">${storage.curStatus}</td>
                                                  </c:if>
                                            </tr>
                                        </table>
                                </tr>
                                </td>
                            </table>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </td>
        <td style="width: 20%"><img src="${basePath}/images/line.png" width="100%"></td>
        <td style="width: 40%">
            <div class="panel" defH="200">
                <h1>TRUCKER</h1>

                <div class="panelContent"><a href=" javascript:;"
                                             onclick="navTab.openTab('serverInfo','structure/serverInfo.shtml?ip=${trucker}',{title:'服务器详细信息'})">
                    <img src="${basePath}/images/track.png" alt="${trucker}" style="float: left; margin-right:350px"/></a>
                    <span style="font-weight: 800;color: red">${trucker}</span>
                </div>
            </div>
        </td>
    </tr>

</table>
