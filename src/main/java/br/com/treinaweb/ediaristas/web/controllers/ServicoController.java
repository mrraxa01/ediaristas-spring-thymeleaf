package br.com.treinaweb.ediaristas.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.ediaristas.core.enums.Icone;
import br.com.treinaweb.ediaristas.core.models.Servico;

@Controller
@RequestMapping("/admin/servicos")
public class ServicoController {

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){

        return new ModelAndView("admin/servico/form")
        .addObject("servico", new Servico());
      
    }

    @ModelAttribute("icones")
    public Icone[] getIcones(){
        return Icone.values();
    }

}
