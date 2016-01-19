package spiders.model;

import spiders.navigations.Position;

/**
 * Objects exist on cobweb.
 * @author anhcx
 */
public abstract class CobWebObject {
    
    /**
     * Type of objects
     * SPIDER - player and computer
     * FOOD - food of spider
     * RISK - drop rain and rock, what is harmful for spider
     */
    public enum TypeObject {
        COMPUTER, PLAYER, FOOD, RAIN, STONE
    }
    
    // --------------------- Type ---------------
    protected TypeObject _type;
    
    public TypeObject type() {
        return _type;
    }
    
    // --------------- Cob web ------------------
    private CobWeb _cobweb;
    
    public CobWebObject(CobWeb cw) {
        _cobweb = cw;
        _position = null;
    }
    
    public CobWeb cobweb() {
        return _cobweb;
    }
    
    // --------------- Name -------------------
    protected String _name;
    
    public void setName(String name) {
        _name = name;
    }
    
    public String name() {
        return _name;
    } 
    
    // --------------- Position ---------------
    protected Position _position = null;
    
    public Position position() {
        return _position;
    }
    
    public void setPosition(Position pos) {
        if (_position != null)
            cobweb()._mark[_position.row()][_position.column()]--;
        
        _position = pos;
        cobweb()._mark[_position.row()][_position.column()]++;
    }
    
}
