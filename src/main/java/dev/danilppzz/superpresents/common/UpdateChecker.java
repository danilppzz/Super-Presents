package dev.danilppzz.superpresents.common;

import dev.danilppzz.superpresents.SuperPresents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateChecker {
    public static void check() {
        try {
            HttpURLConnection connection = (HttpURLConnection) (new URL("https://api.spigotmc.org/legacy/update.php?resource=117221")).openConnection();
            int timed_out = 1250;

            connection.setConnectTimeout(timed_out);
            connection.setReadTimeout(timed_out);

            String latestVersion = (new BufferedReader(new InputStreamReader(connection.getInputStream()))).readLine();

            int latestVersionNumbers = Integer.parseInt(latestVersion.replaceAll("\\.", ""));
            int pluginVersion = Integer.parseInt(SuperPresents.getInstance().getDescription().getVersion().replaceAll("\\.", ""));

            if (latestVersionNumbers > pluginVersion) {
                SuperPresents.getInstance().getLogger().severe("There is a new version available: "+latestVersion);
                SuperPresents.getInstance().getLogger().severe("You can download it at: https://www.spigotmc.org/resources/superjoin.117221/");
            }
        } catch (Exception var3) {
            Bukkit.getConsoleSender().sendMessage(SuperPresents.getInstance().getName() + ChatColor.RED + " Error while checking update.");
        }
    }
}
