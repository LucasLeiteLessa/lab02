package org.example;

public class Cliente {

    private final String nome;
    private final String documento;
    private final String endereco;

    public Cliente(String nome, String documento, String endereco) {
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }
}
