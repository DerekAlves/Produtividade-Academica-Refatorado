package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    static Scanner in = new Scanner(System.in);
    static Exception exc =  new PAexc();

    private static void printdata(Data data) { System.out.printf("%d / %d / %d\n", data.getDia(), data.getMes(), data.getAno()); }

    private static void listarpa(ArrayList<Publicacao> pub)
    {
        int i;
        System.out.print("Listando publicações...\n");
        for(i = 0; i < pub.size(); i++) System.out.println(pub.get(i).toString());
    }



    private static int findcollab(ArrayList<Colaborador> collabs, int id)
    {
        int i;
        for(i = 0; i < collabs.size(); i++)
        {
            if(id == collabs.get(i).getId())
            {
                System.out.print("Colaborador encontrado!\n");
                return i;
            }
        }
        System.out.print("Colaborador não encontrado!\n");
        return -1;
    }

    private static void listarcollabs(ArrayList<Colaborador> collabs)
    {
        int i;
        System.out.print("Listando colaboradores...\n");
        for(i = 0; i < collabs.size(); i++) System.out.println(collabs.get(i).toString());
    }

    private static void criarcollab(Laboratorio lab)//tipos: 1 - alunog, 2 - alunom, 3 - alunod, 4 - professores, 5 - pesquisadores.
    {
        int tipo;
        System.out.print("Digite o tipo do colaborador:\nTipos: 1 - Aluno Graduação | 2 - Aluno Mestrado | 3 - Aluno Doutorado | 4 - Professor | 5 - Pesquisador.\n");
        tipo = exc.loadintmargin(1, 5);
        Colaborador ncolab = null;
        if(tipo == 1) { System.out.print("Criando Aluno de Graduação!\n"); ncolab = new AlunoG(); }
        else if(tipo == 2) { System.out.print("Criando Aluno de Mestrado!\n"); ncolab = new AlunoM(); }
        else if(tipo == 3) { System.out.print("Criando Aluno de Doutorado!\n"); ncolab = new AlunoD(); }
        else if(tipo == 4) { System.out.print("Professor!\n"); ncolab = new Professor(); }
        else if(tipo == 5) { System.out.print("Criando Pesquisador!\n"); ncolab = new Pesquisador(); }

        System.out.println(ncolab.toString());
        lab.getParticipantes().add(ncolab);
    }


    private static void criarprojeto(Laboratorio lab)
    {
        System.out.print("Criando novo projeto...\n");
        Projeto pjt = new Projeto();
        lab.setPe(lab.getPe() + 1);

        System.out.print("O projeto precisa de um professor, Se o professor já estiver cadastrado no laboratório, Digite [1], Caso queira cadastrar um novo professor Digite [2]: ");
        int op = 0;
        while(op != 1 && op != 2)
        {
            op = exc.loadintmargin(1, 2);
            if(op == 1)
            {
                boolean flag = false;
                while(!flag)
                {
                    System.out.print("Deseja listar os colaboradores? Sim [1], Não [0].\n");
                    if(exc.loadintmargin(1, 2) == 1) listarcollabs(lab.getParticipantes());
                    System.out.print("Digite o ID do professor: \n");
                    int id = exc.loadint();
                    System.out.print("Procurando professor...\n");
                    int i = findcollab(lab.getParticipantes(), id);
                    if( lab.getParticipantes().get(i) instanceof Professor || i == -1)
                    {
                        pjt.getParticipantes().add(lab.getParticipantes().get(i));
                        flag = true;
                    }
                    else System.out.print("Tente novamente!\n");
                }

            }
            else if(op == 2)
            {
                System.out.print("Criando novo professor...\n");
                Professor novo = new Professor();
                System.out.print("Digite o id do professor criado: ");
                int i = findcollab(lab.getParticipantes(), exc.loadint());
                if(i != -1) pjt.getParticipantes().add(lab.getParticipantes().get(i));
                else System.out.print("Colaborador não encontrado! Tente Novamente!\n");
            }
            else System.out.print("Opção Inválida, digite novamente: ");
        }
        System.out.print("Novo projeto criado com sucesso!\n");
        lab.getProjetos().add(pjt);
    }

    private static void editprojeto(Laboratorio lab)
    {
        System.out.print("Deseja listar todos os projetos? [1] -  Sim | [2] - Não: ");
        if(exc.loadintmargin(1, 2) == 1) listarpjts(lab.getProjetos());
        //STATUS//1 - elaboração, 2 - em andamento, 3 - concluido.
        System.out.print("Digite o título do projeto a ser editado: ");
        int i = findpjts(lab.getProjetos(), in.nextLine());
        if(i != -1)
        {
            System.out.print("O que deseja editar no projeto?\n");
            System.out.print("[1] Para alocar participantes;\n");
            System.out.print("[2] Para alterar status;\n");
            System.out.print("[3] Adicionar publicação;\n");
            System.out.print("[4] Adicionar orientação;\n");

            int option =  exc.loadintmargin(1, 4);
            if(option == 1)
            {
                System.out.print("Opção - [1] Para alocar participantes;\n");
                if(lab.getProjetos().get(i).getStatus() != 2)
                {
                    System.out.print("Mude o status do projeto para adicionar colaboradores!\n");
                    System.out.print("Deseja mudar agora? [1] -  Sim | [2] - Não: ");
                    if(exc.loadintmargin(1, 2) == 1)
                    {
                        lab.getProjetos().get(i).setStatus(2);
                        System.out.print("Status setado para 2 - Em andamento!\n");
                        lab.setPe(lab.getPe() - 1);
                        lab.setPa(lab.getPa() + 1);
                    }
                }
                if(lab.getProjetos().get(i).getStatus() == 2)
                {
                    System.out.print("Deseja listar os colaboradores? [1] - Sim | [2] - Não: ");
                    if(exc.loadintmargin(1, 2) == 1) listarcollabs(lab.getParticipantes());
                    System.out.print("Digite o ID do colaborador a ser alocado; ");
                    int j = findcollab(lab.getParticipantes(), exc.loadint());
                    if(j == -1) System.out.print("Colaborador não encontrado! Tente Novamente!\n");
                    else if(lab.getParticipantes().get(j).isPartdeprojeto() && !(lab.getParticipantes().get(j) instanceof Professor)  ) System.out.print("Colaborador já está em um projeto! Ele não será adicionado!\n");
                    else
                    {
                        lab.getProjetos().get(i).getParticipantes().add(lab.getParticipantes().get(j));///TESTAR
                        System.out.print("Colaborador adicionado com sucesso! ");
                        lab.getParticipantes().get(j).setPartdeprojeto(true);
                    }
                }

            }
            else if(option == 2)
            {
                System.out.print("Opção - [2] Para alterar status;\n");
                if(lab.getProjetos().get(i).getStatus() == 1)
                {
                    System.out.print("Status Elaboração! Deseja mudar para Em andamento? [1] - Sim | [2] - Não: ");
                    if(exc.loadintmargin(1, 2) == 1)
                    {
                        lab.getProjetos().get(i).setStatus(2);
                        System.out.print("Status alterado com sucesso!\n");
                        lab.setPe(lab.getPe() - 1);
                        lab.setPa(lab.getPa() + 1);
                    }
                    else System.out.print("Status não alterado!\n");
                }
                if(lab.getProjetos().get(i).getStatus() == 2)
                {
                    System.out.print("Status Em andamento! Deseja mudar para Concluído? [1] - Sim | [2] - Não: ");
                    if(exc.loadintmargin(1, 2) == 1)
                    {
                        lab.getProjetos().get(i).setStatus(3);
                        for (Colaborador collab : lab.getParticipantes()) collab.setPartdeprojeto(false);
                        System.out.print("Status alterado com sucesso!\n");
                        lab.setPa(lab.getPa() - 1);
                        lab.setPc(lab.getPc() + 1);
                    }
                    else System.out.print("Status não alterado!\n");
                }
            }
            else if(option == 3)
            {
                System.out.print("Opção - [3] Adicionar publicação;\n");
                if(lab.getProjetos().get(i).getStatus() != 2) System.out.print("Mude o status para em andamento para adicionar uma publicação!\n");
                else
                {
                    Publicacao pub = new Publicacao(lab.getProjetos().get(i).getTitulo());

                    System.out.printf("Projeto de pesquisa associado: %s\n", lab.getProjetos().get(i).getTitulo());
                    lab.getProjetos().get(i).getPublicacoes().add(pub);
                    lab.setPap(lab.getPap() + 1);
                    System.out.print("Publicação adicionada com sucesso!\n");
                }

            }
            else if(option == 4)
            {
                System.out.print("Opção - [4] Adicionar orientação;\n");
                System.out.print("Falta finalizar, nenhuma alteração foi feita!\n");
            }
        }
        else System.out.print("Tente novamente!\n");
    }

    private static void consultacollab(Laboratorio lab)
    {
        System.out.print("Deseja listar todos os colaboradores? [1] - Sim | [2] -  Não: ");
        if(exc.loadintmargin(1, 2) == 1) listarcollabs(lab.getParticipantes());
        System.out.print("Digite o ID do colaborador a ser consultado: ");
        int i = findcollab(lab.getParticipantes(), exc.loadint());
        if(i != -1) System.out.println(lab.getParticipantes().get(i).toString());
        else System.out.print("Colaborador não encontrado! Tente Novamente!");



        //LISTAR PJTS


    }

    private static void consultapjt(Laboratorio lab)
    {
        System.out.print("Deseja listar todos os projetos? [1] - Sim | [2] -  Não: ");
        if(exc.loadintmargin(1, 2) == 1) listarpjts(lab.getProjetos());
        System.out.print("Digite o nome do projeto a ser consultado: ");
        int i = findpjts(lab.getProjetos(), in.nextLine());
        if(i != -1)
        {
            System.out.printf("Projeto: %s\n", lab.getProjetos().get(i).getTitulo());
            if(lab.getProjetos().get(i).getStatus() == 1) System.out.print("Status: Em elaboração!\n");
            else if(lab.getProjetos().get(i).getStatus() == 2) System.out.print("Status: Em andamento!\n");
            else if(lab.getProjetos().get(i).getStatus() == 3) System.out.print("Status: Concluído!\n");
            System.out.print("Data de Inicio: ");
            printdata(lab.getProjetos().get(i).getInicio());
            System.out.print("Data de Termino: ");
            printdata(lab.getProjetos().get(i).getTermino());
            System.out.printf("Agencia financiadora: %s | Valor financiado %.2f\n", lab.getProjetos().get(i).getAgfinan(), lab.getProjetos().get(i).getValfinan());
            System.out.printf("Objetivo: %s\nDescrição: %s\n", lab.getProjetos().get(i).getObjetivo(), lab.getProjetos().get(i).getDescricao());
            //System.out.print("Listando colaboradores...\n");
            listarcollabs(lab.getProjetos().get(i).getParticipantes());
            listarpa(lab.getProjetos().get(i).getPublicacoes());
        }
        else System.out.print("Projeto não encontrado! Tente Novamente!\n");


        //LISTAR PA ORD D DATA
    }


    private static void listarpjts(ArrayList<Projeto> pjts)
    {
        int i;
        System.out.print("Listando projetos...\n");
        for(i = 0; i < pjts.size(); i++)
            System.out.printf("%s\n", pjts.get(i).getTitulo());
    }

    private static int findpjts(ArrayList<Projeto> pjts, String titulo)
    {
        int i;
        //System.out.print("Listando administradores...\n");
        for(i = 0; i < pjts.size(); i++)
            if(titulo.equals(pjts.get(i).getTitulo()))
            {
                System.out.print("Projeto encontrado!\n");
                return i;
            }
        System.out.print("Projeto não encontrado!\n");
        return -1;//nao achou.!!
    }


    private static void criaradm(ArrayList<Adm> adms)
    {
        System.out.print("Criando novo Administrador...\n");
        Adm adm =  new Adm();
        System.out.print("Nome: ");
        adm.setNome(in.nextLine());
        System.out.print("Usuário: ");
        adm.setUser(in.nextLine());
        System.out.print("Senha: ");
        adm.setSenha(in.nextLine());
        System.out.print("Email: ");
        adm.setEmail(in.nextLine());
        adms.add(adm);
        System.out.print("Novo administrador criado com sucesso!\n");
    }


    private static void listaradms(ArrayList<Adm> adms)
    {
        int i;
        System.out.print("Listando administradores...\n");
        for(i = 0; i < adms.size(); i++)
            System.out.printf("%s\n", adms.get(i).getUser());
    }

    private static int findadm(ArrayList<Adm> adms, String user)
    {
        int i;
        //System.out.print("Listando administradores...\n");
        for(i = 0; i < adms.size(); i++)
            if(user.equals(adms.get(i).getUser()))
            {
                System.out.print("Usuário encontrado!\n");
                return i;
            }
        System.out.print("Usuário não encontrado!\n");
        return -1;//nao achou.!!
    }

    private static void relatorio(Laboratorio lab)
    {
        //a. Número de colaboradores
        //b. Número de projetos em elaboração
        //c. Número de projetos em andamento
        //d. Número de projetos concluídos
        //e. Número total de projetos
        //f. Número de produção acadêmica por tipo de produção

        System.out.print("Gerando Relatório...\n");
        System.out.printf("Número de colaboradores          = %4d\n", lab.getParticipantes().size());
        System.out.printf("Número de projetos em elaboração = %4d\n", lab.getPe());
        System.out.printf("Número de projetos em andamento  = %4d\n", lab.getPa());
        System.out.printf("Número de projetos concluídos    = %4d\n", lab.getPc());
        System.out.printf("Número total de projetos         = %4d\n", lab.getProjetos().size());
        System.out.printf("Publicações                      = %4d\n", lab.getPap());
        System.out.printf("Orientações                      = %4d\n", lab.getPao());
    }




    private static void menu(Laboratorio laboratorio, ArrayList<Adm> adms)
    {
        boolean islogged =  false;
        boolean exit = false;
        int option;
        while(!exit)
        {
            if(islogged)
            {
                System.out.print("Deseja deslogar? 1 - S | 2 - N.\n");
                if(exc.loadintmargin(1, 2) == 1)
                {
                    islogged = false;
                    System.out.print("Usuário deslogado.\n");
                }
                else System.out.print("Usuário continua logado.\n");
            }

            while(!islogged)
            {
                System.out.print("Usuário de administrador não logado!\nLogue para fazer alterações\n");
                listaradms(adms);
                System.out.print("Digite o nome de usuário para logar: ");
                int i = findadm(adms, in.nextLine());
                if( i>= 0)
                {
                    System.out.print("Digite a senha: ");
                    if(in.nextLine().equals(adms.get(i).getSenha()))
                    {
                        System.out.print("Senha correta! Usuário logado!\n");
                        islogged = true;
                    }
                    else System.out.print("Senha inválida! Tente novamente\n");
                }
            }
            //menu(laboratorio, adms, islogged);
            System.out.print("----------------------------------------------------------------------------\n");
            System.out.print("Digite:\n");
            System.out.print("[0]  - Sair;\n");
            System.out.print("[1]  - Adicionar colaborador ao laboratório;\n");
            System.out.print("[2]  - Adicionar projeto ao laboratório;\n");
            System.out.print("[3]  - Editar projeto do laboratório;\n");
            System.out.print("[4]  - Consulta colaborador;\n");
            System.out.print("[5]  - Consulta projeto;\n");
            System.out.print("[6]  - Relatório de produção Acadêmica;\n");
            System.out.print("[7]  - Cadastrar novo administrador.\n");

            option = exc.loadintmargin(0, 7);

            if(option == 0)
            {
                System.out.print("Opção [0] escolhida - Sair!\n");//v
                exit = true;
            }
            else if(option == 1)
            {
                System.out.print("Opção [1] escolhida - Adicionar colaborador ao laboratório!\n");//v
                System.out.print("----------------------------------------------------------------------------\n");
                criarcollab(laboratorio);
            }
            else if(option == 2)
            {
                System.out.print("Opção [2] escolhida - Adicionar projeto ao laboratório!\n");//v
                System.out.print("----------------------------------------------------------------------------\n");
                criarprojeto(laboratorio);
            }
            else if(option == 3)
            {
                System.out.print("Opção [3] escolhida - Editar projeto do laboratório!\n");//
                System.out.print("----------------------------------------------------------------------------\n");
                editprojeto(laboratorio);
            }
            else if(option == 4)
            {
                System.out.print("Opção [4] escolhida - Consulta colaborador!\n");//
                System.out.print("----------------------------------------------------------------------------\n");
                consultacollab(laboratorio);
            }
            else if(option == 5)
            {
                System.out.print("Opção [5] escolhida - Consulta projeto!\n");//
                System.out.print("----------------------------------------------------------------------------\n");
                consultapjt(laboratorio);
            }
            else if(option == 6)
            {
                System.out.print("Opção [6] escolhida - Relatório de produção Acadêmica!\n");//
                System.out.print("----------------------------------------------------------------------------\n");
                relatorio(laboratorio);
            }
            else if(option == 7)
            {
                System.out.print("Opção [7] escolhida - Cadastrar novo administrador!\n");//v
                System.out.print("----------------------------------------------------------------------------\n");
                criaradm(adms);
            }
        }
    }


    public static void main(String[] args)
    {
        Laboratorio laboratorio = new Laboratorio();
        ArrayList<Adm> adms = new ArrayList<Adm>();
        System.out.print("Nenhum administrador criado, crie um usuário de administrador para continuar!\n");
        System.out.print("----------------------------------------------------------------------------\n");
        criaradm(adms);
        menu(laboratorio, adms);
    }
}
