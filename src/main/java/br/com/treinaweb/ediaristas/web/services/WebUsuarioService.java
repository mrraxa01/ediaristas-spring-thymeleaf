package br.com.treinaweb.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.core.enums.TipoUsuario;
import br.com.treinaweb.ediaristas.core.exceptions.NotFoundObjectException;
import br.com.treinaweb.ediaristas.core.exceptions.PasswordValidationException;
import br.com.treinaweb.ediaristas.core.exceptions.UsuarioJaCadastradoException;
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

        if(!form.getSenha().equals(form.getConfirmacaoSenha()))
            throw new PasswordValidationException("Validation Password Error - No Match",
             new FieldError(form.getClass().getName(),
              "confirmacaoSenha", 
              form.getConfirmacaoSenha(),
               false,
                null, 
                null, 
                "As senhas devem ser iguais"));
       
       var model = mapper.toModel(form);
       model.setTipoUsuario(TipoUsuario.ADMIN);
       validarCamposUnicos(model);
       return repository.save(model);
    }

    public void excluir(Long id){
       
        repository.delete(findById(id));
    }

    public UsuarioEdicaoForm buscarFormPorId(Long id){
             return mapper.toForm(findById(id));
    }

    public Usuario editar(UsuarioEdicaoForm form, Long id){

        var usuario = findById(id);
        System.out.println(usuario.getNomeCompleto());
        var model = mapper.toModel(form);
        System.out.println(usuario.getNomeCompleto());
        System.out.println(model.getNomeCompleto());
        model.setId(id);
        model.setSenha(usuario.getSenha());
        model.setTipoUsuario(usuario.getTipoUsuario());
        validarCamposUnicos(model);
        return repository.save(model);
    }

    private void validarCamposUnicos(Usuario usuario){
        System.out.println(usuario.getNomeCompleto());
        System.out.println(repository.findByEmail(usuario.getEmail()).get().getEmail());
        repository.findByEmail(usuario.getEmail()).ifPresent(
            (user) -> {
                System.out.println(user.getNomeCompleto());
                
                throw new UsuarioJaCadastradoException("Validation User Fail",
                new FieldError(usuario.getClass().getName(),
                 "email", 
                 usuario.getEmail(),
                  false,
                   null, 
                   null, 
                   "Usuário Já existe"));
            }
        );
    }

}
