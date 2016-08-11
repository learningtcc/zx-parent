package com.ink.channel.core.utils;



/**
 *  interface that indicates that this object requires a callback during destruction.
 *
 *@author Jerry
 * 
 * @version 1.0
 * 
 *   TIME 2015/8/24
 */
public interface Destroyable {

    /**
     * Called when this object is being destroyed, allowing any necessary cleanup of internal resources.
     *
     * @throws Exception if an exception occurs during object destruction.
     */
    void destroy() throws Exception;

}
