package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.annotation.NotNull;
import pl.javaskills.creditapp.core.annotation.ValidationCollection;
import pl.javaskills.creditapp.core.annotation.ValidationObject;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import static pl.javaskills.creditapp.core.utils.Constants.DEFAULT_SYSTEM_ZONE_ID;

public class CreditApplication implements Serializable {
    public static final long serialVersionUID = 1;
    @NotNull
    private final String id;
    @NotNull
    @ValidationObject
    private final Person person;
    @NotNull
    @ValidationObject
    private final PurposeOfLoan purposeOfLoan;
    @NotNull
    @ValidationCollection
    private final Set<Guarantor> guarantors;
    private final transient ZoneId clientTimeZone;
    private final transient ZonedDateTime creationDateClientZone;
    private final transient Locale clientLocale;

    public CreditApplication(Person person, PurposeOfLoan purposeOfLoan) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID().toString();
        this.guarantors = new TreeSet<>();
        this.clientTimeZone = DEFAULT_SYSTEM_ZONE_ID;
        this.creationDateClientZone = ZonedDateTime.now(this.clientTimeZone);
        this.clientLocale = Locale.getDefault();
    }

    public CreditApplication(Person person, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID().toString();
        this.guarantors = guarantors;
        this.clientTimeZone = DEFAULT_SYSTEM_ZONE_ID;
        this.creationDateClientZone = ZonedDateTime.now(this.clientTimeZone);
        this.clientLocale = Locale.getDefault();
    }

    public CreditApplication(Person person, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors, ZoneId clientTimeZone, Locale clientLocale) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID().toString();
        this.guarantors = guarantors;
        this.clientTimeZone = clientTimeZone;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
        this.clientLocale = clientLocale;
    }

    public PurposeOfLoan getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public Person getPerson() {
        return this.person;
    }

    public String getId() {
        return id;
    }

    public Set<Guarantor> getGuarantors() {
        return guarantors;
    }

    public ZoneId getClientTimeZone() {
        return clientTimeZone;
    }

    public ZonedDateTime getCreationDateClientZone() {
        return creationDateClientZone;
    }

    public Locale getClientLocale() {
        return clientLocale;
    }

    @Override
    public String toString() {
        return "CreditApplication{" +
                "id='" + id + '\'' +
                ", person=" + person +
                ", purposeOfLoan=" + purposeOfLoan +
                ", guarantors=" + guarantors +
                ", clientTimeZone=" + clientTimeZone +
                ", creationDateClientZone=" + creationDateClientZone +
                ", clientLocale=" + clientLocale +
                '}';
    }
}
