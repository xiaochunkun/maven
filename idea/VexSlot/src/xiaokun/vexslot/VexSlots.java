package xiaokun.vexslot;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xiaokun.vexslot.command.OnCommand;
import xiaokun.vexslot.listener.PlayerJoin;

import java.util.logging.Logger;

public class VexSlot extends JavaPlugin {
    @Getter
    private JavaPlugin plugin;
    @Getter
    private Logger log;
    @Getter
    private String name;


    @Override
    public void onEnable() {
        plugin = this;
        name = plugin.getName();
        log = Bukkit.getLogger();

        log.info("§a==== §6" + name + " > 正在载入§a ====");
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginCommand("test").setExecutor(new OnCommand());
        log.info("§a> 作者：§fXiaokun");
        log.info("§a==== §6" + name + " > 载入完成§a ====");


    }
}
