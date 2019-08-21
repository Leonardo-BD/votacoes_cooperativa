package br.com.cooperativa.votacao.service;

import br.com.cooperativa.votacao.database.dao.PautaDao;
import br.com.cooperativa.votacao.database.dao.SessaoDao;
import br.com.cooperativa.votacao.exception.enumerator.GenericExceptionEnum;
import br.com.cooperativa.votacao.exception.enumerator.SessaoExceptionEnum;
import br.com.cooperativa.votacao.model.Sessao;
import br.com.cooperativa.votacao.service.rules.SessaoRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessaoService {

    @Autowired
    private PautaDao pautaDao;

    @Autowired
    private SessaoDao sessaoDao;

    public Sessao criarSessaoDeVotacao(short versaoApi, Sessao sessao, Integer tempoExpiracao) {
        if (SessaoRules.versaoApiValida(versaoApi)) {
            if (SessaoRules.modeloSessaoValido(sessao)) {
                if (pautaDao.buscarPautaPorId(sessao.getIdPauta()) != null) {
                    return sessaoDao.registrarSessaoDeVotacao(sessao, tempoExpiracao);
                } else {
                    throw SessaoExceptionEnum.PAUTA_INEXISTENTE.getException();
                }
            } else {
                throw GenericExceptionEnum.MODELO_INVALIDO.getException();
            }
        } else {
            throw GenericExceptionEnum.VERSAO_API_INVALIDA.getException();
        }
    }
}
