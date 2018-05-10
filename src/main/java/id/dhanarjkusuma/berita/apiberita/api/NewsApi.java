package id.dhanarjkusuma.berita.apiberita.api;

import id.dhanarjkusuma.berita.apiberita.api.dto.NewsDto;
import id.dhanarjkusuma.berita.apiberita.api.dto.NewsPayload;
import id.dhanarjkusuma.berita.apiberita.domain.News;
import id.dhanarjkusuma.berita.apiberita.domain.NewsStatus;
import id.dhanarjkusuma.berita.apiberita.domain.Topic;
import id.dhanarjkusuma.berita.apiberita.exception.NotFoundException;
import id.dhanarjkusuma.berita.apiberita.service.NewsService;
import id.dhanarjkusuma.berita.apiberita.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news")
public class NewsApi {
    private final NewsService newsService;
    private final TopicService topicService;

    @Autowired
    public NewsApi(NewsService newsService, TopicService topicService) {
        this.newsService = newsService;
        this.topicService = topicService;
    }


    @PostMapping
    public ResponseEntity createNewNews(@RequestBody @Validated NewsPayload payload){
        News news = NewsMapper.mapToDao(payload);


        Set<Topic> topicSet = new HashSet<>();
        try{
            for(Long topicId : payload.getTopics()){
                Topic topic = topicService.findTopic(topicId);
                topicSet.add(topic);
            }
        }catch (NotFoundException e){
            Map<String, String> message = new HashMap<>();
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        news.setTopics(topicSet);

        return ResponseEntity.ok(NewsMapper.mapToDto(newsService.createNews(news)));
    }

    @GetMapping
    public List<NewsDto> retrieveNews(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "1000") Integer size
    ){
        List<News> news = newsService.retrieveNews(page, size);
        return news.stream().map(NewsMapper::mapToDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findNews(@PathVariable(name = "id") Long id){
        try{
            News existNews = newsService.findNews(id);
            return ResponseEntity.ok(NewsMapper.mapToDto(existNews));
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/by-topic/{topicId}")
    public List<NewsDto> findByTopic(
            @PathVariable(name = "topicId") Long topicId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "1000") Integer size
    ){
        List<News> news = newsService.findByTopic(topicId, page, size);
        return news.stream().map(NewsMapper::mapToDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/by-status/{status:DRAFT|DELETED|PUBLISH}")
    public List<NewsDto> findByStatus(
            @PathVariable(name = "status") String status,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "1000") Integer size
    ){
        NewsStatus newsStatus = NewsStatus.valueOf(status);
        List<News> news = newsService.findByStatus(newsStatus, page, size);
        return news.stream().map(NewsMapper::mapToDto).collect(Collectors.toList());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateNews(@PathVariable(name = "id") Long id, @RequestBody @Validated NewsPayload payload){
        News news = NewsMapper.mapToDao(payload);


        Set<Topic> topicSet = new HashSet<>();
        try{
            for(Long topicId : payload.getTopics()){
                Topic topic = topicService.findTopic(topicId);
                topicSet.add(topic);
            }
        }catch (NotFoundException e){
            Map<String, String> message = new HashMap<>();
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        news.setTopics(topicSet);

        try{
            News resultNews = newsService.updateNews(id, news);
            return ResponseEntity.ok(resultNews);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/upload-thumbnail/{id}")
    public ResponseEntity uploadThumbnail(@PathVariable(name = "id") Long newsId, @RequestParam("thumbnail") MultipartFile file){
        try{
            News news = newsService.addThumbnail(newsId, file);
            return ResponseEntity.ok(news);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (RuntimeException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity destroyNews(@PathVariable(name = "id") Long id){
        try{
            newsService.destroyNews(id);
            return ResponseEntity.noContent().build();
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
