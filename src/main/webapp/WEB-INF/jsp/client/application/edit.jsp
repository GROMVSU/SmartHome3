<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<HEAD>
  <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE>ЖКХ - Редактор заявки</TITLE>
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
  <H2>Редактор заявки</H2><H5></H5>
  <c:if test="${not empty message}"><H3>${message}</H3></c:if>
  <c:url value="/client/application/save.html" var="applicationSaveUrl"/>
  <FORM action="${applicationSaveUrl}" method="post">
    <c:if test="${not empty user}">
      <INPUT type="hidden" name="userIdentity" value="${application.homeClient.identity}">
    </c:if>
    <TABLE>
      <TR>
        <TD>№ Заявки:</TD>
        <TD type="text" id="applicationIdentity" name="applicationIdentity">${application.identity}</TD>
      </TR>
      <TR>
        <TD>Дата заявки:</TD>
        <TD type="text" id="dateApplication" name="dateApplication" ><fmt:formatDate  value="${application.dateApplication}" /></TD>
      </TR>
      <TR>
        <TD>Желаемое время:</TD>
        <TD>
        <INPUT type="text" id="dateExecution" name="dateExecution" value="${application.dateExecution}" pattern = "(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]">
        </TD>
      </TR>
      <TR>
        <TD>Вид работ:</TD>
        <TD>
          <c:forEach items="${application.applicationJobs}" var="item">
              <SELECT id="typeWorkIdentity" name="typeWorkIdentity">
              <c:forEach items="${typeWork}" var="typeWork">
                <OPTION value="${typeWork.identity}" ${typeWork.identity == item.typeWork.identity ? 'selected' : ''}>${typeWork.typeWork}</OPTION>
              </c:forEach>
              </SELECT>
          </c:forEach>
        </TD>
      </TR>
      <TR>
        <TD>Маштаб:</TD>
        <TD>
          <TEXTAREA id="scale" name="scale">${application.scale}</TEXTAREA>
        </TD>
      </TR>
      <TR>
        <TD>Статус заявки:</TD>
        <TD type="text" id="execution" name="execution">${application.execution.execution}</TD>
      </TR>
    </TABLE>
    <INPUT type="hidden" name="identity" value="${application.identity}">
    <BUTTON type="submit">Сохранить</BUTTON>
  </FORM>
</DIV>
<c:url value="/js" var="javascript"/>
<script type="text/javascript" src="${javascript}/jquery.js"></script>
<script type="text/javascript" src="${javascript}/jquery.maskedinput.min.js"></script>
<script type="text/javascript">
  jQuery(function($){
    $("#dateExecution").mask("9999-99-99 99:99",{placeholder:"yyyy-mm-dd HH:mm"});
  });
</script>
</body>
</html>
