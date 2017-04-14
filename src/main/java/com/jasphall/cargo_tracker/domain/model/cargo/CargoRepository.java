package com.jasphall.cargo_tracker.domain.model.cargo;

import java.util.List;

public interface CargoRepository {

    Cargo find(TrackingId trackingId);

    List<Cargo> findAll();

    void store(Cargo cargo);

    TrackingId nextTrackingId();

}
