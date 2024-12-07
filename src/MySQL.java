public class MySQL {
    private static String url = "jdbc:mysql://localhost:3306/icecoin_db";
    private static String user = "root";
    private static String password = "";


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