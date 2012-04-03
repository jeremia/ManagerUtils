/*
 * ExceptionUtils.java
 *
 * Created on 2 ������ 2007, 07:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package jeremiaMorling.utils.managers;

import java.util.Vector;

/**
 *
 * @author menny
 */
public class ExceptionManager {

    private static Vector ms_stack = new Vector();
    private static final int STACK_SIZE = 5;

    public static synchronized void PHASE( String phase ) {
        while (ms_stack.size() >= STACK_SIZE) {
            ms_stack.removeElementAt( 0 );
        }

        ms_stack.addElement( phase );
    }

    public static synchronized String getPhases() {
        String phases = "";
        for ( int i = ms_stack.size(); i > 0; i-- ) {
            phases += ms_stack.elementAt( i - 1 ) + "\n\r";
        }


        return phases;
    }
}
