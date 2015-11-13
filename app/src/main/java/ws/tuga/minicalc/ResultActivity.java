package ws.tuga.minicalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    private TextView textView1;
    private Intent intent;
    private String number1, number2, operation;
    private double result;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btn1=(Button)findViewById(R.id.button);
        intent = getIntent();
        //Armazenar os numeros digitados
        number1 = intent.getStringExtra("number1");
        number2 = intent.getStringExtra("number2");
        operation = intent.getStringExtra("operation");
        //uso do bloco try-catch para o caso de o usuário digitar uma letra por exemplo
        try {
            if (operation.equals("+"))
                result = (Double.parseDouble(number1)) + (Double.parseDouble(number2));
            else if (operation.equals("-"))
                result = (Double.parseDouble(number1)) - (Double.parseDouble(number2));
            else if (operation.equals("*"))
                result = (Double.parseDouble(number1)) * (Double.parseDouble(number2));
            else if (operation.equals("/"))
                result = (Double.parseDouble(number1)) / (Double.parseDouble(number2));
            textView1 = (TextView)findViewById(R.id.textView);
            textView1.setText("O resultado é " + String.valueOf(result));

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
            //finaliza a Activity ExemploIntent2 e retorna à Activity Anterior
            this.finish();
        }
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("result", String.valueOf(result));
        setResult(RESULT_OK, data);
        super.finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
