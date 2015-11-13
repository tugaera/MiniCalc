package ws.tuga.minicalc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private static final int REQUEST_CODE = 1;
    private EditText number1, number2;
    private Button btn1;
    private Intent intent;
    private String operation;
    private RadioGroup radioGroupOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Faz a ligação entre a aplicação e o layout
        // Usar findViewById para beter uma referencia de um controlo grafico
        number1 = (EditText)findViewById(R.id.editText);
        number2 = (EditText)findViewById(R.id.editText2);
        btn1 = (Button)findViewById(R.id.button);
        //instância a intent, definindo a Classe ExemploIntent2 como destino
        intent = new Intent(this,ResultActivity.class);

        //método define a ação do botão, caso clicado
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insere na Intent os dois numero digitados pelo utilizador
                intent.putExtra("number1",number1.getText().toString());
                intent.putExtra("number2",number2.getText().toString());
                intent.putExtra("operation",operation);
                //lança a intenção
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        radioGroupOperation = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroupOperation.setOnCheckedChangeListener(this);
    }

    // Obeter a operação escolhida (juntamente com o setOnCheckedChangeListener)
    public void onCheckedChanged (RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.soma:
                operation="+"; break;
            case R.id.subtracao:
                operation="-"; break;
            case R.id.multiplicacao:
                operation="*"; break;
            case R.id.divisao:
                operation="/"; break;
        }
        Toast.makeText(MainActivity.this, operation, Toast.LENGTH_SHORT).show();
    }

    //Apresentar o resultado numa janela flutuante (TOAST), periodo estendido (LENGTH_LONG)
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("result")) {
                Toast.makeText(this, "O resultado da operação anterior foi de " + data.getExtras().getString("result"), Toast.LENGTH_LONG).show();
                                                                                //data.getStringExtra("result")
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

