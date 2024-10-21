package Menus;

import models.*;
import java.util.Scanner;

public class Funcionario {

    public void vendaMenu(){
        Scanner sc = new Scanner(System.in);
        Venda venda = new Venda();

        venda.mostrarTodasVendas();

        int opcao;
        do {
            System.out.println("Opções: ");
            System.out.println("1. Cadastrar Venda");
            System.out.println("2. Editar lista de uma venda");
            System.out.println("3. Pesquisa Venda");
            System.out.println("4. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    venda.cadastrarVenda();
                    break;
                case 2:
                    venda.editarlistaVendaMenu();
                    break;
                case 3:
                    venda.menuPesquisaVenda();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }while (opcao != 4);
    }

    public void clientesMenu(){
        Scanner sc = new Scanner(System.in);
        Cliente cliente = new Cliente();

        cliente.mostrarTodosClientes();

        int opcao;
        do {
            System.out.println("Opções: ");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Editar Cliente");
            System.out.println("3. Pesquisa Cliente");
            System.out.println("4. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cliente.menuInsercaoCliente();
                    break;
                case 2:
                    cliente.menuAtualizacaoCliente();
                    break;
                case 3:
                    cliente.menuPesquisaCliente();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }

        } while (opcao != 4);
    }

    public void produtosMenu(){
        Scanner sc = new Scanner(System.in);
        Produto produto = new Produto();

        produto.mostrarTodosProdutos();

        int opcao;
        do {
            System.out.println("Opções: ");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Editar Produto");
            System.out.println("3. Pesquisar Produto");
            System.out.println("4. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    produto.menuInsercaoProduto();
                    break;
                case 2:
                    produto.menuAtualizacaoProduto();
                    break;
                case 3:
                    produto.menuPesquisaProduto();
                    break;
                case 4:
                    menuFun();
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }

        } while (opcao != 4);
    }

    public void relatorioMenu(){
        Scanner sc = new Scanner(System.in);
        Venda venda = new Venda();

        int opcao;

        do {
            System.out.println("Opções: ");
            System.out.println("1. Gerar relatorio da venda de produto");
            System.out.println("2. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            switch (opcao){
                case 1:
                    venda.gerarRelatorioPorProduto();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }while(opcao !=2);
    }

    public void menuFun(){
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Opções: ");
            System.out.println("1. Clientes");
            System.out.println("2. Produtos");
            System.out.println("3. Vendas");
            System.out.println("4. Relatorios");
            System.out.println("6. Sair");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    clientesMenu();
                    break;
                case 2:
                    produtosMenu();
                    break;
                case 3:
                    vendaMenu();
                    break;
                case 4:
                    relatorioMenu();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }

        } while (opcao != 5);
    }
}
