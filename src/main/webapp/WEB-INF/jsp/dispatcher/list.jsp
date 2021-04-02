<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<HEAD>
  <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE>ЖКХ - Лист заявок</TITLE>
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
  <H2>Лист заявок</H2>
  <c:if test="${not empty message}"><H3>${message}</H3></c:if>
  <c:choose>
    <c:when test="${not empty applications}">
      <TABLE>
        <TR>
          <TH>Номер заявки</TH>
          <TH style="width: 130px;">Дата заявки</TH>
          <TH style="width: 130px;">Желаемое время</TH>
          <TH>Вид работ</TH>
          <TH>Статус</TH>
          <TH>Действие</TH>
        </TR>
        <c:forEach items="${applications}" var="application">
          <TR style="background: ${application.execution.identity == 7 ? 'red' : ''}">
            <TD>${application.identity}</TD>
            <TD><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${application.dateApplication}"/></TD>
            <TD><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${application.dateExecution}"/></TD>
            <TD>
              <c:forEach items="${application.applicationJobs}" var="applicationJobs">
              ${applicationJobs.typeWork.typeWork}<br>
            </c:forEach>
            </TD>
            <TD>${application.execution.execution}</TD>
            <TD>
              <c:if test="${application.execution.identity == 1}">
                <c:url value="/dispatcher/brigade.html" var="brigadeUrl"/>
                <FORM action="${brigadeUrl}" method="post">
                  <INPUT type="hidden" name="identity" value="${application.identity}">
                  <BUTTON type="submit">Обработать</BUTTON>
              </c:if>
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
