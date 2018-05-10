package id.dhanarjkusuma.berita.apiberita.api;

import id.dhanarjkusuma.berita.apiberita.api.dto.TopicDto;
import id.dhanarjkusuma.berita.apiberita.api.dto.TopicPayload;
import id.dhanarjkusuma.berita.apiberita.domain.Topic;
import id.dhanarjkusuma.berita.apiberita.exception.NotFoundException;
import id.dhanarjkusuma.berita.apiberita.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/topics")
public class TopicApi {

    private final TopicService topicService;

    @Autowired
    public TopicApi(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public TopicDto createNewTopic(@RequestBody @Validated TopicPayload payload){
        Topic topic = TopicMapper.mapToDao(payload);
        return TopicMapper.mapToDto(topicService.createTopic(topic));
    }

    @GetMapping
    public List<TopicDto> retrieveTopic(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "1000") Integer size
    ){
        List<Topic> topics = topicService.retrieveTopics(page, size);
        return topics.stream().map(TopicMapper::mapToDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findTopic(@PathVariable(name = "id") Long id){
        try{
            Topic existTopic = topicService.findTopic(id);
            return ResponseEntity.ok(TopicMapper.mapToDto(existTopic));
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateTopic(@PathVariable(name = "id") Long id, @RequestBody @Validated TopicPayload payload){
        Topic topic = TopicMapper.mapToDao(payload);
        try{
            Topic resultTopic = topicService.updateTopic(id, topic);
            return ResponseEntity.ok(TopicMapper.mapToDto(resultTopic));
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity destroyTopic(@PathVariable(name = "id") Long id){
        try{
            topicService.deleteTopic(id);
            return ResponseEntity.noContent().build();
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
