package com.gaussic.model.history;

import org.hibernate.cfg.annotations.HCANNHelper;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

public abstract class CoalPipingHistory {
    private Long id;
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

    public abstract Long getId();

    public void setId(Long id) {
        this.id = id;
    }


    public abstract Timestamp gethTime();

    public void sethTime(Timestamp hTime) {
        this.hTime = hTime;
    }


    public abstract Float gethPipeAVelocity();

    public Float getPipeAVelocityNotNull() {
        Optional<Float> optionalFloat = Optional.ofNullable(this.gethPipeAVelocity());
        return optionalFloat.orElse(0f);
    }

    public Float getPipeBVelocityNotNull() {
        Optional<Float> optionalFloat = Optional.ofNullable(this.gethPipeBVelocity());
        return optionalFloat.orElse(0f);
    }

    public Float getPipeCVelocityNotNull() {
        Optional<Float> optionalFloat = Optional.ofNullable(this.gethPipeCVelocity());
        return optionalFloat.orElse(0f);
    }

    public Float getPipeDVelocityNotNull() {
        Optional<Float> optionalFloat = Optional.ofNullable(this.gethPipeDVelocity());
        return optionalFloat.orElse(0f);
    }


    public Float getPipeADensityNotNull() {
        Optional<Float> optionalFloat = Optional.ofNullable(this.gethPipeADencity());
        return optionalFloat.orElse(0f);
    }

    public Float getPipeBDensityNotNull() {
        Optional<Float> optionalFloat = Optional.ofNullable(this.gethPipeBDencity());
        return optionalFloat.orElse(0f);
    }

    public Float getPipeCDensityNotNull() {
        Optional<Float> optionalFloat = Optional.ofNullable(this.gethPipeCDencity());
        return optionalFloat.orElse(0f);
    }

    public Float getPipeDDensityNotNull() {
        Optional<Float> optionalFloat = Optional.ofNullable(this.gethPipeDDencity());
        return optionalFloat.orElse(0f);
    }

    public void sethPipeAVelocity(Float hPipeAVelocity) {
        this.hPipeAVelocity = hPipeAVelocity;
    }


    public abstract Float gethPipeBVelocity();


    public void sethPipeBVelocity(Float hPipeBVelocity) {
        this.hPipeBVelocity = hPipeBVelocity;
    }


    public abstract Float gethPipeCVelocity();

    public void sethPipeCVelocity(Float hPipeCVelocity) {
        this.hPipeCVelocity = hPipeCVelocity;
    }

    public abstract Float gethPipeDVelocity();

    public void sethPipeDVelocity(Float hPipeDVelocity) {
        this.hPipeDVelocity = hPipeDVelocity;
    }


    public abstract Float gethPipeADencity();

    public void sethPipeADencity(Float hPipeADencity) {
        this.hPipeADencity = hPipeADencity;
    }


    public abstract Float gethPipeBDencity();

    public void sethPipeBDencity(Float hPipeBDencity) {
        this.hPipeBDencity = hPipeBDencity;
    }


    public abstract Float gethPipeCDencity();

    public void sethPipeCDencity(Float hPipeCDencity) {
        this.hPipeCDencity = hPipeCDencity;
    }


    public abstract Float gethPipeDDencity();

    public Float getFormFloat(float target,int len){
        return new BigDecimal(target).setScale(len, RoundingMode.FLOOR).floatValue();
    }

    public Float gethPipeADencityWithFormat(int len) {
        Float target = this.getPipeADensityNotNull();
        return getFormFloat(target,len);
    }

    public Float gethPipeBDencityWithFormat(int len) {
        Float target = this.getPipeBDensityNotNull();
        return getFormFloat(target,len);
    }

    public Float gethPipeCDencityWithFormat(int len) {
        Float target = this.getPipeCDensityNotNull();
        return getFormFloat(target,len);
    }

    public Float gethPipeDDencityWithFormat(int len) {
        Float target = this.getPipeDDensityNotNull();
        return getFormFloat(target,len);
    }

    public Float gethPipeAVelocityWithFormat(int len) {
        Float target = this.getPipeAVelocityNotNull();
        return getFormFloat(target,len);
    } public Float gethPipeBVelocityWithFormat(int len) {
        Float target = this.getPipeBVelocityNotNull();
        return getFormFloat(target,len);
    }

    public Float gethPipeCVelocityWithFormat(int len) {
        Float target = this.getPipeCVelocityNotNull();
        return getFormFloat(target,len);
    }

    public Float gethPipeDVelocityWithFormat(int len) {
        Float target = this.getPipeDVelocityNotNull();
        return getFormFloat(target,len);
    }

    public void sethPipeDDencity(Float hPipeDDencity) {
        this.hPipeDDencity = hPipeDDencity;
    }


    public abstract Long gethCoalMillId();


    public void sethCoalMillId(Long hCoalMillId) {
        this.hCoalMillId = hCoalMillId;
    }


    public static CoalPipingHistory GeneratorCoalPipingHistory(Class<? extends CoalPipingHistory> tClass) {
        CoalPipingHistory coalPipingHistory = null;
        try {
            coalPipingHistory = tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return coalPipingHistory;
    }
}
