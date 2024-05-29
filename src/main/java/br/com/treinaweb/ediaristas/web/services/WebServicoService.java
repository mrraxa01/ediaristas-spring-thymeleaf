package br.com.treinaweb.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.exceptions.NotFoundObjectException;
import br.com.treinaweb.ediaristas.core.models.Servico;
import br.com.treinaweb.ediaristas.core.repositories.ServicoRepository;
import br.com.treinaweb.ediaristas.web.dtos.ServicoForm;
import br.com.treinaweb.ediaristas.web.mappers.WebServicoMapper;

@Service
public class WebServicoService {

    @Autowired
    private ServicoRepository repository;

    @Autowired
    private WebServicoMapper mapper;

    public List<Servico> buscarTodos (){
        return repository.findAll();
    }

    public Servico cadastrar(ServicoForm form){
        return repository.save(mapper.toModel(form));
    }

    public Servico buscarPorId(Long id){
        var servicoEncontrado = repository.findById(id);
        if (servicoEncontrado.isEmpty()) 
            throw new NotFoundObjectException(String.format("Servico com %id Não Encontrado!", id));
        return servicoEncontrado.get();
    }

    public Servico editar(ServicoForm form, Long id){

     var model = mapper.toModel(form);
     model.setId(buscarPorId(id).getId());
     return repository.save(model);
    }

    public void excluir(Long id){
        repository.delete(buscarPorId(id));
    }
}
