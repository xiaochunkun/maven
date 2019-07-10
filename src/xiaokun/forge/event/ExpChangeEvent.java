package xiaokun.forge.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class ExpChangeEvent extends PlayerEvent{
    private static final HandlerList handlers = new HandlerList();
    @Getter
    private int exp;

    public ExpChangeEvent(final Player player, final int expAmount) {
        super(player);
        this.exp = expAmount;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}