package org.example;

import java.util.Date;

public class ProcessadorDeVendas {

    private static final int TAMANHO_DOCUMENTO = 11;
    private static final double LIMITE_IMPOSTO_MAIOR = 100.0;
    private static final double TAXA_IMPOSTO_MAIOR = 0.15;
    private static final double TAXA_IMPOSTO_MENOR = 0.05;

    private final Cliente cliente;
    private final double[] precos;
    private final int[] quantidades;
    private final EstrategiaFrete estrategiaFrete;

    public ProcessadorDeVendas(
            Cliente cliente,
            double[] precos,
            int[] quantidades,
            EstrategiaFrete estrategiaFrete) {
        this.cliente = cliente;
        this.precos = precos;
        this.quantidades = quantidades;
        this.estrategiaFrete = estrategiaFrete;
    }

    public void processar() {
        System.out.println("Iniciando processamento...");

        validarVenda();

        double subtotal = calcularSubtotal();
        double totalImpostos = calcularImpostos();
        double frete = estrategiaFrete.calcularFrete(cliente);
        double totalFinal = subtotal + totalImpostos + frete;

        imprimirRecibo(subtotal, totalImpostos, frete, totalFinal);
    }

    private void validarVenda() {
        if (cliente == null || cliente.getDocumento() == null
                || cliente.getDocumento().length() != TAMANHO_DOCUMENTO) {
            throw new IllegalArgumentException("Documento invalido.");
        }
        if (cliente.getEndereco() == null || precos == null || quantidades == null
                || estrategiaFrete == null) {
            throw new IllegalArgumentException("Dados da venda incompletos.");
        }
        if (precos.length != quantidades.length) {
            throw new IllegalArgumentException("Precos e quantidades devem ter o mesmo tamanho.");
        }
    }

    private double calcularSubtotal() {
        double subtotal = 0;
        for (int indice = 0; indice < precos.length; indice++) {
            subtotal += precos[indice] * quantidades[indice];
        }
        return subtotal;
    }

    private double calcularImpostos() {
        double totalImpostos = 0;
        for (int indice = 0; indice < precos.length; indice++) {
            double subtotalItem = precos[indice] * quantidades[indice];
            double taxaImposto = precos[indice] > LIMITE_IMPOSTO_MAIOR
                    ? TAXA_IMPOSTO_MAIOR
                    : TAXA_IMPOSTO_MENOR;
            totalImpostos += subtotalItem * taxaImposto;
        }
        return totalImpostos;
    }

    private void imprimirRecibo(
            double subtotal,
            double totalImpostos,
            double frete,
            double totalFinal) {
        System.out.println("=====================================");
        System.out.println("RECIBO DE VENDA");
        System.out.println("=====================================");
        System.out.println("Data: " + new Date());
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Documento: " + cliente.getDocumento());
        System.out.println("-------------------------------------");
        System.out.println("Subtotal Itens: R$ " + subtotal);
        System.out.println("Total Impostos: R$ " + totalImpostos);
        System.out.println("Frete: R$ " + frete);
        System.out.println("-------------------------------------");
        System.out.println("TOTAL A PAGAR: R$ " + totalFinal);
        System.out.println("=====================================");
    }
}
