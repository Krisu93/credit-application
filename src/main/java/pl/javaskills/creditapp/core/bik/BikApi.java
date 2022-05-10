package pl.javaskills.creditapp.core.bik;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface BikApi {

    ScoringResponse getScoring(ScoringRequest request) ;
}
