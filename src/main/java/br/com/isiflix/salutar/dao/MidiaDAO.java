package br.com.isiflix.salutar.dao;

import br.com.isiflix.salutar.model.Midia;
import org.springframework.data.repository.CrudRepository;

public interface MidiaDAO extends CrudRepository<Midia, Integer> {
}
