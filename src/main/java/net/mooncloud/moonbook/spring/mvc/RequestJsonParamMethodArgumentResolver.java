package net.mooncloud.moonbook.spring.mvc;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.servlet.ServletException;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class RequestJsonParamMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver {

	private ObjectMapper mapper = new ObjectMapper();

	public RequestJsonParamMethodArgumentResolver() {
		super(null);
		mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(RequestJsonParam.class)) {
			return true;
		}
		return false;
	}

	@Override
	protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
		RequestJsonParam annotation = parameter.getParameterAnnotation(RequestJsonParam.class);
		return new RequestJsonParamNamedValueInfo(annotation);

	}

	@Override
	protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest webRequest) throws Exception {
		String[] paramValues = webRequest.getParameterValues(name);
		webRequest.getParameterMap();
		Class<?> paramType = parameter.getParameterType();
		if (paramValues == null) {
			return null;

		}

		try {
			if (paramValues.length == 1) {
				Type type = parameter.getGenericParameterType();
				JavaType javaType = getJavaType(paramType);

				if (Collection.class.isAssignableFrom(paramType)) {
					javaType = javaType
							.narrowContentsBy((Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0]);
				}
			
				return mapper.readValue(paramValues[0], javaType);
			}

		} catch (Exception e) {
			throw new JsonMappingException("Could not read request json parameter", e);
		}

		throw new UnsupportedOperationException("too many request json parameter '" + name
				+ "' for method parameter type [" + paramType + "], only support one json parameter");
	}

	protected JavaType getJavaType(Class<?> clazz) {
		return TypeFactory.defaultInstance().constructType(clazz);
	}

	@Override
	protected void handleMissingValue(String paramName, MethodParameter parameter) throws ServletException {
		String paramType = parameter.getParameterType().getName();
		throw new ServletRequestBindingException("Missing request json parameter '" + paramName
				+ "' for method parameter type [" + paramType + "]");
	}

	private class RequestJsonParamNamedValueInfo extends NamedValueInfo {

		private RequestJsonParamNamedValueInfo() {
			super("", false, null);
		}

		private RequestJsonParamNamedValueInfo(RequestJsonParam annotation) {
			super(annotation.value(), annotation.required(), null);
		}
	}

}