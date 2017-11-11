import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Abu on 11/11/2017.
 */
public class Utils {
    public static ByteArrayOutputStream getPhotoStream(String photoLocation){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;
        URL url = null;
        try {
            url = new URL(photoLocation);
            is = url.openStream ();
            byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
            int n;

            while ( (n = is.read(byteChunk)) > 0 ) {
                baos.write(byteChunk, 0, n);
            }
        } catch (IOException e) {
            System.err.printf ("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
            e.printStackTrace ();
            // Perform any other exception handling that's appropriate.
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ioe){
                    System.err.printf ("Failed closing input stream  %s", ioe.getMessage());
                    ioe.printStackTrace ();

                }
            }
        }
        return baos;
    }
}
