<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head lang="${lang}">
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Montserrat:400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <title>Registration</title>
</head>
<body>

    <jsp:include page="_menu.jsp"></jsp:include>

    <div class="form__intro"></div>
    <div class="form__wrapper">
        <h2><fmt:message key="registration.page.form.name"/></h2>
        <div class="form__container">
            <c:if test="${not empty errorMessage}">
                <h3>${errorMessage}</h3>
            </c:if>
            <form method="POST" action="${pageContext.request.contextPath}/app/registration"  autocomplete="off" >
                <label id="usernameLabel"
                       for="username"><fmt:message key="registration.page.username"/></label>
                <input class="form__input"
                       type="text"
                       id="username"
                       name="username"
                       placeholder="<fmt:message key="registration.page.username.placeholder"/>"
                       required>
                <label id="fullNameLabel"
                       for="fullName"><fmt:message key="registration.page.full.name"/></label>
                <input type="text"
                       class="form__input"
                       id="fullName"
                       name="fullName"
                       placeholder="<fmt:message key="registration.page.full.name.placeholder"/>"
                       required>
                <label id="emailLabel"
                       for="email"><fmt:message key="registration.page.email"/></label>
                <input type="email"
                       class="form__input"
                       id="email"
                       name="email"
                       placeholder="<fmt:message key="registration.page.email.placeholder"/>"
                       required>
                <label id="passwordLabel"
                       for="password"><fmt:message key="registration.page.password"/></label>
                <input type="password"
                       class="form__input"
                       id="password"
                       name="password"
                       placeholder="<fmt:message key="registration.page.password.placeholder"/>"
                       required>
                <button type="submit"
                        class="form__button">
                    <fmt:message key="registration.page.button"/>
                </button>
            </form>
        </div>
    </div>

</body>
</html>
