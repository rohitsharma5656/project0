<%@ page language="java" contentType="text/html;charset=UTF-8 "
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet"
	href="../resources/css/bootstrap-datepicker3.min.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">








<c:url var="editUrl" value="/ctl/User?id=" />
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="/bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			
			
			<a class="navbar-brand"><img alt="Brand"
				title="SunilOS Open Source Technolgies Center"
				src="/ORS_Project0/resources/img/customsirLogo.png" width="150" height="30"></a>
		</div>

		<c:choose>
			<c:when test="${empty sessionScope.user }">
				<ul class="nav navbar-nav">
					<li><a href="<c:url value="/Welcome" />"> <span
							class="glyphicon glyphicon-home"></span> &nbsp;<s:message code="label.home" />
					</a></li>



					<li><a href="<c:url value="/SignUp" />"><span
							class="glyphicon glyphicon-list"></span> &nbsp;<s:message code="label.userRegistration" /></a></li>


                       

					<li><a href="<c:url value="/Login"  />"><span
							class="glyphicon glyphicon-user"></span> &nbsp;<s:message code="label.login" /></a></li>


                        




</ul>
			</c:when>
			<c:otherwise>
				<ul class="nav navbar-nav">
					<li class="active"><a href="<c:url value="/Welcome" />"> <span
							class="glyphicon glyphicon-home"></span> &nbsp;<s:message code="label.home" />
					</a></li>

					<c:if test="${sessionScope.user.roleId==1 }">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><s:message code="label.user" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/ctl/User"/>"><s:message code="label.addUser" /></a></li>
								<li><a href="<c:url value="/ctl/User/search"/>"><s:message code="label.userList" />
										</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><s:message code="label.role" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/ctl/Role"/>"> <s:message code="label.addRole" /> </a></li>
								<li><a href="<c:url value="/ctl/Role/search"/>"><s:message code="label.roleList" />
										</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><s:message code="label.student" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/ctl/Student"/>"><s:message code="label.addStudent" /></a></li>
								<li><a href="<c:url value="/ctl/Student/search"/>"><s:message code="label.studentList" />
										</a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><s:message code="label.college" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/ctl/College"/>"><s:message code="label.addCollege" />
								</a></li>
								<li><a href="<c:url value="/ctl/College/search"/>"><s:message code="label.collegeList" />
										</a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><s:message code="label.course" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/ctl/Course"/>"><s:message code="label.addCourse" /> </a></li>
								<li><a href="<c:url value="/ctl/Course/search"/>"><s:message code="label.courseList" />
										</a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><s:message code="label.faculty" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/ctl/Faculty"/>"><s:message code="label.addFaculty" />
								</a></li>
								<li><a href="<c:url value="/ctl/Faculty/search"/>"><s:message code="label.facultyList" />
										</a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><s:message code="label.marksheet" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/ctl/Marksheet"/>"><s:message code="label.addMarksheet" />
										 </a></li>
								<li><a href="<c:url value="/ctl/Marksheet/search"/>"><s:message code="label.marksheetList" />
										 </a></li>
								<li><a href="<c:url value="/ctl/Marksheet/get"/>"><s:message code="label.getMarksheet" />
										 </a></li>
								<li><a href="<c:url value="/ctl/Marksheet/meritlist"/>"><s:message code="label.meritList" />
										  </a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><s:message code="label.timetable" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/ctl/TimeTable"/>"><s:message code="label.addTimetable" />
										 </a></li>
								<li><a href="<c:url value="/ctl/TimeTable/search"/>"><s:message code="label.timetableList" />
										</a></li>
							</ul></li>
					</c:if>
					<c:if test="${sessionScope.user.roleId==2 }"></c:if>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><s:message code="label.logout" /><span class="caret"></span></a>
						<ul class="dropdown-menu">
						
						
						   <li><a style="color: #0073e6;"><b> <u><H4>
																Welcome
																<c:out value="${sessionScope.user.firstName}" />
																<c:out value="${sessionScope.user.lastName}" />
															</H4></b></a></u></li>
						

							<li><a href="<c:url value="ORS_Project0/resources/doc/index.html" />"><span
									class="glyphicon glyphicon-book"></span> &nbsp;<s:message code="label.javadoc" /> </a></li>


							<li><a href="<c:url value="/Login" />"><span
									class="glyphicon glyphicon-log-out"></span> &nbsp;<s:message code="label.logout" /></a></li>

						</ul></li>

					<%-- 	<c:choose>
						<c:when test="${ sessionScope.user.photo !=null}">

							<a href="${editUrl }${sessionScope.user.id}"> <img
								title="click here to change profile pic"
								onmouseover="bigImg(this)" onmouseout="normalImg(this)" alt=""
								src="data:image/jpeg;base64,${userImage }" class="img-rounded"
								width="50" height="50">
							</a>

						</c:when>
						<c:otherwise>

							<a href="${editUrl }${sessionScope.user.id}"> <img
								title="click here to change profile pic"
								onmouseover="bigImg(this)" onmouseout="normalImg(this)" alt=""
								src="resources/img/checked-user-xxl.png" class="img-rounded"
								width="50" height="50">
							</a>

						</c:otherwise>

					</c:choose>
 --%>


				</ul>

			</c:otherwise>
		</c:choose>


	</div>
</nav>
