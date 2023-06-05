package com.danieleloy.mazocomida;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ReservarActivity extends AppCompatActivity {

    private Calendar fechaSeleccionada;

    private EditText edtNombreCliente = null;
    private EditText edtNumeroClientes = null;

    private TextView edtFechaReserva = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);

        edtNombreCliente = (EditText) findViewById(R.id.EditTextNombre);
        edtNumeroClientes = (EditText) findViewById(R.id.EditTextNumeroPersonas);

        edtFechaReserva = (TextView) findViewById(R.id.txtFechaHoraSeleccionada);

    }

    public void Reservar(View view) {
        String nombreCliente = edtNombreCliente.getText().toString().trim();
        String numeroClientes = edtNumeroClientes.getText().toString().trim();
        String fechaReserva = edtFechaReserva.getText().toString().trim();

        if (nombreCliente.isEmpty() || numeroClientes.isEmpty() || fechaReserva.isEmpty()) {
            // Al menos uno de los campos está vacío, mostrar un error
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            // Todos los campos están completos, realizar la reserva
            CollectionReference reservasRef = FirebaseFirestore.getInstance().collection("reservas");

            reservasRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot documentSnapshots) {
                    int numReservas = documentSnapshots.size();

                    // Verificar el límite de reservas
                    if (numReservas >= 2) {
                        // Mostrar mensaje de "Estamos llenos"
                        Toast.makeText(ReservarActivity.this, "Lo sentimos, estamos llenos", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(numeroClientes) > 5) {
                        // Mostrar mensaje de "Máximo 5 clientes"
                        Toast.makeText(ReservarActivity.this, "Máximo 5 clientes", Toast.LENGTH_SHORT).show();
                    } else {
                        // Continuar con el proceso de guardar la reserva en Firebase

                        // Obtener la hora actual
                        Calendar calendar = Calendar.getInstance();
                        int diaActual = calendar.get(Calendar.DAY_OF_MONTH);
                        int añoActual = calendar.get(Calendar.YEAR);
                        int mesActual = calendar.get(Calendar.MONTH);
                        int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
                        int minutoActual = calendar.get(Calendar.MINUTE);
                        int segundoActual = calendar.get(Calendar.SECOND);

                        // Crear un nuevo nodo con el nombre del cliente como clave
                        String nodoCliente = nombreCliente + " " + String.valueOf(añoActual) + " " + String.valueOf(mesActual) + " " + String.valueOf(diaActual) + String.valueOf(horaActual) + String.valueOf(minutoActual) + String.valueOf(segundoActual);
                        DocumentReference clienteRef = reservasRef.document(nodoCliente);

                        // Crear un Map con los datos del cliente
                        Map<String, Object> clienteData = new HashMap<>();
                        clienteData.put("nombreCliente", nombreCliente);
                        clienteData.put("numeroClientes", numeroClientes);
                        clienteData.put("fechaReserva", fechaReserva);

                        // Guardar los datos del cliente en Firebase
                        clienteRef.set(clienteData).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Mostrar mensaje de éxito
                                Toast.makeText(ReservarActivity.this, "Reserva realizada correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Mostrar mensaje de error en caso de fallo al guardar la reserva
                                Toast.makeText(ReservarActivity.this, "Error al realizar la reserva", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Mostrar mensaje de error en caso de fallo al obtener las reservas
                    Toast.makeText(ReservarActivity.this, "Error al obtener las reservas", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }




    public void mostrarDatePickerDialog(View view) {
        final Calendar calendarioActual = Calendar.getInstance();
        int año = calendarioActual.get(Calendar.YEAR);
        int mes = calendarioActual.get(Calendar.MONTH);
        int día = calendarioActual.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Guardar la fecha seleccionada
                fechaSeleccionada = Calendar.getInstance();
                fechaSeleccionada.set(year, monthOfYear, dayOfMonth);

                // Mostrar el diálogo de selección de hora
                mostrarTimePickerDialog();
            }
        }, año, mes, día);

        // Establecer la fecha mínima como la fecha actual
        datePickerDialog.getDatePicker().setMinDate(calendarioActual.getTimeInMillis());

        // Mostrar el diálogo de selección de fecha
        datePickerDialog.show();
    }


    public void mostrarTimePickerDialog() {
        if (fechaSeleccionada != null) {
            int hora = fechaSeleccionada.get(Calendar.HOUR_OF_DAY);
            int minuto = fechaSeleccionada.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    // Validar el rango de tiempo seleccionado
                    if ((hourOfDay == 13 && minute >= 0) || (hourOfDay > 13 && hourOfDay <= 22)) { // Entre 1:00 PM y 10:00 PM
                        // Actualizar la fecha seleccionada con la hora seleccionada
                        fechaSeleccionada.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        fechaSeleccionada.set(Calendar.MINUTE, minute);

                        // Aquí puedes realizar cualquier acción con la fecha y hora seleccionadas
                        // Por ejemplo, guardarlas en una base de datos o mostrarlas en un TextView
                        // ...

                        // Ejemplo: Mostrar la fecha y hora seleccionadas en un TextView
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                        String fechaHoraSeleccionada = sdf.format(fechaSeleccionada.getTime());
                        TextView txtFechaHoraSeleccionada = findViewById(R.id.txtFechaHoraSeleccionada);
                        txtFechaHoraSeleccionada.setText(fechaHoraSeleccionada);
                    } else {
                        // Mostrar un mensaje de error si se selecciona una hora fuera del rango permitido
                        Toast.makeText(ReservarActivity.this, "Por favor, selecciona una hora entre 1:00 PM y 10:00 PM", Toast.LENGTH_SHORT).show();
                    }
                }
            }, hora, minuto, false);

            // Mostrar el diálogo de selección de hora
            timePickerDialog.show();
        }
    }





}