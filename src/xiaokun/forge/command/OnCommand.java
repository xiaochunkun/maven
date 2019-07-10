package xiaokun.forge.command;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xiaokun.forge.until.CreateInventory;
import xiaokun.forge.until.ItemConfig;
import xiaokun.forge.until.Message;
import xiaokun.forge.until.Turn;

import java.util.List;


public class OnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if ((command.getName().equalsIgnoreCase("dz")) && (player.hasPermission("dz.duanzao")) && (args.length == 0)) {
                Turn.init();
                player.closeInventory();
                player.openInventory(CreateInventory.getInventory(0, player));
                return true;
            }
            if ((args[0].equals("map")) && (player.hasPermission("dz.map")) && (args.length >= 1)) {
                if (args.length == 1) {
                    List<String> list = ItemConfig.getList();
                    if (list.size() > 0) {
                        player.sendMessage("§a---------------§4 图纸列表§a---------------");
                        for (int i = 0; i < list.size(); i++) {
                            String o = list.get(i);
                            final YamlConfiguration yml = ItemConfig.getMapYml(o);
                            final ItemMeta meta = yml.getItemStack(o + ".item").getItemMeta();
                            String name = null;
                            if (meta != null) {
                                name = meta.getDisplayName();
                            } else {
                                name = o;
                            }
                            TextComponent tc = Message.getTextComponent("§e" + String.valueOf(i + 1) + ". §a" + name, "/dz map give " + o, meta.getLore());
                            Message.sendTextComponent(sender, tc);
                        }
                    } else {
                        player.sendMessage("§4暂无图纸信息");
                    }
                    player.sendMessage("§a---------------§4 点击领取§a---------------");
                    return true;
                }
                if (args[1].equals("give") && args.length >= 2) {
                    if (!(Bukkit.getPlayer(args[2]) instanceof Player) && (ItemConfig.getList().contains(args[2]))) {
                        final YamlConfiguration yml = ItemConfig.getMapYml(args[2]);
                        final ItemStack item = yml.getItemStack(args[2] + ".item");
                        if (args.length == 3) {
                            player.getInventory().addItem(item);
                        } else {
                            if (Integer.valueOf(args[3]) instanceof Integer) {
                                item.setAmount(Integer.valueOf(args[3]));
                            }
                            player.getInventory().addItem(item);
                        }
                        player.sendMessage("§a 物品已经发送至背包，请查收");
                        return true;
                    } else if ((Bukkit.getPlayer(args[2]) instanceof Player) && (ItemConfig.getList().contains(args[3]))) {
                        final YamlConfiguration yml = ItemConfig.getMapYml(args[3]);
                        final ItemStack item = yml.getItemStack(args[3] + ".item");
                        Player p = Bukkit.getPlayer(args[2]);
                        if (args.length == 4) {
                            p.getInventory().addItem(item);
                        } else {
                            if (Integer.valueOf(args[4]) instanceof Integer) {
                                item.setAmount(Integer.valueOf(args[4]));
                            }
                            p.getInventory().addItem(item);
                        }
                        player.sendMessage("§a 物品已经发送至背包，请查收");
                        return true;
                    }
                }
            }
            if ((args[0].equals("item")) && (player.hasPermission("dz.item")) && (args.length >= 1)) {
                if (args.length == 1) {
                    List<String> list = ItemConfig.getList();
                    if (list.size() > 0) {
                        player.sendMessage("§a---------------§4 物品列表§a---------------");
                        for (int i = 0; i < list.size(); i++) {
                            String o = list.get(i);
                            final YamlConfiguration yml = ItemConfig.getItemYml(o);
                            final ItemMeta meta = yml.getItemStack(o).getItemMeta();
                            String name = null;
                            if (meta != null) {
                                name = meta.getDisplayName();
                            } else {
                                name = o;
                            }
                            TextComponent tc = Message.getTextComponent("§e" + String.valueOf(i + 1) + ". §a" + name, "/dz item give " + o, meta.getLore());
                            Message.sendTextComponent(sender, tc);
                        }
                    } else {
                        player.sendMessage("§4暂无物品信息");
                    }
                    player.sendMessage("§a---------------§4 点击领取§a---------------");
                    return true;
                }
                if (args[1].equals("give") && args.length >= 2) {
                    if (!(Bukkit.getPlayer(args[2]) instanceof Player) && (ItemConfig.getList().contains(args[2]))) {
                        final YamlConfiguration yml = ItemConfig.getItemYml(args[2]);
                        final ItemStack item = yml.getItemStack(args[2]);
                        if (args.length == 3) {
                            player.getInventory().addItem(item);
                        } else {
                            if (Integer.valueOf(args[3]) instanceof Integer) {
                                item.setAmount(Integer.valueOf(args[3]));
                            }
                            player.getInventory().addItem(item);
                        }
                        player.sendMessage("§a 物品已经发送至背包，请查收");
                        return true;
                    } else if ((Bukkit.getPlayer(args[2]) instanceof Player) && (ItemConfig.getList().contains(args[3]))) {
                        final YamlConfiguration yml = ItemConfig.getItemYml(args[3]);
                        final ItemStack item = yml.getItemStack(args[3]);
                        Player p = Bukkit.getPlayer(args[2]);
                        if (args.length == 4) {
                            p.getInventory().addItem(item);
                        } else {
                            if (Integer.valueOf(args[4]) instanceof Integer) {
                                item.setAmount(Integer.valueOf(args[4]));
                            }
                            p.getInventory().addItem(item);
                        }
                        player.sendMessage("§a 物品已经发送至背包，请查收");
                        return true;
                    }
                }
            }
            if ((args[0].equals("material")) && (player.hasPermission("dz.material")) && (args.length >= 1)) {
                if (args.length == 1) {
                    List<String> list = ItemConfig.getList();
                    if (list.size() > 0) {
                        player.sendMessage("§a---------------§4 材料列表§a---------------");
                        for (int i = 0; i < list.size(); i++) {
                            String o = list.get(i);
                            final YamlConfiguration yml = ItemConfig.getItemYml(o);
                            final ItemMeta meta = yml.getItemStack(o).getItemMeta();
                            String name = null;
                            if (meta != null) {
                                name = meta.getDisplayName() + " §e的材料";
                            } else {
                                name = o + " §e的材料";
                            }
                            TextComponent tc = Message.getTextComponent("§e" + String.valueOf(i + 1) + ". §a" + name, "/dz material give " + o, meta.getLore());
                            Message.sendTextComponent(sender, tc);
                        }
                    } else {
                        player.sendMessage("§4暂无物品信息");
                    }
                    player.sendMessage("§a---------------§4 点击领取§a---------------");
                    return true;
                }
                if (args[1].equals("give") && args.length >= 2) {
                    if (!(Bukkit.getPlayer(args[2]) instanceof Player) && (ItemConfig.getList().contains(args[2]))) {
                        final YamlConfiguration yml = ItemConfig.getMaterialYml(args[2]);
                        final ConfigurationSection section = yml.getConfigurationSection(args[2]);
                        if (args.length == 3) {
                            for (String key : section.getKeys(false)) {
                                player.getInventory().addItem(section.getItemStack(key));
                            }
                        } else {
                            if (Integer.valueOf(args[3]) instanceof Integer) {
                                for (String key : section.getKeys(false)) {
                                    final ItemStack item = section.getItemStack(key);
                                    item.setAmount(Integer.valueOf(args[3]));
                                    player.getInventory().addItem(item);
                                }
                            }
                        }
                        player.sendMessage("§a 物品已经发送至背包，请查收");
                        return true;
                    } else if ((Bukkit.getPlayer(args[2]) instanceof Player) && (ItemConfig.getList().contains(args[3]))) {
                        final YamlConfiguration yml = ItemConfig.getMaterialYml(args[3]);
                        final ConfigurationSection section = yml.getConfigurationSection(args[3]);
                        if (args.length == 4) {
                            for (String key : section.getKeys(false)) {
                                player.getInventory().addItem(section.getItemStack(key));
                            }
                        } else {
                            if (Integer.valueOf(args[4]) instanceof Integer) {
                                for (String key : section.getKeys(false)) {
                                    final ItemStack item = section.getItemStack(key);
                                    item.setAmount(Integer.valueOf(args[4]));
                                    player.getInventory().addItem(item);
                                }
                            }
                        }
                        player.sendMessage("§a 物品已经发送至背包，请查收");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private SenderType getType(CommandSender sender) {
        if (sender instanceof Player) {
            return SenderType.PLAYER;
        }
        return SenderType.CONSOLE;
    }


}
