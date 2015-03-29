/*
 * PlayList.java
 *
 * Created on March 24, 2007, 9:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.beans;

import com.jeveloper.gameserver.core.IPlay;
import java.util.ArrayList;
import java.util.List;

/**
 * stores only objects of type Play
 * @author serge
 */
public class PlayList implements java.io.Serializable{
    
    private List<IPlay> list;
    
    /** Creates a new instance of PlayList */
    public PlayList() {
        list = new ArrayList();
    }

    public PlayList(int number_of_plays) {
        list = new ArrayList(number_of_plays);
    }
    
    public void addPlay(IPlay play){
        list.add(play);
    }
    
    public List<IPlay> getList() {
        return list;
    }

    public void setList(List<IPlay> list) {
        this.list = list;
    }
    
}
