import io.pivotal.ce.fileproc.controllers.FileUploadController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by cq on 22/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class AppTests {

        private MockMvc mvc;

        @InjectMocks
        FileUploadController fileUploadController;

        @Mock
        AmqpTemplate amqpTemplate;

        @Before
        public void setUp() throws Exception {
                MockitoAnnotations.initMocks(this);
                mvc = MockMvcBuilders.standaloneSetup(fileUploadController).build();
        }

        @Test
        public void testFileUpload() throws Exception {

                MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some text"
                        .getBytes());

                mvc.perform(MockMvcRequestBuilders
                        .fileUpload("/upload")
                        .file(file))
                        .andExpect(status().isOk())
                        .andExpect(content().string(equalTo("File sent for processing!!!")));
        }
}
