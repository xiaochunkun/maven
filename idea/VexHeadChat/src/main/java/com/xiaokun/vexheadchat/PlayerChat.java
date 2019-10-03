package com.xiaokun.vexheadchat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (event.isAsynchronous()) {
            String msg = event.getMessage();
            Util.sendTestTag(msg, event.getPlayer());
        }
    }


}
