<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="Codescandy" />
<meta http-equiv="Content-Security-policy" 
	content="default-src 'none';
	script-src 'self' 'nonce-rAnd0m' https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js
					https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js
					https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js
					https://cdn.datatables.net/2.0.5/js/dataTables.min.js
					https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js
					https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css; 
	connect-src 'self'; 
	img-src 'self'; 
	font-src 'self';
	style-src 'self' 'nonce-rAnd0m';
	form-action 'self'"/>

<link nonce="rAnd0m" href="<c:url value='/views/luyende/css/styleLuyenDeTest.css' />"
	rel="stylesheet" type="text/css">
<link nonce="rAnd0m"
	href='<c:url value="/assets/fonts/themify-icons/themify-icons.css" />'
	rel="stylesheet" type="text/css">

<link nonce="rAnd0m" rel="preconnect" href="https://fonts.googleapis.com">
<link nonce="rAnd0m" rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link nonce="rAnd0m"
	href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&family=Lato:wght@100;300;400;700;900&family=Roboto:wght@300;400;500;700&display=swap"
	rel="stylesheet">

<link nonce="rAnd0m" href='<c:url value="/stylecss/base/base.css" />' rel="stylesheet"
	type="text/css">

<link nonce="rAnd0m" rel="stylesheet"
	href="../assets/libs/glightbox/dist/css/glightbox.min.css" />

<!-- Favicon icon-->
<link nonce="rAnd0m" rel="shortcut icon" type="image/x-icon"
	href="../assets/images/favicon/favicon.ico" />

<!-- darkmode js -->
<script nonce="rAnd0m" src="../assets/js/vendors/darkMode.js"></script>

<!-- Libs CSS -->
<link nonce="rAnd0m" href="../assets/fonts/feather/feather.css" rel="stylesheet" />
<link nonce="rAnd0m" href="../assets/libs/bootstrap-icons/font/bootstrap-icons.min.css"
	rel="stylesheet" />
<link nonce="rAnd0m" href="../assets/libs/simplebar/dist/simplebar.min.css"
	rel="stylesheet" />

<!-- Theme CSS -->
<link nonce="rAnd0m" rel="stylesheet" href="../assets/css/theme.min.css" />

<title>Education - Geeks Bootstrap 5 Template</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script
	src="https://cdn.datatables.net/2.0.5/js/dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css"></script>
<script>
	$(document)
			.ready(
					function() {

						// Cấu hình các nhãn phân trang
						$('.table-pag')
								.dataTable(
										{
											"language" : {
												"sProcessing" : "Đang xử lý...",
												"sLengthMenu" : "Xem _MENU_ mục",
												"sZeroRecords" : "Không tìm thấy dòng nào phù hợp",
												"sInfo" : "Đang xem _START_ đến _END_ trong tổng số _TOTAL_ mục",
												"sInfoEmpty" : "Đang xem 0 đến 0 trong tổng số 0 mục",
												"sInfoFiltered" : "(được lọc từ _MAX_ mục)",
												"sInfoPostFix" : "",
												"sSearch" : "Tìm:",
												"sUrl" : "",
												"oPaginate" : {
													"sFirst" : "Đầu",
													"sPrevious" : "Trước",
													"sNext" : "Tiếp",
													"sLast" : "Cuối"
												}
											},
											"processing" : true, // tiền xử lý trước
											"aLengthMenu" : [
													[ 5, 10, 15, 20 ],
													[ 5, 10, 15, 20 ] ], // danh sách số trang trên 1 lần hiển thị bảng
											"order" : [ [ 3, 'asc' ] ]
										//sắp xếp giảm dần theo cột thứ 1
										// stateSave: true 
										});

					});
</script>
	<script nonce="rAnd0m" type="text/javascript">
		function setDefaultImage(img) {
			img.onerror = null; // Ngăn chặn việc gọi lặp lại
			img.src = "https://th.bing.com/th/id/OIP.xaADddZHWRoU3TbjEVGssQHaFj?rs=1&pid=ImgDetMain";
		}
	</script>
</head>
<body style="overflow-x: hidden;">
	<%@ include file="/common/user/Header.jsp"%>
	<div class="d-flex flex-column container-fluid"
		style="max-width: 1440px; margin-top: 60px;">
		<decorator:body>

		</decorator:body>
	</div>
	<%@ include file="/common/user/Footer.jsp"%>

	<!-- Scripts -->
	<!-- Libs JS -->
	<script nonce="rAnd0m" src="../assets/libs/%40popperjs/core/dist/umd/popper.min.js"></script>
	<script nonce="rAnd0m" src="../assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
	<script nonce="rAnd0m" src="../assets/libs/simplebar/dist/simplebar.min.js"></script>

	<!-- Theme JS -->
	<script nonce="rAnd0m" src="../assets/js/theme.min.js"></script>

	<script nonce="rAnd0m" src="../assets/libs/apexcharts/dist/apexcharts.min.js"></script>
	<script nonce="rAnd0m" src="../assets/js/vendors/chart.js"></script>
	<script nonce="rAnd0m" src="../assets/libs/flatpickr/dist/flatpickr.min.js"></script>
	<script nonce="rAnd0m" src="../assets/js/vendors/flatpickr.js"></script>



</body>
</html>