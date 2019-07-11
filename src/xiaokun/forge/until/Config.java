package xiaokun.forge.until;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import xiaokun.forge.Forge;

import java.io.File;
import java.io.IOException;

public class Config {
    @Getter
    static final File file = new File("plugins" + File.separator + Forge.getPluginName() + File.separator + "config.yml");
    @Getter
    static final YamlConfiguration yml = new YamlConfiguration();

    public static void loadConfig() {
        if (!file.exists()) {
            Forge.getPlugin().saveDefaultConfig();
        }
        Message.sendMessage("§a找到 config.yml");
        try {
            yml.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            Message.sendMessage("§a读取 config.yml 时发生错误");
        }
    }

    public static int getExp(final String level) {
        return yml.getInt("ForgeLevel." + level + ".exp");
    }

    public static String getLevelName(final String level){
        return yml.getString("ForgeLevel." + level + ".name");
    }

}
