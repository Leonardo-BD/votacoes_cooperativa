package br.com.cooperativa.votacao.database.dao;

import br.com.cooperativa.votacao.database.ConnectionFactory;
import br.com.cooperativa.votacao.exception.enumerator.VotoExceptionEnum;
import br.com.cooperativa.votacao.model.Voto;
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
public class VotoDao {

    @Autowired
    private ConnectionFactory connectionFactory;

    private static final Logger log = LoggerFactory.getLogger(VotoDao.class);
    private static final String MSG_EXCEPTION = "Exception :: ";

    public Voto registrarVoto(Voto voto) {
        String sql = "INSERT INTO voto(id_sessao, id_associado, valor) VALUES (?, ?, ?)";

        try (Connection connection = connectionFactory.criarConexao(); PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, voto.getIdSessao());
            ps.setInt(2, voto.getIdAssociado());
            ps.setBoolean(3, voto.getValor());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    voto.setId(rs.getInt(rs.findColumn("id")));
                    voto.setDataInsercao(rs.getObject(rs.findColumn("data_insercao"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00")));
                    return voto;
                }
            }
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);

            if (e.getMessage().contains("ERROR: duplicate key value violates unique constraint \"voto_id_sessao_id_associado_key\"")) {
                throw VotoExceptionEnum.ASSOCIADO_JA_VOTOU.getException();
            } else {
                throw VotoExceptionEnum.ERRO_REGISTRAR_VOTO.getException();
            }
        }

        throw VotoExceptionEnum.ERRO_REGISTRAR_VOTO.getException();
    }

    public Set<Voto> buscarVotosPorSessaoDeVotacao(int idSessao) {
        String sql = "SELECT * FROM voto WHERE id_sessao = ?";
        Set<Voto> votoList = new HashSet<>();

        try (Connection connection = connectionFactory.criarConexao(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, idSessao);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    votoList.add(new Voto(
                            rs.getInt(rs.findColumn("id")),
                            rs.getInt(rs.findColumn("id_sessao")),
                            rs.getInt(rs.findColumn("id_associado")),
                            rs.getBoolean(rs.findColumn("valor")),
                            rs.getObject(rs.findColumn("data_insercao"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00"))
                    ));
                }

                if (!votoList.isEmpty()) {
                    return votoList;
                }
            }
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw VotoExceptionEnum.ERRO_BUSCAR_VOTOS.getException();
        }

        throw VotoExceptionEnum.VOTOS_NAO_ENCONTRADOS.getException();
    }
}