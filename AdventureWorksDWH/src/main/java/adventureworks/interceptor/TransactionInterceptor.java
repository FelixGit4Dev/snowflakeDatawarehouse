package adventureworks.interceptor;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Transactional
@Interceptor
public class TransactionInterceptor implements Serializable{

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Resource
private UserTransaction userTransaction;


@AroundInvoke
public Object invoke(InvocationContext invocationContext) throws IllegalStateException, SecurityException, SystemException{
	Object result=null;
try {	
if(userTransaction!=null){

	userTransaction.begin();
}
result= invocationContext.proceed();
if(userTransaction!=null ) {
userTransaction.commit();	
}
} catch (Exception e) {
	userTransaction.rollback();
	
	e.printStackTrace();
}	
return result;	
}

	
}
