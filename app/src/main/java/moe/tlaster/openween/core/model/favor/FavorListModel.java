package moe.tlaster.openween.core.model.favor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class FavorListModel {
    @SerializedName("favorites")
    private List<FavorModel> mFavorites;
    @SerializedName("total_number")
    private int mTotalNumber;

    public List<FavorModel> getFavorites() {
        return mFavorites;
    }

    public void setFavorites(List<FavorModel> favorites) {
        mFavorites = favorites;
    }

    public int getTotalNumber() {
        return mTotalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        mTotalNumber = totalNumber;
    }
}
