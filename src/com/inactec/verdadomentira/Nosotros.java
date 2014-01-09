package com.inactec.verdadomentira;

import com.inactec.verdadomentira.R;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Typeface;

public class Nosotros extends Activity {
 
    private TextView txtNosotros;
	private Typeface miFuente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nosotros);
		miFuente = Typeface.createFromAsset(getAssets(), "fonts/SigmarOne.ttf");
		
		txtNosotros = (TextView) findViewById(R.id.txtNosotros);
		txtNosotros.setTypeface(miFuente);
	}

}
