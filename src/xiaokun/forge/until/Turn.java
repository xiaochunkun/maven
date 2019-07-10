package xiaokun.forge.until;

import lombok.Getter;

public class Turn {
    @Getter
    private static int itemNum;
    @Getter
    private static int mapNum;

    public static void setItemNum(int itemNum) {
        if(itemNum >= 0) {
            Turn.itemNum = itemNum;
        }
    }
    public static void setMapNum(int mapNum) {
        if(mapNum >= 0) {
            Turn.mapNum = mapNum;
        }
    }

    public static void init() {
        setMapNum(0);
        setItemNum(0);
    }
}
