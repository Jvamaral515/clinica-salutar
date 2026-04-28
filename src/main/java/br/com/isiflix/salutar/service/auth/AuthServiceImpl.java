package br.com.isiflix.salutar.service.auth;

import br.com.isiflix.salutar.dao.UsuarioDAO;
import br.com.isiflix.salutar.model.Usuario;
import br.com.isiflix.salutar.security.SalutarToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements IAuthService{

    @Autowired
    private UsuarioDAO dao;

    @Override
    public Usuario criarUsuario(Usuario novo) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String novaSenha = encoder.encode(novo.getSenha());
        novo.setSenha(novaSenha);
        return dao.save(novo);
    }

    @Override
    public SalutarToken fazerLogin(Usuario dadosLogin) {
        return null;
    }
}
