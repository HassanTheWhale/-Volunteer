package site.thewhale.volunteer

import android.app.Activity
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import www.sanju.motiontoast.MotionToast

fun motionS(context: Activity, title: String, msg: String) {
    MotionToast.createToast(context,
            title,
            msg,
            MotionToast.TOAST_SUCCESS,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context,R.font.helvetica_regular))
}

fun motionE(context: Activity, title: String, msg: String) {
    MotionToast.createToast(context,
            title,
            msg,
            MotionToast.TOAST_ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context,R.font.helvetica_regular))
}

fun motionI(context: Activity, title: String, msg: String) {
    MotionToast.createToast(context,
            title,
            msg,
            MotionToast.TOAST_INFO,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context,R.font.helvetica_regular))
}
