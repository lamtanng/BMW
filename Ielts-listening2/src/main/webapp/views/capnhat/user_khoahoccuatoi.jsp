<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>


<%
Long count = (Long) request.getAttribute("count");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ page import="javax.servlet.jsp.PageContext"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>

<body>
	<main>
		<section class="pt-5 pb-5">
			<div class="container">
				<!-- User info -->
				<div class="row align-items-center">
					<div class="col-xl-12 col-lg-12 col-md-12 col-12">
						<div class="rounded-top"
							style="background: url(../assets/images/background/profile-bg.jpg) no-repeat; background-size: cover; height: 100px"></div>
						<div
							class="card px-4 pt-2 pb-4 shadow-sm rounded-top-0 rounded-bottom-0 rounded-bottom-md-2">
							<div class="d-flex align-items-e nd justify-content-between">
								<div class="d-flex align-items-center">
									<div
										class="me-2 position-relative d-flex justify-content-end align-items-end mt-n5">
										<c:if test="${currentUser.image != null}">
											<img
												src='<c:url value='/image?fname=userAvatar/${ currentUser.image}'/>'
												class="avatar-xl rounded-circle border border-4 border-white"
												alt="avatar" />
										</c:if>
										<c:if test="${currentUser.image == null}">
											<img class="rounded-circle"
												style="width: 100px; height: 100px;"
												src='<c:url value = "https://res.cloudinary.com/dh6bfx865/image/upload/v1698335051/cuahangdientu/default_avatar.png"/>'
												alt="anh dai dien">
										</c:if>
									</div>
									<div class="lh-1">
										<h2 class="mb-0" name="userId" value="${user.userId}">
											<c:if test="${ currentUser.name != null}">
											${ currentUser.name}
										</c:if>
											<c:if test="${ currentUser.name == null}">
											${user.userId }
										</c:if>
											<a href="#" class="" data-bs-toggle="tooltip"
												data-placement="top" title="Beginner"> <svg width="16"
													height="16" viewBox="0 0 16 16" fill="none"
													xmlns="http://www.w3.org/2000/svg">
														<rect x="3"
														y="8" width="2" height="6" rx="1" fill="#754FFE"></rect>
														<rect x="7"
														y="5" width="2" height="9" rx="1" fill="#DBD8E9"></rect>
														<rect x="11"
														y="2" width="2" height="12" rx="1" fill="#DBD8E9"></rect>
													</svg>
											</a>
										</h2>
										<p class="mb-0 d-block">${user.email }</p>
									</div>
								</div>
			
							</div>
						</div>
					</div>
				</div>
	
				<div class="row mt-0 mt-md-4">
					<!-- Left sidebar -->
					<div class="col-lg-3 col-md-4 col-12">
						<!-- Side navbar -->
						<nav
							class="navbar navbar-expand-md shadow-sm mb-4 mb-lg-0 sidenav">
							<!-- Menu -->
							<a class="d-xl-none d-lg-none d-md-none text-inherit fw-bold"
								href="#">Menu</a>
							<!-- Button -->
							<button
								class="navbar-toggler d-md-none icon-shape icon-sm rounded bg-primary text-light"
								type="button" data-bs-toggle="collapse"
								data-bs-target="#sidenav" aria-controls="sidenav"
								aria-expanded="false" aria-label="Toggle navigation">
								<span class="fe fe-menu"></span>
							</button>
							<!-- Collapse navbar -->
							<div class="collapse navbar-collapse" id="sidenav">
								<div class="navbar-nav flex-column">
									<span class="navbar-header">CÁ NHÂN</span>
									<!-- List -->
									<ul class="list-unstyled ms-n2 mb-4">
										<!-- Nav item -->
										<li class="nav-item active"><a class="nav-link"
											href="khoahoccuatoi?userId=${user.userId}"> <i
												class="fe fe-calendar nav-icon"></i> Khóa học
										</a></li>
										<!-- Nav item -->
										<li class="nav-item"><a class="nav-link"
											href="thongkediem?userId=${user.userId}"> <i
												class="fe fe-calendar nav-icon"></i> Điểm số
										</a></li>

										<!-- Nav item -->
										
										<li class="nav-item"><a class="nav-link"
											href="myorder?userId=${user.userId}"> <i
												class="fe fe-calendar nav-icon"></i> Hóa đơn
										</a></li>

									</ul>
									<span class="navbar-header">TÀI  KHOẢN</span>
									<!-- List -->
									<ul class="list-unstyled ms-n2 mb-0">
										<!-- Nav item -->
										<li class="nav-item"><a class="nav-link"
											href="capnhattaikhoan?userId=${user.userId}"> <i
												class="fe fe-settings nav-icon"></i> Thông tin cá nhân
										</a></li>
										<!-- Nav item -->
										<li class="nav-item"><a class="nav-link"
											href="capnhatmatkhau?userId=${user.userId}"> <i
												class="fe fe-user nav-icon"></i> Bảo mật
										</a></li>

										<!-- Nav item -->
										<form action=logout method="post">
											<li class="nav-item">
												<button class="nav-link" style="width: 100%;"> 
													<i class="fe fe-power nav-icon"></i>
													Đăng xuất
												</button>
											</li>
										</form>
									</ul>
								</div>
							</div>
						</nav>
					</div>

					<div class="col-lg-9 col-md-8 col-12">
						<!-- Card -->
				
						<div class="card">
							<!-- Card header -->
							<div class="card-header">
								<h3 class="mb-0">Khóa học của tôi</h3>
								<p class="mb-0">Theo dõi quá trình học tập của bạn.</p>
							</div>
							<!-- Card body -->
							<form action="capnhatmatkhau?userId=${currentUser.userId}"
								method="post">
								<input name="userId" value="${currentUser.userId}" class="hide"
									style="display: none">
								<div class="card-body">
									<div class="row">
										<c:forEach var="i" items="${userCourse}">
				
											<div class="col-xl-3 col-md-6 col-12">
												<div class="card mb-4 card-hover border">
													<a href="course-detail?courseId=${i.courses.courseId }">
														<img onerror="setDefaultImage(this)"
														src="<c:url value='/image?fname=${folder}/${i.courses.image}'/>"
														alt="health" class="w-100 rounded-top-3" style="width: 100%;height: 180px;object-fit: cover;object-position: center;">
													</a>
													<div class="card-body">
														<h4 class="mb-3">
															<a href="course-detail?courseId=${i.courses.courseId }"
																class="text-inherit">${i.courses.courseName }</a>
															<%-- <a href="#!" class="text-inherit">${i.courses.lessons }</a> --%>
															<c:set var="countTren5" value="0" />
															<c:set var="sumScore" value="0" />
															<c:set var="score" value="0" />
															<c:forEach var="i" items="${i.courses.lessons}">
				
																<c:forEach var="j" items="${i.enrrolLesson}">
																	<c:set var="score" value="${j.score }" />
				
				
																	<c:choose>
																		<c:when test="${j.users.userId eq userId}">
																			<c:set var="sumScore" value="${sumScore + 1}" />
																		</c:when>
																	</c:choose>
				
																	<c:choose>
																		<c:when
																			test="${j.users.userId eq userId && j.score > 4.9}">
																			<c:set var="countTren5" value="${countTren5 + 1}" />
																		</c:when>
																	</c:choose>
																</c:forEach>
				
															</c:forEach>
														</h4>
				
														<c:set var="percen"
															value="${countTren5 * 100.0 / sumScore}" />
				
														<div class="progress mb-3" style="height: 8px;">
															<div class="progress-bar" role="progressbar"
																style="width: ${percen}%" aria-valuenow="50"
																aria-valuemin="0" aria-valuemax="100"></div>
														</div>
														<a class="btn btn-primary col-12" href="course-detail?courseId=${i.courses.courseId }" >
															Tiếp tục <span> 
															<svg
																	xmlns="http://www.w3.org/2000/svg" width="20"
																	height="20" fill="currentColor"
																	class="bi bi-arrow-right-short" viewBox="0 0 16 16">
															<path
																		fill-rule="evenodd"
																		d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z" />
														</svg>
														</span>
														</a>
													</div>
												</div>
											</div>
				
										</c:forEach>
										
									</div>
								</div>
							</form>
						</div>
					</div>

				</div>

			</div>

			</div>
		</section>
	</main>

	
</body>