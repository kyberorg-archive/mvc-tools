package net.virtalab.mvctools.test;

import com.google.gson.Gson;
import net.virtalab.mvctools.render.type.ErrorJson;
import net.virtalab.mvctools.test.app.struct.DivisionStruct;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This is example class for testing Spring MVC Applications and library
 *
 * @author Alexander Muravya {@literal <muravya@corp.sputnik.ru>}
 * @since 2.2
 */
@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:test-app.xml"})
@WebAppConfiguration
public class ExceptionHandlerTest {

    @Autowired
    WebApplicationContext wac;
    @Autowired
    MockHttpServletRequest request;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void generalExceptionTest() throws Exception {
        DivisionStruct requestObject = new DivisionStruct();
        requestObject.numerator = 1;
        requestObject.denominator = 0;

        String requestBody = new Gson().toJson(requestObject);

        MvcResult result = this.mockMvc
                .perform(post("/divisor").content(requestBody))
                .andExpect(status().is5xxServerError())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Assert.assertFalse("Body is Empty", responseBody.trim().isEmpty());

        ErrorJson errorJson = new Gson().fromJson(responseBody, ErrorJson.class);
        Assert.assertNotNull(errorJson);

        ErrorJson.Error error = errorJson.error;
        Assert.assertNotNull(error);
        Assert.assertNotNull(error.message);
        Assert.assertFalse("Error message is empty", error.message.trim().isEmpty());

    }
}
