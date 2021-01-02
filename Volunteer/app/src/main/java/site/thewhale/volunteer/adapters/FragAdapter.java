package site.thewhale.volunteer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import site.thewhale.volunteer.fragments.Favorite;
import site.thewhale.volunteer.fragments.Main;
import site.thewhale.volunteer.fragments.Profile;

public class FragAdapter extends FragmentPagerAdapter {


    public FragAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Main();
            case 1:
                return new Favorite();
            case 2:
                return new Profile();


        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
