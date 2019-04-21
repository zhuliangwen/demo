package com.demo.controller;

import com.demo.utils.MapUtil;
import com.demo.utils.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RedisLockTestController {
 
    @Autowired
    private RedisLock redisLock;
 
    @PostMapping("createOrder")
    public String createOrder(HttpServletRequest request){
        String lockKey = MapUtil.getRedisKeyByParam(request.getParameterMap());
        if (redisLock.lock(lockKey)){
            //处理逻辑
            redisLock.delete(lockKey);
            return "success";
        }else {
            // 设置失败次数计数器, 当到达5次时, 返回失败
            int failCount = 1;
            while(failCount <= 5){
                // 等待100ms重试
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (redisLock.lock(lockKey)){
                    // 执行逻辑操作
                    //处理逻辑
                    redisLock.delete(lockKey);
                    return "success";
                }else{
                    failCount ++;
                }
            }
            return "请勿重复提交请求";
        }
 
    }
 
}
