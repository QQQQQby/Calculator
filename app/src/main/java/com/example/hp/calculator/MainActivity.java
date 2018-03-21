package com.example.hp.calculator;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button k0,k1,k2,k3,k4,k5,k6,k7,k8,k9,kPl,kMi,kMu,kDi,kPo,kEq;
    ImageButton deleteButton,clearButton;
    private NumberStack numberStack=new NumberStack();
    private OperatorStack operatorStack=new OperatorStack();
    private TextView ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        k0=findViewById(R.id.key0);
        k1=findViewById(R.id.key1);
        k2=findViewById(R.id.key2);
        k3=findViewById(R.id.key3);
        k4=findViewById(R.id.key4);
        k5=findViewById(R.id.key5);
        k6=findViewById(R.id.key6);
        k7=findViewById(R.id.key7);
        k8=findViewById(R.id.key8);
        k9=findViewById(R.id.key9);
        kPl=findViewById(R.id.keyPlus);
        kMi=findViewById(R.id.keyMinus);
        kMu=findViewById(R.id.keyMultiply);
        kDi=findViewById(R.id.keyDivide);
        kPo=findViewById(R.id.keyPoint);
        kEq=findViewById(R.id.keyEqual);
        deleteButton=findViewById(R.id.deleteButton);
        clearButton=findViewById(R.id.clearButton);
        ans=findViewById(R.id.displayExpression);

        k0.setOnClickListener(this);
        k1.setOnClickListener(this);
        k2.setOnClickListener(this);
        k3.setOnClickListener(this);
        k4.setOnClickListener(this);
        k5.setOnClickListener(this);
        k6.setOnClickListener(this);
        k7.setOnClickListener(this);
        k8.setOnClickListener(this);
        k9.setOnClickListener(this);
        kPl.setOnClickListener(this);
        kMi.setOnClickListener(this);
        kMu.setOnClickListener(this);
        kDi.setOnClickListener(this);
        kPo.setOnClickListener(this);
        kEq.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.key0:
                ans.setText(ans.getText()+"0");
                break;
            case R.id.key1:
                ans.setText(ans.getText()+"1");
                break;
            case R.id.key2:
                ans.setText(ans.getText()+"2");
                break;
            case R.id.key3:
                ans.setText(ans.getText()+"3");
                break;
            case R.id.key4:
                ans.setText(ans.getText()+"4");
                break;
            case R.id.key5:
                ans.setText(ans.getText()+"5");
                break;
            case R.id.key6:
                ans.setText(ans.getText()+"6");
                break;
            case R.id.key7:
                ans.setText(ans.getText()+"7");
                break;
            case R.id.key8:
                ans.setText(ans.getText()+"8");
                break;
            case R.id.key9:
                ans.setText(ans.getText()+"9");
                break;
            case R.id.keyPlus:
                ans.setText(ans.getText()+"+");
                break;
            case R.id.keyMinus:
                ans.setText(ans.getText()+"-");
                break;
            case R.id.keyMultiply:
                ans.setText(ans.getText()+"*");
                break;
            case R.id.keyDivide:
                ans.setText(ans.getText()+"/");
                break;
            case R.id.keyPoint:
                ans.setText(ans.getText()+".");
                break;
            case R.id.keyEqual:
                ans.setText(String.valueOf(numberStack.calculate(operatorStack.toReversePolishExpression(ans.getText().toString()))));
                break;
            case R.id.deleteButton:
                if(!ans.getText().toString().isEmpty())
                    ans.setText(ans.getText().subSequence(0,ans.getText().length()-1));
                break;
            case R.id.clearButton:
                ans.setText("");
                break;
        }
    }
}
