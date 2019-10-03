package com.xiaokun.vexheadchat;

import lk.vexview.api.VexViewAPI;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class VexHeadChat extends JavaPlugin implements Listener {

    @Getter
    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        long oldTimes = System.currentTimeMillis();

        if (compareVersion("2.4",VexViewAPI.getVexView().getVersion()) > 0) {
            Bukkit.getLogger().info("§4请使用VexView2.4及以上版本后，再使用此插件");
            Bukkit.getPluginManager().disablePlugin(this);
        }else {
            plugin = this;
            loadConfig();

            Bukkit.getPluginManager().registerEvents(new PlayerChat(), this);

            Bukkit.getLogger().info("Load Time: §c" + (System.currentTimeMillis() - oldTimes) + "§r ms");

            Bukkit.getLogger().info("§cAuthor: XiaoKun QQ: 1240816911");
        }

    }

    public void loadConfig() {
        File file = new File(VexHeadChat.getPlugin().getDataFolder(), "config.yml");
        if (!file.exists()) {
            VexHeadChat.getPlugin().saveDefaultConfig();
            Bukkit.getLogger().info("§a创建 [config.yml] 配置文件");
        }

        try {
            new YamlConfiguration().load(file);
            Bukkit.getLogger().info("§a[config.yml] 配置文件已加载");
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            Bukkit.getLogger().info("§4[config.yml] 配置文件加载失败");
        }

    }

    public static int compareVersion(String v1, String v2) {
        if (v1.equals(v2)) {
            return 0;
        }
        String[] version1Array = v1.split("[._]");
        String[] version2Array = v2.split("[._]");
        int index = 0;
        int minLen = Math.min(version1Array.length, version2Array.length);
        long diff = 0;

        while (index < minLen
                && (diff = Long.parseLong(version1Array[index]) - Long.parseLong(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            for (int i = index; i < version1Array.length; i++) {
                if (Long.parseLong(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Long.parseLong(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

}
