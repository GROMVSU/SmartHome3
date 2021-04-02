<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25.02.2021
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<c:url value="/sensor/save.html" var="saveUrl"/>--%>
<%--<c:url value="/sensor/delete.html" var="delUrl"/>--%>
<html>
<BODY>
<TABLE>
    <TR>
        <TH>№</TH>
        <TH>Название</TH>
        <TH colspan="2">Действие</TH>
    </TR>
    <c:forEach items="${types}" var="type">
        <TR id="tr_${type.identity}">
            <TD>${type.identity}</TD>
            <TD>
                <input id="val_${type.identity}" value="${type.title}" />
            </TD>
            <TD>
<%--                <FORM action="${saveUrl}" method="post">--%>
<%--                    <INPUT type="hidden" name="identity" value="${type.identity}">--%>
                    <BUTTON onclick="save(${type.identity})">Сохранить</BUTTON>
<%--                </FORM>--%>
            <TD>
<%--                <FORM action="${delUrl}" method="post">--%>
<%--                    <INPUT type="hidden" name="identity" value="${type.identity}">--%>
                    <BUTTON onclick="del(${type.identity})">Удалить</BUTTON>
<%--                </FORM>--%>
            </TD>
        </TR>
    </c:forEach>
</TABLE>
</BODY>
</html>
