package com.bondarenko.memoryanalyzer.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "data", length = 500000)
    private String data;

    @Column(name = "modification_data")
    private LocalDateTime modificationData;

    public int getId() {
        return this.id;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setModificationData(LocalDateTime modificationData) {
        this.modificationData = modificationData;
    }
}

