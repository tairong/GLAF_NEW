package baseSrc.framework.workflow;

import baseSrc.framework.BaseException;

public class ProcessInterceptorException extends BaseException {
	private static final long serialVersionUID = 1L;

	public ProcessInterceptorException() {
    	super();    	
    }

    public ProcessInterceptorException(String s) {
        super(s);
    }

	public ProcessInterceptorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProcessInterceptorException(Throwable cause) {
		super(cause);
	}
}
