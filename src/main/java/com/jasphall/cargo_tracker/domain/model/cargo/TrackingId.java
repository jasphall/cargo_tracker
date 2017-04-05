package com.jasphall.cargo_tracker.domain.model.cargo;

import org.apache.commons.lang.Validate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Jednoznacznie identyfikuje konkretny ładunek. Jest generowany automatycznie przez aplikację.
 */
@Embeddable
public class TrackingId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "tracking_id", unique = true, updatable = false)
    private String id;

    public TrackingId() {}

    public TrackingId(String id) {
        Validate.notNull(id);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    boolean sameValueAs(TrackingId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackingId that = (TrackingId) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
