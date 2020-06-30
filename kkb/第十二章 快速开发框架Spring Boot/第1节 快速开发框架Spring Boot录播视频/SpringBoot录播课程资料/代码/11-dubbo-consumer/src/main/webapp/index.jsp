<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>index</title>
</head>
<body>
    <form action="some/register" method="post">
        姓名：<input type="text" name="name"> <br>
        年龄：<input type="text" name="age"> <br>
        <input type="submit" value="注册">
    </form>
    <hr>
    <%--这里获取到的结果必须与DB中保持一致--%>
    <form action="some/find" method="post">
        年龄上限：<input type="text" name="age"> <br>
        <input type="submit" value="查询">
    </form>
    <hr>
    <%--这里获取到的结果与DB中的差不多就可以了--%>
    <a href="some/count">查询学生总人数</a>
</body>
</html>
