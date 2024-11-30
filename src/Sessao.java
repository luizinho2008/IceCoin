public class Sessao {
    private static String nomeUsuario;
    private static Integer idUsuario;

    public static String getNomeUsuario() {
        return nomeUsuario;
    }

    public static void setNomeUsuario(String nomeUsuario) {
        Sessao.nomeUsuario = nomeUsuario;
    }

    public static Integer getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(Integer idUsuario) {
        Sessao.idUsuario = idUsuario;
    }
}