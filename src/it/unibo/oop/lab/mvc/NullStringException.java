package it.unibo.oop.lab.mvc;

public class NullStringException extends IllegalStateException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return the string representation of instances of this class.
     */
    @Override
    public String toString() {
        return "String can't be null";
    }

    /**
     * @return the string representation of instances of this class.
     */
    @Override
    public String getMessage() {
        return this.toString();
    }
}