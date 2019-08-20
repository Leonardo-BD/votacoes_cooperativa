package br.com.cooperativa.votacao.service.threads;

import br.com.cooperativa.votacao.database.dao.ResultadoVotacaoDao;
import br.com.cooperativa.votacao.database.dao.SessaoDao;
import br.com.cooperativa.votacao.database.dao.VotoDao;
import br.com.cooperativa.votacao.model.ResultadoVotacao;
import br.com.cooperativa.votacao.model.Sessao;
import br.com.cooperativa.votacao.model.Voto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ResultadoVotacaoThread {

    @Autowired
    private ResultadoVotacaoDao resultadoVotacaoDao;

    @Autowired
    private SessaoDao sessaoDao;

    @Autowired
    private VotoDao votoDao;

    private final Logger log = LoggerFactory.getLogger(ResultadoVotacaoThread.class);
    private static final String MSG_EXCEPTION = "Exception::";

    //TODO Estes resultados serão enviados para o Kafka.
    @Scheduled(initialDelayString = "${agendamento.initial-delay}", fixedRateString = "${agendamento.time-rate}")
    public void gerarResultadosDeVotacoes() {
        Set<Sessao> sessaoList = sessaoDao.buscarSessoesEncerradas();

        for (Sessao sessao : sessaoList) {
            Set<Voto> votoList = votoDao.buscarVotosPorSessaoDeVotacao(sessao.getId());

            int votosFavoraveis = 0;
            int votosContrarios = 0;

            for (Voto voto : votoList) {
                if (voto.getValor()) {
                    votosFavoraveis += 1;
                } else {
                    votosContrarios += 1;
                }
            }

            //Aprovada somente se os votos favoráveis superarem os contrários. Não havendo votos, é reprovada.
            ResultadoVotacao resultadoVotacao = new ResultadoVotacao(
                    sessao.getIdPauta(),
                    votosFavoraveis,
                    votosContrarios,
                    votosFavoraveis > votosContrarios
            );

            resultadoVotacaoDao.registrarResultado(resultadoVotacao);
            sessaoDao.declararSessaoComoContabilizada(sessao.getId());

            log.info("Resultado gerado para a sessão de votação " + sessao.getId() + " da pauta " + sessao.getIdPauta());
        }
    }
}
