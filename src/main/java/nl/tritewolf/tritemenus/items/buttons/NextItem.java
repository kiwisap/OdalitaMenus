package nl.tritewolf.tritemenus.items.buttons;

import lombok.Getter;
import lombok.Setter;
import nl.tritewolf.tritemenus.contents.SlotPos;
import nl.tritewolf.tritemenus.items.MenuItem;
import nl.tritewolf.tritemenus.menu.providers.MenuProvider;
import nl.tritewolf.tritemenus.pagination.Pagination;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class NextItem implements MenuItem {

    public static NextItem of(MenuProvider provider, Pagination pagination, ItemStack itemStack, boolean showOnLastPage) {
        return new NextItem(provider, pagination, itemStack, showOnLastPage);
    }

    public static NextItem of(MenuProvider provider, Pagination pagination, boolean showOnLastPage) {
        return new NextItem(provider, pagination, showOnLastPage);
    }

    public static NextItem of(MenuProvider provider, Pagination pagination, ItemStack itemStack) {
        return new NextItem(provider, pagination, itemStack);
    }

    public static NextItem of(MenuProvider provider, Pagination pagination) {
        return new NextItem(provider, pagination);
    }

    private final MenuProvider provider;
    private final Pagination pagination;
    private final ItemStack itemStack;
    private final boolean showOnLastPage;
    @Setter
    @Getter
    private SlotPos slot;

    protected NextItem(MenuProvider provider, Pagination pagination, ItemStack itemStack, boolean showOnLastPage) {
        this.provider = provider;
        this.pagination = pagination;
        this.showOnLastPage = showOnLastPage;
        this.itemStack = itemStack;
    }

    protected NextItem(MenuProvider provider, Pagination pagination, boolean showOnLastPage) {
        this.provider = provider;
        this.pagination = pagination;
        this.showOnLastPage = showOnLastPage;

        this.itemStack = new ItemStack(Material.ARROW);
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bNext page &8("));
        itemMeta.setLore(List.of(ChatColor.translateAlternateColorCodes('&', "&7Go to the next page.")));
        this.itemStack.setItemMeta(itemMeta);
    }

    protected NextItem(MenuProvider provider, Pagination pagination, ItemStack itemStack) {
        this(provider, pagination, itemStack, false);
    }

    protected NextItem(MenuProvider provider, Pagination pagination) {
        this(provider, pagination, false);
    }

    @Override
    public @NotNull ItemStack getItemStack() {
        if (!showOnLastPage && this.pagination.isLast()) {
            return new ItemStack(Material.AIR);
        }

        return this.itemStack;
    }

    @Override
    public @NotNull Consumer<InventoryClickEvent> onClick() {
        return (event) -> {
            if (!(event.getWhoClicked() instanceof Player)) return;
            if (this.pagination.isLast()) return;

            this.pagination.nextPage();
        };
    }
}