package com.bankdev.cadastro_pessoa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@Service
public class TransferenciaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public String realizarTransferencia(Integer idOrigem, Integer idDestino, BigDecimal valor) {
        return jdbcTemplate.execute((Connection connection) -> {
            try (CallableStatement callableStatement = connection.prepareCall("{ ? = CALL realizar_transferencia(?, ?, ?) }")) {
                callableStatement.registerOutParameter(1, Types.VARCHAR);
                callableStatement.setInt(2, idOrigem);
                callableStatement.setInt(3, idDestino);
                callableStatement.setBigDecimal(4, valor);

                callableStatement.execute();
                return callableStatement.getString(1);
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao realizar a transferência", e);
            }
        });
    }
}
