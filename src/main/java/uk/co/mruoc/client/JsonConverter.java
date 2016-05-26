package uk.co.mruoc.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import uk.co.mruoc.api.Customer;

import java.io.IOException;

public class JsonConverter {

    private final ObjectMapper mapper = new ObjectMapper();
    private final TypeFactory typeFactory = TypeFactory.defaultInstance();

    public JsonConverter() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    public Customer toCustomer(String json) {
        try {
            return mapper.readValue(json, Customer.class);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static class JsonException extends RuntimeException {

        public JsonException(Throwable cause) {
            super(cause);
        }

    }
}
