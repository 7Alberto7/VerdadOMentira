package com.inactec.verdadomentira;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.inactec.verdadomentira.R;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button btnPlay;
	Button btnTutorial;
	Button btnNosotros;
	AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);
		Location location = null;
		if (provider != null) {
			location = locationManager.getLastKnownLocation(provider);
		}

		adView = (AdView) findViewById(R.id.adView);
		AdRequest request = new AdRequest();

		if (location != null) {
			request.setLocation(location);
		}

	    adView.loadAd(request);
		
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
