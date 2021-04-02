<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12.02.2021
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<nav>
    <ui class="right topmenu">
        <c:forEach items="${menu}" var="item">
            <c:url value="${item.url}" var="itemUrl"/>
                <c:if test="${item.level==1}">
                    <li><a href="${itemUrl}">${item.name}</a>
                    <ul class="submenu">
                </c:if>
                <c:if test="${item.level==0}">
                    <li><a href="${itemUrl}">${item.name}</a></li>
                </c:if>
                <c:if test="${item.level==2}">
                    <li><a href="${itemUrl}">${item.name}</a></li>
                    </ul></li>
                </c:if>
        </c:forEach>
                <c:url value="/logout.html" var="logoutUrl"/>
                <LI><A href="${logoutUrl}">Выход</A></LI>
    </ui>
</nav>
<%--    <UL class="right">--%>
<%--        <c:forEach items="${menu}" var="item">--%>
<%--            <c:url value="${item.url}" var="itemUrl"/>--%>
<%--            <LI class="item"><A href="${itemUrl}">${item.name}</A></LI>--%>
<%--        </c:forEach>--%>
<%--&lt;%&ndash;        <c:url value="/profile/edit.html" var="profileEditUrl"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <LI class="item"><A href="${profileEditUrl}">${authorizedUser.login}</A></LI>&ndash;%&gt;--%>
<%--        <c:url value="/logout.html" var="logoutUrl"/>--%>
<%--        <LI class="item"><A href="${logoutUrl}">Выход</A></LI>--%>
<%--    </UL>--%>
</html>
