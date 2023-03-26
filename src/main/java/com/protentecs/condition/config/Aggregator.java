package com.protentecs.condition.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Pavel Moriakov
 * @since 26/03/2023
 */
@Component
public class Aggregator {

    private final FileValidator fileValidator;

    private final StructureValidator structureValidator;

    public Aggregator(@Autowired(required = false) FileValidator fileValidator,
                      @Autowired(required = false) StructureValidator structureValidator) {
        this.fileValidator = fileValidator;
        this.structureValidator = structureValidator;
    }

    @PostConstruct
    public void validate(){
        Stream.of(fileValidator,structureValidator)
                .filter(Objects::nonNull)
                .forEach(validator -> System.out.println(validator));
    }

}
