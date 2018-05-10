package id.dhanarjkusuma.berita.apiberita.api.dto;

import javax.validation.constraints.NotNull;

public class TopicPayload {

    @NotNull
    private String topic;

    public TopicPayload() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
