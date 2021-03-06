package com.bolin.logistics.model;

public class Car {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.id
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.user_id
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.warehouse_id
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private Integer warehouseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.state
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private Integer state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.drive_licence
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private String driveLicence;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.id_card
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private String idCard;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.insurance_card
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private String insuranceCard;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.run_licence
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private String runLicence;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.allow_carry_volume
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private Double allowCarryVolume;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.allow_carry_weight
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private Double allowCarryWeight;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.car_length
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private Double carLength;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.car_width
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private Double carWidth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.car_height
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private Double carHeight;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.car_frame_no
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private String carFrameNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.car_no
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private String carNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.car_type
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private String carType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.remark
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.gmt_create
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column car.gmt_modified
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    private Long gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.id
     *
     * @return the value of car.id
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.id
     *
     * @param id the value for car.id
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.user_id
     *
     * @return the value of car.user_id
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.user_id
     *
     * @param userId the value for car.user_id
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.warehouse_id
     *
     * @return the value of car.warehouse_id
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.warehouse_id
     *
     * @param warehouseId the value for car.warehouse_id
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.state
     *
     * @return the value of car.state
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.state
     *
     * @param state the value for car.state
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.drive_licence
     *
     * @return the value of car.drive_licence
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public String getDriveLicence() {
        return driveLicence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.drive_licence
     *
     * @param driveLicence the value for car.drive_licence
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setDriveLicence(String driveLicence) {
        this.driveLicence = driveLicence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.id_card
     *
     * @return the value of car.id_card
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.id_card
     *
     * @param idCard the value for car.id_card
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.insurance_card
     *
     * @return the value of car.insurance_card
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public String getInsuranceCard() {
        return insuranceCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.insurance_card
     *
     * @param insuranceCard the value for car.insurance_card
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setInsuranceCard(String insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.run_licence
     *
     * @return the value of car.run_licence
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public String getRunLicence() {
        return runLicence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.run_licence
     *
     * @param runLicence the value for car.run_licence
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setRunLicence(String runLicence) {
        this.runLicence = runLicence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.allow_carry_volume
     *
     * @return the value of car.allow_carry_volume
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public Double getAllowCarryVolume() {
        return allowCarryVolume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.allow_carry_volume
     *
     * @param allowCarryVolume the value for car.allow_carry_volume
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setAllowCarryVolume(Double allowCarryVolume) {
        this.allowCarryVolume = allowCarryVolume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.allow_carry_weight
     *
     * @return the value of car.allow_carry_weight
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public Double getAllowCarryWeight() {
        return allowCarryWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.allow_carry_weight
     *
     * @param allowCarryWeight the value for car.allow_carry_weight
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setAllowCarryWeight(Double allowCarryWeight) {
        this.allowCarryWeight = allowCarryWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.car_length
     *
     * @return the value of car.car_length
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public Double getCarLength() {
        return carLength;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.car_length
     *
     * @param carLength the value for car.car_length
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setCarLength(Double carLength) {
        this.carLength = carLength;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.car_width
     *
     * @return the value of car.car_width
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public Double getCarWidth() {
        return carWidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.car_width
     *
     * @param carWidth the value for car.car_width
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setCarWidth(Double carWidth) {
        this.carWidth = carWidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.car_height
     *
     * @return the value of car.car_height
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public Double getCarHeight() {
        return carHeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.car_height
     *
     * @param carHeight the value for car.car_height
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setCarHeight(Double carHeight) {
        this.carHeight = carHeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.car_frame_no
     *
     * @return the value of car.car_frame_no
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public String getCarFrameNo() {
        return carFrameNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.car_frame_no
     *
     * @param carFrameNo the value for car.car_frame_no
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setCarFrameNo(String carFrameNo) {
        this.carFrameNo = carFrameNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.car_no
     *
     * @return the value of car.car_no
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public String getCarNo() {
        return carNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.car_no
     *
     * @param carNo the value for car.car_no
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.car_type
     *
     * @return the value of car.car_type
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public String getCarType() {
        return carType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.car_type
     *
     * @param carType the value for car.car_type
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.remark
     *
     * @return the value of car.remark
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.remark
     *
     * @param remark the value for car.remark
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.gmt_create
     *
     * @return the value of car.gmt_create
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.gmt_create
     *
     * @param gmtCreate the value for car.gmt_create
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column car.gmt_modified
     *
     * @return the value of car.gmt_modified
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column car.gmt_modified
     *
     * @param gmtModified the value for car.gmt_modified
     *
     * @mbg.generated Wed Mar 09 17:07:09 CST 2022
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}