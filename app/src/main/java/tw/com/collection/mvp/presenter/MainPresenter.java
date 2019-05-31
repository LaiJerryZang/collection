package tw.com.collection.mvp.presenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tw.com.collection.basic.base.MultiTypeAdapter;
import tw.com.collection.basic.http.IDataCallListener;
import tw.com.collection.mvp.activity.IMainViewContract;
import tw.com.collection.mvp.item.TextItem;
import tw.com.collection.mvp.model.MainModel;

public class MainPresenter {
    private IMainViewContract IMainViewContractLisener;
    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private MainModel mainModel = new MainModel();

    public MainPresenter(IMainViewContract IMainViewContractLisener) {
        this.IMainViewContractLisener = IMainViewContractLisener;
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
                        adapter.addItem(new TextItem(title));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();
                IMainViewContractLisener.setRefreshing(false);
            }

            @Override
            public void onFailure(String response) {
                IMainViewContractLisener.Error(response);
            }

            @Override
            public void start() {
                IMainViewContractLisener.startProgress();
            }

            @Override
            public void end() {
                IMainViewContractLisener.endProgress();
            }
        });
    }

}
