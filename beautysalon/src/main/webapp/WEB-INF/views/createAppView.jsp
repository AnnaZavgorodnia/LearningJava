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
    <title>Make an appointment</title>
</head>
<body>

    <jsp:include page="_menu.jsp"></jsp:include>

    <section class="section top">
    </section>
    <section class="section">
        <div class="container">
            <div class="section__header">
                <h3 class="section__subtitle">
                    <fmt:message key="create.appointment.title"/>
                </h3>
            </div>
            <div class="create__app__content">
                <input type="hidden" value="${master.id}" id="master_id"/>
                <div class="create__app__master__image" id="masterImg">
                    <img src="${pageContext.request.contextPath}/masters/${master.imagePath}">
                </div>
                <div class="input-field">
                    <select id="select-service">
                        <option value="" disabled selected>Choose your option</option>
                        <c:forEach items="${master.services}" var="i">
                            <option value="${i.id}"
                                    id="${i.name}"
                                    data-price="${i.price}">${i.name}</option>
                        </c:forEach>
                    </select>
                    <label for="select-service" id="select_label">
                        <fmt:message key="create.appointment.service.title"/>
                    </label>
                    <h5 id="price"></h5>
                    <h5 id="app_date"></h5>
                    <h5 id="time"></h5>
                </div>
                <div class="create__app__time__date">
                    <label for="date">
                        <fmt:message key="create.appointment.date"/>
                    </label>
                    <input type="text" id="date" class="datepicker">
                    <div class="create__app__time" id="time-table">
                    </div>
                </div>
            </div>
        </div>
    </section>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/book_app.js"></script>
</body>
</html>
