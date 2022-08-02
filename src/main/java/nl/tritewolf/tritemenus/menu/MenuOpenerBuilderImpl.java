package nl.tritewolf.tritemenus.menu;

import lombok.AccessLevel;
import lombok.Getter;
import nl.tritewolf.tritemenus.items.ItemProcessor;
import nl.tritewolf.tritemenus.menu.providers.MenuProvider;
import nl.tritewolf.tritemenus.menu.providers.MenuProviderLoader;
import nl.tritewolf.tritemenus.utils.Pair;
import nl.tritewolf.tritemenus.utils.Triple;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Getter(AccessLevel.PACKAGE)
final class MenuOpenerBuilderImpl<P extends MenuProvider> implements MenuOpenerBuilder {

    @Getter(AccessLevel.NONE)
    private final MenuProcessor menuProcessor;
    @Getter(AccessLevel.NONE)
    private final ItemProcessor itemProcessor;

    private final P provider;
    private final Player player;
    private final MenuProviderLoader<P> providerLoader;

    private final Map<String, Integer> paginationPages = new HashMap<>();
    private final Map<String, Pair<Integer, Integer>> scrollableAxes = new HashMap<>();

    MenuOpenerBuilderImpl(MenuProcessor menuProcessor, ItemProcessor itemProcessor, P provider, Player player, MenuProviderLoader<P> providerLoader) {
        this.menuProcessor = menuProcessor;
        this.itemProcessor = itemProcessor;
        this.provider = provider;
        this.player = player;
        this.providerLoader = providerLoader;
    }

    @Override
    public @NotNull MenuOpenerBuilder pagination(@NotNull String id, int page) {
        this.paginationPages.put(id, page);
        return this;
    }

    @Override
    public @NotNull MenuOpenerBuilder paginationPages(@NotNull Collection<@NotNull Pair<@NotNull String, @NotNull Integer>> paginationPages) {
        for (Pair<String, Integer> page : paginationPages) {
            this.paginationPages.put(page.getKey(), page.getValue());
        }

        return this;
    }

    @Override
    public @NotNull MenuOpenerBuilder paginationPages(@NotNull Supplier<@NotNull Collection<@NotNull Pair<@NotNull String, @NotNull Integer>>> paginationPages) {
        return this.paginationPages(paginationPages.get());
    }

    @Override
    public @NotNull MenuOpenerBuilder scrollable(@NotNull String id, int xAxis, int yAxis) {
        this.scrollableAxes.put(id, Pair.of(xAxis, yAxis));
        return this;
    }

    @Override
    public @NotNull MenuOpenerBuilder scrollableAxes(@NotNull Collection<@NotNull Triple<@NotNull String, @NotNull Integer, Integer>> scrollableAxes) {
        for (Triple<String, Integer, Integer> axis : scrollableAxes) {
            this.scrollableAxes.put(axis.getFirst(), Pair.of(axis.getSecond(), axis.getThird()));
        }

        return this;
    }

    @Override
    public @NotNull MenuOpenerBuilder scrollableAxes(@NotNull Supplier<@NotNull Collection<@NotNull Triple<@NotNull String, @NotNull Integer, Integer>>> scrollableAxes) {
        return this.scrollableAxes(scrollableAxes.get());
    }

    @Override
    public void open() {
        new MenuInitializer<>(this.menuProcessor, this.itemProcessor, this).initializeMenu();
    }
}