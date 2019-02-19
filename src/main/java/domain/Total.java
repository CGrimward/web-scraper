package domain;

import java.math.BigDecimal;

public class Total {

    private BigDecimal gross;
    private BigDecimal vat;

    public Total(BigDecimal gross, BigDecimal vat) {
        this.gross = gross;
        this.vat = vat;
    }

    public BigDecimal getGross() {
        return gross;
    }

    public BigDecimal getVat() {
        return vat;
    }
}
