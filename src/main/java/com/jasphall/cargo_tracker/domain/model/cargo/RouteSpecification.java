package com.jasphall.cargo_tracker.domain.model.cargo;

import com.jasphall.cargo_tracker.domain.model.location.Location;
import com.jasphall.cargo_tracker.domain.shared.AbstractSpecification;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Opis trasy. Mówi o tym, skąd ładunek rusza, jaki jest jego cel oraz deadline dotarcia do celu. Można to traktować
 * jako wymagania klienta.
 */
@Embeddable
public class RouteSpecification extends AbstractSpecification<Itinerary> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Początek trasy w specyfikacji
     */
    @ManyToOne
    @JoinColumn(name = "spec_origin_id", updatable = false)
    private Location origin;

    /**
     * Koniec trasy w specyfikacji
     */
    @ManyToOne
    @JoinColumn(name = "spec_destination_id", updatable = false)
    private Location destination;

    /**
     * Termin dotarcia ładunku do celu
     */
    @Column(name = "spec_arrival_deadline")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date arrivalDeadline;

    public RouteSpecification() {}

    public RouteSpecification(Location origin, Location destination, Date arrivalDeadline) {
        Validate.notNull(origin, "Początek trasy jest wymagany");
        Validate.notNull(destination, "Koniec trasy jest wymagany");
        Validate.notNull(arrivalDeadline, "Termin dotarcia ładunku do celu jest wymagany");
//        Validate.isTrue(!origin.sameIdentityAs(destination), "Początek i koniec trasy nie mogą być takie same");

        this.origin = origin;
        this.destination = destination;
        this.arrivalDeadline = arrivalDeadline;
    }

    @Override
    public boolean isSatisfiedBy(Itinerary itinerary) {
        return false;
    }

    public Location getOrigin() {
        return origin;
    }

    public Location getDestination() {
        return destination;
    }

    public Date getArrivalDeadline() {
        return arrivalDeadline;
    }

    private boolean sameValueAs(RouteSpecification other) {
        return other != null
                && new EqualsBuilder().append(this.origin, other.origin)
                .append(this.destination, other.destination)
                .append(this.arrivalDeadline, other.getArrivalDeadline())
                .isEquals();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteSpecification that = (RouteSpecification) o;

        if (origin != null ? !origin.equals(that.origin) : that.origin != null) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        return arrivalDeadline != null ? arrivalDeadline.equals(that.arrivalDeadline) : that.arrivalDeadline == null;
    }

    @Override
    public int hashCode() {
        int result = origin != null ? origin.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (arrivalDeadline != null ? arrivalDeadline.hashCode() : 0);
        return result;
    }
}
