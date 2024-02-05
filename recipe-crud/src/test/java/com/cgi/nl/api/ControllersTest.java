package com.cgi.nl.api;

import com.cgi.nl.RecepieRepo;
import com.cgi.nl.Recipe;
import com.cgi.nl.mappers.RecipeMapper;
import com.cgi.nl.service.ServiceImpl;
import com.cgi.nl.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ControllersTest {
    private final String createURL = "/api/v1/recipies/createRecipe";
    private final String deleteURL = "/api/v1/recipies/removeRecipie/123";
    private final String getURL = "/api/v1/recipies/getRecipies?pageNo=1&size=10";
    private final String updateURL = "/api/v1/recipies/saveRecipie/123";

    private MockMvc mockMvc;

    @Mock
    private ServiceImpl mockservice;
    @Autowired
    private RecepieRepo recipeRepo;
    @Autowired
    private WebApplicationContext   context;
    private List<Recipe> recipeList;


    @BeforeEach
    void setUp() {
        recipeList = Utils.loadData(Recipe.class);
        Optional.of(recipeList)
                .ifPresent(cs -> recipeList = recipeRepo.saveAll(cs));
        MockitoAnnotations.openMocks(this);
        mockservice = new ServiceImpl(recipeRepo);
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();

    }
    @AfterEach
    void tearDown(){
        recipeRepo.deleteAll();
    }
    /**
     * Test to check if receipe is created from controller
     * @throws
     * @throws Exception
     */
    @Test
    @WithMockUser(username = "dummy",password = "dummy",roles = "ADMIN")
    void testCreateRecipe() throws Exception {
        String content = "{\n" +
                "    \"recipieName\": \"briyani\",\n" +
                "    \"suitableFor\": 1,\n" +
                "    \"veg\": false,\n" +
                "    \"ingredients\":[{\n" +
                "        \"itemName\":\"rice\"\n" +
                "    },\n" +
                "{\n" +
                " \"itemName\":\"chicken\"   \n" +
                "}\n" +
                "],\n" +
                "    \n" +
                "    \"instructions\": \"cooked with rice chicken and masala\"\n" +
                "}";

        mockMvc.perform(post(createURL).header("traceId","123").
                content(content).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "dummy",password = "dummy",roles = "ADMIN")
    void testUpdateRecipe() throws Exception {
        String content = "{\n" +
                "    \"recipieName\": \"briyani\",\n" +
                "    \"suitableFor\": 1,\n" +
                "    \"veg\": false,\n" +
                "    \"ingredients\":[{\n" +
                "        \"itemName\":\"rice\"\n" +
                "    },\n" +
                "{\n" +
                " \"itemName\":\"chicken\"   \n" +
                "}\n" +
                "],\n" +
                "    \n" +
                "    \"instructions\": \"cooked with rice chicken and masala\"\n" +
                "}";

        mockMvc.perform(put(updateURL).header("traceId","123").
                content(content).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser(username = "dummy",password = "dummy",roles = "ADMIN")
    void testdeleteRecipe() throws Exception {


        mockMvc.perform(delete(deleteURL).header("traceId","123").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "dummy",password = "dummy",roles = "ADMIN")
    void testgetRecipe() throws Exception {
        String content="{\n" +
                "  \"suitableFor\": \"\",\n" +
                "  \"veg\": \"\",\n" +
                "  \"ingredients\": [\n" +
                "    {\n" +
                "      \"contains\": \"\",\n" +
                "      \"notContains\": \"\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"instructions\": \"\"\n" +
                "}";

        mockMvc.perform(post(getURL).header("traceId","123").
                content(content).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).andExpect(status().isOk());
    }

}