package dev.danilppzz.superpresents;

import dev.danilppzz.superpresents.common.HexColor;
import dev.danilppzz.superpresents.handler.ItemHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class MainCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String string, String[] args) {
        if (args[0].equalsIgnoreCase("reload") && hasPermission(sender, "superpresents.reload")) {
            SuperPresents.getInstance().reloadConfig();
            sender.sendMessage(HexColor.write("&8[#FFB600⭐&8] &fThe plugin was reloaded."));
        } else if (args[0].equalsIgnoreCase("reward") && hasPermission(sender, "superpresents.reward")) {
            Player player = (Player) sender;
            if (args[1].equalsIgnoreCase("create")) {
                ItemStack itemStack = player.getItemInHand();
                ItemHandler.createReward(itemStack, args[2].toLowerCase());
                sender.sendMessage(HexColor.write("&8[&2✔&8] &fThe item was created with the name: #0599FF"+args[2].toLowerCase()));
            } else if (args[1].equalsIgnoreCase("delete")) {
                ItemHandler.deleteReward(args[2].toLowerCase());
                sender.sendMessage(HexColor.write("&8[&2✔&8] &fThe item: #0599FF"+args[2].toLowerCase()+" &fwas deleted."));
            } else if (args[1].equalsIgnoreCase("get")) {
                ItemHandler.getReward(args[2].toLowerCase());
                sender.sendMessage(HexColor.write("&8[&2✔&8] &fItem claimed."));
            }
        }
        return false;
    }

    private boolean hasPermission(CommandSender sender, String permission) {
        if (sender.hasPermission(permission)) {
            return true;
        } else {
            sender.sendMessage(HexColor.write("&8[&c❌&8] "+SuperPresents.getInstance().getConfig().getString("translation.error_permission")));
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        List<String> argList = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("superpresents")) {
            if (args.length == 1) {
                argList.add("reload");
                return argList;
            }
        }
        return null;
    }
}
