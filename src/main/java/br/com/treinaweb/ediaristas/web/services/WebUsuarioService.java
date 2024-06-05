package br.com.treinaweb.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.enums.TipoUsuario;
import br.com.treinaweb.ediaristas.core.exceptions.NotFoundObjectException;
import br.com.treinaweb.ediaristas.core.models.Usuario;
import br.com.treinaweb.ediaristas.core.repositories.UsuarioRepository;
import br.com.treinaweb.ediaristas.web.dtos.UsuarioCadastroForm;
import br.com.treinaweb.ediaristas.web.dtos.UsuarioEdicaoForm;
import br.com.treinaweb.ediaristas.web.mappers.WebUsuarioMapper;

@Service
public class WebUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private WebUsuarioMapper mapper;


    public Usuario findById(Long id){
        return repository.findById(id).orElseThrow(
            ()-> new NotFoundObjectException("Usuário não encontrado!")  );
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario cadastrar(UsuarioCadastroForm form){
       var model = mapper.toModel(form);
       model.setTipoUsuario(TipoUsuario.ADMIN);

        return repository.save(model);
    }

    public void excluir(Long id){
       
        repository.delete(findById(id));
    }

    public UsuarioEdicaoForm buscarFormPorId(Long id){
             return mapper.toForm(findById(id));
    }

}
