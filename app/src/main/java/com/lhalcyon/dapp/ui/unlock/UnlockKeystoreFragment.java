package com.lhalcyon.dapp.ui.unlock;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.lhalcyon.dapp.R;
import com.lhalcyon.dapp.bindlite.Perform;
import com.lhalcyon.dapp.config.Tag;
import com.lhalcyon.dapp.databinding.FragmentUnlockKeystoreBinding;
import com.lhalcyon.dapp.manager.InitWalletManager;
import com.lhalcyon.dapp.model.HLWallet;
import com.lhalcyon.dapp.stuff.HLError;
import com.lhalcyon.dapp.stuff.HLSubscriber;
import com.lhalcyon.dapp.stuff.ScheduleCompat;
import com.lhalcyon.dapp.ui.MainActivity;
import com.lhalcyon.dapp.ui.base.BaseFragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * <pre>
 * Create by  :    L
 * Create Time:    2018/6/22
 * Brief Desc :
 * </pre>
 */
public class UnlockKeystoreFragment extends BaseFragment<FragmentUnlockKeystoreBinding> {

    public final Perform<View> call = new Perform<>(view -> {
        switch (view.getId()) {
            case R.id.btn_unlock:
                attemptUnlock();
                break;
            default:
                break;
        }
    });

    private void attemptUnlock() {
        String keystore = mBinding.etMnemonic.getText().toString().trim();
        String password = mBinding.etPassword.getText().toString().trim();

        Flowable
                .just(keystore)
                .filter(s -> validateInput(s,password))
                .flatMap(s -> InitWalletManager.shared().importKeystore(mContext,s,password))
                .compose(ScheduleCompat.apply())
                .subscribe(new HLSubscriber<HLWallet>(mContext,true) {
                    @SuppressLint("CheckResult")
                    @Override
                    protected void success(HLWallet data) {
                        Snackbar.make(mBinding.getRoot(),"Unlock Success",Snackbar.LENGTH_LONG).show();
                        Flowable.just(1)
                                .delay(2000, TimeUnit.MILLISECONDS)
                                .compose(ScheduleCompat.apply())
                                .subscribe(integer -> {
                                    startActivity(new Intent(mContext,MainActivity.class).putExtra(Tag.INDEX,1));
                                    getActivity().finish();
                                });
                    }

                    @Override
                    protected void failure(HLError error) {
                        Snackbar.make(mBinding.getRoot(),error.getMessage(),Snackbar.LENGTH_LONG)
                                .show();
                    }
                });
    }

    private boolean validateInput(String privateKey, String password) {
        // validate empty
        if (TextUtils.isEmpty(privateKey) || TextUtils.isEmpty(password)){
            ScheduleCompat.snackInMain(mBinding.getRoot(),"please input data");
            return false;
        }
        return true;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        mBinding.setCall(call);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_unlock_keystore;
    }
}
