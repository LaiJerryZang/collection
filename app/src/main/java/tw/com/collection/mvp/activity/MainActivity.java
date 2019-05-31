package tw.com.collection.mvp.activity;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import tw.com.collection.R;
import tw.com.collection.basic.base.BaseActivity;
import tw.com.collection.basic.utils.CommonUtil;
import tw.com.collection.databinding.ActivityMainBinding;
import tw.com.collection.mvp.presenter.MainPresenter;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainViewContract {

    private MainPresenter mainPresenter = new MainPresenter(this);

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        RecyclerView recyclerView = dataBinding.rv;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mainPresenter.getAdapter());

    }

    @Override
    protected void initData() {
        SwipeRefreshLayout refreshLayout = dataBinding.swipeRefreshLayout;
        refreshLayout.setOnRefreshListener(() -> mainPresenter.reLoadData());
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        dataBinding.swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void Error(String exception) {
        CommonUtil.showToast(this,exception);
    }

    @Override
    public void startProgress() {
        showDialog("");
    }

    @Override
    public void endProgress() {
        dismissDialog();
    }

    @Override
    public void showToast(String msg) {
        CommonUtil.showToast(this,msg);
    }


}