package it.objectmethod.jpa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tag")
public class Tag {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	@Column(name = "description")
	private String description;

	@Column(name = "color")
	private String color;

	@JsonIgnore
	@JoinColumn(name = "id")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Todo> todo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Todo> getTodo() {
		return todo;
	}

	public void setTodo(List<Todo> todo) {
		this.todo = todo;
	}
}
