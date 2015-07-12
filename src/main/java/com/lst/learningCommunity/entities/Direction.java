package com.lst.learningCommunity.entities;

// Generated 2015-7-12 13:28:32 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Direction generated by hbm2java
 */
@Entity
@Table(name = "direction", catalog = "LearningCommunity")
public class Direction implements java.io.Serializable {

	private String directionName;
	private Set<Course> courses = new HashSet<Course>(0);

	public Direction() {
	}

	public Direction(String directionName) {
		this.directionName = directionName;
	}

	public Direction(String directionName, Set<Course> courses) {
		this.directionName = directionName;
		this.courses = courses;
	}

	@Id
	@Column(name = "directionName", unique = true, nullable = false, length = 20)
	public String getDirectionName() {
		return this.directionName;
	}

	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "direction")
	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}
