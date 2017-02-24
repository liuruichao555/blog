<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="/style/bootstrap.css?v=20150905"/>
<c:set var="preview" value="5"></c:set>
<c:if test="${requestScope.pageBean != null}">
    <ul class="pagination">
        <%-- 首页和上一页 --%>
        <c:if test="${requestScope.pageBean.pageIndex == 1}">
            <li title="首页" class="disabled"><a href="javascript:;">首页</a></li>
            <li title="上一页" class="disabled"><a href="javascript:;">上一页</a></li>
        </c:if>
        <c:if test="${requestScope.pageBean.pageIndex > 1}">
            <li title="首页"><a href="${requestScope.pageBean.url}/1">首页</a></li>
            <li title="上一页"><a href="${requestScope.pageBean.url}/${requestScope.pageBean.pageIndex - 1}">上一页</a></li>
        </c:if>

        <%-- 显示的页码 --%>
        <%-- 显示前两页 --%>
        <c:set var="startIndex" value="${requestScope.pageBean.pageIndex - preview}"></c:set>
        <c:set var="endIndex" value="${requestScope.pageBean.pageIndex}"></c:set>
        <c:if test="${startIndex < 1}">
            <c:set var="startIndex" value="1"></c:set>
        </c:if>
        <c:forEach begin="${startIndex}" end="${endIndex}" var="pageIndex">
            <c:if test="${pageIndex == requestScope.pageBean.pageIndex}">
                <li class="disabled" title="${requestScope.pageBean.pageIndex}">
                    <a href="javascript:;">${pageIndex}</a>
                </li>
            </c:if>
            <c:if test="${pageIndex != requestScope.pageBean.pageIndex}">
                <li title="${requestScope.pageBean.pageIndex}">
                    <a href="${requestScope.pageBean.url}/${pageIndex}">${pageIndex}</a>
                </li>
            </c:if>
        </c:forEach>
        <%-- 显示后两页 --%>
        <c:set var="startIndex" value="${requestScope.pageBean.pageIndex + 1}"></c:set>
        <c:set var="endIndex" value="${requestScope.pageBean.pageIndex + preview}"></c:set>
        <c:if test="${endIndex >= requestScope.pageBean.totalPage}">
            <c:set var="endIndex" value="${requestScope.pageBean.totalPage}"></c:set>
        </c:if>
        <c:forEach begin="${startIndex}" end="${endIndex}" var="pageIndex">
            <c:if test="${pageIndex == requestScope.pageBean.pageIndex}">
                <li class="disabled" title="${requestScope.pageBean.pageIndex}">
                    <a href="javascript:;">${pageIndex}</a>
                </li>
            </c:if>
            <c:if test="${pageIndex != requestScope.pageBean.pageIndex}">
                <li title="${requestScope.pageBean.pageIndex}">
                    <a href="${requestScope.pageBean.url}/${pageIndex}">${pageIndex}</a>
                </li>
            </c:if>
        </c:forEach>

        <%-- 尾页和下一页 --%>
        <c:if test="${requestScope.pageBean.pageIndex >= requestScope.pageBean.totalPage}">
            <li class="disabled" title="下一页"><a href="javascript:;">下一页</a></li>
            <li class="disabled" title="尾页"><a href="javascript:;">尾页</a></li>
        </c:if>
        <c:if test="${requestScope.pageBean.pageIndex < requestScope.pageBean.totalPage}">
            <li title="下一页">
                <a href="${requestScope.pageBean.url}/${requestScope.pageBean.pageIndex + 1}">下一页</a>
            </li>
            <li title="尾页"><a href="${requestScope.pageBean.url}/${requestScope.pageBean.totalPage}">尾页</a></li>
        </c:if>
    </ul>
</c:if>