package com.company;

public class AlunoD extends Colaborador
{
    public AlunoD()
    {
        super();
        System.out.print("Criando com sucesso!\n");
    }

    @Override
    public String toString() {
        return "-------------------------- ALUNO DOUTORADO --------------------------\n" + super.toString();
    }
}
