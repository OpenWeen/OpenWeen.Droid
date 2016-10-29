package moe.tlaster.openween.core.model;

import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import moe.tlaster.openween.common.helpers.TimeHelper;
import moe.tlaster.openween.core.model.user.UserModel;

/**
 * Created by Tlaster on 2016/8/26.
 */
public abstract class BaseModel implements MultiItemEntity {

    public static final int MESSAGE = 1;
    public static final int COMMENT = 2;
    @SerializedName("id")
    protected long mID;
    @SerializedName("mid")
    protected long mMID;
    @SerializedName("idstr")
    protected String mIDStr;
    @SerializedName("created_at")
    protected String mCreatedAt;
    @SerializedName("text")
    protected String mText;
    @SerializedName("source")
    protected String mSource;
    @SerializedName("user")
    protected UserModel mUser;

    public long getMID() {
        return mMID;
    }

    public void setMID(long MID) {
        mMID = MID;
    }

    public long getID() {
        return mID;
    }

    public void setID(long ID) {
        mID = ID;
    }

    public String getIDStr() {
        return mIDStr;
    }

    public void setIDStr(String IDStr) {
        mIDStr = IDStr;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }
    public String getCreatedAtDiffForHuman(){
        try {
            return TimeHelper.getmPrettyTime().format(TimeHelper.getmSimpleDateFormat().parse(mCreatedAt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mCreatedAt;
    }

    public Date getCreatedDate(){
        try {
            return TimeHelper.getmSimpleDateFormat().parse(mCreatedAt);
        } catch (ParseException e) {
            return null;
        }
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public UserModel getUser() {
        return mUser;
    }

    public void setUser(UserModel user) {
        mUser = user;
    }


}
