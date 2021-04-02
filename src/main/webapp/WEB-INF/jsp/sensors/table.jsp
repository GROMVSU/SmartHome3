<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25.02.2021
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<BODY>
<TABLE>
    <TR>
        <TH>№</TH>
        <TH>Название</TH>
        <TH>Состояние</TH>
    </TR>
    <c:forEach items="${sensors}" var="sensor">
        <TR>
            <TD>${sensor.identity}</TD>
            <TD id="title_${sensor.identity}"> ${sensor.name} </TD>
            <TD>
                <c:choose>
                <c:when test="${sensor.state}">
                    <div id="state_${sensor.identity}" class="circle green"></div>
                </c:when>
                <c:otherwise>
                    <div id="state_${sensor.identity}" class="circle red"></div>
                </c:otherwise>
                </c:choose>
            </TD>
        </TR>
    </c:forEach>
</TABLE>
</BODY>
</html>
