package com.atsun.springboot_hibernate.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SH
 */
@Api
@RequestMapping(value = BaseController.BASE_URL)
@RestController
public class BaseController {

    public static final String BASE_URL = "api";

}
