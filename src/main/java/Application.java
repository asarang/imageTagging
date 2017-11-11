import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.uploader.UploadMetaData;
import com.aetrion.flickr.uploader.Uploader;
import java.io.File;
import java.nio.file.Files;

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
            Flickr f = flickrConnector.getFlickr();

            Uploader uploader =  f.getUploader();
            UploadMetaData uploadMetaData = new UploadMetaData();
            uploadMetaData.setTitle("Test Image");
            uploader.upload(data,uploadMetaData);

        } catch (Exception fe){
            System.out.println(fe.getMessage());
        }
    }




}

