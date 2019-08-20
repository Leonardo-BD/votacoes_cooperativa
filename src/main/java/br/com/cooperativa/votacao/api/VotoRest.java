package br.com.cooperativa.votacao.api;

import br.com.cooperativa.votacao.model.Voto;
import br.com.cooperativa.votacao.service.VotoService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class VotoRest {

    @Autowired
    private VotoService votoService;

    private static final Logger log = LoggerFactory.getLogger(VotoRest.class);
    private static final String MSG_EXCEPTION = "Exception :: ";

    @ResponseBody
    @PostMapping("/no-auth/v{versaoApi}/voto")
    @ApiOperation(value = "Computa o voto de um associado em uma sessão de votação vinculada a uma pauta.")
    public Voto computarVotoNaPauta(@PathVariable("versaoApi") short versaoApi, @RequestBody Voto voto) {
        try {
            return votoService.computarVotoNaPauta(versaoApi, voto);
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw e;
        }
    }
}
