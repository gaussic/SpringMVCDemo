package com.gaussic.model.dcs;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "device_point_history2", schema = "wind", catalog = "")
public class DevicePointHistory2Pojo {
    public static String TABLE_NAME = "device_point_history2";
    private Long historyId;
    private Timestamp hTime;
    private Float param1;
    private Float param2;
    private Float param3;
    private Float param4;
    private Float param5;
    private Float param6;
    private Float param7;
    private Float param8;
    private Float param9;
    private Float param10;
    private Float param11;
    private Float param12;
    private Float param13;
    private Float param14;
    private Float param15;
    private Float param16;
    private Float param17;
    private Float param18;
    private Float param19;
    private Float param20;
    public DevicePointHistory2Pojo(LocalDateTime localDateTime){
        this.hTime = Timestamp.valueOf(localDateTime);
    }
    public DevicePointHistory2Pojo(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id")
    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
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
    @Column(name = "param_1")
    public Float getParam1() {
        return param1;
    }

    public void setParam1(Float param1) {
        this.param1 = param1;
    }

    @Basic
    @Column(name = "param_2")
    public Float getParam2() {
        return param2;
    }

    public void setParam2(Float param2) {
        this.param2 = param2;
    }

    @Basic
    @Column(name = "param_3")
    public Float getParam3() {
        return param3;
    }

    public void setParam3(Float param3) {
        this.param3 = param3;
    }

    @Basic
    @Column(name = "param_4")
    public Float getParam4() {
        return param4;
    }

    public void setParam4(Float param4) {
        this.param4 = param4;
    }

    @Basic
    @Column(name = "param_5")
    public Float getParam5() {
        return param5;
    }

    public void setParam5(Float param5) {
        this.param5 = param5;
    }

    @Basic
    @Column(name = "param_6")
    public Float getParam6() {
        return param6;
    }

    public void setParam6(Float param6) {
        this.param6 = param6;
    }

    @Basic
    @Column(name = "param_7")
    public Float getParam7() {
        return param7;
    }

    public void setParam7(Float param7) {
        this.param7 = param7;
    }

    @Basic
    @Column(name = "param_8")
    public Float getParam8() {
        return param8;
    }

    public void setParam8(Float param8) {
        this.param8 = param8;
    }

    @Basic
    @Column(name = "param_9")
    public Float getParam9() {
        return param9;
    }

    public void setParam9(Float param9) {
        this.param9 = param9;
    }

    @Basic
    @Column(name = "param_10")
    public Float getParam10() {
        return param10;
    }

    public void setParam10(Float param10) {
        this.param10 = param10;
    }

    @Basic
    @Column(name = "param_11")
    public Float getParam11() {
        return param11;
    }

    public void setParam11(Float param11) {
        this.param11 = param11;
    }

    @Basic
    @Column(name = "param_12")
    public Float getParam12() {
        return param12;
    }

    public void setParam12(Float param12) {
        this.param12 = param12;
    }

    @Basic
    @Column(name = "param_13")
    public Float getParam13() {
        return param13;
    }

    public void setParam13(Float param13) {
        this.param13 = param13;
    }

    @Basic
    @Column(name = "param_14")
    public Float getParam14() {
        return param14;
    }

    public void setParam14(Float param14) {
        this.param14 = param14;
    }

    @Basic
    @Column(name = "param_15")
    public Float getParam15() {
        return param15;
    }

    public void setParam15(Float param15) {
        this.param15 = param15;
    }

    @Basic
    @Column(name = "param_16")
    public Float getParam16() {
        return param16;
    }

    public void setParam16(Float param16) {
        this.param16 = param16;
    }

    @Basic
    @Column(name = "param_17")
    public Float getParam17() {
        return param17;
    }

    public void setParam17(Float param17) {
        this.param17 = param17;
    }

    @Basic
    @Column(name = "param_18")
    public Float getParam18() {
        return param18;
    }

    public void setParam18(Float param18) {
        this.param18 = param18;
    }

    @Basic
    @Column(name = "param_19")
    public Float getParam19() {
        return param19;
    }

    public void setParam19(Float param19) {
        this.param19 = param19;
    }

    @Basic
    @Column(name = "param_20")
    public Float getParam20() {
        return param20;
    }

    public void setParam20(Float param20) {
        this.param20 = param20;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevicePointHistory2Pojo that = (DevicePointHistory2Pojo) o;
        return historyId == that.historyId &&
                Objects.equals(hTime, that.hTime) &&
                Objects.equals(param1, that.param1) &&
                Objects.equals(param2, that.param2) &&
                Objects.equals(param3, that.param3) &&
                Objects.equals(param4, that.param4) &&
                Objects.equals(param5, that.param5) &&
                Objects.equals(param6, that.param6) &&
                Objects.equals(param7, that.param7) &&
                Objects.equals(param8, that.param8) &&
                Objects.equals(param9, that.param9) &&
                Objects.equals(param10, that.param10) &&
                Objects.equals(param11, that.param11) &&
                Objects.equals(param12, that.param12) &&
                Objects.equals(param13, that.param13) &&
                Objects.equals(param14, that.param14) &&
                Objects.equals(param15, that.param15) &&
                Objects.equals(param16, that.param16) &&
                Objects.equals(param17, that.param17) &&
                Objects.equals(param18, that.param18) &&
                Objects.equals(param19, that.param19) &&
                Objects.equals(param20, that.param20);
    }

    @Override
    public int hashCode() {

        return Objects.hash(historyId, hTime, param1, param2, param3, param4, param5, param6, param7, param8, param9, param10, param11, param12, param13, param14, param15, param16, param17, param18, param19, param20);
    }
}
