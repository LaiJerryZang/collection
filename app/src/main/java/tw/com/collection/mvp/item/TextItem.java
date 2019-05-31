package tw.com.collection.mvp.item;

import tw.com.collection.BR;
import tw.com.collection.R;
import tw.com.collection.basic.base.MultiTypeAdapter;

public class TextItem implements MultiTypeAdapter.IItem {

    private String content;

    @Override
    public int getLayout() {
        return R.layout.item_tv;
    }

    @Override
    public int getVariableId() {
        return BR.item;
    }

    public TextItem(String content){
        this.content = content;
    }

    public String getText(){
        return content;
    }

}
