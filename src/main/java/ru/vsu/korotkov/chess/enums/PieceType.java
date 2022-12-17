package ru.vsu.korotkov.chess.enums;

public enum PieceType {
    WKING(0,true),
    WQUEEN(1,true),
    WROOK(2,true),
    WBISHOPS(3,true),
    WKNIGHT(4,true),
    WPAWN(5,true),
    BKING(0,false),
    BQUEEN(1,false),
    BROOK(2,false),
    BBISHOPS(3,false),
    BKNIGHT(4,false),
    BPAWN(5,false);

    private int index;
    private boolean isWhite;

    PieceType(int index, boolean isWhite) {
        this.index = index;
        this.isWhite = isWhite;
    }

    public int getIndex() {
        return index;
    }

    public boolean isWhite() {
        return isWhite;
    }
}
