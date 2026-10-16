package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        double[] valores = {50.0, 150.0, 30.0};
        int[] quantidades = {2, 1, 3};

        Cliente clienteVip = new Cliente(
                "João Silva", "12345678901", "Rua das Flores, 123 - SP");
        ProcessadorDeVendas vendaVip = new ProcessadorDeVendas(
                clienteVip,
                valores,
                quantidades,
                new FreteVipStrategy()
        );
        vendaVip.processar();

        Cliente clienteSudeste = new Cliente(
                "Maria Souza", "98765432100", "Avenida Brasil, 456 - RJ");
        ProcessadorDeVendas vendaSudeste = new ProcessadorDeVendas(
                clienteSudeste,
                valores,
                quantidades,
                new FreteSudesteStrategy()
        );
        vendaSudeste.processar();

        Cliente clienteNacional = new Cliente(
                "Carlos Lima", "45678912300", "Rua Central, 789 - BA");
        ProcessadorDeVendas vendaNacional = new ProcessadorDeVendas(
                clienteNacional,
                valores,
                quantidades,
                new FreteNacionalStrategy()
        );
        vendaNacional.processar();
    }
}
