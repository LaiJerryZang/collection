package tw.com.collection.mvp.socket;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import tw.com.collection.R;
import tw.com.collection.basic.base.BaseActivity;
import tw.com.collection.basic.socket.SocketClient;
import tw.com.collection.basic.utils.CommonUtil;
import tw.com.collection.databinding.ActivitySocketBinding;

public class SocketActivity extends BaseActivity<ActivitySocketBinding> implements SocketClient.CallBack {

    SocketClient socketClient;

    @Override
    protected int setLayout() {
        return R.layout.activity_socket;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        socketClient = new SocketClient("192.168.0.167",6666,this);
        socketClient.start();
    }

    @Override
    public void message(String msg) {
        TextView textView = new TextView(this);
        textView.setText(msg);
        dataBinding.lly.addView(textView);
    }

    public void send(View view){
        CommonUtil.showToast(this,"send");
        Log.d("write_aaa","send");
        String msg = dataBinding.edt.getText().toString();
        socketClient.sendMessage(msg);
    }
}
