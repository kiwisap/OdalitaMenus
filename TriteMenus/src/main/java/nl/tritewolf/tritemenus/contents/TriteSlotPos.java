package nl.tritewolf.tritemenus.contents;

import lombok.Getter;

@Getter
public class TriteSlotPos {

    private final int row;
    private final int column;

    public TriteSlotPos(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public TriteSlotPos(int slot) {
        if (slot >= 0 && slot <= 8) {
            this.row = 0;
            this.column = slot;
        } else if (slot >= 9 && slot <= 17) {
            this.row = 1;
            this.column = slot - 9;
        } else if (slot >= 18 && slot <= 26) {
            this.row = 2;
            this.column = slot - 18;
        } else if (slot >= 27 && slot <= 35) {
            this.row = 3;
            this.column = slot - 27;
        } else if (slot >= 36 && slot <= 44) {
            this.row = 4;
            this.column = slot - 36;
        } else if (slot >= 45 && slot <= 53) {
            this.row = 5;
            this.column = slot - 45;
        } else {
            this.row = 0;
            this.column = 0;
        }
    }

    public static TriteSlotPos of(int slot) {
        return new TriteSlotPos(slot);
    }
}
