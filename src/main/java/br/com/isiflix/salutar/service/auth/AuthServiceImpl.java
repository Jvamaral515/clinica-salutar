package br.com.isiflix.salutar.service.auth;

import br.com.isiflix.salutar.dao.UsuarioDAO;
import br.com.isiflix.salutar.model.Usuario;
import br.com.isiflix.salutar.security.SalutarToken;
import br.com.isiflix.salutar.security.TokenUtil;
import org.h2.command.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements IAuthService{

    private final UsuarioDAO dao;
    private final TokenUtil tokenUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public AuthServiceImpl(UsuarioDAO dao, TokenUtil tokenUtil){
        this.dao = dao;
        this.tokenUtil = tokenUtil;
    }

    @Override
    public Usuario criarUsuario(Usuario novo) {
        novo.setSenha(encoder.encode(novo.getSenha()));
        return dao.save(novo);
    }

    @Override
    public SalutarToken fazerLogin(Usuario dadosLogin) {
        Usuario res = dao.findByLogin(dadosLogin.getLogin());
        if (res != null && encoder.matches(dadosLogin.getSenha(), res.getSenha())){
                return tokenUtil.encode(res);
        }
        return null;
    }
}
