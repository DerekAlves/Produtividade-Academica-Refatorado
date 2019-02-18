package com.company;

public class Data
{
    private int dia;
    private int mes;
    private int ano;

    public Data()
    {
        Exception exc = new PAexc();
        System.out.print("Digite a data com o seguinte formato (DD MM AAAA): ");
        this.dia = exc.loadintmargin(1, 31);
        this.mes = exc.loadintmargin(1, 12);
        this.ano = exc.loadint();
    }

    @Override
    public String toString() {
        return "Data: " + dia + "/" + mes + "/" + ano + "\n";
    }

    public int getDia() { return dia; }
    public void setDia(int dia) { this.dia = dia; }

    public int getMes() { return mes; }
    public void setMes(int mes) { this.mes = mes; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
}
