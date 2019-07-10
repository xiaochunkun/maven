package xiaokun.forge.until;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xiaokun.forge.Forge;

import java.util.List;
import java.util.stream.IntStream;


public class Message {

    public static void sendNormalMessage(String msg) {
        if (msg != null) {
            Bukkit.getConsoleSender().sendMessage("§a" + msg);
        }

    }

    public static void sendMessage(String msg) {
        if (msg != null) {
            Bukkit.getConsoleSender().sendMessage("[" + Forge.getPluginName() + "] §a" + msg);
        }
    }

    public static TextComponent getTextComponent(String message, String command, List<String> stringList) {
        TextComponent tcMessage = new TextComponent(message);
        if (stringList != null && stringList.size() > 0) {
            ComponentBuilder bc = new ComponentBuilder("§7" + stringList.get(0).replace("&", "§"));
            IntStream.range(1, stringList.size()).mapToObj(i -> "\n§7" + stringList.get(i).replace("&", "§")).forEach(bc::append);
            tcMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, bc.create()));
        }
        if (command != null) {
            tcMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        }
        return tcMessage;
    }

    public static void sendTextComponent(CommandSender sender, TextComponent tc) {
        if (sender instanceof Player) {
            ((Player) sender).spigot().sendMessage(tc);
        } else {
            sender.sendMessage(tc.getText());
        }
    }

}
