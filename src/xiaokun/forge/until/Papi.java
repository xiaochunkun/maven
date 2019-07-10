package xiaokun.forge.until;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class Papi extends PlaceholderExpansion {
    @Override
    public boolean canRegister()
    {
        return true;
    }

    @Override
    public String getAuthor() {
        return "XiaoKun";
    }

    @Override
    public String getIdentifier() {
        return "dz";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier){
        if(player == null){
            return "";
        }
        if(identifier.equals("level")){
            System.out.println(PlayerData.getLevel(player));
            return String.valueOf(PlayerData.getLevel(player));
        }
        if(identifier.equals("exp")){
            return String.valueOf(PlayerData.getExp(player));
        }
        return null;
    }
}
