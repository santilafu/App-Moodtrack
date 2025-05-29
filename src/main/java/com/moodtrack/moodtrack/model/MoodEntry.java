package com.moodtrack.moodtrack.model;



import jakarta.persistence.*;
import java.time.LocalDate;


@Entity // Marca esta clase como una tabla en la base de datos
public class MoodEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
    private Long id;

    private LocalDate fecha;

    private String emocion;

    private String nota;

    // Constructor vacío (necesario para JPA)
    public MoodEntry() {
    }

    // Constructor con todos los campos menos el ID
    public MoodEntry(LocalDate fecha, String emocion, String nota) {
        this.fecha = fecha;
        this.emocion = emocion;
        this.nota = nota;
    }

    // Getters y Setters (sirven para acceder y modificar los atributos)
    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
