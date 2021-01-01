package so.ido.oauthtest_1117

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_menu.*
import org.json.JSONObject


class MenuFragment : Fragment() {
    private var args: Bundle? = null
    private var getAccessTokenContent:String = ""

    companion object {
        const val AccessTokenContent = "AccessToken"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        args = arguments
        if (args == null) args = Bundle()

        getAccessTokenContent = args!!.getString(AccessTokenContent, "")

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (getAccessTokenContent != "") {

            val jObject = JSONObject(getAccessTokenContent)
            Log.d("TAG", "responseJson : $getAccessTokenContent")

            val accesstoken = jObject.getString("access_token")
            val tokentype = jObject.getString("token_type")
            val expiresin = jObject.getString("expires_in")
            val refreshtoken = jObject.getString("refresh_token")
            val scope = jObject.getString("scope")
            val userseqno = jObject.getString("user_seq_no")

            accesstoken_content.text = accesstoken
            tokentype_content.text = tokentype
            expires_content.text = expiresin
            refresh_token_content.text = refreshtoken
            scope_content.text = scope
            user_seq_no_content.text = userseqno

        }
    }
}

