<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<HEAD>
  <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE>ЖКХ - Лист наряда сотрудников</TITLE>
  <c:url value="/css/main.css" var="cssUrl"/>
  <LINK rel="stylesheet" type="text/css" href="${cssUrl}">
</HEAD>
<body>
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
  <H2>Лист наряда сотрудников</H2>
  <c:if test="${not empty message}"><H3>${message}</H3></c:if>
  <c:choose>
    <c:when test="${not empty workPlans}">
      <TABLE>
        <TR>
          <TH>Номер наряда</TH>
          <TH style="width: 130px;">Дата наряда</TH>
          <TH>Номер заявки</TH>
          <TH style="width: 130px;">Дата заявки</TH>
          <TH style="width: 130px;">Ответственный</TH>
          <TH>Статус</TH>
          <TH>Действие</TH>
        </TR>
        <c:forEach items="${workPlans}" var="workPlan">
          <TR style="background: ${workPlan.application.execution.identity == 7 ? 'red' : ''}">
            <c:url value="/dispatcher/update.html" var="updateUrl"/>
            <FORM action="${updateUrl}" method="post">
            <TD>${workPlan.identity}</TD>
            <TD><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${workPlan.dateOrder}"/></TD>
            <TD>${workPlan.application.identity}</TD>
            <TD><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${workPlan.application.dateApplication}"/></TD>
            <TD>${workPlan.foreman.lastname} ${workPlan.foreman.name}</TD>
            <TD>
              <SELECT id="executionIdentity" name="executionIdentity" ${workPlan.application.execution.identity == 7 ? 'disabled' : ''}>
                <c:forEach items="${executions}" var="executions">
                  <OPTION value="${executions.identity}" ${executions.identity == workPlan.application.execution.identity ? 'selected' : ''}>${executions.execution}</OPTION>
                </c:forEach>
              </SELECT>
            </TD>
            <TD>
                <INPUT type="hidden" name="identity" value="${workPlan.application.identity}">
                <BUTTON type="submit">Сохранить</BUTTON>
            </TD>
            </FORM>
          </TR>
        </c:forEach>
      </TABLE>
    </c:when>
    <c:otherwise>
      <P>Список пуст</P>
    </c:otherwise>
  </c:choose>
</DIV>

</body>
</html>
