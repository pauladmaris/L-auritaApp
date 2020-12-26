package com.bien.easyorder;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DisplayMessageActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    int ok = 0;
    List<String> lista = new ArrayList<String>();
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        CardStackView cardStackView = findViewById(R.id.card_stack_view);
        manager = new CardStackLayoutManager(this, new CardStackListener() {

            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                if (direction == Direction.Right){
                    String name = adapter.getItems().get(manager.getTopPosition()-1).getName();
                    lista.add(name);
                }
                if (direction == Direction.Top){
                    //Toast.makeText(DisplayMessageActivity.this, "Direction Top", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Left){
                    //mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.nay);
                    //mediaPlayer.start();
                }
                if (direction == Direction.Bottom){
                    //Toast.makeText(DisplayMessageActivity.this, "Direction Bottom", Toast.LENGTH_SHORT).show();
                }

                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5){
                    paginate();
                }

                ++ok;
                if (ok == adapter.getItemCount()) {//scrisul apare doar dupa ce s-au afisat toate ingredientele
                    showAnimation();
                }
            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", name: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", name: " + tv.getText());
            }

        });

        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new CardStackAdapter(addList());
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());
    }

    //butonul de "inapoi" este apasat
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new Builder(this);
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
        Intent intent = new Intent(DisplayMessageActivity.this, MainActivity.class);
        Toast.makeText(DisplayMessageActivity.this, "Selectarea ingredientelor a eșuat...\n Încearcă din nou!", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }


    private void paginate() {
        List<ItemModel> old = adapter.getItems();
        List<ItemModel> baru = new ArrayList<>(addList());
        CardStackCallback callback = new CardStackCallback(old, baru);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(baru);
        hasil.dispatchUpdatesTo(adapter);
    }

    //adaugarea ingredientelor
    private List<ItemModel> addList() {
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

    //animatia de la final
    private void showAnimation(){
        final TypeWriter tw = (TypeWriter) findViewById(R.id.tv);

        tw.setText("");
        tw.setCharacterDelay(150);
        tw.animateText("The end...");
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

    //return true daca se gaseste reteta, false altfel
    private boolean verifReteta(List<String> lista,List<String> reteta){

        //pentru orice desert
        int k = 0;
        boolean ok = false;
        String cuv = "";

        for (int i = 0; i < lista.size(); i++)
        {
            cuv = lista.get(i);
            ok = false;
            for (int j = 0; j < reteta.size(); j++)
                if (cuv.contains(reteta.get(j)))
                    ok = true;

            if (ok == true)
                ++k;
        }

        //pt cazul in care am intr-o reteta mai putine ingrediente (insa toate sunt preferate)

        int exista = 0;
        for (int i = 0; i < lista.size(); i++)
        {
            cuv = lista.get(i);
            for (int j = 0; j < reteta.size(); j++)
                if (cuv.contains(reteta.get(j)) == true)
                    exista++;
        }

        if ((k == lista.size() && k == reteta.size()) || exista == reteta.size()){
            return true;
        }
        return false;
    }

    //returneaza toate retetele rezultate
    private ArrayList<String> showResult(List<String> lista,ArrayList<List<String>> retete) {

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

    //butornl de rezultat
    public void onBtnClicked(View v)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        Bundle bundle = new Bundle();

        ArrayList<String> msg = showResult(lista, retete());

        bundle.putString("nume","DisplayMessageActivity");
        bundle.putStringArrayList("value", msg);
        intent.putExtras(bundle);

        startActivity(intent);
    }

}