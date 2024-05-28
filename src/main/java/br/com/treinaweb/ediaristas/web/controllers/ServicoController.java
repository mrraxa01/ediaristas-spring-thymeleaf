package br.com.treinaweb.ediaristas.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.ediaristas.core.enums.Icone;
import br.com.treinaweb.ediaristas.core.repositories.ServicoRepository;
import br.com.treinaweb.ediaristas.web.dtos.ServicoForm;
import br.com.treinaweb.ediaristas.web.mappers.WebServicoMapper;

@Controller
@RequestMapping("/admin/servicos")
public class ServicoController {


    @Autowired
    private ServicoRepository repository;

    @Autowired
    private WebServicoMapper mapper;

    @GetMapping
    public ModelAndView buscarTodos(){
        return new ModelAndView("admin/servico/listar")
        .addObject("servicos", repository.findAll());
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){

        return new ModelAndView("admin/servico/form")
        .addObject("form", new ServicoForm());
      
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid ServicoForm form){
        repository.save(mapper.toModel(form));
        return "redirect:/admin/servicos";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){
    var servico = repository.getReferenceById(id);
    var form = mapper.toForm(servico);
    System.out.println(form);
        return new ModelAndView("/admin/servico/form")
        .addObject("form", mapper.toForm(repository.getReferenceById(id)));
       
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id,@Valid ServicoForm form){
        var servico = mapper.toModel(form);
        servico.setId(id);
        repository.save(servico);
        return "redirect:/admin/servicos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){
        repository.deleteById(id);
        return "redirect:/admin/servicos";
    }

    @ModelAttribute("icones")
    public Icone[] getIcones(){
        return Icone.values();
    }

}
