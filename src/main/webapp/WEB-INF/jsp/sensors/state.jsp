<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12.02.2021
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/js/jquery-3.3.1.min.js" var="jsQ"/>
<script type="text/javascript" src="${jsQ}"></script>
<c:url value="/css/main.css" var="cssUrl"/>
<LINK rel="stylesheet" type="text/css" href="${cssUrl}">
<script type="text/javascript">
    setInterval(getData, 2000);
    function getData() {
        $.ajax({
            url: 'SensorInfoStateServlet',
            type: "POST",
            data : {},
            success: function(result) {
                for (var i in result) {
                    $("#title_"+result[i].identity).text(result[i].name);
                    $("#state_"+result[i].identity).removeAttr('class');
                    $("#state_"+result[i].identity).attr('class', result[i].state ? 'circle green' : 'circle red');
                }
            }
        });
    }
</script>
<HTML>
<HEAD>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <TITLE>Датчики - Состояние</TITLE>
</HEAD>
<BODY>
<c:import url="/WEB-INF/jsp/header.jsp" />
<DIV id="page">
    <H2>Список датчиков</H2>
    <c:if test="${not empty message}"><H3>${message}</H3></c:if>
    <c:import url="/WEB-INF/jsp/sensors/table.jsp" />
</DIV>
</BODY>
</HTML>

