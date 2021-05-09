package com.oftekfak.emagazine.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public final class ObjectUtils {
    @Autowired
    private static ModelMapper modelMapper;

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }
}
