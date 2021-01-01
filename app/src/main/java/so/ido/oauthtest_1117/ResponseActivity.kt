package so.ido.oauthtest_1117

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class ResponseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_response)

    }

    override fun onResume() {

        val CALLBACK_URL = "bentley://bentleyhost"
        val uri = intent.data
        if(uri != null && uri.toString().startsWith(CALLBACK_URL))
        {
            val access_token = uri.getQueryParameter("access_token")
            Toast.makeText(this@ResponseActivity, "access token : $access_token", Toast.LENGTH_SHORT).show()
        }
        super.onResume()
    }
}