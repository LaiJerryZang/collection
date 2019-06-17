package tw.com.collection.basic.view.dotsview;

import android.view.View;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class ViewInvalidateListener implements InvalidateListener {
    private Reference<View> viewReference;

    public ViewInvalidateListener(View view) {
        this.viewReference = new WeakReference<View>(view);
    }

    @Override
    public void invalidate() {
        final View view = viewReference.get();
        if (view != null) {
            view.postInvalidate();
        }
    }
}