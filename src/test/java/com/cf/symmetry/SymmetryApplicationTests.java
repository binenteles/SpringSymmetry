package com.cf.symmetry;

import com.cf.symmetry.controller.SymmetryController;
import com.cf.symmetry.dto.EvalRequestDto;
import com.cf.symmetry.mapper.EvalRequestMapper;
import com.cf.symmetry.service.evaluation.EvaluatorService;
import com.cf.symmetry.service.evaluation.SymmetryFactory;
import com.cf.symmetry.validator.EvalRequestValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SymmetryController.class)
@Import({EvaluatorService.class, SymmetryFactory.class, EvalRequestMapper.class, EvalRequestValidator.class})
class SymmetryApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldHaveAcceptedOutcome() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/symmetry-status").contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new EvalRequestDto("([{}])", "WHILE")))).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"String ([{}]) is symmetric. Method used: WHILE\"}"));
    }

    @Test
    void shouldHaveAcceptedOutcome_MethodIsNull() throws Exception {


        EvalResponse evalResponse = new EvalResponse("String ([{}]) is symmetric. Method used: FOR");
        String response = objectMapper.writeValueAsString(evalResponse);

        mockMvc.perform(post("/api/symmetry-status").contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new EvalRequestDto("([{}])", null)))).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(response));
    }

    @Test
    void shouldHaveBadRequestOutcome_StringIsNull() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/symmetry-status").contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new EvalRequestDto(null, "REGEX")))).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Please provide a valid string. Method is optional; options are: FOR, WHILE, STACK, REGEX\",\"httpStatus\":\"BAD_REQUEST\"}"));
    }


}
