<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12.02.2021
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/css/bootstrap.min.css" var="cssBoot"/>
<LINK rel="stylesheet" type="text/css" href="${cssBoot}">
<c:url value="/js/bootstrap.min.js" var="jsB"/>
<c:url value="/js/fontawesome-all.min.js" var="jsF"/>
<script type="text/javascript" src="${jsB}"></script>
<script type="text/javascript" src="${jsF}"></script>
<c:url value="/js/jquery-3.3.1.min.js" var="jsQ"/>
<script type="text/javascript" src="${jsQ}"></script>
<c:url value="/css/main.css" var="cssUrl"/>
<LINK rel="stylesheet" type="text/css" href="${cssUrl}">

<HTML>
<HEAD>
    <script type="text/javascript">
        function updateChDoor(id){
            $.ajax({
                url: 'SensorsUpdateServlet',
                data : {
                    type    : 1,
                    id      : id,
                    val     : ($('#door_'+id).is(":checked") ? 1 : 0)
                },
                success: function(data) {
                    //alert(data);
                    // $('#sensorId').val()//$('#response').html(data);
            }
        });
        }
        function updateChCrach(id){
            $.ajax({
                url: 'SensorsUpdateServlet',
                data : {
                    type    : 2,
                    id      : id,
                    val     : ($('#crash_'+id).is(":checked") ? 1 : 0)
                },
                success: function(data) {
                }
            });
        }
        function updateChOn(id){
            $.ajax({
                url: 'SensorsUpdateServlet',
                data : {
                    type    : 3,
                    id      : id,
                    val     : ($('#on_'+id).is(":checked") ? 1 : 0)
                },
                success: function(data) {
                }
            });
        }
        function updateChTitle(id, title){
            $.ajax({
                url: 'SensorsUpdateServlet',
                data : {
                    type    : 4,
                    id      : id,
                    val     : title
                },
                success: function(data) {
                    $('#title_'+id).text(title);
                }
            });
        }
        function getFormUpdate(id) {
            $('.popup-fade').css('display', 'block');
            $('#sch_id').val(id);
            $('#title').val($('#title_'+id).text());
        }
    </script>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <TITLE>Датчики - Настройка</TITLE>
</HEAD>
<BODY>
<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="popup-fade" >
    <input id="sch_id"></input>
    <div class="popup" style="height: 120px;">
        <a class="popup-close" href="#">Х</a>
        <table>
            <tr>
                <td>
                    Наименование
                </td>
            </tr>
            <tr>
                <td>
                    <input id="title" type="text" class="form-control" style="width: 270px;">
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
    <H2>Список датчиков</H2>
    <c:if test="${not empty message}"><H3>${message}</H3></c:if>
        <TABLE >
            <TR>
                <TH>№</TH>
                <TH>Название</TH>
                <TH>Звонок</TH>
                <TH>Охрана</TH>
                <TH>Включен</TH>
            </TR>
            <c:forEach items="${sensors}" var="sensor">
                <TR>
                    <TD>${sensor.identity}</TD>
                    <TD id="title_${sensor.identity}" onclick="getFormUpdate(${sensor.identity});" >${sensor.title}</TD>
                    <TD>
                        <input onclick="updateChDoor(${sensor.identity})" type="checkbox" class="checkbox" id="door_${sensor.identity}" <c:if test="${sensor.door}">checked=checked</c:if> value="${sensor.door}"/>
                        <label for="door_${sensor.identity}"></label>
                    </TD>
                    <TD>
                        <input onclick="updateChCrach(${sensor.identity})" type="checkbox" class="checkbox" id="crash_${sensor.identity}" <c:if test="${sensor.crash}">checked=checked</c:if> value="${sensor.crash}" />
                        <label for="crash_${sensor.identity}"></label>
                    </TD>
                    <TD>
                        <input onclick="updateChOn(${sensor.identity})" type="checkbox" class="checkbox" id="on_${sensor.identity}" <c:if test="${sensor.on_off}">checked=checked</c:if> value="${sensor.on_off}" />
                        <label for="on_${sensor.identity}"></label>
                    </TD>
                </TR>
            </c:forEach>
        </TABLE>

</DIV>
</BODY>
</HTML>
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

        $('.cancel').click(function() {
            $(this).parents('.popup-fade').fadeOut();
            return false;
        });
        $('.ok').click(function() {
            updateChTitle($('#sch_id').val(), $('#title').val());
            $(this).parents('.popup-fade').fadeOut();
            return false;
        });
    })
</script>
