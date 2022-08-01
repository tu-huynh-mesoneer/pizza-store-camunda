package com.example.pizzastorecamundamodeler.enums;

import java.util.stream.Stream;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum SizeType {

    small("1", "small", 1),
    medium("2", "medium", 2),
    large("3", "large ", 3);

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
    private SizeType(String value, String display, int sortOrder) {
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
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    public static SizeType of(String value) {
        return ObjectUtils.isEmpty(value) ? null : Stream.of(SizeType.values()).filter(e -> value.equals(e.getValue())).findFirst().orElse(null);
    }

    /**
     * Gets the.
     *
     * @param value the value
     * @return the enum demo
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static SizeType of(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        if (value instanceof Map) {
            Map<String, String> mapEnum = (Map<String, String>) value;
            return ObjectUtils.isEmpty(value) ? null : Stream.of(SizeType.values()).filter(e -> e.getValue().equals(mapEnum.get("value"))).findFirst().orElseThrow(IllegalArgumentException::new);
        }
        return ObjectUtils.isEmpty(value) ? null : Stream.of(SizeType.values()).filter(e -> value.equals(e.getValue())).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Gets the display.
     *
     * @return the display
     */
    public String getDisplay() {
        return this.display;
    }

    /**
     * Gets the sort order.
     *
     * @return the sort order
     */
    @JsonIgnore
    public int getSortOrder() {
        return this.sortOrder;
    }
}
