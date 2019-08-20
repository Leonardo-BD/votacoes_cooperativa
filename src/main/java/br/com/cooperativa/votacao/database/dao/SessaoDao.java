package br.com.cooperativa.votacao.database.dao;

import br.com.cooperativa.votacao.database.ConnectionFactory;
import br.com.cooperativa.votacao.exception.enumerator.SessaoExceptionEnum;
import br.com.cooperativa.votacao.model.Sessao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

@Component
public class SessaoDao {

    @Autowired
    private ConnectionFactory connectionFactory;

    private static final Logger log = LoggerFactory.getLogger(SessaoDao.class);
    private static final String MSG_EXCEPTION = "Exception :: ";

    public Sessao registrarSessaoDeVotacao(Sessao sessao) {
        String sql = sessao.getDataEncerramento() != null ?
                "INSERT INTO sessao(id_pauta, data_encerramento) VALUES (?, ?)" : "INSERT INTO sessao(id_pauta) VALUES (?)";

        try (Connection connection = connectionFactory.criarConexao(); PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            if (sessao.getDataEncerramento() != null) {
                ps.setObject(2, sessao.getDataEncerramento());
            }

            ps.setInt(1, sessao.getIdPauta());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    sessao.setId(rs.getInt(rs.findColumn("id")));
                    sessao.setContabilizada(rs.getBoolean(rs.findColumn("contabilizada")));
                    sessao.setDataAbertura(rs.getObject(rs.findColumn("data_abertura"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00")));
                    sessao.setDataEncerramento(rs.getObject(rs.findColumn("data_encerramento"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00")));
                    return sessao;
                }
            }
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw SessaoExceptionEnum.ERRO_REGISTRAR_SESSAO.getException();
        }

        throw SessaoExceptionEnum.ERRO_REGISTRAR_SESSAO.getException();
    }

    public void declararSessaoComoContabilizada(int idSessao) {
        String sql = "UPDATE sessao SET contabilizada = true WHERE id = ?";

        try (Connection connection = connectionFactory.criarConexao(); PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, idSessao);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw SessaoExceptionEnum.ERRO_CONTABILIZAR_SESSAO.getException();
                }
            }
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw SessaoExceptionEnum.ERRO_CONTABILIZAR_SESSAO.getException();
        }
    }

    public Sessao buscarSessaoPorId(int idSessao) {
        String sql = "SELECT * FROM sessao WHERE id = ?";
        Sessao sessao;

        try (Connection connection = connectionFactory.criarConexao(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, idSessao);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sessao = new Sessao(
                            rs.getInt(rs.findColumn("id")),
                            rs.getInt(rs.findColumn("id_pauta")),
                            rs.getBoolean(rs.findColumn("contabilizada")),
                            rs.getObject(rs.findColumn("data_abertura"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00")),
                            rs.getObject(rs.findColumn("data_encerramento"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00"))
                    );
                    return sessao;
                }
            }
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw SessaoExceptionEnum.ERRO_BUSCAR_SESSAO.getException();
        }

        throw SessaoExceptionEnum.SESSAO_NAO_ENCONTRADA.getException();
    }

    public Set<Sessao> buscarSessoesEncerradas() {
        String sql = "SELECT * FROM sessao WHERE contabilizada = false AND CURRENT_TIMESTAMP > data_encerramento";
        Set<Sessao> sessaoList = new HashSet<>();

        try (Connection connection = connectionFactory.criarConexao(); PreparedStatement ps = connection.prepareStatement(sql);) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sessaoList.add(new Sessao(
                            rs.getInt(rs.findColumn("id")),
                            rs.getInt(rs.findColumn("id_pauta")),
                            rs.getBoolean(rs.findColumn("contabilizada")),
                            rs.getObject(rs.findColumn("data_abertura"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00")),
                            rs.getObject(rs.findColumn("data_encerramento"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00"))
                    ));
                }

                return sessaoList;
            }
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw SessaoExceptionEnum.ERRO_BUSCAR_SESSAO.getException();
        }
    }
}