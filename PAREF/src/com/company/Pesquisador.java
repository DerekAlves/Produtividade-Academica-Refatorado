package com.company;

public class Pesquisador extends Colaborador
{
    public Pesquisador()
    {
        super();
        System.out.print("Criando com sucesso!\n");
    }

    @Override
    public String toString() {
        return "-------------------------- PESQUISADOR --------------------------\n" + super.toString();
    }
}


