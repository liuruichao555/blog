<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/web/common/common.inc"%>
    <title>文章列表</title>
    <style>
        .list {
            width: 100%;
            margin-top: 30px;
        }

        .list-title {
            width: 100%;
            margin-bottom: 30px;
        }

        .list-item {
            padding: 5px;
            border-bottom: 1px solid #E0E0E0;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/web/common/top.jsp" %>

    <div class="main">
        <div class="list">
            <div class="list-title">
                类别：
                <select style="width:150px;" id="catSelect">
                    <option value="0">全部</option>
                    <c:forEach items="${requestScope.allCategory}" var="category">
                        <option
                            <c:if test="${curCategory == category.catId}">selected="selected" </c:if>
                            value="${category.catId}">${category.catName}</option>
                    </c:forEach>
                </select>
            </div>
            <c:forEach items="${requestScope.pageBean.data}" var="article">
                <div class="list-item">
                    <a href="/article/${article.articleId}" target="_blank">${article.title}</a>
                </div>
            </c:forEach>
        </div>
        <div class="page">
            <%@ include file="/WEB-INF/web/common/page.jsp"%>
        </div>
    </div>

    <%@ include file="/WEB-INF/web/common/bottom.jsp" %>
    <script type="text/javascript">
        $(function(){
            $('#catSelect').change(function(){
                var item = $(this);
                var catId = item.val();
                if (catId) {
                    location.href = '/article/list/' + catId + '/1';
                }
            });
        });
    </script>
</body>
</html>
