<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<HTML>
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<TITLE>ЖКХ - Ошибка</TITLE>
	<c:url value="/css/main.css" var="cssUrl"/>
	<LINK rel="stylesheet" type="text/css" href="${cssUrl}">
</HEAD>
<BODY>
<DIV id="header">
	<H1>ЖКХ<BR>«Первомайский район г.Витебск»</H1>
	<c:if test="${not empty authorizedUser}">
		<UL class="right">
			<c:forEach items="${menu}" var="item">
				<c:url value="${item.url}" var="itemUrl"/>
				<LI class="item"><A href="${itemUrl}">${item.name}</A></LI>
			</c:forEach>
			<c:url value="/profile/edit.html" var="profileEditUrl"/>
			<LI class="item"><A href="${profileEditUrl}">${authorizedUser.login}</A></LI>
			<c:url value="/logout.html" var="logoutUrl"/>
			<LI class="item"><A href="${logoutUrl}">выход</A></LI>
		</UL>
	</c:if>
</DIV>
<DIV id="page">
	<c:choose>
		<c:when test="${not empty error}">
			<H2>${error}</H2>
		</c:when>
		<c:when test="${not empty pageContext.errorData.requestURI}">
			<H2>Запрошенная страница ${pageContext.errorData.requestURI} не найдена на сервере</H2>
		</c:when>
		<c:otherwise>Непредвиденная ошибка приложения</c:otherwise>
	</c:choose>
	<c:url value="/index.html" var="mainUrl"/>
	<A href="${mainUrl}">На главную</A>
</DIV>
</BODY>
</HTML>
