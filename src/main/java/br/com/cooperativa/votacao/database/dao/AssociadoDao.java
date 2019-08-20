package br.com.cooperativa.votacao.database.dao;

import br.com.cooperativa.votacao.database.ConnectionFactory;
import br.com.cooperativa.votacao.exception.enumerator.AssociadoExceptionEnum;
import br.com.cooperativa.votacao.model.Associado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class AssociadoDao {

    @Autowired
    private ConnectionFactory connectionFactory;

    private static final Logger log = LoggerFactory.getLogger(AssociadoDao.class);
    private static final String MSG_EXCEPTION = "Exception :: ";

    public Associado registrarAssociado(Associado associado) {
        String sql = "INSERT INTO associado(cpf) VALUES (?)";

        try (Connection connection = connectionFactory.criarConexao(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, associado.getCpf());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    associado.setId(rs.getInt(rs.findColumn("id")));
                    associado.setDataInsercao(rs.getObject(rs.findColumn("data_insercao"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00")));
                    return associado;
                }
            }
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw AssociadoExceptionEnum.ERRO_REGISTRAR_ASSOCIADO.getException();
        }

        throw AssociadoExceptionEnum.ERRO_REGISTRAR_ASSOCIADO.getException();
    }

    public Associado buscarAssociadoPorId(int idAssociado) {
        String sql = "SELECT * FROM associado WHERE id = ?";
        Associado associado = null;

        try (Connection connection = connectionFactory.criarConexao(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, idAssociado);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    associado = new Associado(
                            rs.getInt(rs.findColumn("id")),
                            rs.getString(rs.findColumn("cpf")),
                            rs.getObject(rs.findColumn("data_insercao"), OffsetDateTime.class).withOffsetSameInstant(ZoneOffset.of("-03:00"))
                    );

                    return associado;
                }
            }
        } catch (Exception e) {
            log.error(MSG_EXCEPTION, e);
            throw AssociadoExceptionEnum.ERRO_BUSCAR_ASSOCIADO.getException();
        }

        throw AssociadoExceptionEnum.ASSOCIADO_NAO_ENCONTRADO.getException();
    }
}
