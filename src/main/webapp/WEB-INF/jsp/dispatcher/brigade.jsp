<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<HEAD>
  <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE>ЖКХ - Оформление</TITLE>
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
  <H2>Формирование наряда</H2>
  <c:if test="${not empty message}"><H3>${message}</H3></c:if>
  <c:choose>
    <c:when test="${not empty application}">
      <H5>${application.homeClient.lastname} ${application.homeClient.name} <br> ${application.homeClient.street} ${application.homeClient.home} ${application.homeClient.housing} ${application.homeClient.apartment} <br> ${application.homeClient.phone}</H5>
      <c:url value="/dispatcher/work.html" var="workPlanUrl"/>
      <FORM action="${workPlanUrl}" method="post">
      <TABLE>
        <TR>
          <TH>№</TH>
          <TH style="width: 130px;">Дата заявки</TH>
          <TH style="width: 130px;">Желаемое время</TH>
          <TH>Вид работ</TH>
          <TH>Бригадир</TH>
          <TH style="width: 150px;">Сотрудники</TH>
          <TH>Действие</TH>
        </TR>
          <TR>
            <TD>${application.identity}</TD>
            <TD><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${application.dateApplication}"/></TD>
            <TD><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${application.dateExecution}"/></TD>
            <TD>
              <c:forEach items="${application.applicationJobs}" var="applicationJobs">
                ${applicationJobs.typeWork.typeWork}<br>
              </c:forEach>
            </TD>
            <TD>
              <SELECT id="employee" name="employee">
                <OPTION value="-1">Выберите сотрудника</OPTION>
                <c:forEach items="${employees}" var="employees">
                  <OPTION value="${employees.identity}">${employees.lastname} ${employees.name}</OPTION>
                </c:forEach>
              </SELECT>
            </TD>
            <TD>
              <c:forEach items="${employees}" var="employees">
                <input style="height: 15px; width: 15px;" type="checkbox" id="employee_id" name="employee_id" value="${employees.identity}"> ${employees.lastname} ${employees.name}<br>
              </c:forEach>
            </TD>
            <TD>
              <FORM action="${workPlanUrl}" method="post">
                <INPUT type="hidden" name="identity" value="${application.identity}">
                <BUTTON type="submit">Сформировать</BUTTON>
              </FORM>
            </TD>
          </TR>
        </TABLE>
        </FORM>
    </c:when>
    <c:otherwise>
      <P>Список пуст</P>
    </c:otherwise>
  </c:choose>
</DIV>
</body>
</html>
