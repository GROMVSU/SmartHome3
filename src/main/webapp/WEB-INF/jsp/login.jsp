<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${!empty language}">
	<fmt:setLocale value="${language}" scope="session"/>
</c:if>
<fmt:bundle basename="/messages" prefix="login.">
<HTML>
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<TITLE><fmt:message key="title"/></TITLE>
	<c:url value="/css/bootstrap.min.css" var="cssBoot"/>
	<LINK rel="stylesheet" type="text/css" href="${cssBoot}">
	<c:url value="/js/jquery-3.3.1.min.js" var="jsQ"/>
	<c:url value="/js/bootstrap.min.js" var="jsB"/>
	<c:url value="/js/fontawesome-all.min.js" var="jsC"/>
	<script type="text/javascript" src="${jsQ}"></script>
	<script type="text/javascript" src="${jsB}"></script>
	<script type="text/javascript" src="${jsC}"></script>
	<c:url value="/css/main.css" var="cssUrl"/>
	<LINK rel="stylesheet" type="text/css" href="${cssUrl}">
	<c:url value="/login.html" var="loginUrl"/>
</HEAD>
<BODY>
<c:import url="/WEB-INF/jsp/header.jsp" />
<FORM action="${loginUrl}" method="post">
	<div class="container outer-wrapper">
		<div class="row justify-content-center align-items-center" style="height:100vh">
			<div class="col-4">
				<div class="card">
					<div class="card-header h3">
						Вход в систему
						<c:import url="/WEB-INF/jsp/locale.jsp" />
					</div>
					<div class="card-body">

							<div style="margin-bottom: 30px" class="input-group">
								<input type="text" id="login" name="login" class="form-control" placeholder="Логин пользователя" required="" autofocus="" value="${param.login}" required />
								<i class="fa fa-user fa-1x" aria-hidden="true" style="margin-top: 10px;"></i>
							</div>

							<div style="margin-bottom: 30px" class="input-group">
								<input type="password" id="password" name="password" class="form-control" placeholder="Пароль" >
								<i class="fa fa-lock fa-1x" aria-hidden="true" style="margin-top: 10px;"></i>
							</div>

							<button class="btn btn-lg btn-primary btn-block" type="submit">Вход</button>
					</div>
					<c:if test="${not empty message}">
						<div class="card-footer text-center" >
							<p class="h6" style="color: red;">${message}</p>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</FORM>
</BODY>
</HTML>
</fmt:bundle>