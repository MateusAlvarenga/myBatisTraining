package com.qat.employee.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
@ConditionalOnExpression("${aspect.enabled:true}")
@Slf4j
public class ExecutionTimeAdvice {

  // Anotation to track execution time of a method
  @Around("@annotation(com.qat.employee.advice.TrackExecutionTime)")
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

  private Object logExecutionTime(ProceedingJoinPoint point) throws Throwable {

    final long startTime = System.currentTimeMillis();
    final Object object = point.proceed();
    final long endtime = System.currentTimeMillis();
    final long executionTime = endtime - startTime;

    //final String className = point.getTarget().getClass().getSimpleName();
    final String methodName = point.getSignature().getName();
    final String classNameWithPackage = point.getSignature().getDeclaringTypeName();
    final String className = classNameWithPackage.substring(classNameWithPackage
        .lastIndexOf('.') + 1);

    final String msg = className + " " + methodName + " " + "execution time: " + executionTime + "ms";

    log.info(msg);
    return object;
  }
}