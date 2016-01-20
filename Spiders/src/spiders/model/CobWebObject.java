package spiders.model;

import spiders.navigations.Position;
import spiders.views.ObjectView;

/**
 * Objects exist on cobweb.
 * @author anhcx
 */
public abstract class CobWebObject {
    
    // --------------- COB WEB ------------------
    private CobWeb _cobweb;
    
    public CobWebObject(CobWeb cw) {
        _cobweb = cw;
        _position = null;
        _stopWork = false;
        _stop = false;
    }
    
    public CobWeb cobweb() {
        return _cobweb;
    }
    
    // --------------- NAME -------------------
    protected String _name;
    
    protected void setName(String name) {
        _name = name;
    }
    
    /**
     * Get name of object.
     * @return name of object.
     */
    public String name() {
        return _name;
    } 
    
    // --------------- POSITION ---------------
    protected Position _position = null;
    
    /**
     * Get position of object.
     * @return position of object.
     */
    public Position position() {
        return _position;
    }
    
    /**
     * Set object to new position.
     * @param pos new position for object.
     */
    public void setPosition(Position pos) {
        if (_position != null)
            cobweb()._mark[_position.row()][_position.column()]--;
        
        _position = pos;
        cobweb()._mark[_position.row()][_position.column()]++;
    }
    
    // --------------- Appearence ---------------
    protected ObjectView _view;
    
    /**
     * Return appearance of objects.
     * @return appearance of objects.
     */
    public ObjectView view() {
        return _view;
    }
    
    // ---------------- Stop working ------------
    private boolean _stopWork;
    
    public boolean isStop() {
        return _stopWork;
    }
    
    protected void stopWork() {
        _stopWork = true;
    }
    
    
    // ------------------ Stop moving or working because a Holder---------------
    private boolean _stop;
    
    /**
     * Get state is stopped or not.
     * @return true if object is stopping, otherwise false.
     */
    public boolean isStuned() {
        return _stop;
    }
    
    /**
     * Let object continue work or play.
     */
    public void unStun() {
        _stop = false;
    }
    
    /**
     * Prevent object move or work rightly.
     */
    public void stun() {
        _stop = true;
    }
}
