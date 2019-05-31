package tw.com.collection.mvp.item;

import android.view.View;

import tw.com.collection.BR;
import tw.com.collection.R;
import tw.com.collection.basic.base.MultiTypeAdapter;

public class TextItem extends BaseItem {
    private ItemContract itemContract;
    private String content;

    @Override
    public int getLayout() {
        return R.layout.item_tv;
    }

    @Override
    public int getVariableId() {
        return BR.item;
    }

    public String getText() {
        return content;
    }

    public TextItem(String content, ItemContract contract, MultiTypeAdapter adapter) {
        this.content = content;
        this.itemContract = contract;
        setOnClickListener(v -> {
            itemContract.onClick(v,adapter.findPos(this));
        });
    }


}
