package ru.vsu.korotkov.chess.figures;

import ru.vsu.korotkov.chess.enums.PieceType;

public class King extends Figure {


    private boolean isChecked = false;

    public King(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord, (King) gameField[coord.y][coord.x]);
        if (isWhite) {
            pieceType = PieceType.WKING;
        } else pieceType = PieceType.BKING;
    }

    @Override
    public boolean canMove(int x, int y) {
        if (Math.abs(x - coord.x) > 1 && Math.abs(y - coord.y) > 1) {
            return false;
        }

        return true;
    }


    public boolean isChecked() {
        Figure figure;
        for (int z = -1; z < 2; z += 2) {
            for (int k = -1; k < 2; k += 2) {

                for (int i = 1; Math.max(coord.x + k * i, coord.y + z * k * i) < gameField.length && Math.min(coord.x + k * i, coord.y + z * k * i) >= 0; i++) {
                    figure = gameField[coord.y + z * k * i][coord.x + k * i];
                    if (figure == null) {
                        continue;
                    }

                    if (figure.isWhite == this.isWhite) {
                        break;
                    }
                    if (figure.toString().equals("Q") || figure.toString().equals("B")) {
                        return true;
                    }

                }
            }
        }

        for (int z = 0; z <= 1; z++) {
            for (int k = -1; k < 2; k += 2) {
                for (int i = 1; Math.max(coord.x + (1 - z) * k * i, coord.y + z * k * i) < gameField.length && Math.min(coord.x + (1 - z) * k * i, coord.y + z * k * i) >= 0; i++) {
                    figure = gameField[coord.y + z * k * i][coord.x + (1 - z) * k * i];
                    if (figure == null) {
                        continue;
                    }

                    if (figure.isWhite == this.isWhite) {
                        break;
                    }
                    if (figure.toString().substring(1).equals("Q") || figure.toString().substring(1).equals("R")) {
                        System.out.println("King is checked");
                        return true;
                    }

                }
            }
        }


        return false;
    }


    @Override
    public String toString() {
        return super.toString() + "KG";
    }
}
