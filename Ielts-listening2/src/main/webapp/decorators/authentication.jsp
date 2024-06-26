<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Security-policy"
	content="default-src 'none'; 
	script-src 'self' 'nonce-rAnd0m' https://code.jquery.com/jquery-3.6.0.min.js
						https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js; 
	connect-src 'self'; 
	img-src 'self'; 
	style-src 'self' 'nonce-rAnd0m' https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css
				https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css;
	form-action 'self'" />
<title>Insert title here</title>
<!-- Add Bootstrap CSS Link -->
<link nonce="rAnd0m" rel="stylesheet"
	href="<c:url value="/assets/css/bootstrap.min.css"/>">
<link nonce="rAnd0m" href="<c:url value="/assets/css/fontawesome.css"/>"
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
