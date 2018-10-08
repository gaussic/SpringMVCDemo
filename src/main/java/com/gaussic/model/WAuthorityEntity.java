package com.gaussic.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "w_authority", schema = "wind", catalog = "")
public class WAuthorityEntity {
    private long id;
    private String aName;
    private String aNum;
    private String aNote;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "a_name")
    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    @Basic
    @Column(name = "a_num")
    public String getaNum() {
        return aNum;
    }

    public void setaNum(String aNum) {
        this.aNum = aNum;
    }

    @Basic
    @Column(name = "a_note")
    public String getaNote() {
        return aNote;
    }

    public void setaNote(String aNote) {
        this.aNote = aNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WAuthorityEntity that = (WAuthorityEntity) o;
        return id == that.id &&
                Objects.equals(aName, that.aName) &&
                Objects.equals(aNum, that.aNum) &&
                Objects.equals(aNote, that.aNote);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, aName, aNum, aNote);
    }
}
