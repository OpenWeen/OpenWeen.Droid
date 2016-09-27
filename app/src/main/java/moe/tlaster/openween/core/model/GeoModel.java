package moe.tlaster.openween.core.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/8/26.
 */
public class GeoModel {
    @SerializedName("longitude")
    private String mLongitude;
    @SerializedName("latitude")
    private String mLatitude;
    @SerializedName("city")
    private String mCity;
    @SerializedName("province")
    private String mProvince;
    @SerializedName("city_name")
    private String mCityName;
    @SerializedName("province_name")
    private String mProvinceName;
    @SerializedName("address")
    private String mAddress;
    @SerializedName("pinyin")
    private String mPinyin;
    @SerializedName("more")
    private String mMore;

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getProvince() {
        return mProvince;
    }

    public void setProvince(String province) {
        mProvince = province;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        mCityName = cityName;
    }

    public String getProvinceName() {
        return mProvinceName;
    }

    public void setProvinceName(String provinceName) {
        mProvinceName = provinceName;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getPinyin() {
        return mPinyin;
    }

    public void setPinyin(String pinyin) {
        mPinyin = pinyin;
    }

    public String getMore() {
        return mMore;
    }

    public void setMore(String more) {
        mMore = more;
    }
}
