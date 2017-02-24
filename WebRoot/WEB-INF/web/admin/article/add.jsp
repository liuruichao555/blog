<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/web/common/common.inc"%>
    <title>${requestScope.article.title}</title>
    <link rel="stylesheet" href="/js/themes/default/default.css" />
    <script charset="utf-8" src="/js/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="/style/lang/zh_CN.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/web/common/top.jsp" %>

    <div class="main">
        <h1>添加文章</h1>
        <div style="padding-top: 20px;">
            <p>标题：<input type="text" id="articleTitle" value=""></p>
            <p>
                分类：
                <select id="articleCategory">
                    <option value="-1">===请选择分类===</option>
                    <c:forEach items="${requestScope.allCategory}" var="category">
                        <option value="${category.catId}">${category.catName}</option>
                    </c:forEach>
                </select>
            </p>
            内容：<textarea id="articleContent" style="width: 100%;height: 600px;"></textarea><br/>
            <input type="button" value="提交" id="addBtn">
        </div>
    </div>

    <%@ include file="/WEB-INF/web/common/bottom.jsp" %>
    <script type="text/javascript">
        KindEditor.ready(function(K) {
            window.editor = K.create('#articleContent');
        });
        $(function(){
            var isPost = false;
            $('#addBtn').click(function(){
                if (isPost) {
                    return;
                }
                isPost = true;
                // kindereditor同步数据
                window.editor.sync();
                var title = $('#articleTitle').val();
                var catId = $('#articleCategory').val();
                var content = $('#articleContent').val();
                if (title == '') {
                    Boxy.alert('没写标题啊大哥。');
                    isPost = false;
                    return;
                }
                if (catId == -1) {
                    Boxy.alert('没选择分类啊大哥。');
                    isPost = false;
                    return;
                }
                if (content.length <= 0) {
                    Boxy.alert('没写内容啊大哥。');
                    isPost = false;
                    return;
                }
                var url = '/lrc/manager/admin/article/add';
                $.ajax({
                    url : url,
                    type : 'POST',
                    data : {'catId' : catId, 'title' : title, 'content' : content},
                    dataType : 'json',
                    success : function(data) {
                        isPost = false;
                        if (data.status == 1) {
                            location.href = '/lrc/manager/admin/article/toUpdate/' + data.data;
                        } else {
                            Boxy.alert(data.message);
                        }
                    },
                    error : function(data) {
                        isPost = false;
                        Boxy.alert('创建ajax请求失败。');
                    }
                })
            });
        });
    </script>
</body>
</html>
