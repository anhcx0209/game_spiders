/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders.model;

import spiders.figure.Spider;

/**
 * Game model, determine when player have to end game, control the computers...
 * @author anhcx
 */
public class GameModel {
    
    private CobWeb _field;  // cobweb - where player play on
    private Level _level;
    
    /**
     * getter for _field
     * @return cobweb - where game is based on
     */
    public CobWeb field() {
        return _field;
    }
    
    /**
     * create a game model with pre-defined level
     * level = hard, medium and easy
     * @param l pre-define level for game model
     */
    public GameModel(Level l) {
        _field = new CobWeb(l._baseSize);
        _level = l;
        
        // create new player
        _player = new Spider(_field);
        
    }
    
    // ------------------ PLAYER -------------------------
    private Spider _player;
    
    
}
