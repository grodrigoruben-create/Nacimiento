package com.example.nacimiento;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.Period;

public class MainActivity extends AppCompatActivity
        implements View.OnContextClickListener, View.OnClickListener {

    TextView nacimiento, anio, meses, dia;
    CalendarView cl;
    Button bt;

    // Aquí vamos a guardar la fecha seleccionada
    LocalDate fechaNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );
            return insets;
        });

        nacimiento = findViewById(R.id.nacimiento);
        anio = findViewById(R.id.anio);
        meses = findViewById(R.id.meses);
        dia = findViewById(R.id.dia);
        cl = findViewById(R.id.cl);
        bt = findViewById(R.id.bt);

        bt.setOnClickListener(this);

        cl.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(
                    CalendarView view,
                    int year,
                    int month,
                    int dayOfMonth) {

                // CalendarView empieza los meses en 0
                int mesCorrecto = month + 1;

                // Guardamos la fecha seleccionada
                fechaNacimiento = LocalDate.of(
                        year,
                        mesCorrecto,
                        dayOfMonth
                );

                // Mostramos la fecha seleccionada
                nacimiento.setText(
                        dayOfMonth + "/" + mesCorrecto + "/" + year
                );
            }
        });
    }

    @Override
    public void onClick(View v) {

        // Comprobamos que se haya seleccionado una fecha
        if (fechaNacimiento == null) {
            nacimiento.setText("Selecciona tu fecha de nacimiento");
            return;
        }

        // Fecha actual
        LocalDate hoy = LocalDate.now();

        // Calculamos la diferencia entre ambas fechas
        Period edad = Period.between(fechaNacimiento, hoy);

        // Mostramos la edad
        anio.setText(String.valueOf(edad.getYears()));
        meses.setText(String.valueOf(edad.getMonths()));
        dia.setText(String.valueOf(edad.getDays()));
    }

    @Override
    public boolean onContextClick(View v) {
        return false;
    }
}

