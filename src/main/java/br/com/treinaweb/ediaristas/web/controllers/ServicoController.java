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

import br.com.treinaweb.ediaristas.core.enums.Icone;
import br.com.treinaweb.ediaristas.web.dtos.FlashMessage;
import br.com.treinaweb.ediaristas.web.dtos.ServicoForm;
import br.com.treinaweb.ediaristas.web.mappers.WebServicoMapper;
import br.com.treinaweb.ediaristas.web.services.WebServicoService;

@Controller
@RequestMapping("/admin/servicos")
public class ServicoController {

    @Autowired
    private WebServicoService service;

    @Autowired
    private WebServicoMapper mapper;

    @GetMapping
    public ModelAndView buscarTodos(){
        return new ModelAndView("admin/servico/listar")
        .addObject("servicos", service.buscarTodos());
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){

        return new ModelAndView("admin/servico/form")
        .addObject("form", new ServicoForm());
      
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("form") ServicoForm form, BindingResult resultValidation, RedirectAttributes attributes){
        if(resultValidation.hasErrors())
            return "admin/servico/form";
        service.cadastrar(form);
        attributes.addFlashAttribute("alert", new FlashMessage("alert-success","Serviço cadastrado com sucesso!"));
        return "redirect:/admin/servicos";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){


        return new ModelAndView("/admin/servico/form")
        .addObject("form", service.buscarPorId(id));
       
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id, @Valid @ModelAttribute("form") ServicoForm form,
     BindingResult resultValidation, RedirectAttributes attributes){
        
        if (resultValidation.hasErrors()) 
            return "admin/servico/form";
        
        var servico = mapper.toModel(form);
        servico.setId(id);
        service.editar(form, id);
        attributes.addFlashAttribute("alert", new FlashMessage("alert-success","Serviço editado com sucesso!"));
        return "redirect:/admin/servicos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attributes){
        service.excluir(id);
        attributes.addFlashAttribute("alert", new FlashMessage("alert-success","Serviço excluído com sucesso!"));
        return "redirect:/admin/servicos";
    }

    @ModelAttribute("icones")
    public Icone[] getIcones(){
        return Icone.values();
    }

}
