package com.example.appcontroller;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CodeQR extends Fragment {


    TextView result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.codeqrfragment, container, false);
                IntentIntegrator intentIntegrator = IntentIntegrator.forSupportFragment(CodeQR.this);
                intentIntegrator.setPrompt("For flash use volume up key");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();



        return view;
    }


   public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult.getContents() != null) {

            SharedPreferences.Editor myEdit = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.AlertDialogStyle);
            builder.setTitle("Resultat Code QR           ");
            int i = Integer.parseInt(intentResult.getContents(),16);
            myEdit.putString("codeqr", intentResult.getContents());
            myEdit.apply();
            builder.setMessage(String.valueOf(i));

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    dialogInterface.dismiss();
                    startActivity(new Intent(getContext(), navigationbar.class));
                }
            });
            builder.show();

        }
        else{
            Toast.makeText(getContext(),"Vous n'avez pas scanner aucun code QR",Toast.LENGTH_SHORT).show();
        }





    }
}



        

      

    

