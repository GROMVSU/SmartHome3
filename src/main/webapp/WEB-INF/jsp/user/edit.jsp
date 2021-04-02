<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${not empty user}">
		<c:set var="roleIdentity" value="${user.role.identity}"/>
		<c:set var="login" value="${user.login}"/>
		<c:set var="title" value="Сотрудник «${user.login}»"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Новый сотрудник"/>
	</c:otherwise>
</c:choose>

<HTML>
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<TITLE>Сотрудник</TITLE>
	<c:url value="/css/main.css" var="cssUrl"/>
	<LINK rel="stylesheet" type="text/css" href="${cssUrl}">
</HEAD>
<BODY>
<DIV id="header">
	<c:import url="/WEB-INF/jsp/header.jsp" />
</DIV>
<DIV id="page">
	<H2>${title}</H2>
	<c:if test="${not empty message}"><H3>${message}</H3></c:if>
	<c:url value="/user/save.html" var="userSaveUrl"/>
	<FORM action="${userSaveUrl}" method="post">
		<c:if test="${not empty user}">
			<INPUT type="hidden" name="identity" value="${user.identity}">
		</c:if>
		<LABEL for="login">Имя пользователя:</LABEL>
		<INPUT type="text" id="login" name="login" value="${login}">
		<LABEL for="role">Роль:</LABEL>
		<SELECT id="role" name="role">
			<c:forEach items="${roles}" var="role">
				<c:remove var="selected"/>
				<c:if test="${not empty roleIdentity and roleIdentity eq role.identity}">
					<c:set var="selected" value="selected"/>
				</c:if>
				<OPTION value="${role.identity}" ${selected}>${role.name}</OPTION>
			</c:forEach>
		</SELECT>
		<BUTTON type="submit">Сохранить</BUTTON>
		<BUTTON type="reset">Отмена</BUTTON>
	</FORM>
	<c:if test="${not empty user}">
		<c:url value="/user/delete.html" var="userDeleteUrl"/>
		<FORM action="${userDeleteUrl}" method="post">
			<INPUT type="hidden" name="identity" value="${user.identity}">
			<BUTTON type="submit">Удалить</BUTTON>
		</FORM>
	</c:if>
</DIV>
</BODY>
</HTML>
