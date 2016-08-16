<%@ page language="java" contentType="text/html;charset=UTF-8 "
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page isELIgnored="false"%>

<div>
	<h1><s:message code="label.meritList" /></h1>
	<sf:form commandName="form" method="GET"
		class="form-inline container  text-center">
		<c:if test="${! empty list }">
			<table class="table table-striped table-bordered">
				<tr>
					<th  class="col-md-1">S.No.</th>
					<th class="col-md-1"><s:message code="label.rollNo" /></th>
					<th class="col-md-1"><s:message code="label.name" /></th>
					<th class="col-md-1"><s:message code="label.physics" /></th>
					<th class="col-md-1"><s:message code="label.chemistry" /></th>
					<th class="col-md-1"><s:message code="label.maths" /></th>
					<th class="col-md-1"><s:message code="label.total" /></th>



				</tr>
				<c:forEach items="${list }" var="marksheet" varStatus="ct">
					<tr>

						<td ><c:out
								value="${(form.pageSize*(form.pageNo-1))+ct.index+1 }" /></td>

						<td><c:out value="${marksheet.rollNo }" /></td>
						<td><c:out value="${marksheet.name }" /></td>
						<td><c:out value="${ marksheet.physics}" /></td>
						<td><c:out value="${marksheet.chemistry }" /></td>
						<td><c:out value="${marksheet.maths }"></c:out></td>
						<td><c:out
								value="${marksheet.maths+marksheet.physics+marksheet.chemistry }" /></td>

					</tr>
				</c:forEach>
			</table>
		</c:if>
	</sf:form>
</div>