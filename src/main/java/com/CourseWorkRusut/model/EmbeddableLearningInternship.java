package com.CourseWorkRusut.model;

import javax.persistence.Embeddable;

@Embeddable
public class EmbeddableLearningInternship {

    private byte[] report;
    private String mark;
    private String topic;

    public byte[] getReport() {
        return report;
    }

    public void setReport(byte[] report) {
        this.report = report;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
