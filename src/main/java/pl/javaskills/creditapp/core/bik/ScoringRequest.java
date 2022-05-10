package pl.javaskills.creditapp.core.bik;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ScoringRequest {
    @JsonInclude(Include.NON_NULL)
    private String pesel;
    @JsonInclude(Include.NON_NULL)
    private String nip;

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
