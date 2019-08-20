package br.com.cooperativa.votacao.api;

import br.com.cooperativa.votacao.model.Sessao;
import br.com.cooperativa.votacao.service.SessaoService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SessaoRest {

    @Autowired
    private SessaoService sessaoService;

    private static final Logger log = LoggerFactory.getLogger(SessaoRest.class);
    private static final String MSG_EXCEPTION = "Exception :: ";

    @ResponseBody
    @PostMapping("/no-auth/v{versaoApi}/sessao")
    @ApiOperation(value = "Abre uma nova sessão de votação para uma pauta. Considere o valor do tempo de expiração em minutos. Será considerado 1 minuto se este não for informado na chamada.")
    public Sessao criarSessaoDeVotacao(@PathVariable("versaoApi") short versaoApi, @RequestBody Sessao sessao, @RequestParam(value = "tempoExpiracao", required = false) Integer tempoExpiracao) {
        try {
            return sessaoService.criarSessaoDeVotacao(versaoApi, sessao, tempoExpiracao);
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw e;
        }
    }
}
