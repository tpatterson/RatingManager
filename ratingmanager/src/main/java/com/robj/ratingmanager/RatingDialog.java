package com.robj.ratingmanager;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import android.support.v7.app.AlertDialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;

/**
 * Created by Rob J on 16/09/17.
 */

class RatingDialog {

    private static final String TAG = "RatingDialog";

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
        View view = LayoutInflater.from(context).inflate(R.layout.rm_dialog_rating, null);
        AlertDialog dialog = new AlertDialog.Builder(context, R.style.RatingManagerTheme_Dialog_Custom)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.setOnKeyListener((d, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                DataManager.setAskLater(context);
                d.dismiss();
                return true;
            }
            return false;
        });

        TextView title = view.findViewById(R.id.rm_dialog_title);
        TextView message = view.findViewById(R.id.rm_dialog_message);
        ImageButton closeBtn = view.findViewById(R.id.rm_dialog_close);
        Button neutralBtn = view.findViewById(R.id.rm_btn_neutral);
        Button negativeBtn = view.findViewById(R.id.rm_btn_negative);
        Button positiveBtn = view.findViewById(R.id.rm_btn_positive);

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
        applyColors(context, neutralBtn, negativeBtn, positiveBtn);

        if (ratingDialogOptions.dialogBackgroundColor != null) {
            double luminance = ColorUtils.calculateLuminance(ratingDialogOptions.dialogBackgroundColor);
            int tint = luminance < 0.5 ? Color.WHITE : Color.BLACK;
            closeBtn.setColorFilter(tint);
            title.setTextColor(tint);
            message.setTextColor(ColorUtils.setAlphaComponent(tint, 0xB3));
        }

        closeBtn.setOnClickListener(v -> {
            DataManager.setAskLater(context);
            dialog.dismiss();
        });

        neutralBtn.setOnClickListener(v -> {
            if (neutralListener != null) neutralListener.onClick();
            dialog.dismiss();
            neutralAction.run(context);
        });

        negativeBtn.setOnClickListener(v -> {
            if (negativeListener != null) negativeListener.onClick();
            dialog.dismiss();
            negativeAction.run(context);
        });

        positiveBtn.setOnClickListener(v -> {
            if (positiveListener != null) positiveListener.onClick();
            dialog.dismiss();
            positiveAction.run(context);
        });

        applyDialogBackground(context, dialog);
        dialog.show();
    }

    // endregion

    // region Helpers

    private void applyDialogBackground(Context context, AlertDialog dialog) {
        Window window = dialog.getWindow();
        if (window != null) {
            if (ratingDialogOptions.dialogBackgroundColor != null) {
                GradientDrawable bg = new GradientDrawable();
                bg.setShape(GradientDrawable.RECTANGLE);
                bg.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, context.getResources().getDisplayMetrics()));
                bg.setColor(ratingDialogOptions.dialogBackgroundColor);
                window.setBackgroundDrawable(bg);
            } else {
                window.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.rm_bg_dialog_rounded));
            }
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

    private void applyColors(Context context, Button neutralBtn, Button negativeBtn, Button positiveBtn) {
        Integer outlinedColor = ratingDialogOptions.outlinedButtonColor;
        if (outlinedColor != null) {
            neutralBtn.setBackground(createButtonDrawable(context, outlinedColor, 8));
            negativeBtn.setBackground(createButtonDrawable(context, outlinedColor, 8));
        }

        Integer primaryColor = ratingDialogOptions.primaryButtonColor;
        if (primaryColor != null) {
            positiveBtn.setBackground(createButtonDrawable(context, primaryColor, 8));
        }

        Integer primaryTextColor = ratingDialogOptions.primaryButtonTextColor;
        if (primaryTextColor != null) {
            positiveBtn.setTextColor(primaryTextColor);
        }

        Integer outlinedTextColor = ratingDialogOptions.outlinedButtonTextColor;
        if (outlinedTextColor != null) {
            neutralBtn.setTextColor(outlinedTextColor);
            negativeBtn.setTextColor(outlinedTextColor);
        }
    }

    private static StateListDrawable createButtonDrawable(Context context, int color, float cornerRadiusDp) {
        float cornerRadiusPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, cornerRadiusDp,
                context.getResources().getDisplayMetrics());

        GradientDrawable pressed = new GradientDrawable();
        pressed.setShape(GradientDrawable.RECTANGLE);
        pressed.setCornerRadius(cornerRadiusPx);
        pressed.setColor(ColorUtils.blendARGB(color, Color.BLACK, 0.15f));

        GradientDrawable normal = new GradientDrawable();
        normal.setShape(GradientDrawable.RECTANGLE);
        normal.setCornerRadius(cornerRadiusPx);
        normal.setColor(color);

        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressed);
        drawable.addState(StateSet.WILD_CARD, normal);
        return drawable;
    }

    private static void launchEmailIntent(Context context, String email, String subject, String body) {
        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT, body);
        i = Intent.createChooser(i, context.getString(R.string.dialog_open_with));
        try {
            context.startActivity(i);
        } catch (ActivityNotFoundException e) {
            Log.w(TAG, "No activity found to handle email intent", e);
        }
    }

    @SuppressLint("InlinedApi") // FLAG_ACTIVITY_NEW_DOCUMENT has same value as FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET on API <21
    private static void launchUrl(Context context, String url) {
        if (url == null) return;
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_NEW_TASK);
        i = Intent.createChooser(i, context.getString(R.string.dialog_open_with));
        try {
            context.startActivity(i);
        } catch (ActivityNotFoundException e) {
            Log.w(TAG, "No activity found to handle URL intent: " + url, e);
        }
    }

    // endregion

    RatingDialog(RatingDialogOptions ratingDialogOptions) {
        this.ratingDialogOptions = ratingDialogOptions;
    }

}
