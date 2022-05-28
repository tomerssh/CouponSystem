package app.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import app.core.utils.JwtUtil;

public class LoginFilter implements Filter {
	private JwtUtil jwtUtil;

	public LoginFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("login filter before");
		HttpServletRequest req = (HttpServletRequest) request;
		String token = req.getHeader("token");
		System.out.println(token);
		try {
			jwtUtil.extractClient(token);
			chain.doFilter(request, response);
			System.out.println("login filter after");
		} catch (Exception e) {
			e.printStackTrace();
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendError(HttpStatus.UNAUTHORIZED.value(), "you are not logged in");
		}
	}

}
