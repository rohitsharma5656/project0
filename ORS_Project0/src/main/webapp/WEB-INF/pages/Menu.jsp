<%@ page language="java" contentType="text/html;charset=UTF-8 "
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>

<div align="justify" style="width: 80%; background-color: white;"
	class="col-sm-offset-1">

	<div style="background-color: black; height: 10%;">
		&ensp;<a href="?lang=en" style="color: white;"><font size="4"><s:message code="label.english" /></font></a>
		&ensp;<a href="?lang=hi" style="color: white;"><font size="4"><s:message code="label.hindi" /></font></a>
	</div>
	<div style="background-color: black;">
		<ul>
			<li style="color: white;"><a href="<c:url value="/AboutUs" />"
				style="color: white;"><font size="4"><s:message code="label.aboutUs" /></font></a></li>
			<li style="color: white;"><a href="<c:url value="/ContactUs"/>"
				style="color: white;"><font size="4"><s:message code="label.contactUs" /></font></a></li>
		</ul>

	</div>
	<div style="background-color: black;">
		<c:if test="${sessionScope.user!=null }">


			<ul>

				<li style="color: white;"><a style="color: white;"
					href="<c:url value="/ctl/User/profile" />"><font size="3"><s:message code="label.myProfile" />
							</font></a></li>
				<li style="color: white;"><a style="color: white;"
					href="<c:url value="/ctl/User/changepassword" />"><font
						size="3"><s:message code="label.changePassword" /></font></a></li>
				<%-- <li style="color: white;"><a style="color: white;"
					href="<c:url value="/ctl/Marksheet/meritlist"/>"><font size="3">Get
							MeritList</font></a></li> --%>

			</ul>
		</c:if>
	</div>
</div>


