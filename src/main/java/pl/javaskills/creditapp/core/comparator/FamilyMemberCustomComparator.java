package pl.javaskills.creditapp.core.comparator;

import pl.javaskills.creditapp.core.model.FamilyMember;

import java.util.Comparator;

public class FamilyMemberCustomComparator implements Comparator<FamilyMember> {

    @Override
    public int compare(FamilyMember o1, FamilyMember o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
