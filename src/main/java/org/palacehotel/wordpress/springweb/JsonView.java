package org.palacehotel.wordpress.springweb;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

import flexjson.JSONSerializer;

public class JsonView implements View {

	@Override
	public String getContentType() {
		return "text/plain";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void render(Map model, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		JSONSerializer serializer = new JSONSerializer().exclude("*.class");
		resp.setContentType("text/plain;charset=utf-8");
		Object modelResult = model.get("result");
		String result = serializer.serialize(modelResult);
		resp.getWriter().write(result);
	}

}
