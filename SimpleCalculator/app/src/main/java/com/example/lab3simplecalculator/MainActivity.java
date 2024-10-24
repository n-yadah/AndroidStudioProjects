package com.example.lab3simplecalculator;

import android.os.Bundle;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.view.View;
import android.widget.EditText;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;



// Improved code follows (line 151)

public class MainActivity extends AppCompatActivity {
    private enum Operator {none, add, minus, multiply, divide, eq}

    private double data1 = 0, data2 = 0;
    private Operator optr = Operator.none;
    private boolean requiresCleaning = false;
    private boolean hasDot = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btn00Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "0");
    }

    public void btn01Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "1");
    }

    public void btn02Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "2");
    }

    public void btn03Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "3");
    }

    public void btn04Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "4");
    }

    public void btn05Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "5");
    }

    public void btn06Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "6");
    }

    public void btn07Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "7");
    }

    public void btn08Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "8");
    }

    public void btn09Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "9");
    }

    public void btnAddClick(View view) {
        optr = Operator.add;
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        data1 = Double.parseDouble(eText.getText().toString());
        eText.setText("");
    }

    public void btnMinusClick(View view) {
        optr = Operator.minus;
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        data1 = Double.parseDouble(eText.getText().toString());
        eText.setText("");
    }

    public void btnMultiplyClick(View view) {
        optr = Operator.multiply;
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        data1 = Double.parseDouble(eText.getText().toString());
        eText.setText("");
    }

    public void btnDivideClick(View view) {
        optr = Operator.divide;
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        data1 = Double.parseDouble(eText.getText().toString());
        eText.setText("");
    }

    public void btnClearClick(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText("");
    }

    public void btnDotClick(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + ".");
    }

    public void btnResultClick(View view) {
        if (optr != Operator.none) {
            TextView eText = (TextView) findViewById(R.id.resultEdit);
            data2 = Double.parseDouble(eText.getText().toString());
            double result = 0;
            if (optr == Operator.add) {
                result = data1 + data2;
            } else if (optr == Operator.minus) {
                result = data1 - data2;
            } else if (optr == Operator.multiply) {
                result = data1 * data2;
            } else if (optr == Operator.divide) {
                result = data1 / data2;
            }
            optr = Operator.none;
            data1 = result;
            if ((result - (int) result) != 0) {
                eText.setText(String.valueOf(result));
            } else {
                eText.setText(String.valueOf((int) result));
            }
        }
    }

    /*next portion of the code will be function if
       android:onClick values of the buttons are changed
       to "onClickFunctionButton".
     */

    //Function called every time a number button is pressed
    public void onClickNumericalButton(View view) {
        //Getting ID of pressed Button
        int pressID = view.getId();
        //Getting Text object where we display the current number value
        TextView curText = (TextView) findViewById(R.id.resultEdit);
        //If we had an equal sign pressed last, standard operation is to clean
        if (optr == Operator.eq) {
            optr = Operator.none;
            curText.setText("");
        }
        if (requiresCleaning) {
            requiresCleaning = false;
            curText.setText("");
        }
        //Figuring out which button was pressed and updating the represented text field object
        switch (pressID) {
            case R.id.button00:
                curText.setText(curText.getText() + "0");
                break;
            case R.id.button01:
                curText.setText(curText.getText() + "1");
                break;
            case R.id.button02:
                curText.setText(curText.getText() + "2");
                break;
            case R.id.button03:
                curText.setText(curText.getText() + "3");
                break;
            case R.id.button04:
                curText.setText(curText.getText() + "4");
                break;
            case R.id.button05:
                curText.setText(curText.getText() + "5");
                break;
            case R.id.button06:
                curText.setText(curText.getText() + "6");
                break;
            case R.id.button07:
                curText.setText(curText.getText() + "7");
                break;
            case R.id.button08:
                curText.setText(curText.getText() + "8");
                break;
            case R.id.button09:
                curText.setText(curText.getText() + "9");
                break;
            case R.id.buttonDot:
                if (!hasDot) {
                    curText.setText(curText.getText() + ".");
                    hasDot = true;
                } else {
                    //Do nothing
                }
                break;
            default:
                curText.setText("ERROR");
                Log.d("Error", "Error:Unknown Button Pressed!");
                break;
        }
    }

    public void onClickFunctionButton(View view) {
        //Getting ID of pressed Button
        int pressID = view.getId();
        //Getting Text object where we display the current number value
        TextView curText = (TextView) findViewById(R.id.resultEdit);
        //CE clears all regardless of context
        if (pressID == R.id.buttonCE) {
            optr = Operator.none;
            curText.setText("");
            data1 = 0;
            data2 = 0;
            requiresCleaning = false;
            return;
        }
        String dataText = curText.getText().toString();
        double numberVal = dataText.length() > 0 ? Double.parseDouble(dataText) : 0;
        //Checking if we have "prior data" aka something stored in data1 that we should use
        // Having data1 != 0 is not enough, we need to know of a previous operator, hence this
        if (optr == Operator.none) {
            data1 = numberVal;
            requiresCleaning = true;
            switch (pressID) {
                case R.id.buttonEq:
                    //Do nothing
                    optr = Operator.eq;
                    data1 = 0;
                    break;
                case R.id.buttonAdd:
                    optr = Operator.add;
                    break;
                case R.id.buttonSub:
                    optr = Operator.minus;
                    break;
                case R.id.buttonDiv:
                    optr = Operator.divide;
                    break;
                case R.id.buttonMult:
                    optr = Operator.multiply;
                    break;
                case R.id.buttonCE:
                    optr = Operator.none;
                    break;
            }
        } else {
            double result = 0;
            data2 = numberVal;
            switch (optr) {
                case eq:
                    break;
                case none:
                    break;
                case add:
                    result = data1 + data2;
                    break;
                case minus:
                    result = data1 - data2;
                    break;
                case divide:
                    result = data1 / data2;
                    break;
                case multiply:
                    result = data1 * data2;
                    break;
            }
            data1 = result;
            optr = Operator.none;
            if (result - (int)result != 0){
                curText.setText(String.valueOf(result));
            }else{
                curText.setText(String.valueOf((int)result));
            }
        }
    }
}