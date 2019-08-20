package br.com.cooperativa.votacao.database.dao;

import br.com.cooperativa.votacao.database.ConnectionFactory;
import br.com.cooperativa.votacao.exception.enumerator.ResultadoVotacaoExceptionEnum;
import br.com.cooperativa.votacao.model.ResultadoVotacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class ResultadoVotacaoDao {

    @Autowired
    private ConnectionFactory connectionFactory;

    private static final Logger log = LoggerFactory.getLogger(ResultadoVotacaoDao.class);
    private static final String MSG_EXCEPTION = "Exception :: ";

    public ResultadoVotacao registrarResultado(ResultadoVotacao resultadoVotacao) {
        String sql = "INSERT INTO resultado_votacao(id_pauta, votos_favoraveis, votos_contrarios, aprovada) VALUES (?, ?, ?, ?)";

        try (Connection connection = connectionFactory.criarConexao(); PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, resultadoVotacao.getIdPauta());
            ps.setInt(2, resultadoVotacao.getVotosFavoraveis());
            ps.setInt(3, resultadoVotacao.getVotosContrarios());
            ps.setBoolean(4, resultadoVotacao.getAprovada());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    resultadoVotacao.setId(rs.getInt(rs.findColumn("id")));
                    resultadoVotacao.setDataPublicacao(rs.getObject(rs.findColumn("data_publicacao"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00")));
                    return resultadoVotacao;
                }
            }
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw ResultadoVotacaoExceptionEnum.ERRO_REGISTRAR_RESULTADO.getException();
        }

        throw ResultadoVotacaoExceptionEnum.ERRO_REGISTRAR_RESULTADO.getException();
    }

    public ResultadoVotacao buscarResultadoVotacaoPorIdPauta(int idPauta) {
        String sql = "SELECT * FROM resultado_votacao WHERE id_pauta = ?";
        ResultadoVotacao resultadoVotacao;

        try (Connection connection = connectionFactory.criarConexao(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, idPauta);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    resultadoVotacao = new ResultadoVotacao(
                            rs.getInt(rs.findColumn("id")),
                            rs.getInt(rs.findColumn("id_pauta")),
                            rs.getInt(rs.findColumn("votos_favoraveis")),
                            rs.getInt(rs.findColumn("votos_contrarios")),
                            rs.getBoolean(rs.findColumn("aprovada")),
                            rs.getObject(rs.findColumn("data_publicacao"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00"))
                    );
                    return resultadoVotacao;
                }
            }
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw ResultadoVotacaoExceptionEnum.ERRO_BUSCAR_RESULTADO.getException();
        }

        throw ResultadoVotacaoExceptionEnum.RESULTADO_NAO_ENCONTRADO.getException();
    }
}
