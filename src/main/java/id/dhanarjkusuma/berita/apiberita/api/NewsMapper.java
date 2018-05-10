package id.dhanarjkusuma.berita.apiberita.api;

import id.dhanarjkusuma.berita.apiberita.api.dto.NewsDto;
import id.dhanarjkusuma.berita.apiberita.api.dto.NewsPayload;
import id.dhanarjkusuma.berita.apiberita.domain.News;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

public class NewsMapper {

    public static News mapToDao(NewsPayload payload){
        News dao = new News();
        dao.setTitle(payload.getTitle());
        dao.setDescription(payload.getDescription());
        dao.setStatus(payload.getStatus());
        return dao;
    }

    public static NewsDto mapToDto(News dao){
        NewsDto dto = new NewsDto();
        dto.setId(dao.getId());
        dto.setTitle(dao.getTitle());
        dto.setThumbnail(dao.getThumbnail());
        dto.setStatus(dao.getStatus());
        dto.setDescription(dao.getDescription());
        dto.setUpdatedAt(ZonedDateTime.ofInstant(dao.getUpdatedAt().toInstant(), ZoneId.systemDefault()));
        dto.setCreatedAt(ZonedDateTime.ofInstant(dao.getCreatedAt().toInstant(), ZoneId.systemDefault()));
        dto.setTopics(dao.getTopics().stream().map(TopicMapper::mapToDto).collect(Collectors.toList()));
        return dto;
    }
}
