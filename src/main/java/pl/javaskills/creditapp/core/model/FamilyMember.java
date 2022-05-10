package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.annotation.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class FamilyMember implements Comparable<FamilyMember>, Serializable {
    public static final long serialVersionUID = 1;
    @NotNull()
    private final String name;
    @NotNull()
    private final LocalDate birthDate;

    public FamilyMember(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public int compareTo(FamilyMember o) {
        return o.birthDate.compareTo(this.birthDate);
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "FamilyMember{" +
                "name='" + name + '\'' +
                ", age=" + getAge() +
                '}';
    }
}
