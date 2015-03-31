package com.appcsv;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.os.Parcelable;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.appcsv.R;


public class MainActivity extends Activity {
    private ListView listView;
    TableLayout tabla;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabla = (TableLayout)findViewById(R.id.listadoDatos);

        mostrar();         

    }
    
    public void mostrar()
    { 
    	String datos="";
    
        
		File file = new File(Environment.getExternalStorageDirectory(), "/datos.csv");
          

        InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
		
		
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> listadoDatos = csvFile.read();
    	    tabla.setStretchAllColumns(true);
    	    tabla.bringToFront();
    	    
    	    for(int i = 0; i < listadoDatos.size(); i++){
    	    	TableRow tr =  new TableRow(this);
    	    	
    	    	String[] arreglo=listadoDatos.get(i);
    	    	
    	    	for(int x=0;x<arreglo.length;x++){

        	    	List<String> listaCondicionesArreglo=new ArrayList<String>(
        					Arrays.asList(arreglo[x].split(";")));
        	    	
        	    	for(int w=0; w<listaCondicionesArreglo.size(); w++)
        	    	{
            	        TextView c1 = new TextView(this);
            	        c1.setText(listaCondicionesArreglo.get(w)+"       ");
            	        
            	        if( i % 2 == 0) 
            	        {
            	        	c1.setBackgroundResource(R.color.azul);
            	        }
            	        else{
            	        	c1.setBackgroundResource(R.color.verde);
            	        }

            	        
            	        tr.addView(c1);
        	    	}       	        
    	    		   	    		
    	    	}  
    	
    	        tabla.addView(tr);
    	        datos="";
    	    }
    	    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(Menu.NONE, 1, Menu.NONE, "Actualizar datos");

		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		int opcion = item.getItemId();
		Intent intent;

		switch (opcion) {

		case 1:
			Intent i = new Intent(getBaseContext(), MainActivity.class);
			startActivity(i);
			
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}
    


}