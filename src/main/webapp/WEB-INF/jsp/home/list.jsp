<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<HEAD>
  <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE>ЖКХ - Квартиросъемщики</TITLE>
  <c:url value="/css/main.css" var="cssUrl"/>
  <LINK rel="stylesheet" type="text/css" href="${cssUrl}">
</HEAD>
<BODY>
<DIV id="header">
  <H1>ЖКХ<BR>«Первомайский район г.Витебск»</H1>
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
</DIV>
<DIV id="page">
  <H2>Квартиросъемщики</H2>
  <c:if test="${not empty message}"><H3>${message}</H3></c:if>
  <c:choose>
    <c:when test="${not empty homeClients}">
      <TABLE>
        <TR>
          <TH>Login</TH>
          <TH>Фамилия Имя Отчество</TH>
          <TH>Адрес проживания</TH>
          <TH>Телефон</TH>
          <TH colspan="2">Действие</TH>
        </TR>
        <c:forEach items="${homeClients}" var="homeClient">
          <TR>
            <TD>${homeClient.login}</TD>
            <TD>${homeClient.lastname} ${homeClient.name}</TD>
            <TD>${homeClient.street} ${homeClient.home} ${homeClient.housing} ${homeClient.apartment}</TD>
            <TD>${homeClient.phone}</TD>
            <TD>
              <c:url value="/home/edit.html" var="homeClientEditUrl"/>
              <FORM action="${homeClientEditUrl}" method="post">
                <INPUT type="hidden" name="identity" value="${homeClient.identity}">
                <BUTTON type="submit">Редактировать</BUTTON>
              </FORM>
            </TD>
            <TD>
              <c:url value="/home/delete.html" var="homeClientDeleteUrl"/>
              <FORM action="${homeClientDeleteUrl}" method="post">
                <INPUT type="hidden" name="identity" value="${homeClient.identity}">
                <BUTTON type="submit">Удалить</BUTTON>
              </FORM>
            </TD>
          </TR>
        </c:forEach>
      </TABLE>
    </c:when>
    <c:otherwise>
      <P>Список пуст</P>
    </c:otherwise>
  </c:choose>
</DIV>
</BODY>
</HTML>
