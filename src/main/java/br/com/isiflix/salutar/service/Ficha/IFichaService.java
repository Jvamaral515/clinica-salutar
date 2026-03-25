package br.com.isiflix.salutar.service.Ficha;

import br.com.isiflix.salutar.model.FichaPaciente;

import java.util.List;

public interface IFichaService {

    public FichaPaciente cadastrar(FichaPaciente nova);
    public FichaPaciente alterar(FichaPaciente ficha);
    public List<FichaPaciente> buscarPorNome(String nome);
    public FichaPaciente buscarPorId(Integer id);
    public boolean excluir(Integer id);
}
