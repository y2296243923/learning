<%-- 
    Document   : view
    Created on : 2020-5-4, 15:51:27
    Author     : Hello
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>用户意见箱</title>
</head>
<body>
    <h1 align=center>用户意见填写表</h1>
    <form method="post" >
        <p>姓&nbsp;&nbsp;&nbsp;&nbsp;名：
            <input type="text" class=txt size="12" maxlength="20" name="username" />
        </p>
        <p>性&nbsp;&nbsp;&nbsp;&nbsp;别：
            <input type="radio" value="male" />男
            <input type="radio" value="female" />女
        </p>
        <p>年&nbsp;&nbsp;&nbsp;&nbsp;龄：
            <input type="text" class=txt name="age"  />
        </p>
        <p>联系电话：
            <input type="text" class=txt name="tel" />
        </p>
        <p>电子邮件：
            <input type="text" class=txt name="email" />
        </p>
        <p>联系地址：
            <input type="text"  class=txt name="address" />
        </p>
        <p>请提出您宝贵的意见<br>
            <textarea name="yourworks" cols ="50" rows = "5"></textarea>
            <br>
            <input type="submit" name="submit" value="提交"/>
            <input type="reset" name="reset" value="清除" />
        </p>
    </form>
    <h2 align=center>我们会尽快为您解决！！！</h2>
    <h2 align=center>请您耐心等待❤</h2>
</body>
</html>
