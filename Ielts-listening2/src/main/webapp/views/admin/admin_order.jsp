<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
	<div class="header">
		<!-- navbar -->
	</div>
	<!-- Container fluid -->
	<section class="container-fluid p-4">
		<div class="row">
			
		</div>

		<div class="row">
			<!-- basic table -->
			<div class="col-md-12 col-12 mb-5">
				<div class="card">
					<!-- card header  -->
					<div class="card-header">
						<h4 class="mb-1">Hóa đơn</h4>
						<p class="mb-0">Quản lý các hóa dơn một cách hiệu qủa !</p>
					</div>
					<!-- table  -->
					<div class="card-body">
						<div class="table-card">
							<table id="dataTableBasic" class="table table-hover"
								style="width: 100%">
								<thead class="table-light">
									<tr>
										<th>ID hóa đơn</th>
										<th>Người mua</th>
										<th>số lượng</th>
										<th>trạng thái</th>
										<th>Ngày</th>
										<th>Tổng</th>
										<th>Hành động</th>
									</tr>

								</thead>
								<tbody>
									<c:forEach var="i" items="${list}">
										<tr>
									
											<td><a href="od-detail?id=${i.paymentId}">${i.paymentId}</a></td>

											<td>${i.users.account.userName }</td>
											<td>1 Items</td>
											<td><span class="badge text-success bg-light-success">Paid</span></td>
											<td>${i.dateBuy}</td>
											<td>${i.cost}</td>
											<td><span class="dropdown dropstart"> <a
													class="btn-icon btn btn-ghost btn-sm rounded-circle"
													href="#" role="button" id="orderDropdownTwo"
													data-bs-toggle="dropdown" data-bs-offset="-20,20"
													aria-expanded="false"> <i class="fe fe-more-vertical"></i>
												</a> <span class="dropdown-menu"
													aria-labelledby="orderDropdownTwo"> <span
														class="dropdown-header">Settings</span> <a
														class="dropdown-item" href="#"> <i
															class="fe fe-edit dropdown-item-icon"></i> Edit
													</a> <a class="dropdown-item" href="#"> <i
															class="fe fe-mail dropdown-item-icon"></i> Invite
													</a> <a class="dropdown-item" href="#"> <i
															class="fe fe-trash dropdown-item-icon"></i> Delete
													</a>
												</span>
											</span></td>
										</tr>

									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<!-- basic table -->
			<div class="col-md-12 col-12 mb-5">
				<div class="card">
					<!-- card header  -->

				</div>
			</div>
		</div>
	</section>
</body>

