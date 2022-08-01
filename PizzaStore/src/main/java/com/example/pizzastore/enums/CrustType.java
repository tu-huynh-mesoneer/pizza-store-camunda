package com.example.pizzastore.enums;


import com.example.pizzastore.enums.base.CodeEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum CrustType implements CodeEnum {

    thin("1", "thin", 1),
    thick("2", "thick", 2),
    cripsy ("3", "cripsy", 3);

    private final String value;
    private final String display;
    private final int sortOrder;

    /**
     * Instantiates a new enum demo.
     *
     * @param value     the value
     * @param display   the display
     * @param sortOrder the sort order
     */
    private CrustType(String value, String display, int sortOrder) {
        this.value = value;
        this.display = display;
        this.sortOrder = sortOrder;
    }

    /**
     * Gets the.
     *
     * @param value the value
     * @return the enum demo
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static CrustType of(String value) {
        if(StringUtils.isNumeric(value)) {
            return ObjectUtils.isEmpty(value) ? null : Stream.of(CrustType.values()).filter(e -> value.equals(e.getValue())).findFirst().orElse(null);
        } else {
            return ObjectUtils.isEmpty(value) ? null : Stream.of(CrustType.values()).filter(e -> value.equals(e.getDisplay())).findFirst().orElse(null);
        }
    }

    /**
     * Gets the.
     *
     * @param value the value
     * @return the enum demo
     */
    public static CrustType ofObject(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        if (value instanceof Map) {
            Map<String, String> mapEnum = (Map<String, String>) value;
            return ObjectUtils.isEmpty(value) ? null : Stream.of(CrustType.values()).filter(e -> e.getValue().equals(mapEnum.get("value"))).findFirst().orElseThrow(IllegalArgumentException::new);
        }
        return ObjectUtils.isEmpty(value) ? null : Stream.of(CrustType.values()).filter(e -> value.equals(e.getValue())).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * Gets the display.
     *
     * @return the display
     */
    @Override
    public String getDisplay() {
        return this.display;
    }

    /**
     * Gets the sort order.
     *
     * @return the sort order
     */
    @Override
    public int getSortOrder() {
        return this.sortOrder;
    }
}
