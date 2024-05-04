<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Add Bootstrap CSS Link -->
<link rel="stylesheet"
	href="<c:url value="/assets/css/bootstrap.min.css"/>">
<link href="<c:url value="/assets/css/fontawesome.css"/>"
	rel="stylesheet">
</head>
<body style="overflow-x: hidden;">
	<div class="container h-100  max-w-xxl py-5">
		<div
			class=" w-100 h-100 d-flex justify-content-center  align-items-center">
			<decorator:body>
			</decorator:body>
		</div>

	</div>

	<!-- Add Bootstrap JS and Popper.js -->

	<script src="<c:url value="/assets/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/assets/js/bootstrap.min.js"/>"></script>
</body>
</html>
