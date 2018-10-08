package com.gaussic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "u_log", schema = "wind", catalog = "")
public class ULogEntity {
    private long id;
    private Long lUserId;
    private Timestamp lLogTime;
    private Integer lLoginOrlogOut;
    private String lNote;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "l_user_id")
    public Long getlUserId() {
        return lUserId;
    }

    public void setlUserId(Long lUserId) {
        this.lUserId = lUserId;
    }

    @Basic
    @Column(name = "l_log_time")
    public Timestamp getlLogTime() {
        return lLogTime;
    }

    public void setlLogTime(Timestamp lLogTime) {
        this.lLogTime = lLogTime;
    }

    @Basic
    @Column(name = "l_loginOrlogOut")
    public Integer getlLoginOrlogOut() {
        return lLoginOrlogOut;
    }

    public void setlLoginOrlogOut(Integer lLoginOrlogOut) {
        this.lLoginOrlogOut = lLoginOrlogOut;
    }

    @Basic
    @Column(name = "l_note")
    public String getlNote() {
        return lNote;
    }

    public void setlNote(String lNote) {
        this.lNote = lNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ULogEntity that = (ULogEntity) o;
        return id == that.id &&
                Objects.equals(lUserId, that.lUserId) &&
                Objects.equals(lLogTime, that.lLogTime) &&
                Objects.equals(lLoginOrlogOut, that.lLoginOrlogOut) &&
                Objects.equals(lNote, that.lNote);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, lUserId, lLogTime, lLoginOrlogOut, lNote);
    }
}
