package com.example.commondialog.commondialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;


class AlertController {
	private CommonDialog mCommonDialog;
	private Window mWindow;
	private DialogViewHelper mViewHelper;

	public AlertController(Context context, CommonDialog commonDialog,
			Window window) {
		this.mCommonDialog = commonDialog;
		this.mWindow = window;
	}

	public CommonDialog getDialog() {
		return mCommonDialog;
	}

	public Window getWindow() {
		return mWindow;
	}

	public View getView(int viewId) {
		return getDialogViewHelper().getView(viewId);
	}

	private void setViewHelper(DialogViewHelper viewHelper) {
		mViewHelper = viewHelper;
	}

	public void setText(int viewId, CharSequence text) {
		getDialogViewHelper().setText(viewId, text);
	}

	public DialogViewHelper getDialogViewHelper() {
		return mViewHelper;
	}

	public void setClickListener(int viewId, OnClickListener l) {
		getDialogViewHelper().setClickListener(viewId, l);
	}

	static class AlertParams {
		public Context mContext;
		public boolean mCancelable = true;
		public boolean mCancelableOutsideDialog = true;
		public DialogInterface.OnCancelListener mOnCancelListener;
		public DialogInterface.OnDismissListener mOnDismissListener;
		public DialogInterface.OnKeyListener mOnKeyListener;
		public int mLayoutId;
		public View mLayoutView;

		public boolean mFull;
		public boolean mFromBottom;

		public SparseArray<CharSequence> mTextMap = new SparseArray<CharSequence>();
		public SparseArray<OnClickListener> mListenerMap = new SparseArray<OnClickListener>();
		/*
		 */
		public int mAnimation;

		public AlertParams(Context context) {
			mContext = context;
			mFull = false;
			mFromBottom = false;
		}

		public void apply(AlertController dialog) {
			DialogViewHelper viewHelper = null;
			if (mLayoutId != 0) {
				viewHelper = new DialogViewHelper(mContext, mLayoutId);
			}
			if (mLayoutView != null && viewHelper == null) {
				viewHelper = new DialogViewHelper(mContext, mLayoutView);
			}
			if (viewHelper == null) {
				throw new RuntimeException("must use builder.setContentView()");
			}
			dialog.setViewHelper(viewHelper);
			dialog.getDialog().setContentView(viewHelper.getContentView());

			for (int i = 0; i < mTextMap.size(); i++) {
				viewHelper.setText(mTextMap.keyAt(i), mTextMap.valueAt(i));
			}

			for (int i = 0; i < mListenerMap.size(); i++) {
				viewHelper.setClickListener(mListenerMap.keyAt(i),
						mListenerMap.valueAt(i));
			}

			Window window = dialog.getWindow();
			LayoutParams layoutParams = window.getAttributes();
			if (mFull) {
				layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
			}
			window.setAttributes(layoutParams);
			if (mFromBottom) {
				window.setGravity(Gravity.BOTTOM);
			}

			if (mAnimation != 0) {
				window.setWindowAnimations(mAnimation);
			}

		}
	}

}
