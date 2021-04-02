<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<HTML>
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<TITLE>ЖКХ - Профиль пользователя «${authorizedUser.login}»</TITLE>
	<c:url value="/css/main.css" var="cssUrl"/>
	<LINK rel="stylesheet" type="text/css" href="${cssUrl}">
</HEAD>
<BODY>
<DIV id="header">
	<c:import url="/WEB-INF/jsp/header.jsp" />
</DIV>
<DIV id="page">
	<H2>Профиль пользователя «${authorizedUser.login}»</H2>
	<c:if test="${not empty message}"><H3>${message}</H3></c:if>
	<c:url value="/profile/save.html" var="profileSaveUrl"/>
	<FORM action="${profileSaveUrl}" method="post">
		<LABEL for="login">Имя пользователя:</LABEL>
		<INPUT type="text" id="login" value="${authorizedUser.login}" disabled>
		<LABEL for="role">Роль:</LABEL>
		<SELECT id="role" disabled>
			<OPTION selected>${authorizedUser.role.name}</OPTION>
		</SELECT>
		<LABEL for="old-password">Старый пароль:</LABEL>
		<INPUT type="password" id="old-password" name="old-password">
		<LABEL for="new-password">Новый пароль:</LABEL>
		<INPUT type="password" id="new-password" name="new-password">
		<BUTTON type="submit">Сменить пароль</BUTTON>
		<BUTTON type="reset">Отмена</BUTTON>
	</FORM>
</DIV>
</BODY>
</HTML>
