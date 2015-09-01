package net.virtalab.mvctools.test;


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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * This is example class for testing Spring MVC Applications and library
 *
 * @author Alexander Muravya {@literal <muravya@corp.sputnik.ru>}
 * @since 2.2
 */
@SuppressWarnings({"unused" , "FieldCanBeLocal"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:test-app.xml"})
@WebAppConfiguration
public class StubTest {

    @Autowired
    WebApplicationContext wac;
    @Autowired
    MockHttpServletRequest request;

    private MockMvc mockMvc;

    /*
    * Do if needed
    @InjectMocks
    private AnyController controller;
    */

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void sampleTest() throws Exception {
        /*
         * String testBodyStr = "Test or JSON Here";
         MvcResult result = this.mockMvc
         .perform(post("/path").content(testBodyStr))
         .andExpect(status().is2xxSuccessful())
         .andReturn();

         String responseBody = result.getResponse().getContentAsString();
         //parse from json
         SomeStructHere json = new Gson().fromJson(responseBody, SomeStructHere.class);

         Assert.assertEquals("Body mismatch", testBodyStr, json.body);
         }
         */
        //Stub
        Assert.assertTrue(true);

    }
}
