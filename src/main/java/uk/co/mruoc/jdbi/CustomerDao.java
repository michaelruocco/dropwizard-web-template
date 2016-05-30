package uk.co.mruoc.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import uk.co.mruoc.api.Customer;

import java.util.List;

@RegisterMapper(CustomerMapper.class)
public abstract class CustomerDao {

    private static final String FIELDS = "accountNumber, firstName, surname, balance";

    @SqlUpdate("insert into customer (" + FIELDS + ") values (:accountNumber, :firstName, :surname, :balance)")
    public abstract void create(@BindBean Customer customer);

    @SqlQuery("select " + FIELDS + " from customer where accountNumber = :accountNumber")
    public abstract Customer read(@Bind("accountNumber") String accountNumber);

    @SqlQuery("select " + FIELDS + " from customer")
    public abstract List<Customer> read();

    @SqlUpdate("update customer set firstName = :firstName, surname = :surname, balance = :balance where accountNumber = :accountNumber")
    public abstract void update(@BindBean Customer customer);

    @SqlUpdate("delete from customer where accountNumber = :accountNumber")
    public abstract void delete(@Bind("accountNumber") String accountNumber);

    @SqlQuery("select count(*) from customer where accountNumber = :accountNumber")
    abstract int count(@Bind("accountNumber") String accountNumber);

    public boolean exists(String accountNumber) {
        return count(accountNumber) > 0;
    }

}