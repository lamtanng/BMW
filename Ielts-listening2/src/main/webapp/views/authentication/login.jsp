<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<!-- Add Bootstrap CSS Link -->
<link href="../../assets/fonts/feather/feather.css" rel="stylesheet" />
<style>
</style>
</head>
<body>
	<div class="container-fluid  bg-light">
		<div class="row justify-content-start pt-5 pt-lg-0">
			<div class="col-lg-5 col-xl-6 col-xxl-5 text-left py-5 p-4">
				<div class="col-xl-7 col-xxl-6">
					<img class="img-fluid"
						src="https://prepedu.com/imgs/login/decor.png">
				</div>
				<h3 class="fw-bold text-primary fs-4">Miễn Phí Kiểm Tra Trình
					Độ</h3>
				<p class="col-xxl-9 col-xxl-9 mt-3 text-secondary fs-5">
					Kiểm tra trình độ hoàn toàn miễn phí.<br>Chỉ cần 1 tài khoản
					duy nhất, bạn có thể thực hiện các bài<br>Kiểm Tra Đầu Vào để
					xác định trình độ nhanh chóng.
				</p>
			</div>
			<div class="col-lg-2 col-xl-1 col-xxl-1"></div>
			<div class="col-lg-5 col-xl-5 px-4 py-5">
				<div class="bg-white p-4 rounded-3">
					<div class="login-box">
						<div class="text-center">
							<p class="fw-bold fs-5 mb-4">Đăng Nhập</p>
						</div>
						<div class="username-pwd-form">
							<form action="authentication-login" method="post">
								<div class="mb-4">
									<label for="username" class="form-label mb-2 text-secondary">
										Username <span class="text-danger">*</span>
									</label>
									<div class="input-group">
										<input id="username" name="userName" class="form-control"
											type="text" placeholder="Nhập username">
									</div>
								</div>
								<input type="hidden" id="csrfToken" name="csrfToken" value="">
								<div class="mb-4">
									<label for="pwd" class="form-label mb-2 text-secondary">
										Mật khẩu <span class="text-danger">*</span>
									</label>
									<div class="input-group">
										<input id="pwd" class="form-control" name="passWord"
											type="password" placeholder="Nhập mật khẩu">
										<button onclick="handleToggleShowPassword()"
											class="bg-white border" type="button">
											<div style="width: 35px">
												<i id="icon__show" class="fe fe-eye"></i> <i
													style="display: none" id="icon__hide" class="fe fe-eye-off"></i>
											</div>
										</button>
									</div>
								</div>
								<div class="mb-4">
									<button class="btn btn-primary fw-bold w-100" type="submit">Đăng
										nhập</button>
								</div>
							</form>
						</div>
						<div class="d-flex justify-content-between align-items-center">
							<a href="authentication-forgotpassword"
								class=" text-primaryfw-bold text-decoration-underline"> Quên
								mật khẩu ? </a> <span class="text-primary">Chưa có tài khoản?
								<a href="authentication-signup"
								class="fw-bold text-decoration-underline">Đăng ký</a>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script>
		function generateToken(length) {
			const charset = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
			let token = '';
			for (let i = 0; i < length; i++) {
				const randomIndex = Math.floor(Math.random() * charset.length);
				token += charset[randomIndex];
			}
			return token;
		}

		// Retrieve CSRF token from cookie and set it as the value of the hidden input field
		const csrfTokenGlobal = generateToken(16);
		console.log(csrfTokenGlobal);
		if (csrfTokenGlobal) {
			document.getElementById('csrfToken').value = csrfTokenGlobal;
		}

		document.addEventListener("DOMContentLoaded", function() {
			const form = document.querySelector('form');

			form.addEventListener('submit', function(event) {
				const csrfToken = document.getElementById('csrfToken').value;
				console.log(csrfTokenGlobal, csrfToken)
				if (!isValidCsrfToken(csrfToken)) {
					event.preventDefault();
					console.error('CSRF token is invalid');

				} else {
					console.log('CSRF token is valid');
					// Proceed with form submission
					form.submit();
				}
			});
			function isValidCsrfToken(token) {
				return token === csrfTokenGlobal
			}
		});

		const message = "${message}";
		if (message && message.trim() !== "") {
			// If the message is not empty, show it as a toast
			showToast(message);
		}

		function handleToggleShowPassword() {
			const passwordInput = document.getElementById("pwd");
			const showBtn = document.getElementById("icon__show");
			const hideBtn = document.getElementById("icon__hide");
			if (passwordInput.type === "password") {
				passwordInput.type = "text"; // Change the input type to text to reveal the password
				showBtn.style.display = "none"; // Hide the show button
				hideBtn.style.display = "block"; // Show the hide button

			} else {
				passwordInput.type = "password"; // Change it back to password to hide the password
				showBtn.style.display = "block"; // Show the show button
				hideBtn.style.display = "none"; // Hide the hide button
			}
		}
		function showToast(message) {
			// Tạo một toast message
			const toast = document.createElement("div");
			toast.classList.add("show", "toast", "position-fixed", "top-0",
					"end-0", "end-0");
			toast.setAttribute("role", "alert");
			toast.setAttribute("aria-live", "assertive");
			toast.setAttribute("aria-atomic", "true");
			toast.setAttribute("style", "border-left:4px solid red")

			// Tạo nội dung toast
			const toastBody = document.createElement("div");
			toastBody.classList.add("toast-body");
			toastBody.innerText = message;

			// Thêm nội dung vào toast và toast vào trang
			toast.appendChild(toastBody);
			document.body.appendChild(toast);

			// Hiển thị toast
			const bootstrapToast = new bootstrap.Toast(toast);
			bootstrapToast.show();
		}
	</script>
</body>
</html>
