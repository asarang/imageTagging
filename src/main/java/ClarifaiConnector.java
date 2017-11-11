import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

import java.util.List;

public class ClarifaiConnector {
    ClarifaiClient client = new ClarifaiBuilder("cfc9d14b745b4e30be3a3061d332325e").buildSync();


    List<Concept> getTagsFromUrl(String url){
        List<ClarifaiOutput<Concept>> predictionResults =client.getDefaultModels().generalModel() // You can also do client.getModelByID("id") to get your custom models
                .predict()
                .withInputs(
                        ClarifaiInput.forImage(url))
                .executeSync()
                .get();

        return predictionResults.get(0).data();

    }


}

