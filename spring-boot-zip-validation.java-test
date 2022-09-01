package com.example.demo.validation;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

@Validated
@RestController
@RequestMapping(value = "/testzipcode")
public class ZipCodeTestController {

    private final ZipcodeService zipcodeService;

    public ZipCodeTestController(ZipcodeService zipcodeService) {
        this.zipcodeService = zipcodeService;
    }

    @GetMapping
    public String get(@Zipcode @RequestParam("zipcode") String zipCode) {
        return zipCode;
    }

    @GetMapping(path = "dummy")
    public String getDummy(@RequestParam("zipcode") String zipCode) {
        try {
            zipcodeService.validate(zipCode);
            return zipCode;
        } catch (ZipCodeValidationException e) {
            return e.getMessage();
        }
    }


    @Constraint(validatedBy = {ZipcodeValidator.class})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Zipcode {
        String message() default "Invalid Zipcode value";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    public static class ZipcodeValidator implements ConstraintValidator<Zipcode, String> {
        private final ZipCodeRegexMatcher zipCodeRegexMatcher;

        public ZipcodeValidator() {
            zipCodeRegexMatcher = new ZipCodeRegexMatcher();
        }

        @Override
        public boolean isValid(String zipCode, ConstraintValidatorContext constraintValidatorContext) {
            return zipCodeRegexMatcher.isValid(zipCode);
        }
    }

    public static class ZipCodeRegexMatcher {
        public static final String ZIP_REGEX = "^[0-9]{5}(?:-[0-9]{4})?$";
        private final Pattern pattern;

        public ZipCodeRegexMatcher() {
            pattern = Pattern.compile(ZIP_REGEX);
        }

        public boolean isValid(String zipCode) {
            return pattern.matcher(zipCode).matches();
        }
    }

    public interface ZipcodeService {
        void validate(@Zipcode String zipCode) throws ZipCodeValidationException;
    }

    @Service
    public static class ZipcodeServiceImpl implements ZipcodeService {
        private final ZipCodeRegexMatcher zipCodeRegexMatcher;

        public ZipcodeServiceImpl() {
            zipCodeRegexMatcher = new ZipCodeRegexMatcher();
        }

        @Override
        public void validate(String zipCode) throws ZipCodeValidationException {
            // uncomment for Regex Validation
            // boolean valid = zipCodeRegexMatcher.isValid(zipCode);
            // uncomment for Simple text validation
            final boolean valid = !ObjectUtils.isEmpty(zipCode);

            if (!valid) {
                throw new ZipCodeValidationException("Invalid zipcode");
            }
        }
    }

    public static class ZipCodeValidationException extends Exception {
        public ZipCodeValidationException(String message) {
            super(message);
        }
    }
}
