package moe.tlaster.openween.fragment.user

import android.os.Bundle
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.typeface.IIcon
import moe.tlaster.openween.adapter.BaseModelAdapter
import moe.tlaster.openween.core.api.favorites.Favorites
import moe.tlaster.openween.core.model.favor.FavorListModel
import moe.tlaster.openween.core.model.favor.FavorModel
import moe.tlaster.openween.core.model.status.MessageModel
import moe.tlaster.openween.fragment.WeiboListBase

/**
 * Created by Asahi on 16/12/01.
 */
class Favorite(override val icon: IIcon = GoogleMaterial.Icon.gmd_star) : WeiboListBase<MessageModel>() {

    private var mID: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mID = arguments.getLong("id")
    }


    override fun initAdapter(): BaseQuickAdapter<MessageModel, BaseViewHolder> {
        return BaseModelAdapter()
    }

    override fun loadMoreOverride(callback: Callback<List<MessageModel>>) {
        Favorites.getFavorList(mLoadCount, mPage++, object : WeiboListCallback<FavorListModel>() {
            override fun onResponse(response: FavorListModel, id: Int) {
                callback.onResponse(response.favorites?.map { it.status!! }!!, response.totalNumber)
            }
        })
    }

    override fun refreshOverride(callback: Callback<List<MessageModel>>) {
        Favorites.getFavorList(mLoadCount, mPage++, object : WeiboListCallback<FavorListModel>() {
            override fun onResponse(response: FavorListModel, id: Int) {
                callback.onResponse(response.favorites?.map { it.status!! }!!, response.totalNumber)
            }
        })
    }

    companion object {

        fun create(id: Long): Favorite {
            val favorite = Favorite()
            val bundle = Bundle()
            bundle.putLong("id", id)
            favorite.arguments = bundle
            return favorite
        }
    }
}