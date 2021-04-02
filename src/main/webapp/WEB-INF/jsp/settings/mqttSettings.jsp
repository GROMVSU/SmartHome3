<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 03.03.2021
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/js/jquery-3.3.1.min.js" var="jsQ"/>
<script type="text/javascript" src="${jsQ}"></script>
<c:url value="/css/main.css" var="cssUrl"/>
<LINK rel="stylesheet" type="text/css" href="${cssUrl}">
<c:url value="/settings/saveMqtt.html" var="saveMqtt"/>

<html>
<head>
    <TITLE>Настройки</TITLE>
</head>
<body>
<c:import url="/WEB-INF/jsp/header.jsp" />
<DIV id="page">
    <H2>Настройки подключения к брокеру</H2>
    <c:if test="${not empty message}"><H3>${message}</H3></c:if>
    <FORM action="${saveMqtt}" method="post">
        <div>
            <LABEL for="ip">IP сервера:</LABEL>
            <INPUT type="text" id="ip" name="ip" class="text-center" value="${mqtt.ip}">
            <LABEL for="port">Порт:</LABEL>
            <INPUT type="text" id="port" name="port" class="text-center" value="${mqtt.port}">
            <LABEL for="pubTopic">Topic чтения:</LABEL>
            <INPUT type="text" id="pubTopic" name="pubTopic" class="text-center" value="${mqtt.pubTopic}">
            <LABEL for="subTopic">Topic записи:</LABEL>
            <INPUT type="text" id="subTopic" name="subTopic" class="text-center" value="${mqtt.subTopic}">
        </div>
<%--        <div class="right">--%>
            <BUTTON type="submit">Сохранить</BUTTON>
<%--        </div>--%>
    </FORM>
</DIV>
</body>
</html>
