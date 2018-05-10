package id.dhanarjkusuma.berita.apiberita;

import id.dhanarjkusuma.berita.apiberita.api.NewsApi;
import id.dhanarjkusuma.berita.apiberita.api.dto.NewsDto;
import id.dhanarjkusuma.berita.apiberita.api.dto.TopicDto;
import id.dhanarjkusuma.berita.apiberita.domain.NewsStatus;
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
@WebMvcTest(NewsApi.class)
public class NewsApiTest {

    private final String BASEPATH = "http://localhost:8080/api";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsApi newsApi;

    @Test
    public void getNews() throws Exception {
        TopicDto topic = new TopicDto();
        topic.setId(1L);
        topic.setName("Topic Contoh Berita");
        topic.setCreatedAt(ZonedDateTime.now());
        topic.setUpdatedAt(ZonedDateTime.now());


        NewsDto news = new NewsDto();
        news.setId(1L);
        news.setTitle("Contoh Berita");
        news.setThumbnail("https://alibaba.kumpar.com/kumpar/image/upload/c_fill,g_face,f_jpg,q_auto,fl_progressive,fl_lossy,w_800/xwn31obug0syctfkcgp8.jpg");
        news.setDescription("Berikut adalah contoh berita. ");
        news.setStatus(NewsStatus.DRAFT);
        news.setTopics(new ArrayList<>(Collections.singleton(topic)));
        news.setCreatedAt(ZonedDateTime.now());
        news.setUpdatedAt(ZonedDateTime.now());


        given(newsApi.retrieveNews(0, 10)).willReturn(new ArrayList<>(Collections.singleton(news)));

        mockMvc.perform(get(BASEPATH + "/news?page=0&size=10").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findById() throws Exception {
        TopicDto topic = new TopicDto();
        topic.setId(1L);
        topic.setName("Topic Contoh Berita");
        topic.setCreatedAt(ZonedDateTime.now());
        topic.setUpdatedAt(ZonedDateTime.now());


        NewsDto news = new NewsDto();
        news.setId(1L);
        news.setTitle("Contoh Berita");
        news.setThumbnail("https://alibaba.kumpar.com/kumpar/image/upload/c_fill,g_face,f_jpg,q_auto,fl_progressive,fl_lossy,w_800/xwn31obug0syctfkcgp8.jpg");
        news.setDescription("Berikut adalah contoh berita. ");
        news.setStatus(NewsStatus.DRAFT);
        news.setTopics(new ArrayList<>(Collections.singleton(topic)));
        news.setCreatedAt(ZonedDateTime.now());
        news.setUpdatedAt(ZonedDateTime.now());


        given(newsApi.findNews(1L)).willReturn(ResponseEntity.ok(news));

        mockMvc.perform(get(BASEPATH + "/news/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findByTopicId() throws Exception {
        TopicDto topic = new TopicDto();
        topic.setId(1L);
        topic.setName("Topic Contoh Berita");
        topic.setCreatedAt(ZonedDateTime.now());
        topic.setUpdatedAt(ZonedDateTime.now());


        NewsDto news = new NewsDto();
        news.setId(1L);
        news.setTitle("Contoh Berita");
        news.setThumbnail("https://alibaba.kumpar.com/kumpar/image/upload/c_fill,g_face,f_jpg,q_auto,fl_progressive,fl_lossy,w_800/xwn31obug0syctfkcgp8.jpg");
        news.setDescription("Berikut adalah contoh berita. ");
        news.setStatus(NewsStatus.DRAFT);
        news.setTopics(new ArrayList<>(Collections.singleton(topic)));
        news.setCreatedAt(ZonedDateTime.now());
        news.setUpdatedAt(ZonedDateTime.now());


        given(newsApi.findByTopic(1L,0, 10)).willReturn(new ArrayList<>(Collections.singleton(news)));

        mockMvc.perform(get(BASEPATH + "/news/by-topic/1?page=0&size10").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findByStatus() throws Exception {
        TopicDto topic = new TopicDto();
        topic.setId(1L);
        topic.setName("Topic Contoh Berita");
        topic.setCreatedAt(ZonedDateTime.now());
        topic.setUpdatedAt(ZonedDateTime.now());


        NewsDto news = new NewsDto();
        news.setId(1L);
        news.setTitle("Contoh Berita");
        news.setThumbnail("https://alibaba.kumpar.com/kumpar/image/upload/c_fill,g_face,f_jpg,q_auto,fl_progressive,fl_lossy,w_800/xwn31obug0syctfkcgp8.jpg");
        news.setDescription("Berikut adalah contoh berita. ");
        news.setStatus(NewsStatus.DRAFT);
        news.setTopics(new ArrayList<>(Collections.singleton(topic)));
        news.setCreatedAt(ZonedDateTime.now());
        news.setUpdatedAt(ZonedDateTime.now());


        given(newsApi.findByStatus("DRAFT",0, 10)).willReturn(new ArrayList<>(Collections.singleton(news)));

        mockMvc.perform(get( BASEPATH + "/news/by-status/DRAFT?page=0&size=10").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
