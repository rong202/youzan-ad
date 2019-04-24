package com.youzan.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by baimugudu on 2019/3/25
 */


@Slf4j
@Component
public class AccessLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
       // HttpServletResponse response = context.getResponse();
        HttpServletRequest request = context.getRequest();
        /*Long startTime = (Long)context.get("startTime");
        String uri =  httpServletRequest.getRequestURI();
        Long cha =  System.currentTimeMillis()- startTime;
        log.info("uri:"+uri+";时间差:"+cha/100+"ms");*/
        String requestURI = request.getRequestURI();
        if(!"/login".equals(requestURI)){
            String token = request.getParameter("token");
            if(StringUtils.isEmpty(token)){
                context.setResponseBody("token不能为空");
                context.setResponseStatusCode(400);//参数错误
                context.setSendZuulResponse(false);
                return null;
            }else{
                try{
                    Claims claims = Jwts.parser().setSigningKey("mypassword")
                            .parseClaimsJws(token).getBody();
                }catch (MalformedJwtException e){//token无效，或者伪造的
                    context.setResponseBody("token无效");
                    context.setResponseStatusCode(401);//请求要求身份验证
                    context.setSendZuulResponse(false);
                    return null;
                }catch (ExpiredJwtException e){//token已过期
                    context.setResponseBody("token已过期");
                    context.setResponseStatusCode(403);//服务器拒绝请求
                    context.setSendZuulResponse(false);
                    return null;
                }catch (Exception e){
                    context.setResponseBody("token已过期");
                    context.setResponseStatusCode(412);//未满足前提条件
                    context.setSendZuulResponse(false);
                    return null;
                }
                context.setResponseBody("token正确");
                context.setResponseStatusCode(200);
                context.setSendZuulResponse(true);
                return null;


            }

        }


        return null;
    }
}
