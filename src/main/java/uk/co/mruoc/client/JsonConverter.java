package uk.co.mruoc.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.api.ErrorMessage;

import java.io.IOException;
import java.util.List;

class JsonConverter {

    private final ObjectMapper mapper = new ObjectMapper();
    private final TypeFactory typeFactory = TypeFactory.defaultInstance();

    private final CollectionType customerListType = typeFactory.constructCollectionType(List.class, Customer.class);

    JsonConverter() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    protected String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    protected ErrorMessage toErrorMessage(String json) {
        return toType(json, ErrorMessage.class);
    }


    protected Customer toCustomer(String json) {
        return toType(json, Customer.class);
    }

    protected List<Customer> toCustomers(String json) {
        return toCollectionType(json, customerListType);
    }

    private <T> T toCollectionType(String json, CollectionType type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    private <T> T toType(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    static class JsonException extends RuntimeException {

        JsonException(Throwable cause) {
            super(cause);
        }

    }

}
