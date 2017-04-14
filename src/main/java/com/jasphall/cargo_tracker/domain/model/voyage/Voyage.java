package com.jasphall.cargo_tracker.domain.model.voyage;

import com.jasphall.cargo_tracker.domain.model.location.Location;
import org.apache.commons.lang.Validate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Reprezentacja rejsu
 */
@Entity
public class Voyage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Null Object Pattern
     */
    public static final Voyage NONE = new Voyage(new VoyageNumber(""), VoyageSchedule.EMPTY);

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @NotNull
    private VoyageNumber voyageNumber;

    @Embedded
    @NotNull
    private VoyageSchedule schedule;

    public Voyage() {}

    public Voyage(VoyageNumber voyageNumber, VoyageSchedule schedule) {
        Validate.notNull(voyageNumber, "Voyage number is required");
        Validate.notNull(schedule, "Schedule is required");

        this.voyageNumber = voyageNumber;
        this.schedule = schedule;
    }

    @Override
    public int hashCode() {
        return voyageNumber.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Voyage)) {
            return false;
        }

        Voyage that = (Voyage) o;

        return sameIdentityAs(that);
    }

    public boolean sameIdentityAs(Voyage other) {
        return other != null
                && this.voyageNumber.sameValueAs(other.voyageNumber);
    }

    public VoyageNumber getVoyageNumber() {
        return voyageNumber;
    }

    public VoyageSchedule getSchedule() {
        return schedule;
    }

    public static class Builder {

        private List<CarrierMovement> carrierMovements = new ArrayList<>();
        private VoyageNumber voyageNumber;
        private Location departureLocation;

        public Builder(VoyageNumber voyageNumber, Location departureLocation) {
            Validate.notNull(voyageNumber, "Voyage number is required");
            Validate.notNull(departureLocation, "Departure location is required");

            this.voyageNumber = voyageNumber;
            this.departureLocation = departureLocation;
        }

        public Builder addMovement(Location arrivalLocation, Date departureTime, Date arrivalTime) {
            carrierMovements.add(new CarrierMovement(departureLocation, arrivalLocation, departureTime, arrivalTime));
            this.departureLocation = arrivalLocation;

            return this;
        }

        public Voyage build() {
            return new Voyage(voyageNumber, new VoyageSchedule(carrierMovements));
        }

    }

}
