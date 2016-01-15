/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders.model;

/**
 * Game model
 * @author anhcx
 */
public class GameModel {
    
    private CobWeb _field;
    
    public CobWeb field() {
        return _field;
    }
    
    public GameModel() {
        _field = new CobWeb();
        _field.setSize(6);
    }
    
}
