package com.lst.learningCommunity.entities;

// Generated 2015-7-11 20:31:17 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * RelUserRoute generated by hbm2java
 */
@Entity
@Table(name = "relUserRoute", catalog = "LearningCommunity")
public class RelUserRoute implements java.io.Serializable {

	private int userId;
	private StudyRoute studyRoute;
	private User user;
	private Date time;
	private int progress;

	public RelUserRoute() {
	}

	public RelUserRoute(StudyRoute studyRoute, User user, Date time,
			int progress) {
		this.studyRoute = studyRoute;
		this.user = user;
		this.time = time;
		this.progress = progress;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "userId", unique = true, nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "routeId", nullable = false)
	public StudyRoute getStudyRoute() {
		return this.studyRoute;
	}

	public void setStudyRoute(StudyRoute studyRoute) {
		this.studyRoute = studyRoute;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", nullable = false, length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "progress", nullable = false)
	public int getProgress() {
		return this.progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

}
