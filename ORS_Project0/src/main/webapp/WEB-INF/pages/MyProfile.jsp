<%@ page language="java" contentType="text/html;charset=UTF-8 "
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>



<c:url var="editUrl1" value="/ctl/User/changepassword" />
<div class="col-sm-offset-2">
	<h1 class="col-sm-offset-2">
		<span class="label label-primary"><s:message
				code="label.myProfile" /></span>
	</h1>
	<sf:form id="sandbox-container" method="POST" commandName="form" class="form-horizontal">
		<sf:hidden path="id" />
		<h3></h3>
		<c:choose>
			<c:when test="${empty error }">
				<div style="width: 50%; height: 10%;" class="alert alert-danger"
					hidden="">
					<strong>Error !..</strong> ${error }
				</div>

			</c:when>
			<c:otherwise>
				<div style="width: 50%; height: 10%;" class="alert alert-danger">
					<strong>Error !..</strong> ${error }
				</div>

			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${empty success }">
				<div style="width: 50%; height: 10%;" class="alert alert-success"
					hidden="">
					<strong>Data Saved !..</strong> ${success }
				</div>


			</c:when>
			<c:otherwise>
				<div style="width: 50%; height: 10%;" class="alert alert-success">
					<strong>Data Saved !..</strong> ${success }
				</div>

			</c:otherwise>

		</c:choose>
		<h3></h3>
		<div class="form-group">
			<label class="col-sm-2"><s:message code="label.login" />*</label>
			<div class="col-sm-4">
				<sf:input class="form-control" path="login" />
				&nbsp;
				<sf:errors path="login" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2"><s:message code="label.firstName" />*</label>
			<div class="col-sm-4">
				<sf:input class="form-control" path="firstName" />
				&nbsp;
				<sf:errors path="firstName" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2"><s:message code="label.lastName" />*</label>
			<div class="col-sm-4">
				<sf:input class="form-control" path="lastName" />
				&nbsp;
				<sf:errors path="lastName" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2"><s:message code="label.gender" />*</label>

			<div class="col-sm-4">
				<sf:radiobutton path="gender" value="Male" />
				<s:message code="label.male" />
				<sf:radiobutton path="gender" value="Female" />
				<s:message code="label.female" />
				&nbsp;
				<sf:errors path="gender" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2"><s:message code="label.mobileNo" />*</label>
			<div class="col-sm-4">
				<sf:input class="form-control" path="mobileNo" />
				&nbsp;
				<sf:errors path="mobileNo" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2"><s:message code="label.dob" />*</label>
			<div class="col-sm-4">
				<div class="input-group date">
					<sf:input class="form-control" path="dob" id="dob"
						readonly="readonly" />
					<span class="input-group-addon"><span
						class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
				</div>
				&nbsp;
				<sf:errors path="dob" cssClass="error" />

			</div>
		</div>
		<div class="col-sm-offset-2">
			<button type="submit" name="operation" value="Save"
				class="btn btn-primary">Submit</button>
			&nbsp;&nbsp; <a href="${editUrl1 }" class="btn btn-primary">Change
				Password</a>
		</div>
	</sf:form>
</div>


