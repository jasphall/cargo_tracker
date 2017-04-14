package com.jasphall.cargo_tracker.domain.model.voyage;

import com.jasphall.cargo_tracker.domain.model.location.Location;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Klasa reprezentuje podróż statkiem z jednej lokacji do drugiej
 */
@Entity
@Table(name = "carrier_movement")
public class CarrierMovement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Null Object Pattern
     */
    public static final CarrierMovement NONE = new CarrierMovement(Location.UNKNOWN, Location.UNKNOWN,
            new Date(0) , new Date(0));

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Lokacja początkowa
     */
    @ManyToOne
    @JoinColumn(name = "departure_location_id")
    @NotNull
    private Location departureLocation;

    /**
     * Lokacja docelowa
     */
    @ManyToOne
    @JoinColumn(name = "arrival_location_id")
    @NotNull
    private Location arrivalLocation;

    /**
     * Czas rozpoczęcia podróży
     */
    @Column(name = "departure_time")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date departureTime;

    /**
     * Czas zakończenia podróży
     */
    @Column(name = "arrival_time")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date arrivalTime;

    public CarrierMovement() {}

    public CarrierMovement(Location departureLocation, Location arrivalLocation, Date departureTime, Date arrivalTime) {
        Validate.noNullElements(new Object[] {departureLocation, arrivalLocation, departureTime, arrivalTime});
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
    }

    private boolean sameValueAs(CarrierMovement other) {
        return other != null &&
                new EqualsBuilder()
                    .append(this.departureLocation, other.departureLocation)
                    .append(this.arrivalLocation, other.arrivalLocation)
                    .append(this.departureLocation, other.departureLocation)
                    .append(this.arrivalTime, other.arrivalTime)
                    .isEquals();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarrierMovement that = (CarrierMovement) o;

        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.departureLocation)
                .append(this.departureTime)
                .append(this.arrivalLocation)
                .append(this.arrivalTime)
                .toHashCode();
    }

}
