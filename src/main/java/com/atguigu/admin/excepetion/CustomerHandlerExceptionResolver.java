package com.atguigu.admin.excepetion;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 1、默认情况下springboot异常处理在ErrorMvcAutoConfiguration配置，主要是通过其内置的tomcat完成，针对内部异常，将错误指向/error->BasicErrorController进行处理，
 * 但是要注意，同时通过BeanNameViewer这个视图解析器来完成error最终的显示。要注意的是，这个处理内部异常；基于内置tomcat异常。
 * 2、通过继承AbstractErrorController可以覆盖BasicErrorController，改写其默认逻辑
 * 3、通过重写ErrorAttributes可以在BasicErrorController的处理逻辑上修改返回值
 * 4、通过实现HandlerExceptionResolver可以覆盖或扩展默认的异常处理器，这个使用时要注意
 * 5、通过@ControllerAdvice可以实现Controller的异常统一处理
 * 6、通过注册EmbeddedServletContainerCustomizer可以根据不同响应错误定位不同处理页面比如404、500...，同1类似
 * ————————————————
 * 版权声明：本文为CSDN博主「_沉浮_」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/su_chunlong/article/details/95348588
 */

//@Order(Ordered.HIGHEST_PRECEDENCE)  //变成最高优先级，使得自定义异常放在handlerExceptionResolvers前，先执行自定义异常，优先级数字越小，优先级越高
@Component
public class CustomerHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            response.sendError(511,"我喜欢的错误");  //一开始自动配置的时候，已经将/error相关的errorPage对象注册进内嵌的tomcat中了,
                // 使用后tomcat会为我们重定向/error请求，由org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController去响应
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
