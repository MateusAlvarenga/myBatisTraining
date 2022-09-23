package com.qat.employee.aspect;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import java.lang.reflect.Method;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.reflect.MethodSignature;


@RunWith(MockitoJUnitRunner.class)
public class ExecutiontimeadviceTest {

  @Test
  public void logExecutionTimeTest() throws Throwable {

    final String expectedMessage = "String fakeMethod execution time: 0ms";
    // get executionTimeAdvice Logger
    final Logger executionTimeAdviceLogger = (Logger) LoggerFactory.getLogger(ExecutionTimeAdvice.class);
    // create and start a ListAppender
    final ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
    listAppender.start();
    // add the appender to the logger
    executionTimeAdviceLogger.addAppender(listAppender);

    ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
    MethodSignature signature = mock(MethodSignature.class);
    Method method = new FakeMethod().getMethod();

    when(joinPoint.getTarget()).thenReturn(joinPoint);
    when(joinPoint.getSignature()).thenReturn(signature);
    when(joinPoint.getTarget().getClass().getSimpleName()).thenReturn("FakeClass");
    when(joinPoint.getSignature().getName()).thenReturn("fakeMethod");
    when(joinPoint.getSignature().getDeclaringTypeName()).thenReturn("com.qat.employee.aspect.FakeClass");
    when(signature.getMethod()).thenReturn(method);

    ExecutionTimeAdvice executionTimeAdvice = Mockito.spy(new ExecutionTimeAdvice());
    doReturn(0L).when(executionTimeAdvice).getCurrentTime();
    executionTimeAdvice.logExecutionTime(joinPoint);

    // get Logback Logger
    final List<ILoggingEvent> logsList = listAppender.list;

    assertThat(logsList.size()).isOne();
    logsList
        .forEach(
            log -> {
              assertThat(log.getMessage()).isEqualTo(expectedMessage);
            }
        );
  }

}
