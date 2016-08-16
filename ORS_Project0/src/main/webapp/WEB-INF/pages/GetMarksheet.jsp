<%@ page language="java" contentType="text/html;charset=UTF-8 "
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ page isELIgnored="false"%>

<div>
	<sf:form commandName="form" method="POST"
		class="form-inline container">
		<h1>
			<span class="label label-primary"><s:message code="label.getMarksheet" /></span>
		</h1>
		<br>
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
		<div class="forminline">
			<div>



				<label><s:message code="label.rollNo" />*</label>
				<sf:input path="rollNo" class="form-control " />
				&nbsp;
				<button type="submit" class="btn btn-primary" name="operation"
					value="Get">
					Go&nbsp;<span class="glyphicon glyphicon-search"></span>
				</button>
			</div>


		</div>
		<br>
		<c:if test="${form.id >0 }">

			<div>
				<table class="table table-bordered">
					<tr class="info">
						<td><s:message code="label.rollNo" /></td>
						<td><c:out value="${form.rollNo }"></c:out></td>
					</tr>
					<tr class="info">
						<td><s:message code="label.name" /></td>
						<td><c:out value="${form.name }"></c:out></td>
					</tr>
					<tr class="info">
						<td><s:message code="label.physics" /></td>
						<td><c:out value="${ form.physics}"></c:out></td>
					</tr>
					<tr class="info">
						<td><s:message code="label.chemistry" /></td>
						<td><c:out value="${form.chemistry }"></c:out></td>
					</tr>
					<tr class="info">
						<td><s:message code="label.maths" /></td>
						<td><c:out value="${form.maths }"></c:out></td>

					</tr>
					<tr class="info">
						<td><s:message code="label.total" /></td>
						<td><c:out value="${form.maths+form.physics+form.chemistry }"></c:out></td>
					</tr>
				</table>
			</div>



		</c:if>



	</sf:form>
</div>
