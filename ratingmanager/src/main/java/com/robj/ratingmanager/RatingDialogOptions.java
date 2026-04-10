package com.robj.ratingmanager;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

public class RatingDialogOptions {

    public final int dialogThemeResId;
    @Nullable public final Typeface regularTypeface;
    @Nullable public final Typeface boldTypeface;
    @Nullable public final Integer primaryButtonColor;
    @Nullable public final Integer outlinedButtonColor;
    @Nullable public final Integer primaryButtonTextColor;
    @Nullable public final Integer outlinedButtonTextColor;
    @Nullable public final Integer dialogBackgroundColor;
    public final boolean useCustomLayout;

    public final String initialPopupMessage;
    public final String initialPopupPositiveBtnText;
    public final String initialPopupNegativeBtnText;
    public final String initialPopupLaterBtnText;

    public final String ratingPopupTitle;
    public final String ratingPopupMessage;
    public final String ratingPopupPositiveBtnText;
    public final String ratingPopupLaterBtnText;
    public final String ratingPopupNeverBtnText;
    public final String ratingUrl;

    public final String feedbackPopupTitle;
    public final String feedbackPopupMessage;
    public final String feedbackPopupNegativeBtnText;
    public final String feedbackPopupPositiveBtnText;
    public final String feedbackPopupLaterBtnText;
    public final String feedbackEmailSubject;
    public final String feedbackEmailBody;
    
    public final OnRatingClickListener onInitialPositiveClickListener;
    public final OnRatingClickListener onInitialNegativeClickListener;
    public final OnRatingClickListener onInitialLaterClickListener;
    public final OnRatingClickListener onRatingPositiveClickListener;
    public final OnRatingClickListener onRatingNegativeClickListener;
    public final OnRatingClickListener onRatingLaterClickListener;
    public final OnRatingClickListener onFeedbackPositiveClickListener;
    public final OnRatingClickListener onFeedbackNegativeClickListener;
    public final OnRatingClickListener onFeedbackLaterClickListener;

    RatingDialogOptions(int dialogThemeResId, Typeface regularTypeface, Typeface boldTypeface, Integer primaryButtonColor, Integer outlinedButtonColor, Integer primaryButtonTextColor, Integer outlinedButtonTextColor, Integer dialogBackgroundColor, boolean useCustomLayout, String initialPopupMessage, String initialPopupPositiveBtnText, String initialPopupNegativeBtnText, String initialPopupLaterBtnText, String ratingPopupTitle, String ratingPopupMessage, String ratingPopupPositiveBtnText, String ratingPopupLaterBtnText, String ratingPopupNeverBtnText, String ratingUrl, String feedbackPopupTitle, String feedbackPopupMessage, String feedbackPopupNegativeBtnText, String feedbackPopupPositiveBtnText, String feedbackPopupLaterBtnText, String feedbackEmailSubject, String feedbackEmailBody, OnRatingClickListener onInitialPositiveClickListener, OnRatingClickListener onInitialNegativeClickListener, OnRatingClickListener onInitialLaterClickListener, OnRatingClickListener onRatingPositiveClickListener, OnRatingClickListener onRatingNegativeClickListener, OnRatingClickListener onRatingLaterClickListener, OnRatingClickListener onFeedbackPositiveClickListener, OnRatingClickListener onFeedbackNegativeClickListener, OnRatingClickListener onFeedbackLaterClickListener) {
        this.dialogThemeResId = dialogThemeResId;
        this.regularTypeface = regularTypeface;
        this.boldTypeface = boldTypeface;
        this.primaryButtonColor = primaryButtonColor;
        this.outlinedButtonColor = outlinedButtonColor;
        this.primaryButtonTextColor = primaryButtonTextColor;
        this.outlinedButtonTextColor = outlinedButtonTextColor;
        this.dialogBackgroundColor = dialogBackgroundColor;
        this.useCustomLayout = useCustomLayout;
        this.initialPopupMessage = initialPopupMessage;
        this.initialPopupPositiveBtnText = initialPopupPositiveBtnText;
        this.initialPopupNegativeBtnText = initialPopupNegativeBtnText;
        this.initialPopupLaterBtnText = initialPopupLaterBtnText;
        this.ratingPopupTitle = ratingPopupTitle;
        this.ratingPopupMessage = ratingPopupMessage;
        this.ratingPopupPositiveBtnText = ratingPopupPositiveBtnText;
        this.ratingPopupLaterBtnText = ratingPopupLaterBtnText;
        this.ratingPopupNeverBtnText = ratingPopupNeverBtnText;
        this.ratingUrl = ratingUrl;
        this.feedbackPopupTitle = feedbackPopupTitle;
        this.feedbackPopupMessage = feedbackPopupMessage;
        this.feedbackPopupNegativeBtnText = feedbackPopupNegativeBtnText;
        this.feedbackPopupPositiveBtnText = feedbackPopupPositiveBtnText;
        this.feedbackPopupLaterBtnText = feedbackPopupLaterBtnText;
        this.feedbackEmailSubject = feedbackEmailSubject;
        this.feedbackEmailBody = feedbackEmailBody;
        this.onInitialPositiveClickListener = onInitialPositiveClickListener;
        this.onInitialNegativeClickListener = onInitialNegativeClickListener;
        this.onInitialLaterClickListener = onInitialLaterClickListener;
        this.onRatingPositiveClickListener = onRatingPositiveClickListener;
        this.onRatingNegativeClickListener = onRatingNegativeClickListener;
        this.onRatingLaterClickListener = onRatingLaterClickListener;
        this.onFeedbackPositiveClickListener = onFeedbackPositiveClickListener;
        this.onFeedbackNegativeClickListener = onFeedbackNegativeClickListener;
        this.onFeedbackLaterClickListener = onFeedbackLaterClickListener;
    }

    /** @deprecated Use the Builder API instead. */
    @Deprecated
    public RatingDialogOptions(int dialogThemeResId, String initialPopupMessage, String initialPopupPositiveBtnText, String initialPopupNegativeBtnText, String initialPopupLaterBtnText, String ratingPopupTitle, String ratingPopupMessage, String ratingPopupPositiveBtnText, String ratingPopupLaterBtnText, String ratingPopupNeverBtnText, String ratingUrl, String feedbackPopupTitle, String feedbackPopupMessage, String feedbackPopupNegativeBtnText, String feedbackPopupPositiveBtnText, String feedbackPopupLaterBtnText, String feedbackEmailSubject, String feedbackEmailBody, OnRatingClickListener onInitialPositiveClickListener, OnRatingClickListener onInitialNegativeClickListener, OnRatingClickListener onInitialLaterClickListener, OnRatingClickListener onRatingPositiveClickListener, OnRatingClickListener onRatingNegativeClickListener, OnRatingClickListener onRatingLaterClickListener, OnRatingClickListener onFeedbackPositiveClickListener, OnRatingClickListener onFeedbackNegativeClickListener, OnRatingClickListener onFeedbackLaterClickListener) {
        this(dialogThemeResId, null, null, null, null, null, null, null, false, initialPopupMessage, initialPopupPositiveBtnText, initialPopupNegativeBtnText, initialPopupLaterBtnText, ratingPopupTitle, ratingPopupMessage, ratingPopupPositiveBtnText, ratingPopupLaterBtnText, ratingPopupNeverBtnText, ratingUrl, feedbackPopupTitle, feedbackPopupMessage, feedbackPopupNegativeBtnText, feedbackPopupPositiveBtnText, feedbackPopupLaterBtnText, feedbackEmailSubject, feedbackEmailBody, onInitialPositiveClickListener, onInitialNegativeClickListener, onInitialLaterClickListener, onRatingPositiveClickListener, onRatingNegativeClickListener, onRatingLaterClickListener, onFeedbackPositiveClickListener, onFeedbackNegativeClickListener, onFeedbackLaterClickListener);
    }

    public static class Builder {

        private int dialogThemeResId = R.style.RatingManagerTheme_Dialog;
        private boolean dialogThemeResIdExplicitlySet;
        private Typeface regularTypeface;
        private Typeface boldTypeface;
        private Integer primaryButtonColor;
        private Integer outlinedButtonColor;
        private Integer primaryButtonTextColor;
        private Integer outlinedButtonTextColor;
        private Integer dialogBackgroundColor;
        private Boolean useCustomLayout;

        private String initialPopupMessage;
        private String initialPopupPositiveBtnText;
        private String initialPopupNegativeBtnText;
        private String initialPopupLaterBtnText;

        private String ratingPopupTitle;
        private String ratingPopupMessage;
        private String ratingPopupPositiveBtnText;
        private String ratingPopupLaterBtnText;
        private String ratingPopupNeverBtnText;
        private String ratingUrl;

        private String feedbackPopupTitle;
        private String feedbackPopupMessage;
        private String feedbackPopupNegativeBtnText;
        private String feedbackPopupPositiveBtnText;
        private String feedbackPopupLaterBtnText;
        private String feedbackEmailSubject;
        private String feedbackEmailBody;

        private OnRatingClickListener onInitialPositiveClickListener;
        private OnRatingClickListener onInitialNegativeClickListener;
        private OnRatingClickListener onInitialLaterClickListener;
        private OnRatingClickListener onRatingPositiveClickListener;
        private OnRatingClickListener onRatingNegativeClickListener;
        private OnRatingClickListener onRatingLaterClickListener;
        private OnRatingClickListener onFeedbackPositiveClickListener;
        private OnRatingClickListener onFeedbackNegativeClickListener;
        private OnRatingClickListener onFeedbackLaterClickListener;

        private String getAppLabel(Context context) {
            try {
                ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
                if (appInfo != null) {
                    return appInfo.loadLabel(context.getPackageManager()).toString();
                }
            } catch (Exception var3) {
                var3.printStackTrace();
            }
            return "Unknown";
        }

        public Builder(Context context) {
            String appName = getAppLabel(context);
            initialPopupMessage = context.getString(R.string.initial_rating_title, appName);
            initialPopupPositiveBtnText = context.getString(R.string.yes);
            initialPopupNegativeBtnText = context.getString(R.string.no);
            initialPopupLaterBtnText = context.getString(R.string.ask_me_later);

            ratingPopupTitle = context.getString(R.string.rating_title);
            ratingPopupMessage = context.getString(R.string.rating_text);
            ratingPopupPositiveBtnText = context.getString(R.string.rate_us);
            ratingPopupLaterBtnText = context.getString(R.string.maybe_later);
            ratingPopupNeverBtnText = context.getString(R.string.never);
            ratingUrl = "https://play.google.com/store/apps/details?id=" + context.getPackageName();

            feedbackPopupTitle = context.getString(R.string.feedback_title);
            feedbackPopupMessage = context.getString(R.string.feedback_text);
            feedbackPopupPositiveBtnText = context.getString(R.string.leave_feedback);
            feedbackPopupLaterBtnText = context.getString(R.string.maybe_later);
            feedbackPopupNegativeBtnText = context.getString(R.string.never);
            feedbackEmailSubject = context.getString(R.string.email_feedback_subject, getAppLabel(context));
            feedbackEmailBody = null;
        }

        /**
         * Sets the theme resource used for the legacy {@link AlertDialog}.
         * <p><b>Note:</b> this theme is ignored when the custom layout is active
         * (i.e.&nbsp;when {@link #setUseCustomLayout(boolean)} is {@code true} or
         * when it is auto-enabled by setting visual properties such as typefaces,
         * button colours, or a dialog background colour). The custom layout path
         * always uses its own internal theme.
         */
        public Builder setDialogThemeResId(@StyleRes int dialogThemeResId) {
            this.dialogThemeResId = dialogThemeResId;
            this.dialogThemeResIdExplicitlySet = true;
            return this;
        }

        /**
         * Sets the regular typeface used for the dialog message text.
         * <p><b>Note:</b> this property only takes effect when the custom layout
         * is active ({@link #setUseCustomLayout(boolean)}), which requires
         * API 21+. On older devices the legacy AlertDialog is used and this
         * value is ignored.
         */
        public Builder setRegularTypeface(Typeface regularTypeface) {
            this.regularTypeface = regularTypeface;
            return this;
        }

        /**
         * Sets the bold typeface used for the dialog title and buttons.
         * <p><b>Note:</b> this property only takes effect when the custom layout
         * is active ({@link #setUseCustomLayout(boolean)}), which requires
         * API 21+. On older devices the legacy AlertDialog is used and this
         * value is ignored.
         */
        public Builder setBoldTypeface(Typeface boldTypeface) {
            this.boldTypeface = boldTypeface;
            return this;
        }

        /**
         * Sets the background color of the primary (positive) button.
         * <p><b>Note:</b> this property only takes effect when the custom layout
         * is active ({@link #setUseCustomLayout(boolean)}), which requires
         * API 21+. On older devices the legacy AlertDialog is used and this
         * value is ignored.
         */
        public Builder setPrimaryButtonColor(@ColorInt int primaryButtonColor) {
            this.primaryButtonColor = primaryButtonColor;
            return this;
        }

        /**
         * Sets the background color of the outlined (neutral and negative) buttons.
         * <p><b>Note:</b> this property only takes effect when the custom layout
         * is active ({@link #setUseCustomLayout(boolean)}), which requires
         * API 21+. On older devices the legacy AlertDialog is used and this
         * value is ignored.
         */
        public Builder setOutlinedButtonColor(@ColorInt int outlinedButtonColor) {
            this.outlinedButtonColor = outlinedButtonColor;
            return this;
        }

        /**
         * Sets the text color of the primary (positive) button.
         * <p><b>Note:</b> this property only takes effect when the custom layout
         * is active ({@link #setUseCustomLayout(boolean)}), which requires
         * API 21+. On older devices the legacy AlertDialog is used and this
         * value is ignored.
         */
        public Builder setPrimaryButtonTextColor(@ColorInt int primaryButtonTextColor) {
            this.primaryButtonTextColor = primaryButtonTextColor;
            return this;
        }

        /**
         * Sets the text color of the outlined (neutral and negative) buttons.
         * <p><b>Note:</b> this property only takes effect when the custom layout
         * is active ({@link #setUseCustomLayout(boolean)}), which requires
         * API 21+. On older devices the legacy AlertDialog is used and this
         * value is ignored.
         */
        public Builder setOutlinedButtonTextColor(@ColorInt int outlinedButtonTextColor) {
            this.outlinedButtonTextColor = outlinedButtonTextColor;
            return this;
        }

        /**
         * Sets the background color of the dialog window.
         * <p><b>Note:</b> this property only takes effect when the custom layout
         * is active ({@link #setUseCustomLayout(boolean)}), which requires
         * API 21+. On older devices the legacy AlertDialog is used and this
         * value is ignored.
         */
        public Builder setDialogBackgroundColor(@ColorInt int dialogBackgroundColor) {
            this.dialogBackgroundColor = dialogBackgroundColor;
            return this;
        }

        public Builder setInitialPopupMessage(String initialPopupMessage) {
            this.initialPopupMessage = initialPopupMessage;
            return this;
        }

        public Builder setInitialPopupNegativeBtnText(String initialPopupNegativeBtnText) {
            this.initialPopupNegativeBtnText = initialPopupNegativeBtnText;
            return this;
        }

        public Builder setInitialPopupPositiveBtnText(String initialPopupPositiveBtnText) {
            this.initialPopupPositiveBtnText = initialPopupPositiveBtnText;
            return this;
        }

        public Builder setInitialPopupLaterBtnText(String initialPopupLaterBtnText) {
            this.initialPopupLaterBtnText = initialPopupLaterBtnText;
            return this;
        }

        public Builder setRatingPopupTitle(String ratingPopupTitle) {
            this.ratingPopupTitle = ratingPopupTitle;
            return this;
        }

        public Builder setRatingPopupMessage(String ratingPopupMessage) {
            this.ratingPopupMessage = ratingPopupMessage;
            return this;
        }

        public Builder setRatingPopupLaterBtnText(String ratingPopupLaterBtnText) {
            this.ratingPopupLaterBtnText = ratingPopupLaterBtnText;
            return this;
        }

        public Builder setRatingPopupPositiveBtnText(String ratingPopupPositiveBtnText) {
            this.ratingPopupPositiveBtnText = ratingPopupPositiveBtnText;
            return this;
        }

        public Builder setRatingPopupNeverBtnText(String ratingPopupNeverBtnText) {
            this.ratingPopupNeverBtnText = ratingPopupNeverBtnText;
            return this;
        }

        public Builder setRatingUrl(String ratingUrl) {
            this.ratingUrl = ratingUrl;
            return this;
        }

        public Builder setFeedbackPopupTitle(String feedbackPopupTitle) {
            this.feedbackPopupTitle = feedbackPopupTitle;
            return this;
        }

        public Builder setFeedbackPopupMessage(String feedbackPopupMessage) {
            this.feedbackPopupMessage = feedbackPopupMessage;
            return this;
        }

        public Builder setFeedbackPopupNegativeBtnText(String feedbackPopupNegativeBtnText) {
            this.feedbackPopupNegativeBtnText = feedbackPopupNegativeBtnText;
            return this;
        }

        public Builder setFeedbackPopupPositiveBtnText(String feedbackPopupPositiveBtnText) {
            this.feedbackPopupPositiveBtnText = feedbackPopupPositiveBtnText;
            return this;
        }

        public Builder setFeedbackPopupLaterBtnText(String feedbackPopupLaterBtnText) {
            this.feedbackPopupLaterBtnText = feedbackPopupLaterBtnText;
            return this;
        }

        public Builder setFeedbackEmailSubject(String feedbackEmailSubject) {
            this.feedbackEmailSubject = feedbackEmailSubject;
            return this;
        }

        public Builder setFeedbackEmailBody(String feedbackEmailBody) {
            this.feedbackEmailBody = feedbackEmailBody;
            return this;
        }

        public Builder setOnInitialPositiveClickListener(OnRatingClickListener onInitialPositiveClickListener) {
            this.onInitialPositiveClickListener = onInitialPositiveClickListener;
            return this;
        }

        public Builder setOnInitialNegativeClickListener(OnRatingClickListener onInitialNegativeClickListener) {
            this.onInitialNegativeClickListener = onInitialNegativeClickListener;
            return this;
        }

        public Builder setOnInitialLaterClickListener(OnRatingClickListener onInitialLaterClickListener) {
            this.onInitialLaterClickListener = onInitialLaterClickListener;
            return this;
        }

        public Builder setOnRatingPositiveClickListener(OnRatingClickListener onPopupPositiveClickListener) {
            this.onRatingPositiveClickListener = onPopupPositiveClickListener;
            return this;
        }

        public Builder setOnRatingNegativeClickListener(OnRatingClickListener onPopupNegativeClickListener) {
            this.onRatingNegativeClickListener = onPopupNegativeClickListener;
            return this;
        }

        public Builder setOnRatingLaterClickListener(OnRatingClickListener onPopupLaterClickListener) {
            this.onRatingLaterClickListener = onPopupLaterClickListener;
            return this;
        }

        public Builder setOnFeedbackPositiveClickListener(OnRatingClickListener onFeedbackPositiveClickListener) {
            this.onFeedbackPositiveClickListener = onFeedbackPositiveClickListener;
            return this;
        }

        public Builder setOnFeedbackNegativeClickListener(OnRatingClickListener onFeedbackNegativeClickListener) {
            this.onFeedbackNegativeClickListener = onFeedbackNegativeClickListener;
            return this;
        }

        public Builder setOnFeedbackLaterClickListener(OnRatingClickListener onFeedbackLaterClickListener) {
            this.onFeedbackLaterClickListener = onFeedbackLaterClickListener;
            return this;
        }

        public Builder setUseCustomLayout(boolean useCustomLayout) {
            this.useCustomLayout = useCustomLayout;
            return this;
        }

        public RatingDialogOptions build() {
            boolean useCustomLayout;
            if (this.useCustomLayout != null) {
                useCustomLayout = this.useCustomLayout;
            } else {
                boolean hasCustomProps = regularTypeface != null || boldTypeface != null
                        || primaryButtonColor != null || outlinedButtonColor != null
                        || primaryButtonTextColor != null || outlinedButtonTextColor != null
                        || dialogBackgroundColor != null;
                useCustomLayout = hasCustomProps;
                if (hasCustomProps) {
                    Log.w("RatingDialogOptions",
                            "useCustomLayout was auto-enabled because a visual property "
                            + "(typeface, button color, or background color) was set. "
                            + "This implicit behavior is deprecated and will be removed "
                            + "in a future release. Please call "
                            + "setUseCustomLayout(true) explicitly.");
                }
            }
            // Custom layout requires API 21+ for proper elevation/animation support.
            // On older devices, silently fall back to the legacy dialog.
            if (useCustomLayout && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                Log.w("RatingDialogOptions",
                        "useCustomLayout requires API 21+; falling back to legacy dialog on API "
                        + Build.VERSION.SDK_INT);
                useCustomLayout = false;

                boolean hasCustomProps = regularTypeface != null || boldTypeface != null
                        || primaryButtonColor != null || outlinedButtonColor != null
                        || primaryButtonTextColor != null || outlinedButtonTextColor != null
                        || dialogBackgroundColor != null;
                if (hasCustomProps) {
                    Log.w("RatingDialogOptions",
                            "Visual properties (typeface, button color, background color) "
                            + "have no effect on API " + Build.VERSION.SDK_INT
                            + " because the custom layout requires API 21+. "
                            + "The legacy AlertDialog will be shown without these customizations.");
                }
            }

            if (useCustomLayout && dialogThemeResIdExplicitlySet) {
                Log.w("RatingDialogOptions",
                        "setDialogThemeResId() has no effect when the custom layout "
                        + "is active. The custom layout uses its own internal theme "
                        + "and the supplied dialogThemeResId will be ignored.");
            }

            return new RatingDialogOptions(dialogThemeResId, regularTypeface, boldTypeface, primaryButtonColor, outlinedButtonColor, primaryButtonTextColor, outlinedButtonTextColor, dialogBackgroundColor, useCustomLayout, initialPopupMessage, initialPopupPositiveBtnText, initialPopupNegativeBtnText, initialPopupLaterBtnText, ratingPopupTitle, ratingPopupMessage, ratingPopupPositiveBtnText, ratingPopupLaterBtnText, ratingPopupNeverBtnText, ratingUrl, feedbackPopupTitle, feedbackPopupMessage, feedbackPopupNegativeBtnText, feedbackPopupPositiveBtnText, feedbackPopupLaterBtnText, feedbackEmailSubject, feedbackEmailBody, onInitialPositiveClickListener, onInitialNegativeClickListener, onInitialLaterClickListener, onRatingPositiveClickListener, onRatingNegativeClickListener, onRatingLaterClickListener, onFeedbackPositiveClickListener, onFeedbackNegativeClickListener, onFeedbackLaterClickListener);
        }

    }
}
