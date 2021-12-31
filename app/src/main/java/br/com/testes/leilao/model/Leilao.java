package br.com.testes.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {
    private final long id;
    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;
    private double menorLance = 0.0;
    private int contQtdMaxLances;

    public Leilao(String descricao) {
        this.id = 0L;
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public String getDescricao() {
        return descricao;
    }

    public void propoe(Lance lance) {
        double valorLance = lance.getValor();
        if (lanceNaoValido(lance)) return;
        lances.add(lance);
        if (defineMenorEMaiorLanceCasoPrimeiroLance(valorLance)) return;
        Collections.sort(lances);
        calculaMaiorLance(valorLance);
        calculaMenorLance(valorLance);
    }

    private boolean defineMenorEMaiorLanceCasoPrimeiroLance(double valorLance) {
        if (lances.size() == 1) {
            menorLance = valorLance;
            maiorLance = valorLance;
            return true;
        }
        return false;
    }

    private boolean lanceNaoValido(Lance lance) {
        if (lances.size() > 0) {
            if (seUsuarioForMesmoUltimoLance(lance)) return true;
            if (usuarioDeuCincoLances(lance)) return true;
        }
        if (seLanceMenorQueUltimoLance(lance)) return true;
        return false;
    }

    private boolean usuarioDeuCincoLances(Lance lance) {
        Integer qtdlancesUsuario = 0;
        for (Lance l :
                lances) {
            if (l.getUsuario().equals(lance.getUsuario())) {
                qtdlancesUsuario++;
                if (qtdlancesUsuario == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean seUsuarioForMesmoUltimoLance(Lance lance) {
        if (lance.getUsuario().equals(lances.get(0).getUsuario())) {
            return true;
        }
        return false;
    }

    private boolean seLanceMenorQueUltimoLance(Lance lance) {
        if (lance.getValor() < maiorLance) {
            return true;
        }
        return false;
    }

    private void calculaMenorLance(double valorLance) {
        if (valorLance < menorLance) {
            menorLance = valorLance;
        }
    }

    private void calculaMaiorLance(double valorLance) {
        if (valorLance > maiorLance) {
            maiorLance = valorLance;
        }
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public List<Lance> getTresMaioresLances() {
        contQtdMaxLances = lances.size();
        if (contQtdMaxLances > 3) {
            contQtdMaxLances = 3;
        }
        return lances.subList(0, contQtdMaxLances);
    }

    public int qtdLances() {
        return lances.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Leilao leilao = (Leilao) o;

        if (id != leilao.id) return false;
        return descricao != null ? descricao.equals(leilao.descricao) : leilao.descricao == null;
    }
}
