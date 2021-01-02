package site.thewhale.volunteer.other;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import site.thewhale.volunteer.adapters.FragAdapter;
import site.thewhale.volunteer.KtFunctionsKt;
import site.thewhale.volunteer.LoginActivity;
import site.thewhale.volunteer.R;

public class ShareCodes {
    static FragAdapter adapterViewPager;

    public static com.mikepenz.materialdrawer.Drawer createDrawer(Activity activity) {
        ViewPager vpPager = (ViewPager) activity.findViewById(R.id.vb);
        adapterViewPager = new FragAdapter(((FragmentActivity)activity).getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String name;
        String email;
        if (user != null) {
            name = user.getDisplayName();
            email = user.getEmail();
        } else {
            name = "N/A";
            email = "N/A";
        }

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName(name).withEmail(email).withIcon(R.drawable.profile)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();


        //if you want to update the items at a later time it is recommended to keep it in a variable
        SecondaryDrawerItem item1 = new SecondaryDrawerItem().withIdentifier(1).withTextColor(Color.BLACK).withIconColor(Color.BLACK).withSelectedTextColor(Color.BLACK).withSelectedIconColor(Color.BLACK).withName("الرئيسية").withIcon(FontAwesome.Icon.faw_home);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withSelectedTextColor(Color.BLACK).withSelectedIconColor(Color.DKGRAY).withName("إعدادات الحساب").withIcon(FontAwesome.Icon.faw_cogs);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withSelectedTextColor(Color.DKGRAY).withSelectedIconColor(Color.DKGRAY).withName("تسجيل الخروج").withIcon(FontAwesome.Icon.faw_sign_out_alt);
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(101).withTextColor(Color.BLACK).withIconColor(Color.BLACK).withSelectedTextColor(Color.BLACK).withSelectedIconColor(Color.BLACK).withName("الأفرقة المفضلة").withIcon(FontAwesome.Icon.faw_star);
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withIdentifier(102).withSelectedTextColor(Color.DKGRAY).withSelectedIconColor(Color.DKGRAY).withName("مشاركاتي").withIcon(FontAwesome.Icon.faw_list);
        SecondaryDrawerItem item6 = new SecondaryDrawerItem().withIdentifier(103).withSelectedTextColor(Color.GRAY).withSelectedIconColor(Color.DKGRAY).withName("حملاتي").withIcon(FontAwesome.Icon.faw_list);

        //create the drawer and remember the `Drawer` result object
        com.mikepenz.materialdrawer.Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(false)

//                .withDrawerGravity(Gravity.END)
//                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item4,
                        item5,
                        item6,
                        new DividerDrawerItem(),
                        item2,
                        new DividerDrawerItem(),
                        item3
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 3) {
                            logOut(activity);
                        } else if (drawerItem.getIdentifier() == 1) {
                            vpPager.setCurrentItem(0);
                        }
                        else if (drawerItem.getIdentifier() == 2) {
                            vpPager.setCurrentItem(2);
                        } else if (drawerItem.getIdentifier() == 101) {
                            vpPager.setCurrentItem(1);
                        }
                        return false;
                    }
                })
                .build();
        TextView title = activity.findViewById(R.id.pageTitle);
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {
                if (position == 0){
                    result.setSelection(1);
                    title.setText("الرئيسية");
                }
                else if (position == 2){
                    result.setSelection(2);
                    title.setText("إعدادات الحساب");
                }
                else if (position == 1){
                    result.setSelection(101);
                    title.setText("الأفرقة المفضلة");
                }
            }
        });

        return result;
    }

    private static void logOut(Activity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("تأكيد تسجيل الخروج");
        builder.setMessage("هل أنت متأكد من عملية تسجيل الخروج؟");
        builder.setCancelable(false).setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(activity, LoginActivity.class);
                activity.startActivity(i);
                activity.finish();
                KtFunctionsKt.motionI(activity, "وداعاً", "نراك قريباً");
            }
        });
        builder.setNegativeButton("لا :)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(activity, "إستمتع بتصفحك", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
