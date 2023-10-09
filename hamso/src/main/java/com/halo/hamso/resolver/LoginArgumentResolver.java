package com.halo.hamso.resolver;

import org.springframework.core.MethodParameter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class LoginArgumentResolver implements HandlerMethodArgumentResolver {



    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        //boolean hasMemberType = parameter.getParameterType().equals(UserDto.class);
        return hasLoginAnnotation ;//&& hasMemberType;

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
        return null;
    }
}

/**     instanceof는 특정 Object가 어떤 클래스/인터페이스를 상속/구현했는지를 체크하며
        Class.isAssignableFrom()은 특정 Class가 어떤 클래스/인터페이스를 상속/구현했는지 체크합니다.
 *
 * */