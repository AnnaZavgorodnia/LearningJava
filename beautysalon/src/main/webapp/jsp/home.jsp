<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Montserrat:400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
    <title>Home</title>
</head>
<body>

<div class="video__intro">
    <div class="video">
        <video class="video__media" src="${pageContext.request.contextPath}/images/fashion.mp4" autoplay muted loop></video>
    </div>

    <div class="intro__content">
        <div class="container">
            <div class="intro__inner">
                <h2 class="intro__subtitle">Creative template</h2>
                <h1 class="intro__title">S a l o n</h1>
                <a class="btn" href="#">Learn More</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
