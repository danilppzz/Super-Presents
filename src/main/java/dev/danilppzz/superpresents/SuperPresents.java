package dev.danilppzz.superpresents;

import dev.danilppzz.superpresents.common.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SuperPresents extends JavaPlugin {

    private static SuperPresents instance;

    public static Boolean useBetaFeatures() { return getInstance().getConfig().getBoolean("useBetaFeatures"); }

    public static SuperPresents getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable()
    {
        instance = this;
        saveDefaultConfig();

        if (getConfig().getBoolean("updateChecker")) UpdateChecker.check();

        Objects.requireNonNull(getCommand("superpresents")).setExecutor(new MainCommand());
    }

    @Override
    public void onDisable()
    {
        this.reloadConfig();
        this.saveConfig();
    }
}
