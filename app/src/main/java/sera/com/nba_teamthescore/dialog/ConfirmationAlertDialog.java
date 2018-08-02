package sera.com.nba_teamthescore.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import sera.com.nba_teamthescore.R;

public class ConfirmationAlertDialog  extends DialogFragment implements View.OnClickListener {

    private TextView mTitleTxt;
    private TextView mMessageTxt;
    private Button mNegativeBtn;
    private Button mPositiveBtn;

    private String title;
    private String message;
    private String ok;
    private String cancel;

    private Listener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog d = super.onCreateDialog(savedInstanceState);
        d.getWindow().setWindowAnimations(R.style.ConfirmAnimation);
        d.setCanceledOnTouchOutside(false);
        return d;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(STYLE_NO_TITLE, 0);

        title = getArguments().getString("title", null);
        message = getArguments().getString("message", null);
        ok = getArguments().getString("ok", null);
        cancel = getArguments().getString("cancel", null);
    }

    public static ConfirmationAlertDialog newInstance(String title, String message, String ok) {

        ConfirmationAlertDialog f = new ConfirmationAlertDialog();

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        args.putString("ok", ok);
        f.setArguments(args);

        return f;
    }

    public static ConfirmationAlertDialog newInstance(String title, String message, String ok, String cancel) {

        ConfirmationAlertDialog f = new ConfirmationAlertDialog();

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        args.putString("ok", ok);
        args.putString("cancel", cancel);
        f.setArguments(args);

        return f;
    }

    public void setListener(Listener listener) {

        this.mListener = listener;
    }

    public ConfirmationAlertDialog() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.confirmation_alert_dialog_view, container, false);

        mTitleTxt = v.findViewById(R.id.titleTxt);
        mMessageTxt = v.findViewById(R.id.messageTxt);
        mNegativeBtn = v.findViewById(R.id.negativeBtn);
        mPositiveBtn = v.findViewById(R.id.positiveBtn);

        if (TextUtils.isEmpty(title))
            mTitleTxt.setVisibility(View.GONE);
        else
            mTitleTxt.setText(title);

        if (TextUtils.isEmpty(message))
            mMessageTxt.setVisibility(View.GONE);
        else
            mMessageTxt.setText(message);

        if (TextUtils.isEmpty(ok))
            mPositiveBtn.setText(getString(R.string.ok));
        else
            mPositiveBtn.setText(ok);

        if (TextUtils.isEmpty(cancel))
            mNegativeBtn.setText(getString(R.string.cancel));
        else
            mNegativeBtn.setText(cancel);

        mNegativeBtn.setOnClickListener(this);
        mPositiveBtn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.negativeBtn: {

                if (mListener != null)
                    mListener.onNegativeActionSelected();

                dismiss();
            }
            break;

            case R.id.positiveBtn: {

                if (mListener != null)
                    mListener.onPositiveActionSelected();

                dismiss();
            }
            break;

            default: {
            }
            break;
        }
    }

    public interface Listener {

        void onPositiveActionSelected();

        void onNegativeActionSelected();
    }

}
