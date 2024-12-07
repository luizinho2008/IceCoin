public class MySQL {
    private static String url = "jdbc:mysql://nx6vj.h.filess.io:3307/IceCoin_betweenfox";
    private static String user = "IceCoin_betweenfox";
    private static String password = "186f084cc6f22a27c5ff1ea3faf39e85896fb47e";


    public static String getUrl() {
        return url;
    }
    public static String setUrl() {
        return MySQL.url;
    }


    public static String getUser() {
        return user;
    }
    public static void setUser(String user) {
        MySQL.user = user;
    }


    public static String getPassword() {
        return password;
    }
    public static void setPassword(String password) {
        MySQL.password = password;
    }

}