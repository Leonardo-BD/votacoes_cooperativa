package br.com.cooperativa.votacao.api;

import br.com.cooperativa.votacao.model.Pauta;
import br.com.cooperativa.votacao.service.PautaService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PautaRest {

    @Autowired
    private PautaService pautaService;

    private static final Logger log = LoggerFactory.getLogger(PautaRest.class);
    private static final String MSG_EXCEPTION = "Exception :: ";

    @ResponseBody
    @PostMapping("/no-auth/v{versaoApi}/pauta")
    @ApiOperation(value = "Cria uma nova pauta.")
    public Pauta cadastrarNovaPauta(@PathVariable("versaoApi") short versaoApi, @RequestBody Pauta pauta) {
        try {
            return pautaService.cadastrarNovaPauta(versaoApi, pauta);
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw e;
        }
    }
}
