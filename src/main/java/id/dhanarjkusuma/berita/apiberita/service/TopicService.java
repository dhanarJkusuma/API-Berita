package id.dhanarjkusuma.berita.apiberita.service;

import id.dhanarjkusuma.berita.apiberita.domain.Topic;
import id.dhanarjkusuma.berita.apiberita.exception.NotFoundException;

import java.util.List;

public interface TopicService {
    Topic createTopic(Topic topic);
    Topic findTopic(Long id) throws NotFoundException;
    List<Topic> retrieveTopics(int page, int size);
    Topic updateTopic(Long id, Topic topic) throws NotFoundException;
    void deleteTopic(Long id) throws NotFoundException;
}
