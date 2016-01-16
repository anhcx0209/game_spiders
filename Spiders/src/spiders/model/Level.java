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
    int _numberOfComputer;
    
    /**
     * create a hard level
     * base size = 15
     * @return
     */
    public static Level hard() {
        Level l = new Level();
        l._baseSize = 15;
        l._numberOfComputer = 3;
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
        l._numberOfComputer = 1;
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
        l._numberOfComputer = 2;
        return l;
    }
    
}
