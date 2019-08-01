<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<header class="header">
    <div class="container">
        <div class="header__inner">
            <div class="header__logo"><fmt:message key="header.logo" /></div>
            <nav class="header__nav">
                <ul>
                    <li class="nav__link"
                        <c:if test="${module == 'index'}">class="active"</c:if>
                    >
                        <a class="nav__sublink"
                           href="${pageContext.request.contextPath}/"><fmt:message key="header.menu.home"/></a>
                    </li>
                    <c:if test="${not empty loginedUser && loginedUser.role == 'CLIENT'}">
                        <li class="nav__link"
                            <c:if test="${module == 'masters'}">class="active"</c:if>
                        >
                            <a class="nav__sublink"
                               href="${pageContext.request.contextPath}/app/masters"><fmt:message key="header.menu.masters"/></a>
                        </li>
                    </c:if>
                    <c:if test="${not empty loginedUser && (loginedUser.role == 'ADMIN' || loginedUser.role == 'MASTER')}">
                        <li class="nav__link"
                            <c:if test="${module == 'all_appointments'}">class="active"</c:if>
                        >
                            <a class="nav__sublink"
                               href="${pageContext.request.contextPath}/app/all_appointments"><fmt:message key="header.menu.all_appoinments"/></a>
                        </li>
                    </c:if>
                    <c:if test="${not empty loginedUser && loginedUser.role == 'ADMIN'}">
                        <li class="nav__link"
                            <c:if test="${module == 'create_master'}">class="active"</c:if>
                        >
                            <a class="nav__sublink"
                               href="${pageContext.request.contextPath}/app/create_master"><fmt:message key="header.menu.create_master"/></a>
                        </li>
                    </c:if>
                    <c:if test="${empty loginedUser}">
                        <li class="nav__link"
                            <c:if test="${module == 'login'}">class="active"</c:if>>
                            <a class="nav__sublink"
                               href="${pageContext.request.contextPath}/app/login"><fmt:message key="header.menu.login"/></a>
                        </li>
                    </c:if>
                    <c:if test="${empty loginedUser}">
                        <li class="nav__link"
                            <c:if test="${module == 'registration'}">class="active"</c:if>
                        >
                            <a class="nav__sublink"
                               href="${pageContext.request.contextPath}/app/registration"><fmt:message key="header.menu.register"/></a>
                        </li>
                    </c:if>
                    <c:if test="${not empty loginedUser}">
                        <li class="nav__link"
                            <c:if test="${module == 'my_appointments'}">class="active"</c:if>
                        >
                            <span class="nav__sublink"><fmt:message key="header.menu.account"/></span>
                            <ul class="nav__drop__menu">
                                <c:if test="${not empty loginedUser && loginedUser.role == 'CLIENT'}">
                                    <li>
                                        <a class="nav__menu__content"
                                           href="${pageContext.request.contextPath}/app/me/appointments"><fmt:message key="header.menu.appointments"/></a>
                                    </li>
                                </c:if>
                                <li>
                                    <a class="nav__menu__content"
                                       href="${pageContext.request.contextPath}/app/logout"><fmt:message key="header.menu.logout"/></a>
                                </li>
                            </ul>
                        </li>
                    </c:if>
                    <li class="nav__link">
                                <span class="nav__sublink"><fmt:message key="header.menu.lang"/></span>
                        <ul class="nav__drop__menu">
                            <li>
                                <a class="nav__menu__content"
                                   href="?lang=ua"><fmt:message key="header.menu.language.ua"/></a>
                            </li>
                            <li>
                                <a class="nav__menu__content"
                                   href="?lang=en"><fmt:message key="header.menu.language.en"/></a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>