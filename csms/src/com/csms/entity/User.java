package com.csms.entity;

/**
 * Created by zhuxiaolei on 2017/6/3.
 */
public class User {
    private Integer id;
    private String userAccount;
    private String password;
    private String userName;
    private String userProfile;//用户简介
    private Integer gradeID;//用户等级 1 管理员 2用户
    private Integer del;//软删除 1正常 2删除

    public User() {
    }

    public User(String userAccount, String password, String userName, String userProfile) {
        this.userAccount = userAccount;
        this.password = password;
        this.userName = userName;
        this.userProfile = userProfile;
    }

    public User(Integer id, String userAccount, String password, String userName, String userProfile, Integer gradeID, Integer del) {
        this.id = id;
        this.userAccount = userAccount;
        this.password = password;
        this.userName = userName;
        this.userProfile = userProfile;
        this.gradeID = gradeID;
        this.del = del;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public Integer getGradeID() {
        return gradeID;
    }

    public void setGradeID(Integer gradeID) {
        this.gradeID = gradeID;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (userAccount != null ? !userAccount.equals(user.userAccount) : user.userAccount != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (userProfile != null ? !userProfile.equals(user.userProfile) : user.userProfile != null) return false;
        if (gradeID != null ? !gradeID.equals(user.gradeID) : user.gradeID != null) return false;
        return del != null ? del.equals(user.del) : user.del == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userAccount != null ? userAccount.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userProfile != null ? userProfile.hashCode() : 0);
        result = 31 * result + (gradeID != null ? gradeID.hashCode() : 0);
        result = 31 * result + (del != null ? del.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userAccount='" + userAccount + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", userProfile='" + userProfile + '\'' +
                ", gradeID=" + gradeID +
                ", del=" + del +
                '}';
    }
}
