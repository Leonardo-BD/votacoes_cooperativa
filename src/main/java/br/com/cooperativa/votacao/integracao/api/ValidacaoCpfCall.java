package br.com.cooperativa.votacao.integracao.api;

import br.com.cooperativa.votacao.integracao.model.retorno.ValidacaoCpfRetorno;
import br.com.cooperativa.votacao.model.Associado;
import br.com.cooperativa.votacao.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ValidacaoCpfCall {

    @Value("${url.api-cpf}")
    private String urlApiCpf;

    public ValidacaoCpfRetorno validacaoCpfRetorno(Associado associado) {
        RestTemplate restTemplate = new RestTemplate();

        return GsonUtils.stringToObject(restTemplate.getForObject(urlApiCpf + associado.getCpf(), String.class), ValidacaoCpfRetorno.class);
    }
}
