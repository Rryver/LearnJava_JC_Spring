package com.kolosov.learnjava_jc_spring.common;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = AbstractRestController.BASE_REST_URL)
public abstract class AbstractRestController {

    public static final String BASE_REST_URL = "/api";
}
