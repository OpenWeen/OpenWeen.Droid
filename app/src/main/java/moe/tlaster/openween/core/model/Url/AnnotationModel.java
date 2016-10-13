package moe.tlaster.openween.core.model.url;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class AnnotationModel {
    @SerializedName("object")
    private ObjectModel mItem;

    public ObjectModel getItem() {
        return mItem;
    }

    public void setItem(ObjectModel item) {
        mItem = item;
    }
}
