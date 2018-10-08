package com.gaussic.model.dcsRemote;

import com.gaussic.model.CoalPipingEntity;
import com.gaussic.model.dcs.DeviceDcsPojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName DcsRemotePointPojo
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/9/3 15:13
 * @Version 1.0
 */
@Entity
@Table(name = "dcs_remote_point", schema = "wind")
public class DcsRemotePointPojo {
    private Integer dcsRemotePointId;

//    private Integer dcsDeviceId;
    private DeviceDcsPojo deviceDcsPojo;
    //可编辑
    private String address;
    private String note;
    private Integer densityOrVelocity;
    private Integer slaveId;
    private String remotePointName;


    private Timestamp currTime;
    private Float currentValue;
//    private Integer coalPipingId;
    private CoalPipingEntity coalPipingEntity;


    @Id
    @Column(name = "dcs_remote_point_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getDcsRemotePointId() {
        return dcsRemotePointId;
    }

    public void setDcsRemotePointId(Integer dcsRemotePointId) {
        this.dcsRemotePointId = dcsRemotePointId;
    }

    @Basic
    @Column(name = "remote_point_name")
    public String getRemotePointName() {
        return remotePointName;
    }

    public void setRemotePointName(String remotePointName) {
        this.remotePointName = remotePointName;
    }






    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "curr_time")
    public Timestamp getCurrTime() {
        return currTime;
    }

    public void setCurrTime(Timestamp currTime) {
        this.currTime = currTime;
    }

    @Basic
    @Column(name = "current_value")
    public Float getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Float currentValue) {
        this.currentValue = currentValue;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @ManyToOne
    @JoinColumn(name = "coal_piping_id")
    public CoalPipingEntity getCoalPipingEntity() {
        return coalPipingEntity;
    }

    public void setCoalPipingEntity(CoalPipingEntity coalPipingEntity) {
        this.coalPipingEntity = coalPipingEntity;
    }

    @ManyToOne
    @JoinColumn(name = "dcs_device_id")
    public DeviceDcsPojo getDeviceDcsPojo() {
        return deviceDcsPojo;
    }

    public void setDeviceDcsPojo(DeviceDcsPojo deviceDcsPojo) {
        this.deviceDcsPojo = deviceDcsPojo;
    }
    @Basic
    @Column(name="density_or_velocity")
    public Integer getDensityOrVelocity() {
        return densityOrVelocity;
    }

    public void setDensityOrVelocity(Integer densityOrVelocity) {
        this.densityOrVelocity = densityOrVelocity;
    }
    @Basic
    @Column(name="slave_id")
    public Integer getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(Integer slaveId) {
        this.slaveId = slaveId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DcsRemotePointPojo that = (DcsRemotePointPojo) o;
        return dcsRemotePointId == that.dcsRemotePointId &&
                Objects.equals(remotePointName, that.remotePointName) &&
                Objects.equals(deviceDcsPojo, that.deviceDcsPojo) &&
                Objects.equals(address, that.address) &&
                Objects.equals(currTime, that.currTime) &&
                Objects.equals(currentValue, that.currentValue) &&
                Objects.equals(note, that.note) &&
                Objects.equals(coalPipingEntity, that.coalPipingEntity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dcsRemotePointId, remotePointName, deviceDcsPojo, address, currTime, currentValue, note,
                coalPipingEntity);
    }
}
