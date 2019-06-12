package tw.com.collection.mvp.serversocket;

import android.widget.TextView;

import tw.com.collection.R;
import tw.com.collection.basic.base.BaseActivity;
import tw.com.collection.basic.socket.SocketServer;
import tw.com.collection.databinding.ActivityServersocketBinding;

public class ServerSocketActivity extends BaseActivity<ActivityServersocketBinding> implements SocketServer.CallBack {
    @Override
    protected int setLayout() {
        return R.layout.activity_serversocket;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        new SocketServer(6666,this).start();
    }

    @Override
    public void message(String msg) {
        TextView textView = new TextView(this);
        textView.setText(msg);
        
    }
}
