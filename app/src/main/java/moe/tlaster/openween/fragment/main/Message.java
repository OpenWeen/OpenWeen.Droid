package moe.tlaster.openween.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.IIcon;

import moe.tlaster.openween.R;
import moe.tlaster.openween.common.controls.Pivot;

/**
 * Created by Tlaster on 2016/9/9.
 */
public class Message extends Pivot.PivotItemFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public IIcon getIcon() {
        return GoogleMaterial.Icon.gmd_forum;
    }
}
