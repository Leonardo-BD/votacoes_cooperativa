package br.com.cooperativa.votacao.service;

import br.com.cooperativa.votacao.database.dao.AssociadoDao;
import br.com.cooperativa.votacao.database.dao.PautaDao;
import br.com.cooperativa.votacao.database.dao.SessaoDao;
import br.com.cooperativa.votacao.database.dao.VotoDao;
import br.com.cooperativa.votacao.exception.enumerator.GenericExceptionEnum;
import br.com.cooperativa.votacao.exception.enumerator.VotoExceptionEnum;
import br.com.cooperativa.votacao.integracao.api.ValidacaoCpfCall;
import br.com.cooperativa.votacao.model.Associado;
import br.com.cooperativa.votacao.model.Sessao;
import br.com.cooperativa.votacao.model.Voto;
import br.com.cooperativa.votacao.integracao.model.retorno.ValidacaoCpfRetorno;
import br.com.cooperativa.votacao.service.rules.SessaoRules;
import br.com.cooperativa.votacao.service.rules.VotoRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    @Autowired
    private AssociadoDao associadoDao;

    @Autowired
    private PautaDao pautaDao;

    @Autowired
    private SessaoDao sessaoDao;

    @Autowired
    private VotoDao votoDao;

    @Autowired
    private ValidacaoCpfCall validacaoCpfCall;

    private static final Logger log = LoggerFactory.getLogger(VotoService.class);
    private static final String MSG_EXCEPTION = "Exception :: ";

    public Voto computarVotoNaPauta(short versaoApi, Voto voto) {
        if (VotoRules.versaoApiValida(versaoApi)) {
            if (VotoRules.modeloVotoValido(voto)) {
                try {
                    Sessao sessao = sessaoDao.buscarSessaoPorId(voto.getIdSessao());

                    if (!SessaoRules.sessaoAberta(sessao)) {
                        throw VotoExceptionEnum.SESSAO_FECHADA.getException();
                    }

                    Associado associado = associadoDao.buscarAssociadoPorId(voto.getIdAssociado());
                    ValidacaoCpfRetorno validacaoCpfRetorno = validacaoCpfCall.validacaoCpfRetorno(associado);

                    if (VotoRules.cpfAutorizado(validacaoCpfRetorno)) {
                        return votoDao.registrarVoto(voto);
                    } else {
                        throw VotoExceptionEnum.CPF_NAO_AUTORIZADO.getException();
                    }
                } catch (Exception e) {
                    log.error(MSG_EXCEPTION, e);
                    throw e;
                }
            } else {
                throw GenericExceptionEnum.MODELO_INVALIDO.getException();
            }
        } else {
            throw GenericExceptionEnum.VERSAO_API_INVALIDA.getException();
        }
    }
}
