package com.example.salarychecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText campoSalario;
    private EditText campoHorasExtras;
    private EditText campoBonificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonCalcular = findViewById(R.id.btnCalcular);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarCalculo();
            }
        };

        botonCalcular.setOnClickListener(onClickListener);

        campoSalario = findViewById(R.id.salario);
        campoHorasExtras = findViewById(R.id.horasExtras);
        campoBonificacion = findViewById(R.id.bonificacion);
    }

    private void realizarCalculo() {

        // Obtener los parámetros del salario base, horas extras y bonificaciones
        double salario = Double.parseDouble(campoSalario.getText().toString());
        int horasExtras = Integer.parseInt(campoHorasExtras.getText().toString());
        int bonificacion = Integer.parseInt(campoBonificacion.getText().toString());

        // Calcular el valor a pagar al empleado
        double valorHoraNormal = salario / 192;
        double valorHorasExtras = valorHoraNormal * 1.25 * horasExtras;
        double valorBonificaciones = salario * 0.05 * bonificacion;
        double salarioTotalAntesDescuentos = salario + valorHorasExtras + valorBonificaciones;
        double descuentoSalud = salarioTotalAntesDescuentos * 0.035;
        double descuentoPension = salarioTotalAntesDescuentos * 0.04;
        double descuentoCajaCompensacion = salarioTotalAntesDescuentos * 0.01;
        double valorPagar = salarioTotalAntesDescuentos - descuentoSalud - descuentoPension - descuentoCajaCompensacion;

        TextView valorPago = findViewById(R.id.valorPago);
        valorPago.setText(String.valueOf(valorPagar));
    }
}
