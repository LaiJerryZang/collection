package tw.com.collection.mvp.presenter;

import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tw.com.collection.R;
import tw.com.collection.basic.base.MultiTypeAdapter;
import tw.com.collection.basic.http.IDataCallListener;
import tw.com.collection.mvp.activity.MainViewContract;
import tw.com.collection.mvp.item.ItemContract;
import tw.com.collection.mvp.item.TextItem;
import tw.com.collection.mvp.model.MainModel;

public class MainPresenter implements ItemContract {
    private MainViewContract mainViewContract;
    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private MainModel mainModel = new MainModel();

    public MainPresenter(MainViewContract mainViewContract) {
        this.mainViewContract = mainViewContract;
    }

    public MultiTypeAdapter getAdapter() {
        return adapter;
    }

    public void reLoadData(){
        addItem();
    }

    private void addItem(){
        mainModel.getOpenData(new IDataCallListener() {
            @Override
            public void onSuccess(String response) {
                adapter.clearItems();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String title = jsonObject.optString("title","a");
                        adapter.addItem(new TextItem(title,MainPresenter.this,adapter));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();
                mainViewContract.setRefreshing(false);
            }

            @Override
            public void onFailure(String response) {
                mainViewContract.Error(response);
            }

            @Override
            public void start() {
                mainViewContract.startProgress();
            }

            @Override
            public void end() {
                mainViewContract.endProgress();
            }
        });
    }


    @Override
    public void onClick(View view, int position) {
        switch (view.getId()){
            case R.id.tv_title:
                mainViewContract.showToast(String.valueOf(position));
                break;
        }
    }
}
