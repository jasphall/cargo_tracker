package com.jasphall.cargo_tracker.domain.model.voyage;

import org.apache.commons.lang.Validate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Numer rejsu
 */
@Embeddable
public class VoyageNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "voyage_number")
    @NotNull
    private String number;

    public VoyageNumber() {}

    public VoyageNumber(String number) {
        Validate.notNull(number, "Number cannot be null.");
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof VoyageNumber)) {
            return false;
        }

        VoyageNumber other = (VoyageNumber) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    boolean sameValueAs(VoyageNumber other) {
        return other != null && this.number.equals(other.number);
    }

}
