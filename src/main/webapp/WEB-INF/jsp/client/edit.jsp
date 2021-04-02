<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<HTML>
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<TITLE>ЖКХ - Заявка</TITLE>
	<c:url value="/css/main.css" var="cssUrl"/>
	<LINK rel="stylesheet" type="text/css" href="${cssUrl}">
</HEAD>
<BODY>
<DIV id="header">
	<H1>ЖКХ<BR>«Первомайский район г.Витебск»</H1>
	<UL class="right">
		<c:forEach items="${menu}" var="item">
			<c:url value="${item.url}" var="itemUrl"/>
			<LI class="item"><A href="${itemUrl}">${item.name}</A></LI>
		</c:forEach>
		<c:url value="/profile/edit.html" var="profileEditUrl"/>
		<LI class="item"><A href="${profileEditUrl}">${authorizedUser.login}</A></LI>
		<c:url value="/logout.html" var="logoutUrl"/>
		<LI class="item"><A href="${logoutUrl}">выход</A></LI>
	</UL>
</DIV>
<DIV id="page">
	<H2>Оформление заявки</H2><H5>${homeClient.lastname} ${homeClient.name} <br> ${homeClient.street} ${homeClient.home} ${homeClient.housing} ${homeClient.apartment} <br> ${homeClient.phone}</H5>
	<c:if test="${not empty message}"><H3>${message}</H3></c:if>
	<c:url value="/application/save.html" var="applicationSaveUrl"/>
	<FORM action="${applicationSaveUrl}" method="post">
		<c:if test="${not empty user}">
			<INPUT type="hidden" name="userIdentity" value="${user.identity}">
		</c:if>
		<LABEL for="typeWorkIdentity">Вид работ:</LABEL>
			<TABLE id="dataTable" border="0">
				<TR>
					<TD>
						<SELECT id="typeWorkIdentity" name="typeWorkIdentity">
							<OPTION value="-1">Выберите вид работ</OPTION>
							<c:forEach items="${typeWork}" var="typeWork">
								<OPTION value="${typeWork.identity}">${typeWork.typeWork}</OPTION>
							</c:forEach>
						</SELECT>
					</TD>
				</TR>
			</TABLE>
		<BUTTON type="button" onclick="addElement('dataTable')">+</BUTTON>
		<BUTTON hidden type="button" onclick="deleteRow('dataTable')">-</BUTTON>
		<LABEL for="scale">Маштаб работ:</LABEL>
		<TEXTAREA id="scale" name="scale" value="${scale}"></TEXTAREA>
		<LABEL for="dateExecution">Желаемое время:</LABEL>
		<INPUT type="text" id="dateExecution" name="dateExecution" pattern = "(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]">
		<BUTTON type="submit">Сохранить</BUTTON>
		<BUTTON type="reset">Сбросить</BUTTON>
	</FORM>
</DIV>
<c:url value="/js" var="javascript"/>
<script type="text/javascript" src="${javascript}/jquery.js"></script>
<script type="text/javascript" src="${javascript}/jquery.maskedinput.min.js"></script>
<script type="text/javascript" src="${javascript}/clientform.js"></script>
</BODY>
</HTML>
