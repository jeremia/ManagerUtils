package jeremiaMorling.utils.managers;

import java.util.Stack;
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Item;
import javax.microedition.midlet.MIDlet;

/**
 * 
 *
 * @author Jeremia M�rling
 */
public class DM {

    private static Display display;
    private static Stack displayStack;
    //private static String stackTrace;
    private static Vector log = new Vector();

    public static final int TIMEOUT_ALMOST_FOREVER = Integer.MAX_VALUE;

    private DM() {
    }

    public static void init( MIDlet midlet ) {
        display = Display.getDisplay( midlet );
        displayStack = new Stack();
    }
    
    public static void add( Displayable displayable ) {
        displayStack.push( displayable );
        display.setCurrent( displayable );
    }
    
    public static void add( Displayable displayable, boolean removeCurrent ) {
        if( removeCurrent )
            displayStack.pop();
        
        add( displayable );
    }
    
    public static int getDisplayStackSize() {
        return displayStack.size();
    }
    
    public static void back() {
        back( false );
    }
    
    public static void back( int screensToRemove ) {
        back( false, screensToRemove );
    }
    
    public static void back( boolean refreshNeeded ) {
        back( refreshNeeded, 1 );
    }

    public static void back( boolean refreshNeeded, int screensToRemove ) {
        if( screensToRemove < 1 )
            return;
        pop( screensToRemove );
        
        Displayable displayable = (Displayable)displayStack.peek();
        display.setCurrent( displayable );
        if( displayable instanceof IFocusable ) {
            ((IFocusable)displayable).focusGained( refreshNeeded );
        }
    }
    
    public static void pop() {
        pop( 1 );
    }
    
    public static void pop( int screensToRemove ) {
        for( int i=0; i<screensToRemove; i++ ) {
            if( displayStack.size() == 1 )
                break;
            displayStack.pop();
        }
    }

    public static void error( Exception e, String sendingMethod ) {
        //e.printStackTrace();
        StringBuffer errorString = new StringBuffer();
        errorString.append( sendingMethod );
        errorString.append( "\n\n" );
        if ( e.getMessage() != null ) {
            errorString.append( e.getMessage() );
            errorString.append( "\n\n" );
        }
        errorString.append( e.toString() );
        errorString.append( "\n\n" );

        errorString.append( ExceptionManager.getPhases() );

        //Alert error = new Alert( "Error", errorString.toString(), null, AlertType.ERROR );
        //error.setTimeout( Alert.FOREVER );
        add( new MessageForm( "Error", errorString.toString() ) );
    }

    public static void testMessage( String message ) {
        Alert msg = new Alert( "Message", message, null, AlertType.INFO );
        msg.setTimeout( Alert.FOREVER );
        display.setCurrent( msg );
    }

    public static void newAlert( String title, String alertText, Image alertImage, int timeOut ) {
        Alert alert = new Alert( title, alertText, alertImage, null );
        alert.setTimeout( timeOut );
        display.setCurrent( alert );
    }
    
    public static void newAlert( String title, String alertText, Image alertImage ) {
        newAlert( title, alertText, alertImage, Alert.FOREVER );
    }

    /*public static void releaseResources() {
    displayStack.removeAllElements();
    Display.getDisplay( midlet ).setCurrent( null );
    }*/
    public static void minimize() {
        display.setCurrent( null );
    }
    
    public static void add( Displayable displayable, Item item  ) {
        displayStack.push( displayable );
        setFocus( item );
    }
    
    public static void setFocus( Item item ) {
        display.setCurrentItem( item );
    }

    /*public static void setStackTrace( String stackTrace ) {
        DisplayManager.stackTrace = stackTrace;
    }*/
    
    public static void log( String logMessage ) {
        log.addElement( logMessage );
        showLog();
    }

    public static void showLog() {
        StringBuffer logMessages = new StringBuffer();
        for( int i=0; i<log.size(); i++ ) {
            logMessages.append( log.elementAt( i ).toString() );
            logMessages.append( "\n\n ");
        }

        MessageForm logForm = new MessageForm( "Log", logMessages.toString() );
        add( logForm );
    }
}
