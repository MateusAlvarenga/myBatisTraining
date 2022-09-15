package com.qat.employee.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnExpression("${aspect.enabled:true}")
public class ExecutionTimeAdvice {

  Logger log = LoggerFactory.getLogger(ExecutionTimeAdvice.class);

  // Anotation to track execution time of a method
  @Around("@annotation(com.qat.employee.aspect.TrackExecutionTime)")
  public Object executionTime(ProceedingJoinPoint point) throws Throwable {
    return logExecutionTime(point);
  }

  // Log execution time of BAC methods
  @Around("execution(*  com.qat.employee.domain.employee.BAC.*.*(..))")
  public Object executionTimeBAC(ProceedingJoinPoint point) throws Throwable {
    return logExecutionTime(point);
  }

  // Log execution time of BAR methods
  @Around("execution(* com.qat.employee.domain.employee.BAR.*.*(..))")
  public Object executionTimeBAR(ProceedingJoinPoint point) throws Throwable {
    return logExecutionTime(point);
  }

  public Object logExecutionTime(ProceedingJoinPoint point) throws Throwable {

    final long startTime = getCurrentTime();
    final Object object = point.proceed();
    final long endtime = getCurrentTime();
    final long executionTime = endtime - startTime;

    final String className = point.getTarget().getClass().getSimpleName();
    final String methodName = point.getSignature().getName();
    final String classNameWithPackage = point.getSignature().getDeclaringTypeName();
//    final String className = classNameWithPackage.substring(classNameWithPackage
//        .lastIndexOf('.') + 1);

    final String msg =
        className + " " + methodName + " " + "execution time: " + executionTime + "ms";

    log.info(msg);
    return object;
  }

  protected long getCurrentTime() {
    return System.currentTimeMillis();
  }
}