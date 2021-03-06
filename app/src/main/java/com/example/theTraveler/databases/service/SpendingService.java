package com.example.theTraveler.databases.service;

import android.content.Context;

import com.example.theTraveler.async.AsyncTaskRunner;
import com.example.theTraveler.async.Callback;
import com.example.theTraveler.databases.DatabaseManager;
import com.example.theTraveler.databases.dao.SpendingDao;
import com.example.theTraveler.databases.model.Spending;

import java.util.List;
import java.util.concurrent.Callable;

public class SpendingService {

    private final SpendingDao spendingDao;
    private final AsyncTaskRunner taskRunner;

    public SpendingService(Context context) {
        spendingDao = DatabaseManager.getInstance(context).getSpendingDao();
        taskRunner = new AsyncTaskRunner();
    }

    public void getAll(String user, Callback<List<Spending>> callback) {
        Callable<List<Spending>> callable = new Callable<List<Spending>>() {
            @Override
            public List<Spending> call() throws Exception {
                return spendingDao.getAll(user);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void insert(final Spending spending, long visit, Callback<Spending> callback) {
        Callable<Spending> callable = new Callable<Spending>() {
            @Override
            public Spending call() {
                if (spending == null) {
                    return null;
                } else {
                    spending.setVisit(visit);
                    long id = spendingDao.insert(spending);
                    if (id == -1) {
                        return null;
                    } else {
                        spending.setId(id);
                        return spending;
                    }
                }
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void update(final Spending spending, Callback<Spending> callback) {
        Callable<Spending> callable = new Callable<Spending>() {
            @Override
            public Spending call() throws Exception {
                if (spending == null) {
                    return null;
                }
                int count = spendingDao.update(spending);
                if (count < 1) {
                    return null;
                }
                return spending;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void delete(final Spending spending, Callback<Integer> callback) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                if (spending == null) {
                    return -1;
                }
                return spendingDao.delete(spending);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }


    public void getMin(String user, Callback<Float> callback) {
        Callable<Float> callable = new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                return spendingDao.getMin(user);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void getMax(String user, Callback<Float> callback) {
        Callable<Float> callable = new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                return spendingDao.getMax(user);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void getAvg(String user, Callback<Float> callback) {
        Callable<Float> callable = new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                return spendingDao.getAvg(user);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void getTotal(String user, Callback<Float> callback) {
        Callable<Float> callable = new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                return spendingDao.getTotal(user);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }


}
