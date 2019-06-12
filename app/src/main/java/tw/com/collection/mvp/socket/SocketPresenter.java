package tw.com.collection.mvp.socket;

import tw.com.collection.basic.socket.SocketClient;

public class SocketPresenter  implements SocketClient.CallBack {

    private SocketViewContract socketViewContract;

    private SocketClient socketClient;

    public SocketPresenter(SocketViewContract socketViewContract){
        this.socketViewContract = socketViewContract;
        socketClient = new SocketClient("192.168.0.167",6666,this);
        socketClient.start();
    }


    public void send(String msg){
        socketClient.sendMessage(msg);
    }

    public void close(){
        socketClient.close();
    }

    @Override
    public void message(String msg) {
        socketViewContract.showMessage(msg);
    }
}
