package spiders.figure;

import java.util.ArrayList;
import spiders.events.PlayerActionListener;
import spiders.model.CobWeb;
import spiders.navigations.Direction;

/**
 * Player is spider controlled by user.
 * @author anhcx
 */
public class Player extends Spider {

    /**
     * Constructor with a cobweb.
     * @param cw cobweb, where object will play on.
     */
    public Player(CobWeb cw) {
        super(cw);
    }
    
    /**
     * Player make his spider move up/down/left/right.
     * If this move correctly, player fire action to let other computer make
     * his move.
     * @param dir direction of move.
     */
    public void makeMove(Direction dir) {
        if (tryMovingTo(dir)) {
            // fire event to game model
            for (PlayerActionListener pal : _playerListeners)   
                pal.playerMoved();
        }
    }
    
    // ------------------- ADD PLAYER LISTENER -----------------------------
    private ArrayList<PlayerActionListener> _playerListeners = new ArrayList<>();
    
    public void addPAL(PlayerActionListener l) {
        _playerListeners.add(l);
    }
    
    public void removePAL(PlayerActionListener l) {
        _playerListeners.remove(l);
    }
}
