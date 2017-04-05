package com.jasphall.cargo_tracker.domain.shared;

/**
 * Interfejs specyfikacji.
 */
public interface Specification<T> {

    /**
     * Sprawdza, czy obiekt {@code t} spełnia specyfikację.
     * @param t
     * @return
     */
    boolean isSatisfiedBy(T t);

    /**
     * Tworzy nową specyfikację, która jest złączeniem przez AND bieżącej oraz podanej specyfikacji.
     * @param specification
     * @return
     */
    Specification<T> and(Specification<T> specification);

    /**
     * Tworzy nową specyfikację, która jest złączeniem przez OR bieżącej oraz podanej specyfikacji.
     * @param specification
     * @return
     */
    Specification<T> or(Specification<T> specification);

    /**
     * Tworzy nową specyfikację, która jest złączeniem przez NOT bieżącej oraz podanej specyfikacji.
     * @param specification
     * @return
     */
    Specification<T> not(Specification<T> specification);

}
