package xiaokun.forge.instener;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xiaokun.forge.until.ItemConfig;
import xiaokun.forge.until.PlayerData;

import java.io.IOException;

public class InteractEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        int mapNum = PlayerData.getMapNum(player);
        final YamlConfiguration yml = PlayerData.getPlayerData(player);
        final ConfigurationSection section = yml.getConfigurationSection("map");
        boolean exist = false;
        if ((item != null) && (!item.getType().equals(Material.AIR))) {
            ItemStack items = item.clone();
            items.setAmount(1);
            if (ItemConfig.getKey(ItemConfig.getMap(), items) != null) {
                for (String key : section.getKeys(false)) {
                    if ((section.getItemStack(key) != null) && (section.getItemStack(key).equals(items))) {
                        player.sendMessage("§c你已经学过该图纸，请勿重复学习");
                        exist = true;
                    }
                }
                if (!exist) {
                    if (PlayerData.getLevel(player) >= ItemConfig.getLevel(ItemConfig.getKey(ItemConfig.getMap(), items))) {
                        player.sendMessage("§a你成功学习的§8[§e" + items.getItemMeta().getDisplayName() + "§8]§a图纸");
                        yml.set("map." + String.valueOf(mapNum), items);
                        yml.set("map.num", ++mapNum);
                        try {
                            yml.save(PlayerData.getPlayerFile(player));
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    } else {
                        player.sendMessage("§4你的锻造等级不够！！！");
                    }
                    item.setAmount(item.getAmount() - 1);
                }
            }
        }
    }
}
