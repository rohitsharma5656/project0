<%@ page language="java" contentType="text/html;charset=UTF-8 "
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>
<br>
<div>

	<div class="col-sm-offset-3">
		<h1 align="left">
			<font face="georgia" size="7px" color="red"><s:message code="label.welcome" /> </font>
		</h1>
		<h1 style="background-color: black;"></h1>
		<h1 style="background-color: black;"></h1>
		<h1 style="background-color: black;"></h1>
		<h1 style="background-color: black;"></h1>
		<c:if test="${not empty sessionScope.user }">
			<c:if test="${sessionScope.user.roleId==2 }">
				<h2 align="left" class="text-center">
					<a style="color: blue;" href="">Click here to see your
						Marksheet </a>
				</h2>

			</c:if>
		</c:if>
	</div>
</div>
