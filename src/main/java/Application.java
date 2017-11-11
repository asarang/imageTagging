
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;

import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.flickr4java.flickr.uploader.Uploader;
import org.scribe.model.Token;
import org.scribe.model.Verifier;

public class Application {
    public Application() {
    }

    public static void main(String[] args) {
        try {
            String photoLocation = "/Users/Abu/tmp/tmp.jpg";
            File photoFile = new File("/Users/Abu/tmp/tmp.jpg");

//            ClarifaiConnector connector = new ClarifaiConnector();
//            List<Concept> concepts =  connector.getTagsFromUrl("https://scripts.njae.me.uk/festive-road-pictures/full-size/2017/Harminder%202017/29341669790_df9c56c6f4_k.jpg");
//            for(Concept concept: concepts){
//                System.out.println(concept.name());
//            }
//            ByteArrayOutputStream baos = Utils.getPhotoStream(photoLocation);

            byte[] data = Files.readAllBytes(photoFile.toPath());


            FlickrConnector flickrConnector = new FlickrConnector();
            Flickr flickr = flickrConnector.getFlickr();

            Flickr.debugStream = false;
            AuthInterface authInterface = flickr.getAuthInterface();

            Scanner scanner = new Scanner(System.in);
            Token token = authInterface.getRequestToken();
            System.out.println("token: " + token);

            String url = authInterface.getAuthorizationUrl(token, Permission.WRITE);
            System.out.println("Follow this URL to authorise yourself on Flickr");
            System.out.println(url);
            System.out.println("Paste in the token it gives you:");
            System.out.print(">>");

            String tokenKey = scanner.nextLine();
            scanner.close();

            Token requestToken = authInterface.getAccessToken(token, new Verifier(tokenKey));
            System.out.println("Authentication success");

            Auth auth = authInterface.checkToken(requestToken);

            // This token can be used until the user revokes it.
            System.out.println("Token: " + requestToken.getToken());
            System.out.println("Secret: " + requestToken.getSecret());
            System.out.println("nsid: " + auth.getUser().getId());
            System.out.println("Realname: " + auth.getUser().getRealName());
            System.out.println("Username: " + auth.getUser().getUsername());
            System.out.println("Permission: " + auth.getPermission().getType());



            Uploader uploader =  flickr.getUploader();
            UploadMetaData uploadMetaData = new UploadMetaData();
            uploadMetaData.setTitle("Test Image");
            uploader.upload(data,uploadMetaData);

        } catch (Exception fe){
            System.out.println(fe.getMessage());
        }
    }




}

