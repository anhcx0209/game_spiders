/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders.model;

/**
 * Game model, determine when player have to end game, control the computers...
 * @author anhcx
 */
public class GameModel {
    
    private CobWeb _field;  // cobweb - where player play on
    
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
     */
    public GameModel(Level l) {
        _field = new CobWeb();
        _field.setSize(l._baseSize);
    }
    
    // ------------------ LEVEL -------------------------
    private Level _level = new Level();
    
    
    
    // ------------------ PLAYER -------------------------
}
