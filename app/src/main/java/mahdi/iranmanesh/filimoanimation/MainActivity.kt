package mahdi.iranmanesh.filimoanimation

import android.graphics.Color
import android.graphics.drawable.RotateDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.GravityInt
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.core.view.setPadding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainView = LinearLayout(this)
        mainView.orientation = LinearLayout.VERTICAL
        mainView.setBackgroundColor(Color.DKGRAY)

        setContentView(mainView)

        AndroidUtilities.density = resources.displayMetrics.density.toInt();

        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT)
        lp.gravity = Gravity.CENTER
        lp.setMargins(100)

        val customView = CustomViewAnimation(this)
        customView.image =  ResourcesCompat.getDrawable(resources , R.drawable.filimo_logo , null)!!
        customView.text = "Filimo"
        customView.layoutParams = lp
        mainView.addView(customView)

        val button = Button(this)
        button.setText("Start")
        button.setPadding(16)
        button.gravity = Gravity.CENTER
        val buttonLp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT)
        buttonLp.gravity = Gravity.CENTER
        mainView.addView(button , buttonLp)

        button.setOnClickListener {
            customView.startAnimation()
        }

    }
}