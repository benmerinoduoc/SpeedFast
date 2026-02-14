package model;

public enum TipoPedido {
    COMIDA,
    ENCOMIENDA,
    EXPRESS;

    public static TipoPedido fromLabel(String label) {
        if (label == null) return COMIDA;
        String v = label.trim().toLowerCase();
        return switch (v) {
            case "comida" -> COMIDA;
            case "encomienda" -> ENCOMIENDA;
            case "express" -> EXPRESS;
            default -> COMIDA;
        };
    }

    public String toLabel() {
        return switch (this) {
            case COMIDA -> "comida";
            case ENCOMIENDA -> "encomienda";
            case EXPRESS -> "express";
        };
    }
}
