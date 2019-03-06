package in.codecorp.myapplication.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Window;

import in.codecorp.myapplication.R;


public class DialogUtility {
    private static ProgressDialog mProgressDialog;

    public static void showLoading(Activity activity) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(activity);
                mProgressDialog.setCancelable(false);
                mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                mProgressDialog.show();
                mProgressDialog.setContentView(R.layout.view_progress);
            }
        } catch (RuntimeException e) {
            Log.e("DialogUtitlity", e.toString());
        } catch (Exception e) {
            Log.e("DialogUtitlity", e.toString());
        }
    }

    public static void hideLoading() {
        try {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}