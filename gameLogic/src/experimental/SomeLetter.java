/*
 * SomeLetter.java
 *
 * Created on July 14, 2007, 8:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package experimental;

/**
 *
 * @author serge
 */
public class SomeLetter {
    private Character sl = null;
    /** Creates a new instance of SomeLetter */
    public SomeLetter() {
    }
    public SomeLetter(Character ch) {
        sl = ch;
    }
    public Character getSl() {
        return sl;
    }
    
    public void setSl(Character sl) {
        this.sl = sl;
    }
    
}
