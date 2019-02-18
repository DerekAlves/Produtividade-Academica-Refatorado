package com.company;

import java.util.ArrayList;

public class Orientacao
{
    private Publicacao orienta;
    ArrayList<Colaborador> participantes;

    public Publicacao getOrienta() { return orienta; }
    public void setOrienta(Publicacao orienta) { this.orienta = orienta; }

    public ArrayList<Colaborador> getParticipantes() { return participantes; }
    public void setParticipantes(ArrayList<Colaborador> participantes) { this.participantes = participantes; }
}
