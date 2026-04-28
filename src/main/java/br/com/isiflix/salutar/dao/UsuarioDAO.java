package br.com.isiflix.salutar.dao;

import br.com.isiflix.salutar.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {

    public Usuario findByLogin(String login);

}
