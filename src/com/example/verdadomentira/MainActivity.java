package com.example.verdadomentira;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button btnPlay;
	Button btnTutorial;
	Button btnNosotros;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnPlay = (Button) findViewById(R.id.btnPlay);
		
		btnPlay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IniciarJuego();
			}
		});
		
		btnTutorial = (Button) findViewById(R.id.btnTutorial);
		
		btnTutorial.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IniciarTutorial();
			}
		});
		
		btnNosotros = (Button) findViewById(R.id.btnNosotros);
		
		btnNosotros.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IniciarNosotros();
			}
		});
	}
	
	public void IniciarJuego(){
		Intent i= new Intent(this, Juego.class);
		
		startActivity(i);
	}
	
	public void IniciarTutorial(){
		Intent i= new Intent(this, Tutorial.class);
		
		startActivity(i);
	}
	
	public void IniciarNosotros(){
		Intent i= new Intent(this, Nosotros.class);
		
		startActivity(i);
	}

}
