package jeremiaMorling.utils.managers;

import java.io.IOException;
import java.util.Hashtable;

import javax.microedition.lcdui.Image;

public class ResourceManager {

    private static Hashtable images = new Hashtable();

    protected ResourceManager() {}

    public static Image getImage( String path ) {
        return getImage( path, false );
    }
    
    public static Image getImage( String path, boolean loadFromDisk ) {
        if( loadFromDisk )
            return loadImage( path );
        else {
            Image result = (Image) images.get( path );
            if( result == null ) {
                return loadImage( path );
            } else {
                return result;
            }
        }
    }
    
    private static Image loadImage( String path ) {
        Image result = null;
        
        try {
            result = Image.createImage( path );
            images.put( result, path );
        } catch ( IOException ex ) {
            DM.error( ex, "ResourceManager: getImage()" );
        }
        
        return result;
    }

    public static void emptyCache() {
        images.clear();
    }
}
