package server.api.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.api.model.CmsUserAuth;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;

public class CmsUserAuthListConverter implements AttributeConverter<List<CmsUserAuth>, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<CmsUserAuth> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CmsUserAuth> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<CmsUserAuth>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
