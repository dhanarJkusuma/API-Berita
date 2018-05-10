package id.dhanarjkusuma.berita.apiberita.api.dto;

import id.dhanarjkusuma.berita.apiberita.domain.NewsStatus;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class NewsPayload {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private NewsStatus status;

    @NotNull
    private Set<Long> topics;

    public NewsPayload() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NewsStatus getStatus() {
        return status;
    }

    public void setStatus(NewsStatus status) {
        this.status = status;
    }

    public Set<Long> getTopics() {
        return topics;
    }

    public void setTopics(Set<Long> topics) {
        this.topics = topics;
    }
}
