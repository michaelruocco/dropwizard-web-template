package uk.co.mruoc;


import io.cucumber.datatable.DataTable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomerConverter {

    public List<Customer> toCustomers(DataTable table) {
        List<Customer> customers = new ArrayList<>();
        List<List<String>> rows = table.asLists();
        for (int r = 1; r < rows.size(); r++) {
            List<String> row = rows.get(r);
            customers.add(toCustomer(row));
        }
        return customers;
    }

    public Customer toCustomer(List<String> row) {
        return new Customer.CustomerBuilder()
                .setAccountNumber(row.get(0))
                .setFirstName(row.get(1))
                .setSurname(row.get(2))
                .setBalance(new BigDecimal(row.get(3)))
                .build();
    }

}
