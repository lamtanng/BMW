package hcmute.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/user/helpcenter", "/user/guides" })
public class HelpPageController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		resp.setHeader("X-Frame-Options", "DENY");
		resp.setHeader("X-Content-Type-Options", "nosniff");
		if (url.contains("helpcenter")) {
			req.getRequestDispatcher("/views/user/HelpCenter.jsp").forward(req, resp);
		} else if (url.contains("guides")) {
			req.getRequestDispatcher("/views/user/Guides.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setHeader("X-Frame-Options", "DENY");
		resp.setHeader("X-Content-Type-Options", "nosniff");
		super.doPost(req, resp);
	}
}
