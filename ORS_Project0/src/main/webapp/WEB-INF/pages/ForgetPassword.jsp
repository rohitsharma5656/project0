<%@ page language="java" contentType="text/html;charset=UTF-8 "
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page isELIgnored="false"%>




<div class="col-sm-offset-2">

	<h1 class="col-sm-offset-1">
		&nbsp;&nbsp;&nbsp;&nbsp;<span class="label label-primary">Forget
			Password</span>
	</h1>
	<sf:form method="POST" commandName="form">
		<sf:hidden path="id" />
		<c:choose>
			<c:when test="${empty error }">
				<div style="width: 50%; height: 10%;" class="alert alert-danger"
					hidden="">
					<strong>Error !..</strong> ${error}
				</div>

			</c:when>
			<c:otherwise>
				<div style="width: 50%; height: 10%;" class="alert alert-danger">
					<strong>Error !..</strong> ${error}
				</div>

			</c:otherwise>

		</c:choose>
		<c:choose>
			<c:when test="${empty success }">
				<div style="width: 50%; height: 10%;" class="alert alert-success"
					hidden="">
					<strong>Success !..</strong> ${success}
				</div>
			</c:when>
			<c:otherwise>
				<div style="width: 50%; height: 10%;" class="alert alert-success">
					<strong>Success !..</strong> ${success}
				</div>

			</c:otherwise>

		</c:choose>

		<h3 class="col-sm-offset-0">
			<span class="label label-primary">Submit your email address
				and we'll send you password.</span>
		</h3>

		<div class="form-group">
			<label class="col-md-1"><s:message code="label.login" />*</label>
			<div class="col-sm-4">
				<sf:input class="form-control" path="login" />

			</div>
			<button type="submit" name="operation" value="Go"
				class="btn btn-primary">Go</button>
			<div class="col-sm-offset-2">
				<sf:errors path="login" cssClass="error" />
			</div>

		</div>

	</sf:form>
</div>

