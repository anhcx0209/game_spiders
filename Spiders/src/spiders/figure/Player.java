/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public Player(CobWeb cw) {
        super(cw);
        _type = TypeObject.PLAYER;
    }
    
    
    public void move(Direction dir) {
        if (moveTo(dir)) {
            // fire event to game model
            for (PlayerActionListener pal : _playerListeners)   
                pal.playerMoved();
        }
    }
    
    // ------------------- PLAYER LISTENER -----------------------------
    private ArrayList<PlayerActionListener> _playerListeners = new ArrayList<>();
    
    public void addPAL(PlayerActionListener l) {
        _playerListeners.add(l);
    }
    
    public void removePAL(PlayerActionListener l) {
        _playerListeners.remove(l);
    }
}
