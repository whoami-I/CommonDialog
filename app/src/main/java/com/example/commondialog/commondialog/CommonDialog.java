package com.example.commondialog.commondialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;


public class CommonDialog extends Dialog {

    private AlertController mAlert;

    public View getView(int viewId) {
        return getAlertController().getView(viewId);
    }

    public void setText(int viewId, CharSequence text) {
        getAlertController().setText(viewId, text);
    }

    public void setClickListener(int viewId, View.OnClickListener l) {
        getAlertController().setClickListener(viewId, l);
    }

    private AlertController getAlertController() {
        return mAlert;
    }

    protected CommonDialog(Context context, int theme) {
        super(context, theme);
        mAlert = new AlertController(getContext(), this, getWindow());
    }

    /**
     * Builder design pattern
     */
    public static class Builder {
        /**
         * P is a collection that save all the information about this dialog
         */
        private final AlertController.AlertParams P;
        private int mTheme;


        public Builder(Context context) {
            this(context, 0);
        }

        public Builder(Context context, int theme) {
            P = new AlertController.AlertParams(context);
            mTheme = theme;
        }

        /**
         * @return context
         */
        public Context getContext() {
            return P.mContext;
        }

        /**
         * @param viewId id of view
         * @param text   text of TextView
         * @return this builder
         */
        public Builder setText(int viewId, CharSequence text) {
            P.mTextMap.put(viewId, text);
            return this;
        }

        /**
         * @param viewId id of view
         * @param l      click listener of view
         * @return this builder
         */
        public Builder setClickListener(int viewId, View.OnClickListener l) {
            P.mListenerMap.put(viewId, l);
            return this;
        }

        public Builder setContentView(int layoutId) {
            P.mLayoutId = layoutId;
            return this;
        }

        public Builder setContentView(View v) {
            P.mLayoutView = v;
            return this;
        }

        /**
         * @param full whether the dialog is full width
         * @return
         */
        public Builder fullWidth(boolean full) {
            P.mFull = full;
            return this;
        }

        /**
         * @param isFromBottom whether the dialog is at bottom
         * @return
         */
        public Builder bottom(boolean isFromBottom) {
            P.mFromBottom = isFromBottom;
            return this;
        }

        /**
         * @param animation animation of dialog window
         * @return
         */
        public Builder setAnimation(int animation) {
            P.mAnimation = animation;
            return this;
        }

        public CommonDialog create() {
            final CommonDialog dialog = new CommonDialog(P.mContext, mTheme);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            dialog.setCanceledOnTouchOutside(P.mCancelable);
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        /**
         * @return show the dialog
         */
        public CommonDialog show() {
            CommonDialog dialog = create();
            dialog.show();
            return dialog;
        }

    }

}
