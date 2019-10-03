package com.xiaokun.vexglobalshop.util;

import com.xiaokun.vexglobalshop.GlobalShop;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    @Getter
    private static File file = new File(GlobalShop.getPluginFile(),"config.yml");

    @Getter
    private static YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);


    public static void loadConfig() {
        if (!file.exists()) {
            GlobalShop.getPlugin().saveDefaultConfig();
        }
        Message.sendMessage("§eFind config.yml");
        try {
            yml.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            Message.sendMessage("§a读取 config.yml 时发生错误");
        }
    }
}
