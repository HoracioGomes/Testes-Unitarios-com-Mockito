package br.com.testes.leilao.ui;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.testes.leilao.database.dao.UsuarioDAO;
import br.com.testes.leilao.model.Usuario;
import br.com.testes.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeUsuarioTest {

    @Mock
    private UsuarioDAO dao;
    @Mock
    private ListaUsuarioAdapter adapter;
    @Mock
    private RecyclerView recyclerView;

    @Test
    public void deve_AtualizarListaDeUsuario_QuandoSalvarUsuario() {
        AtualizadorDeUsuario atualizador = new AtualizadorDeUsuario(
                dao,
                adapter,
                recyclerView
        );
        Usuario joao = new Usuario("João");
        when(dao.salva(joao)).thenReturn(new Usuario(1, "João"));
        when(adapter.getItemCount()).thenReturn(1);

        atualizador.salva(joao);

        verify(dao).salva(new Usuario("João"));
        verify(adapter).adiciona(new Usuario(1, "João"));
        verify(recyclerView).smoothScrollToPosition(0);
    }


}