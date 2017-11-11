import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.test.TestInterface;

import javax.xml.parsers.ParserConfigurationException;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Abu on 11/11/2017.
 */
public class FlickrConnector {

    String apiKey = "154f99e0a415763dfb197c366d527445";
    String sharedSecret = "6662d57b908145bb";
    
    public Flickr getFlickr(){
        try{
            return new Flickr(apiKey, sharedSecret, new REST());

        } catch (ParserConfigurationException pce){
            System.out.println("pce.getMessage() = " + pce.getMessage());
            return null;
        }
    }
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }
}
