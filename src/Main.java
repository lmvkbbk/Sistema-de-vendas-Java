import Menus.*;
import database.Login;

public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        Administrador adm = new Administrador();
        Funcionario fun = new Funcionario();

        while (!login.getAutenticacao()) {
            login.menuLogin();
        }

        if (login.getTipoUsuario()) {
            System.out.println("vc é Administrador");
            adm.menuAdm();
        } else {
            System.out.println("vc é funcionario");
            fun.menuFun();
        }
    }
}