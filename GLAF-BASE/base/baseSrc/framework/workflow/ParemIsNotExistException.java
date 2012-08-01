package baseSrc.framework.workflow;

public class ParemIsNotExistException extends ProcessInterceptorException {
	private static final long serialVersionUID = 1L;

	public ParemIsNotExistException() {
    	super();    	
    }

    public ParemIsNotExistException(String s) {
        super(s);
    }

	public ParemIsNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParemIsNotExistException(Throwable cause) {
		super(cause);
	}
}
