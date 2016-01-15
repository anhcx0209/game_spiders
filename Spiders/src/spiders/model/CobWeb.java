package spiders.model;

import java.util.ArrayList;
import spiders.model.CobWebObject.TypeObject;

/**
 * Cobweb - where spider and bug move
 * size of cobweb = n x n
 * @author anhcx
 */
public class CobWeb {
    
    // ------------------ size --------------------
    int _size;
    
    CobWeb(int s) {
        _size = s;
    }
    
    public int size() {
        return _size;
    }
    
    public void setSize(int s) {
        _size = s;
    }
    
    public CobWeb() {
        
    }
    
    // ------------------- objects on cobweb --------
    ArrayList<CobWebObject> _objects = new ArrayList<>();
    
    public void addObject(CobWebObject obj) {
        _objects.add(obj);
    }
    
    public void removeObject(CobWebObject obj) {
        if (_objects.contains(obj))
            _objects.remove(obj);
    }
    
    public ArrayList<CobWebObject> objects() {
        return _objects;
    }
    
    public ArrayList<CobWebObject> objects(TypeObject type) {
        ArrayList<CobWebObject> ret = new ArrayList<>();
        for (CobWebObject e : _objects) {
            if (e.type() == type) {
                ret.add(e);
            }
        }
        return ret;
    }
}
