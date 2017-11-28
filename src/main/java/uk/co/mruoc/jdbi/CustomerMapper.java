package uk.co.mruoc.jdbi;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import uk.co.mruoc.Customer;
import uk.co.mruoc.Customer.CustomerBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements ResultSetMapper<Customer> {

    public Customer map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
        return new CustomerBuilder()
                .setAccountNumber(rs.getString("accountNumber"))
                .setFirstName(rs.getString("firstName"))
                .setSurname(rs.getString("surname"))
                .setBalance(rs.getBigDecimal("balance"))
                .build();
    }

}