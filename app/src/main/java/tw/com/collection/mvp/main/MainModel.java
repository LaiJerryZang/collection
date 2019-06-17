package tw.com.collection.mvp.main;

import androidx.databinding.BaseObservable;

import tw.com.collection.basic.http.HttpClient;
import tw.com.collection.basic.http.IDataCallListener;

public class MainModel extends BaseObservable {
    public void getOpenData(IDataCallListener listener){
        HttpClient.get("http://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelFood.aspx", null, listener);
    }
}
