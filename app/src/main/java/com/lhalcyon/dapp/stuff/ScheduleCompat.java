

package com.lhalcyon.dapp.stuff;


import android.annotation.SuppressLint;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 * Create by  :    L
 * Create Time:    2018-4-17
 * Brief Desc :
 * </pre>
 */
public class ScheduleCompat {

   public static <T> FlowableTransformer<T,T> apply(){
       return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
   }

   @SuppressLint("CheckResult")
   public static void snackInMain(View view, String content){
       Flowable
               .just(content)
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(s -> Snackbar.make(view,s,Snackbar.LENGTH_LONG).show());
   }

}
