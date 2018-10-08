package com.gaussic.model.dcs;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "device_point_realtime", schema = "wind", catalog = "")
public class DevicePointRealtimePojo {
    private Long realTimeId;
    private DevicePointPojo devicePointPojo;
    private Timestamp rTime;
    private Float pointValue;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "realTime_id")
    public Long getRealTimeId() {
        return realTimeId;
    }

    public void setRealTimeId(Long realTimeId) {
        this.realTimeId = realTimeId;
    }


    @OneToOne
    @JoinColumn(name="device_point_id")
    public DevicePointPojo getDevicePointPojo() {
        return devicePointPojo;
    }

    public void setDevicePointPojo(DevicePointPojo devicePointPojo) {
        this.devicePointPojo = devicePointPojo;
    }

    @Basic
    @Column(name = "r_time")
    public Timestamp getrTime() {
        return rTime;
    }

    public void setrTime(Timestamp rTime) {
        this.rTime = rTime;
    }

    @Basic
    @Column(name = "point_value")
    public Float getPointValue() {
        return pointValue;
    }

    public void setPointValue(Float pointValue) {
        this.pointValue = pointValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevicePointRealtimePojo that = (DevicePointRealtimePojo) o;
        return realTimeId == that.realTimeId &&
                Objects.equals(devicePointPojo, that.devicePointPojo) &&
                Objects.equals(rTime, that.rTime) &&
                Objects.equals(pointValue, that.pointValue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(realTimeId, devicePointPojo, rTime, pointValue);
    }
}
