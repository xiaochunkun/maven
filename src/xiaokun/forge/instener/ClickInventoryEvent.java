package xiaokun.forge.instener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xiaokun.forge.Forge;
import xiaokun.forge.event.ExpChangeEvent;
import xiaokun.forge.until.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClickInventoryEvent implements Listener {
    private Inventory pInv;

    @EventHandler
    public void clickInventory(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        final Inventory inv = event.getInventory();
        final ItemStack eItem = event.getCurrentItem();
        String key = null;
        switch (inv.getName()) {
            case "锻造台":
            case "可锻造列表":
            case "开始锻造":
            case "历史锻造":
            case "请拿走你锻造的装备":
                event.setCancelled(true);
                break;
        }
        if ((event.getCurrentItem() != null) && (!event.getCurrentItem().getType().equals(Material.AIR))) {
            ItemMeta eMeta = eItem.getItemMeta();
            switch (eMeta.getDisplayName()) {
                case "查看已学习的图纸":
                    Turn.setMapNum(Turn.getMapNum() + 1);
                    pInv = CreateInventory.getInventory(1, player);
                    player.closeInventory();
                    player.openInventory(pInv);
                    break;
                case "查看历史锻造的装备":
                    Turn.setItemNum(Turn.getItemNum() + 1);
                    pInv = CreateInventory.getInventory(3, player);
                    player.closeInventory();
                    player.openInventory(pInv);
                    break;
                case "返回主菜单":
                    pInv = CreateInventory.getInventory(0, player);
                    player.closeInventory();
                    player.openInventory(pInv);
                    break;
                case "上一页":
                    if (inv.getName().equals("可锻造列表")) {
                        Turn.setMapNum(Turn.getMapNum() - 1);
                        pInv = CreateInventory.getInventory(1, player);
                        player.closeInventory();
                        player.openInventory(pInv);
                    }
                    if (inv.getName().equals("历史锻造")){
                        Turn.setItemNum(Turn.getItemNum() - 1);
                        pInv = CreateInventory.getInventory(3, player);
                        player.closeInventory();
                        player.openInventory(pInv);
                    }
                    break;
                case "下一页":
                    if (inv.getName().equals("可锻造列表")) {
                        Turn.setMapNum(Turn.getMapNum() + 1);
                        pInv = CreateInventory.getInventory(1, player);
                        player.closeInventory();
                        player.openInventory(pInv);
                    }
                    if (inv.getName().equals("历史锻造")){
                        Turn.setItemNum(Turn.getItemNum() + 1);
                        pInv = CreateInventory.getInventory(3, player);
                        player.closeInventory();
                        player.openInventory(pInv);
                    }
                    break;
            }
        }
        switch (inv.getName()) {
            case "可锻造列表":
                key = ItemConfig.getKey(ItemConfig.getMap(), eItem);
                if (key != null) {
                    pInv = CreateInventory.getInventory(2, player);
                    final YamlConfiguration yml = ItemConfig.getMaterialYml(key);
                    final ConfigurationSection section = yml.getConfigurationSection(key);
                    int num = 0;
                    pInv.setItem(19, eItem);
                    for (String keys : section.getKeys(false)) {
                        pInv.setItem((((num / 5) + 1) * 9) + 3 + (num % 5), section.getItemStack(keys));
                        num++;
                    }
                    player.closeInventory();
                    player.openInventory(pInv);
                }
                break;
            case "开始锻造":
                if ((eItem != null) && (!event.getCurrentItem().getType().equals(Material.AIR)) && (eItem.getItemMeta().getDisplayName().equals("开始锻造"))) {
                    ItemStack book = inv.getItem(19);
                    key = ItemConfig.getKey(ItemConfig.getMap(), book);
                    if (key != null) {
                        pInv = player.getInventory();
                        boolean confirm = false;
                        ItemStack[] map = pInv.getContents();
                        final YamlConfiguration yml = ItemConfig.getMaterialYml(key);
                        final ConfigurationSection section = yml.getConfigurationSection(key);
                        for (String keys : section.getKeys(false)) {
                            ItemStack item = section.getItemStack(keys);
                            final int needNum = item.getAmount();
                            final int hasNum = ItemConfig.getInvItemNum(pInv, item);
                            if (hasNum < needNum) {
                                confirm = true;
                                break;
                            } else {
                                ItemConfig.removeItem(pInv, item, needNum);
                            }
                        }
                        if (confirm) {
                            for (int i = 0; i < pInv.getSize(); i++) {
                                if (map[i] != null) {
                                    pInv.setItem(i, map[i]);
                                }
                            }
                            player.sendMessage("§c材料不全，已经退还全部材料");
                        } else {
                            final YamlConfiguration ymls = ItemConfig.getItemYml(key);
                            ItemStack items = ymls.getItemStack(key);
                            if ((items != null) && (items.getItemMeta() != null)) {
                                ItemMeta meta = items.getItemMeta();
                                List<String> l = new ArrayList<String>();
                                List<String> l2 = meta.getLore();
                                String level = Randoms.getLevel();
                                double ratio = Randoms.getLevelAttribute(level);
                                String name = Randoms.getLevelName(level);
                                for (String s : l2) {
                                    if (s.indexOf("$forge-") != -1) {
                                        double math = Double.valueOf(Forge.getSubString(s, "$forge-", "$"));
                                        final int att = (int) Math.rint(math * ratio);
                                        s = s.replace("$forge-" + String.valueOf((int) math) + "$", String.valueOf(att));
                                        if (s.indexOf("$forge-") != -1) {
                                            double math2 = Double.valueOf(Forge.getSubString(s, "$forge-", "$"));
                                            final int att2 = (int) Math.rint(math2 * ratio);
                                            s = s.replace("$forge-" + String.valueOf((int) math2) + "$", String.valueOf(att2));
                                        }
                                    }
                                    if (s.indexOf("%quality%") != 1) {
                                        s = s.replace("%quality%", name);
                                    }

                                    if (s.indexOf("%dz_author%") != 1) {
                                        s = s.replace("%dz_author%", player.getName());
                                    }
                                    l.add(s);
                                }
                                meta.setLore(l);
                                items.setItemMeta(meta);

                                Inventory newInv = Bukkit.createInventory(null, 9, "请拿走你锻造的装备");
                                newInv.setItem(4, items);
                                player.openInventory(newInv);

                                PlayerData.addExp(player, ItemConfig.getExp(key));

                                ExpChangeEvent e = new ExpChangeEvent(player, ItemConfig.getExp(key));
                                Bukkit.getServer().getPluginManager().callEvent(e);

                                File file = PlayerData.getPlayerFile(player);
                                YamlConfiguration yml2 = YamlConfiguration.loadConfiguration(file);
                                int num = PlayerData.getItemNum(player);
                                yml2.set("item." + String.valueOf(num), items);
                                yml2.set("item.num", ++num);
                                try {
                                    yml2.save(file);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
                }
                break;
            case "请拿走你锻造的装备":
                if (event.getCurrentItem() != null) {
                    player.getInventory().addItem(event.getCurrentItem());
                    player.closeInventory();
                }
                break;
        }
    }
}
