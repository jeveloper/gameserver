/*
 * DrawList.java
 *
 * Created on March 21, 2007, 10:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class DrawList implements java.io.Serializable{
    
    
    // generating Ids is necessary , lets call it draw sequence , since it starts from 1 to N
    
    private List<Draw> list ;
    
    /** Creates a new instance of DrawList */
    public DrawList() {
        list = new ArrayList();
    }
    
    public void addDraw(Draw draw){
        list.add(draw);
    }
    
    public void dispose(){
        this.list.clear();
    }
    
    public List<Draw> getList() {
        return list;
    }
    
    public void setList(List<Draw> list) {
        this.list = list;
    }
    
    
    
    
}
