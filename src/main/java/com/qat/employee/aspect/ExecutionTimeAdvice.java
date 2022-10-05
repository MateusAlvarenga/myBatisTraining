package com.qat.employee.aspect;

import java.lang.reflect.Method;
import org.apache.ibatis.session.SqlSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnExpression("${aspect.enabled:true}")
public class ExecutionTimeAdvice {

  Logger log = LoggerFactory.getLogger(ExecutionTimeAdvice.class);

  @Around("execution(public * com.qat..*BAC.*(..)) || execution(public * com.qat..*BAR.*(..)) )")
  public Object logExecutionTime(ProceedingJoinPoint point) throws Throwable {

    final long startTime = getCurrentTime();
    final Object object = point.proceed();
    final long endtime = getCurrentTime();
    final long executionTime = endtime - startTime;

    final String className = point.getTarget().getClass().getSimpleName();
    final String methodName = point.getSignature().getName();

    final String msg =
        className + " " + methodName + " " + "execution time: " + executionTime + "ms";

    log.info(msg);

    return object;
  }

  @AfterThrowing(pointcut =" execution(public * com.qat..*BAR.*(..))", throwing="ex")
  public void logError(Exception ex) {
	System.out.print(false);
    log.error("LOG ERROR:");
    log.error(ex.getCause().getMessage());
    log.error(ex.getMessage());
  }

  protected long getCurrentTime() {
    return System.currentTimeMillis();
  }
}