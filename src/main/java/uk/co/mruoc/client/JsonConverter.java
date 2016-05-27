package uk.co.mruoc.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import uk.co.mruoc.api.Customer;

import java.io.IOException;
import java.util.List;

class JsonConverter {

    private final ObjectMapper mapper = new ObjectMapper();
    private final TypeFactory typeFactory = TypeFactory.defaultInstance();
    private final CollectionType customerListType = typeFactory.constructCollectionType(List.class, Customer.class);

    JsonConverter() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    Customer toCustomer(String json) {
        try {
            return mapper.readValue(json, Customer.class);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    List<Customer> toCustomers(String json) {
        try {
            return mapper.readValue(json, customerListType);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    private static class JsonException extends RuntimeException {

        JsonException(Throwable cause) {
            super(cause);
        }

    }

}
