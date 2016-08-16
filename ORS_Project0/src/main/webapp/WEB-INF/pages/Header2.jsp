
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<c:url var="editUrl" value="/ctl/User?id=" />

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
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
				src="resources/img/customLogo.png" width="150" height="30"></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<ul class="nav navbar-nav">


			<c:choose>
				<c:when test="${empty sessionScope.user }">

					<!-- <ul class="nav nav-tabs"> -->
					<li><a href="<c:url value="/Welcome" />"> <span
							class="glyphicon glyphicon-home"></span> &nbsp;Home
					</a></li>



					<li><a href="<c:url value="/SignUp" />"><span
							class="glyphicon glyphicon-list"></span> &nbsp;User Registration</a></li>


					<li><a href="<c:url value="/Login"  />"><span
							class="glyphicon glyphicon-user"></span> &nbsp;Login</a></li>


					<!-- </ul> -->



				</c:when>
				<c:otherwise>

					<c:if test="${not empty sessionScope.user }">
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">


								<li class="active"><a href="<c:url value="/Welcome" />">
										<span class="glyphicon glyphicon-home"></span> &nbsp;Home
								</a></li>


								<c:if test="${sessionScope.user.roleId==1 }">

									<!-- <ul class="nav navbar-nav"> -->
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">User<span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/ctl/User"/>">Add User </a></li>
											<li><a href="<c:url value="/ctl/User/search"/>">User
													List</a></li>
										</ul></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">Role<span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/ctl/Role"/>">Add Role </a></li>
											<li><a href="<c:url value="/ctl/Role/search"/>">Role
													List</a></li>
										</ul></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">Student<span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/ctl/Student"/>">Add
													Student</a></li>
											<li><a href="<c:url value="/ctl/Student/search"/>">Student
													List</a></li>
										</ul></li>

									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">College<span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/ctl/College"/>">Add
													College </a></li>
											<li><a href="<c:url value="/ctl/College/search"/>">College
													List</a></li>
										</ul></li>

									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">Course<span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/ctl/Course"/>">Add
													Course </a></li>
											<li><a href="<c:url value="/ctl/Course/search"/>">Course
													List</a></li>
										</ul></li>

									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">Faculty<span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/ctl/Faculty"/>">Add
													Faculty </a></li>
											<li><a href="<c:url value="/ctl/Faculty/search"/>">Faculty
													List</a></li>
										</ul></li>

									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">Marksheet<span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/ctl/Marksheet"/>">Add
													Marksheet </a></li>
											<li><a href="<c:url value="/ctl/Marksheet/search"/>">Marksheet
													List </a></li>
											<li><a href="<c:url value="/ctl/Marksheet/get"/>">Get
													Marksheet </a></li>
											<li><a href="<c:url value="/ctl/Marksheet/meritlist"/>">Get
													Merit List </a></li>
										</ul></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">TimeTable<span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/ctl/Timetable"/>">Add
													TimeTable </a></li>
											<li><a href="<c:url value="/ctl/Timetable/search"/>">TimeTable
													List</a></li>
										</ul></li>
									<!-- </ul> -->







								</c:if>

								<c:if test="${sessionScope.user.roleId==2 }"></c:if>

								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false">Logout<span class="caret"></span></a>
									<ul class="dropdown-menu">

										<li><a href="<c:url value="/Javadoc" />"><span
												class="glyphicon glyphicon-book"></span> &nbsp;Javadoc </a></li>


										<li><a href="<c:url value="/Login" />"><span
												class="glyphicon glyphicon-log-out"></span> &nbsp;Logout</a></li>

									</ul></li>

								<c:choose>
									<c:when test="${ sessionScope.user.photo !=null}">
										<div align="right">
											<a href="${editUrl }${sessionScope.user.id}"> <img
												title="click here to change profile pic"
												onmouseover="bigImg(this)" onmouseout="normalImg(this)"
												alt="" src="data:image/jpeg;base64,${userImage }"
												class="img-rounded" width="50" height="50">
											</a>
										</div>
									</c:when>
									<c:otherwise>
										<div align="right">
											<a href="${editUrl }${sessionScope.user.id}"> <img
												title="click here to change profile pic"
												onmouseover="bigImg(this)" onmouseout="normalImg(this)"
												alt="" src="resources/img/checked-user-xxl.png"
												class="img-rounded" width="50" height="50">
											</a>
										</div>
									</c:otherwise>

								</c:choose>

							</ul>



						</div>


					</c:if>

				</c:otherwise>
			</c:choose>

		</ul>



	</div>
</nav>

