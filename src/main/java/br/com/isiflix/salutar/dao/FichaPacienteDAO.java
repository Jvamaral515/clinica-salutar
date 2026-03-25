package br.com.isiflix.salutar.dao;

import br.com.isiflix.salutar.model.FichaPaciente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FichaPacienteDAO extends CrudRepository<FichaPaciente, Integer> {

    public List<FichaPaciente> findByNomePacienteContaining(String palavraChave);
}
