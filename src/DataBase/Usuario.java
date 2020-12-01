package DataBase;

public class Usuario {
    private int id;
    private String usuario;
    private String password;
    private int privilegio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario(int id, String usuario, String password, int privilegio) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.privilegio = privilegio;
    }

    public int getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(int privilegio) {
        this.privilegio = privilegio;
    }

    public Usuario(String usuario, String password, int privilegio) {
        this.usuario = usuario;
        this.password = password;
        this.privilegio = privilegio;
    }
    public Usuario(){}

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
