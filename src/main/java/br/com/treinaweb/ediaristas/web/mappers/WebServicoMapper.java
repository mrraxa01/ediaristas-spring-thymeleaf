package br.com.treinaweb.ediaristas.web.mappers;

import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.core.models.Servico;
import br.com.treinaweb.ediaristas.web.dtos.ServicoForm;


@Component
public class WebServicoMapper {

    
/*     WebServicoMapper INSTANCE = Mappers.getMapper(WebServicoMapper.class);
    Servico toModel(ServicoForm form);
    ServicoForm toForm(Servico model);
 */
    public Servico toModel(ServicoForm servicoForm){
        if (servicoForm == null) 
            throw new IllegalArgumentException("Objeto inválido");
    
            return new Servico(null, servicoForm.getNome(), servicoForm.getValorMinimo(),servicoForm.getQtdHoras() ,
            servicoForm.getPercentualComissao(), servicoForm.getHorasQuarto(), servicoForm.getValorQuarto(),
            servicoForm.getHorasSala(), servicoForm.getValorSala(), servicoForm.getHorasBanheiro(),servicoForm.getValorBanheiro(),
            servicoForm.getHorasCozinha(),servicoForm.getValorCozinha(),servicoForm.getHorasQuintal(), servicoForm.getValorQuintal(),
            servicoForm.getHorasOutros(), servicoForm.getValorOutros(), servicoForm.getIcone(), servicoForm.getPosicao() 
             );
        }   
    public ServicoForm toForm(Servico servico)
    {
        if (servico==null)
            throw new IllegalArgumentException("Objeto inválido");
        return new ServicoForm(servico.getNome(), servico.getValorMinimo(), servico.getQtdHoras(), servico.getPercentualComissao(), servico.getHorasQuarto(), servico.getValorQuarto(),
        servico.getHorasSala(), servico.getValorSala(), servico.getHorasBanheiro(), servico.getValorBanheiro(), servico.getHorasCozinha(),
        servico.getValorCozinha(), servico.getHorasQuintal(), servico.getValorQuintal(), servico.getHorasOutros(), servico.getValorOutros(),
        servico.getIcone(), servico.getPosicao());
    }

}
