package com.CourseWorkRusut.model;

import javax.persistence.Embeddable;

@Embeddable
public class EmbeddableLearningInternship {

    private byte[] report;
    private String mark;

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
}
