package br.com.treinaweb.ediaristas.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.ediaristas.core.models.Usuario;
import br.com.treinaweb.ediaristas.web.dtos.UsuarioCadastroForm;

@Mapper(componentModel = "spring")
public interface WebUsuarioMapper {
    WebUsuarioMapper INSTANCE = Mappers.getMapper(WebUsuarioMapper.class);
     @Mappings({
        @Mapping(target = "id", ignore = true), // Ignora o campo 'id'
        @Mapping(target = "tipoUsuario", ignore = true) // Ignora o campo 'tipoUsuario'
    })
    Usuario toModel(UsuarioCadastroForm form);
}
