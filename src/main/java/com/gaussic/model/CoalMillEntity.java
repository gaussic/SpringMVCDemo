package com.gaussic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "coal_mill", schema = "wind", catalog = "")
public class CoalMillEntity {
    private long id;
    private String cName;
    private Timestamp cTime;
    private Float cRealValue;
    private Float cSetValue;
    private Integer cCommunicationState;
    private Integer cStartOrStopState;
    private Integer cAlarmState;
    private String cNote;

    private List<CoalPipingEntity> coalPipingEntityList;


    /**
     * 添加管道
     * @param coalPipingEntity
     * @return
     */
    public List<CoalPipingEntity> addCoalPiping(CoalPipingEntity coalPipingEntity){
        this.coalPipingEntityList = this.getCoalPipingEntityList();
        this.coalPipingEntityList.add(coalPipingEntity);
        return this.coalPipingEntityList;
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "c_name")
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
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
    @Column(name = "c_real_value")
    public Float getcRealValue() {
        return cRealValue;
    }

    public void setcRealValue(Float cRealValue) {
        this.cRealValue = cRealValue;
    }

    @Basic
    @Column(name = "c_set_value")
    public Float getcSetValue() {
        return cSetValue;
    }

    public void setcSetValue(Float cSetValue) {
        this.cSetValue = cSetValue;
    }

    @Basic
    @Column(name = "c_communication_state")
    public Integer getcCommunicationState() {
        return cCommunicationState;
    }

    public void setcCommunicationState(Integer cCommunicationState) {
        this.cCommunicationState = cCommunicationState;
    }

    @Basic
    @Column(name = "c_startOrStop_state")
    public Integer getcStartOrStopState() {
        return cStartOrStopState;
    }

    public void setcStartOrStopState(Integer cStartOrStopState) {
        this.cStartOrStopState = cStartOrStopState;
    }

    @Basic
    @Column(name = "c_alarm_state")
    public Integer getcAlarmState() {
        return cAlarmState;
    }

    public void setcAlarmState(Integer cAlarmState) {
        this.cAlarmState = cAlarmState;
    }

    @Basic
    @Column(name = "c_note")
    public String getcNote() {
        return cNote;
    }

    public void setcNote(String cNote) {
        this.cNote = cNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoalMillEntity that = (CoalMillEntity) o;
        return id == that.id &&
                Objects.equals(cName, that.cName) &&
                Objects.equals(cTime, that.cTime) &&
                Objects.equals(cRealValue, that.cRealValue) &&
                Objects.equals(cSetValue, that.cSetValue) &&
                Objects.equals(cCommunicationState, that.cCommunicationState) &&
                Objects.equals(cStartOrStopState, that.cStartOrStopState) &&
                Objects.equals(cAlarmState, that.cAlarmState) &&
                Objects.equals(cNote, that.cNote);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, cName, cTime, cRealValue, cSetValue, cCommunicationState, cStartOrStopState, cAlarmState, cNote);
    }

//    @Transient
    @OneToMany(mappedBy = "coalMillEntity")
    public List<CoalPipingEntity> getCoalPipingEntityList() {
//        if(null == coalPipingEntityList) {
////            coalPipingEntityList = new ArrayList<CoalPipingEntity>();
////        }
        return coalPipingEntityList;
    }

    public void setCoalPipingEntityList(List<CoalPipingEntity> coalPipingEntityList) {
        this.coalPipingEntityList = coalPipingEntityList;
    }
}
