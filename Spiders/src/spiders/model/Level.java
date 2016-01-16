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
    
    private int _baseSize;
    private int _numberOfComputer;
    private int _numberBug;
    
    /**
     * @return number of bug must be generate per time.
     */
    public int numberBug() {
        return _numberBug;
    }
    
    /**
     * @return number of computer in start of game
     */
    public int numberCom() {
        return _numberOfComputer;
    }
    
    /**
     * @return size of game field
     */
    public int baseSize() {
        return _baseSize;
    }
    
    /**
     * create a hard level
     * base size = 15
     * @return
     */
    public static Level hard() {
        Level l = new Level();
        l._baseSize = 12;
        l._numberOfComputer = 3;
        l._numberBug = 20;
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
        l._numberOfComputer = 2;
        l._numberBug = 6;
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
        l._numberBug = 8;
        return l;
    }
    
}
