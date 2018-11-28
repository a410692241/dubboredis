package com.wei.service.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * employee
 * @author 
 */
public class Employee implements Serializable {
    /**
     * [监管员id]监管员id
     */
    private Integer employeeId;

    /**
     * [网格化Id]负责的区域
     */
    private Integer areaId;

    /**
     * [部门Id]部门id
     */
    private Integer departmentId;

    /**
     * [监管员姓名]监管员姓名
     */
    private String employeeName;

    /**
     * [性别]1：男 2：女
     */
    private Short sex;

    /**
     * [职位编码]职位编码
     */
    private Short positionCode;

    /**
     * [手机号]手机号
     */
    private String mobile;

    /**
     * [密码]密码
     */
    private String password;

    /**
     * [入职时间]入职时间
     */
    private Date entryTime;

    /**
     * [创建时间]创建时间
     */
    private Date createTime;

    /**
     * [监管局code]
     */
    private Short prisonCode;

    /**
     * [手机token]手机tokeny
     */
    private String mobileToken;

    /**
     * [头像]
     */
    private String headImage;

    /**
     * [是否是领导]
     */
    private Short isLeader;

    /**
     * [执法人员编号]
     */
    private String jobNumber;

    /**
     * [账号状态]1：启用 2：禁用
     */
    private Short statu;

    /**
     * [主体业态]科长主管的主体业态
     */
    private Integer mainBodyFormsId;

    private static final long serialVersionUID = 1L;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public Short getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(Short positionCode) {
        this.positionCode = positionCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getPrisonCode() {
        return prisonCode;
    }

    public void setPrisonCode(Short prisonCode) {
        this.prisonCode = prisonCode;
    }

    public String getMobileToken() {
        return mobileToken;
    }

    public void setMobileToken(String mobileToken) {
        this.mobileToken = mobileToken;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Short getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Short isLeader) {
        this.isLeader = isLeader;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Short getStatu() {
        return statu;
    }

    public void setStatu(Short statu) {
        this.statu = statu;
    }

    public Integer getMainBodyFormsId() {
        return mainBodyFormsId;
    }

    public void setMainBodyFormsId(Integer mainBodyFormsId) {
        this.mainBodyFormsId = mainBodyFormsId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Employee other = (Employee) that;
        return (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getEmployeeName() == null ? other.getEmployeeName() == null : this.getEmployeeName().equals(other.getEmployeeName()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getPositionCode() == null ? other.getPositionCode() == null : this.getPositionCode().equals(other.getPositionCode()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getEntryTime() == null ? other.getEntryTime() == null : this.getEntryTime().equals(other.getEntryTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getPrisonCode() == null ? other.getPrisonCode() == null : this.getPrisonCode().equals(other.getPrisonCode()))
            && (this.getMobileToken() == null ? other.getMobileToken() == null : this.getMobileToken().equals(other.getMobileToken()))
            && (this.getHeadImage() == null ? other.getHeadImage() == null : this.getHeadImage().equals(other.getHeadImage()))
            && (this.getIsLeader() == null ? other.getIsLeader() == null : this.getIsLeader().equals(other.getIsLeader()))
            && (this.getJobNumber() == null ? other.getJobNumber() == null : this.getJobNumber().equals(other.getJobNumber()))
            && (this.getStatu() == null ? other.getStatu() == null : this.getStatu().equals(other.getStatu()))
            && (this.getMainBodyFormsId() == null ? other.getMainBodyFormsId() == null : this.getMainBodyFormsId().equals(other.getMainBodyFormsId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getEmployeeName() == null) ? 0 : getEmployeeName().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getPositionCode() == null) ? 0 : getPositionCode().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getEntryTime() == null) ? 0 : getEntryTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getPrisonCode() == null) ? 0 : getPrisonCode().hashCode());
        result = prime * result + ((getMobileToken() == null) ? 0 : getMobileToken().hashCode());
        result = prime * result + ((getHeadImage() == null) ? 0 : getHeadImage().hashCode());
        result = prime * result + ((getIsLeader() == null) ? 0 : getIsLeader().hashCode());
        result = prime * result + ((getJobNumber() == null) ? 0 : getJobNumber().hashCode());
        result = prime * result + ((getStatu() == null) ? 0 : getStatu().hashCode());
        result = prime * result + ((getMainBodyFormsId() == null) ? 0 : getMainBodyFormsId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", employeeId=").append(employeeId);
        sb.append(", areaId=").append(areaId);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", employeeName=").append(employeeName);
        sb.append(", sex=").append(sex);
        sb.append(", positionCode=").append(positionCode);
        sb.append(", mobile=").append(mobile);
        sb.append(", password=").append(password);
        sb.append(", entryTime=").append(entryTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", prisonCode=").append(prisonCode);
        sb.append(", mobileToken=").append(mobileToken);
        sb.append(", headImage=").append(headImage);
        sb.append(", isLeader=").append(isLeader);
        sb.append(", jobNumber=").append(jobNumber);
        sb.append(", statu=").append(statu);
        sb.append(", mainBodyFormsId=").append(mainBodyFormsId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}