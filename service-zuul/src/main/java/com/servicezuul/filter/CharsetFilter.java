package com.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * Email 1655379381@qq.com
 *
 * @author DaiJin
 * Created Time 2019/7/19.
 */
@Component //交给Spring管理
public class CharsetFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(CharsetFilter.class);

    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     * */
    @Override
    public String filterType() {
        // 返回一个字符串代表过滤器的类型
        return "pre";
    }

    /**
     * filterOrder：过滤的顺序
     * */
    @Override
    public int filterOrder() {
        return 0;
    }
    /**
     * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     * */
    @Override
    public boolean shouldFilter() {
        return true;
    }
    /**
     * run：过滤器的具体逻辑。可用很复杂，
     * 包括查sql，nosql去判断该请求到底有没有权限访问。
     *
     * */
    @Override
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}

            return null;
        }
        log.info("ok");
        return null;
    }
}