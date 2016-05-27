package uk.co.mruoc.facade;

import uk.co.mruoc.api.Customer;
import uk.co.mruoc.service.CreateCustomerService;
import uk.co.mruoc.service.ReadCustomerService;
import uk.co.mruoc.service.UpdateCustomerService;

import java.util.List;

public class CustomerFacade {

    private final ReadCustomerService readService;
    private final CreateCustomerService createService;
    private final UpdateCustomerService updateService;

    private CustomerFacade(CustomerFacadeBuilder builder) {
        this.readService = builder.readService;
        this.createService = builder.createService;
        this.updateService = builder.updateService;
    }

    public Customer read(String accountNumber) {
        return readService.read(accountNumber);
    }

    public List<Customer> read() { return readService.read(); }

    public void create(Customer customer) {
        createService.create(customer);
    }

    public void update(Customer customer) {
        updateService.update(customer);
    }

    public static class CustomerFacadeBuilder {

        private ReadCustomerService readService;
        private CreateCustomerService createService;
        private UpdateCustomerService updateService;

        public CustomerFacadeBuilder setReadService(ReadCustomerService readService) {
            this.readService = readService;
            return this;
        }

        public CustomerFacadeBuilder setCreateService(CreateCustomerService createService) {
            this.createService = createService;
            return this;
        }

        public CustomerFacadeBuilder setUpdateService(UpdateCustomerService updateService) {
            this.updateService = updateService;
            return this;
        }

        public CustomerFacade build() {
            return new CustomerFacade(this);
        }

    }

}
