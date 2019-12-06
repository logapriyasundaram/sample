package com.client.android.app.bunk.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.client.android.app.bunk.R;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class CustomDialogModal extends DialogFragment implements View.OnClickListener {

    private View rootView;
    public static boolean isDialogShown = false;
    private RelativeLayout modalContainer;
    private RelativeLayout dailogLayout;
    LinearLayout default_layout, confirm_layout;
    private Typeface primaryFont, secondaryFont;
    Activity actvity;
    private AsyncTask<?, ?, ?> mTask;

    static Activity activity;
    private static CustomDialogModal modalDailog;
    private static DialogClickListner mDialogClickListner;
    private Typeface typeface;
    private InputStream fontStream;

    private ImageView imgAlert;
    private TextView tvMainText;
    private TextView tvSubText;
    private  static int width;
    private  static int height;
    private String headText, buttonText, contentText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initializeViews(inflater, container);
        getValues();
//        CustomizeModalViews(modalType);
        setValuesToViews();
        initializeListner();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return rootView;
    }

    /**
     * Method to initiating DialogFragment for defaultModal type.
     *
     * @param context
     * @param DisplayMessage
     * @param actionButtonText
     * @param modalType
     * @return DefaultModalDailog.
     */


    /**
     * Method to initiating DialogFragment for defaultModal type.
     *
     * @param context
     * @param DisplayMessage
     * @param actionButtonText
     * @param modalType
     * @return DefaultModalDailog.
     */
    public static CustomDialogModal newInstance(Activity context, String head, String DisplayMessage,
                                                String actionButtonText, DialogType modalType, DialogClickListner dialogClickListner) {
        try {
            FragmentManager fm = context.getFragmentManager();
            modalDailog = new CustomDialogModal();
            activity = context;
            mDialogClickListner = dialogClickListner;
            Bundle args = new Bundle();
            args.putString("head", head);
            args.putString("content", DisplayMessage);
            args.putString("button_text", actionButtonText);
            args.putSerializable("model_type", modalType);
            modalDailog.setCancelable(false);
            modalDailog.setArguments(args);

            modalDailog.show(fm, "Modelpoup");
            isDialogShown = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return modalDailog;
    }


    public static CustomDialogModal newInstance(Activity context,  String head, String DisplayMessage, DialogType alert,int screenwidth,int screenheight, DialogClickListner dialogClickListner) {
        try {
            FragmentManager fm = context.getFragmentManager();
            modalDailog = new CustomDialogModal();
            activity = context;
            mDialogClickListner = dialogClickListner;
            Bundle args = new Bundle();
            args.putString("head", head);
            args.putString("content", DisplayMessage);
            args.putSerializable("model_type", alert);
            modalDailog.setCancelable(false);
            modalDailog.setArguments(args);
            width=screenwidth;
            height=screenheight;

            modalDailog.show(fm, "Modelpoup");
            isDialogShown = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return modalDailog;

    }

    public void setDialogClickListner(DialogClickListner mDialogClickListner) {
        this.mDialogClickListner = mDialogClickListner;
    }

    /*private void setfontFamily() {
        // TODO Auto-generated method stub
        AssetManager mg = getResources().getAssets();
        String secondaryFont = getResources().getString(R.string.secondary_font);
        if (!TextUtils.isEmpty(secondaryFont)) {
            try {
                fontStream = mg.open(secondaryFont);
                if (fontStream != null) {
                    typeface = Typeface.createFromAsset(activity.getAssets(), secondaryFont);
                    contentView.setTypeface(typeface);
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        String primaryFont = getResources().getString(R.string.primary_font);
        if (!TextUtils.isEmpty(primaryFont)) {
            try {
                fontStream = mg.open(primaryFont);
                if (fontStream != null) {
                    typeface = Typeface.createFromAsset(activity.getAssets(), primaryFont);
                    headView.setTypeface(typeface);
                    okButton.setTypeface(typeface);
                    confirm_yes_btn.setTypeface(typeface);
                    confirm_no_btn.setTypeface(typeface);
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }


    }*/


    public enum DialogType {
        ALERT
    }


    private void initializeListner() {
        // TODO Auto-generated method stub
//        okButton.setOnClickListener(this);
//        confirm_yes_btn.setOnClickListener(this);
//        confirm_no_btn.setOnClickListener(this);

    }

    /**
     * Method to get Arguments
     *
     * @author Android - Askar
     */
    private void getValues() {

        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                getDialog().dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 3000);
        // TODO Auto-generated method stub
       /* headText = getArguments().getString("head");
        contentText = getArguments().getString("content");
        buttonText = getArguments().getString("button_text");
        // bagroundid = getArguments().getInt("baground_id");
        modalType = (DialogType)getArguments().getSerializable("model_type");
        if (modalType == DialogType.ALERTCONFIRMATION) {
            actionButtonLeftText = getArguments().getString("actionButtonLeftText");
            actionButtonRigntText = getArguments().getString("actionButtonRigntText");
            confirm_layout.setVisibility(View.VISIBLE);
            headView.setVisibility(View.VISIBLE);
            default_layout.setVisibility(View.GONE);

        }else if(modalType == DialogType.ALERTDELETE){
            actionButtonLeftText = getArguments().getString("actionButtonLeftText");
            actionButtonRigntText = getArguments().getString("actionButtonRigntText");
            confirm_layout.setVisibility(View.VISIBLE);
            headView.setVisibility(View.GONE);
            default_layout.setVisibility(View.GONE);
        }else if(modalType == DialogType.ALERTTITLE){
            confirm_layout.setVisibility(View.GONE);
            headView.setVisibility(View.VISIBLE);
            default_layout.setVisibility(View.VISIBLE);
        }else{
            default_layout.setVisibility(View.VISIBLE);
            confirm_layout.setVisibility(View.GONE);
            headView.setVisibility(View.GONE);
        }*/
    }

    /**
     * Method to set Values to the views
     *
     * @author Android - Askar
     */
    private void setValuesToViews() {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // TODO Auto-generated method stub
        int imgIconWidth = (int) (width * 0.085);
        int imgIconHeight = (int) (width * 0.085);

        headText = getArguments().getString("head");
        contentText = getArguments().getString("content");

        RelativeLayout.LayoutParams imgLayParams = (RelativeLayout.LayoutParams) imgAlert.getLayoutParams();
        imgLayParams.width = imgIconWidth;
        imgLayParams.height = imgIconHeight;
        imgAlert.setLayoutParams(imgLayParams);

        tvMainText.setText(headText);
        tvSubText.setText(contentText);
        /*headView.setText(headText);
        contentView.setText(contentText);

        if (modalType == DialogType.ALERTCONFIRMATION) {
            confirm_yes_btn.setText(actionButtonLeftText);
            confirm_no_btn.setText(actionButtonRigntText);
        } if (modalType== DialogType.ALERTDELETE){
            confirm_yes_btn.setText(actionButtonLeftText);
            confirm_no_btn.setText(actionButtonRigntText);
        }else {
            okButton.setText(buttonText);
        }*/

        // dailogLayout.setBackgroundResource(bagroundid);
//        getDialog().getWindow().getDecorView().getBackground().setAlpha(10);
       // modalContainer.getBackground().setAlpha(220);
        // dailogLayout.setBackgroundColor(getResources().getColor(R.color.abc_search_url_text_normal));


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getDialog().getWindow().setGravity(Gravity.TOP);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.CustomAnimations;
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * @param inflater,container
     * @desc method to initializeViews.
     *
     */
    private void initializeViews(LayoutInflater inflater, ViewGroup container) {
        // TODO Auto-generated method stub
        rootView = inflater.inflate(R.layout.dialog_fragment, container, false);

        modalContainer = rootView.findViewById(R.id.modal_container);
        imgAlert = rootView.findViewById(R.id.imgAlert);
        tvMainText = rootView.findViewById(R.id.tvMainText);
        tvSubText = rootView.findViewById(R.id.tvSubText);


    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {

            DisplayMetrics displaymetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            // int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;
            // int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int leftRightSpace = (int) (width * 0.0153);
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(leftRightSpace * 56, height);
//            dailogLayout.setBackground(getResources().getDrawable(R.drawable.dialog_bg));
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams windowParams = window.getAttributes();
            windowParams.dimAmount = 0.50f;
            windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(windowParams);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        this.actvity = activity;
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

       /* if (v.getId() == R.id.okButton) {
            if(mDialogClickListner != null)
                mDialogClickListner.onClick(v.getId(), getDialog(), null);
        } else if (v.getId() == R.id.confirm_yes_btn) {
            if(mDialogClickListner != null)
                mDialogClickListner.onClick(v.getId(), getDialog(), leftButtonName);
        } else if (v.getId() == R.id.confirm_no_btn) {
            if(mDialogClickListner != null)
                mDialogClickListner.onClick(v.getId(), getDialog(), rightButtonName);
        }*/

    }



    public void dismissDialog() {
        if (getDialog() != null) {
            getDialog().dismiss();
        }
    }

    public boolean isShowing() {

//        if (getDialog() != null) {
//
//        }
        if (getDialog().isShowing()) {
            return true;
        } else {
            return false;
        }
    }
}
