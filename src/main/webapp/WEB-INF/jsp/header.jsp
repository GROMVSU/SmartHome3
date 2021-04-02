<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<DIV id="header">
	<H1>SmartHome</H1>
	<c:if test="${not empty authorizedUser}">
		<c:import url="/WEB-INF/jsp/menu.jsp" />
	</c:if>
</DIV>
</html>