package user.model;

public abstract class User {

    private int id;
    private int tipo;
    private String nomeCompleto;
    private String nomeUsuario;
    private String senha;
    private String saldo;

    public User(int id, int tipo, String nomeCompleto, String nomeUsuario, String senha, String saldo) {
        this.id = id;
        this.tipo = tipo;
        this.nomeCompleto = nomeCompleto;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public void comprar(float valor) {
        this.setSaldo(this.getSaldo() + valor);
    }

    public void visualizar() {

        String tipo = switch (this.tipo) {
            case 1 -> "Conta Cliente";
            case 2 -> "Conta Lojista";
            default -> "";
        };

        System.out.println("\n\n***********************************************************");
        System.out.println("Dados da Conta:");
        System.out.println("***********************************************************");
        System.out.println("ID da Conta: " + this.id);
        System.out.println("Nome Completo: " + this.nomeCompleto);
        System.out.println("Tipo da Conta: " + tipo);
        System.out.println("Nome de usuário: " + this.nomeUsuario);
        System.out.println("Saldo: " + this.saldo);
    }
}
