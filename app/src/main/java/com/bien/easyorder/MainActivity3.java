package com.bien.easyorder;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity3 extends Activity {

    //adaugarea ingredientelor
    private List<ItemModel> ingredList() {
        List<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel(R.drawable.laptuc, "Lapte"));
        items.add(new ItemModel(R.drawable.bana1, "Banane"));
        items.add(new ItemModel(R.drawable.cioco, "Ciocolată"));
        items.add(new ItemModel(R.drawable.biscu, "Biscuiți"));
        items.add(new ItemModel(R.drawable.nuci1, "Nuci"));
        items.add(new ItemModel(R.drawable.ou1, "Ou"));
        items.add(new ItemModel(R.drawable.prafo, "Praf de copt"));
        items.add(new ItemModel(R.drawable.zaharel, "Zahăr"));
        items.add(new ItemModel(R.drawable.faina, "Făină"));
        items.add(new ItemModel(R.drawable.cacaoo, "Cacao"));
        items.add(new ItemModel(R.drawable.unti1, "Unt"));
        items.add(new ItemModel(R.drawable.frisca, "Frișcă"));
        items.add(new ItemModel(R.drawable.apa, "Apă"));
        items.add(new ItemModel(R.drawable.maasc, "Mascarpone"));
        items.add(new ItemModel(R.drawable.smantana, "Smântână"));
        items.add(new ItemModel(R.drawable.scort, "Scorțișoară"));
        items.add(new ItemModel(R.drawable.lam1, "Lămâie"));
        items.add(new ItemModel(R.drawable.ov1, "Ovăz"));
        items.add(new ItemModel(R.drawable.mer1, "Mere"));
        items.add(new ItemModel(R.drawable.cafea, "Cafea"));
        items.add(new ItemModel(R.drawable.iaurt, "Iaurt"));
        items.add(new ItemModel(R.drawable.mieree, "Miere"));

        return items;
    }

    List<String> lista = new ArrayList<String>();
    boolean ok = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final LinearLayout gallery = findViewById(R.id.gallery2);
        LayoutInflater inflater = LayoutInflater.from(this);

        final List<ItemModel> items = ingredList();

        for (int i = 0; i < items.size(); i++) {

            final View view = inflater.inflate(R.layout.item_v2, gallery, false);

            final ImageView img = view.findViewById(R.id.imageView3);
            img.setImageResource(items.get(i).getImage());

            TextView tw = view.findViewById(R.id.textView4);
            final String name = items.get(i).getName();
            tw.setText(name);
            final boolean[] ok = {false};
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ok[0] == false) {
                        lista.add(name);    //adaugare ingredient in lista de favorite
                        img.setColorFilter(ContextCompat.getColor(
                                MainActivity3.this,
                                R.color.DimGray),
                                PorterDuff.Mode.SCREEN);
                        ok[0] = true;
                    }
                    else
                    {
                        removeElement(lista, name); //eliminare ingredient din lista de favorite
                        img.clearColorFilter();
                        ok[0] = false;
                    }
                }
            });

            gallery.addView(view);
        }
    }

    //eliminarea unui ingredient din lista cu favorite
    public void removeElement(List<String> lista, String name){
        for (int i = 0; i < lista.size(); i++)
            if (lista.get(i).equals(name))
                lista.remove(i);
    }

    //butonul de "inapoi" este apasat
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Sigur dorești să părăsești pagina?");
        builder.setCancelable(false);

        builder.setPositiveButton(
                "Da",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        goBack();
                    }
                });

        builder.setNegativeButton(
                "Nu",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();

    }

    private void goBack(){
        Intent intent = new Intent(MainActivity3.this, MainActivity.class);
        Toast.makeText(MainActivity3.this, "Selectarea ingredientelor a eșuat...\n Încearcă din nou!", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }


    //adaugarea retetelor
    private ArrayList<List<String>> retete(){
        ArrayList<List<String>> retete = new ArrayList<List<String>>();

        retete.add(Arrays.asList("Unt","Lapte","Făină","Zahăr","Cacao","Ou","Frișcă","Ciocolată","Praf de copt"));  //1
        retete.add(Arrays.asList("Cacao","Biscuiți","Ciocolată","Praf de copt"));                                   //2
        retete.add(Arrays.asList("Banane","Făină","Zahăr","Ou","Lapte","Praf de copt"));                            //3
        retete.add(Arrays.asList("Unt","Biscuiți","Mascarpone","Cacao","Ou","Ciocolată","Zahăr"));                  //4
        retete.add(Arrays.asList("Unt","Făină","Zahăr","Ou","Mere","Praf de copt"));                                //5
        retete.add(Arrays.asList("Unt","Făină","Zahăr","Ou","Smântână"));                                           //6
        retete.add(Arrays.asList("Lapte","Zahăr","Ou","Frișcă","Ciocolată"));                                       //7
        retete.add(Arrays.asList("Lapte","Unt","Banane","Ou","Frișcă"));                                            //8
        retete.add(Arrays.asList("Apă","Zahăr","Cafea"));                                                           //9
        retete.add(Arrays.asList("Ciocolată","Frișcă"));                                                            //11
        retete.add(Arrays.asList("Biscuiți","Unt","Zahăr","Mascarpone"));                                           //12
        retete.add(Arrays.asList("Smântână","Ciocolată","Biscuiți","Frișcă","Unt"));                                //14
        retete.add(Arrays.asList("Ou","Lapte","Smântână","Zahăr"));                                                 //15
        retete.add(Arrays.asList("Cafea","Lapte","Miere"));                                                         //16
        retete.add(Arrays.asList("Ou","Unt","Zahăr","Făină","Ciocolată","Praf de copt"));                           //17
        retete.add(Arrays.asList("Iaurt","Făină"));                                                                 //18
        retete.add(Arrays.asList("Ou","Unt","Zahăr","Făină","Praf de copt"));                                       //19
        retete.add(Arrays.asList("Ou","Lapte","Făină","Lămâie","Apă"));                                             //20
        retete.add(Arrays.asList("Ou","Lapte"));                                                                    //21
        retete.add(Arrays.asList("Ou","Zahăr","Apă"));                                                              //22
        retete.add(Arrays.asList("Ou","Ciocolată","Biscuiți","Frișcă"));                                            //23
        retete.add(Arrays.asList("Ciocolată","Frișcă"));                                                            //24
        retete.add(Arrays.asList("Ciocolată","Ou","Praf de copt"));                                                 //25
        retete.add(Arrays.asList("Ciocolată","Banane","Ovăz"));                                                     //26
        retete.add(Arrays.asList("Ciocolată","Biscuiți","Mascarpone","Iaurt","Zahăr"));                             //27
        retete.add(Arrays.asList("Banane","Biscuiți","Iaurt","Frișcă"));                                            //28

        return retete;
    }

    private boolean verifReteta(List<String> lista, List<String> reteta){

        int k = 0;
        String cuv = "";

        for (int i = 0; i < lista.size(); i++)
        {
            cuv = lista.get(i);
            for (int j = 0; j < reteta.size(); j++)
                if (cuv.contains(reteta.get(j)))
                    k++;
        }

        if ((k == lista.size() && k <= reteta.size()) || k == reteta.size()){
            return true;
        }
        return false;
    }

    private ArrayList<String> showResult(List<String> lista, ArrayList<List<String>> retete) {

        ArrayList<String> rezultat = new ArrayList<String>();
        for (int i = 0 ; i < retete.size() ; i++)
            if (verifReteta(lista, retete.get(i)))
            {
                String cuv = "D";
                cuv += String.valueOf(i + 1);
                rezultat.add(cuv);
            }
        return rezultat;
    }

    public void onBtnClicked(View v)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        Bundle bundle = new Bundle();
        ArrayList<String> msg = new ArrayList<String>();

        if (lista.size() > 0)
            msg = showResult(lista, retete());

        bundle.putString("nume","MainActivity3");
        bundle.putStringArrayList("value", msg);
        intent.putExtras(bundle);

        startActivity(intent);
    }

}