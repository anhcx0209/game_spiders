/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders.model;

import java.util.ArrayList;

/**
 * Game's level
 * Hard - 
 * Easy - 
 * Medium - 
 * @author anhcx
 */
public class Level {
    
    int _baseSize;
    
    /**
     * create a hard level
     * base size = 15
     * @return
     */
    public static Level hard() {
        Level l = new Level();
        l._baseSize = 15;
        
        return l;
    }
    
    /**
     * create easy level
     * base = 6
     * @return
     */
    public static Level easy() {
        Level l = new Level();
        l._baseSize = 6;
        
        return l;
    }
    
    /**
     * create a medium level
     * base size = 10
     * @return
     */
    public static Level medium() {
        Level l = new Level();
        l._baseSize = 10;
        
        return l;
    }
    
}
