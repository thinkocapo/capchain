package dao.evm;

public class Account<Type> {
	private Type type; 
	public Account() {
		// could do the 'set' here? * this.type = Type 
	}
	
    public void set(Type t) {
        this.type = t;
    }

    public Type type() {
        return type;
    }
    
    public String toString(PrintFormat format) {
    	switch (format) {
	    	case OBJECT:
	    		return this.type().getClass().getName();
	    	case OBJECTHEADER:
	    		return "OBJECT HEADER : " + this.type().getClass().getName();
    		default:
    			return this.type().getClass().getName();
    	}
    }

}