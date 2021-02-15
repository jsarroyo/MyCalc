package com.example.mycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_0,btn_Add,btn_Sub,btn_Mul,btn_Div,btn_result,btn_dec,btn_clear,btn_clearOne;
    TextView ed1;
    String Value1, Value2;
    //nCaso == 0 // No tiene operador
    //nCaso == 1 // Es Suma
    //nCaso == 2 // Es Division
    //nCaso == 3 // Es Multiplicacion
    //nCaso == 4 // Es resta
    int nCaso;
    public static final int nWithoutMathOperation = 0;
    public static final int nAddMathOperation = 1;
    public static final int nDivMathOperation = 2;
    public static final int nMultMathOperation = 3;
    public static final int nSubsMathOperation = 4;
    public static final int nExecuteMathOperation = 5;
    public static final String cEqualsOperator = "=";
    public static final String cDivisionOperator = "/";
    public static final String cMultiplicationOperator = "*";
    public static final String cSubstractOperator = "-";
    public static final String cAddOperator = "+";


    boolean mAddition, mSubtract, mMultiplication, mDivision ;

    TextView txtVP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (TextView) findViewById(R.id.txt_Historia);
        btn_0 = (Button) findViewById(R.id.btn_Cero);
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText() == ""){
                    ed1.setText("0");
                }
                else{
                    if(ed1.getText() == "0"){
                        ed1.setText("0.0");
                    }else{
                        ed1.setText(ed1.getText()+"0");
                    }
                }
            }
        });

        btn_1 = (Button) findViewById(R.id.btn_Uno);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"1");
            }
        });

        btn_2 = (Button) findViewById(R.id.btn_Dos);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"2");
            }
        });

        btn_3 = (Button) findViewById(R.id.btn_Tres);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"3");
            }
        });

        btn_4 = (Button) findViewById(R.id.btn_Cuatro);
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"4");
            }
        });

        btn_5 = (Button) findViewById(R.id.btn_Cinco);
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"5");
            }
        });

        btn_6 = (Button) findViewById(R.id.btn_Seis);
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"6");
            }
        });

        btn_7 = (Button) findViewById(R.id.btn_Siete);
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"7");
            }
        });

        btn_8 = (Button) findViewById(R.id.btn_Ocho);
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"8");
            }
        });

        btn_9 = (Button) findViewById(R.id.btn_Nueve);
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"9");
            }
        });

        btn_dec = (Button) findViewById(R.id.btn_Punto);
        btn_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ed1.getText().toString().contains(".")){
                    ed1.setText(ed1.getText()+".");
                }
                //else{} //toast = no se puede agregar otro punto
            }
        });

        btn_result = (Button) findViewById(R.id.btn_Resultado);
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMathCommand("=");
            }
        });


        btn_Add = (Button) findViewById(R.id.btn_Suma);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMathCommand(cAddOperator);
            }
        });

        btn_Sub = (Button) findViewById(R.id.btn_Resta);
        btn_Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMathCommand(cSubstractOperator);
            }
        });

        btn_Mul = (Button) findViewById(R.id.btn_Multiplicacion);
        btn_Mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMathCommand(cMultiplicationOperator);
            }
        });

        btn_Div = (Button) findViewById(R.id.btn_Division);
        btn_Div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMathCommand(cDivisionOperator);
            }
        });

        btn_clear = (Button) findViewById(R.id.btn_BorrarTodo);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText("");
            }
        });

        btn_clearOne = (Button) findViewById(R.id.btn_BorrarUno);
        btn_clearOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vlntam = ed1.getText().length();
                if(vlntam>0){
                    ed1.setText(ed1.getText().toString().substring(0,vlntam-1));
                }
            }
        });

    }

    private void EvaluateExpression() {
        try {
            Float nResultado;

            Log.println(Log.INFO,"Main","evaluar expresion, caso: " + nCaso);

            String cExpresion = ed1.getText().toString();
            int nIndexOfcOperator = cExpresion.indexOf(nCaso == nAddMathOperation ? cAddOperator : nCaso == nDivMathOperation ? cDivisionOperator : nCaso == nMultMathOperation ? cMultiplicationOperator : cSubstractOperator);
            if (nCaso != nExecuteMathOperation){
                if (nIndexOfcOperator ==-1)
                {
                    Log.println(Log.INFO,"Main","No se encontro operador EN LA EXPRESION " + cExpresion +  " CASO: " + nCaso);
                    return;
                }
            }
            Log.println(Log.INFO,"Main","indice del operador: " + nCaso + " indice: " + nIndexOfcOperator);
            int nLen = cExpresion.length();
            String cFirstPart = cExpresion.substring(0, nIndexOfcOperator );
            String cSecondPart = cExpresion.substring(nIndexOfcOperator+1, nLen);

            Log.println(Log.INFO,"Main","cFirstPart: "+ cFirstPart);
            Log.println(Log.INFO,"Main","cSecondPart: "+  cSecondPart);

            if(cFirstPart!=""){

                nResultado = Float.parseFloat(cFirstPart);

                //nCaso == 1 // Es Suma
                //nCaso == 2 // Es Division
                //nCaso == 3 // Es Multiplicacion
                //nCaso == 4 // Es resta
                if(cSecondPart!=""){
                    if (nCaso == nAddMathOperation) {
                        nResultado = nResultado + Float.parseFloat(cSecondPart);
                    } else if (nCaso == nDivMathOperation) {
                        nResultado = cSecondPart != "0"? nResultado / Float.parseFloat(cSecondPart): 0;
                        //No definida la division por cero
                    } else if (nCaso == nMultMathOperation) {
                        nResultado = nResultado * Float.parseFloat(cSecondPart);
                    } else if (nCaso == nSubsMathOperation) {
                        nResultado = nResultado - Float.parseFloat(cSecondPart);
                    }
                    if(nCaso>0 && nCaso< 5){
                        ed1.setText( String.valueOf(nResultado));
                    }
                }
            }

            nCaso = 0;
            Set_AbleButtons();

        }catch (Exception ex ){
            Log.println(Log.INFO,"Main","error al evaluar: " + ex.getMessage());
            //ex.getMessage()
        }
    }

    private void InsertOperation(String vlcParam) {
        int nPreviusExpresionContainOperator = 0;
        String cExpression = "";
        cExpression = ed1.getText().toString();
        int nExpressionLen = cExpression.length();

        //validacion-1 validar que el comando tenga antes una expresion
        //validacion-2 validar que el ultimo comando no sea una operacion, si es, lo reemplaza y no procesa la expresion

        //validacion-1
        if(nExpressionLen==0)
        {
            Log.println(Log.INFO,"Main","Tamano no es suficiente");
            return;
        }
        nPreviusExpresionContainOperator = cExpression.contains(cAddOperator)?1:0;
        nPreviusExpresionContainOperator = nPreviusExpresionContainOperator > 0? nPreviusExpresionContainOperator : (cExpression.contains(cDivisionOperator)?2:0);
        nPreviusExpresionContainOperator = nPreviusExpresionContainOperator > 0? nPreviusExpresionContainOperator : (cExpression.contains(cMultiplicationOperator)?3:0);
        nPreviusExpresionContainOperator = nPreviusExpresionContainOperator > 0? nPreviusExpresionContainOperator : (cExpression.contains(cSubstractOperator)?4:0);

        if(nPreviusExpresionContainOperator==0 && vlcParam == cEqualsOperator ){
            Log.println(Log.INFO,"Main","Comando innecesario, no hay operaciones para calcular");
            return;
        }
        String cLastCharacter = "";
        if (nPreviusExpresionContainOperator>0){
            Log.println(Log.INFO,"Main","Operacion a calcular " + nPreviusExpresionContainOperator);
            //validacion-2
            if(nExpressionLen>=2){
                Log.println(Log.INFO,"Main","Operacion a calcular " + ed1.getText().toString());

                cLastCharacter = cExpression.substring(nExpressionLen-2,nExpressionLen-1);
                if(cLastCharacter == cAddOperator
                        || cLastCharacter == cDivisionOperator
                        || cLastCharacter == cMultiplicationOperator
                        || cLastCharacter == cSubstractOperator  )
                {
                    if ( vlcParam != cEqualsOperator){
                        ed1.setText( ed1.getText().toString().substring(0,nExpressionLen-2) + vlcParam);
                    }

                    Log.println(Log.INFO,"Main","resultado " + ed1.getText().toString());

                    return;
                }
            }else{
                Log.println(Log.INFO,"Main","Tamano de la expresion invalida. ");
                return;
            }
        }

        Log.println(Log.INFO,"Main","Procede con el calculo ");
        //EL ULTIMO CARACTER NO ES UNA OPERCION
        //INTENTAR PROCESAR
        switch (vlcParam)
        {
            case cAddOperator:
                nCaso = nAddMathOperation;
                break;
            case cDivisionOperator:
                nCaso = nDivMathOperation;
                break;
            case cMultiplicationOperator:
                nCaso = nMultMathOperation;
                break;
            case cSubstractOperator:
                nCaso = nSubsMathOperation;
                break;
            case cEqualsOperator:
                nCaso = nCaso; //queda igual
                break;
            default:
                nCaso = nWithoutMathOperation;
                break;
        }

        Log.println(Log.INFO,"Main","Procede con el calculo CASO " + nCaso);

        if(nPreviusExpresionContainOperator == nWithoutMathOperation && vlcParam != cEqualsOperator){
            //Agrega el comando sin ejecutar
            Log.println(Log.INFO,"Main","Agrega comando " + nCaso);

            ed1.setText( ed1.getText().toString() + vlcParam);
            return;
        }

        if(nPreviusExpresionContainOperator == nAddMathOperation ||
                nPreviusExpresionContainOperator == nDivMathOperation ||
                nPreviusExpresionContainOperator == nMultMathOperation ||
                nPreviusExpresionContainOperator == nSubsMathOperation){

                //Intenta evaluar la expression actual y la ejecuta, luego le suma el comando del nuevo operador
                //sI EL COMANDO enviado es un IGUAL, no le agrega el nuevo comando.
            Log.println(Log.INFO,"Main","Intenta evaluar la expresion  " + nPreviusExpresionContainOperator );
            EvaluateExpression();
                if (!(vlcParam == cEqualsOperator)){
                    ed1.setText( ed1.getText().toString().substring(0, ed1.getText().length()) + vlcParam);
                }
        }
        Set_AbleButtons();
    }
    private void Set_AbleButtons() {

        if(nCaso > 0 && nCaso < 5){
            btn_Div.setEnabled(false);
            btn_Mul.setEnabled(false);
            btn_Add.setEnabled(false);
            btn_Sub.setEnabled(false); 
        }
        else   {
            btn_Add.setEnabled(true);
            btn_Div.setEnabled(true);
            btn_Mul.setEnabled(true);
            btn_Sub.setEnabled(true);
        }
    }
    private void SendMathCommand(String vlcCommand){
        if (ed1 == null){
            ed1.setText("");
        }else {
            InsertOperation(vlcCommand);
        }
    }
}