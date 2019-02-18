package com.company;

public class AlunoM extends Colaborador
{
    public AlunoM()
    {
        super();
        System.out.print("Criando com sucesso!\n");
    }

    @Override
    public String toString() {
        return "-------------------------- ALUNO MESTRADO --------------------------\n" + super.toString();
    }
}
