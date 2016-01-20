package spiders.events;

import java.util.EventListener;

/**
 * Listen all move of spider, rains.
 * @author anhcx
 */
public interface GameListener extends EventListener {

    /**
     * Fire when on the field have something change 
     */
    public void needRepaint();
    
    
    public void gameOver();
}
