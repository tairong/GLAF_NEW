package baseSrc.framework.workflow;

public class taskInstanceHasEndedException extends ProcessInterceptorException {
	private static final long serialVersionUID = 1L;

	public taskInstanceHasEndedException() {
    	super();    	
    }

    public taskInstanceHasEndedException(String s) {
        super(s);
    }

	public taskInstanceHasEndedException(String message, Throwable cause) {
		super(message, cause);
	}

	public taskInstanceHasEndedException(Throwable cause) {
		super(cause);
	}
}
