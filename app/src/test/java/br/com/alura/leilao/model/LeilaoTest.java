package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    private double delta = 0.0001;

    @Test
    public void getDescricao() {
        //Criar cenario de teste
        Leilao leilaoExemlo = new Leilao("LeilaoTal");
        //executar acao esperada
        String descricao = leilaoExemlo.getDescricao();
        //testar resultado esperado
        assertEquals("LeilaoTal", descricao);

    }

    @Test
    public void getMaiorLance(){
        Leilao leilaoExemlo = new Leilao("LeilaoTal");
        leilaoExemlo.propoe(new Lance(new Usuario("Fulano"),500.));
        double maiorLanceRecebido = leilaoExemlo.getMaiorLance();
        assertEquals(500, maiorLanceRecebido, delta);
    }
}