package com.lucasengcomp.junit.blog.utility;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ConversorSlugTest {

    @Test
    void mustConverterTogetherWithCode() {
        try(MockedStatic<CodeGenerator> codeGeneratorMockedStatic = Mockito.mockStatic(CodeGenerator.class)) {
            codeGeneratorMockedStatic.when(CodeGenerator::generate).thenReturn("123456");
            String slug = ConversorSlug.convertWithCode("hi devs Java!");
            assertEquals("hi-devs-java-123456", slug);
        }
    }
}
