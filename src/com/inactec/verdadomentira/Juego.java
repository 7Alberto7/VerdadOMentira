package com.inactec.verdadomentira;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.inactec.verdadomentira.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Juego extends Activity {
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1001;
    private static final int NUM_FRASES = 1;
 
    private TextView txtFrase;
    private ImageButton btnHablar;
    private ImageView imgVeredicto;
    private MediaPlayer mentira1, mentira2, mentira3, verdad1, verdad2, verdad3;
    private long tiempoInicio, tiempoFin;
	private AdView adView;
	private Typeface miFuente;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
		miFuente = Typeface.createFromAsset(getAssets(), "fonts/SigmarOne.ttf");

		LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);
		Location location = null;
		if (provider != null) {
			location = locationManager.getLastKnownLocation(provider);
		}

		adView = (AdView) findViewById(R.id.adViewJuego);
		AdRequest request = new AdRequest();

		if (location != null) {
			request.setLocation(location);
		}

	    adView.loadAd(request);
        
     
        txtFrase = (TextView) findViewById(R.id.txtFrase);
        txtFrase.setTypeface(miFuente);
        btnHablar = (ImageButton) findViewById(R.id.btnHablar);
        imgVeredicto = (ImageView) findViewById(R.id.imgVeredicto);
        
        //Sonidos
		mentira1 = MediaPlayer.create(this, R.raw.mentira1);
		mentira2 = MediaPlayer.create(this, R.raw.mentira2);
		mentira3 = MediaPlayer.create(this, R.raw.mentira3);
		verdad1 = MediaPlayer.create(this, R.raw.verdad1);
		verdad2 = MediaPlayer.create(this, R.raw.verdad2);
		verdad3 = MediaPlayer.create(this, R.raw.verdad3);
		
		btnHablar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hablar(v);
			}
		});
    }
 
    public void hablar(View view) {
    	Calendar cal = new GregorianCalendar();
    	tiempoInicio = cal.getTimeInMillis();
    	try{
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass()
                    .getPackage().getName());
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, NUM_FRASES);
            startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    	}
    	catch(ActivityNotFoundException e)
    	{
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	 
    		builder.setTitle("Ups! No tienes instalado Voice Search de Google");
    		builder.setMessage("¿Quieres instalarlo?");
    	 
    		builder.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {    	 
	    	   @Override
	    	   public void onClick(DialogInterface dialog, int which) {	    	         
	    	        // Sí. Abre Play Store para descargar el Voice Recognizer.
	    	        dialog.dismiss();
	    	        Intent browserIntent = new Intent(Intent.ACTION_VIEW,   Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.voicesearch"));
	    	        startActivity(browserIntent);
	    	   }    	 
    		});
    	 
    		builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {    	 
    			@Override
    			public void onClick(DialogInterface dialog, int which) {
    				// No. Aparece un toast diciendo que no puede ejecutarse el programa puesto que no tienes instalado el reconocedor de voz de google.    			
    				dialog.dismiss();
    	        	Toast.makeText(getApplicationContext(), "No podrá ejecutarse el reconocedor de voz de Google ya que no lo tienes instalado", Toast.LENGTH_SHORT).show();
    			}    	 
    		});    	 
    	 
    		AlertDialog alert = builder.create();
    		alert.show();
    	}
    }
 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE)
 
            //If Voice recognition is successful then it returns RESULT_OK
            if(resultCode == RESULT_OK) {
            	Calendar cal = new GregorianCalendar();
            	tiempoFin = cal.getTimeInMillis();
            	
            	long duracion = tiempoFin - tiempoInicio;
            	
                String textResultado = data
                .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
 
                if (textResultado != "") {
                	txtFrase.setText("Has dicho '" + textResultado + "' y es...");
                    
                    boolean veredicto = CalcularVeredicto(textResultado, duracion);
                    
                    if (veredicto) {
                        imgVeredicto.setImageResource(R.drawable.verdad);
					} else {
                        imgVeredicto.setImageResource(R.drawable.mentira);
					}
                    
                } else {
                	showToastMessage("No te oigo, vuelve a intentarlo.");
                }
            //Result code for various error.    
            }else if(resultCode == RecognizerIntent.RESULT_AUDIO_ERROR){
                showToastMessage("Audio Error");
            }else if(resultCode == RecognizerIntent.RESULT_CLIENT_ERROR){
                showToastMessage("Client Error");
            }else if(resultCode == RecognizerIntent.RESULT_NETWORK_ERROR){
                showToastMessage("Network Error");
            }else if(resultCode == RecognizerIntent.RESULT_NO_MATCH){
                showToastMessage("No Match");
            }else if(resultCode == RecognizerIntent.RESULT_SERVER_ERROR){
                showToastMessage("Server Error");
            }
        super.onActivityResult(requestCode, resultCode, data);
        
    }
    public void showToastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public Boolean CalcularVeredicto(String cadena, long duracion) {
    	double valorRandom = Math.random();
    	int signoCoef = 1;
    	if (valorRandom < 0.5) {
			signoCoef = -1;
		}
    	double coefAleatoriedad = signoCoef * (valorRandom * 0.2);
		double valor =  ((double) cadena.length()*100 / (duracion - 2500)) + coefAleatoriedad;
		boolean veredicto = true;
		
		if (valor < 0.8) {
			veredicto = false;
		}

		if (valor < 0.4) {
			mentira1.start();
		} else if (valor >= 0.4 && valor < 0.6) {
			mentira2.start();
		} else if (valor >= 0.6 && valor < 0.8) {
			mentira3.start();
		} else if (valor >= 0.8 && valor < 1) {
			verdad1.start();
		} else if (valor >= 1 && valor < 1.2) {
			verdad2.start();
		} else {
			verdad3.start();
		}
		
    	return veredicto;
    }

}
