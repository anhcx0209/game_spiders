package spiders.events;

import java.util.EventListener;

/**
 * Listen all action of player.
 * @author anhcx
 */
public interface PlayerActionListener extends EventListener {

    /**
     * Fire when player successfully move his spider.
     */
    public void playerMoved();
}
