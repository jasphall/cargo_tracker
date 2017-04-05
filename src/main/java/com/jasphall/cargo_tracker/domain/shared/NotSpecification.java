package com.jasphall.cargo_tracker.domain.shared;

/**
 * Specyfikacja typu NOT, używana do tworzenia nowej specyfikacji z negacji podanej specyfikacji.
 */
public class NotSpecification<T> extends AbstractSpecification<T> {

    private Specification<T> specification1;

    public NotSpecification(Specification<T> specification1) {
        this.specification1 = specification1;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        return !specification1.isSatisfiedBy(t);
    }

}
