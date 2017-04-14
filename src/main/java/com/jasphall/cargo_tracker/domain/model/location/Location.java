package com.jasphall.cargo_tracker.domain.model.location;

import org.apache.commons.lang.Validate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Lokacja nieznana
     */
    public static final Location UNKNOWN = new Location(
            new UnLocode("XXXXX"), "Unknown location");

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Kod
     */
    @Embedded
    private UnLocode unLocode;

    /**
     * Nazwa
     */
    @NotNull
    private String name;

    public Location() {}

    public Location(UnLocode unLocode, String name) {
        Validate.notNull(unLocode);
        Validate.notNull(name);

        this.unLocode = unLocode;
        this.name = name;
    }

    public boolean sameIdentityAs(Location other) {
        return this.unLocode.sameValueAs(other.unLocode);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        return sameIdentityAs(other);
    }

    @Override
    public int hashCode() {
        return unLocode.hashCode();
    }

}
