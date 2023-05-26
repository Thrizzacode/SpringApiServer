package server.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum CmsUserAuth {
    ADMIN,TS,NORMAL;

    @JsonCreator
    public CmsUserAuth fromString(String key) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(key))
                .findFirst()
                .orElse(null);
    }
}
