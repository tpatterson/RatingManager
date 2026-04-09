package com.robj.ratingmanager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

/**
 * Created by Rob J on 16/09/17.
 */

class RatingDialog {

    private final RatingDialogOptions ratingDialogOptions;

    private String feedbackEmailAddress;
    private boolean showFeedbackOption;

    protected void setShowFeedbackOption(boolean showFeedbackOption, String feedbackEmail) {
        this.showFeedbackOption = showFeedbackOption;
        this.feedbackEmailAddress = feedbackEmail;
    }

    public void showRatingPopup(final Context context) {
        if(!showFeedbackOption) {
            showLeaveRatingPopup(context);
            return;
        }

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_rating, null);
        AlertDialog dialog = new AlertDialog.Builder(context, ratingDialogOptions.dialogThemeResId)
                .setView(view)
                .setCancelable(false)
                .create();

        TextView title = view.findViewById(R.id.dialog_title);
        TextView message = view.findViewById(R.id.dialog_message);
        ImageButton closeBtn = view.findViewById(R.id.dialog_close);
        Button neutralBtn = view.findViewById(R.id.btn_neutral);
        Button negativeBtn = view.findViewById(R.id.btn_negative);
        Button positiveBtn = view.findViewById(R.id.btn_positive);

        title.setText(ratingDialogOptions.initialPopupMessage);
        message.setVisibility(View.GONE);

        neutralBtn.setText(ratingDialogOptions.initialPopupLaterBtnText);
        negativeBtn.setText(ratingDialogOptions.initialPopupNegativeBtnText);
        positiveBtn.setText(ratingDialogOptions.initialPopupPositiveBtnText);
        applyTypeface(title, message, neutralBtn, negativeBtn, positiveBtn);

        closeBtn.setOnClickListener(v -> {
            DataManager.setAskLater(context);
            dialog.dismiss();
        });

        neutralBtn.setOnClickListener(v -> {
            if(ratingDialogOptions.onInitialLaterClickListener != null)
                ratingDialogOptions.onInitialLaterClickListener.onClick();
            DataManager.setAskLater(context);
            dialog.dismiss();
        });

        negativeBtn.setOnClickListener(v -> {
            if(ratingDialogOptions.onInitialNegativeClickListener != null)
                ratingDialogOptions.onInitialNegativeClickListener.onClick();
            dialog.dismiss();
            showFeedbackPopup(context);
        });

        positiveBtn.setOnClickListener(v -> {
            if(ratingDialogOptions.onInitialPositiveClickListener != null)
                ratingDialogOptions.onInitialPositiveClickListener.onClick();
            dialog.dismiss();
            showLeaveRatingPopup(context);
        });

        dialog.show();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_dialog_rounded));
        }    }

    public void showLeaveRatingPopup(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_rating, null);
        AlertDialog dialog = new AlertDialog.Builder(context, ratingDialogOptions.dialogThemeResId)
                .setView(view)
                .setCancelable(false)
                .create();

        TextView title = view.findViewById(R.id.dialog_title);
        TextView message = view.findViewById(R.id.dialog_message);
        ImageButton closeBtn = view.findViewById(R.id.dialog_close);
        Button neutralBtn = view.findViewById(R.id.btn_neutral);
        Button negativeBtn = view.findViewById(R.id.btn_negative);
        Button positiveBtn = view.findViewById(R.id.btn_positive);

        title.setText(ratingDialogOptions.ratingPopupTitle);
        message.setText(ratingDialogOptions.ratingPopupMessage);

        neutralBtn.setText(ratingDialogOptions.ratingPopupLaterBtnText);
        negativeBtn.setText(ratingDialogOptions.ratingPopupNeverBtnText);
        positiveBtn.setText(ratingDialogOptions.ratingPopupPositiveBtnText);
        applyTypeface(title, message, neutralBtn, negativeBtn, positiveBtn);

        closeBtn.setOnClickListener(v -> {
            DataManager.setAskLater(context);
            dialog.dismiss();
        });

        neutralBtn.setOnClickListener(v -> {
            if(ratingDialogOptions.onRatingLaterClickListener != null)
                ratingDialogOptions.onRatingLaterClickListener.onClick();
            DataManager.setAskLater(context);
            dialog.dismiss();
        });

        negativeBtn.setOnClickListener(v -> {
            if(ratingDialogOptions.onRatingNegativeClickListener != null)
                ratingDialogOptions.onRatingNegativeClickListener.onClick();
            DataManager.setNeverAsk(context);
            dialog.dismiss();
        });

        positiveBtn.setOnClickListener(v -> {
            if(ratingDialogOptions.onRatingPositiveClickListener != null)
                ratingDialogOptions.onRatingPositiveClickListener.onClick();
            DataManager.setRatingLeft(context);
            dialog.dismiss();
            launchUrl(context, ratingDialogOptions.ratingUrl);
        });

        dialog.show();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_dialog_rounded));
        }    }

    private void showFeedbackPopup(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_rating, null);
        AlertDialog dialog = new AlertDialog.Builder(context, ratingDialogOptions.dialogThemeResId)
                .setView(view)
                .setCancelable(false)
                .create();

        TextView title = view.findViewById(R.id.dialog_title);
        TextView message = view.findViewById(R.id.dialog_message);
        ImageButton closeBtn = view.findViewById(R.id.dialog_close);
        Button neutralBtn = view.findViewById(R.id.btn_neutral);
        Button negativeBtn = view.findViewById(R.id.btn_negative);
        Button positiveBtn = view.findViewById(R.id.btn_positive);

        title.setText(ratingDialogOptions.feedbackPopupTitle);
        message.setText(ratingDialogOptions.feedbackPopupMessage);

        neutralBtn.setText(ratingDialogOptions.feedbackPopupLaterBtnText);
        negativeBtn.setText(ratingDialogOptions.feedbackPopupNegativeBtnText);
        positiveBtn.setText(ratingDialogOptions.feedbackPopupPositiveBtnText);
        applyTypeface(title, message, neutralBtn, negativeBtn, positiveBtn);

        closeBtn.setOnClickListener(v -> {
            DataManager.setAskLater(context);
            dialog.dismiss();
        });

        neutralBtn.setOnClickListener(v -> {
            if(ratingDialogOptions.onFeedbackLaterClickListener != null)
                ratingDialogOptions.onFeedbackLaterClickListener.onClick();
            DataManager.setAskLater(context);
            dialog.dismiss();
        });

        negativeBtn.setOnClickListener(v -> {
            if(ratingDialogOptions.onFeedbackNegativeClickListener != null)
                ratingDialogOptions.onFeedbackNegativeClickListener.onClick();
            DataManager.setNeverAsk(context);
            dialog.dismiss();
        });

        positiveBtn.setOnClickListener(v -> {
            if(ratingDialogOptions.onFeedbackPositiveClickListener != null)
                ratingDialogOptions.onFeedbackPositiveClickListener.onClick();
            DataManager.setFeedbackLeft(context);
            dialog.dismiss();
            launchEmailIntent(context, feedbackEmailAddress, ratingDialogOptions.feedbackEmailSubject, ratingDialogOptions.feedbackEmailBody);
        });

        dialog.show();

        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_dialog_rounded));
        }
    }

    private void applyTypeface(TextView title, TextView message, Button neutralBtn, Button negativeBtn, Button positiveBtn) {
        Typeface boldTypeface = ratingDialogOptions.boldTypeface;
        if (boldTypeface != null) {
            title.setTypeface(boldTypeface, Typeface.BOLD);
            neutralBtn.setTypeface(boldTypeface, Typeface.BOLD);
            neutralBtn.setTypeface(boldTypeface, Typeface.BOLD);
            negativeBtn.setTypeface(boldTypeface, Typeface.BOLD);
            positiveBtn.setTypeface(boldTypeface, Typeface.BOLD);
        }

        Typeface typeface = ratingDialogOptions.regularTypeface;
        if (typeface != null) {
            message.setTypeface(typeface);
        }
    }

    private static void launchEmailIntent(Context context, String email, String subject, String body) {
        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT, body);
        i = Intent.createChooser(i, context.getString(R.string.dialog_open_with));
        context.startActivity(i);
    }

    private static void launchUrl(Context context, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_NEW_TASK);
        i = Intent.createChooser(i, context.getString(R.string.dialog_open_with));
        context.startActivity(i);
    }

    RatingDialog(RatingDialogOptions ratingDialogOptions) {
        this.ratingDialogOptions = ratingDialogOptions;

    }

}
