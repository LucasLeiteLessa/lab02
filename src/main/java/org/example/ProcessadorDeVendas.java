package org.example;

import java.util.Date;

public class ProcessadorDeVendas {

    private static final int TAMANHO_DOCUMENTO = 11;
    private static final double LIMITE_IMPOSTO_MAIOR = 100.0;
    private static final double TAXA_IMPOSTO_MAIOR = 0.15;
    private static final double TAXA_IMPOSTO_MENOR = 0.05;
    private static final double FRETE_ESTADOS_PROXIMOS = 15.0;
    private static final double FRETE_OUTROS_ESTADOS = 50.0;

    private final String nomeCliente;
    private final String documento;
    private final boolean clienteVip;
    private final String endereco;
    private final double[] precos;
    private final int[] quantidades;

    public ProcessadorDeVendas(
            String nomeCliente,
            String documento,
            boolean clienteVip,
            String endereco,
            double[] precos,
            int[] quantidades) {
        this.nomeCliente = nomeCliente;
        this.documento = documento;
        this.clienteVip = clienteVip;
        this.endereco = endereco;
        this.precos = precos;
        this.quantidades = quantidades;
    }

    public void processar() {
        System.out.println("Iniciando processamento...");

        validarVenda();

        double subtotal = calcularSubtotal();
        double totalImpostos = calcularImpostos();
        double frete = calcularFrete();
        double totalFinal = subtotal + totalImpostos + frete;

        imprimirRecibo(subtotal, totalImpostos, frete, totalFinal);
    }

    private void validarVenda() {
        if (documento == null || documento.length() != TAMANHO_DOCUMENTO) {
            throw new IllegalArgumentException("Documento invalido.");
        }
        if (endereco == null || precos == null || quantidades == null) {
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

    private double calcularFrete() {
        if (clienteVip) {
            return 0;
        }
        return possuiFreteReduzido() ? FRETE_ESTADOS_PROXIMOS : FRETE_OUTROS_ESTADOS;
    }

    private boolean possuiFreteReduzido() {
        return endereco.contains("SP")
                || endereco.contains("RJ")
                || endereco.contains("MG");
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
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Documento: " + documento);
        System.out.println("-------------------------------------");
        System.out.println("Subtotal Itens: R$ " + subtotal);
        System.out.println("Total Impostos: R$ " + totalImpostos);
        System.out.println("Frete: R$ " + frete);
        System.out.println("-------------------------------------");
        System.out.println("TOTAL A PAGAR: R$ " + totalFinal);
        System.out.println("=====================================");
    }
}
