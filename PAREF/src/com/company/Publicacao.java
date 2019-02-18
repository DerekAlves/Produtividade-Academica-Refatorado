package com.company;

//título, nome da conferência onde foi publicada, ano de publicação e projeto
//de pesquisa associado (se houver).

import java.util.Scanner;

public class Publicacao
{
    private String titulo;
    private String nomeconf;
    private Data publicacao;
    private String ppassociado;

    public Publicacao(String ppa)
    {
        Exception exc = new PAexc();
        Scanner in = new Scanner(System.in);

        System.out.print("Digite o título da publicação: ");
        this.titulo = in.nextLine();
        System.out.print("Digite o nome da conferência: ");
        this.nomeconf = in.nextLine();
        System.out.print("Data da publicação\n");
        this.publicacao = new Data();
        this.ppassociado = ppa;
    }

    @Override
    public String toString() {
        return "-------------------------- PUBLICAÇÃO --------------------------\n" + "Título: " + titulo + "\nConferência: " + nomeconf + "\n" + publicacao.toString() + "Projeto de Pesquisa Associado: " + ppassociado;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getNomeconf() { return nomeconf; }
    public void setNomeconf(String nomeconf) { this.nomeconf = nomeconf; }

    public Data getPublicacao() { return publicacao; }
    public void setPublicacao(Data publicacao) { this.publicacao = publicacao; }

    public String getPpassociado() { return ppassociado; }
    public void setPpassociado(String ppassociado) { this.ppassociado = ppassociado; }
}
