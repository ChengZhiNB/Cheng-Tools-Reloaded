package cn.ChengZhiYa.ChengToolsReloaded.Listener;

import cn.ChengZhiYa.ChengToolsReloaded.HashMap.LocationHashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
    @EventHandler
    public void On_Event(PlayerDeathEvent event) {
        Player player = event.getEntity();
        LocationHashMap.Set(player.getName() + "_DeathLocation", player.getLocation());
    }
}
