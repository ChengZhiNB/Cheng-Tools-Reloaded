package cn.ChengZhiYa.ChengToolsReloaded.Commands;

import cn.ChengZhiYa.ChengToolsReloaded.main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static cn.ChengZhiYa.ChengToolsReloaded.Ultis.multi.*;

public class List implements CommandExecutor {
    private final Runtime runtime = Runtime.getRuntime();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        for (String Message : main.main.getConfig().getStringList("SuperListSettings.Message")) {
            java.util.List<String> PlayerList = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                PlayerList.add(player.getName());
            }
            sender.sendMessage(ChatColor(PlaceholderAPI.setPlaceholders(null,Message.
                    replaceAll("%mem_used%", String.valueOf((runtime.totalMemory() - runtime.freeMemory()) / 1048576L)).
                    replaceAll("%mem_max%", String.valueOf(runtime.maxMemory() / 1048576L)).
                    replaceAll("%PlayerList%", String.valueOf(PlayerList)).
                    replaceAll("%Tps_1%", getTps(1)).
                    replaceAll("%Tps_5%", getTps(5)).
                    replaceAll("%Tps_15%", getTps(15)))));
        }
        return false;
    }
}