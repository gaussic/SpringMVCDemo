package com.gaussic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "w_user", schema = "wind", catalog = "")
public class WUserEntity {
    private long id;
    private String uName;
    private String uLoginName;
    private String uLoginPwd;
    private Timestamp uLastLoginTime;
    private Integer uLoginState;
    private Long uAuthorityId;
    private String uNote;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "u_name")
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Basic
    @Column(name = "u_login_name")
    public String getuLoginName() {
        return uLoginName;
    }

    public void setuLoginName(String uLoginName) {
        this.uLoginName = uLoginName;
    }

    @Basic
    @Column(name = "u_login_pwd")
    public String getuLoginPwd() {
        return uLoginPwd;
    }

    public void setuLoginPwd(String uLoginPwd) {
        this.uLoginPwd = uLoginPwd;
    }

    @Basic
    @Column(name = "u_last_login_time")
    public Timestamp getuLastLoginTime() {
        return uLastLoginTime;
    }

    public void setuLastLoginTime(Timestamp uLastLoginTime) {
        this.uLastLoginTime = uLastLoginTime;
    }

    @Basic
    @Column(name = "u_login_state")
    public Integer getuLoginState() {
        return uLoginState;
    }

    public void setuLoginState(Integer uLoginState) {
        this.uLoginState = uLoginState;
    }

    @Basic
    @Column(name = "u_authority_id")
    public Long getuAuthorityId() {
        return uAuthorityId;
    }

    public void setuAuthorityId(Long uAuthorityId) {
        this.uAuthorityId = uAuthorityId;
    }

    @Basic
    @Column(name = "u_note")
    public String getuNote() {
        return uNote;
    }

    public void setuNote(String uNote) {
        this.uNote = uNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WUserEntity that = (WUserEntity) o;
        return id == that.id &&
                Objects.equals(uName, that.uName) &&
                Objects.equals(uLoginName, that.uLoginName) &&
                Objects.equals(uLoginPwd, that.uLoginPwd) &&
                Objects.equals(uLastLoginTime, that.uLastLoginTime) &&
                Objects.equals(uLoginState, that.uLoginState) &&
                Objects.equals(uAuthorityId, that.uAuthorityId) &&
                Objects.equals(uNote, that.uNote);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, uName, uLoginName, uLoginPwd, uLastLoginTime, uLoginState, uAuthorityId, uNote);
    }
}
