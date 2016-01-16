package spiders.model;

import java.util.ArrayList;
import java.util.Random;
import spiders.model.CobWebObject.TypeObject;
import spiders.navigations.Position;

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
        _mark = new boolean[_size + 1][_size + 1];
    }
    
    public int size() {
        return _size;
    }
    
    public CobWeb() {
        
    }
    
    // ------------------- get free position --------
    boolean[][] _mark;
    
    private ArrayList<Position> freePosition() {
        ArrayList<Position> ret = new ArrayList<>();
        for (int i = 1; i < _size + 1; i++) {
            for (int j = 1; j < _size + 1; j++) {
                if (_mark[i][j] == false)
                    ret.add(new Position(i, j));
            }
        }
        
        return ret;
    }
    
    public Position getFreePosition() {
        ArrayList<Position> freePos = freePosition();
        
        if (freePos.size() > 0) {
            Random rand = new Random();
            int n = rand.nextInt(_size * _size) + 1;
            return freePos.get(n);
        }
        
        return null;
    }
      
    // ------------------- objects on cobweb --------
    ArrayList<CobWebObject> _objects = new ArrayList<>();
    
    public boolean addObject(CobWebObject obj) {
        if (obj.position() == null)
            return false;
        
        _objects.add(obj);
        _mark[obj.position().row()][obj.position().column()] = true;
        return true;
    }
    
    public void removeObject(CobWebObject obj) {
        if (_objects.contains(obj)) {
            _objects.remove(obj);
            _mark[obj.position().row()][obj.position().column()] = false;
        }
            
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
