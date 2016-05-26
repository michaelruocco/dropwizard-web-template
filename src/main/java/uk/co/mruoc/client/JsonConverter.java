package uk.co.mruoc.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import uk.co.mruoc.api.Customer;

import java.io.IOException;

class JsonConverter {

    private final ObjectMapper mapper = new ObjectMapper();
    private final TypeFactory typeFactory = TypeFactory.defaultInstance();

    JsonConverter() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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

    private static class JsonException extends RuntimeException {

        JsonException(Throwable cause) {
            super(cause);
        }

    }
}
