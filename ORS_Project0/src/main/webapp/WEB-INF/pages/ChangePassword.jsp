<%@ page language="java" contentType="text/html;charset=UTF-8 "
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>

<div class="col-sm-offset-2">
	<h1><s:message code="label.changePassword" /></h1>
	<c:url var="editUrl" value="/ctl/User/changepassword" />
	<sf:form commandName="form" method="POST" class="form-horizontal">
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

		<div class="form-group">
			<label class="col-md-2"><s:message code="label.oldpassword" />*</label>
			<div class="col-sm-4">
				<sf:password class="form-control" path="oldpassword" />
				&nbsp;
				<sf:errors path="oldpassword" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2"><s:message code="label.newpassword" />*</label>
			<div class="col-sm-4">
				<sf:password class="form-control" path="newpassword" />
				&nbsp;
				<sf:errors path="newpassword" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2"><s:message
					code="label.confirmPassword" />*</label>
			<div class="col-sm-4">
				<sf:password class="form-control" path="confirmpassword" />
				&nbsp;
				<sf:errors path="confirmpassword" cssClass="error" />
			</div>
		</div>

		<div class="col-sm-offset-4">
			<button type="submit" name="operation" value="Save"
				class="btn btn-primary">Submit</button>
			&nbsp;&nbsp; <a href="${editUrl}" class="btn btn-primary">Cancel</a>

		</div>



	</sf:form>



</div>
