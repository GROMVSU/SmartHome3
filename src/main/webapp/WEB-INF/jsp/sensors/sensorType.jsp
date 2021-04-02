<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17.03.2021
  Time: 10:15
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
    function save(id) {
        $.ajax({
            url: 'SensorsTypeUpdateServlet',
            data: {
                type: 1,
                id: id,
                val: ($('#val_' + id).val())
            },
            success: function (data) {
                //alert(data);
                // $('#sensorId').val()//$('#response').html(data);
            }
        });
    }
    function del(id) {
        $.ajax({
            url: 'SensorsTypeUpdateServlet',
            data: {
                type: 2,
                id: id
            },
            success: function (data) {
                $('#tr_' + id).remove();
            }
        });
    }
</script>
<HTML>
<HEAD>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <TITLE>Датчики - Типы</TITLE>
</HEAD>
<BODY>
<c:import url="/WEB-INF/jsp/header.jsp" />
<DIV id="page">
    <H2>Список типов датчиков</H2>
    <c:if test="${not empty message}"><H3>${message}</H3></c:if>
    <c:import url="/WEB-INF/jsp/sensors/tableType.jsp" />
</DIV>
</BODY>
</HTML>
