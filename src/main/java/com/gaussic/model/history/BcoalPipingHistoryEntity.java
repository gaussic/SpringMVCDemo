package com.gaussic.model.history;

import com.gaussic.model.CoalMillEntity;
import com.gaussic.model.CoalPipingEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bcoal_piping_history", schema = "wind", catalog = "")
public class BcoalPipingHistoryEntity implements  CoalPipingHistory{
    private long id;
    private Timestamp hTime;
    private Float hPipeAVelocity;
    private Float hPipeBVelocity;
    private Float hPipeCVelocity;
    private Float hPipeDVelocity;
    private Float hPipeADencity;
    private Float hPipeBDencity;
    private Float hPipeCDencity;
    private Float hPipeDDencity;
    private Long hCoalMillId;

    public BcoalPipingHistoryEntity(CoalMillEntity coalMillEntity, Date now) {
        this.hTime = new Timestamp(now.getTime());
        this.hCoalMillId = coalMillEntity.getId();
    }
    public  BcoalPipingHistoryEntity(   ){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "h_time")
    public Timestamp gethTime() {
        return hTime;
    }

    public void sethTime(Timestamp hTime) {
        this.hTime = hTime;
    }

    @Basic
    @Column(name = "h_pipeA_Velocity")
    public Float gethPipeAVelocity() {
        return hPipeAVelocity;
    }

    public void sethPipeAVelocity(Float hPipeAVelocity) {
        this.hPipeAVelocity = hPipeAVelocity;
    }

    @Basic
    @Column(name = "h_pipeB_Velocity")
    public Float gethPipeBVelocity() {
        return hPipeBVelocity;
    }

    public void sethPipeBVelocity(Float hPipeBVelocity) {
        this.hPipeBVelocity = hPipeBVelocity;
    }

    @Basic
    @Column(name = "h_pipeC_velocity")
    public Float gethPipeCVelocity() {
        return hPipeCVelocity;
    }

    public void sethPipeCVelocity(Float hPipeCVelocity) {
        this.hPipeCVelocity = hPipeCVelocity;
    }

    @Basic
    @Column(name = "h_pipeD_velocity")
    public Float gethPipeDVelocity() {
        return hPipeDVelocity;
    }

    public void sethPipeDVelocity(Float hPipeDVelocity) {
        this.hPipeDVelocity = hPipeDVelocity;
    }

    @Basic
    @Column(name = "h_pipeA_dencity")
    public Float gethPipeADencity() {
        return hPipeADencity;
    }

    public void sethPipeADencity(Float hPipeADencity) {
        this.hPipeADencity = hPipeADencity;
    }

    @Basic
    @Column(name = "h_pipeB_dencity")
    public Float gethPipeBDencity() {
        return hPipeBDencity;
    }

    public void sethPipeBDencity(Float hPipeBDencity) {
        this.hPipeBDencity = hPipeBDencity;
    }

    @Basic
    @Column(name = "h_pipeC_dencity")
    public Float gethPipeCDencity() {
        return hPipeCDencity;
    }

    public void sethPipeCDencity(Float hPipeCDencity) {
        this.hPipeCDencity = hPipeCDencity;
    }

    @Basic
    @Column(name = "h_pipeD_dencity")
    public Float gethPipeDDencity() {
        return hPipeDDencity;
    }

    public void sethPipeDDencity(Float hPipeDDencity) {
        this.hPipeDDencity = hPipeDDencity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BcoalPipingHistoryEntity that = (BcoalPipingHistoryEntity) o;
        return id == that.id &&
                Objects.equals(hTime, that.hTime) &&
                Objects.equals(hPipeAVelocity, that.hPipeAVelocity) &&
                Objects.equals(hPipeBVelocity, that.hPipeBVelocity) &&
                Objects.equals(hPipeCVelocity, that.hPipeCVelocity) &&
                Objects.equals(hPipeDVelocity, that.hPipeDVelocity) &&
                Objects.equals(hPipeADencity, that.hPipeADencity) &&
                Objects.equals(hPipeBDencity, that.hPipeBDencity) &&
                Objects.equals(hPipeCDencity, that.hPipeCDencity) &&
                Objects.equals(hPipeDDencity, that.hPipeDDencity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, hTime, hPipeAVelocity, hPipeBVelocity, hPipeCVelocity, hPipeDVelocity, hPipeADencity, hPipeBDencity, hPipeCDencity, hPipeDDencity);
    }

    @Basic
    @Column(name = "h_coal_mill_id")
    public Long gethCoalMillId() {
        return hCoalMillId;
    }

    public void sethCoalMillId(Long hCoalMillId) {
        this.hCoalMillId = hCoalMillId;
    }
}
