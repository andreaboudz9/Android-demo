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
        DIV("/"),
        POUR("%");

        private String name = "";
        Opera(String name){this.name = name;}
        public String toString(){return name;}
    }


    private EditText screen;
    private double opera1=0;
    private double opera2=0;
    private Opera operator=null;
    private boolean isOpera1=true;
    private Button buClear;
    private Button buEgal;
    private Button bu8,bu1,bu2, bu5;
    private Button bu3,bu4,bu6, bu7;
    private Button bu9,bu0;
    private View.OnClickListener addNumberListener;
    private Calculator calcul;
    private String  operat = "";
    private boolean comp = false;
    private double resul;
    private boolean cal = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatrice);
        calcul = new Calculator();

        screen = (EditText) findViewById (R.id.screen);

                //Methode 1
                buClear = findViewById(R.id.buClear);
        buClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear(view);
                calcul.Clear();
            }
        });
        //Methode 2
        buEgal = findViewById(R.id.buEgal);
        buEgal.setOnClickListener(this::onClick);
        //Methode 3
        addNumberListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber(view);
            }
        };
        bu1 = findViewById(R.id.bu1);
        bu1.setOnClickListener(addNumberListener);
        bu2 = findViewById(R.id.bu2);
        bu2.setOnClickListener(addNumberListener);
        bu3 = findViewById(R.id.bu3);
        bu3.setOnClickListener(addNumberListener);
        bu4 = findViewById(R.id.bu4);
        bu4.setOnClickListener(addNumberListener);
        bu5 = findViewById(R.id.bu5);
        bu5.setOnClickListener(addNumberListener);
        bu6 = findViewById(R.id.bu6);
        bu6.setOnClickListener(addNumberListener);
        bu7 = findViewById(R.id.bu7);
        bu7.setOnClickListener(addNumberListener);
        bu8 = findViewById(R.id.bu8);
        bu8.setOnClickListener(addNumberListener);
        bu9 = findViewById(R.id.bu9);
        bu9.setOnClickListener(addNumberListener);
        bu0 = findViewById(R.id.bu0);
        bu0.setOnClickListener(addNumberListener);

    }

    private void Afficher() {
        double x=opera1;
        if(!isOpera1) {
             screen.setText(String.valueOf(opera1) +" "+operat+" "+String.valueOf(opera2)); }

        else{
                if(cal == true){
                    screen.setText( String.valueOf(resul) );
                }
                else
                    {
                    screen.setText( String.valueOf(opera1) );
                    }
            }
    }


    public void compute(View v) {
        if(isOpera1) {

        } else {
            switch(operator) {
                case PLUS : resul = this.calcul.addition(opera1,
                        opera2).toDouble(); break;
                case MOINS : resul = this.calcul.soustration(opera1,
                        opera2).toDouble(); break;
                case FOIS: resul = this.calcul.multiplication(opera1,
                        opera2).toDouble(); break;
                case DIV : resul = this.calcul.division(opera1,
                        opera2).toDouble(); break;
                case POUR: resul = this.calcul.reste(opera1,
                        opera2).toDouble(); break;
                default:
                    return;
            }

            opera2 = 0;
            isOpera1 = true;
            Afficher();
        }
    }



    private void clear(View v) {
        opera1 = 0;
        opera2 = 0;
        operator = null;
        isOpera1 = true;
        Afficher();
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
        Afficher();
    }


            public void addNumber(View v){
        try {
            int val = Integer.parseInt(((Button)v).getText().toString());
            if (isOpera1) {
                opera1 = opera1 * 10 + val;
                Afficher();
            } else {
                opera2 = opera2 * 10 + val;
                Afficher();
            }
        }catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée",Toast.LENGTH_LONG);
        }
    }

    public void onClick(View view) {
        compute(view);

    }

}
