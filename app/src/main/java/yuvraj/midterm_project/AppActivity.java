package yuvraj.midterm_project;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class AppActivity extends AppCompatActivity {


    public static void hideKeyboard(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = null;

        if (inputMethodManager == null) {
            return;
        }

        if (context instanceof Activity) {
            view = ((Activity) context).getCurrentFocus();
        }

        IBinder windowToken = view != null ? view.getWindowToken() : null;

        if (windowToken != null) {
            inputMethodManager.hideSoftInputFromInputMethod(windowToken, 0);
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
        }
    }

    /**
     * Show soft keyboard
     *
     * @param context context If null will skip
     * @param view focused view.
     */
    public static void showKeyboard(Context context, @NonNull View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(view, 0);
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appactivity);
    }
}
