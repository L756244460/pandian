package cn.datasset.yipandian.client.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.ttl.TransmittableThreadLocal;

import cn.datasset.yipandian.client.model.user.UserInfo;
import cn.datasset.yipandian.client.service.user.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lzl
 * @version 1.0
 * @date 2021/10/16 15:08
 */
@Component
public class UserInfoInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(UserInfoInterceptor.class);
    public static TransmittableThreadLocal<UserInfo> userInfoThreadLocal = new TransmittableThreadLocal<>();

    @Resource
    UserInfoService userInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        //这里要判断该userId是否存在用户表中 还是 调用接口查询
        if (StringUtils.isEmpty(userId) || !userInfoService.userExist(userId)) {
            response.setContentType("application/json;charset=utf-8");
            Map<String, Object> m = new HashMap<>(8);
            m.put("message", "用户信息失效，请重新登录");
            m.put("code", 999);
            m.put("data", null);
            response.getWriter().write(JSONObject.toJSONString(m));
            return false;
        } else {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfoThreadLocal.set(userInfo);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        userInfoThreadLocal.remove();
    }
    
}
