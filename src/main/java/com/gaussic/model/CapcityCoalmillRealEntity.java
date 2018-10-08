package com.gaussic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "capcity_coalmill_real", schema = "wind", catalog = "")
public class CapcityCoalmillRealEntity {
    private long id;
    private Timestamp cTime;
    private Float cCapcityValue;
    private Float cCoalMillSet;
    private Float cCalMillReal;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "c_time")
    public Timestamp getcTime() {
        return cTime;
    }

    public void setcTime(Timestamp cTime) {
        this.cTime = cTime;
    }

    @Basic
    @Column(name = "c_capcity_value")
    public Float getcCapcityValue() {
        return cCapcityValue;
    }

    public void setcCapcityValue(Float cCapcityValue) {
        this.cCapcityValue = cCapcityValue;
    }

    @Basic
    @Column(name = "c_coal_mill_set")
    public Float getcCoalMillSet() {
        return cCoalMillSet;
    }

    public void setcCoalMillSet(Float cCoalMillSet) {
        this.cCoalMillSet = cCoalMillSet;
    }

    @Basic
    @Column(name = "c_cal_mill_real")
    public Float getcCalMillReal() {
        return cCalMillReal;
    }

    public void setcCalMillReal(Float cCalMillReal) {
        this.cCalMillReal = cCalMillReal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CapcityCoalmillRealEntity that = (CapcityCoalmillRealEntity) o;
        return id == that.id &&
                Objects.equals(cTime, that.cTime) &&
                Objects.equals(cCapcityValue, that.cCapcityValue) &&
                Objects.equals(cCoalMillSet, that.cCoalMillSet) &&
                Objects.equals(cCalMillReal, that.cCalMillReal);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, cTime, cCapcityValue, cCoalMillSet, cCalMillReal);
    }
}
