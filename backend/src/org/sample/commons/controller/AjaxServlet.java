package org.sample.commons.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxServlet extends HttpServlet {

	private static final long serialVersionUID = 4112508550273494752L;

	/*
	 * only allow POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
		IOException {
		process(request, response);
	}

	/*
	 * 
	 */
	private void process(HttpServletRequest request, HttpServletResponse response) {

		String pathInfo = request.getPathInfo();

		if ("/login".equals(pathInfo)) {
			login(request, response);

		} else {
			// not support action
			sendResponse(response, "{\"status\":500,\"message\":\"not supported path\"}");
		}
	}

	/*
	 * 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) {

		String userID = request.getParameter("userID");

		String pass = request.getParameter("pass");

		// no_this_parameter should be null
		@SuppressWarnings("unused")
		String no_this_parameter = request.getParameter("no_this_parameter");

		// avoid null pointer exception
		if ( "cornsoup".equals(userID) && "1234567".equals(pass) ) {
			sendResponse(response, "{\"status\":200,\"message\":\"login success\"}");

		} else {
			sendResponse(response, "{\"status\":500,\"message\":\"login failed\"}");
		}
	}

	/*
	 * 
	 */
	private void sendResponse(HttpServletResponse response, String message) {
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;

		try {
			response.setContentLength(countByteArrayLengthOfString(message));
			writer = response.getWriter();
			writer.write(message);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	private int countByteArrayLengthOfString(CharSequence sequence) {
		final int len = sequence.length();
		int count = len;
		for (int i = 0; i < len; i++) {
			char ch = sequence.charAt(i);
			if (ch <= 0x7F) {
				// count++;
			} else if (ch <= 0x7FF) {
				count += 1;
			} else if (ch >= 0xD800 && ch <= 0xDBFF) {
				count += 2;
				++i;
			} else {
				count += 2;
			}
		}
		return count;
	}
}
