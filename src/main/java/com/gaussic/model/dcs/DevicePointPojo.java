package com.gaussic.model.dcs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "device_point", schema = "wind", catalog = "")
public class DevicePointPojo {
    private Long pointId;


    private DeviceDcsPojo deviceDcsPojo;
    //可编辑
    private String pointName;
    private String pointAddress;
    private String pointNote;
    private String pointHistoryDeviceTableName;
    private String pointHistoryColumnName;
    private String pointHistoryPorpertyName;
    private Integer pointHistoryOffset;

    private DevicePointRealtimePojo devicePointRealtimePojo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "point_id")
    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }



    @Basic
    @Column(name = "point_name")
    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    @Basic
    @Column(name = "point_address")
    public String getPointAddress() {
        return pointAddress;
    }

    public void setPointAddress(String pointAddress) {
        this.pointAddress = pointAddress;
    }

    @Basic
    @Column(name = "point_note")
    public String getPointNote() {
        return pointNote;
    }

    public void setPointNote(String pointNote) {
        this.pointNote = pointNote;
    }

    @Basic
    @Column(name = "point_history_device_table_name")
    public String getPointHistoryDeviceTableName() {
        return pointHistoryDeviceTableName;
    }

    public void setPointHistoryDeviceTableName(String pointHistoryDeviceTableName) {
        this.pointHistoryDeviceTableName = pointHistoryDeviceTableName;
    }

    @Basic
    @Column(name = "point_history_column_name")
    public String getPointHistoryColumnName() {
        return pointHistoryColumnName;
    }

    public void setPointHistoryColumnName(String pointHistoryColumnName) {
        this.pointHistoryColumnName = pointHistoryColumnName;
    }

    @Basic
    @Column(name = "point_history_porperty_name")
    public String getPointHistoryPorpertyName() {
        return pointHistoryPorpertyName;
    }

    public void setPointHistoryPorpertyName(String pointHistoryPorpertyName) {
        this.pointHistoryPorpertyName = pointHistoryPorpertyName;
    }

    @Basic
    @Column(name = "point_history_offset")
    public Integer getPointHistoryOffset() {
        return pointHistoryOffset;
    }

    public void setPointHistoryOffset(Integer pointHistoryOffset) {
        this.pointHistoryOffset = pointHistoryOffset;
    }

    @ManyToOne
    @JoinColumn(name="device_dcs_id")
    public DeviceDcsPojo getDeviceDcsPojo() {
        return deviceDcsPojo;
    }

    public void setDeviceDcsPojo(DeviceDcsPojo deviceDcsPojo) {
        this.deviceDcsPojo = deviceDcsPojo;
    }
    @OneToOne(mappedBy = "devicePointPojo")
    public DevicePointRealtimePojo getDevicePointRealtimePojo() {
        return devicePointRealtimePojo;
    }

    public void setDevicePointRealtimePojo(DevicePointRealtimePojo devicePointRealtimePojo) {
        this.devicePointRealtimePojo = devicePointRealtimePojo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevicePointPojo that = (DevicePointPojo) o;
        return pointId == that.pointId &&
                Objects.equals(deviceDcsPojo, that.deviceDcsPojo) &&
                Objects.equals(pointName, that.pointName) &&
                Objects.equals(pointAddress, that.pointAddress) &&
                Objects.equals(pointNote, that.pointNote) &&
                Objects.equals(pointHistoryDeviceTableName, that.pointHistoryDeviceTableName) &&
                Objects.equals(pointHistoryColumnName, that.pointHistoryColumnName) &&
                Objects.equals(pointHistoryPorpertyName, that.pointHistoryPorpertyName) &&
                Objects.equals(pointHistoryOffset, that.pointHistoryOffset);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pointId, deviceDcsPojo, pointName, pointAddress, pointNote, pointHistoryDeviceTableName, pointHistoryColumnName, pointHistoryPorpertyName, pointHistoryOffset);
    }
}
