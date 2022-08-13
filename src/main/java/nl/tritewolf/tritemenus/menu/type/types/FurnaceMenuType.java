package nl.tritewolf.tritemenus.menu.type.types;

import nl.tritewolf.tritemenus.menu.type.MenuType;
import nl.tritewolf.tritemenus.menu.type.SupportedFeatures;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Set;

public final class FurnaceMenuType implements MenuType {

    @Override
    public @NotNull InventoryType type() {
        return InventoryType.FURNACE;
    }

    @Override
    public int maxRows() {
        return 0;
    }

    @Override
    public int maxColumns() {
        return 0;
    }

    @Override
    public @NotNull Collection<@NotNull Integer> otherSlots() {
        return Set.of(0, 1, 2);
    }

    @Override
    public @NotNull Collection<@NotNull SupportedFeatures> disallowedFeatures() {
        return Set.of(
                SupportedFeatures.PAGINATION,
                SupportedFeatures.SCROLLABLE
        );
    }
}