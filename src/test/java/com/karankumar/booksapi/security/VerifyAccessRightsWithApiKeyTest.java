package com.karankumar.booksapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Map;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VerifyAccessRightsWithApiKeyTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @ValueSource(strings = {"{findAllBooks{ title } }",
            "{findBookByIsbn13(isbn13: \"9780857501004\"){title}}",
            "{findByTitleIgnoreCase(title: \"A Brief History of Time\"){title}}"})
    public void allQueries_shouldBeAccessible_withoutAuthentication(String query) throws Exception {

        this.mockMvc.perform(post("/graphql").contentType("application/json")
                .content(objectMapper.writeValueAsString(Map.of("query", query)))).andExpect(status().is2xxSuccessful());

    }

    @ParameterizedTest
    @ValueSource(strings = {"mutation {addAuthor(fullName: \"Aae\"){fullName}}"})
    public void allMutations_shouldntBeAccessible_withoutAuthentication(String query) throws Exception {

        this.mockMvc.perform(post("/graphql").contentType("application/json")
                .content(objectMapper.writeValueAsString(Map.of("query", query))))
                .andExpect(matchAll(
                        status().is2xxSuccessful(),
                        jsonPath("$.errors[0].extensions.errorType").value("PERMISSION_DENIED")
                ));

    }

}
