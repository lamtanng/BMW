package hcmute.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.module.ModuleDescriptor.Requires;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JPAConfig.JPAConfig;
import hcmute.entity.Account;
import hcmute.entity.Cart;
import hcmute.entity.User;
import hcmute.services.AccountServiceImpl;
import hcmute.services.CartServiceImpl;
import hcmute.services.IAccountServices;
import hcmute.services.ICartService;
import hcmute.services.IUserService;
import hcmute.services.UserServiceImpl;
import hcmute.utils.Email;
import hcmute.utils.compositeId.PasswordEncryptor;

@WebServlet(urlPatterns = { "/authentication-login", "/authentication-signup", "/authentication-forgotpassword",
		"/authentication-verifycode", "/authentication-resent", "/user/logout", "/admin/logout", "/waiting" })
public class AuthenticationControllers extends HttpServlet {

	IAccountServices accountService = new AccountServiceImpl();
	IUserService userService = new UserServiceImpl();
	ICartService cartService = new CartServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("X-Frame-Options", "DENY");
		resp.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
		String url = req.getRequestURI().toString();
		if (url.contains("login")) {
			getLogin(req, resp);
		} else if (url.contains("signup")) {
			RequestDispatcher rd = req.getRequestDispatcher("/views/authentication/signUp.jsp");
			rd.forward(req, resp);
		} else if (url.contains("waiting")) {
			HttpSession session = req.getSession();
			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				String role = (String) session.getAttribute("role");
				@SuppressWarnings("unchecked")
				List<Cart> carts = (List<Cart>) session.getAttribute("cart");
				if (role.equals("admin")) {
					req.setAttribute("user", user);
					resp.sendRedirect(req.getContextPath() + "/admin/analytics");
				} else {
					req.setAttribute("cart", carts);
					req.setAttribute("user", user);
					resp.sendRedirect(req.getContextPath() + "/user/home");
				}

			} else
				req.getRequestDispatcher("views/authentication/login.jsp").forward(req, resp);
		} else if (url.contains("forgotpassword")) {
			req.getRequestDispatcher("views/authentication/forgotpassword.jsp").forward(req, resp);
		} else if (url.contains("verifycode")) {
			req.getRequestDispatcher("views/authentication/verifycode.jsp").forward(req, resp);
		} else if (url.contains("resent")) {
			getResent(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("X-Frame-Options", "DENY");
		resp.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
		String url = req.getRequestURI().toString();
		if (url.contains("signup")) {
			SignUp(req, resp);
		} else if (url.contains("login")) {
			postLogin(req, resp);
		} else if (url.contains("logout")) {
			HttpSession session = req.getSession();
			session.removeAttribute("user");
			session.removeAttribute("role");
			session.removeAttribute("cart");
//			Cookie[] cookies = req.getCookies();
//
//			if (cookies != null) {
//				Cookie cookie1 = new Cookie("username", "");
//				cookie1.setMaxAge(0);
//				cookie1.setPath("/");
//
//				cookie1.setSecure(true);	
//				resp.addCookie(cookie1);
//				Cookie cookie2 = new Cookie("email", "");
//				cookie2.setMaxAge(0);
//				cookie2.setPath("/");
//				cookie2.setSecure(true);
//				resp.addCookie(cookie2);
//				Cookie cookie3 = new Cookie("code", "");
//				cookie3.setMaxAge(0);
//				cookie3.setPath("/");
//				cookie3.setSecure(true);
//				resp.addCookie(cookie3);
//				Cookie cookie4 = new Cookie("password", "");
//				cookie4.setMaxAge(0);
//				cookie4.setPath("/");
//				cookie4.setSecure(true);
//				resp.addCookie(cookie4);
//				Cookie cookie5 = new Cookie("createCodeAt", "");
//				cookie5.setMaxAge(0);
//				cookie5.setPath("/");
//				cookie5.setSecure(true);
//				resp.addCookie(cookie5);
//				Cookie cookie6 = new Cookie("turn", "");
//				cookie6.setMaxAge(0);
//				cookie6.setPath("/");
//				cookie6.setSecure(true);
//				resp.addCookie(cookie6);
//			}
//			resp.sendRedirect(req.getContextPath() + "/user/home");

			Cookie[] cookies = req.getCookies();

			if (cookies != null) {
				Cookie cookie1 = new Cookie("username", "");
				cookie1.setMaxAge(0);
				cookie1.setPath("/");
				cookie1.setHttpOnly(true);
				cookie1.setSecure(true);
				resp.addCookie(cookie1);
				Cookie cookie2 = new Cookie("email", "");
				cookie2.setMaxAge(0);
				cookie2.setPath("/");
				cookie2.setHttpOnly(true);
				cookie2.setSecure(true);
				resp.addCookie(cookie2);
				Cookie cookie3 = new Cookie("code", "");
				cookie3.setMaxAge(0);
				cookie3.setPath("/");
				cookie3.setHttpOnly(true);
				cookie3.setSecure(true);
				resp.addCookie(cookie3);
				Cookie cookie4 = new Cookie("password", "");
				cookie4.setMaxAge(0);
				cookie4.setPath("/");
				cookie4.setHttpOnly(true);
				cookie4.setSecure(true);
				resp.addCookie(cookie4);
				Cookie cookie5 = new Cookie("createCodeAt", "");
				cookie5.setMaxAge(0);
				cookie5.setPath("/");
				cookie5.setHttpOnly(true);
				cookie5.setSecure(true);
				resp.addCookie(cookie5);
				Cookie cookie6 = new Cookie("turn", "");
				cookie6.setMaxAge(0);
				cookie6.setPath("/");
				cookie6.setHttpOnly(true);
				cookie6.setSecure(true);
				resp.addCookie(cookie6);
			}

			resp.sendRedirect(req.getContextPath() + "/user/home");

		} else if (url.contains("forgotpassword")) {
			postForgotPassword(req, resp);
		} else if (url.contains("verifycode")) {
			postVerifyCode(req, resp);
		}
	}

	private void SignUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			Email sm = new Email();

			// get the 6-digit code
			String code = sm.getRandom();
			String email = req.getParameter("email");
			if (email.contains(" ")) {
				req.setAttribute("message",
						"Xin lỗi, Email chỉ được phép sử dụng các chữ cái (a-z), số (0-9), và dấu chấm (.).");
				RequestDispatcher rd = req.getRequestDispatcher("/views/authentication/signUp.jsp");
				rd.forward(req, resp);
				return;
			}
			String userName = req.getParameter("userName");
			if (userName.contains(" ")) {
				req.setAttribute("message", "username nhập không được phép có dấu cách");
				RequestDispatcher rd = req.getRequestDispatcher("/views/authentication/signUp.jsp");
				rd.forward(req, resp);
				return;
			}
			String passWord;
			if (req.getParameter("passWord").contains(" ")) {
				req.setAttribute("message", "Mật khẩu không được phép có dấu cách");
				RequestDispatcher rd = req.getRequestDispatcher("/views/authentication/signUp.jsp");
				rd.forward(req, resp);
				return;
			} else
				passWord = PasswordEncryptor.encryptPassword(req.getParameter("passWord"));
			/*
			 * if (accountService.checkExistEmail(email)) { req.setAttribute("message",
			 * "Email đã tồn tại trong hệ thống!"); RequestDispatcher rd =
			 * req.getRequestDispatcher("/views/authentication/signUp.jsp"); rd.forward(req,
			 * resp); return; }
			 */
			if (accountService.checkExistUsername(userName)) {
				req.setAttribute("message", "Username đã tồn tại trong hệ thống!");
				RequestDispatcher rd = req.getRequestDispatcher("/views/authentication/signUp.jsp");
				rd.forward(req, resp);
				return;
			}

			boolean test = sm.sendCodeEmail(email, code);
			if (!test) {
				req.setAttribute("message", "Lỗi gửi email!");
				RequestDispatcher rd = req.getRequestDispatcher("/views/authentication/signUp.jsp");
				rd.forward(req, resp);
				return;
			}
//			int minutes = 15;
//			Cookie cookie1 = new Cookie("username", userName);
//			cookie1.setMaxAge(minutes * 60);
//			cookie1.setHttpOnly(true);
//			cookie1.setPath("/");
//
//			resp.addCookie(cookie1);
//
//			Cookie cookie2 = new Cookie("email", email);
//			cookie2.setMaxAge(minutes * 60);
//			cookie2.setHttpOnly(true);
//			cookie2.setPath("/");
//			resp.addCookie(cookie2);
//
//			Cookie cookie3 = new Cookie("code", PasswordEncryptor.encryptPassword(code));
//			cookie3.setMaxAge(minutes * 60);
//			cookie3.setHttpOnly(true);
//			cookie3.setPath("/");
//			resp.addCookie(cookie3);
//
//			Cookie cookie4 = new Cookie("password", passWord);
//			cookie4.setMaxAge(minutes * 60);
//			cookie4.setHttpOnly(true);
//			cookie4.setPath("/");
//			resp.addCookie(cookie4);

			int minutes = 15;
			String sameSiteAttribute = "Lax"; // Hoặc "Strict" hoặc "Lax"

			Cookie cookie1 = new Cookie("username", userName);
			cookie1.setMaxAge(minutes * 60);
			cookie1.setHttpOnly(true);
			cookie1.setPath("/");
			cookie1.setSecure(true);
			resp.addCookie(cookie1);

			Cookie cookie2 = new Cookie("email", email);
			cookie2.setMaxAge(minutes * 60);
			cookie2.setHttpOnly(true);
			cookie2.setPath("/");
			cookie2.setSecure(true);
			resp.addCookie(cookie2);

			Cookie cookie3 = new Cookie("code", PasswordEncryptor.encryptPassword(code));
			cookie3.setMaxAge(minutes * 60);
			cookie3.setHttpOnly(true);
			cookie3.setPath("/");
			cookie3.setSecure(true);
			resp.addCookie(cookie3);

			Cookie cookie4 = new Cookie("password", passWord);
			cookie4.setMaxAge(minutes * 60);
			cookie4.setHttpOnly(true);
			cookie4.setSecure(true);
			cookie4.setPath("/");
			cookie4.setSecure(true); // Điều này là tùy chọn, nếu bạn muốn sử dụng Secure attribute

			String cookie4Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; SameSite=%s",
					cookie4.getName(), cookie4.getValue(), cookie4.getMaxAge(), sameSiteAttribute);
			resp.addHeader("Set-Cookie", cookie4Header);
			resp.addCookie(cookie4);

			long createCodeAt = 0;
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("createCodeAt")) {
						createCodeAt = Long.parseLong(cookie.getValue());
					}
				}
			}
			if (createCodeAt == 0) {
				createCodeAt = new Date().getTime();
//				Cookie cookie5 = new Cookie("createCodeAt", String.valueOf(createCodeAt));
//				cookie5.setHttpOnly(true);
//				cookie5.setPath("/");
//				cookie5.setMaxAge(minutes * 60);
//				cookie5.setSecure(true);
//				resp.addCookie(cookie5);
				Cookie cookie5 = new Cookie("createCodeAt", String.valueOf(createCodeAt));
				cookie5.setMaxAge(minutes * 60);
				cookie5.setHttpOnly(true);
				cookie5.setPath("/");
				cookie5.setSecure(true); // Điều này là tùy chọn, nếu bạn muốn sử dụng Secure attribute

				String cookie5Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; SameSite=%s",
						cookie5.getName(), cookie5.getValue(), cookie5.getMaxAge(), sameSiteAttribute);
				resp.addHeader("Set-Cookie", cookie5Header);
				resp.addCookie(cookie5);

			}

			String turn = "5";
//			Cookie cookieTurn = new Cookie("turn", turn);
//			cookieTurn.setSecure(true);
//			cookieTurn.setMaxAge(minutes * 60);
//			cookieTurn.setHttpOnly(true);
//			cookieTurn.setPath("/");
//			resp.addCookie(cookieTurn);
			Cookie cookieTurn = new Cookie("turn", turn);
			cookieTurn.setMaxAge(minutes * 60);
			cookieTurn.setHttpOnly(true);
			cookieTurn.setPath("/");
			cookieTurn.setSecure(true); // Điều này là tùy chọn, nếu bạn muốn sử dụng Secure attribute

			String cookieTurnHeader = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; SameSite=%s",
					cookieTurn.getName(), cookieTurn.getValue(), cookieTurn.getMaxAge(), sameSiteAttribute);
			resp.addHeader("Set-Cookie", cookieTurnHeader);
			resp.addCookie(cookieTurn);

			resp.sendRedirect(req.getContextPath() + "/authentication-verifycode");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Eror: " + e.getMessage());
		}

	}

	public void postLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IAccountServices accountService = new AccountServiceImpl();

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Account account = new Account();
		String userName = req.getParameter("userName");
		String passWord = PasswordEncryptor.encryptPassword(req.getParameter("passWord"));
		account.setUserName(userName);
		account.setPassWord(passWord);
		if (userName.isEmpty() || passWord.isEmpty()) {
			req.getRequestDispatcher("views/authentication/login.jsp").forward(req, resp);
			return;
		}

		Account acc = accountService.findByUserName(userName);
		System.out.print("errrer" + acc);
		if ((acc != null)) {
			if (acc.getRole() != "admin")
				account.setRole("user");
			else
				account.setRole("admin");

			User user = accountService.Login(account);

			if (user == null) {
				req.setAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng");
				RequestDispatcher rd = req.getRequestDispatcher("/views/authentication/login.jsp");
				rd.forward(req, resp);
			} else {
				List<Cart> carts = cartService.findByUserId(user.getUserId());
				List<Cart> finalCarts = new ArrayList<Cart>();
				for (Cart cart2 : carts) {
					if (cart2.isBuy() == false)
						finalCarts.add(cart2);
				}
				HttpSession session = req.getSession(true);
				session.setAttribute("user", user);
				session.setAttribute("role", acc.getRole());
				session.setAttribute("cart", finalCarts);
				resp.sendRedirect(req.getContextPath() + "/waiting");
				return;
			}
		} else {
			req.setAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng");
			RequestDispatcher rd = req.getRequestDispatcher("/views/authentication/login.jsp");
			rd.forward(req, resp);
		}

	}

	private void getLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath() + "/waiting");
			return;
		}

		req.getRequestDispatcher("views/authentication/login.jsp").forward(req, resp);

	}

	private void postForgotPassword(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String email = req.getParameter("email");
		User user = accountService.getUserByEmail(email);

		if (user == null) {
			req.setAttribute("message", "Email không tồn tại trong hệ thống!");
			req.getRequestDispatcher("views/authentication/forgotpassword.jsp").forward(req, resp);
			return;
		}

		Email sm = new Email();

		boolean test = sm.sendPasswordEmail(user);

		if (test) {
			req.setAttribute("message",
					"Mật khẩu đã được gửi về email. \nVui lòng kiểm tra email để nhận mật khẩu nhé!");
			req.getRequestDispatcher("views/authentication/login.jsp").forward(req, resp);
		} else {
			req.setAttribute("message", "Lỗi gửi mail!");
			resp.sendRedirect(req.getContextPath() + "/authentication-forgotpassword");
		}

	}

	private void postVerifyCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String otp1 = req.getParameter("otp1");
		String otp2 = req.getParameter("otp2");
		String otp3 = req.getParameter("otp3");
		String otp4 = req.getParameter("otp4");
		String otp5 = req.getParameter("otp5");
		String otp6 = req.getParameter("otp6");

		String otp = PasswordEncryptor.encryptPassword(otp1 + otp2 + otp3 + otp4 + otp5 + otp6);

		String username = "";
		String email = "";
		String code = "";
		String password = "";
		int turn = 0;
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					username = cookie.getValue();
				}
				if (cookie.getName().equals("email")) {
					email = cookie.getValue();
				}
				if (cookie.getName().equals("code")) {
					code = cookie.getValue();
				}
				if (cookie.getName().equals("password")) {
					password = cookie.getValue();
				}
				if (cookie.getName().equals("turn")) {
					turn = Integer.parseInt(cookie.getValue());
				}
			}
		}
		if (turn <= 0) {
//			Cookie cookie1 = new Cookie("username", "");
//			cookie1.setHttpOnly(true);
//			cookie1.setMaxAge(0);
//			cookie1.setPath("/");
//			cookie1.setSecure(true);
//			resp.addCookie(cookie1);
//			Cookie cookie2 = new Cookie("email", "");
//			cookie2.setHttpOnly(true);
//			cookie2.setMaxAge(0);
//			cookie2.setPath("/");
//			cookie2.setSecure(true);
//			resp.addCookie(cookie2);
//			Cookie cookie3 = new Cookie("code", "");
//			cookie3.setHttpOnly(true);
//			cookie3.setMaxAge(0);
//			cookie3.setPath("/");
//			cookie3.setSecure(true);
//			resp.addCookie(cookie3);
//			Cookie cookie4 = new Cookie("password", "");
//			cookie4.setHttpOnly(true);
//			cookie4.setMaxAge(0);
//			cookie4.setPath("/");
//			cookie4.setSecure(true);
//			resp.addCookie(cookie4);
//			Cookie cookie5 = new Cookie("createCodeAt", "");
//			cookie5.setHttpOnly(true);
//			cookie5.setMaxAge(0);
//			cookie5.setPath("/");
//			cookie5.setSecure(true);
//			resp.addCookie(cookie5);
//			Cookie cookie6 = new Cookie("turn", "");
//			cookie6.setHttpOnly(true);
//			cookie6.setMaxAge(0);
//			cookie6.setPath("/");
//			cookie6.setSecure(true);
//			resp.addCookie(cookie6);

			String sameSiteAttribute = "SameSite=Lax"; // Hoặc "SameSite=Lax", "SameSite=None" tùy thuộc vào yêu cầu
															// của bạn

			Cookie cookie1 = new Cookie("username", "");
			cookie1.setHttpOnly(true);
			cookie1.setMaxAge(0);
			cookie1.setPath("/");
			cookie1.setSecure(true);

			String cookie1Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; %s", cookie1.getName(),
					cookie1.getValue(), cookie1.getMaxAge(), sameSiteAttribute);
			resp.addHeader("Set-Cookie", cookie1Header);
			resp.addCookie(cookie1);

			Cookie cookie2 = new Cookie("email", "");
			cookie2.setHttpOnly(true);
			cookie2.setMaxAge(0);
			cookie2.setPath("/");
			cookie2.setSecure(true);

			String cookie2Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; %s", cookie2.getName(),
					cookie2.getValue(), cookie2.getMaxAge(), sameSiteAttribute);
			resp.addHeader("Set-Cookie", cookie2Header);
			resp.addCookie(cookie2);

			Cookie cookie3 = new Cookie("code", "");
			cookie3.setHttpOnly(true);
			cookie3.setMaxAge(0);
			cookie3.setPath("/");
			cookie3.setSecure(true);

			String cookie3Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; %s", cookie3.getName(),
					cookie3.getValue(), cookie3.getMaxAge(), sameSiteAttribute);
			resp.addHeader("Set-Cookie", cookie3Header);
			resp.addCookie(cookie3);

			Cookie cookie4 = new Cookie("password", "");
			cookie4.setHttpOnly(true);
			cookie4.setMaxAge(0);
			cookie4.setPath("/");
			cookie4.setSecure(true);

			String cookie4Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; %s", cookie4.getName(),
					cookie4.getValue(), cookie4.getMaxAge(), sameSiteAttribute);
			resp.addHeader("Set-Cookie", cookie4Header);
			resp.addCookie(cookie4);

			Cookie cookie5 = new Cookie("createCodeAt", "");
			cookie5.setHttpOnly(true);
			cookie5.setMaxAge(0);
			cookie5.setPath("/");
			cookie5.setSecure(true);

			String cookie5Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; %s", cookie5.getName(),
					cookie5.getValue(), cookie5.getMaxAge(), sameSiteAttribute);
			resp.addHeader("Set-Cookie", cookie5Header);
			resp.addCookie(cookie5);

			Cookie cookie6 = new Cookie("turn", "");
			cookie6.setHttpOnly(true);
			cookie6.setMaxAge(0);
			cookie6.setPath("/");
			cookie6.setSecure(true);

			String cookie6Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; %s", cookie6.getName(),
					cookie6.getValue(), cookie6.getMaxAge(), sameSiteAttribute);
			resp.addHeader("Set-Cookie", cookie6Header);
			resp.addCookie(cookie6);

			req.setAttribute("message", "tạo tài khoản không thành công do otp nhập sai quá 5 lần!");
			RequestDispatcher rd = req.getRequestDispatcher("/views/authentication/signUp.jsp");
			rd.forward(req, resp);
			return;
		}

		if (otp.equals(code)) {
//			Cookie cookie1 = new Cookie("username", "");
//			cookie1.setMaxAge(0);
//			cookie1.setHttpOnly(true);
//			cookie1.setPath("/");
//			resp.addCookie(cookie1);
			String sameSiteAttribute = "SameSite=Lax"; // Hoặc "SameSite=Lax", "SameSite=None" tùy thuộc vào yêu cầu
															// của bạn

			Cookie cookie1 = new Cookie("username", "");
			cookie1.setMaxAge(0);
			cookie1.setHttpOnly(true);
			cookie1.setSecure(true);
			cookie1.setPath("/");
			cookie1.setSecure(true);

			String cookie1Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; %s", cookie1.getName(),
					cookie1.getValue(), cookie1.getMaxAge(), sameSiteAttribute);
			resp.addHeader("Set-Cookie", cookie1Header);
			resp.addCookie(cookie1);

//			Cookie cookie2 = new Cookie("email", "");
//			cookie2.setMaxAge(0);
//			cookie2.setHttpOnly(true);
//			cookie2.setPath("/");
//			resp.addCookie(cookie2);

			Cookie cookie2 = new Cookie("email", "");
			cookie2.setMaxAge(0);
			cookie2.setHttpOnly(true);
			cookie2.setSecure(true);
			cookie2.setPath("/");
			cookie2.setSecure(true);

			String cookie2Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; %s", cookie2.getName(),
					cookie2.getValue(), cookie2.getMaxAge(), sameSiteAttribute);
			resp.addHeader("Set-Cookie", cookie2Header);
			resp.addCookie(cookie2);

//			Cookie cookie3 = new Cookie("code", "");
//			cookie3.setMaxAge(0);
//			cookie3.setHttpOnly(true);
//			cookie3.setPath("/");
//			resp.addCookie(cookie3);

			Cookie cookie3 = new Cookie("code", "");
			cookie3.setMaxAge(0);
			cookie3.setHttpOnly(true);
			cookie3.setSecure(true);
			cookie3.setPath("/");
			cookie3.setSecure(true);

			String cookie3Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; %s", cookie3.getName(),
					cookie3.getValue(), cookie3.getMaxAge(), sameSiteAttribute);
			resp.addHeader("Set-Cookie", cookie3Header);
			resp.addCookie(cookie3);

//			Cookie cookie4 = new Cookie("password", "");
//			cookie4.setMaxAge(0);
//			cookie4.setHttpOnly(true);
//			cookie4.setPath("/");
//			resp.addCookie(cookie4);

			Cookie cookie4 = new Cookie("password", "");
			cookie4.setMaxAge(0);
			cookie4.setHttpOnly(true);
			cookie4.setSecure(true);
			cookie4.setPath("/");
			cookie4.setSecure(true);

			String cookie4Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; %s", cookie4.getName(),
					cookie4.getValue(), cookie4.getMaxAge(), sameSiteAttribute);
			resp.addHeader("Set-Cookie", cookie4Header);
			resp.addCookie(cookie4);

//			Cookie cookie5 = new Cookie("createCodeAt", "");
//			cookie5.setMaxAge(0);
//			cookie5.setHttpOnly(true);
//			cookie5.setPath("/");
//			resp.addCookie(cookie5);

			Cookie cookie5 = new Cookie("createCodeAt", "");
			cookie5.setMaxAge(0);
			cookie5.setHttpOnly(true);
			cookie5.setSecure(true);
			cookie5.setPath("/");
			cookie5.setSecure(true);

			String cookie5Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; %s", cookie5.getName(),
					cookie5.getValue(), cookie5.getMaxAge(), sameSiteAttribute);
			resp.addHeader("Set-Cookie", cookie5Header);
			resp.addCookie(cookie5);

			Account account = new Account();
			account.setUserName(username);
			account.setPassWord(password);
			account.setRole("user");

			String res = accountService.SignUp(account);
			account = accountService.findByID(username);
			User user = account.getUsers();
			user.setEmail(email);
			userService.update(user);

			if (res != "Success") {
				req.setAttribute("message", res);
				RequestDispatcher rd = req.getRequestDispatcher("/views/authentication/signUp.jsp");
				rd.forward(req, resp);

			}

			resp.sendRedirect(req.getContextPath() + "/authentication-login");
			req.setAttribute("message", "Đã thêm thành công");
		} else {

			turn = turn - 1;
			Cookie cookieTurn = new Cookie("turn", String.valueOf(turn));
			cookieTurn.setMaxAge(15 * 60); //
			cookieTurn.setHttpOnly(true);
			cookieTurn.setPath("/");
			cookieTurn.setSecure(true);
			resp.addCookie(cookieTurn);

			req.setAttribute("message", "Mã OTP chưa chính xác. Vui lòng nhập lại");
			req.getRequestDispatcher("views/authentication/verifycode.jsp").forward(req, resp);
		}
	}

	private void getResent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Email sm = new Email();

		// get the 6-digit code
		String code = sm.getRandom();
		Cookie[] cookies = req.getCookies();
		long time = 0;
		String email = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("createCodeAt")) {
					time = Long.parseLong(cookie.getValue());
				}
				if (cookie.getName().equals("email")) {
					email = cookie.getValue();
				}
			}
		}
		boolean test = sm.sendCodeEmail(email, code);
		if (!test) {
			req.setAttribute("message", "Lỗi gửi email!");
			req.getRequestDispatcher("views/authentication/verifycode.jsp").forward(req, resp);
			return;
		}
//
//		Cookie cookie3 = new Cookie("code", PasswordEncryptor.encryptPassword(code));
//		int age = (int) ((new Date().getTime() - time) / 1000);
//		cookie3.setMaxAge(15 * 60 - age);
//		cookie3.setHttpOnly(true);
//		cookie3.setHttpOnly(true);
//		cookie3.setPath("/");
//		resp.addCookie(cookie3);
		
		String sameSiteAttribute = "SameSite=Lax"; // Hoặc "SameSite=Lax", "SameSite=None" tùy thuộc vào yêu cầu của bạn

		Cookie cookie3 = new Cookie("code",  PasswordEncryptor.encryptPassword(code));
		int age = (int) ((new Date().getTime() - time) / 1000);
		cookie3.setMaxAge(15 * 60 - age);
		cookie3.setHttpOnly(true);
		cookie3.setSecure(true);
		cookie3.setPath("/");
		cookie3.setSecure(true);

		String cookie3Header = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; %s",
		        cookie3.getName(), cookie3.getValue(), cookie3.getMaxAge(), sameSiteAttribute);
		resp.addHeader("Set-Cookie", cookie3Header);
		resp.addCookie(cookie3);
		
		req.setAttribute("message", "Gửi otp mới thành công. Hãy kiểm tra lại!");
		req.getRequestDispatcher("views/authentication/verifycode.jsp").forward(req, resp);
	}

	private static final long serialVersionUID = 1L;
}
