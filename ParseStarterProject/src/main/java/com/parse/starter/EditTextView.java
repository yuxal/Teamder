package com.parse.starter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by yuvalsela on 12/29/15.
 */
public class EditTextView extends RelativeLayout {
    private OnAddListener mListener;
    private EditText mEditText;
    private TextView mTextView;
    private FloatingActionButton mAddBtn;
    private int mType;

    public static final int TYPE_MEMBER = 0;
    public static final int TYPE_NEEDED = 1;

    public EditTextView(Context context, OnAddListener listener, int type) {
        super(context);
        mListener = listener;
        mType = type;
        init();
    }

    public EditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View v = inflate(getContext(), R.layout.view_edit_text, null);
        addView(v);
        mEditText = (EditText)findViewById(R.id.edit_text);
        mTextView = (TextView)findViewById(R.id.textview);
        mAddBtn = (FloatingActionButton)findViewById(R.id.add_button);
        mAddBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddBtn.setVisibility(View.GONE);
                mEditText.setVisibility(View.GONE);
                mTextView.setVisibility(View.VISIBLE);
                String name = mEditText.getText().toString();

                mTextView.setText(name);
                mListener.onAdded(name, mType);
            }
        });

    }

    public interface OnAddListener {
        public void onAdded(String name, int type);
    }
}
