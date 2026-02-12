package com.SmartAgriculture.Cropp.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fertilizer_recommendations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fertilizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fertilizerId;

    @Column(nullable = false)
    private String recommendedFertilizer;

    private Double quantity;

    private String application;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_id", nullable = false)
    private SensorData sensorData;
}
