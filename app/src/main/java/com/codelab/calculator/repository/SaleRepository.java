package com.codelab.calculator.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.codelab.calculator.database.SaleDatabase;
import com.codelab.calculator.models.SaleDao;
import com.codelab.calculator.models.SaleModel;

import java.util.List;

public class SaleRepository {

    private SaleDao dao;
    private LiveData<List<SaleModel>> allItems;


    public SaleRepository(Application application) {
        SaleDatabase database = SaleDatabase.getInstance(application);
        dao = database.Dao();
        allItems = dao.getAllItems();
    }

    // creating a method to insert the data to our database.
    public void insert(SaleModel model) {
        new InsertCourseAsyncTask(dao).execute(model);
    }

    // creating a method to update data in database.
    public void update(SaleModel model) {
        new UpdateCourseAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(SaleModel model) {
        new DeleteCourseAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the courses.
    public void deleteAllItems() {
        new DeleteAllCoursesAsyncTask(dao).execute();
    }

    // below method is to read all the courses.
    public LiveData<List<SaleModel>> getAllItems() {
        return allItems;
    }

    // we are creating a async task method to insert new course.
    private static class InsertCourseAsyncTask extends AsyncTask<SaleModel, Void, Void> {
        private SaleDao dao;

        private InsertCourseAsyncTask(SaleDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(SaleModel... model) {
            // below line is use to insert our modal in dao.
            dao.Insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateCourseAsyncTask extends AsyncTask<SaleModel, Void, Void> {
        private SaleDao dao;

        private UpdateCourseAsyncTask(SaleDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(SaleModel... models) {
            // below line is use to update
            // our modal in dao.
            dao.Update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCourseAsyncTask extends AsyncTask<SaleModel, Void, Void> {
        private SaleDao dao;

        private DeleteCourseAsyncTask(SaleDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(SaleModel... models) {
            // below line is use to delete
            // our course modal in dao.
            dao.Delete(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all courses.
    private static class DeleteAllCoursesAsyncTask extends AsyncTask<Void, Void, Void> {
        private SaleDao dao;
        private DeleteAllCoursesAsyncTask(SaleDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllItem();
            return null;
        }
    }
}
