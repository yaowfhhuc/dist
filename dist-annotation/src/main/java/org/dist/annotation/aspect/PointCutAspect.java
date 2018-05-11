package org.dist.annotation.aspect;

import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author yaowf
 * @createDate 2018-05-08 10:24
 */

@Aspect
public class PointCutAspect {

    @Pointcut(value = "execution(public * org.dist.annotation..*(..)) && args(params))",argNames = "params")
    public void beforePointCut(Map<String,Object> params){

    }

    @Before(value = "beforePointCut(params)" ,argNames = "params")
    public void beforeAspect(Map<String,Object> params){

        System.out.println("params = [" + params + "]");
    }
}
