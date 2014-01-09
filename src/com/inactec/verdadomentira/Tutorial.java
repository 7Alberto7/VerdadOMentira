package com.inactec.verdadomentira;

import com.inactec.verdadomentira.R;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Typeface;

public class Tutorial extends Activity {
 
    private TextView txtTutorial;
	private Typeface miFuente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tutorial);
		miFuente = Typeface.createFromAsset(getAssets(), "fonts/SigmarOne.ttf");
		
		txtTutorial = (TextView) findViewById(R.id.txtTutorial);
		txtTutorial.setTypeface(miFuente);
	}

}
