<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

<link href="../assets/css/fontawesome.css" rel="stylesheet">


<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&family=Lato:wght@100;300;400;700;900&family=Roboto:wght@300;400;500;700&display=swap"
	rel="stylesheet">

<link href='<c:url value="/stylecss/base/base.css" />' rel="stylesheet"
	type="text/css">
<link href='<c:url value="/stylecss/components/dropdown.css" />'
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="../assets/libs/flatpickr/dist/flatpickr.min.css" />
<!-- Required meta tags -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="Codescandy" />
<meta http-equiv="Content-Security-policy"
	content="default-src 'none'; script-src 'self'; connect-src 'self'; 
	img-src 'self'; style-src 'self';base-uri 'self';form-action 'self'; frame-ancestors 'self'" />
<script
	src="https://cdn.ckeditor.com/ckeditor5/40.0.0/classic/ckeditor.js"></script>

<!-- Favicon icon-->
<link rel="shortcut icon" type="image/x-icon"
	href="../assets/images/favicon/favicon.ico" />

<script src="../assets/js/vendors/darkMode.js"></script>
<!-- Libs CSS -->
<link href="../assets/fonts/feather/feather.css" rel="stylesheet" />
<link href="../assets/libs/bootstrap-icons/font/bootstrap-icons.min.css"
	rel="stylesheet" />
<link href="../assets/libs/simplebar/dist/simplebar.min.css"
	rel="stylesheet" />

<!-- Theme CSS -->
<link rel="stylesheet" href="../assets/css/theme.min.css" />
<script src="<c:url value="/assets/js/jquery.min.js"/>"></script>
<script src="<c:url value="/assets/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/assets/js/popper.min.js"/>"></script>
<script src="<c:url value="/assets/js/datatables.min.js"/>"></script>
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
											"order" : [ [ 1, 'desc' ] ], //sắp xếp giảm dần theo cột thứ 1
											stateSave : true
										});

					});
</script>
<script type="text/javascript">
	function setDefaultImage(img) {
		img.onerror = null; // Ngăn chặn việc gọi lặp lại
		img.src = "https://th.bing.com/th/id/OIP.xaADddZHWRoU3TbjEVGssQHaFj?rs=1&pid=ImgDetMain";
	}
</script>
<title></title>
</head>

<body style="overflow-x: hidden;">

	<div id="db-wrapper">
		<div>
			<%@ include file="/common/admin/Left.jsp"%></div>
		<main id="page-content">
			<div>
				<%@ include file="/common/admin/Header.jsp"%></div>
			<div style="padding-left: 24px; padding-right: 24px;">
				<decorator:body>
				</decorator:body>
			</div>
		</main>
	</div>


	<script src="../assets/libs/%40popperjs/core/dist/umd/popper.min.js"></script>
	<script src="../assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="../assets/libs/simplebar/dist/simplebar.min.js"></script>

	<!-- Theme JS -->
	<script src="../assets/js/theme.min.js"></script>

	<script src="../assets/libs/apexcharts/dist/apexcharts.min.js"></script>
	<script src="../assets/js/vendors/chart.js"></script>
	<script src="../assets/libs/flatpickr/dist/flatpickr.min.js"></script>
	<script src="../assets/js/vendors/flatpickr.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://cdn.ckeditor.com/ckeditor5/40.0.0/classic/ckeditor.js"></script>

	<script src="../assets/libs/%40popperjs/core/dist/umd/popper.min.js"></script>
	<script src="../assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="../assets/libs/simplebar/dist/simplebar.min.js"></script>

	<!-- Theme JS -->
	<script src="../assets/js/theme.min.js"></script>

	<script src="../assets/js/vendors/jquery.min.js"></script>
	<script src="../assets/libs/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="../assets/libs/datatables.net-bs5/js/dataTables.bootstrap5.min.js"></script>
	<script
		src="../assets/libs/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="../assets/libs/datatables.net-responsive-bs5/js/responsive.bootstrap5.min.js"></script>
	<script
		src="../assets/libs/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="../assets/libs/datatables.net-buttons-bs5/js/buttons.bootstrap5.min.js"></script>
	<script
		src="../assets/libs/datatables.net-buttons/js/buttons.html5.min.js"></script>
	<script
		src="../assets/libs/datatables.net-buttons/js/buttons.flash.min.js"></script>
	<script
		src="../assets/libs/datatables.net-buttons/js/buttons.print.min.js"></script>
	<script src="../assets/libs/pdfmake/build/pdfmake.min.js"></script>
	<script src="../assets/js/vendors/datatables.js"></script>
</body>
</html>