package com.gaussic.model;

import com.gaussic.model.dcsRemote.DcsRemotePointPojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "coal_piping", schema = "wind", catalog = "")
public class CoalPipingEntity {
    private long id;
    private String pName;
    private Long pCoalMillId;
    private Long pCoalPipingSetId;
    private Timestamp pTime;
    private String pLocation;
    private Float pVelocity;
    private Float pDencity;
    private Integer pCommunicationState;
    private Integer pStartOrStopState;
    private Integer pAlarmState;
    private String pNote;
    private String pNameUserDefined;
    private String pNumUserDefined;
    private String pLocationUserDefined;


    private CoalPipingSetEntity coalPipingSetEntity;
    private CoalMillEntity coalMillEntity;


    private AlarmHistoryEntity alarmHistoryEntity;
    private List<DcsRemotePointPojo> dcsRemotePointPojoList;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "p_name")
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    /*@Basic
    @Column(name = "p_coalMill_id")*/
    @Transient
    public Long getpCoalMillId() {
        if (null != this.getCoalMillEntity())
            return this.getCoalMillEntity().getId();
        return pCoalMillId;
    }

    public void setpCoalMillId(Long pCoalMillId) {
        this.pCoalMillId = pCoalMillId;
    }

    @Basic
    @Column(name = "p_coalPiping_set_id")
    public Long getpCoalPipingSetId() {
        return pCoalPipingSetId;
    }

    public void setpCoalPipingSetId(Long pCoalPipingSetId) {
        this.pCoalPipingSetId = pCoalPipingSetId;
    }

    @Basic
    @Column(name = "p_time")
    public Timestamp getpTime() {
        return pTime;
    }

    public void setpTime(Timestamp pTime) {
        this.pTime = pTime;
    }

    @Basic
    @Column(name = "p_location")
    public String getpLocation() {
        return pLocation;
    }

    public void setpLocation(String pLocation) {
        this.pLocation = pLocation;
    }

    @Basic
    @Column(name = "p_velocity")
    public Float getpVelocity() {
        if(null != this.pVelocity){
            BigDecimal bigDecimal = new BigDecimal(this.pVelocity);
            BigDecimal bigDecimal1 = bigDecimal.setScale(6,BigDecimal.ROUND_FLOOR);
            this.pVelocity = bigDecimal1.floatValue();
        }
        return Optional.ofNullable(pVelocity).orElse(0f);
    }

    public void setpVelocity(Float pVelocity) {
        this.pVelocity = pVelocity;
    }

    @Basic
    @Column(name = "p_dencity")
    public Float getpDencity() {
        if(null != this.pDencity){
            BigDecimal bigDecimal = new BigDecimal(this.pDencity);
            BigDecimal bigDecimal1 = bigDecimal.setScale(6,BigDecimal.ROUND_FLOOR);
            this.pDencity = bigDecimal1.floatValue();
        }
        return Optional.ofNullable(this.pDencity).orElse(0f);
    }

    public void setpDencity(Float pDencity) {
        this.pDencity = pDencity;
    }

    @Basic
    @Column(name = "p_communication_state")
    public Integer getpCommunicationState() {
        return pCommunicationState;
    }

    public void setpCommunicationState(Integer pCommunicationState) {
        this.pCommunicationState = pCommunicationState;
    }

    @Basic
    @Column(name = "p_startOrStop_state")
    public Integer getpStartOrStopState() {
        return pStartOrStopState;
    }

    public void setpStartOrStopState(Integer pStartOrStopState) {
        this.pStartOrStopState = pStartOrStopState;
    }

    @Basic
    @Column(name = "p_alarm_state")
    public Integer getpAlarmState() {
        return pAlarmState;
    }

    public void setpAlarmState(Integer pAlarmState) {
        this.pAlarmState = pAlarmState;
    }

    @Basic
    @Column(name = "p_note")
    public String getpNote() {
        return pNote;
    }

    public void setpNote(String pNote) {
        this.pNote = pNote;
    }

    @Basic
    @Column(name = "p_name_user_defined")
    public String getpNameUserDefined() {
        return pNameUserDefined;
    }

    public void setpNameUserDefined(String pNameUserDefined) {
        this.pNameUserDefined = pNameUserDefined;
    }

    @Basic
    @Column(name = "p_num_user_defined")
    public String getpNumUserDefined() {
        return pNumUserDefined;
    }

    public void setpNumUserDefined(String pNumUserDefined) {
        this.pNumUserDefined = pNumUserDefined;
    }

    @Basic
    @Column(name = "p_location_user_defined")
    public String getpLocationUserDefined() {
        return pLocationUserDefined;
    }

    public void setpLocationUserDefined(String pLocationUserDefined) {
        this.pLocationUserDefined = pLocationUserDefined;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoalPipingEntity that = (CoalPipingEntity) o;
        return id == that.id &&
                Objects.equals(pName, that.pName) &&
                Objects.equals(pCoalMillId, that.pCoalMillId) &&
                Objects.equals(pCoalPipingSetId, that.pCoalPipingSetId) &&
                Objects.equals(pTime, that.pTime) &&
                Objects.equals(pLocation, that.pLocation) &&
                Objects.equals(pVelocity, that.pVelocity) &&
                Objects.equals(pDencity, that.pDencity) &&
                Objects.equals(pCommunicationState, that.pCommunicationState) &&
                Objects.equals(pStartOrStopState, that.pStartOrStopState) &&
                Objects.equals(pAlarmState, that.pAlarmState) &&
                Objects.equals(pNote, that.pNote) &&
                Objects.equals(pNameUserDefined, that.pNameUserDefined) &&
                Objects.equals(pNumUserDefined, that.pNumUserDefined);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, pName, pCoalMillId, pCoalPipingSetId, pTime, pLocation, pVelocity, pDencity, pCommunicationState, pStartOrStopState, pAlarmState, pNote, pNameUserDefined, pNumUserDefined);
    }

    /* @OneToOne
     @JoinColumn(name="coalPipingId")*/
/*   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name="coalPiping_id")*/
//    @OneToOne(mappedBy = "coalPipingEntity")
//    @Transient
    @OneToOne(mappedBy = "coalPipingEntity")
    public CoalPipingSetEntity getCoalPipingSetEntity() {
        return coalPipingSetEntity;
    }

    public void setCoalPipingSetEntity(CoalPipingSetEntity coalPipingSetEntity) {
        this.coalPipingSetEntity = coalPipingSetEntity;
    }

    //    @Transient
    @ManyToOne
    @JoinColumn(name = "p_coalMill_id")
    public CoalMillEntity getCoalMillEntity() {
        return coalMillEntity;
    }

    public void setCoalMillEntity(CoalMillEntity coalMillEntity) {
        this.coalMillEntity = coalMillEntity;
    }

    @OneToMany(mappedBy = "coalPipingEntity")
    public List<DcsRemotePointPojo> getDcsRemotePointPojoList() {
        return dcsRemotePointPojoList;
    }

    public void setDcsRemotePointPojoList(List<DcsRemotePointPojo> dcsRemotePointPojoList) {
        this.dcsRemotePointPojoList = dcsRemotePointPojoList;
    }




    @Transient
    public AlarmHistoryEntity getAlarmHistoryEntity() {
        return alarmHistoryEntity;
    }

    public void setAlarmHistoryEntity(AlarmHistoryEntity alarmHistoryEntity) {
        this.alarmHistoryEntity = alarmHistoryEntity;
    }
}
