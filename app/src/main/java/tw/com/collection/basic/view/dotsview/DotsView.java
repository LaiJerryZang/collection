package tw.com.collection.basic.view.dotsview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;



public class DotsView extends FrameLayout {

    private int[] colors = new int[]{
            Color.parseColor("#039BE5"),
            Color.parseColor("#D32F2F"),
            Color.parseColor("#FFB300"),
            Color.parseColor("#6D4C41"),
            Color.parseColor("#ED1B2F"),
            Color.parseColor("#006A4F"),
    };

    private int numberOfCircles = 5;
    private List<CircleShape> circles = new ArrayList<>();

    private AnimationHandler animationHandler = new AnimationHandler();

    public DotsView(Context context) {
        super(context);
        init(context);
    }

    public DotsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DotsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setWillNotDraw(false);//要重寫viewgroup的ondraw要調用的方法

//        Runnable 對象中的方法會在 View 的 measure、layout 等事件完成後觸發。
        post(()->{

            //建立數個circles shape 加到陣列
            if(circles.isEmpty()){
                for (int i = 0; i < numberOfCircles; i++) {
                    circles.add(new CircleShape());
                }
            }

            final float radius = dpToPx(this, 8);//圓半徑
            final float distanceBetweenCircles = dpToPx(this, 16);//每個圓的距離

            //設定屬性
            for (int i = 0; i < circles.size(); i++) {
                circles.get(i)
                        .setRadius(radius)//半徑
                        .setColor(colors[i % colors.length])//設定圓color
                        .centerVertical(getHeight())
                        .setCenterX((getHeight() / 2f) + ((radius + distanceBetweenCircles) * (i - circles.size() / 2)));
            }
        });
    }

//    @Override
//    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
//        super.onSizeChanged(width, height, oldw, oldh);

//        if(circles.isEmpty()){
//            for (int i = 0; i < numberOfCircles; i++) {
//                circles.add(new CircleShape());
//            }
//        }
//
//        final float radius = dpToPx(this, 6);
//        final float distanceBetweenCircles = dpToPx(this, 16);
//
//        for (int i = 0; i < circles.size(); i++) {
//            circles.get(i)
//                    .setRadius(radius)
//                    .setColor(colors[i % colors.length])
//                    .centerVertical(height)
//                    .setCenterX((height / 2f) + ((radius + distanceBetweenCircles) * (i - circles.size() / 2)));
//        }
//    }

    public void animateView() {
        animationHandler.start();
    }

    public void cancelAnimaton() {
        animationHandler.cancel();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(this::animateView);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelAnimaton();
    }

    private void animationLoop(@NonNull final AnimationHandler handler, int animateCircleIndex) {
        final float jump = dpToPx(this, 15);

        final CircleShape firstCircle = circles.get(animateCircleIndex);
        final CircleShape nextCircle = circles.get(animateCircleIndex + 1);

        new ShapeAnimator(this)
                .play(
                        firstCircle.animate().centerXTo(nextCircle.getCenterX()),
                        firstCircle.animate().centerYPlus(-1 * jump, 0),
                        nextCircle.animate().centerXTo(firstCircle.getCenterX())
                )
                .onAnimationEnd(() -> {
                    //intervert circles
                    circles.set(animateCircleIndex, nextCircle);
                    circles.set(animateCircleIndex + 1, firstCircle);

                    handler.next();
                })
                .start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (CircleShape circle : circles) {
            circle.onDraw(canvas);
        }
    }

    private class AnimationHandler extends Handler {
        private final int CONTINUE_FIRST_ANIM = 1;
        private int animateCircleIndex = 0;

        public AnimationHandler() {
            super(Looper.getMainLooper());
        }

        public void start() {
            sendEmptyMessage(CONTINUE_FIRST_ANIM);
        }

        public void next() {
            animateCircleIndex++;
            if (animateCircleIndex == circles.size() - 1) {
                animateCircleIndex = 0;
            }
            sendEmptyMessage(CONTINUE_FIRST_ANIM);
        }

        public void cancel() {
            removeMessages(CONTINUE_FIRST_ANIM);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CONTINUE_FIRST_ANIM:
                    animationLoop(this, animateCircleIndex);
                    break;
                default:
                    break;
            }
        }
    }

    public static float dpToPx(Context context, int dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPx(View view, int size) {
        return dpToPx(view.getContext(), size);
    }
}
