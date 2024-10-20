public class Main {
    public static void main(String[] args) {
        Login login = new Login();

        while(!login.getAutenticacao()){
            login.menuLogin();
        }

        if (login.getTipoUsuario()) {
            System.out.println("vc é Administrador");
            Teste.teste();
        }else
            System.out.println("vc é funcionario");
    }
}