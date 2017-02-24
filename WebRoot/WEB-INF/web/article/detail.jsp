<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/web/common/common.inc"%>
    <title>${requestScope.article.title}</title>
    <style>
        .main {
            width: 60%;
            border-left: 1px solid #E0E0E0;
            border-right: 1px solid #E0E0E0;
        }

        .detail-title {
            padding-top: 20px;
            text-align: center;
        }

        .detail-content {
            padding-top: 20px;
            padding-left: 20px;
            padding-right: 20px;
            line-height: 28px;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/web/common/top.jsp" %>

    <div class="main">
        <div class="detail-title">
            <p>${requestScope.article.title}</p>
            <p>分类：<a href="/article/list/${requestScope.category.catId}/1">${requestScope.category.catName}</a></p>
        </div>
        <div class="detail-content">
            ${requestScope.article.content}
        </div>
    </div>

    <%@ include file="/WEB-INF/web/common/bottom.jsp" %>
</body>
</html>
