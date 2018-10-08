package com.gaussic.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gather_data_set", schema = "wind", catalog = "")
public class GatherDataSetEntity {
    private long id;
    private Integer gGatherInterval;
    private Byte gShowLastValue;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "g_gather_interval")
    public Integer getgGatherInterval() {
        return gGatherInterval;
    }

    public void setgGatherInterval(Integer gGatherInterval) {
        this.gGatherInterval = gGatherInterval;
    }

    @Basic
    @Column(name = "g_showLastValue")
    public Byte getgShowLastValue() {
        return gShowLastValue;
    }

    public void setgShowLastValue(Byte gShowLastValue) {
        this.gShowLastValue = gShowLastValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GatherDataSetEntity that = (GatherDataSetEntity) o;
        return id == that.id &&
                Objects.equals(gGatherInterval, that.gGatherInterval) &&
                Objects.equals(gShowLastValue, that.gShowLastValue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, gGatherInterval, gShowLastValue);
    }
}
