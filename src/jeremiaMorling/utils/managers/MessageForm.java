/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jeremiaMorling.utils.managers;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

/**
 *
 * @author Jeremia
 */
class MessageForm extends Form implements CommandListener {
    MessageForm( String title, String bodyText ) {
        super( title );
        StringItem body = new StringItem( null, bodyText );
        body.setFont( Font.getFont( Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL ) );
        append( body );
        
        addCommand( new Command( "OK", Command.OK, 0 ) );
        setCommandListener( this );
    }

    public void commandAction( Command c, Displayable d ) {
        DM.back();
    }
}
