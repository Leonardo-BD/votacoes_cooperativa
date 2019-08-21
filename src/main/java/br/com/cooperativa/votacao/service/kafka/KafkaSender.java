package br.com.cooperativa.votacao.service.kafka;

import br.com.cooperativa.votacao.model.ResultadoVotacao;
import br.com.cooperativa.votacao.utils.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaSender.class);
	private static final String MSG_EXCEPTION = "Exception :: ";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${kafka.topic.resultado-votacao}")
	private String resultadoVoracaoSenderTopic;

	public void enviarResultadoVotacao(ResultadoVotacao resultadoVotacao) {
		try {
			kafkaTemplate.send(resultadoVoracaoSenderTopic, GsonUtils.objectToString(resultadoVotacao));
		} catch (Exception e) {
			log.error("Resultados não enviados ao kafka.");
			log.error(MSG_EXCEPTION, e);
		}
	}
}
