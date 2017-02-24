<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>类别管理</title>
    <%@ include file="/WEB-INF/web/common/common.inc"%>
    <style>
        .title {
            font-size: 30px;
            margin-top: 50px;
            margin-left: 50px;
        }
        .my-table {
            border-spacing: 0px;
            margin-top:20px;
            padding: 0px;
            width: 100%;
            border-left: 1px solid #DBDBDB;
            border-top: 1px solid #DBDBDB;
            text-align: center;
        }
        .my-table tr {
            height: 50px;
        }
        .my-table td {
            border-bottom: 1px solid #DBDBDB;
            border-right: 1px solid #DBDBDB;
        }
        .tr-focus {
            background-color: #faf2cc;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/web/common/top.jsp" %>

    <div class="main">
        <div class="title">类别管理</div>
        <div style="margin-top: 30px; margin-left: 50px;">
            <input type="text" id="addCatName" value="">
            <input type="button" id="addCatBtn" value="添加分类">
        </div>
        <table class="my-table">
            <tr>
                <td>类别</td>
                <td>文章</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${requestScope.allCategory}" var="category">
                <tr>
                    <td>${category.catName}</td>
                    <td>文章数量</td>
                    <td>
                        <!--<a href="javascript:;" class="editCat" data-id="${category.catId}">编辑</a>-->
                        <a href="javascript:;" class="delCat" data-id="${category.catId}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <%@ include file="/WEB-INF/web/common/bottom.jsp" %>
    <script type="text/javascript">
        $(function(){
            $('#addCatBtn').click(function(){
                var catName = $('#addCatName').val();
                if (!catName) {
                    Boxy.alert('你是不是傻，没写分类还想添加？')
                    return;
                }
                var url = '/category/add';
                $.ajax({
                    type : 'POST',
                    url : url,
                    data : {'categoryName' : catName},
                    dataType : 'json',
                    success : function(data) {
                        if (data.status == 1) {
                            location.href = location.href;
                        } else {
                            Boxy.alert(data.message);
                        }
                    }
                })
            });
            $('.delCat').click(function(){
                var item = $(this);
                var catId = item.attr('data-id');
                if (catId && catId > 0) {
                    var url = '/category/del';
                    $.ajax({
                        type : 'POST',
                        url : url,
                        data : {'catId' : catId},
                        dataType : 'json',
                        success : function(data) {
                            if (data.status == 1) {
                                location.href = location.href;
                            } else {
                                Boxy.alert(data.message);
                            }
                        }
                    })
                }
            });
        });
    </script>
</body>
</html>
