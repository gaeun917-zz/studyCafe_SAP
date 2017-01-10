package com.studycafe.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Aspect
@Component("txAdvice")
public class TransactionAdvice {
	
	@Autowired
	@Qualifier("txManager")
	private PlatformTransactionManager txManager;
	
	public void before(JoinPoint joinPoint) {
		System.out.println(
			"before : " + joinPoint.getSignature().getName());
	}
	
	//@Around(value="execution(* *..*.*Tx3(..)")
	public Object doInTransaction(ProceedingJoinPoint joinPoint) {
		
		System.out.println("around : " + 
			joinPoint.getSignature().getName());
		//before
		TransactionStatus status = 
			txManager.getTransaction(new DefaultTransactionDefinition());
		
		Object result = null;
		try {
			result = joinPoint.proceed();
			txManager.commit(status);
		} catch (Throwable ex) {
			txManager.rollback(status);
		}
		//after
		return result;
	}

}





