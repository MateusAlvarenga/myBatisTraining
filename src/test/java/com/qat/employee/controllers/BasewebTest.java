package com.qat.employee.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public abstract class BasewebTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper mapper;

  protected RequestBuilder createRequest(HttpMethod method, String url, Object body)
      throws JsonProcessingException {
    return MockMvcRequestBuilders
        .request(method, url)
        .content(mapper.writeValueAsString(body))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);
  }

  protected RequestBuilder createRequest(String url, Object body)
      throws JsonProcessingException {
    return createRequest(HttpMethod.POST, url, body);
  }

  protected RequestBuilder createRequest(HttpMethod method, String url)
      throws JsonProcessingException {
    return MockMvcRequestBuilders
        .request(method, url)
        .accept(MediaType.APPLICATION_JSON);
  }

  protected MvcResult performRequest(RequestBuilder requestBuilder) throws Exception {
    return mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andReturn();
  }
  protected MvcResult performRequestAndReturn(RequestBuilder requestBuilder) throws Exception {
    return mockMvc.perform(requestBuilder)
        .andReturn();
  }

  protected void assertJsonEquals(MvcResult expected, Object actual) throws Exception {
    JSONAssert.assertEquals(
        expected.getResponse().getContentAsString(),
        mapper.writeValueAsString(actual), true);
  }

}
