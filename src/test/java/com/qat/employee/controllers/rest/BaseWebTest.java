package com.qat.employee.controllers.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class BaseWebTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  protected RequestBuilder createRequest(String url, Object body)
      throws JsonProcessingException {
    return MockMvcRequestBuilders
        .post(url)
        .content(mapper.writeValueAsString(body))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);
  }
  protected MvcResult performRequest(RequestBuilder requestBuilder) throws Exception {
    return mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andReturn();
  }

}
