package com.example.commondialog.commondialog;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DialogViewHelper {
	private Context mContext;
	private View mContentView;

	public DialogViewHelper(Context context, int LayoutResId) {
		this.mContext = context;
		mContentView = View.inflate(context, LayoutResId, null);
	}

	public DialogViewHelper(Context context, View contentView) {
		this.mContext = context;
		mContentView = contentView;
	}

	public View getContentView() {
		return mContentView;
	}

	public View getView(int viewId) {
		return getContentView().findViewById(viewId);
	}

	public void setText(int viewId, CharSequence text) {
		TextView tv = mContentView.findViewById(viewId);
		if (tv != null) {
			tv.setText(text);
		}
	}

	public void setClickListener(int viewId, OnClickListener l) {
		View view = mContentView.findViewById(viewId);
		if (view != null)
			view.setOnClickListener(l);
	}
}
