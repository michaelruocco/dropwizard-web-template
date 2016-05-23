package uk.co.mruoc.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import uk.co.mruoc.api.Customer;

@RegisterMapper(CustomerMapper.class)
public interface CustomerDao {

    String FIELDS = "accountNumber, firstName, surname, balance";

    @SqlUpdate("insert into customer (" + FIELDS + ") values (:accountNumber, :firstName, :surname, :balance)")
    void insert(@BindBean Customer customer);

    @SqlQuery("select " + FIELDS + " from customer where accountNumber = :accountNumber")
    Customer getCustomer(@Bind("accountNumber") String accountNumber);

    void close();

}