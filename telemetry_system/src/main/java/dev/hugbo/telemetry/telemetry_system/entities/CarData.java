package dev.hugbo.telemetry.telemetry_system.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "data")
public class CarData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String channel;
    private int value;
    private String unit;

    // Getters
    public Long getId() { return id; }
    public String getChannel() { return channel; }
    public int getValue() { return value; }
    public String getUnit() { return unit; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setChannel(String channel) { this.channel = channel; }
    public void setValue(int value) { this.value = value; }
    public void setUnit(String unit) { this.unit = unit; }

}
