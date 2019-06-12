package tw.com.collection.mvp.socket;

import android.widget.TextView;

import tw.com.collection.R;
import tw.com.collection.basic.base.BaseActivity;
import tw.com.collection.databinding.ActivitySocketBinding;

public class SocketActivity extends BaseActivity<ActivitySocketBinding> implements SocketViewContract{

    private SocketPresenter socketPresenter;

    @Override
    protected int setLayout() {
        return R.layout.activity_socket;
    }

    @Override
    protected void initView() {
        socketPresenter = new SocketPresenter(this);
        dataBinding.setPresenter(socketPresenter);
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
        socketPresenter.close();
        super.onDestroy();
    }
}
