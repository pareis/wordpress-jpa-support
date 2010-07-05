package org.palacehotel.wordpress.springweb;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palacehotel.wordpress.security.WordpressUserdetails;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class ServiceInvokerController implements Controller {

	private Map<String,Object> services = new HashMap<String,Object>();
	
	@SuppressWarnings("unchecked")
	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		setTimezoneInfo(req);
		
		String requestURI = req.getRequestURI();
		String[] splits = requestURI.split("/");
		if (splits.length<3)
			return error("Badly specified URL: " + requestURI, resp);
		String serviceName = splits[2];
		Object service = services.get(serviceName);
		if (service==null)
			return error("No such service: " + serviceName, resp);

		String methodName = stripExtension(splits[3]);
		Method method = getMethod(req, service.getClass(), methodName);
		if (method==null)
			return error("No such method: " + methodName + " in service " + serviceName, resp);
		
		Map result = invokeService(service, method, req);
		return new ModelAndView(requestURI.endsWith("json")?new JsonView():new XmlView(), result);
	}

	private void setTimezoneInfo(HttpServletRequest req) {
		WordpressUserdetails userDetails = getUserDetails();
		
		String tzOffsetString = req.getParameter("tzOffset");
		if (tzOffsetString!=null&&!tzOffsetString.equals(""))
			userDetails.setTzOffset(Float.valueOf(tzOffsetString));
		
		String daylightSavingString = req.getParameter("daylightSaving");
		if (daylightSavingString!=null&&!daylightSavingString.equals(""))
			userDetails.setDaylightSaving(Boolean.valueOf(daylightSavingString));
	}

	private WordpressUserdetails getUserDetails() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		WordpressUserdetails details = (WordpressUserdetails) authentication.getPrincipal();
		return details;
	}
	
	private static String stripExtension(String s) {
		int indexOfDot = s.indexOf(".");
		if (indexOfDot!=-1)
			return s.substring(0, indexOfDot);
		return s;
	}

	private ModelAndView error(String errorMessage, HttpServletResponse resp)
		throws IOException
	{
		resp.getWriter().write(errorMessage);
		return null;
	}

	@SuppressWarnings("unchecked")
	private Map invokeService(Object service, Method method, HttpServletRequest req)
		throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		String[] arguments = req.getParameter("params")!=null
			? req.getParameterValues("params")
			: null;
		return invoke(service, method, arguments);
	}

	@SuppressWarnings("unchecked")
	private Map invoke(Object service, Method method, String[] arguments)
		throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		Map result = new HashMap();
		Object serviceResult = null;
		if (arguments==null||arguments.length==0)
			serviceResult = method.invoke(service);
		else {
			Object[] parameterValues = getParameterValues(method, arguments);
			serviceResult = method.invoke(service, parameterValues);
		}
		if (serviceResult==null)
			result.put("result", "void");
		else
			result.put("result", serviceResult);
		return result;
	}

	private Object[] getParameterValues(Method method, String[] parameters) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		Object[] values = new Object[parameterTypes.length];
		for (int i=0; i<parameterTypes.length;i++) {
			if (String.class==parameterTypes[i])
				values[i] = parameters[i];
			else if (Long.TYPE==parameterTypes[i] || Long.class == parameterTypes[i])
				values[i] = Long.valueOf(parameters[i]);
			else if (Integer.TYPE==parameterTypes[i] || Integer.class == parameterTypes[i])
				values[i] = Integer.valueOf(parameters[i]);
			else if (Float.TYPE==parameterTypes[i] || Float.class == parameterTypes[i])
				values[i] = Float.valueOf(parameters[i]);
			else if (Boolean.TYPE==parameterTypes[i] || Boolean.class == parameterTypes[i])
				values[i] = Boolean.valueOf(parameters[i]);
			else if (Double.TYPE==parameterTypes[i] || Double.class == parameterTypes[i])
				values[i] = Double.valueOf(parameters[i]);
		}
		return values;
	}

	private Method getMethod(HttpServletRequest req, Class<?> clazz,
			String methodName) {
		Method[] methods = clazz.getMethods();
		for (int i=0; i<methods.length; i++) {
			if (methods[i].getName().equals(methodName)) {
				if (req.getParameter("params")!=null) {
					int nParams = req.getParameterValues("params").length;
					if (methods[i].getParameterTypes()!=null && methods[i].getParameterTypes().length==nParams) {
						return methods[i];
					}
				} else if (methods[i].getParameterTypes()==null
							|| methods[i].getParameterTypes().length==0)
					return methods[i];
			}
		}
		return null;
	}

	public Map<String, Object> getServices() {
		return services;
	}

	public void setServices(Map<String, Object> services) {
		this.services = services;
	}

}
