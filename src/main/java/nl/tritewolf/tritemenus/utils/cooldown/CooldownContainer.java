package nl.tritewolf.tritemenus.utils.cooldown;

import nl.tritewolf.tritemenus.utils.collection.Table;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public final class CooldownContainer {

    private final Table<UUID, String, Cooldown> playerCooldowns = new Table<>();

    public void addCooldown(UUID uuid, String name, Cooldown cooldown) {
        this.playerCooldowns.computeIfAbsent(uuid, name, (u, s) -> cooldown);
    }

    public void addCooldown(UUID uuid, String name, int value, TimeUnit timeUnit) {
        this.addCooldown(uuid, name, new Cooldown(value, timeUnit));
    }

    public boolean hasCooldown(UUID uuid, String name) {
        Cooldown cooldown = this.playerCooldowns.get(uuid, name);
        if (cooldown == null) return false;

        if (cooldown.isExpired()) {
            this.playerCooldowns.remove(uuid, name);
            return false;
        }

        return true;
    }

    public void removeCooldown(UUID uuid, String name) {
        this.playerCooldowns.remove(uuid, name);
    }

    public boolean checkAndCreate(UUID uuid, String name, Cooldown cooldown) {
        if (this.hasCooldown(uuid, name)) return true;

        this.addCooldown(uuid, name, cooldown);
        return false;
    }

    public boolean checkAndCreate(UUID uuid, String name, int value, TimeUnit timeUnit) {
        return this.checkAndCreate(uuid, name, Cooldown.of(value, timeUnit));
    }

    public void removeExpiredCooldowns() {
        this.playerCooldowns.removeIf((uuid, name, cooldown) -> {
            return cooldown.isExpired();
        });
    }
}