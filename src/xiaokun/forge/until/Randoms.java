package xiaokun.forge.until;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class Randoms {
    private static YamlConfiguration yml = Config.getYml();

    public static String getLevel() {
        ConfigurationSection section = yml.getConfigurationSection("Strength");
        double sjs = Math.random();
        for (String key : section.getKeys(false)) {
            if ((sjs > section.getDouble(key + ".mix")) && (sjs < section.getDouble(key + ".max"))) {
                return key;
            }
        }
        return null;
    }

    public static String getLevelName(final String level) {
        return yml.getString("Strength." + level + ".name");
    }

    public static double getLevelAttribute(final String level) {
        return yml.getDouble("Strength." + level + ".attribute");
    }
}
