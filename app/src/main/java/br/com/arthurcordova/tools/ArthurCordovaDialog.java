package br.com.arthurcordova.tools;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.com.arthurcordova.R;
import br.com.arthurcordova.controller.GithubController;

/**
 * Created by acstapassoli on 16/10/17.
 */

public class ArthurCordovaDialog extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_alert, container, false);
//        getDialog().setTitle("Sample");
        return view;
    }

    View.OnClickListener doneAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Test", Toast.LENGTH_LONG).show();
        }
    };


}
