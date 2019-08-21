package br.com.cooperativa.votacao;

import br.com.cooperativa.votacao.database.dao.ResultadoVotacaoDao;
import br.com.cooperativa.votacao.model.Pauta;
import br.com.cooperativa.votacao.model.Sessao;
import br.com.cooperativa.votacao.model.Voto;
import br.com.cooperativa.votacao.service.PautaService;
import br.com.cooperativa.votacao.service.SessaoService;
import br.com.cooperativa.votacao.service.VotoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VotacaoApplication.class)
@ActiveProfiles("test")
public class FluxoPadraoIT {

    @Autowired
    private PautaService pautaService;

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private VotoService votoService;

    @Autowired
    private ResultadoVotacaoDao resultadoVotacaoDao;

    @Test
    public void testarFluxoPadrao() throws InterruptedException {
        Pauta pauta = new Pauta();
        pauta.setTitulo("Título de pauta teste");
        pauta.setDescricao("Descrição de pauta teste");
        pauta = pautaService.cadastrarNovaPauta((short)1, pauta);

        Assert.assertNotNull(pauta.getId());
        Assert.assertNotNull(pauta.getDataInsercao());

        Sessao sessao = new Sessao();
        sessao.setIdPauta(pauta.getId());
        sessao = sessaoService.criarSessaoDeVotacao((short)1, sessao, null);

        Assert.assertNotNull(sessao.getId());
        Assert.assertFalse(sessao.getContabilizada());
        Assert.assertNotNull(sessao.getDataAbertura());
        Assert.assertNotNull(sessao.getDataEncerramento());

        for (int i = 0; i < 6; i++) {
            Voto voto = new Voto();
            voto.setIdSessao(sessao.getId());
            voto.setIdAssociado(i);
            voto.setValor(i % 2 == 0);

            try {
                voto = votoService.computarVotoNaPauta((short) 1, voto);

                Assert.assertNotNull(voto.getId());
                Assert.assertNotNull(voto.getDataInsercao());
            } catch (Exception e) {} //A api externa integrada pode impedir o usuário de votar
        }

        Thread.sleep(85000); //Margem para que a aplicação processe o resultado de uma sessão de um minuto

        Assert.assertNotNull(resultadoVotacaoDao.buscarResultadoVotacaoPorIdPauta(pauta.getId()));
    }
}
