package moe.tlaster.openween.core.model.url;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class UrlInfoListModel {
    public List<UrlInfoModel> getUrls() {
        return mUrls;
    }

    public void setUrls(List<UrlInfoModel> urls) {
        mUrls = urls;
    }

    @SerializedName("urls")
    private List<UrlInfoModel> mUrls;

}
