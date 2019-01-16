package com.example.demo.filter;

//import org.apache.http.HttpStatus;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
			HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;


//		httpResp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS");
//		httpResp.setHeader("Access-Control-Allow-Origin", "*");
//		httpResp.setHeader("Access-Control-Allow-Credentials", "false");
		
		httpResp.setHeader("Access-Control-Allow-Origin", "*");
		httpResp.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
		httpResp.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
		httpResp.setHeader("Access-Control-Allow-Credentials", "false");
		httpResp.setHeader("Access-Control-Allow-Headers","Origin,Accept,x-target,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,X-Auth-Token,Cache-Control");
		
		
//		if (httpReq.getMethod().equalsIgnoreCase("OPTIONS")) {
//			httpResp.setHeader("Access-Control-Allow-Headers",
//			httpReq.getHeader("Access-Control-Request-Headers"));
//			httpResp.setStatus(HttpStatus.SC_OK);
//		}else{
			chain.doFilter(request, response);
//		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}