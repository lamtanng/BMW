package hcmute.controllers;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import hcmute.utils.Constants;

@WebServlet(urlPatterns = { "/image", "/audio", "/video" })
public class DownloadImageController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String DIR = "C:\\ImagesWeb";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("fname");

		// Kiểm tra tính hợp lệ của fileName
		if (isValidFileName(fileName)) {
			try {
				File file = new File(DIR, fileName).getCanonicalFile();
				if (Files.exists(file.toPath()) && file.getAbsolutePath().startsWith(DIR)) {
					String url = req.getRequestURI().toString();
					if (url.contains("image")) {
						resp.setContentType("image/jpeg");
						try (InputStream inputStream = new FileInputStream(file)) {
							OutputStream outputStream = resp.getOutputStream();
							byte[] buffer = new byte[4096];
							int bytesRead;
							while ((bytesRead = inputStream.read(buffer)) != -1) {
								outputStream.write(buffer, 0, bytesRead);
							}
						}
					} else if (url.contains("audio")) {
						resp.setContentType("audio/mpeg");
						resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
						resp.setHeader("Accept-Ranges", "bytes");
						long fileSize = file.length();
						resp.setContentLengthLong(fileSize);
						try (InputStream inputStream = new FileInputStream(file)) {
							IOUtils.copy(inputStream, resp.getOutputStream());
						}
					} else if (url.contains("video")) {
						resp.setContentType("video/mp4");
						long fileSize = file.length();
						resp.setContentLengthLong(fileSize);
						try (InputStream inputStream = new FileInputStream(file)) {
							IOUtils.copy(inputStream, resp.getOutputStream());
						}
					} else {
						resp.setContentType("text/plain");
						resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						resp.getWriter().println("Unsupported file type.");
					}
				} else {
					sendBadRequestResponse(resp);
				}
			} catch (IOException e) {
				sendBadRequestResponse(resp);
			} catch (Exception e) {
				sendBadRequestResponse(resp);
			}
		} else {
			sendBadRequestResponse(resp);
		}
	}

	// Phương thức gửi phản hồi lỗi
	private void sendErrorResponse(HttpServletResponse resp, String errorMessage) throws IOException {
		resp.setContentType("text/plain");
		resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		resp.getWriter().println(errorMessage);
	}

	// Phương thức gửi phản hồi lỗi khi yêu cầu không hợp lệ
	private void sendBadRequestResponse(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		resp.getWriter().println("Bad request.");
	}

	// Phương thức gửi phản hồi lỗi khi không tìm thấy tệp tin
	private void sendNotFoundResponse(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		resp.getWriter().println("File not found.");
	}

	// Phương thức kiểm tra tính hợp lệ của tên tệp
	// mã kiểm tra xem tên tệp có chứa chuỗi ".." không
	private boolean isValidFileName(String fileName) {
		// Kiểm tra xem fileName không null và không chứa chuỗi ".."
		return fileName != null && !fileName.contains("..");
	}

	// Phương thức gửi mã lỗi 404 (Not Found)
//	private void sendNotFoundResponse(HttpServletResponse resp) throws IOException {
//		checkError(resp);
//
//	}
//
//	// Phương thức gửi mã lỗi 400 (Bad Request)
//	//
//	private void sendBadRequestResponse(HttpServletResponse resp) throws IOException {
//		checkError(resp);
//	}
//
//	private void checkError(HttpServletResponse resp) throws IOException {
//		resp.setContentType("text/plain");
//		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//		resp.getWriter().println("Unsupported file type.");
//	}
//	private static final long serialVersionUID = 1L;
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String fileName = req.getParameter("fname");
//
//		// Kiểm tra tính hợp lệ của fileName
//		if (isValidFileName(fileName)) {
//			// Tiếp tục xử lý tệp tin với đường dẫn đã được kiểm tra.
//			File file = new File(Constants.DIR, fileName).getCanonicalFile();
//			String url = req.getRequestURI().toString();
//			if (file.exists() && file.getAbsolutePath().startsWith(Constants.DIR)) {
//				if (url.contains("image")) {
//					resp.setContentType("image/jpeg");
//					try (InputStream inputStream = new FileInputStream(file)) {
//						IOUtils.copy(inputStream, resp.getOutputStream());
//					}
//				} else if (url.contains("audio")) {
//					resp.setContentType("audio/mpeg");
//					resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
//					resp.setHeader("Accept-Ranges", "bytes");
//					long fileSize = file.length();
//					resp.setContentLengthLong(fileSize);
//					try (FileInputStream in = new FileInputStream(file)) {
//						IOUtils.copy(in, resp.getOutputStream());
//					}
//				} else if (url.contains("video")) {
//					resp.setContentType("video/mp4");
//					long fileSize = file.length();
//					resp.setContentLengthLong(fileSize);
//					try (FileInputStream in = new FileInputStream(file)) {
//						IOUtils.copy(in, resp.getOutputStream());
//					}
//				}
//			} else {
//				sendNotFoundResponse(resp);
//			}
//		} else {
//			sendBadRequestResponse(resp);
//		}
//	}
//
//	// Phương thức kiểm tra tính hợp lệ của tên tệp
//	private boolean isValidFileName(String fileName) {
//		// Kiểm tra xem fileName không null và không chứa chuỗi ".."
//		return fileName != null && !fileName.contains("..");
//	}
//
//	// Phương thức gửi mã lỗi 404 (Not Found)
//	private void sendNotFoundResponse(HttpServletResponse resp) throws IOException {
//		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//		resp.getWriter().println("<html><body><p>NOT FOUND</p></body></html>");
//	}
//
//	// Phương thức gửi mã lỗi 400 (Bad Request)
//	private void sendBadRequestResponse(HttpServletResponse resp) throws IOException {
//		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		//String fileName = req.getParameter("fname");
//		// 
//		
//		String fileName = req.getParameter("fname");
//		    // Tiếp tục xử lý tệp tin với đường dẫn đã được kiểm tra.
//			File file = new File(Constants.DIR + "/" + fileName);
//			String url = req.getRequestURI().toString();
//			if (file.exists()) {
//				if (url.contains("image")) {
//					resp.setContentType("image/jpeg");
//					IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
//				} else if (url.contains("audio")) {
//					resp.setContentType("audio/mpeg");
//					resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
//					resp.setHeader("Accept-Ranges", "bytes");
//					long fileSize = file.length();
//					String rangeHeader = req.getHeader("Range");
//					resp.setContentLengthLong(fileSize);
//					FileInputStream in = new FileInputStream(file);
//					if (rangeHeader != null) {
//						String[] range = rangeHeader.substring("bytes=".length()).split("-");
//						long start = Long.parseLong(range[0]);
//						long end = (range.length > 1 && !range[1].isEmpty()) ? Long.parseLong(range[1])
//								: (long) (fileSize - 1);
//						resp.setHeader("Content-Range", "bytes " + start + "-" + end + "/" + fileSize);
//						try {
//							byte[] buffer = new byte[1024 * 4];
//							int bytesRead;
//							while ((start < end) && ((bytesRead = in.read(buffer)) != -1)) {
//								resp.getOutputStream().write(buffer, 0, bytesRead);
//								start += bytesRead;
//							}
//						} finally {
//							in.close();
//						}
//					} else {
//						try {
//							byte[] buffer = new byte[1024 * 4];
//							int bytesRead;
//							while ((bytesRead = in.read(buffer)) != -1) {
//								resp.getOutputStream().write(buffer, 0, bytesRead);
//							}
//						} finally {
//							in.close();
//						}
//					}
//				} else if (url.contains("video")) {
//					resp.setContentType("video/mp4");
//					ServletOutputStream output = resp.getOutputStream();
//					try {
//						FileInputStream fin = new FileInputStream(Constants.DIR + "/" + fileName);
//
//						byte[] buf = new byte[4096];
//						int read;
//						while ((read = fin.read(buf)) != -1) {
//							output.write(buf, 0, read);
//						}
//						fin.close();
//						output.flush();
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
//				}
//			} else {
//				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//				resp.getWriter().println("<html><body><p>NOT FOUND</p></body></html>");
//			}
//		}
//		
//	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	        String fileName = req.getParameter("fname");
//	        File file = new File(Constants.DIR + "/" + fileName);
//			String url = req.getRequestURI().toString();
//	        // Kiểm tra tính hợp lệ của fileName
//	        if (isValidFileName(fileName)) {
//	            String basePath = "C:\\ImagesWeb\\download"; // Thay đổi thành đường dẫn thực tế của bạn
//
//	            // Xây dựng đường dẫn tuyệt đối đến tệp tin
//	            File file = new File(basePath, fileName).getCanonicalFile();
//
//	            // Kiểm tra sự tồn tại của tệp tin và xử lý nó tùy thuộc vào loại nội dung
//	            if (file.exists()) {
//					if (url.contains("image")) {
//						resp.setContentType("image/jpeg");
//						IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
//					} else if (url.contains("audio")) {
//						resp.setContentType("audio/mpeg");
//						resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
//						resp.setHeader("Accept-Ranges", "bytes");
//						long fileSize = file.length();
//						String rangeHeader = req.getHeader("Range");
//						resp.setContentLengthLong(fileSize);
//						FileInputStream in = new FileInputStream(file);
//						if (rangeHeader != null) {
//							String[] range = rangeHeader.substring("bytes=".length()).split("-");
//							long start = Long.parseLong(range[0]);
//							long end = (range.length > 1 && !range[1].isEmpty()) ? Long.parseLong(range[1])
//									: (long) (fileSize - 1);
//							resp.setHeader("Content-Range", "bytes " + start + "-" + end + "/" + fileSize);
//							try {
//								byte[] buffer = new byte[1024 * 4];
//								int bytesRead;
//								while ((start < end) && ((bytesRead = in.read(buffer)) != -1)) {
//									resp.getOutputStream().write(buffer, 0, bytesRead);
//									start += bytesRead;
//								}
//							} finally {
//								in.close();
//							}
//						} else {
//							try {
//								byte[] buffer = new byte[1024 * 4];
//								int bytesRead;
//								while ((bytesRead = in.read(buffer)) != -1) {
//									resp.getOutputStream().write(buffer, 0, bytesRead);
//								}
//							} finally {
//								in.close();
//							}
//						}
//					} else if (url.contains("video")) {
//						resp.setContentType("video/mp4");
//						ServletOutputStream output = resp.getOutputStream();
//						try {
//							FileInputStream fin = new FileInputStream(Constants.DIR + "/" + fileName);
//
//							byte[] buf = new byte[4096];
//							int read;
//							while ((read = fin.read(buf)) != -1) {
//								output.write(buf, 0, read);
//							}
//							fin.close();
//							output.flush();
//						} catch (Exception ex) {
//							ex.printStackTrace();
//						}
//					}
//				} else {
//					resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//					resp.getWriter().println("<html><body><p>NOT FOUND</p></body></html>");
//				}
//	        } else {
//	            // Trả về mã lỗi 400 nếu fileName không hợp lệ
//	            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//	        }
//	    }
//		
//
//	// Hàm kiểm tra tính hợp lệ của tên tệp
//    private boolean isValidFileName(String fileName) {
//        // Kiểm tra xem fileName có chứa các ký tự đặc biệt hoặc chuỗi ".." không
//        return fileName != null && !fileName.contains("..");
//    }

}
