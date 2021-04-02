<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.02.2021
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/css/bootstrap.min.css" var="cssBoot"/>
<LINK rel="stylesheet" type="text/css" href="${cssBoot}">
<c:url value="/css/bootstrap-clockpicker.min.css" var="cssBootCl"/>
<LINK rel="stylesheet" type="text/css" href="${cssBootCl}">
<c:url value="/js/jquery-3.3.1.min.js" var="jsQ"/>
<c:url value="/js/bootstrap.min.js" var="jsB"/>
<c:url value="/js/bootstrap-clockpicker.js" var="jsC"/>
<script type="text/javascript" src="${jsQ}"></script>
<script type="text/javascript" src="${jsB}"></script>
<script type="text/javascript" src="${jsC}"></script>
<c:url value="/css/main.css" var="cssUrl"/>
<LINK rel="stylesheet" type="text/css" href="${cssUrl}">
<html>
<head>
    <script type="text/javascript">
        function updateOn(id){
            $.ajax({
                url: 'ScheduleUpdateServlet',
                data : {
                    type    : 1,
                    id      : id,
                    val     : ($('#on_'+id).is(":checked") ? 1 : 0)
                },
                success: function(data) {
                }
            });
        }
        function updateWeek(id){
            $.ajax({
                url: 'ScheduleUpdateServlet',
                data : {
                    type    : 2,
                    id      : id,
                    w1     : ($('#w1_'+id).is(":checked") ? 1 : 0),
                    w2     : ($('#w2_'+id).is(":checked") ? 1 : 0),
                    w3     : ($('#w3_'+id).is(":checked") ? 1 : 0),
                    w4     : ($('#w4_'+id).is(":checked") ? 1 : 0),
                    w5     : ($('#w5_'+id).is(":checked") ? 1 : 0),
                    w6     : ($('#w6_'+id).is(":checked") ? 1 : 0),
                    w7     : ($('#w7_'+id).is(":checked") ? 1 : 0)
                },
                success: function(data) {
                }
            });
        }
        function updateTime(){
            var id = $('#sch_id').text();
            var fF = $('#timeF').val();
            var fS = $('#timeS').val();
            $.ajax({
                url: 'ScheduleUpdateServlet',
                data : {
                    type   : 3,
                    id     : id,
                    tF     : fF,
                    tS     : fS
                },
                success: function(data) {
                    $('#time_'+id).text(fF+" - "+ fS);
                }
            });
        }
        function getTime(id){
            $.ajax({
                url: 'ScheduleInfoOneServlet',
                data : {
                    id      : id
                },
                success: function(data) {
                     $('#sch_id').text(id);
                     $('#timeF').val(data.timeFirst);
                     $('#timeS').val(data.timeSecond);
                     $('.popup-fade').css('display', 'block');
                }
            });
        }
      </script>
    <TITLE>Табло</TITLE>
</head>
<body>
    <c:import url="/WEB-INF/jsp/header.jsp" />
    <input id="sch_id"></input>
    <div class="popup-fade" >
        <div class="popup" style="height: 120px;">
            <a class="popup-close" href="#">Х</a>
            <table>
                <tr>
                    <td>
                        Время старта
                    </td>
                    <td>
                        Время финиша
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="input-group clockpicker">
                            <input id="timeF" type="text" class="form-control" value="18:00">
                            <span class="input-group-addon">
				                <span class="glyphicon glyphicon-time"></span>
			                </span>
                        </div>
                    </td>
                    <td>
                        <div class="input-group clockpicker">
                            <input id="timeS" type="text" class="form-control" value="18:00">
                            <span class="input-group-addon">
				                <span class="glyphicon glyphicon-time"></span>
			                </span>
                        </div>
                    </td>
                </tr>
            </table>
            <div class="right">
                <button class="btn btn-primary btn-sm ok">OK</button>
                <button class="btn btn-primary btn-sm cancel">Cancel</button>
            </div>
        </div>
    </div>



    <DIV id="page">
        <H2>Расписание</H2>
        <c:if test="${not empty message}"><H3>${message}</H3></c:if>
        <TABLE>
            <TR>
                <TH rowspan="2">№</TH>
                <TH rowspan="2">Название</TH>
                <TH rowspan="2">Вкл/Выкл</TH>
                <TH rowspan="2">Время</TH>
                <TH colspan="7">Дни недели</TH>
            <tr>
                <TH>Пн</TH>
                <TH>Вт</TH>
                <TH>Ср</TH>
                <TH>Чт</TH>
                <TH>Пт</TH>
                <TH>Сб</TH>
                <TH>Вс</TH>
            </tr>
            </TR>
            <c:forEach items="${sch}" var="sch">
                <TR>
                    <TD>${sch.identity}</TD>
                    <TD id="title_${sch.identity}" >Расписание ${sch.identity}</TD>
                    <TD>
                        <input onclick="updateOn(${sch.identity})" type="checkbox" class="checkbox" id="on_${sch.identity}" <c:if test="${sch.onOff}">checked=checked</c:if> value="${sch.onOff}" />
                        <label for="on_${sch.identity}"></label>
                    </TD>
                    <TD onclick="getTime(${sch.identity})" id="time_${sch.identity}" >
                        <fmt:formatDate value="${sch.timeFirst}" type="time" timeStyle="short" />
                        -
                        <fmt:formatDate value="${sch.timeSecond}" type="time" timeStyle="short" />
                    </TD>
                    <TD>
                        <input onclick="updateWeek(${sch.identity})" type="checkbox" class="checkbox" id="w1_${sch.identity}" <c:if test="${sch.weekN.w1}">checked=checked</c:if> value="${sch.weekN.w1}" />
                        <label for="w1_${sch.identity}"></label>
                    </TD>
                    <TD>
                        <input onclick="updateWeek(${sch.identity})" type="checkbox" class="checkbox" id="w2_${sch.identity}" <c:if test="${sch.weekN.w2}">checked=checked</c:if> value="${sch.weekN.w2}" />
                        <label for="w2_${sch.identity}"></label>
                    </TD>
                    <TD>
                        <input onclick="updateWeek(${sch.identity})" type="checkbox" class="checkbox" id="w3_${sch.identity}" <c:if test="${sch.weekN.w3}">checked=checked</c:if> value="${sch.weekN.w3}" />
                        <label for="w3_${sch.identity}"></label>
                    </TD>
                    <TD>
                        <input onclick="updateWeek(${sch.identity})" type="checkbox" class="checkbox" id="w4_${sch.identity}" <c:if test="${sch.weekN.w4}">checked=checked</c:if> value="${sch.weekN.w4}" />
                        <label for="w4_${sch.identity}"></label>
                    </TD>
                    <TD>
                        <input onclick="updateWeek(${sch.identity})" type="checkbox" class="checkbox" id="w5_${sch.identity}" <c:if test="${sch.weekN.w5}">checked=checked</c:if> value="${sch.weekN.w5}" />
                        <label for="w5_${sch.identity}"></label>
                    </TD>
                    <TD>
                        <input onclick="updateWeek(${sch.identity})" type="checkbox" class="checkbox" id="w6_${sch.identity}" <c:if test="${sch.weekN.w6}">checked=checked</c:if> value="${sch.weekN.w6}" />
                        <label for="w6_${sch.identity}"></label>
                    </TD>
                    <TD>
                        <input onclick="updateWeek(${sch.identity})" type="checkbox" class="checkbox" id="w7_${sch.identity}" <c:if test="${sch.weekN.w7}">checked=checked</c:if> value="${sch.weekN.w7}" />
                        <label for="w7_${sch.identity}"></label>
                    </TD>

                </TR>
            </c:forEach>
        </TABLE>
    </DIV>
</body>
</html>
<script>
    $(function() {
        $('.popup-open').click(function() {
            $('.popup-fade').fadeIn();
            return false;
        });

        $('.popup-close').click(function() {
            $(this).parents('.popup-fade').fadeOut();
            return false;
        });

        $(document).keydown(function(e) {
            if (e.keyCode === 27) {
                e.stopPropagation();
                $('.popup-fade').fadeOut();
            }
        });

        $('.popup-fade').click(function(e) {
            if ($(e.target).closest('.popup').length == 0) {
                $(this).fadeOut();
            }
        });
        $('.clockpicker').clockpicker({
             placement: 'left',
             align: 'top',
             autoclose: true
        });
        $('.cancel').click(function() {
            $(this).parents('.popup-fade').fadeOut();
            return false;
        });
        $('.ok').click(function() {
            updateTime();
            $(this).parents('.popup-fade').fadeOut();
            return false;
        });
    })
</script>
