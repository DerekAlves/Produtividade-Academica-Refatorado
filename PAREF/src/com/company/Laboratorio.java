package com.company;

import java.util.ArrayList;

//Número de colaboradores
//b. Número de projetos em elaboração
//c. Número de projetos em andamento
//d. Número de projetos concluídos
//e. Número total de projetos
//f. Número de produção acadêmica por tipo de produção


public class Laboratorio
{
    private ArrayList<Colaborador> participantes = new ArrayList<Colaborador>();
    private ArrayList<Projeto> projetos =  new ArrayList<Projeto>();
    private int pe = 0;
    private int pa = 0;
    private int pc = 0;
    private int pap = 0;
    private int pao = 0;

    public ArrayList<Colaborador> getParticipantes() { return participantes; }
    public void setParticipantes(ArrayList<Colaborador> participantes) { this.participantes = participantes; }

    public ArrayList<Projeto> getProjetos() { return projetos; }
    public void setProjetos(ArrayList<Projeto> projetos) { this.projetos = projetos; }

    public int getPe() { return pe; }
    public void setPe(int pe) { this.pe = pe; }

    public int getPa() { return pa; }
    public void setPa(int pa) { this.pa = pa; }

    public int getPc() { return pc; }
    public void setPc(int pc) { this.pc = pc; }

    public int getPap() { return pap; }
    public void setPap(int pap) { this.pap = pap; }

    public int getPao() { return pao; }
    public void setPao(int pao) { this.pao = pao; }
}
