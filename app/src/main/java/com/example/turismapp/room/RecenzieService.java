package com.example.turismapp.room;

import android.content.Context;


import java.util.List;
import java.util.concurrent.Callable;


public class RecenzieService {
    private final RecenzieDao recenzieDao;
    private final AsyncTaskRunner taskRunner;

    public RecenzieService(Context context) {
        recenzieDao = DatabaseManager.getInstance(context).getRecenzieDao();
        taskRunner = new AsyncTaskRunner();
    }


    public void getAll(Callback<List<Recenzie>> callback) {
        Callable<List<Recenzie>> callable = new Callable<List<Recenzie>>() {
            @Override
            public List<Recenzie> call() {
                return recenzieDao.getAll();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void insert(Callback<Recenzie> callback, final Recenzie recenzie) {
        Callable<Recenzie> callable = new Callable<Recenzie>() {
            @Override
            public Recenzie call() {
                if (recenzie == null) {
                    return null;
                }
                long id = recenzieDao.insert(recenzie);
                if (id == -1) {
                    return null;
                }
                recenzie.setId(id);
                return recenzie;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void update(Callback<Recenzie> callback, final Recenzie recenzie) {
        Callable<Recenzie> callable = new Callable<Recenzie>() {
            @Override
            public Recenzie call() {
                if (recenzie == null) {
                    return null;
                }
                int count = recenzieDao.update(recenzie);
                if (count < 1) {
                    return null;
                }
                return recenzie;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void delete(Callback<Integer> callback, final Recenzie recenzie) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if (recenzie == null) {
                    return -1;
                }
                return recenzieDao.delete(recenzie);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
}
