<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Montserrat:400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <title>Create Master</title>
</head>
<body>
    <jsp:include page="_menu.jsp"></jsp:include>

    <section class="section top"></section>

    <section class="section">
        <div class="container">
            <div class="section__header">
                <h3 class="section__subtitle">
                    <fmt:message key="create.master.title"/>
                </h3>
            </div>
            <div class="row">
                <form class="col s10 offset-s1"
                      method="post"
                      action="${pageContext.request.contextPath}/app/add_master"
                      autocomplete="off"
                      enctype="multipart/form-data">
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="firstName" id="firstName" type="text" class="validate" required>
                            <label for="firstName"><fmt:message key="registration.page.first.name"/></label>
                        </div>
                        <div class="input-field col s6">
                            <input name="lastName" id="lastName" type="text" class="validate" required>
                            <label for="lastName"><fmt:message key="registration.page.last.name"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="username" id="username" type="text" class="validate" required>
                            <label for="username"><fmt:message key="registration.page.username"/></label>
                        </div>
                        <div class="input-field col s6">
                            <input name="password" id="password" type="password" class="validate" required>
                            <label for="password"><fmt:message key="registration.page.password"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="email" id="email" type="email" class="validate" required>
                            <label for="email"><fmt:message key="registration.page.email"/></label>
                        </div>
                        <div class="input-field col s6">
                            <input name="instagram" id="instagram" type="text" class="validate" required>
                            <label for="instagram"><fmt:message key="create.master.instagram.label"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <select id="position-select" name="position" class="validate" required>
                                <c:forEach items="${positions}" var="i">
                                    <option value="${i}">${i}</option>
                                </c:forEach>
                            </select>
                            <label><fmt:message key="create.master.position.label"/></label>
                        </div>
                        <div class="input-field col s6">
                            <select id="service-select" name="services" multiple class="validate" required>
                                <c:forEach items="${services}" var="i">
                                    <option value="${i.id}" selected>${i.name}</option>
                                </c:forEach>
                            </select>
                            <label><fmt:message key="create.master.service.label"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="file-field input-field">
                            <div class="row">
                                <div class="col s2 btn-black btn-active" style="height: 35px;">
                                    <span><fmt:message key="create.master.photo.label"/></span>
                                    <input type="file" name="file" class="validate" required>
                                </div>
                                <div class="col s10 file-path-wrapper">
                                    <input class="file-path validate" type="text">
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn-black btn-active"><fmt:message key="create.master.button.submit"/></button>
                </form>
            </div>
        </div>
    </section>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/create_master.js"></script>
</body>
</html>
