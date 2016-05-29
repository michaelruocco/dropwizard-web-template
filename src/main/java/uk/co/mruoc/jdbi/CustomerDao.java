package uk.co.mruoc.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import uk.co.mruoc.api.Customer;

import java.util.List;

@RegisterMapper(CustomerMapper.class)
public interface CustomerDao {

    String FIELDS = "accountNumber, firstName, surname, balance";

    @SqlUpdate("insert into customer (" + FIELDS + ") values (:accountNumber, :firstName, :surname, :balance)")
    void create(@BindBean Customer customer);

    @SqlQuery("select " + FIELDS + " from customer where accountNumber = :accountNumber")
    Customer read(@Bind("accountNumber") String accountNumber);

    @SqlQuery("select " + FIELDS + " from customer")
    List<Customer> read();

    @SqlUpdate("update customer set firstName = :firstName, surname = :surname, balance = :balance where accountNumber = :accountNumber")
    void update(@BindBean Customer customer);

    @SqlUpdate("delete from customer where accountNumber = :accountNumber")
    void delete(@Bind("accountNumber") String accountNumber);

    @SqlQuery("select count(*) from customer where accountNumber = :accountNumber")
    int count(@Bind("accountNumber") String accountNumber);

    void close();

}