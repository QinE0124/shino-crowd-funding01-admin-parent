package com.shino.crowd.cofig;

import com.google.gson.Gson;
import com.shino.crowd.constant.CrowdConstant;
import com.shino.crowd.exception.AccessForbiddenException;
import com.shino.crowd.exception.LoginFailedException;
import com.shino.crowd.util.CrowdUtil;
import com.shino.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 基于注解的异常处理类
 * @author QinE
 * @create 2022-10-19 12:20
 */
//ControllerAdvice将当前类标识为异常处理的组件
@ControllerAdvice
public class CrowdExceptionResolver {

    //ExceptionHandler用于设置所标识的方法处理的异常
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(
            LoginFailedException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        //只需指定当前异常对应的页面
        String viewName = "admin-login";

        return commonResolveException(exception, request, response, viewName);
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView resolveException(
            Exception exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String viewName = "system-error";

        return commonResolveException(exception, request, response, viewName);
    }

    @ExceptionHandler(value = AccessForbiddenException.class)
    public ModelAndView resolveAccessForbiddenException(
            AccessForbiddenException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String viewName = "admin-login";

        return commonResolveException(exception, request, response, viewName);
    }

    /**
     * 核心异常处理方法
     * @param exception SpringMVC捕获的异常对象
     * @param request 为了判断当前请求是“普通请求”还是“Ajax请求”，需要传入原生request对象
     * @param response 为了能将JSon字符串作为响应数据返回给浏览器
     * @param viewName 指定要前往的视图名称
     * @return ModelAndView
     */
    private ModelAndView commonResolveException(
            Exception exception,
            HttpServletRequest request,
            HttpServletResponse response,
            String viewName) throws IOException {

        //1.判断当前请求是“普通请求”还是“Ajax请求”
        boolean judgeResult = CrowdUtil.judgeRequestType(request);

        //2.如果是Ajax请求
        if (judgeResult) {

            //3.从当前异常对象中获取异常信息
            String message = exception.getMessage();

            //4.创建ResultEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(message);

            //5.创建Gson对象
            Gson gson = new Gson();

            //6.将resultEntity转化为JSON字符串
            String json = gson.toJson(resultEntity);

            //7.把当前JSON字符串作为请求的响应数据返回给浏览器
            PrintWriter writer = response.getWriter();
            writer.write(json);

            //8.返回null，不给SpringMVC提供ModelAndView对象，这样SpringMVC就知道不需要框架视图解析器来提供响应，而是程序员自己提供响应
            return null;
        }

        //9.创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();

        //10.将Exception对象存入模型
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);

        //11.设置目标视图名称
        modelAndView.setViewName(viewName);

        //12.返回视图
        return modelAndView;
    }
}
