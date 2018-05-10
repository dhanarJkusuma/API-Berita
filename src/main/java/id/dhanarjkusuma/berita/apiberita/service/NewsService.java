package id.dhanarjkusuma.berita.apiberita.service;

import id.dhanarjkusuma.berita.apiberita.domain.News;
import id.dhanarjkusuma.berita.apiberita.domain.NewsStatus;
import id.dhanarjkusuma.berita.apiberita.exception.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    News createNews(News news);
    News addThumbnail(Long id, MultipartFile file);
    List<News> retrieveNews(int page, int size);
    List<News> findByTopic(Long topicId, int page, int size);
    List<News> findByStatus(NewsStatus status, int page, int size);
    News findNews(Long id) throws NotFoundException;
    News updateNews(Long id, News news) throws NotFoundException;
    void destroyNews(Long id) throws NotFoundException;
}
