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
<c:url value="/drawable/planner_off.png" var="planner_off"/>
<c:url value="/drawable/planner_on.png" var="planner_on"/>
<c:url value="/drawable/light_off.png" var="light_off"/>
<c:url value="/drawable/light_on.png" var="light_on"/>
<c:url value="/drawable/on.png" var="on"/>
<c:url value="/drawable/off.png" var="off"/>
<c:url value="/drawable/unlock.png" var="unlock"/>
<c:url value="/drawable/lock.png" var="lock"/>
<c:url value="/drawable/detector.png" var="detector"/>
<c:url value="/drawable/error.png" var="error"/>
<html>
<script type="text/javascript">
    var shed;
    var lamp;
    var man;
    var crash;
    var error;
    var alarm;
    setInterval(getDataInfo, 2000);
    function getDataInfo() {
        $.ajax({
            url: 'ControlUpdateServlet',
            type: "POST",
            data : {
                type    :   1
            },
            success: function(result) {
                shed = result.bShed;
                lamp = result.bLamp;
                man = result.bMan;
                crash = result.bCrash;
                error = result.bError;
                alarm = result.bAlarm;
                $('#img1').attr("src", (shed ? "${planner_on}":"${planner_off}"));
                $('#img2').attr("src", (lamp ? "${light_on}":"${light_off}"));
                $('#img3').attr("src", (man ?  "${on}":"${off}"));
                if (crash){
                    $('#info').text("Сработала сигнализация");
                    $('#img0').attr("src", "${detector}");
                }else if(error){
                    $('#info').text("Ошибка датчика");
                    $('#img0').attr("src", "${error}");
                }else if(alarm){
                    $('#info').text("Поставлена на сигнализацию");
                    $('#img0').attr("src", "${lock}");
                }else{
                    $('#info').text("Выключена");
                    $('#img0').attr("src", "${unlock}");
                }
            }
        });
    }
    function setControlType(id){
        var val;
        switch (id) {
            case 1:
                val = shed ? 0 : 1;
                break;
            case 2:
                val = lamp ? 0 : 1;
                break;
            case 3:
                val = man ? 0 : 1;
                break;
            case 0:
                if (crash | error | alarm){
                    val = 0;
                }else{
                    val = 1;
                }
                break;
        }
        $.ajax({
            url: 'ControlUpdateServlet',
            type: "POST",
            data : {
                type    :   2,
                id      :   id,
                val     :   val
            },
            success: function(data) {

            }
        });
    }
</script>
<head>
    <TITLE>Управление</TITLE>
</head>
<body>
    <c:import url="/WEB-INF/jsp/header.jsp" />
    <DIV id="page">
        <H2>Управление и контроль</H2>
        <c:if test="${not empty message}"><H3>${message}</H3></c:if>
        <div class="parent">
            <img id="img0" onclick="setControlType(0)" class="displayed" src="${unlock}">
            <p id="info" class="text-center">Выключена</p>
        </div>

        <div class="parent">
            <div class="child">
                <img id="img1" onclick="setControlType(1)" class="displayed"
                    <c:choose>
                    <c:when test="${board.bShed}">
                        src="${planner_on}"
                    </c:when>
                    <c:otherwise>
                        src="${planner_off}"
                    </c:otherwise>
                    </c:choose>
                >
                <p class="text-center">По расписанию</p>
            </div>
            <div class="child">
                 <img id="img2" onclick="setControlType(2)" class="displayed"
                 <c:choose>
                 <c:when test="${board.bLamp}">
                      src="${light_on}"
                 </c:when>
                 <c:otherwise>
                      src="${light_off}"
                 </c:otherwise>
                 </c:choose>
                 >
                <p class="text-center">Состояние</p>
                <p></p>
            </div>
            <div class="child">
                 <img id="img3" onclick="setControlType(3)" class="displayed"
                 <c:choose>
                 <c:when test="${board.bMan}">
                      src="${on}"
                 </c:when>
                 <c:otherwise>
                      src="${off}"
                 </c:otherwise>
                 </c:choose>
                 >
                <p class="text-center">Ручное</p>
            </div>
        </div>
    </DIV>
</body>
</html>
