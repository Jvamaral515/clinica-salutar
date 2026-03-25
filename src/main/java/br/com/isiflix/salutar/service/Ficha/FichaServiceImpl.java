package br.com.isiflix.salutar.service.Ficha;

import br.com.isiflix.salutar.dao.FichaPacienteDAO;
import br.com.isiflix.salutar.model.FichaPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FichaServiceImpl implements IFichaService{

    @Autowired
    private FichaPacienteDAO dao;

    @Override
    public FichaPaciente cadastrar(FichaPaciente nova) {
        nova.setUuid(UUID.randomUUID().toString());
        nova.setAtivo(1);
        return dao.save(nova);
    }

    @Override
    public FichaPaciente alterar(FichaPaciente ficha) {
        return dao.save(ficha);
    }

    @Override
    public List<FichaPaciente> buscarPorNome(String nome) {
        return dao.findByNomePacienteContaining(nome);
    }

    @Override
    public FichaPaciente buscarPorId(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public boolean excluir(Integer id) {
        FichaPaciente ficha = buscarPorId(id);
        if (ficha != null){
            ficha.setAtivo(0);
            dao.save(ficha);
            return true;
        }
        return false;
    }
}
