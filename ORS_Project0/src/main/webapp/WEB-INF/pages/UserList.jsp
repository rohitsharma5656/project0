<%@ page language="java" contentType="text/html;charset=UTF-8 "
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
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
		<s:message code="label.userList" />
	</h1>
	<c:url var="editUrl" value="/ctl/User?id=" />
	<c:url var="editUrl1" value="/ctl/User" />
	<sf:form action="search" method="POST" commandName="form"
		class="form-inline container  text-center">

		<sf:hidden path="pageNo" />
		<sf:hidden path="pageSize" />
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
		<div align="left" class="forminline">
			<div>
				<label><s:message code="label.role" />*</label>
				<sf:select STYLE="width: 150px" size="0" class="form-control"
					path="roleId">
					<sf:option value="0" label="Select"></sf:option>
					<sf:options items="${roleList }" itemLabel="roleName"
						itemValue="id" />
				</sf:select>

				&nbsp; <label><s:message code="label.firstName" />*</label>

				<sf:input class="form-control" path="firstName" />

				&nbsp; <label><s:message code="label.email" />*</label>
				<sf:input path="login" class="form-control" />
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
					<th class="col-md-2"><s:message code="label.firstName" /></th>
					<th class="col-md-2"><s:message code="label.lastName" /></th>
					<th class="col-md-2"><s:message code="label.email" /></th>
					<th class="col-md-2"><s:message code="label.gender" /></th>
					<th class="col-md-2"><s:message code="label.dob" /></th>
					<th class="col-md-1">Edit</th>
				</tr>



				<c:forEach items="${list }" var="user" varStatus="ct">
					<tr>
						<td><input type="checkbox" name="ids" value="${user.id }"></td>
						<td><c:out
								value="${(form.pageSize*(form.pageNo-1))+ct.index+1}" /></td>
						<td><c:out value="${user.firstName }" /></td>
						<td><c:out value="${user.lastName }" /></td>
						<td><c:out value="${user.login }" /></td>
						<td><c:out value="${user.gender }" /></td>
						<td><c:out value="${user.dob }" /></td>
						<td><a style="color: black;" href="${editUrl }${user.id}"><span
								class="glyphicon glyphicon-pencil"></span>Edit</a>
				</c:forEach>

			</c:if>
		</table>
		<table>
			<tr>
				<c:forEach begin="1" end="${noOfPages }" var="=i">
					<c:choose>
						<c:when test="${pageNo eq i }">
							<td>${i }</td>
						</c:when>
						<c:otherwise>
							<td><a href="${editUrl2 }${i}">${i }</a></td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</table>
		<a href="${editUrl1 }" class="btn btn-primary"><s:message
				code="label.new" /></a>
		<button type="submit" class="btn btn-primary pull-right"
			name="operation" value="Delete">
			<s:message code="label.delete" />
		</button>
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