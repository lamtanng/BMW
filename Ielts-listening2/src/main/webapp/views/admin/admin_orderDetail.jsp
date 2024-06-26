<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section class="container-fluid p-4">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-12">
				<!-- Page header -->
				<div class="border-bottom pb-3 mb-3">
					<div class="mb-2 mb-lg-0">
						<h1 class="mb-0 h2 fw-bold">Order Detail</h1>
					</div>
				</div>
			</div>
		</div>
		<!-- row -->
		<div class="row justify-content-center">
			<div class="col-lg-8 col-12">
				<!-- card -->
				<div class="card">
					<!-- card body -->
					<div class="card-body">
						<div class="mb-6">
							<!-- heading -->
							<h2 class="mb-0">Thank you for your order</h2>
							<p class="mb-0">We appreciate your order, were currently
								processing it. So hard tight and we will send you confirmation
								very soon!</p>
						</div>
						<div>
							<div class="border-bottom mb-3 pb-3">
								<!-- text -->
								<div class="d-flex align-items-center">
									<h4 class="mb-0">Order tracking number</h4>
									<a href="#" class="ms-2 fw-semibold">${order.paymentId}</a>

								</div>
								<div class="mb-0 d-flex align-items-center ">
									<a href="#" class=" fw-semibold">Date time:
										${order.dateBuy} </a>
								</div>

							</div>
							<c:set var="totalCount" value="0" />
							<!-- Tăng giá trị của biến bên ngoài mỗi lần lặp -->

							<c:forEach var="course" items="${list_oddetail}">
								<c:set var="totalCount"
									value="${totalCount + course.payment.cost}" />
								<div class="row justify-content-between mb-5 mt-2">
									<!-- col -->
									<div class="col-lg-8 col-12">
										<div class="d-md-flex">
											<!-- img -->
											<div>
												<img src="../assets/images/ecommerce/ecommerce-img-1.jpg"
													alt="" class="img-4by3-xl rounded" />
											</div>
											<!-- text -->
											<div class="ms-md-4 mt-2">
												<h5 class="mb-1">${course.userCourse.courses.courseName}</h5>
												<span> Color: <span class="text-dark">Orange</span> ,
													Size: <span class="text-dark">10</span>
												</span>
											</div>
										</div>
									</div>
									<!-- price -->
									<div class="col-lg-4 col-12">
										<div class="d-flex justify-content-end mt-2">
											<fmt:formatNumber value="${course.payment.cost}"
												pattern="###,### VNĐ" />
										</div>
									</div>
								</div>
							</c:forEach>
							<hr class="my-3" />
							<ul class="list-unstyled mb-0">

								<li class="d-flex justify-content-between mb-2 mt-3"><span>Giảm
										giá</span> <span class="text-dark fw-medium">0 VNĐ</span></li>
								<li class="d-flex justify-content-between mb-2"><span>Thành
										tiền</span> <span class="text-success fw-medium"> <fmt:formatNumber
											value="${totalCount}" pattern="###,### VNĐ" />
								</span></li>

								<li class="d-flex justify-content-between mb-2"><span>Phương
										thức thanh toán </span> <span class="text-secondary fw-medium">
										Chuyển khoản ngân hàng </span></li>
							</ul>
							<hr class="my-3" />
							<div>
								<form id="buyCourseForm" action="confirmCheckout" method="post">
									<button type="button" style="float: right;"
										class="btn btn-primary" onclick="redirectToCoursePage()">
										Mua khóa học mới</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<script>
		function redirectToCoursePage() {
			window.location.href = '/Ielts-listening2/user/course';
		}
	</script>
</body>
</html>