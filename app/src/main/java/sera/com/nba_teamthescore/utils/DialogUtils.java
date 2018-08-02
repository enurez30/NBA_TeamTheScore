package sera.com.nba_teamthescore.utils;

import sera.com.nba_teamthescore.NBA_TeamTheScoreApplication;
import sera.com.nba_teamthescore.dialog.ConfirmationAlertDialog;

public class DialogUtils {

    public static void showConfirmationDialog(String title, String message, String positiveButton, ConfirmationAlertDialog.Listener listener) {
        ConfirmationAlertDialog dialog = ConfirmationAlertDialog.newInstance(title, message, positiveButton);
        dialog.setListener(listener);
        dialog.show(NBA_TeamTheScoreApplication.getInstance().getMasterActivity().getFragmentManager(), "ConfirmationAlertDialog");
    }

}
