package cn.ChengZhiYa.ChengToolsReloaded.Commands;

import cn.ChengZhiYa.ChengToolsReloaded.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cn.ChengZhiYa.ChengToolsReloaded.Ultis.multi.ChatColor;

public class Home implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                Player player = (Player) sender;
                String HomeName = args[0];
                File HomeData = new File(main.main.getDataFolder() + "/HomeData");
                File HomeData_File = new File(HomeData, player.getName() + ".yml");
                if (!HomeData_File.exists()) {
                    try {
                        HomeData_File.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                YamlConfiguration PlayerHomeData = YamlConfiguration.loadConfiguration(HomeData_File);
                List<String> HomeList = PlayerHomeData.getStringList(player.getName() + "_HomeList");
                if (!HomeList.contains(HomeName)) {
                    player.sendMessage(ChatColor("&c&l你没有名为 " + HomeName + " 的家!"));
                    return false;
                }
                Location HomeLocation = new Location(Bukkit.getWorld(Objects.requireNonNull(PlayerHomeData.getString(HomeName + ".World"))),
                        PlayerHomeData.getDouble(HomeName + ".X"),
                        PlayerHomeData.getDouble(HomeName + ".Y"),
                        PlayerHomeData.getDouble(HomeName + ".Z"),
                        player.getLocation().getYaw(),
                        player.getLocation().getPitch());
                player.teleport(HomeLocation);
                player.sendMessage(ChatColor("&a&l传送成功!"));
            } else {
                sender.sendMessage(ChatColor("&c用法错误,用法:/home <家名称>"));
            }
        } else {
            sender.sendMessage(ChatColor("&c这个命令只有玩家才能执行"));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                Player player = (Player) sender;
                List<String> TabList = new ArrayList<>();
                File HomeData = new File(main.main.getDataFolder() + "/HomeData");
                File HomeData_File = new File(HomeData, player.getName() + ".yml");
                YamlConfiguration PlayerHomeData = YamlConfiguration.loadConfiguration(HomeData_File);
                List<String> HomeList = PlayerHomeData.getStringList(player.getName() + "_HomeList");
                if (HomeList.get(0) != null) {
                    TabList.addAll(HomeList);
                } else {
                    TabList.add("你还没有家呢!请先设置一个家!");
                }
                return TabList;
            }
        }
        return null;
    }
}
