package it.unibo.oop.lab.codeanalysis;

import java.util.ArrayList;
import java.util.Collection;

/*
 * Checkstyle complains: the author and version Javadoc tags should not be used.
 */
/**
 * This code triggers static code analyzers. You should use it to see how things
 * SHOULD NOT be done.
 *
 *
 */
public class ThisIsHowThingsShouldNotBeDone {

    /*
     * PMD complains:
     * 
     * This class should be final, because it is a utility class (all methods
     * are static)
     * 
     * This class should have a private constructor, because is not meant to be
     * extended (it is an utility class)
     * 
     * 
     * Checkstyle complains:
     * 
     * This class should not have a public or default constructor
     */
    private static final int ELEM = 43;
    /*
     * PMD complains:
     * 
     * "a" is not final, but it should be
     * 
     * This method could use varargs
     * 
     * 
     * Checkstyle complains:
     * 
     * Missing Javadoc
     */
    private static void main(final String[] a) {
        /*
         * PMD Complains:
         * 
         * this variable should be final!
         */
        final Collection<Object> c = new ArrayList<>();
        /*
         * PMD complains:
         * 
         * Use curly braces!
         * 
         * Checkstyle complains:
         * 
         * Use curly braces!
         * 
         * What does it mean "43"? It's a magic number!
         */
        for (int i = 0; i < ELEM; i++) {
            c.add(new Object());
        }
        /*
         * FindBugs complains
         * 
         * Slow: use clear() instead!
         */
        //c.removeAll(c);
        //c.clear();
    }

}
