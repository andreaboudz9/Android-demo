package andreaboudz9.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class CalculatriceActivity extends AppCompatActivity {

    public enum Opera {
        PLUS("+"),
        MOINS("-"),
        FOIS("x"),
        DIV("/");

        private String name = "";
        Opera(String name){this.name = name;}
        public String toString(){return name;}
    }


    private EditText screen;
    private int opera1=0;
    private int opera2=0;
    private Opera operator=null;
    private boolean isOpera1=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (EditText) findViewById (R.id.screen);
        Button btnEgal = (Button)findViewById(R.id.buEgal);
        btnEgal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
            }
        });

        Button btnClear = (Button)findViewById(R.id.buClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
    }

    private void updateDisplay() {
        int x=opera1;
        if(!isOpera1) {
            x=opera2;
        }

        screen.setText(String.format("%9d",x));
    }


    public void compute() {
        if(isOpera1) {
            // do nothing
        } else {
            switch(operator) {
                case PLUS  : opera1 = opera1 + opera2; break;
                case MOINS : opera1 = opera1 - opera2; break;
                case FOIS  : opera1 = opera1 * opera2; break;
                case DIV   : opera1 = opera1 / opera2; break;
                default : return; // do nothing if no operator
            }

            opera2 = 0;
            isOpera1 = true;
            updateDisplay();
        }
    }


    private void clear() {
        opera1 = 0;
        opera2 = 0;
        operator = null;
        isOpera1 = true;
        updateDisplay();
    }

    public void setOperator(View v) {
        switch (v.getId()) {
            case R.id.buPlus  : operator=Opera.PLUS;  break;
            case R.id.buMoins : operator=Opera.MOINS; break;
            case R.id.buFois  : operator=Opera.FOIS;  break;
            case R.id.buDiv   : operator=Opera.DIV;   break;
            default :
                Toast.makeText(this, "Cet opérateur n'est pas reconnu",Toast.LENGTH_LONG);
                return;
        }
        isOpera1=false;
        updateDisplay();
    }


    public void addNumber(View v){
        try {
            int val = Integer.parseInt(((Button)v).getText().toString());
            if (isOpera1) {
                opera1 = opera1 * 10 + val;
                updateDisplay();
            } else {
                opera2 = opera2 * 10 + val;
                updateDisplay();
            }
        }catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée",Toast.LENGTH_LONG);
        }
    }
}
