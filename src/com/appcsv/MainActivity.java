package com.appcsv;


import android.app.Activity;
import android.os.Environment;
import android.os.Parcelable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
    private ItemArrayAdapter itemArrayAdapter;
    TableLayout prices;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
      
        prices = (TableLayout)findViewById(R.id.listadoDatos);

        
		File file = new File(Environment.getExternalStorageDirectory(), "/datos.csv");
          

        InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> listadoDatos = csvFile.read();
        
        mostrar(listadoDatos);
         

    }
    
    public void mostrar(List<String[]> pListadoDatos)
    { 
    	String datos="";
    	    prices.setStretchAllColumns(true);
    	    prices.bringToFront();
    	    for(int i = 0; i < pListadoDatos.size(); i++){
    	    	TableRow tr =  new TableRow(this);
    	    	
    	    	String[] arreglo=pListadoDatos.get(i);
    	    	
 
    	    	
    	    	for(int x=0;x<arreglo.length;x++){
    	    		
        	    	List<String> listaCondicionesArreglo=new ArrayList<String>(
        					Arrays.asList(arreglo[x].split(";")));
        	    	
        	    	for(int w=0; w<listaCondicionesArreglo.size(); w++)
        	    	{
            	        TextView c1 = new TextView(this);
            	        c1.setText(listaCondicionesArreglo.get(w)+"       ");
            	        tr.addView(c1);
        	    	}       	        
    	    		   	    		
    	    	}  
    	
    	        prices.addView(tr);
    	        datos="";
    	    }
    }
    


}