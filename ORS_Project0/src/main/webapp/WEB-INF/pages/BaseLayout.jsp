<%@ page language="java" contentType="text/html;charset=UTF-8 "
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="en">
<head>


<title>Project0</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<!-- <link rel="stylesheet"
	href="/resources/css/bootstrap-datepicker3.min.css"> -->

<script type="text/javascript" src="../resources/css/js/jquery.min.js"></script>
<script type="text/javascript" src="../../resources/css/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="../../resources/css/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../../resources/css/js/calendar.js"></script>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script>
	function bigImg(x) {
		x.style.height = "90px";
		x.style.width = "90px";
	}
	function normalImg(x) {
		x.style.height = "50px";
		x.style.width = "50px";
	}
</script>

<style type="text/css">
.error {
	color: red;
	font-weight: bold;
}

.success {
	color: green;
	font-weight: bold;
}

a:HOVER {
	color: blue;
}

.nav>li>a:focus,.nav>li>a:hover {
	background-color: white;
	color: red;
}

a {
	color: white;
}

.map-responsive {
	overflow: hidden;
	padding-bottom: 56.25%;
	position: relative;
	height: 0;
}

.map-responsive iframe {
	left: 0;
	top: 0;
	height: 100%;
	width: 100%;
	position: absolute;
}
</style>

</head>
<body>
	<table cellspacing="2" align="center" width="100%">
		<tr>
			<td valign="top" colspan="2" height="100px"><tiles:insertAttribute
					name="header"></tiles:insertAttribute></td>
		</tr>
		<tr>
			<td style="background-color: white;" width="20%" colspan="0"
				height="300px" valign="top"><tiles:insertAttribute name="menu"></tiles:insertAttribute></td>
			<td colspan="2" height="500px" valign="top"><tiles:insertAttribute
					name="body"></tiles:insertAttribute></td>
		</tr>
		<tr>
			<td valign="bottom" colspan="2" align="center"><tiles:insertAttribute
					name="footer"></tiles:insertAttribute></td>
		</tr>
	</table>


</body>
</html>