package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        double[] valores = {50.0, 150.0, 30.0};
        int[] quantidades = {2, 1, 3};

        ProcessadorDeVendas p = new ProcessadorDeVendas(
                "João Silva",
                "12345678901",
                false,
                "Rua das Flores, 123 - SP",
                valores,
                quantidades
        );

        p.processar();
    }
}
