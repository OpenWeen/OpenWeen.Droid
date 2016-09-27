package moe.tlaster.openween.core.model.status;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import moe.tlaster.openween.core.model.BaseListModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class GroupListModel extends BaseListModel {
    @SerializedName("lists")
    private List<GroupModel> mLists;

    public List<GroupModel> getLists() {
        return mLists;
    }

    public void setLists(List<GroupModel> lists) {
        mLists = lists;
    }
}
