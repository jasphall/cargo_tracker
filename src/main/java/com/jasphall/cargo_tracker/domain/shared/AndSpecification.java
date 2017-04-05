package com.jasphall.cargo_tracker.domain.shared;

/**
 * Specyfikacja typu AND, używana do tworzenia nowej specyfikacji z części wspólnej dwóch istniejących.
 */
public class AndSpecification<T> extends AbstractSpecification<T> {

    private Specification<T> specification1;
    private Specification<T> specification2;

    public AndSpecification(Specification<T> specification1, Specification<T> specification2) {
        this.specification1 = specification1;
        this.specification2 = specification2;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        return specification1.isSatisfiedBy(t) && specification2.isSatisfiedBy(t);
    }
}
