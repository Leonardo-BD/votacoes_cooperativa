package br.com.cooperativa.votacao.database.dao;

import br.com.cooperativa.votacao.database.ConnectionFactory;
import br.com.cooperativa.votacao.exception.enumerator.PautaExceptionEnum;
import br.com.cooperativa.votacao.model.Pauta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class PautaDao {

    @Autowired
    private ConnectionFactory connectionFactory;

    private static final Logger log = LoggerFactory.getLogger(PautaDao.class);
    private static final String MSG_EXCEPTION = "Exception :: ";

    public Pauta registrarPauta(Pauta pauta) {
        String sql = "INSERT INTO pauta(titulo, descricao) VALUES (?, ?)";

        try (Connection connection = connectionFactory.criarConexao(); PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, pauta.getTitulo());
            ps.setString(2, pauta.getDescricao());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pauta.setId(rs.getInt(rs.findColumn("id")));
                    pauta.setDataInsercao(rs.getObject(rs.findColumn("data_insercao"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00")));
                    return pauta;
                }
            }
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw PautaExceptionEnum.ERRO_REGISTRAR_PAUTA.getException();
        }

        throw PautaExceptionEnum.ERRO_REGISTRAR_PAUTA.getException();
    }

    public Pauta buscarPautaPorId(int idPauta) {
        String sql = "SELECT * FROM pauta WHERE id = ?";
        Pauta pauta;

        try (Connection connection = connectionFactory.criarConexao(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, idPauta);

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pauta = new Pauta(
                            rs.getInt(rs.findColumn("id")),
                            rs.getString(rs.findColumn("titulo")),
                            rs.getString(rs.findColumn("descricao")),
                            rs.getObject(rs.findColumn("data_insercao"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00"))
                    );
                    return pauta;
                }
            }
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw PautaExceptionEnum.ERRO_BUSCAR_PAUTA.getException();
        }

        throw PautaExceptionEnum.PAUTA_NAO_ENCONTRADA.getException();
    }
}