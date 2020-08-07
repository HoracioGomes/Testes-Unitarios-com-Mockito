package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {

    private double delta = 0.0001;
    private final Usuario HORACIO = new Usuario("Horacio");
    private final Leilao LEILAO_CONSOLE = new Leilao("PS5");

    @Test
    public void getDescricao_QuandoRecebeDescricao() {
        String descricao = LEILAO_CONSOLE.getDescricao();
        assertEquals("PS5", descricao);
    }

    @Test
    public void getMaiorLance_QuandoRecebeUmLance() {
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 500.));
        double maiorLanceRecebido = LEILAO_CONSOLE.getMaiorLance();
        assertEquals(500, maiorLanceRecebido, delta);
    }

    @Test
    public void getMaiorLance_QuandoRecebeMaisDeUmLance_EmOrdemCrescente() {
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 300));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Mauricio"), 1000));
        double maiorLanceComputador = LEILAO_CONSOLE.getMaiorLance();
        assertEquals(1000, maiorLanceComputador, delta);
    }

    @Test
    public void getMaiorLance_QuandoRecebeMaisDeUmLance_EmOrdemDecrescente() {
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Mauricio"), 900));
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 300));
        double maiorLanceNotebook = LEILAO_CONSOLE.getMaiorLance();
        assertEquals(900, maiorLanceNotebook, delta);
    }

    @Test
    public void getMenorLance_QuandoRecebeUmLance() {
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 500.));
        double menorLanceRecebido = LEILAO_CONSOLE.getMenorLance();
        assertEquals(500, menorLanceRecebido, delta);
    }


    @Test
    public void getMenorLance_QuandoRecebeMaisDeUmLance_EmOrdemCrescente() {
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 300));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Mauricio"), 1000));
        double menorLanceComputador = LEILAO_CONSOLE.getMenorLance();
        assertEquals(300, menorLanceComputador, delta);
    }

    @Test
    public void getMenorLance_QuandoRecebeMaisDeUmLance_EmOrdemDecrescente() {
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Mauricio"), 900));
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 300));
        double menorLanceNotebook = LEILAO_CONSOLE.getMenorLance();
        assertEquals(300, menorLanceNotebook, delta);
    }
}