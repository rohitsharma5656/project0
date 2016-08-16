<%@ page language="java" contentType="text/html;charset=UTF-8 "
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>

<script type="text/javascript">
	function checkAll(master) {
		var checked = master.checked;
		var col = document.getElementsByTagName("INPUT");
		for (var i = 0; i < col.length; i++) {
			col[i].checked = checked;
		}
	}
</script>
<div>
	<h1>
		<s:message code="label.roleList" />
	</h1>
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
	<c:url var="editUrl" value="/ctl/Role?id=" />
	<c:url var="editUrl1" value="/ctl/Role" />
	<sf:form method="POST" commandName="form" action="search"
		class="form-inline container  text-center">
		<sf:hidden path="pageNo" />
		<sf:hidden path="pageSize" />

		<div class="forminline">
			<div align="left">
				<label><s:message code="label.role" />*</label>

				<sf:input class="form-control" path="roleName" />
				&nbsp;
				<button type="submit" class="btn btn-primary" name="operation"
					value="Search">
					<s:message code="label.search" />
					&nbsp;<span class="glyphicon glyphicon-search"></span>
				</button>
			</div>

		</div>


		<br>
		<br>
		<table class="table table-striped table-bordered">

			<c:if test="${!empty list }">
				<tr>
					<th class="col-md-1"><input type="checkbox"
						onclick="checkAll(this)">Select</th>
					<th class="col-md-1">S.No.</th>
					<th class="col-md-2"><s:message code="label.role" /></th>
					<th class="col-md-2"><s:message code="label.description" /></th>
					<th class="col-md-1">Edit</th>
				</tr>



				<c:forEach items="${list }" var="role" varStatus="ct">
					<tr>
						<td><input type="checkbox" name="ids" value="${role.id }"></td>
						<td><c:out
								value="${(form.pageSize*(form.pageNo-1))+ct.index+1}" /></td>
						<td><c:out value="${role.roleName }" /></td>
						<td><c:out value="${role.roleDescription }" /></td>

						<td><a style="color: black;" href="${editUrl }${role.id}"><span
								class="glyphicon glyphicon-pencil"></span>Edit</a>
				</c:forEach>

			</c:if>


		</table>
		<a href="${editUrl1 }" class="btn btn-primary">New</a>
		<button type="submit" class="btn btn-primary pull-right"
			name="operation" value="Delete">Delete</button>
		<br>
		<div align="left">
			<div class="btn-group">
				<c:forEach var="i" begin="1" end="${size}">
					<button type="submit" class="btn btn-primary text-center"
						name="pageNO" value="${i}">
						<span
							class="<c:out default="None" escapeXml="true" value="${form.pageNo==i}"/>">${i}</span>
					</button>
				</c:forEach>
			</div>
		</div>
	</sf:form>
</div>
