package com.company;

//colaboradores: alunos de graduação, alunos
//de mestrado, alunos de doutorado, professores e pesquisadores.

import java.util.ArrayList;
import java.util.Scanner;

public class Colaborador {

    static int gid = 0;
    private int id;
    private String nome;
    private String email;
    //private int tipo;//1 - alunog, 2 - alunom, 3 - alunod, 4 - professores, 5 - pesquisadores.
    //private String tipostr;
    private boolean partdeprojeto = false;
    private ArrayList<Projeto> projetos = new ArrayList<Projeto>();
    private ArrayList<Publicacao> publicacao = new ArrayList<Publicacao>();

    public Colaborador()
    {
        Scanner in = new Scanner(System.in);

        gid++;
        this.id = gid;
        System.out.printf("Novo ID criado! - ID = %d\n", this.getId());
        System.out.print("Digite o nome: ");
        this.nome = in.nextLine();
        System.out.print("Digite o email: ");
        this.email = in.nextLine();
    }

    @Override
    public String toString()
    {
        String pa;
        if(partdeprojeto) pa = "Sim";
        else pa = "Não";
        return "Nome: " + nome + "\nID: " + id + "\nEmail: " + email + "\nParticipa de projeto? " + pa + "!\n";
    }

    public static int getGid() { return gid; }
    public static void setGid(int gid) { Colaborador.gid = gid; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public ArrayList<Projeto> getProjetos() { return projetos; }
    public void setProjetos(ArrayList<Projeto> projetos) { this.projetos = projetos; }

    public ArrayList<Publicacao> getPublicacao() { return publicacao; }
    public void setPublicacao(ArrayList<Publicacao> publicacao) { this.publicacao = publicacao; }

    public boolean isPartdeprojeto() { return partdeprojeto; }
    public void setPartdeprojeto(boolean partdeprojeto) { this.partdeprojeto = partdeprojeto; }
}
