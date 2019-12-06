package fscut.manager.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

    private final static Logger log = LoggerFactory.getLogger(TimeAspect.class);

    @Pointcut("execution(* fscut.manager.demo.controller..*.*(..))")
    public void performance(){}

    @Around("performance()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info(joinPoint.getSignature().getName() + " started!");
        Object result = new Object();
        long begin = System.currentTimeMillis();
        try{
            result = joinPoint.proceed();

        }catch (Exception e){
            log.error("日志记录发生错误，errorMessage:{}",e.getMessage());
        }finally {
            long costTime = System.currentTimeMillis() - begin;
            log.info(joinPoint.getSignature().getName() + " finished! Cost:" + costTime + " ms");

        }
        return result;
    }
}
