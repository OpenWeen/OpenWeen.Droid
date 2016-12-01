package moe.tlaster.openween.fragment.main

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.typeface.IIcon
import moe.tlaster.openween.adapter.BaseModelAdapter
import moe.tlaster.openween.core.api.attitudes.Attitudes
import moe.tlaster.openween.core.model.attitude.AttitudeListModel
import moe.tlaster.openween.core.model.attitude.AttitudeModel
import moe.tlaster.openween.fragment.WeiboListBase

/**
 * Created by Asahi on 16/12/01.
 */
class Attitude(override val icon: IIcon = GoogleMaterial.Icon.gmd_thumb_up) : WeiboListBase<AttitudeModel>() {

    override fun initAdapter(): BaseQuickAdapter<AttitudeModel, BaseViewHolder> {
        return BaseModelAdapter()
    }

    override fun loadMoreOverride(callback: Callback<List<AttitudeModel>>) {
        Attitudes.likeToMe(count = mLoadCount, max_id = mAdapter?.data?.last()?.id!!, callback = object : WeiboListCallback<AttitudeListModel>() {
            override fun onResponse(response: AttitudeListModel, id: Int) {
                response.attitudes = response.attitudes?.subList(1, response.attitudes?.lastIndex!!)
                callback.onResponse(response.attitudes!!, response.totalNumber)
            }
        })
    }

    override fun refreshOverride(callback: Callback<List<AttitudeModel>>) {
        Attitudes.likeToMe(count = mLoadCount, callback = object : WeiboListCallback<AttitudeListModel>() {
            override fun onResponse(response: AttitudeListModel, id: Int) {
                callback.onResponse(response.attitudes!!, response.totalNumber)
            }
        })
    }
}