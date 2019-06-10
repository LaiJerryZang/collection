package tw.com.collection.basic.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import tw.com.collection.basic.utils.CommonUtil;

/**
 *  縮放按鈕
 */
public class ScaleButton extends AppCompatButton {
    boolean isMove = false;
    private OnClickListener onClickListener;
    private Handler handler = new Handler();

    public ScaleButton(Context context) {
        super(context);
    }

    public ScaleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public interface OnClickListener {
        void onClick(View v);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int action = event.getAction();
        // Listening for the down and up touch events
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                handler.post(() -> Scale(0.9f));
                break;

            case MotionEvent.ACTION_MOVE:
                isMove = true;
                if (!isMotionEventInsideView(event)) {//判斷是否移動出button
                    // User moved outside bounds
                    isMove = false;
                    handler.postDelayed(() -> Scale(1.0f), 150);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!isMove) {
                    handler.postDelayed(this::performClick, 150);
                } else {
                    handler.postDelayed(() -> Scale(1.0f), 150);
                }
                isMove = false;

                break;
        }
        return true;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public boolean performClick() {
        // Calls the super implementation, which generates an AccessibilityEvent
        // and calls the onClick() listener on the view, if any
        super.performClick();

        Scale(1.0f);

        if (onClickListener == null) {
            return true;
        }
        // Handle the action for the custom click here
        if (CommonUtil.isFastToClick()) {
            onClickListener.onClick(this);
        }

        return true;
    }

    private void Scale(float value) {
        setScaleX(value);
        setScaleY(value);
    }

    private boolean isMotionEventInsideView(MotionEvent event) {
        Rect viewRect = new Rect(
                getLeft(),
                getTop(),
                getRight(),
                getBottom()
        );

        return viewRect.contains(
                getLeft() + (int) event.getX(),
                getTop() + (int) event.getY()
        );
    }
}
