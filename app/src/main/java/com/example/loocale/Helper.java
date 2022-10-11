package com.example.loocale;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class Helper {

    public static void setColorSpan (TextView view, String text, String targetText, Integer color) {
        String target = targetText;
        String sourceText = text;
        int startSpan = sourceText.indexOf(target);
        int endSpan = startSpan + target.length();
        SpannableString resultText = new SpannableString(sourceText);
        resultText.setSpan(new ForegroundColorSpan(color),
                startSpan,
                endSpan,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(resultText);
    }
}
