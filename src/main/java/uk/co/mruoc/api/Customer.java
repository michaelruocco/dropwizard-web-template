package uk.co.mruoc.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.FormParam;
import java.math.BigDecimal;

public class Customer {

    private String accountNumber;
    private String firstName;
    private String surname;
    private BigDecimal balance;

    public Customer() {
        // Jackson deserialization
    }

    public Customer(CustomerBuilder builder) {
        this.accountNumber = builder.accountNumber;
        this.firstName = builder.firstName;
        this.surname = builder.surname;
        this.balance = builder.balance;
    }

    @JsonProperty
    @FormParam("accountNumber")
    public String getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty
    @FormParam("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    @FormParam("surname")
    public String getSurname() {
        return surname;
    }

    @JsonProperty
    @FormParam("balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public String getFullName() {
        String fullName = firstName + " " + surname;
        return fullName.trim();
    }

    public static class CustomerBuilder {

        private String accountNumber;
        private String firstName;
        private String surname;
        private BigDecimal balance;

        public CustomerBuilder setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public CustomerBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public CustomerBuilder setBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }

    }

}
