package baseSrc.framework.workflow;

public class ProcessActionExcpetion extends ProcessInterceptorException {
	private static final long serialVersionUID = 1L;

	public ProcessActionExcpetion() {
    	super();    	
    }

    public ProcessActionExcpetion(String s) {
        super(s);
    }

	public ProcessActionExcpetion(String message, Throwable cause) {
		super(message, cause);
	}

	public ProcessActionExcpetion(Throwable cause) {
		super(cause);
	}
}
