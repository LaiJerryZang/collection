package tw.com.collection.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import tw.com.collection.R;
import tw.com.collection.basic.base.BaseActivity;
import tw.com.collection.basic.utils.CommonUtil;
import tw.com.collection.basic.view.ScaleButton;
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
        //設定 RecyclerView
        RecyclerView recyclerView = dataBinding.rv;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mainPresenter.getAdapter());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        llm.findLastVisibleItemPosition() >= mainPresenter.getAdapter().getItemCount() - 2) {
                    mainPresenter.loadMoreData();
                }
            }
        });

        //監聽 SwipeRefreshLayout 滑動
        SwipeRefreshLayout refreshLayout = dataBinding.swipeRefreshLayout;
        refreshLayout.setOnRefreshListener(() -> mainPresenter.loadData(true));

        //回到最上方
        ScaleButton button = dataBinding.btn;
        button.setOnClickListener((ScaleButton.OnClickListener) v -> recyclerView.smoothScrollToPosition(0));
    }

    @Override
    protected void initData() {
        mainPresenter.loadData(false);
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

    @Override
    public void callBack(View view) {
        openActivity(view,"img_transition");
    }

    @Override
    protected void onDestroy() {
        mainPresenter.Destroy();
        super.onDestroy();
    }

    private void openActivity(View view, String transitionName) {
        Pair<View, String> imagePair = Pair.create(view, transitionName);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view,view.getTransitionName());
        ActivityCompat.startActivity(this, new Intent(this, SecondActivity.class), compat.toBundle());
    }
}
