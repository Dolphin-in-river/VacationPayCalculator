package com.project.vacationpaycalculator.controller;

import com.project.vacationpaycalculator.attributes.CustomErrorAttributes;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

@Controller
public class CustomErrorController extends BasicErrorController {

    @Autowired
    private CustomErrorAttributes customErrorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes, new ErrorProperties());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        ErrorAttributeOptions options = ErrorAttributeOptions.of(
                ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.BINDING_ERRORS
        );
        Map<String, Object> body = customErrorAttributes.getErrorAttributes(new ServletWebRequest(request), options);
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }
}
