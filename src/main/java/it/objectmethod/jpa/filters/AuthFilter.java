package it.objectmethod.jpa.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import it.objectmethod.jpa.controller.beans.UserLog;
import it.objectmethod.jpa.model.User;

@Component
public class AuthFilter implements Filter {

	@Autowired
	private UserLog log;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		String url = httpReq.getRequestURI();
		String method = httpReq.getMethod();

		if (HttpMethod.OPTIONS.matches(method)) {
			chain.doFilter(request, response);
		} else {
			if (url.contains("login")) {
				System.out.println("RICHIESTA APPROVATA!");
				chain.doFilter(request, response);
			} else {
				String token = httpReq.getHeader("auth-token");

				if (token != null) {
					User user = log.getUserMap().get(token);

					if (user != null) {
						System.out.println("TOKEN VALIDO RICHIESTA APPROVATA!");
						chain.doFilter(request, response);
//						if (url.contains("admin") || url.contains("backoffice")) {
//						chain.doFilter(request, response);
//						} else {
//							System.out.println("TIPO UTENTE ERRATO RICHIESTA BLOCCATA!");
//							httpResp.setStatus(HttpServletResponse.SC_FORBIDDEN);
//						}
					} else {
						System.out.println("TOKEN NON VALIDO RICHIESTA BLOCCATA!");
						httpResp.setStatus(HttpServletResponse.SC_FORBIDDEN);
					}
				} else {
					System.out.println("TOKEN NON PRESENTE RICHIESTA BLOCCATA!");
					httpResp.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}

			}
		}

	}

}
