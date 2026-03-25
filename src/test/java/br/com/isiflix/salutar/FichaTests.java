package br.com.isiflix.salutar;

import br.com.isiflix.salutar.model.FichaPaciente;
import br.com.isiflix.salutar.service.Ficha.IFichaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class FichaTests {

    @Autowired
    IFichaService service;

    @Test
    public void shouldCreateFicha(){
        FichaPaciente f = new FichaPaciente();
        f.setNomePaciente("Joao Victor");
        FichaPaciente res = service.cadastrar(f);
        assertTrue(res != null && res.getUuid() != null && res.getAtivo() == 1);
    }

    @Test
    public void shouldDeleteFicha(){
        assertTrue(service.excluir(10));
    }

    @Test
    public void shouldNotDeleteFicha(){
        assertFalse(service.excluir(85939489));
    }

    @Test
    public void shouldReturnSeveralFichas(){
        List<FichaPaciente> lista = service.buscarPorNome("a");
        assertTrue(lista.size() > 0);
    }

    @Test
    public void shouldNotFindFicha(){
        List<FichaPaciente> lista = service.buscarPorNome("Adamastor");
        assertTrue(lista.size() == 0);
    }
}
