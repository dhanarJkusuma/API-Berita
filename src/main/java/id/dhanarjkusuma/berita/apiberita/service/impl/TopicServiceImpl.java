package id.dhanarjkusuma.berita.apiberita.service.impl;

import id.dhanarjkusuma.berita.apiberita.domain.Topic;
import id.dhanarjkusuma.berita.apiberita.exception.NotFoundException;
import id.dhanarjkusuma.berita.apiberita.repository.TopicRepository;
import id.dhanarjkusuma.berita.apiberita.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository repository;

    @Autowired
    public TopicServiceImpl(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public Topic createTopic(Topic topic) {
        return repository.save(topic);
    }

    @Override
    public Topic findTopic(Long id) throws NotFoundException {
        Optional<Topic> topic = repository.findById(id);
        if(!topic.isPresent()){
            throw new NotFoundException("Unknown Topic with id : " + id);
        }
        return topic.get();
    }

    @Override
    public List<Topic> retrieveTopics(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).getContent();
    }

    @Override
    public Topic updateTopic(Long id, Topic topic) throws NotFoundException {
        Topic existTopic = findTopic(id);
        topic.setId(existTopic.getId());
        topic.setCreatedAt(existTopic.getCreatedAt());
        return repository.save(topic);
    }

    @Override
    public void deleteTopic(Long id) throws NotFoundException {
        Topic existTopic = findTopic(id);
        repository.delete(existTopic);
    }
}
