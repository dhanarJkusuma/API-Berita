package id.dhanarjkusuma.berita.apiberita.repository;

import id.dhanarjkusuma.berita.apiberita.domain.News;
import id.dhanarjkusuma.berita.apiberita.domain.NewsStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findByTopics_Id(Long id, Pageable pageable);
    Page<News> findByStatus(NewsStatus newsStatus, Pageable pageable);
}
