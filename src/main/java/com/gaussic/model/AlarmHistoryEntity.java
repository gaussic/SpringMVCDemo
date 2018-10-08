package com.gaussic.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "alarm_history", schema = "wind", catalog = "")
public class AlarmHistoryEntity implements Serializable {
    /**
     * 通讯中断告警
     */
    public static int ALARM_TYPE_COMMUNICATION_PIPE = 10;
    public static int ALARM_TYPE_COMMUNICATION_DCS = 11;
    public static int ALARM_TYPE_DENSITY = 2;
    public static int ALARM_TYPE_DENSITY_LEVEL1 = 21;
    public static int ALARM_TYPE_DENSITY_LEVEL2 = 22;
    public static int ALARM_TYPE_DENSITY_LEVEL3 = 23;
    public static int ALARM_TYPE_VELOCITY = 3;
    public static int ALARM_TYPE_VELOCITY_LEVEL2 = 31;
    public static int ALARM_TYPE_VELOCITY_LEVEL3 = 32;
    public static int ALARM_TYPE_VELOCITY_LEVEL4 = 33;

    public static int ALARM_TYPE_DENSITY_4PIPE = 4;
    public static int ALARM_TYPE_DENSITY_4PIPE_LEVEL1 = 41;
    public static int ALARM_TYPE_DENSITY_4PIPE_LEVEL2 = 42;
    public static int ALARM_TYPE_DENSITY_4PIPE_LEVEL3 = 43;

    public static int ALARM_TYPE_VELOCITY_4PIPE = 5;
    public static int ALARM_TYPE_VELOCITY_4PIPE_LEVEL2 = 51;
    public static int ALARM_TYPE_VELOCITY_4PIPE_LEVEL3 = 52;
    public static int ALARM_TYPE_VELOCITY_4PIPE_LEVEL4 = 53;

    public static int ALARM_STATE_NORMAL = 0;
    public static int ALARM_STATE_ERROR = 1;

    /**
     * 设备类型，管道类型
     */
    public static int DEVICE_TYPE_PIPE = 1;
    /**
     * 设备类型，DCS类型
     */
    public static int DEVICE_TYPE_DCS  =2 ;




    private long id;
    private Long aDeviceId;
    private Integer aDeviceType;
    private String aDeviceName;
    private Timestamp aAlarmTime;
    private Integer aAlarmType;
    private Timestamp aReAlarmTime;

    private String aAlarmNote;
    private Long aAlarmMillId;
    private String aAlarmMillName;
    private Long aAlarmDcsId;
    private String aAlarmDcsName;
    private Long aAlarmPipeId;
    private String aAlarmPipeString;
    private Long aAlarmManagerId;
    private String aAlarmManagerName;
    private Integer aAlarmState;

    private String aAlarmReAlarmNote;




    private Timestamp aConfirmTime;
    private Boolean aConfirmFlag;

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
    @Column(name = "a_device_id")
    public Long getaDeviceId() {
        return aDeviceId;
    }

    public void setaDeviceId(Long aDeviceId) {
        this.aDeviceId = aDeviceId;
    }

    @Basic
    @Column(name = "a_device_type")
    public Integer getaDeviceType() {
        return aDeviceType;
    }

    public void setaDeviceType(Integer aDeviceType) {
        this.aDeviceType = aDeviceType;
    }

    @Basic
    @Column(name = "a_device_name")
    public String getaDeviceName() {
        return aDeviceName;
    }

    public void setaDeviceName(String aDeviceName) {
        this.aDeviceName = aDeviceName;
    }

    @Basic
    @Column(name = "a_alarm_time")
    public Timestamp getaAlarmTime() {
        return aAlarmTime;
    }

    public void setaAlarmTime(Timestamp aAlarmTime) {
        this.aAlarmTime = aAlarmTime;
    }

    @Basic
    @Column(name = "a_alarm_type")
    public Integer getaAlarmType() {
        return aAlarmType;
    }

    public void setaAlarmType(Integer aAlarmType) {
        this.aAlarmType = aAlarmType;
    }

    @Basic
    @Column(name = "a_re_alarm_time")
    public Timestamp getaReAlarmTime() {
        return aReAlarmTime;
    }

    public void setaReAlarmTime(Timestamp aReAlarmTime) {
        this.aReAlarmTime = aReAlarmTime;
    }

    @Basic
    @Column(name = "a_alarm_note")
    public String getaAlarmNote() {
        return aAlarmNote;
    }

    public void setaAlarmNote(String aAlarmNote) {
        this.aAlarmNote = aAlarmNote;
    }
    @Basic
    @Column(name = "a_alarm_mill_id")
    public Long getaAlarmMillId() {
        return aAlarmMillId;
    }

    public void setaAlarmMillId(Long aAlarmMillId) {
        this.aAlarmMillId = aAlarmMillId;
    }
    @Basic
    @Column(name = "a_alarm_mill_name")
    public String getaAlarmMillName() {
        return aAlarmMillName;
    }

    public void setaAlarmMillName(String aAlarmMillName) {
        this.aAlarmMillName = aAlarmMillName;
    }
    @Basic
    @Column(name = "a_alarm_dcs_id")
    public Long getaAlarmDcsId() {
        return aAlarmDcsId;
    }

    public void setaAlarmDcsId(Long aAlarmDcsId) {
        this.aAlarmDcsId = aAlarmDcsId;
    }
    @Basic
    @Column(name = "a_alarm_dcs_name")
    public String getaAlarmDcsName() {
        return aAlarmDcsName;
    }

    public void setaAlarmDcsName(String aAlarmDcsName) {
        this.aAlarmDcsName = aAlarmDcsName;
    }
    @Basic
    @Column(name = "a_alarm_pipe_id")
    public Long getaAlarmPipeId() {
        return aAlarmPipeId;
    }

    public void setaAlarmPipeId(Long aAlarmPipeId) {
        this.aAlarmPipeId = aAlarmPipeId;
    }
    @Basic
    @Column(name = "a_alarm_pipe_name")
    public String getaAlarmPipeString() {
        return aAlarmPipeString;
    }

    public void setaAlarmPipeString(String aAlarmPipeString) {
        this.aAlarmPipeString = aAlarmPipeString;
    }
    @Basic
    @Column(name = "a_alarm_manager_id")
    public Long getaAlarmManagerId() {
        return aAlarmManagerId;
    }

    public void setaAlarmManagerId(Long aAlarmManagerId) {
        this.aAlarmManagerId = aAlarmManagerId;
    }
    @Basic
    @Column(name = "a_alarm_manager_name")
    public String getaAlarmManagerName() {
        return aAlarmManagerName;
    }

    public void setaAlarmManagerName(String aAlarmManagerName) {
        this.aAlarmManagerName = aAlarmManagerName;
    }
    @Basic
    @Column(name = "a_alarm_state")
    public Integer getaAlarmState() {
        return aAlarmState;
    }

    public void setaAlarmState(Integer aAlarmState) {
        this.aAlarmState = aAlarmState;
    }

    @Basic
    @Column(name = "a_alarm_re_alarm_note")
    public String getaAlarmReAlarmNote() {
        return aAlarmReAlarmNote;
    }

    public void setaAlarmReAlarmNote(String aAlarmReAlarmNote) {
        this.aAlarmReAlarmNote = aAlarmReAlarmNote;
    }
    @Basic
    @Column(name = "a_confirm_time")
    public Timestamp getaConfirmTime() {
        return aConfirmTime;
    }

    public void setaConfirmTime(Timestamp aConfirmTime) {
        this.aConfirmTime = aConfirmTime;
    }
    @Basic
    @Column(name="a_confirm_flag" ,nullable = false, columnDefinition = "TINYINT(1)")
    public Boolean getaConfirmFlag() {
        return aConfirmFlag;
    }

    public void setaConfirmFlag(Boolean aConfirmFlag) {
        this.aConfirmFlag = aConfirmFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlarmHistoryEntity that = (AlarmHistoryEntity) o;
        return id == that.id &&
                Objects.equals(aDeviceId, that.aDeviceId) &&
                Objects.equals(aDeviceType, that.aDeviceType) &&
                Objects.equals(aDeviceName, that.aDeviceName) &&
                Objects.equals(aAlarmTime, that.aAlarmTime) &&
                Objects.equals(aAlarmType, that.aAlarmType) &&
                Objects.equals(aReAlarmTime, that.aReAlarmTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, aDeviceId, aDeviceType, aDeviceName, aAlarmTime, aAlarmType, aReAlarmTime);
    }
    @Transient
    public String getAlarmTypeString() {
        Integer type = this.getaAlarmType();
        String alarmTypeString = "";
        if(null != type){
            switch (type.intValue()){
                case 10:alarmTypeString="管道通讯中断";break;
                case 11:alarmTypeString="DCS通讯中断";break;
                case 2:alarmTypeString="浓度偏差告警";break;
                case 21:alarmTypeString="浓度偏差告警(一级)";break;
                case 22:alarmTypeString="浓度偏差告警(二级)";break;
                case 23:alarmTypeString="浓度偏差告警(三级)";break;
                case 3:alarmTypeString="风速偏差告警";break;
                case 31:alarmTypeString="风速偏差告警(一级)";break;
                case 32:alarmTypeString="风速偏差告警(二级)";break;
                case 33:alarmTypeString="风速偏差告警(三级)";break;
                case 4:alarmTypeString="磨煤机4根粉管浓度偏差告警";break;
                case 41:alarmTypeString="磨煤机4根粉管浓度偏差告警(一级)";break;
                case 42:alarmTypeString="磨煤机4根粉管浓度偏差告警(二级)";break;
                case 43:alarmTypeString="磨煤机4根粉管浓度偏差告警(三级)";break;
                case 5:alarmTypeString="磨煤机4台磨风速偏差告警";break;
                case 51:alarmTypeString="磨煤机4台磨风速偏差告警(一级)";break;
                case 52:alarmTypeString="磨煤机4台磨风速偏差告警(二级)";break;
                case 53:alarmTypeString="磨煤机4台磨风速偏差告警(三级)";break;
                default :alarmTypeString = "";break;
            }
        }
        return alarmTypeString;
    }
    @Transient
    public String getaAlarmStateString() {
        Integer state = this.getaAlarmState();
        String aAlarmStateString = "";
        if(null != state){
            if(state.intValue() == 0){
                aAlarmStateString = "正常";
            }else {
                aAlarmStateString = "告警中";
            }
        }
        return aAlarmStateString;
    }
    @Transient
    public String getaConfirmString() {
        //todo 对于通讯中断的告警，不需要进行确认，需要进行确认的告警只有偏差告警
        //TODO 告警确认需要登录成功以后，才可进行告警确认
        //TODO 告警确认需要记录告警确认的时间与人员
        String aConfirmString = "";
        Boolean flag = this.getaConfirmFlag();
        if(null != flag){
            if(flag){
                aConfirmString ="已确认";
            }else{
                aConfirmString= "待确认";
            }
        }
        return aConfirmString;
    }
}
