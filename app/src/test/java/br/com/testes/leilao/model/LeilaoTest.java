package br.com.testes.leilao.model;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LeilaoTest {

    private double delta = 0.0001;
    private final Usuario HORACIO = new Usuario("Horacio");
    private final Leilao LEILAO_CONSOLE = new Leilao("PS5");

    @Before
    public void instancias() {
    }

    @Test
    public void getDescricao_QuandoRecebeDescricao() {
        String descricao = LEILAO_CONSOLE.getDescricao();
        assertThat(descricao, is(equalTo("PS5")));
    }


    @Test
    public void getMaiorLance_QuandoRecebeUmLance() {
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 500.));
        double maiorLanceRecebido = LEILAO_CONSOLE.getMaiorLance();
        assertEquals(500, maiorLanceRecebido, delta);
    }

    @Test
    public void deve_devolverTresMaioresLances_quando_recebeQuatroLances() {
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 100.));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Maria"), 200.));
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 99.));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("João"), 500.5));

        List<Lance> tresMaioresLancesDevolvidos = LEILAO_CONSOLE.getTresMaioresLances();

//        for (int i = 0; i < tresMaioresLancesDevolvidos.size(); i++) {
//            System.out.println("" + tresMaioresLancesDevolvidos.get(i).getValor());
//        }

        assertThat(tresMaioresLancesDevolvidos, both(Matchers.<Lance>hasSize(3))
                .and(contains(
                        new Lance(new Usuario("João"), 500.5),
                        new Lance(new Usuario("Maria"), 200.),
                        new Lance(HORACIO, 100.)

                        )
                )
        );
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

//    @Test
//    public void getMenorLance_QuandoRecebeMaisDeUmLance_EmOrdemDecrescente() {
//        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Mauricio"), 900));
//        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 300));
//        double menorLanceNotebook = LEILAO_CONSOLE.getMenorLance();
//        assertEquals(300, menorLanceNotebook, delta);
//    }

    @Test
    public void testaListaTresMaioresLances_quandoRecebeExatosTresLances() {
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Mauro"), 500));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Fabio"), 600));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Adalberto"), 700));
        List<Lance> listaTresMaioresLances = LEILAO_CONSOLE.getTresMaioresLances();
        assertEquals(3, listaTresMaioresLances.size());
        assertEquals(500, listaTresMaioresLances.get(2).getValor(), delta);
        assertEquals(600, listaTresMaioresLances.get(1).getValor(), delta);
        assertEquals(700, listaTresMaioresLances.get(0).getValor(), delta);
    }


    @Test
    public void testaListaTresMaioresLances_quandoNaoRecebeLances() {
        List<Lance> listaTresMaioresLances = LEILAO_CONSOLE.getTresMaioresLances();
        assertEquals(0, listaTresMaioresLances.size());
    }


    @Test
    public void testaListaTresMaioresLances_quandoRecebeExatoUmLance() {
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Mauro"), 500));
        List<Lance> listaTresMaioresLances = LEILAO_CONSOLE.getTresMaioresLances();
        assertEquals(1, listaTresMaioresLances.size());
        assertEquals(500, listaTresMaioresLances.get(0).getValor(), delta);
    }


    @Test
    public void testaListaTresMaioresLances_quandoRecebeExatosDoisLances() {
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Fabio"), 600));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Adalberto"), 700));
        List<Lance> listaTresMaioresLances = LEILAO_CONSOLE.getTresMaioresLances();
        assertEquals(2, listaTresMaioresLances.size());
        assertEquals(600, listaTresMaioresLances.get(1).getValor(), delta);
        assertEquals(700, listaTresMaioresLances.get(0).getValor(), delta);
    }


    @Test
    public void testaListaTresMaioresLances_quandoRecebeExatosQuatroLances() {
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Mauro"), 500));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Fabio"), 600));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Mauro"), 700));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Fabio"), 800));
        List<Lance> listaTresMaioresLances = LEILAO_CONSOLE.getTresMaioresLances();
        assertEquals(3, listaTresMaioresLances.size());
        assertEquals(600, listaTresMaioresLances.get(2).getValor(), delta);
        assertEquals(700, listaTresMaioresLances.get(1).getValor(), delta);
        assertEquals(800, listaTresMaioresLances.get(0).getValor(), delta);
    }

    @Test
    public void testaListaTresMaioresLances_quandoRecebeExatosQuatroLances_eDoisMaioresLancesSaoIguais() {
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Mauro"), 500));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Fabio"), 600));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Mauro"), 700));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Fabio"), 700));
        List<Lance> listaTresMaioresLances = LEILAO_CONSOLE.getTresMaioresLances();
        assertEquals(3, listaTresMaioresLances.size());
        assertEquals(600, listaTresMaioresLances.get(2).getValor(), delta);
        assertEquals(700, listaTresMaioresLances.get(1).getValor(), delta);
        assertEquals(700, listaTresMaioresLances.get(0).getValor(), delta);
    }

    @Test
    public void naoDeve_adicionarLance_quandoMenorQueMaiorLance() {
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Fafa"), 1000));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Jojo"), 900));
        assertEquals(1, LEILAO_CONSOLE.qtdLances());
    }


    @Test
    public void naoDeve_adicionarLance_quandoMesmoUsuarioDoUltimoLance() {
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 1000));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Horacio"), 1010));
        assertEquals(1, LEILAO_CONSOLE.qtdLances());
    }

    @Test
    public void naoDeve_adicionarLance_quandoUsuarioTemCincoLances() {
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 1000));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Marisa"), 1010));
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 1020));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Marisa"), 1030));
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 1040));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Marisa"), 1050));
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 1060));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Marisa"), 1070));
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 1080));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Marisa"), 1090));
        LEILAO_CONSOLE.propoe(new Lance(HORACIO, 1100));
        LEILAO_CONSOLE.propoe(new Lance(new Usuario("Marisa"), 1110));

        assertEquals(10, LEILAO_CONSOLE.qtdLances());
    }

    @Test
    public void deve_devolverZeroParaMaiorLance_quandoNaoHouverLances() {
        double maiorLanceDevolvido = LEILAO_CONSOLE.getMaiorLance();
        assertEquals(0.0, maiorLanceDevolvido, delta);
    }

    @Test
    public void deve_devolverZeroParaMenorLance_quandoNaoHouverLances() {
        double menorLanceDevolvido = LEILAO_CONSOLE.getMenorLance();
        assertEquals(0.0, menorLanceDevolvido, delta);
    }
}