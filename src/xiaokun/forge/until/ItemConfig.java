package xiaokun.forge.until;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xiaokun.forge.Forge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemConfig {
    @Getter
    private static List<String> list = new ArrayList<String>();
    @Getter
    private static Map<String, ItemStack> map = new HashMap<String, ItemStack>();


    public static File getMapFaile(String name) {
        return new File("plugins" + File.separator + Forge.getPluginName() + File.separator + "Map" + File.separator + name + ".yml");
    }

    public static int getLevel(String map) {
        YamlConfiguration yml = getMaterialYml(map);
        return yml.getInt(map + ".level");
    }

    public static YamlConfiguration getMapYml(String name) {
        return YamlConfiguration.loadConfiguration(getMapFaile(name));
    }

    public static File getItemFaile(String name) {
        return new File("plugins" + File.separator + Forge.getPluginName() + File.separator + "Item" + File.separator + name + ".yml");
    }

    public static YamlConfiguration getItemYml(String name) {
        return YamlConfiguration.loadConfiguration(getItemFaile(name));
    }

    public static File getMaterialFaile(String name) {
        return new File("plugins" + File.separator + Forge.getPluginName() + File.separator + "Material" + File.separator + name + ".yml");
    }

    public static YamlConfiguration getMaterialYml(String name) {
        return YamlConfiguration.loadConfiguration(getMaterialFaile(name));
    }

    public static void createItem(String name) {
        File file = getItemFaile(name);
        YamlConfiguration yml = new YamlConfiguration();
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b§l初造之剑");
        List<String> l = new ArrayList<String>();
        l.add("§a锻造武器    优秀");
        l.add("§e§l——————————————————");
        l.add("§e锻造信息：");
        l.add("§e锻造者：§7[§e%dz_author%§7]");
        l.add("§e锻造品质：§7%quality%");
        l.add("§e§l——————————————————");
        l.add("§7固定属性：");
        l.add("§7暴击几率：10%");
        l.add("§7吸血几率：20%");
        l.add("§e§l——————————————————");
        l.add("§a锻造属性：");
        l.add("§a攻击力：$forge-10$ - $forge-500$");
        l.add("§a吸血倍率：$forge-20$%");
        l.add("§a暴击倍率：$forge-10$%");
        l.add("§e§l——————————————————");
        l.add("§e初学锻造师的一门作品");
        l.add("§e§l——————————————————");
        meta.setLore(l);
        item.setItemMeta(meta);
        yml.set(name, item);
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createMap(String name) {
        File file = getMapFaile(name);
        YamlConfiguration yml = new YamlConfiguration();
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b§l初造之剑图纸");
        List<String> l = new ArrayList<String>();
        l.add("§a锻造师之剑");
        l.add("§c该图纸需要锻造等级:§b§l1");
        l.add("§c§l需要锻造材料:");
        l.add("§5煤炭X1组");
        l.add("§b一阶锻造材料X10");
        l.add("§a下级装备核心X1");
        meta.setLore(l);
        item.setItemMeta(meta);
        yml.set(name + ".item", item);
        yml.set(name + ".level", 1);
        yml.set(name + ".exp", 1);
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createMaterial(String name) {
        File file = getMaterialFaile(name);
        YamlConfiguration yml = new YamlConfiguration();
        ItemStack item = new ItemStack(Material.COAL, 64);
        yml.set(name + ".0", item);

        item = new ItemStack(Material.COAL, 10, (short) 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6一阶锻造材料");
        List<String> l = new ArrayList<String>();
        l.add("§a最初级的锻造材料之一");
        meta.setLore(l);
        item.setItemMeta(meta);
        yml.set(name + ".1", item);

        item = new ItemStack(Material.DIAMOND, 1);
        meta = item.getItemMeta();
        meta.setDisplayName("§6一阶锻造材料");
        l.clear();
        l.add("§a最初级的锻造材料之一");
        meta.setLore(l);
        item.setItemMeta(meta);
        yml.set(name + ".2", item);
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadMapConfig() {
        File file = new File("plugins" + File.separator + Forge.getPluginName() + File.separator + "Map");
        if (!file.exists()) {
            file.mkdirs();
            createMap("Default");
        }
        String[] fs = file.list();
        String name = null;
        int num = 0;
        if (fs.length != 0) {
            for (String f : fs) {
                if (f.endsWith("yml")) {
                    name = f.replace(".yml", "");
                    list.add(name);
                    map.put(name, getMapYml(name).getItemStack(name + ".item"));
                    num++;
                }
            }
        }
        Message.sendMessage("找到了" + String.valueOf(num) + "张锻造图纸");
    }

    public static void loadMaterialConfig() {
        File file = new File("plugins" + File.separator + Forge.getPluginName() + File.separator + "Material");
        if (!file.exists()) {
            file.mkdirs();
            createMaterial("Default");
        }
        String[] fs = file.list();
        int num = 0;
        if (fs.length != 0) {
            for (String f : fs) {
                if (f.endsWith("yml")) {
                    num++;
                }
            }
        }
        Message.sendMessage("找到了" + String.valueOf(num) + "个材料清单");
    }

    public static void loadItemConfig() {
        File file = new File("plugins" + File.separator + Forge.getPluginName() + File.separator + "Item");
        if (!file.exists()) {
            file.mkdirs();
            createItem("Default");
        }
        String[] fs = file.list();
        int num = 0;
        if (fs.length != 0) {
            for (String f : fs) {
                if (f.endsWith("yml")) {
                    num++;
                }
            }
        }
        Message.sendMessage("找到了" + String.valueOf(num) + "个锻造物品");
    }

    public static String getKey(Map<String, ItemStack> map, ItemStack item) {
        if (item != null) {
            for (Map.Entry<String, ItemStack> m : map.entrySet()) {
                if (m.getValue().equals(item)) {
                    return m.getKey();
                }
            }
        }
        return null;
    }
    public static int getInvItemNum(final Inventory inv, final ItemStack itemStack) {
        int num = 0;
        for (final ItemStack item : inv.getContents()) {
            if (item != null && item.isSimilar(itemStack)) {
                num += item.getAmount();
            }
        }
        return num;
    }

    public static void removeItem(final Inventory inv, final ItemStack itemStack, final int num) {
        final ItemStack itemClone = itemStack.clone();
        itemClone.setAmount(1);
        for (int i = 0; i < num; ++i) {
            inv.removeItem(new ItemStack[] { itemClone });
        }
    }

    public static int getExp(final String key){
        return getMapYml(key).getInt(key + ".exp");
    }
}
