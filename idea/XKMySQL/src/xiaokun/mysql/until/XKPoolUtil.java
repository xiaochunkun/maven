package xiaokun.mysql.until;

import lombok.Getter;
import lombok.Setter;

public class MyPool{
    //设置注册属性

    private static String url = "";
    private static String address = "jdbc:mysql://%s:%s/%s?useSSL=%s&characterEncoding=%s&autoReconnect=%s";
    private static String driverClass = "com.mysql.jdbc.Driver";

    @Getter
    @Setter
    private static String host = "9xt7nka3.2328.dnstoo.com";

    @Getter
    @Setter
    private static int port = 5503;

    @Getter
    @Setter
    private static String name = "chunkun";

    @Getter
    @Setter
    private static String user = "chunkun_f";

    @Getter
    @Setter
    private static String password = "likun19980519";

    @Getter
    @Setter
    private static String encoding = "utf8";

    @Getter
    @Setter
    private static String prefix = "xk_";

    @Getter
    @Setter
    private static boolean ssl = false;

    @Getter
    @Setter
    private static boolean reconnect = true;

    @Getter
    @Setter
    private static int initSize = 10;

    @Getter
    @Setter
    private static int maxSize = 20;
}
