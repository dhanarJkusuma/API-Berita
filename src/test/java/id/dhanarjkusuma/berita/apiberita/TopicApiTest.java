package id.dhanarjkusuma.berita.apiberita;

import id.dhanarjkusuma.berita.apiberita.api.TopicApi;
import id.dhanarjkusuma.berita.apiberita.api.dto.TopicDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TopicApi.class)
public class TopicApiTest {

    private final String BASEPATH = "http://localhost:8080/api";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicApi topicApi;


    @Test
    public void getTopics() throws Exception {
        TopicDto topic = new TopicDto();
        topic.setId(1L);
        topic.setName("Topic Contoh Berita");
        topic.setCreatedAt(ZonedDateTime.now());
        topic.setUpdatedAt(ZonedDateTime.now());

        given(topicApi.retrieveTopic(0, 1000)).willReturn(new ArrayList<>(Collections.singleton(topic)));

        mockMvc.perform(get( BASEPATH + "/topics?page=0&size=1000").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getTopicById() throws Exception {
        TopicDto topic = new TopicDto();
        topic.setId(1L);
        topic.setName("Topic Contoh Berita");
        topic.setCreatedAt(ZonedDateTime.now());
        topic.setUpdatedAt(ZonedDateTime.now());

        given(topicApi.findTopic(1L)).willReturn(ResponseEntity.ok(topic));

        mockMvc.perform(get( BASEPATH + "/topics/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
