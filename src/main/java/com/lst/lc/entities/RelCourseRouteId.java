package com.lst.lc.entities;

// Generated 2015-7-13 13:31:58 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RelCourseRouteId generated by hbm2java
 */
@Embeddable
public class RelCourseRouteId implements java.io.Serializable {

	private int courseId;
	private int routeId;

	public RelCourseRouteId() {
	}

	public RelCourseRouteId(int courseId, int routeId) {
		this.courseId = courseId;
		this.routeId = routeId;
	}

	@Column(name = "courseId", nullable = false)
	public int getCourseId() {
		return this.courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@Column(name = "routeId", nullable = false)
	public int getRouteId() {
		return this.routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RelCourseRouteId))
			return false;
		RelCourseRouteId castOther = (RelCourseRouteId) other;

		return (this.getCourseId() == castOther.getCourseId())
				&& (this.getRouteId() == castOther.getRouteId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCourseId();
		result = 37 * result + this.getRouteId();
		return result;
	}

}
