package com.app.pengeluaranku.view.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.pengeluaranku.utils.database.DatabaseClient;
import com.app.pengeluaranku.model.entity.Pengeluaran;
import com.app.pengeluaranku.utils.database.daos.PengeluaranDao;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Pengeluaran>> mPengeluarans;
    private PengeluaranDao pengeluaranDao;
    private LiveData<Integer> mTotalPrice;

    public MainViewModel(@NonNull Application application) {
        super(application);

        pengeluaranDao = DatabaseClient.getInstance(application)
                .getAppDatabase().pengeluaranDao();
        mPengeluarans = pengeluaranDao.getAll();
        mTotalPrice = pengeluaranDao.getTotalPrice();
    }

    public LiveData<List<Pengeluaran>> getPengeluarans() {
        return mPengeluarans;
    }

    public LiveData<Integer> getTotalPrice() {
        return mTotalPrice;
    }

    public void deleteAllData() {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                pengeluaranDao.deleteAllData();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteSingleData(final int uid) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                pengeluaranDao.deleteSingleData(uid);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
