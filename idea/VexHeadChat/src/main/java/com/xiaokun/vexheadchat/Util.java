package com.xiaokun.vexheadchat;

import lk.vexview.api.VexViewAPI;
import lk.vexview.tag.TagDirection;
import lk.vexview.tag.components.VexImageTag;
import lk.vexview.tag.components.VexTextTag;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Util {
    private static File file;
    private static int i;
    private static int h;
    private static int taskId;

    public static void sendTestTag(String msg, Player player) {
        file = new File(VexHeadChat.getPlugin().getDataFolder(), "config.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        Bukkit.getScheduler().cancelTask(taskId);

        if (yml.getBoolean("text.use")) {
            for (int k = 0; k <= h; k++) {
                VexViewAPI.removePlayerTag(player, yml.getString("text.id") + String.valueOf(k));
            }
        }

        if (yml.getBoolean("image.use")) {
            VexViewAPI.removePlayerTag(player, yml.getString("image.id"));
        }

        i = (int) Math.ceil(msg.length() / yml.getInt("text.length"));

        h = i;

        if (yml.getBoolean("text.use")) {
            for (int j = 0; j <= i; j++) {
                String sub = null;
                if (j == i) {
                    sub = msg.substring((j * 10));
                } else {
                    sub = msg.substring((j * 10), (j + 1) * 10);
                }
                VexTextTag textTag = new VexTextTag(yml.getString("text.id") + String.valueOf(j),
                        yml.getDouble("text.x"),
                        yml.getDouble("text.y") + ((i - j) * 0.3),
                        yml.getDouble("text.z"),
                        yml.getString("text.prefix") + sub,
                        yml.getBoolean("text.black"),
                        new TagDirection(yml.getInt("text.tag.x"),
                                (float) yml.getDouble("text.tag.y"),
                                (float) yml.getDouble("text.tag.z"),
                                yml.getBoolean("text.tag.for"),
                                yml.getBoolean("text.tag.see")));
                VexViewAPI.addPlayerTag(player, textTag);
            }
        }

        if (yml.getBoolean("image.use")) {
            VexImageTag vexImageTag = new VexImageTag(yml.getString("image.id"),
                    yml.getDouble("image.x"),
                    yml.getDouble("image.y") + (i * 0.3),
                    yml.getDouble("image.z"),
                    yml.getString("image.url"),
                    yml.getInt("image.w"),
                    yml.getInt("image.h"),
                    (float) yml.getDouble("image.xshow"),
                    (float) (yml.getDouble("image.yshow") * 2 + (i * 0.3)),
                    new TagDirection((float) yml.getDouble("image.tag.x"),
                            (float) yml.getDouble("image.tag.y"),
                            (float) yml.getDouble("image.tag.z"),
                            yml.getBoolean("image.tag.for"),
                            yml.getBoolean("image.tag.see")));
            VexViewAPI.addPlayerTag(player, vexImageTag);
        }
        int time = 20 * yml.getInt("time");

        taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(VexHeadChat.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (yml.getBoolean("text.use")) {
                    for (int k = 0; k <= i; k++) {
                        VexViewAPI.removePlayerTag(player, yml.getString("text.id") + String.valueOf(k));
                    }
                }
                if (yml.getBoolean("image.use")) {
                    VexViewAPI.removePlayerTag(player, yml.getString("image.id"));
                }
            }
        }, time);
    }
}
