<!DOCTYPE html>
<!--DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd"-->

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<HTML>
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<TITLE>ЖКХ - Сотрудники</TITLE>
	<c:url value="/css/main.css" var="cssUrl"/>
	<LINK rel="stylesheet" type="text/css" href="${cssUrl}">
</HEAD>
<BODY>
<c:import url="/WEB-INF/jsp/header.jsp" />
<DIV id="page">
	<H2>Список сотрудников</H2>
	<c:if test="${not empty message}"><H3>${message}</H3></c:if>
	<c:url value="/user/edit.html" var="userEditUrl"/>
	<TABLE>
		<TR>
			<TH>Имя пользователя</TH>
			<TH>Роль</TH>
			<TH>Действие</TH>
		</TR>
		<c:forEach items="${users}" var="user">
			<TR>
				<TD>
					${user.login}
				</TD>
				<TD>${user.role.name}</TD>
				<TD>
					<FORM action="${userEditUrl}" method="post">
						<INPUT type="hidden" name="identity" value="${user.identity}">
						<BUTTON type="submit">Редактировать</BUTTON>
					</FORM>
				</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<FORM action="${userEditUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</DIV>
</BODY>
</HTML>
