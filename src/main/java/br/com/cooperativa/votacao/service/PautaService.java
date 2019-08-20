package br.com.cooperativa.votacao.service;

import br.com.cooperativa.votacao.database.dao.PautaDao;
import br.com.cooperativa.votacao.exception.enumerator.GenericExceptionEnum;
import br.com.cooperativa.votacao.model.Pauta;
import br.com.cooperativa.votacao.service.rules.PautaRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    @Autowired
    private PautaDao pautaDao;

    public Pauta cadastrarNovaPauta(short versaoApi, Pauta pauta) {
        if (PautaRules.versaoApiValida(versaoApi)) {
            if (PautaRules.modeloPautaValido(pauta)) {
                return pautaDao.registrarPauta(pauta);
            } else{
                throw GenericExceptionEnum.MODELO_INVALIDO.getException();
            }
        } else {
            throw GenericExceptionEnum.VERSAO_API_INVALIDA.getException();
        }
    }
}
