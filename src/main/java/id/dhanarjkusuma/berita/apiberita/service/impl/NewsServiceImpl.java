package id.dhanarjkusuma.berita.apiberita.service.impl;

import id.dhanarjkusuma.berita.apiberita.domain.News;
import id.dhanarjkusuma.berita.apiberita.domain.NewsStatus;
import id.dhanarjkusuma.berita.apiberita.exception.NotFoundException;
import id.dhanarjkusuma.berita.apiberita.repository.NewsRepository;
import id.dhanarjkusuma.berita.apiberita.service.NewsService;
import id.dhanarjkusuma.berita.apiberita.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository repository;
    private final StorageService storageService;

    @Autowired
    public NewsServiceImpl(NewsRepository repository, StorageService storageService) {
        this.repository = repository;
        this.storageService = storageService;
    }

    @Override
    public News createNews(News news) {
        return repository.save(news);
    }

    @Override
    public News addThumbnail(Long id, MultipartFile file) {
        News existNews = findNews(id);
        Path path = storageService.store(file, String.valueOf(existNews.getId()));
        existNews.setThumbnail(path.toString());
        existNews.setUpdatedAt(new Date());
        return repository.save(existNews);
    }

    @Override
    public List<News> retrieveNews(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).getContent();
    }

    @Override
    public List<News> findByTopic(Long topicId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<News> newsList = repository.findByTopics_Id(topicId, pageable);
        return newsList.getContent();
    }

    @Override
    public List<News> findByStatus(NewsStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<News> newsList = repository.findByStatus(status, pageable);
        return newsList.getContent();
    }

    @Override
    public News findNews(Long id) throws NotFoundException {
        Optional<News> existNews = repository.findById(id);
        if(!existNews.isPresent()){
            throw new NotFoundException("Unknown news with id : " + id);
        }
        return existNews.get();
    }

    @Override
    public News updateNews(Long id, News news) throws NotFoundException {
        News existNews = findNews(id);
        news.setId(existNews.getId());
        news.setCreatedAt(existNews.getCreatedAt());
        return repository.save(news);
    }

    @Override
    public void destroyNews(Long id) throws NotFoundException {
        News existNews = findNews(id);
        repository.delete(existNews);
    }
}
