package models;

import java.util.List;

/**
 * Created by Coffee on 11/2/2562.
 */
public class Mapping {

    public String title;
    public List<String> topics;

    public Mapping() {
    }

    public Mapping(String title, List<String> topics) {
        this.title = title;
        this.topics = topics;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}
