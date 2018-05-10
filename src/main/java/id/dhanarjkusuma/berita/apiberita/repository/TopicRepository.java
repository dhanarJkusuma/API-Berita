package id.dhanarjkusuma.berita.apiberita.repository;

import id.dhanarjkusuma.berita.apiberita.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
