package org.sample.commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginFilter implements Filter {

	protected FilterConfig config = null;

	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		new java.security.SecureRandom().nextLong();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String requestURI = httpRequest.getRequestURI();

		if (requestURI.startsWith("/login/")) {

			request.getRequestDispatcher("/index_login.jsp").forward(request, response);
			return;
		}

		chain.doFilter(request, response); // allow
		return;
	}

}
