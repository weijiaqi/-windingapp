package app.winding.com.windingapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.KeyEvent;

/**
 * Created by ${LC} on 2017/9/15.
 * Don't believe me just watch!
 */

public class BackDialog extends Dialog {
    private BackDialog  dialog;
    public BackDialog(@NonNull Context context) {
        super(context);
    }

    public BackDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected BackDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            dialog.dismiss();
            return true;
        }
        return super.dispatchKeyShortcutEvent(event);
    }
}
