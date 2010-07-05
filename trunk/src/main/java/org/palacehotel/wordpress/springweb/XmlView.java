package org.palacehotel.wordpress.springweb;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

public class XmlView implements View {

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void render(Map model, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		resp.getWriter().write("hello, this is going to be xml");
	}

}
