<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.03.2021
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <div class="mod-languages">
        <c:url value="/chooseLanguage.html?language=ru" var="urlRu"/>
        <c:url value="/chooseLanguage.html?language=en" var="urlEn"/>
        <c:url value="/img/ru.png" var="ru"/>
        <c:url value="/img/gb.png" var="gb"/>
        <ul class="lang-inline">
            <li dir="ltr">
                <a href="${urlRu}">
                    <img title="RU" alt="RU" src="${ru}">
                </a>
            </li>
            <li dir="ltr">
                <a href="${urlEn}">
                    <img title="EN" alt="EN" src="${gb}">
                </a>
            </li>
        </ul>
    </div>
</head>
<body>

</body>
</html>
