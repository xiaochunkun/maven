package com.xiaokun.vexglobalshop.util;

import com.xiaokun.vexglobalshop.GlobalShop;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class shelvesgui {
    @Getter
    private static File file = new File(GlobalShop.getPluginFile(),"shelvesgui.yml");

    @Getter
    private static YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);

    public static void loadConfig() {
        if (!file.exists()) {
            try {
                yml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Message.sendMessage("§a找到 config.yml");
        try {
            yml.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            Message.sendMessage("§a读取 config.yml 时发生错误");
        }
    }
}
