package uk.co.mruoc;

import cucumber.api.DataTable;
import gherkin.formatter.model.DataTableRow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomerConverter {

    public List<Customer> toCustomers(DataTable table) {
        List<Customer> customers = new ArrayList<>();
        List<DataTableRow> rows = table.getGherkinRows();
        for (int r = 1; r < rows.size(); r++) {
            DataTableRow row = rows.get(r);
            customers.add(toCustomer(row));
        }
        return customers;
    }

    public Customer toCustomer(DataTableRow row) {
        List<String> cells = row.getCells();
        return new Customer.CustomerBuilder()
                .setAccountNumber(cells.get(0))
                .setFirstName(cells.get(1))
                .setSurname(cells.get(2))
                .setBalance(new BigDecimal(cells.get(3)))
                .build();
    }

}
