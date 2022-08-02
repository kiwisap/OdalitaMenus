package nl.tritewolf.tritemenus.menu;

import nl.tritewolf.tritemenus.utils.Pair;
import nl.tritewolf.tritemenus.utils.Triple;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Supplier;

public interface MenuOpenerBuilder {

    @NotNull MenuOpenerBuilder pagination(@NotNull String id, int page);

    @NotNull MenuOpenerBuilder paginationPages(@NotNull Collection<@NotNull Pair<@NotNull String, @NotNull Integer>> paginationPages);

    @NotNull MenuOpenerBuilder paginationPages(@NotNull Supplier<@NotNull Collection<@NotNull Pair<@NotNull String, @NotNull Integer>>> paginationPages);

    @NotNull MenuOpenerBuilder scrollable(@NotNull String id, int xAxis, int yAxis);

    @NotNull MenuOpenerBuilder scrollableAxes(@NotNull Collection<@NotNull Triple<@NotNull String, @NotNull Integer, Integer>> scrollableAxes);

    @NotNull MenuOpenerBuilder scrollableAxes(@NotNull Supplier<@NotNull Collection<@NotNull Triple<@NotNull String, @NotNull Integer, Integer>>> scrollableAxes);

    void open();
}