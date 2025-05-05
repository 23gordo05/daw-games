package com.daw_games.persistence.entities;

import java.time.LocalDate;

import com.daw_games.persistence.entities.enums.Tipo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="juego")
public class Game {

// atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nombre;
	private String genero;
	private String plataformas;
	private double precio;
	private long descargas;
	
	@Column(name = "fecha_lanzamiento")
	private LocalDate fechaLanzamiento;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	private Boolean completado;
	
// getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getPlataformas() {
		return plataformas;
	}
	public void setPlataformas(String plataformas) {
		this.plataformas = plataformas;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public long getDescargas() {
		return descargas;
	}
	public void setDescargas(long descargas) {
		this.descargas = descargas;
	}
	public LocalDate getFechaLanzamiento() {
		return fechaLanzamiento;
	}
	public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Boolean getCompletado() {
		return completado;
	}
	public void setCompletado(Boolean completado) {
		this.completado = completado;
	}
		
}