package com.example.examen_prog_23_24_extraordinaria_client;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen_prog_23_24_extraordinaria_client.model.Jugador;
import com.example.examen_prog_23_24_extraordinaria_client.model.MaquinaAzar;
import com.example.examen_prog_23_24_extraordinaria_client.model.MyRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MaquinaAzar tragaperras;
    private Jugador jugador;
    private Button btnMoneda;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter recyclerViewAdapter;

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

        jugador = new Jugador("Joaquin Alonso",20);
        tragaperras = findViewById(R.id.tragaperras);
        btnMoneda = findViewById(R.id.btnMoneda);
        recyclerView = findViewById(R.id.recycler);

        recyclerViewAdapter = new MyRecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnMoneda.setOnClickListener(v->{

            if(jugador.gastarMoneda()) {
                tragaperras.introducirMoneda();
                recyclerViewAdapter.insertValue(new MyRecyclerViewAdapter.Linea(jugador.getNombre(),-1,jugador.getSaldo()));
            }else
                Toast.makeText(getApplicationContext(),"No tienes saldo",Toast.LENGTH_LONG).show();
        });

        tragaperras.recogerPremioListener(this);

    }

    @Override
    public void onClick(View view) {
        float premio = tragaperras.retirarPremio();
        if(premio!=0) {
            jugador.recogePremio(premio);
            recyclerViewAdapter.insertValue(new MyRecyclerViewAdapter.Linea(jugador.getNombre(), premio, jugador.getSaldo()));
        } else
            Toast.makeText(getApplicationContext(),"Debes jugar primero",Toast.LENGTH_LONG).show();
    }
}