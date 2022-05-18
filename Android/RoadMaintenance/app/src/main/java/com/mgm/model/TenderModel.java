package com.mgm.model;

public class TenderModel {
    String id, title, description, type, area, taluka, district, state, sq_km, amount, tender_date, is_alloted,allotment_letter;

    public TenderModel(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public TenderModel(String id, String title, String description, String type, String area, String taluka, String district, String state, String sq_km, String amount, String tender_date, String is_alloted, String allotment_letter) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.area = area;
        this.taluka = taluka;
        this.district = district;
        this.state = state;
        this.sq_km = sq_km;
        this.amount = amount;
        this.tender_date = tender_date;
        this.is_alloted = is_alloted;
        this.allotment_letter = allotment_letter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSq_km() {
        return sq_km;
    }

    public void setSq_km(String sq_km) {
        this.sq_km = sq_km;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTender_date() {
        return tender_date;
    }

    public void setTender_date(String tender_date) {
        this.tender_date = tender_date;
    }

    public String getIs_alloted() {
        return is_alloted;
    }

    public void setIs_alloted(String is_alloted) {
        this.is_alloted = is_alloted;
    }

    public String getAllotment_letter() {
        return allotment_letter;
    }

    public void setAllotment_letter(String allotment_letter) {
        this.allotment_letter = allotment_letter;
    }
}