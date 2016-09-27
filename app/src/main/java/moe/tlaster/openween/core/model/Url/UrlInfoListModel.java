package moe.tlaster.openween.core.model.Url;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class UrlInfoListModel {
    public UrlInfoModel getUrls() {
        return mUrls;
    }

    public void setUrls(UrlInfoModel urls) {
        mUrls = urls;
    }

    @SerializedName("urls")
    private UrlInfoModel mUrls;

}
