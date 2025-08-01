package com.cc.bootstrap.common.enums;

import com.cc.bootstrap.common.base.restful.IBaseEnum;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 选择标记
 * @author: ChenChen
 * @date: 2025-07-31 16:08
 */
public enum ChooseFlagEnum implements IBaseEnum {
    Y("Y", "是"),
    N("N", "否"),
    ;

    protected static final Map<String, ChooseFlagEnum> CHOOSE_FLAG_ENUM_MAP;

    private String code;
    private String message;

    ChooseFlagEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    static {
        CHOOSE_FLAG_ENUM_MAP = Stream.of(ChooseFlagEnum.values())
                .collect(Collectors.toMap(ChooseFlagEnum::getCode, Function.identity()));
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }

    /**
     * @Description CHOOSE_FLAG_ENUM_MAP
     *
     * @author ChenChen
     * @return java.util.Map<java.lang.String, com.cc.bootstrap.common.enums.ChooseFlagEnum>
     * @date 2025-07-31 16:12
     */
    public static Map<String, ChooseFlagEnum> getChooseFlagEnumMap() {
        return CHOOSE_FLAG_ENUM_MAP;
    }
}
