package nl.tritewolf.tritemenus.menu.type.types;

import nl.tritewolf.tritemenus.menu.type.MenuType;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;

public final class ChestMenuType implements MenuType {

    @Override
    public @NotNull InventoryType type() {
        return InventoryType.CHEST;
    }

    @Override
    public int maxRows() {
        return 6;
    }

    @Override
    public int maxColumns() {
        return 9;
    }
}