package com.guoll.modules.framework.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import util.RoamProjectException;

public class MyExceptionHandler implements HandlerExceptionResolver{  
    private Logger logger = Logger.getLogger(MyExceptionHandler.class);  
      
    @Override  
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception ex) {  
        Map<String,Object> map = new HashMap<String,Object>();  
        logger.error(ex.getMessage(),ex);  
        if(ex instanceof RoamProjectException){  
        	RoamProjectException exception = (RoamProjectException) ex;
            map.put("msg", exception.getMessage());  
            return new ModelAndView(new MappingJackson2JsonView(),map);  
        }  
        map.put("msg", ex.getStackTrace());  
        return new ModelAndView(new MappingJackson2JsonView(),map);  
    }
} 