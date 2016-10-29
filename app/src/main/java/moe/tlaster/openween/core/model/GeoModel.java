package moe.tlaster.openween.core.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/8/26.
 */
public class GeoModel implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mLongitude);
        dest.writeString(this.mLatitude);
        dest.writeString(this.mCity);
        dest.writeString(this.mProvince);
        dest.writeString(this.mCityName);
        dest.writeString(this.mProvinceName);
        dest.writeString(this.mAddress);
        dest.writeString(this.mPinyin);
        dest.writeString(this.mMore);
    }

    public GeoModel() {
    }

    protected GeoModel(Parcel in) {
        this.mLongitude = in.readString();
        this.mLatitude = in.readString();
        this.mCity = in.readString();
        this.mProvince = in.readString();
        this.mCityName = in.readString();
        this.mProvinceName = in.readString();
        this.mAddress = in.readString();
        this.mPinyin = in.readString();
        this.mMore = in.readString();
    }

    public static final Parcelable.Creator<GeoModel> CREATOR = new Parcelable.Creator<GeoModel>() {
        @Override
        public GeoModel createFromParcel(Parcel source) {
            return new GeoModel(source);
        }

        @Override
        public GeoModel[] newArray(int size) {
            return new GeoModel[size];
        }
    };
}
