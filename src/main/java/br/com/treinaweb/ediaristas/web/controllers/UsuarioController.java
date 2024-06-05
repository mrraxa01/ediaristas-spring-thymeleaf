package br.com.treinaweb.ediaristas.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.treinaweb.ediaristas.core.models.Usuario;
import br.com.treinaweb.ediaristas.web.dtos.FlashMessage;
import br.com.treinaweb.ediaristas.web.dtos.UsuarioCadastroForm;
import br.com.treinaweb.ediaristas.web.services.WebUsuarioService;

@Controller
@RequestMapping("admin/usuarios")
public class UsuarioController {

    @Autowired
    private WebUsuarioService service;


    public Usuario findById(){
        return null;
    }


    @GetMapping
    public ModelAndView findAll(){
        return new ModelAndView("admin/usuarios/listar").addObject("usuarios", service.findAll());
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
        return new ModelAndView("admin/usuarios/cadastro-form").addObject("cadastroForm", new UsuarioCadastroForm());
    }

    @PostMapping("/cadastrar")
    public String cadastrar(
        @Valid @ModelAttribute("cadastroForm") UsuarioCadastroForm cadastroFormform,
        BindingResult result,
        RedirectAttributes attributes
    ){
        if (result.hasErrors()) 
            return "admin/usuarios/cadastro-form";
        service.cadastrar(cadastroFormform);
        attributes.addFlashAttribute("alert", new FlashMessage("alert-success", "Usuário Cadastrado com Sucesso!"));

        return "redirect:/admin/usuarios";
            
        
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attributes){
        service.excluir(id);
        attributes.addFlashAttribute("alert", new FlashMessage("alert-success", "Usuário Excluído com Sucesso!"));
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){
        return new ModelAndView("admin/usuarios/edicao-form").addObject("edicaoForm", service.buscarFormPorId(id));
    }

}
