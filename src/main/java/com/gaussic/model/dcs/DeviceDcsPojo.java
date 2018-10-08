package com.gaussic.model.dcs;

import com.gaussic.model.dcsRemote.DcsRemotePointPojo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "device_dcs", schema = "wind", catalog = "")
public class DeviceDcsPojo {
    private Long deviceId;
    private String deviceName;
    private String deviceAddress;
    private Integer devicePort;
    private Integer deviceBoundRate;
    private Integer deviceLinkState;
    private String deviceNote;
    private String deviceNum;
    private Byte deviceFlowControlIn;
    private Byte deviceFlowControlOut;
    private Byte deviceDataBits;
    private Byte deviceStopBits;
    private Byte deviceParity;
    private List<DevicePointPojo> devicePointPojoList;
    private List<DcsRemotePointPojo> dcsRemotePointPojoList;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "device_id")
    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "device_name")
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Basic
    @Column(name = "device_address")
    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    @Basic
    @Column(name = "device_port")
    public Integer getDevicePort() {
        return devicePort;
    }

    public void setDevicePort(Integer devicePort) {
        this.devicePort = devicePort;
    }

    @Basic
    @Column(name = "device_bound_rate")
    public Integer getDeviceBoundRate() {
        return deviceBoundRate;
    }

    public void setDeviceBoundRate(Integer deviceBoundRate) {
        this.deviceBoundRate = deviceBoundRate;
    }

    @Basic
    @Column(name = "device_link_state")
    public Integer getDeviceLinkState() {
        return deviceLinkState;
    }

    public void setDeviceLinkState(Integer deviceLinkState) {
        this.deviceLinkState = deviceLinkState;
    }

    @Basic
    @Column(name = "device_note")
    public String getDeviceNote() {
        return deviceNote;
    }

    public void setDeviceNote(String deviceNote) {
        this.deviceNote = deviceNote;
    }

    @Basic
    @Column(name = "device_num")
    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    @Basic
    @Column(name = "device_flowControl_in")
    public Byte getDeviceFlowControlIn() {
        return deviceFlowControlIn;
    }

    public void setDeviceFlowControlIn(Byte deviceFlowControlIn) {
        this.deviceFlowControlIn = deviceFlowControlIn;
    }

    @Basic
    @Column(name = "device_flowControl_out")
    public Byte getDeviceFlowControlOut() {
        return deviceFlowControlOut;
    }

    public void setDeviceFlowControlOut(Byte deviceFlowControlOut) {
        this.deviceFlowControlOut = deviceFlowControlOut;
    }

    @Basic
    @Column(name = "device_dataBits")
    public Byte getDeviceDataBits() {
        return deviceDataBits;
    }

    public void setDeviceDataBits(Byte deviceDataBits) {
        this.deviceDataBits = deviceDataBits;
    }

    @Basic
    @Column(name = "device_stopBits")
    public Byte getDeviceStopBits() {
        return deviceStopBits;
    }

    public void setDeviceStopBits(Byte deviceStopBits) {
        this.deviceStopBits = deviceStopBits;
    }

    @Basic
    @Column(name = "device_parity")
    public Byte getDeviceParity() {
        return deviceParity;
    }

    public void setDeviceParity(Byte deviceParity) {
        this.deviceParity = deviceParity;
    }

    @OneToMany(mappedBy = "deviceDcsPojo")
    public List<DevicePointPojo> getDevicePointPojoList() {
        return devicePointPojoList;
    }

    public void setDevicePointPojoList(List<DevicePointPojo> devicePointPojoList) {
        this.devicePointPojoList = devicePointPojoList;
    }

    @OneToMany(mappedBy = "deviceDcsPojo")
    public List<DcsRemotePointPojo> getDcsRemotePointPojoList() {
        return dcsRemotePointPojoList;
    }

    public void setDcsRemotePointPojoList(List<DcsRemotePointPojo> dcsRemotePointPojoList) {
        this.dcsRemotePointPojoList = dcsRemotePointPojoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDcsPojo that = (DeviceDcsPojo) o;
        return deviceId.equals(that.deviceId) &&
                Objects.equals(deviceName, that.deviceName) &&
                Objects.equals(deviceAddress, that.deviceAddress) &&
                Objects.equals(devicePort, that.devicePort) &&
                Objects.equals(deviceBoundRate, that.deviceBoundRate) &&
                Objects.equals(deviceLinkState, that.deviceLinkState) &&
                Objects.equals(deviceNote, that.deviceNote) &&
                Objects.equals(deviceNum, that.deviceNum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(deviceId, deviceName, deviceAddress, devicePort, deviceBoundRate, deviceLinkState, deviceNote, deviceNum);
    }
}
