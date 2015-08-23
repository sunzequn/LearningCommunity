package com.lst.lc.entities;

// Generated 2015-8-23 12:55:26 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RelUser generated by hbm2java
 */
@Entity
@Table(name = "relUser", catalog = "LearningCommunity")
public class RelUser implements java.io.Serializable {

        private RelUserId id;
        private User userByUserId1;
        private User userByUserId2;
        private Date date;

        public RelUser() {
        }

        public RelUser(RelUserId id, User userByUserId1, User userByUserId2) {
                this.id = id;
                this.userByUserId1 = userByUserId1;
                this.userByUserId2 = userByUserId2;
        }

        public RelUser(RelUserId id, User userByUserId1, User userByUserId2,
                        Date date) {
                this.id = id;
                this.userByUserId1 = userByUserId1;
                this.userByUserId2 = userByUserId2;
                this.date = date;
        }

        @EmbeddedId
        @AttributeOverrides({
                        @AttributeOverride(name = "userId1", column = @Column(name = "userId1", nullable = false)),
                        @AttributeOverride(name = "userId2", column = @Column(name = "userId2", nullable = false)) })
        public RelUserId getId() {
                return this.id;
        }

        public void setId(RelUserId id) {
                this.id = id;
        }

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "userId1", nullable = false, insertable = false, updatable = false)
        public User getUserByUserId1() {
                return this.userByUserId1;
        }

        public void setUserByUserId1(User userByUserId1) {
                this.userByUserId1 = userByUserId1;
        }

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "userId2", nullable = false, insertable = false, updatable = false)
        public User getUserByUserId2() {
                return this.userByUserId2;
        }

        public void setUserByUserId2(User userByUserId2) {
                this.userByUserId2 = userByUserId2;
        }

        @Temporal(TemporalType.DATE)
        @Column(name = "date", length = 10)
        public Date getDate() {
                return this.date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

}
