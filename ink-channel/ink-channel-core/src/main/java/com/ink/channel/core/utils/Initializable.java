package com.ink.channel.core.utils;


/**
 * all  trans  interface that indicates that this object requires initialization.
 * 
 * @author Jerry
 * 
 * @version 1.0
 * 
 *   TIME 2015/8/24
 *
 */
public interface Initializable {
	
	

    /**
     * Initializes this object.
     *
     */
    void init() throws RuntimeException;

}
