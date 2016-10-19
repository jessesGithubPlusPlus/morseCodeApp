/*
package com.example.mint.morsecodetranslator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
*/
package com.example.mint.morsecodetranslator;

import android.content.Context;
import java.math.BigDecimal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewConfiguration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import android.view.View;
import android.widget.TextView;

import android.media.MediaPlayer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //makes media player play hello world in morse code sound
        final MediaPlayer morseCodeMP = MediaPlayer.create(this, R.raw.morse_code_hello_world);

        Button playHelloWorld = (Button) this.findViewById(R.id.button15);

        playHelloWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                morseCodeMP.start();
            }
        });


    }



    // The ArrayList message is instantiated. The initial dots, dashes, and spaces
    // will be stored here.
    private ArrayList message = new ArrayList();

    // Initialization of the initial time.
    private BigDecimal initialTime = new BigDecimal(System.currentTimeMillis());

    // Defines word as 3000 milliseconds of type BigDecimal to avoid calculation errors.
    // 3000 milliseconds is the pause duration that indicates the end of a word.
    private BigDecimal word = new BigDecimal("3000");

    // Defines letter as 1000 milliseconds of type BigDecimal to avoid calculation errors.
    // 1000 milliseconds is the pause duration that indicates the end of a letter.
    private BigDecimal letter = new BigDecimal("1000");

    // Initializes the variable that will be the morese code string message.
    private String codedMessage = null;

    // Initializes the variable that will record the translated message.
    private String finalMessage = null;

    // Adds dots to the ArrayList 'message' when the DOT button is pushed that will be translated later.
    public void setDOT(View view) {
        // Records the current time when the DOT button was pushed.
        BigDecimal currentTime = new BigDecimal(System.currentTimeMillis());

        // How much time has passed from the initialized initial time, or
        // the initial time time defined after a button had been pushed,
        // to the current time.
        BigDecimal difference = currentTime.subtract(initialTime);
/*
        // If the difference is greater than or equal to a pause that determines the end
        // of a word, two spaces are added to the ArrayList 'message'.
        if (difference.compareTo(word) >= 1){
            message.add("\u00A0");
            message.add("\u00A0");
        }
        // If the difference is greater than or equal to a pause that determines the end
        // of a letter, one space is added to the ArrayList 'message'.
        else if (difference.compareTo(letter) >= 1){
            message.add("\u00A0");
        }
*/
        // A dot is added to the ArrayList 'message'.
        message.add(". ");

        // Initial time is updated so that the current time from the subsequent button
        // push can be subtracted from this time to find out how much of a delay existed
        // between button pushes.
        initialTime = new BigDecimal(System.currentTimeMillis());


        array2String();

        // Stores the reference to the text field.
        TextView content1 = (TextView)findViewById(R.id.textView6);

        // Sets the translated message 'codedMessage' to the text field.
        content1.setText(codedMessage);


    }

    // Adds dashes to the ArrayList 'message' when the DASH button is pushed that will be translated later.
    public void setDASH(View view) {
        // How much time has passed from the initialized initial time, or
        // the initial time time defined after a button had been pushed,
        // to the current time.
        BigDecimal currentTime = new BigDecimal(System.currentTimeMillis());

        // How much time has passed from the initialized initial time, or
        // the initial time time defined after a button had been pushed,
        // to the current time.
        BigDecimal difference = currentTime.subtract(initialTime);

/*        // If the difference is greater than or equal to a pause that determines the end
        // of a word, four spaces are added to the ArrayList 'message'.
        if (difference.compareTo(word) >= 1){
            message.add("\u00A0");
            message.add("\u00A0");
            message.add("\u00A0");
        }
        // If the difference is greater than or equal to a pause that determines the end
        // of a letter, one space is added to the ArrayList 'message'.
        else if (difference.compareTo(letter) >= 1){
            message.add("\u00A0");
        }
*/
        // A dash is added to the ArrayList 'message'.
        message.add("_ ");

        // Initial time is updated so that the current time from the subsequent button
        // push can be subtracted from this time to find out how much of a delay existed
        // between button pushes.
        initialTime = new BigDecimal(System.currentTimeMillis());

        array2String();

        // Stores the reference to the text field.
        TextView content1 = (TextView)findViewById(R.id.textView6);

        // Sets the translated message 'codedMessage' to the text field.
        content1.setText(codedMessage);


    }

    public void clear() {

        //clears out the text fields

        message = new ArrayList();
        codedMessage = null;
        finalMessage = null;

    }

    public void testMessage() {

        //clears out the text fields

        codedMessage = ". . . .    .    . _ . .    . _ . .    _ _ _    . _ _    _ _ _    . _ .    . _ . .    _ . . ";
        finalMessage = "HELLOWORLD";

    }

    public void array2String(){
        // Instantiates an array of strings.
        String[] stringArray1 = new String[message.size()];

        // Inserts the elements with the ArrayList 'message' in the string array
        // 'codedMessage' so that 'codedMessage' can then be converted into a string.
        for (int i = 0; i < message.size(); i++) {
            stringArray1[i] = message.get(i).toString();
        }

        // Converts the array of strings into a string. This is so that the methods
        // for the String class can be used.
        codedMessage = Arrays.toString(stringArray1);

    }

    public void translate() {

        // Instantiates an array of strings.
        String[] codedMessage = new String[message.size()];

        // Inserts the elements with the ArrayList 'message' in the string array
        // 'codedMessage' so that 'codedMessage' can then be converted into a string.
        for (int i = 0; i < message.size(); i++) {
            codedMessage[i] = message.get(i).toString();
        }

        // Converts the array of strings into a string. This is so that the methods
        // for the String class can be used.
        String nonArrayMessage = Arrays.toString(codedMessage);

        // Initializes the variable.
        String unit = "\u0A00";

        // Iterates through the length of the string.
        for (int i = 0; i < nonArrayMessage.length(); i++) {
            // Checks if the element in the string is a space.
            if (nonArrayMessage.indexOf('\u0A00') == i) {
                // Checks if the next element afterwards is NOT a space as well.
                if (nonArrayMessage.indexOf('\u0A00', i + 1) != i + 1) {
                    // Takes the substring from the first space to the next space after
                    // a series of non-space elements and stores it to the variable.
                    unit = nonArrayMessage.substring(i, nonArrayMessage.indexOf('\u0A00', i + 1) + 1);
                } else {
                    // Otherwise, a single space is stored in the variable.
                    unit = "\u0A00";
                }
            }

            // Performs actions depending on what is stored in the variable.
            // Translates the substring into a letter or a space and stores it into
            // 'finalMessage' which will be pushed to the text field of the app when
            // the submit button is pushed.
            switch (unit) {
                case "\u0A00._\u0A00":
                    finalMessage += 'A';
                    i += 3;
                    break;
                case "\u0A00_...\u0A00":
                    finalMessage += 'B';
                    i += 5;
                    break;
                case "\u0A00_._.\u0A00":
                    finalMessage += 'C';
                    i += 5;
                    break;
                case "\u0A00_..\u0A00":
                    finalMessage += 'D';
                    i += 4;
                    break;
                case "\u0A00.\u0A00":
                    finalMessage += 'E';
                    i += 2;
                    break;
                case "\u0A00.._.\u0A00":
                    finalMessage += 'F';
                    i += 5;
                    break;
                case "\u0A00__.\u0A00":
                    finalMessage += 'G';
                    i += 4;
                    break;
                case "\u0A00....\u0A00":
                    finalMessage += 'H';
                    i += 5;
                    break;
                case "\u0A00..\u0A00":
                    finalMessage += 'I';
                    i += 3;
                    break;
                case "\u0A00.___\u0A00":
                    finalMessage += 'J';
                    i += 5;
                    break;
                case "\u0A00_._\u0A00":
                    finalMessage += 'K';
                    i += 4;
                    break;
                case "\u0A00._..\u0A00":
                        finalMessage += 'L';
                    i += 5;
                    break;
                case "\u0A00__\u0A00":
                    finalMessage += 'M';
                    i += 4;
                    break;
                case "\u0A00_.\u0A00":
                    finalMessage += 'N';
                    i += 3;
                    break;
                case "\u0A00___\u0A00":
                    finalMessage += 'O';
                    i += 4;
                    break;
                case "\u0A00.__.\u0A00":
                    finalMessage += 'P';
                    i += 6;
                    break;
                case "\u0A00__._\u0A00":
                    finalMessage += 'Q';
                    i += 6;
                    break;
                case "\u0A00._.\u0A00":
                    finalMessage += 'R';
                    i += 5;
                    break;
                case "\u0A00...\u0A00":
                    finalMessage += 'S';
                    i += 4;
                    break;
                case "\u0A00_\u0A00":
                    finalMessage += 'T';
                    i += 3;
                    break;
                case "\u0A00.._\u0A00":
                    finalMessage += 'U';
                    i += 5;
                    break;
                case "\u0A00..._\u0A00":
                    finalMessage += 'V';
                    i += 6;
                    break;
                case "\u0A00.__\u0A00":
                    finalMessage += 'W';
                    i += 5;
                    break;
                case "\u0A00_.._\u0A00":
                    finalMessage += 'X';
                    i += 6;
                    break;
                case "\u0A00_.__\u0A00":
                    finalMessage += 'Y';
                    i += 6;
                    break;
                case "\u0A00__..\u0A00":
                    finalMessage += 'Z';
                    i += 6;
                    break;
                case "\u0A00._._._\u0A00":
                    finalMessage += '.';
                    i += 8;
                    break;
                case "\u0A00":
                    finalMessage += "\u0A00";
                    i += 1;
                    break;
            }


        }
    }

    // Is invoked when the 'Submit' button is pushed.
    public void submitCode(View view){
        // Invokes the translate method to translate the message.
        translate();

        // Stores the reference to the text field.
        TextView content1 = (TextView)findViewById(R.id.textView6);

        // Sets the translated message 'codedMessage' to the text field.
        content1.setText(codedMessage);

        // Stores the reference to the text field.
        TextView content2 = (TextView)findViewById(R.id.textView7);

        // Sets the translated message 'finalMessage' to the text field.
        content2.setText(finalMessage);
    }

    // Is invoked when the 'Clear' button is pushed.
    public void clearCode(View view){
        // Invokes the clear method to null the message.
        clear();

        // Stores the reference to the text field.
        TextView content1 = (TextView)findViewById(R.id.textView6);

        // Sets the translated message 'codedMessage' to the text field.
        content1.setText(codedMessage);

        // Stores the reference to the text field.
        TextView content2 = (TextView)findViewById(R.id.textView7);

        // Sets the translated message 'finalMessage' to the text field.
        content2.setText(finalMessage);
    }

    // Is invoked when the 'Test' button is pushed.
    public void setTEST(View view){
        // Invokes the test method to translate the message.
        testMessage();

        // Stores the reference to the text field.
        TextView content1 = (TextView)findViewById(R.id.textView6);

        // Sets the translated message 'codedMessage' to the text field.
        content1.setText(codedMessage);

        // Stores the reference to the text field.
        TextView content2 = (TextView)findViewById(R.id.textView7);

        // Sets the translated message 'finalMessage' to the text field.
        content2.setText(finalMessage);
    }

}