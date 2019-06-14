package tw.com.collection.mvp.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import tw.com.collection.R;
import tw.com.collection.basic.base.BaseActivity;
import tw.com.collection.basic.utils.CommonUtil;
import tw.com.collection.basic.view.ScaleButton;
import tw.com.collection.databinding.ActivityMainBinding;
import tw.com.collection.mvp.serversocket.ServerSocketActivity;
import tw.com.collection.mvp.socket.SocketActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainViewContract {
    private MainPresenter mainPresenter;
    private boolean scrollFlag = false;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mainPresenter = new MainPresenter(this);
        //設定 RecyclerView
        RecyclerView recyclerView = dataBinding.rv;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mainPresenter.getAdapter());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                //最底部並停止動作才更新
//                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
//                        llm.findLastVisibleItemPosition() >= mainPresenter.getAdapter().getItemCount() - 2) {
//                    mainPresenter.loadMoreData();
//                }

                //在滑動時到倒數第7個item更新
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING &&
                        llm.findLastVisibleItemPosition() >= mainPresenter.getAdapter().getItemCount() - 7) {
                    mainPresenter.loadMoreData();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {//手指上滑
                    if (!scrollFlag && dataBinding.btn.getAlpha() == 1.0f) {
                        alphaAnim(dataBinding.btn, 1.0f, 0f);
                    }
                    scrollFlag = true;
                } else if(dy < 0){
                    if (scrollFlag && dataBinding.btn.getAlpha() == 0f) {
                        dataBinding.btn.setVisibility(View.VISIBLE);
                        alphaAnim(dataBinding.btn, 0f, 1.0f);
                    }
                    scrollFlag = false;
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
        CommonUtil.showToast(this, exception);
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
    public void setViewTouch(boolean canTouch) {
        dataBinding.fly.setClickable(!canTouch);
    }

    private void alphaAnim(View view, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", values);
        animator.setDuration(500);
        animator.start();

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (dataBinding.btn.getAlpha() == 0f) {
                    dataBinding.btn.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.serverSocket:
                intent.setClass(this, ServerSocketActivity.class);
                break;
            case R.id.clientSocket:
                intent.setClass(this, SocketActivity.class);
                break;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
