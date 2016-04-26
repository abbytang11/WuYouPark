package com.ajie.wechat.aspect;

import com.ajie.wechat.entity.Algorithm;
import com.ajie.wechat.entity.Security;
import com.ajie.wechat.util.RSAUntil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
public class CarOwnerInterceptor {

    @Before("execution(public * com.ajie.wechat.dao.CarOwnerDao.*(..))")
    public void before(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Class aClass = args[0].getClass();
        //返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段
        // 包括公共、保护、默认（包）访问和私有字段，但不包括继承的字段。
        for (Field field : aClass.getDeclaredFields()) {
            //如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null。
            Security security = field.getAnnotation(Security.class);
            if (security != null) {
                if (security.algorithm() == Algorithm.rsa) {
                    //值为 true 则指示反射的对象在使用时应该取消 Java 语言访问(权限)检查
                    // 值为 false 则指示反射的对象应该实施 Java 语言访问检查。
                    field.setAccessible(true);
                    //field.get(args[0]) 返回指定对象上此 Field 表示的字段的值。
                    String result = RSAUntil.encodingByRSA(field.get(args[0]).toString());
                    //将指定对象变量上此 Field 对象表示的字段设置为指定的新值。
                    field.set(args[0], result);
                }
            }
        }
    }


    @AfterReturning(
            pointcut="execution(public * com.ajie.wechat.dao.CarOwnerDao.findOne(..))",
            returning="retVal")
    public void doBasicProfiling(Object retVal) throws Throwable{
        Class aClass = retVal.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            Security security = field.getAnnotation(Security.class);
            if (security != null) {
                if (security.algorithm() == Algorithm.rsa) {
                    field.setAccessible(true);
                    String result = RSAUntil.decodingByRSA(field.get(retVal).toString());
                    field.set(retVal, result);
                }
            }
        }
    }

}
