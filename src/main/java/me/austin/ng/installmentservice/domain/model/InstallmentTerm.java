package me.austin.ng.installmentservice.domain.model;

public enum InstallmentTerm {
    THREE(3),
    SIX(6),
    NINE(9),
    TWELVE(12),
    EIGHTEEN(18),
    TWENTY_FOUR(24);

    private final int months;

    InstallmentTerm(int months) {
        this.months = months;
    }

    public int getMonths() {
        return months;
    }

    public static InstallmentTerm fromMonths(int months) {
        for (InstallmentTerm term : values()) {
            if (term.months == months) {
                return term;
            }
        }
        throw new IllegalArgumentException("Unsupported term: " + months + " months");
    }
}
