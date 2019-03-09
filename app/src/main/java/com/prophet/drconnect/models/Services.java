package com.prophet.drconnect.models;

public class Services {
    private int id, thumbnail;
    private String clinicName, serviceType;

    public Services(int thumbnail, String clinicName, String serviceType) {
        this.thumbnail = thumbnail;
        this.clinicName = clinicName;
        this.serviceType = serviceType;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
