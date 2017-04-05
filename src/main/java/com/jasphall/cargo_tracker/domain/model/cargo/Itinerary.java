package com.jasphall.cargo_tracker.domain.model.cargo;

import org.apache.commons.lang.Validate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Plan podróży.
 */
@Embeddable
public class Itinerary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Zwraca pusty plan podróży (NullObject pattern)
     */
    public static final Itinerary EMPTY_ITINERARY = new Itinerary();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cargo_id")
//    @OrderBy("loadTime")
    @Size(min = 1)
    private List<Leg> legs = Collections.emptyList();

    public Itinerary() {}

    public Itinerary(List<Leg> legs) {
        Validate.notEmpty(legs);
        Validate.noNullElements(legs);

        this.legs = legs;
    }

    public List<Leg> getLegs() {
        return Collections.unmodifiableList(this.legs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Itinerary itinerary = (Itinerary) o;

        return legs != null ? legs.equals(itinerary.legs) : itinerary.legs == null;
    }

    @Override
    public int hashCode() {
        return legs != null ? legs.hashCode() : 0;
    }
}
