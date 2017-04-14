package com.jasphall.cargo_tracker.domain.model.location;

import org.apache.commons.lang.Validate;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Kod lokacyjny
 */
@Embeddable
public class UnLocode implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String UNLOCODE_PATTERN = "[a-zA-z]{2}[a-zA-Z2-9]{3}";

    private static final java.util.regex.Pattern VALID_UNLOCODE_PATTERN = java.util.regex.Pattern.compile(UNLOCODE_PATTERN);

    @NotNull
    @Pattern(regexp = UNLOCODE_PATTERN)
    private String unlocode;

    public UnLocode() {}

    public UnLocode(String countryAndLocation) {
        Validate.notNull(countryAndLocation, "Country and location cannot be null.");
        Validate.isTrue(VALID_UNLOCODE_PATTERN.matcher(countryAndLocation).matches(),
                countryAndLocation + " is not a valid UN/LOCODE (doesn't match pattern).");

        this.unlocode = countryAndLocation.toUpperCase();
    }

    boolean sameValueAs(UnLocode other) {
        return other != null && this.unlocode.equals(other.unlocode);
    }

    @Override
    public int hashCode() {
        return unlocode.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        UnLocode other = (UnLocode) obj;
        return sameValueAs(other);
    }
}
