package View;

import DAO.EmpresaDAO;
import DAO.UsuarioDAO;
import Model.EXEPTIONS.ExistingInstance;
import Model.EXEPTIONS.InvalidFormatException;
import Model.EXEPTIONS.NoSuchArgument;
import Model.EXEPTIONS.WrongPass;
import Model.Empresa;
import Model.Login;
import Model.UserApp;
import Model.Usuario;

import java.util.Scanner;

public record LoginImpl(String email, String pass) implements Login<UserApp> {

    @Override
    public UserApp toLogin(String email, String password) throws NoSuchArgument {
        Usuario user = new UsuarioDAO().searchByValue(email);
        if (user ==  null) {
            throw new NoSuchArgument("Email não encontrado!");
        }
        if (!user.getSenha().equals(password)){
            throw new WrongPass("Senha inválida!");
        }
        Empresa emp = new EmpresaDAO().searchByValue(user.getID());
        if (emp != null){
            return new UserApp(email, password, user, emp);
        } else {
            System.out.println("Ops! Parece que você não tem nenhuma empresa cadastrada!\nVamos fazer o cadastro da sua empresa.\n\n");
            Empresa newE = createEnterprise();
            return new UserApp(email, password, user, newE);
        }
    }
    public static void telaPrincipal(UserApp user, Scanner scan){
        boolean tela = true;
        while (tela){
            Empresa eTemp = user.getEmpresa();
            Usuario uTemp = user.getUsuario();
            Login.showLoginInfos(uTemp.getName(), uTemp.getEmail(), eTemp.getName(), eTemp.getCNPJ());
            System.out.print("HOME\n\n0 - Loggof\n1 - Consulta de guias\nOpção: ");
            switch (scan.nextLine()){
                case "0":
                    tela = false;
                    break;

                case "1": GuiaView.consultaDeGuias("HOME", uTemp, eTemp);

                default:
                    System.out.println("Opção inválida!");
            }
        }

        /*
        //switch expression
        int days = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 2 -> 28;
            default -> 0;
        };
         */
    }

    public Empresa createEnterprise() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Por favor, digite seu CNPJ: ");
            String cnpj = scanner.nextLine();

            System.out.print("Certo, agora digite o nome da sua empresa: ");
            String entName = scanner.nextLine();

            System.out.println("Confirma? " + cnpj + " - " + entName);
            System.out.print("Qualquer tecla - Sim\n2 - Não\nOpção: ");

            String confirm = scanner.nextLine();
            if (confirm.equals("2")) {
                continue; // volta ao início do laço
            }

            try {
                return Empresa.create(cnpj, entName, Empresa.Status.ACTIVE);
            } catch (InvalidFormatException | ExistingInstance e) {
                System.out.println("Erro ao criar empresa: " + e.getMessage());
                System.out.println("Vamos tentar de novo...\n");
            }
        }
    }

    public static void criarUser (Scanner scan){
        System.out.print("CADASTRO DE USUÁRIO\nPor favor, digite seu nome completo: ");
        String nome = scan.nextLine();
        System.out.print("OK!\nPor favor, digite um email: ");
        String email = scan.nextLine();
        System.out.print("Por favor, digite uma senha: ");
        String senha = scan.nextLine();
        System.out.println("CADASTRO DE EMPRESA\nPor favor, digite seu número de CNPJ: ");
        String CNPJ = scan.nextLine();
        System.out.println("CADASTRO DE EMPRESA\nPor favor, digite o nome da sua empresa: ");
        String nomeEmpresa = scan.nextLine();
        try {
            Empresa e = Empresa.create(CNPJ, nomeEmpresa, Empresa.Status.ACTIVE);
            Usuario u = Usuario.create(nome, email, senha, e);
            System.out.println("\n\nCadastro efetuado com sucesso!");
            UserApp userApp = new LoginImpl(email, senha).toLogin(email, senha);
            LoginImpl.telaPrincipal(userApp, scan);
        } catch (ExistingInstance  | InvalidFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Cadastro cancelado!");
        } catch (NoSuchArgument e) {
            System.out.println(e.getMessage());;
        }
    }

    public static void recuperatePass (Scanner scan){
        System.out.print("Por favor, digite o seu email: ");
        String email = scan.nextLine();
        System.out.println("Agora digite sua nova senha: ");
        String novaSenha = scan.nextLine();
        Usuario.recuperatePassword(email, novaSenha);
    }
}
