package com.robj.ratingmanager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.util.StateSet;
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

    // region Public entry points

    public void showRatingPopup(final Context context) {
        if (!showFeedbackOption) {
            showLeaveRatingPopup(context);
            return;
        }
        if (ratingDialogOptions.useCustomLayout) {
            showRatingPopupCustom(context);
        } else {
            showRatingPopupLegacy(context);
        }
    }

    public void showLeaveRatingPopup(final Context context) {
        if (ratingDialogOptions.useCustomLayout) {
            showLeaveRatingPopupCustom(context);
        } else {
            showLeaveRatingPopupLegacy(context);
        }
    }

    private void showFeedbackPopup(final Context context) {
        if (ratingDialogOptions.useCustomLayout) {
            showFeedbackPopupCustom(context);
        } else {
            showFeedbackPopupLegacy(context);
        }
    }

    // endregion

    // region Legacy dialog (theme-driven AlertDialog)

    private void showRatingPopupLegacy(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, ratingDialogOptions.dialogThemeResId);
        builder.setMessage(ratingDialogOptions.initialPopupMessage);
        builder.setPositiveButton(ratingDialogOptions.initialPopupPositiveBtnText, (dialog, which) -> {
            if (ratingDialogOptions.onInitialPositiveClickListener != null)
                ratingDialogOptions.onInitialPositiveClickListener.onClick();
            showLeaveRatingPopup(context);
        });
        builder.setNegativeButton(ratingDialogOptions.initialPopupNegativeBtnText, (dialog, which) -> {
            if (ratingDialogOptions.onInitialNegativeClickListener != null)
                ratingDialogOptions.onInitialNegativeClickListener.onClick();
            showFeedbackPopup(context);
        });
        builder.setNeutralButton(ratingDialogOptions.initialPopupLaterBtnText, (dialog, which) -> {
            if (ratingDialogOptions.onInitialLaterClickListener != null)
                ratingDialogOptions.onInitialLaterClickListener.onClick();
            DataManager.setAskLater(context);
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void showLeaveRatingPopupLegacy(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, ratingDialogOptions.dialogThemeResId);
        builder.setTitle(ratingDialogOptions.ratingPopupTitle);
        builder.setMessage(ratingDialogOptions.ratingPopupMessage);
        builder.setPositiveButton(ratingDialogOptions.ratingPopupPositiveBtnText, (dialog, which) -> {
            if (ratingDialogOptions.onRatingPositiveClickListener != null)
                ratingDialogOptions.onRatingPositiveClickListener.onClick();
            DataManager.setRatingLeft(context);
            launchUrl(context, ratingDialogOptions.ratingUrl);
        });
        builder.setNegativeButton(ratingDialogOptions.ratingPopupLaterBtnText, (dialog, which) -> {
            if (ratingDialogOptions.onRatingLaterClickListener != null)
                ratingDialogOptions.onRatingLaterClickListener.onClick();
            DataManager.setAskLater(context);
        });
        builder.setNeutralButton(ratingDialogOptions.ratingPopupNeverBtnText, (dialog, which) -> {
            if (ratingDialogOptions.onRatingNegativeClickListener != null)
                ratingDialogOptions.onRatingNegativeClickListener.onClick();
            DataManager.setNeverAsk(context);
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void showFeedbackPopupLegacy(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, ratingDialogOptions.dialogThemeResId);
        builder.setTitle(ratingDialogOptions.feedbackPopupTitle);
        builder.setMessage(ratingDialogOptions.feedbackPopupMessage);
        builder.setPositiveButton(ratingDialogOptions.feedbackPopupPositiveBtnText, (dialog, which) -> {
            if (ratingDialogOptions.onFeedbackPositiveClickListener != null)
                ratingDialogOptions.onFeedbackPositiveClickListener.onClick();
            DataManager.setFeedbackLeft(context);
            launchEmailIntent(context, feedbackEmailAddress, ratingDialogOptions.feedbackEmailSubject, ratingDialogOptions.feedbackEmailBody);
        });
        builder.setNegativeButton(ratingDialogOptions.feedbackPopupLaterBtnText, (dialog, which) -> {
            if (ratingDialogOptions.onFeedbackLaterClickListener != null)
                ratingDialogOptions.onFeedbackLaterClickListener.onClick();
            DataManager.setAskLater(context);
        });
        builder.setNeutralButton(ratingDialogOptions.feedbackPopupNegativeBtnText, (dialog, which) -> {
            if (ratingDialogOptions.onFeedbackNegativeClickListener != null)
                ratingDialogOptions.onFeedbackNegativeClickListener.onClick();
            DataManager.setNeverAsk(context);
        });
        builder.setCancelable(false);
        builder.show();
    }

    // endregion

    // region Custom dialog (design-driven layout)

    private void showRatingPopupCustom(final Context context) {
        showCustomDialog(context,
                ratingDialogOptions.initialPopupMessage, null,
                ratingDialogOptions.initialPopupLaterBtnText,
                ratingDialogOptions.initialPopupNegativeBtnText,
                ratingDialogOptions.initialPopupPositiveBtnText,
                ratingDialogOptions.onInitialLaterClickListener,
                ratingDialogOptions.onInitialNegativeClickListener,
                ratingDialogOptions.onInitialPositiveClickListener,
                ctx -> { DataManager.setAskLater(ctx); },
                ctx -> { showFeedbackPopup(ctx); },
                ctx -> { showLeaveRatingPopup(ctx); });
    }

    private void showLeaveRatingPopupCustom(final Context context) {
        showCustomDialog(context,
                ratingDialogOptions.ratingPopupTitle, ratingDialogOptions.ratingPopupMessage,
                ratingDialogOptions.ratingPopupLaterBtnText,
                ratingDialogOptions.ratingPopupNeverBtnText,
                ratingDialogOptions.ratingPopupPositiveBtnText,
                ratingDialogOptions.onRatingLaterClickListener,
                ratingDialogOptions.onRatingNegativeClickListener,
                ratingDialogOptions.onRatingPositiveClickListener,
                ctx -> { DataManager.setAskLater(ctx); },
                ctx -> { DataManager.setNeverAsk(ctx); },
                ctx -> { DataManager.setRatingLeft(ctx); launchUrl(ctx, ratingDialogOptions.ratingUrl); });
    }

    private void showFeedbackPopupCustom(final Context context) {
        showCustomDialog(context,
                ratingDialogOptions.feedbackPopupTitle, ratingDialogOptions.feedbackPopupMessage,
                ratingDialogOptions.feedbackPopupLaterBtnText,
                ratingDialogOptions.feedbackPopupNegativeBtnText,
                ratingDialogOptions.feedbackPopupPositiveBtnText,
                ratingDialogOptions.onFeedbackLaterClickListener,
                ratingDialogOptions.onFeedbackNegativeClickListener,
                ratingDialogOptions.onFeedbackPositiveClickListener,
                ctx -> { DataManager.setAskLater(ctx); },
                ctx -> { DataManager.setNeverAsk(ctx); },
                ctx -> { DataManager.setFeedbackLeft(ctx); launchEmailIntent(ctx, feedbackEmailAddress, ratingDialogOptions.feedbackEmailSubject, ratingDialogOptions.feedbackEmailBody); });
    }

    private interface ContextAction {
        void run(Context context);
    }

    private void showCustomDialog(final Context context,
                                  String titleText, String messageText,
                                  String neutralText, String negativeText, String positiveText,
                                  OnRatingClickListener neutralListener, OnRatingClickListener negativeListener, OnRatingClickListener positiveListener,
                                  ContextAction neutralAction, ContextAction negativeAction, ContextAction positiveAction) {
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

        title.setText(titleText);
        if (messageText != null) {
            message.setText(messageText);
        } else {
            message.setVisibility(View.GONE);
        }

        neutralBtn.setText(neutralText);
        negativeBtn.setText(negativeText);
        positiveBtn.setText(positiveText);
        applyTypeface(title, message, neutralBtn, negativeBtn, positiveBtn);
        applyColors(neutralBtn, negativeBtn, positiveBtn);

        closeBtn.setOnClickListener(v -> {
            DataManager.setAskLater(context);
            dialog.dismiss();
        });

        neutralBtn.setOnClickListener(v -> {
            if (neutralListener != null) neutralListener.onClick();
            neutralAction.run(context);
            dialog.dismiss();
        });

        negativeBtn.setOnClickListener(v -> {
            if (negativeListener != null) negativeListener.onClick();
            negativeAction.run(context);
            dialog.dismiss();
        });

        positiveBtn.setOnClickListener(v -> {
            if (positiveListener != null) positiveListener.onClick();
            dialog.dismiss();
            positiveAction.run(context);
        });

        dialog.show();
        applyDialogBackground(context, dialog);
    }

    // endregion

    // region Helpers

    private void applyDialogBackground(Context context, AlertDialog dialog) {
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
            negativeBtn.setTypeface(boldTypeface, Typeface.BOLD);
            positiveBtn.setTypeface(boldTypeface, Typeface.BOLD);
        }

        Typeface typeface = ratingDialogOptions.regularTypeface;
        if (typeface != null) {
            message.setTypeface(typeface);
        }
    }

    private void applyColors(Button neutralBtn, Button negativeBtn, Button positiveBtn) {
        int outlinedColor = ratingDialogOptions.outlinedButtonColor;
        if (outlinedColor != 0) {
            neutralBtn.setBackground(createButtonDrawable(outlinedColor, 8));
            negativeBtn.setBackground(createButtonDrawable(outlinedColor, 8));
        }

        int primaryColor = ratingDialogOptions.primaryButtonColor;
        if (primaryColor != 0) {
            positiveBtn.setBackground(createButtonDrawable(primaryColor, 8));
        }

        int primaryTextColor = ratingDialogOptions.primaryButtonTextColor;
        if (primaryTextColor != 0) {
            positiveBtn.setTextColor(primaryTextColor);
        }
    }

    private static StateListDrawable createButtonDrawable(int color, float cornerRadiusDp) {
        float cornerRadiusPx = cornerRadiusDp * android.content.res.Resources.getSystem().getDisplayMetrics().density;

        GradientDrawable pressed = new GradientDrawable();
        pressed.setShape(GradientDrawable.RECTANGLE);
        pressed.setCornerRadius(cornerRadiusPx);
        pressed.setColor(darkenColor(color));

        GradientDrawable normal = new GradientDrawable();
        normal.setShape(GradientDrawable.RECTANGLE);
        normal.setCornerRadius(cornerRadiusPx);
        normal.setColor(color);

        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressed);
        drawable.addState(StateSet.WILD_CARD, normal);
        return drawable;
    }

    private static int darkenColor(int color) {
        float factor = 0.85f;
        int a = (color >> 24) & 0xff;
        int r = (int) (((color >> 16) & 0xff) * factor);
        int g = (int) (((color >> 8) & 0xff) * factor);
        int b = (int) ((color & 0xff) * factor);
        return (a << 24) | (r << 16) | (g << 8) | b;
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

    // endregion

    RatingDialog(RatingDialogOptions ratingDialogOptions) {
        this.ratingDialogOptions = ratingDialogOptions;
    }

}
