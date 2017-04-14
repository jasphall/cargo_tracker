package com.jasphall.cargo_tracker.domain.model.voyage;

public interface VoyageRepository {

    Voyage find(VoyageNumber voyageNumber);

}
