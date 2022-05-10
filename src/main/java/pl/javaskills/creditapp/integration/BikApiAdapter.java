package pl.javaskills.creditapp.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.bik.BikApi;
import pl.javaskills.creditapp.core.bik.ErrorResponse;
import pl.javaskills.creditapp.core.bik.ScoringRequest;
import pl.javaskills.creditapp.core.bik.ScoringResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static pl.javaskills.creditapp.core.utils.Constants.BIK_API_URI;

public class BikApiAdapter implements BikApi {

    private static final Logger log = LoggerFactory.getLogger(BikApiAdapter.class);

    private final String api_key;
    {
        this.api_key = System.getenv("API_KEY");
        if (StringUtils.isEmpty(this.api_key))
            throw new IllegalStateException("Api key is not set");
    }

    @Override
    public ScoringResponse getScoring(ScoringRequest request)  {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClient.newBuilder().build();

        try {
        String body = objectMapper.writeValueAsString(request);
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(body);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(BIK_API_URI))
                .POST(bodyPublisher)
                .header("x-token", this.api_key)
                .header("Content-Type", "application/json")
                .build();
        log.info((httpRequest.toString()));
        HttpResponse.BodyHandler<String> tBodyHandler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> httpResponse = client.send(httpRequest,tBodyHandler);
        String responseJson = httpResponse.body();

        if(httpResponse.statusCode() != 200){
            ErrorResponse errorResponse = objectMapper.readValue(responseJson, ErrorResponse.class);
            log.error(errorResponse.getError());
            throw new IllegalStateException(errorResponse.getError());
        }

        return objectMapper.readValue(responseJson, ScoringResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Unexpected error during communication with BIK API");
        }
    }
}
