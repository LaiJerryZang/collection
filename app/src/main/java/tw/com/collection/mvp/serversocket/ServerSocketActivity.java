package tw.com.collection.mvp.serversocket;

import android.widget.TextView;

import tw.com.collection.R;
import tw.com.collection.basic.base.BaseActivity;
import tw.com.collection.databinding.ActivityServersocketBinding;

public class ServerSocketActivity extends BaseActivity<ActivityServersocketBinding> implements ServerSocketViewContract{

    private ServerSocketPresenter serverSocketPresenter;

    @Override
    protected int setLayout() {
        return R.layout.activity_serversocket;
    }

    @Override
    protected void initView() {
        serverSocketPresenter = new ServerSocketPresenter(this);
        dataBinding.setPresenter(serverSocketPresenter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showMessage(String msg) {
        TextView textView = new TextView(this);
        textView.setText(msg);
        dataBinding.lly.addView(textView);
    }

    @Override
    protected void onDestroy() {
        serverSocketPresenter.close();
        super.onDestroy();
    }
}
