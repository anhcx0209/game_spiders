/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders.events;

import java.util.EventListener;

/**
 *
 * @author anhcx
 */
public interface PlayerActionListener extends EventListener {
    public void playerMoved();
}
