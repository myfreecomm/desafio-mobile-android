package testemongeral.com.br.testemongeral.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class ChangeFragment {

    public static Fragment changeFragment(int id, Class<? extends Fragment> fragmentClass, FragmentManager fragmentManager, boolean backstack, Bundle bundle) {
        Fragment naTela = fragmentManager.findFragmentByTag(fragmentClass.getName());
        if(naTela != null) {
            return naTela;
        }

        try {
            Fragment newFragment = fragmentClass.newInstance();

            if (bundle != null) {
                newFragment.setArguments(bundle);
            }

            fragmentManager.beginTransaction().addToBackStack(newFragment.getClass().getName()).replace(id, newFragment).commit();
            return newFragment;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
