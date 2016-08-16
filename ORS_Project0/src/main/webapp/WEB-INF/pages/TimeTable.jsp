<%@ page language="java" contentType="text/html;charset=UTF-8 "
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page isELIgnored="false"%>
<div class="col-sm-offset-2">

	<sf:form method="POST" commandName="form" class="form-horizontal"
		enctype="multipart/form-data">
		<sf:hidden path="id" />
		<sf:hidden path="createdBy"></sf:hidden>
		<%-- <sf:hidden path="modifiedBy"></sf:hidden> --%>
		<sf:hidden path="createdDatetime"></sf:hidden>
		<sf:hidden path="modifiedDatetime"></sf:hidden>
		<h1 class="col-sm-offset-2">
			<c:choose>
				<c:when test="${form.id>0}">
					<span class="label label-primary">Edit Time Table</span>
				</c:when>
				<c:otherwise>
					<span class="label label-primary"><s:message code="label.addTimetable" /></span>
				</c:otherwise>
			</c:choose>
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
		<div class="form-group">
			<label class="col-sm-2"> <s:message code="label.facultyName" />*
			</label>
			<div class="col-sm-3">
				<sf:select STYLE="width: 280px" size="0" class="form-control"
					path="userId">
					<sf:option value="" label="Select" />
					<sf:options items="${facultyList}" itemValue="id"
						itemLabel="value" />
				</sf:select>
				&nbsp;
				<sf:errors path="userId" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2"> <s:message code="label.courseName" />*
			</label>
			<div class="col-sm-3">
				<sf:select STYLE="width: 280px" size="0" class="form-control"
					path="courseId">
					<sf:option value="" label="Select" />
					<sf:options items="${courseList }" itemValue="id"
						itemLabel="courseName" />
				</sf:select>
				&nbsp;
				<sf:errors path="courseId" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2"> <s:message code="label.subjectName" />*
			</label>
			<div class="col-sm-3">
				<sf:select STYLE="width: 280px" size="0" class="form-control"
					path="subjectName">
					<sf:option value="" label="Select" />
					<sf:option value="Physics" label="Physics" />
					<sf:option value="Maths" label="Maths" />
					<sf:option value="Chemistry" label="Chemistry" />
					<sf:option value="JAVA" label="JAVA" />
					<sf:option value="Hibernate" label="Hibernate" />
					<sf:option value="Spring" label="Spring" />
				</sf:select>
				&nbsp;
				<sf:errors path="subjectName" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2"> <s:message code="label.day" />*
			</label>
			<div class="col-sm-3">
				<sf:select STYLE="width: 280px" size="0" class="form-control"
					path="day">
					<sf:option value="" label="Select" />
					<sf:option value="Monday" label="Monday" />
					<sf:option value="Tuesday" label="Tuesday" />
					<sf:option value="Wednesday" label="Wednesday" />
					<sf:option value="Thursday" label="Thursday" />
					<sf:option value="Friday" label="Friday" />
					<sf:option value="Saturday" label="Saturday" />
				</sf:select>
				&nbsp;
				<sf:errors path="day" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2"> <s:message code="label.time" />*
			</label>
			<div class="col-sm-3">
				<sf:select STYLE="width: 280px" size="0" class="form-control"
					path="time">
					<sf:option value="" label="Select" />
					<sf:option value="10.00AM-11.00AM" label="10.00AM-11.00AM" />
					<sf:option value="11.00AM-12.00AM" label="11.00AM-12.00AM" />
					<sf:option value="01.00PM-02.00PM" label="01.00PM-02.00PM" />
					<sf:option value="02.00PM-03.00PM" label="02.00PM-03.00PM" />
					<sf:option value="03.00PM-04.00PM" label="03.00PM-04.00PM" />
					<sf:option value="04.00PM-05.00PM" label="04.00PM-05.00PM" />
				</sf:select>
				&nbsp;
				<sf:errors path="time" cssClass="error" />
			</div>
		</div>
		<div class="col-sm-offset-3">
			<button type="submit" name="operation" value="Save"
				class="btn btn-primary">Submit</button>
			&nbsp;&nbsp;
			<button type="submit" name="operation" value="Cancel"
				class="btn btn-primary">Cancel</button>

		</div>


	</sf:form>


</div>






