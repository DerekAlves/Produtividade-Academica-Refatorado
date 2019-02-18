package com.company;

public class AlunoG extends Colaborador
{
    public AlunoG()
    {
        super();
        System.out.print("Criando com sucesso!\n");
    }

    @Override
    public String toString() {
        return "-------------------------- ALUNO GRADUAÇÃO --------------------------\n" + super.toString();
    }
}
