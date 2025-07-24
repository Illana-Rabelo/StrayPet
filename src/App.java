import java.util.Scanner;

public class Main {

    static final int MAX_USUARIOS = 100;
    static int[] ids = new int[MAX_USUARIOS];
    static String[] nomes = new String[MAX_USUARIOS];
    static String[] emails = new String[MAX_USUARIOS];
    static String[] telefones = new String[MAX_USUARIOS];
    static int totalUsuarios = 0;
    static int proximoId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            exibirMenu(); //FUNÇÃO DE EXIBIR O MENU
            opcao = scanner.nextInt();
            scanner.nextLine(); //LIMPEZA DO BUFFER
            //MENU SIMPLES PARA CADASTRO DE USUÁRIOS
            switch (opcao) {
                case 1:
                    cadastrarUsuario(scanner);
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    //FUNÇÃO PARA EXIBIR O MENU
    public static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Listar Usuários");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    //FUNÇÃO PARA CADASTRAR USUÁRIOS
    public static void cadastrarUsuario(Scanner sc) {
        //CONDIÇÃO PARA VERIFICAR SE O LIMITE DE USUÁRIOS FOI ATINGIDO
        if (totalUsuarios >= MAX_USUARIOS) {
            System.out.println("Limite de usuários atingido. Não é possível cadastrar mais usuários.");
            return;
        }

        String nome;
        do {
            System.out.print("Nome completo: ");
            nome = sc.nextLine();
            if (nome.trim().isEmpty() || nome.length() < 3) {
                System.out.println("Nome inválido. O nome não pode ser vazio e deve ter no mínimo 3 caracteres.");
            }
        } while (nome.trim().isEmpty() || nome.length() < 3);

        String email;
        do {
            System.out.print("Email: ");
            email = sc.nextLine();
            // Validação de email simples: deve conter "@" e "." e não ser vazio
            if (email.trim().isEmpty() || !email.contains("@") || !email.contains(".")) {
                System.out.println("Email inválido. Por favor, insira um email válido (ex: seu.email@dominio.com).");
            } else if (emailJaExiste(email)) {
                System.out.println("Email já cadastrado. Por favor, insira um email único.");
                email = ""; // Limpa o email para forçar nova entrada
            }
        } while (email.trim().isEmpty() || !email.contains("@") || !email.contains(".") || email.isEmpty()); 
        // Adiciona email.isEmpty() para tratar o caso de email duplicado

        String telefone;
        do {
            System.out.print("Telefone (apenas números, mínimo 8 dígitos): ");
            telefone = sc.nextLine();
            // Validação de telefone simples: deve conter apenas números e ter no mínimo 8 dígitos
            if (telefone.trim().isEmpty() || !telefone.matches("\\d+") || telefone.length() < 8) {
                System.out.println("Telefone inválido. Por favor, insira apenas números e no mínimo 8 dígitos.");
            }
        } while (telefone.trim().isEmpty() || !telefone.matches("\\d+") || telefone.length() < 8);

        ids[totalUsuarios] = proximoId;
        nomes[totalUsuarios] = nome;
        emails[totalUsuarios] = email;
        telefones[totalUsuarios] = telefone;

        totalUsuarios++;
        proximoId++; //INCREMENTA O ID PARA O PRÓXIMO USUÁRIO

        System.out.println("Usuário cadastrado com sucesso!");
    }

    //FUNÇÃO PARA VERIFICAR SE O EMAIL JÁ EXISTE
    public static boolean emailJaExiste(String email) {
        for (int i = 0; i < totalUsuarios; i++) {
            if (emails[i].equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    //FUNÇÃO PARA LISTAR USUÁRIOS CADASTRADOS
    public static void listarUsuarios() {
        //VERIFICA SE HÁ USUÁRIOS CADASTRADOS
        if (totalUsuarios == 0) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        //EXIBE A LISTA DE USUÁRIOS CADASTRADOS
        System.out.println("\n--- Usuários Cadastrados ---");
        for (int i = 0; i < totalUsuarios; i++) {
            System.out.println("ID: " + ids[i]);
            System.out.println("Nome: " + nomes[i]);
            System.out.println("Email: " + emails[i]);
            System.out.println("Telefone: " + telefones[i]);
            System.out.println("----------------------------");
        }
    }
}
