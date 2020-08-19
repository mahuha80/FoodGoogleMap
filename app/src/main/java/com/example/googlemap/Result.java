package com.example.googlemap;

import com.google.gson.annotations.SerializedName;

class Result {
    @SerializedName("business_status")
    private String businessStatus;
    @SerializedName("geometry")
    private Geometry geometry;

    public Result(String businessStatus, Geometry geometry) {
        this.businessStatus = businessStatus;
        this.geometry = geometry;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
