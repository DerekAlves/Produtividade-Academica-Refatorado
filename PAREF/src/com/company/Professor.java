package com.company;

public class Professor extends Colaborador
{
    public Professor()
    {
        super();
        System.out.print("Criando com sucesso!\n");
    }

    @Override
    public String toString() {
        return "-------------------------- PROFESSOR --------------------------\n" + super.toString();
    }
}
