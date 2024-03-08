package edu.up.cs301.GameFramework.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;

import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.Stratego.R;

/**
 * Helper-class for showing dialog boxes
 * @author Andrew M. Nuxoll
 * @author Steven R. Vegdahl
 * @author Eric Imperio
 * @version January 2020
 *
 */
public class MessageBox {
    //Tag for logging
    private static final String TAG = "MessageBox";
    private static final String FRAMEWORKCANCEL = "Cancel";

    /**
     * popUpMessage, a handy method for putting a message box on the screen.
     *
     * @param msg
     *          the message to post
     * @param activity
     * 			the current activity. (Nothing will be shown if the activity is null.)
     */
    public static void popUpMessage(String msg, Activity activity) {
        if (activity == null) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(msg);
        builder.setPositiveButton("OK", null);
        AlertDialog alert = builder.create();
        alert.show();
    }// popUpMessage

    /**
     * popUpChoice, a handy method for putting a message box on the screen in which
     * the user is given two options
     *
     * <p>
     * CAVEAT: this dialog does not wait for user input, so the indication of how
     * to respond to the selection is done via attaching listeners (passed as parameters
     * below) to the method.
     *
     * @param msg
     * 			the message to post
     * @param posButtonText
     * 			the "positive" button text
     * @param negButtonText
     * 			the "negative" button text
     * @param posListener
     * 			the listener, if any, to activate when the positive
     * 			button is pressed
     * @param negListener
     * 			the listener, if any, to activate when the negative
     * 			button is pressed
     * @param activity
     * 			the current activity
     */
    public static void popUpChoice(String msg, String posButtonText,
                                   String negButtonText, OnClickListener posListener,
                                   OnClickListener negListener, Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(msg);
        builder.setPositiveButton(posButtonText, posListener);
        builder.setNegativeButton(negButtonText, negListener);
        AlertDialog alert = builder.create();
        alert.show();
    }// popUpChoice

    /**
     * popUpSaveGame, a method used to name and save the current game
     *
     * @param msg
     *          The message to post
     * @param activity
     *          The current activity. (Nothing will be shown if the activity is null.)
     */
    public static void popUpSaveGame(String msg, final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(msg);
        final EditText input = new EditText(activity);
        builder.setView(input);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                if(value.equals(FRAMEWORKCANCEL)){
                    popUpMessage("Cannot name a file: " + FRAMEWORKCANCEL, activity);
                    return;
                }
                ((GameMainActivity) activity).saveGame(value);
                return;
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //Auto-generated method stub
                return;
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    /**
     * popUpLoadGame, a method to select a game to play
     *
     * @param msg
     *          The message to post
     * @param activity
     *          The current activity. (Nothing will be shown if the activity is null.)
     */
    public static void popUpLoadGame(String msg, final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(msg);
        final CharSequence[] strings = MessageBox.getGameFileNames(activity);
        builder.setItems(strings, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Logger.log(TAG, whichButton+"");
                if(strings[whichButton].equals(FRAMEWORKCANCEL)) {
                    return;
                }
                ((GameMainActivity) activity).startLoadedGame(((GameMainActivity) activity).loadGame(strings[whichButton].toString()));
                dialog.dismiss();
                return;
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    /**
     * popUpDeleteGame, a method to select a game to play
     *
     * @param msg
     *          The message to post
     * @param activity
     *          The current activity. (Nothing will be shown if the activity is null.)
     */
    public static void popUpDeleteGame(String msg, final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(msg);
        final CharSequence[] strings = MessageBox.getGameFileNames(activity);
        builder.setItems(strings, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Logger.log(TAG, whichButton+"");
                if(strings[whichButton].equals(FRAMEWORKCANCEL)) {
                    return;
                }
                Logger.log(TAG, Saving.deleteFromFile(activity.getString(R.string.app_name) + "_" + strings[whichButton].toString(), activity) + "");
                Logger.log(TAG, Saving.deleteFromFile(activity.getString(R.string.app_name) + "_" + strings[whichButton].toString() + ".c", activity) + "");
                dialog.dismiss();
                return;
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    /**
     *
     * @param activity
     *          the current activity. (Nothing will be shown if the activity is null.)
     * @return CharSequence[]
     *          The files in directory matching the save prepend for this game
     */
    private static CharSequence[] getGameFileNames(Activity activity){
        File f = new File(activity.getApplicationContext().getFilesDir().getPath());
        File[] files=f.listFiles();
        ArrayList<String> strings = new ArrayList<>();
        String appName = activity.getString(R.string.app_name);
        for(File file: files){
            if(file.getName().startsWith(appName) && !file.getName().endsWith(".c")) {
                //Logger.log(TAG, file.getName());
                strings.add(file.getName().replace(appName + "_", ""));
            }
        }
        strings.add(FRAMEWORKCANCEL);
        return strings.toArray(new CharSequence[strings.size()] );
    }
}
