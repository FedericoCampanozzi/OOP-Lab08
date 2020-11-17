package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private final List<String> memory = new ArrayList<>();
    private String nextString;
    /**
     * {@inheritDoc}
     */
    public void setNextString(final String s) {
        if (s != null && !s.equals("")) {
            memory.add(s);
        } else {
            throw new NullStringException();
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getNextString() {
        return this.nextString;
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getMemoryString() {
        return this.memory;
    }

    /**
     * {@inheritDoc}
     */
    public void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("There is no string set");
        }
        memory.add(this.nextString);
        System.out.println(this.nextString);
        }

}
