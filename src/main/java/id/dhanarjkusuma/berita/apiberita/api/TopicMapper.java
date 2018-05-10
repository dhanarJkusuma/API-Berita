package id.dhanarjkusuma.berita.apiberita.api;

import id.dhanarjkusuma.berita.apiberita.api.dto.TopicDto;
import id.dhanarjkusuma.berita.apiberita.api.dto.TopicPayload;
import id.dhanarjkusuma.berita.apiberita.domain.Topic;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TopicMapper {
    public static Topic mapToDao(TopicPayload payload){
        Topic topic = new Topic();
        topic.setName(payload.getTopic());
        return topic;
    }

    public static TopicDto mapToDto(Topic dao){
        TopicDto dto = new TopicDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setCreatedAt(ZonedDateTime.ofInstant(dao.getCreatedAt().toInstant(), ZoneId.systemDefault()));
        dto.setUpdatedAt(ZonedDateTime.ofInstant(dao.getUpdatedAt().toInstant(), ZoneId.systemDefault()));
        return dto;
    }
}
