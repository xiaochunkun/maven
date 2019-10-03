package xiaokun.vexslot.listener;

import lk.vexview.api.VexViewAPI;
import lk.vexview.event.KeyBoardPressEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xiaokun.vexslot.util.GuiUtil;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onKey(KeyBoardPressEvent event) {
        if (event.getKey() == 19) {
            VexViewAPI.openGui(event.getPlayer(), GuiUtil.getGui());
        }
    }
}
