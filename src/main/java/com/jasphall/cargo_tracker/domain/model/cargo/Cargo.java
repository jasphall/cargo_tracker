package com.jasphall.cargo_tracker.domain.model.cargo;

import com.jasphall.cargo_tracker.domain.model.location.Location;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Ładunek (przesyłka, towar). Jest to główna klasa w modelu domenowym. Jest także rootem agregatu
 * Cargo-Itinerary-Leg-Delivery-RouteSpecification.
 *
 * Ładunek jest identyfikowany przez unikatowy trackingId i zawsze posiada początek (origin) oraz specifikację trasy.
 * Cykl życia ładunku zaczyna się wraz z procedurą rezerwacji (wtedy też nadawany jest trackingId). Podczas krótkiego
 * okresu czasu, pomiędzy rezerwacją a pierwszym nadaniem, ładunek nie posiada planu podróży (itinerary).
 *
 * Urzędnik ds rezerwacji prosi o listę możliwych tras, pasujących do specyfikacji i przypisuje ładunek do jednej trasy.
 * Trasa, do której został przypisany ładunek jest opisana przez plan podróży.
 *
 * Trasa ładunku może zostać zmieniona w trakcie transportu na życzenie klienta. W takim przypadku podawana jest nowa
 * trasa (jest to wymagane). Stary plan podróży (który jest ValueObjectem) jest odrzucany, a nowy jest podłączany.
 *
 * Może się zdarzyć, że trasa ładunku została omyłkowo pomylona. W takim przypadku, należy powiadomić odpowiedni
 * personel oraz wywołać procedurę ponownego trasowania.
 *
 * Gdy ładunek jest obsługiwany, zmienia się jego status dostawy. Wszystkie informacje dotyczące dostawy ładunku zawarte
 * są w klasie Delivery, która jest ValueObjectem. Obiekt ten jest zastępowany, gdy ładunek jest ogarniany przez
 * asynchroniczny event.
 *
 * Na dostawę ładunku mogą mieć wpływ zmiany tras, na przykład gdy specyfikacja trasy się zmieni lub ładunek zostanie
 * przypisany do nowej trasy. W tym przypadku aktualizacja dostawy odbywa się synchronicznie w ramach agregatu Ładunek.
 *
 * Cykl życia ładunku kończy się w momencie zażądania ładunku przez klienta.
 *
 * Agregat ładunku i cały domain domeny są zbudowane w celu rozwiązania problemu rezerwacji i śledzenia ładunków.
 * Wszystkie ważne reguły biznesowe określające, czy ładunek nie został przypadkiem wysłany w złą trasę, jaki jest
 * obecny status przesyłki są określone w tym agregacie.
 */
@Entity
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Unikatowy identyfikator ładunku
     */
    @Embedded
    private TrackingId trackingId;

    /**
     * Lokalizacja początkowa
     */
    @ManyToOne
    @JoinColumn(name = "origin_id", updatable = false)
    private Location origin;

    /**
     * Opis trasy
     */
    @Embedded
    private RouteSpecification routeSpecification;

    /**
     * Plan podróży
     */
    @Embedded
    private Itinerary itinerary;

    /**
     * Rzeczywisty transport ładunku
     */
    @Embedded
    private Delivery delivery;

    public Cargo() {}



}
