package com.lhalcyon.dapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.lhalcyon.dapp.R;
import com.lhalcyon.dapp.bindlite.Perform;
import com.lhalcyon.dapp.databinding.FragmentSettingsBinding;
import com.lhalcyon.dapp.ui.base.BaseFragment;

/**
 * <pre>
 * Create by  :    L
 * Create Time:    2018/6/19
 * Brief Desc :
 * </pre>
 */
public class SettingsFragment extends BaseFragment<FragmentSettingsBinding> {

    public final Perform<View> call = new Perform<>(view -> {
        switch (view.getId()) {
            case R.id.btn_wallet_manage:
                startActivity(new Intent(mContext,InitWalletActivity.class));
                break;
            case R.id.btn_record:
                Snackbar.make(mBinding.getRoot(),"Holder for now:)",Snackbar.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    });

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBinding.setCall(call);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_settings;
    }


}
