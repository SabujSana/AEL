package com.example.sabuj.ael.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.format.DateUtils;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.example.sabuj.ael.R;
import com.nineoldandroids.animation.FloatEvaluator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.util.Property;

public class AboutFragment extends Fragment {
    TextView tvAppName, tvVersion;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvAppName = view.findViewById(R.id.tv_app_name);
        tvVersion = view.findViewById(R.id.tv_version);
        String text = tvAppName.getText().toString();

        AnimatedColorSpan span = new AnimatedColorSpan(getActivity());
        final SpannableString spannableString = new SpannableString(text);
        int start = 0;
        int end = text.length();
        spannableString.setSpan(span, start, end, 0);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                span, ANIMATED_COLOR_SPAN_FLOAT_PROPERTY, 0, 100);
        objectAnimator.setEvaluator(new FloatEvaluator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tvAppName.setText(spannableString);
            }
        });
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(DateUtils.MINUTE_IN_MILLIS * 3);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();

        Shader shader = new LinearGradient(0, 0, 0, tvVersion.getTextSize()
                , Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
        tvVersion.getPaint().setShader(shader);
    }

    private static final Property<AnimatedColorSpan, Float> ANIMATED_COLOR_SPAN_FLOAT_PROPERTY
            = new Property<AnimatedColorSpan, Float>(Float.class, "ANIMATED_COLOR_SPAN_FLOAT_PROPERTY") {
        @Override
        public void set(AnimatedColorSpan span, Float value) {
            span.setTranslateXPercentage(value);
        }

        @Override
        public Float get(AnimatedColorSpan span) {
            return span.getTranslateXPercentage();
        }
    };

    private static class AnimatedColorSpan extends CharacterStyle implements UpdateAppearance {
        private final int[] colors;
        private Shader shader = null;
        private Matrix matrix = new Matrix();
        private float translateXPercentage = 0;

        public AnimatedColorSpan(Context context) {
            colors = context.getResources().getIntArray(R.array.rainbow);
        }

        public void setTranslateXPercentage(float percentage) {
            translateXPercentage = percentage;
        }

        public float getTranslateXPercentage() {
            return translateXPercentage;
        }

        @Override
        public void updateDrawState(TextPaint paint) {
            paint.setStyle(Paint.Style.FILL);
            float width = paint.getTextSize() * colors.length;
            if (shader == null) {
                shader = new LinearGradient(0, 0, 0, width, colors, null,
                        Shader.TileMode.MIRROR);
            }
            matrix.reset();
            matrix.setRotate(90);
            matrix.postTranslate(width * translateXPercentage, 0);
            shader.setLocalMatrix(matrix);
            paint.setShader(shader);
        }
    }

}
