package so.ido.oauthtest_1117

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment

class WebViewFragment : Fragment() {

    private var args: Bundle? = null
    private var urlToLoad: String = ""
    private lateinit var mWebView: WebView
    private lateinit var activity:MainActivity
    private var callbackurl:String = "bentley://bentleyhost"
    private lateinit var code:String


    companion object {
        const val URL_TO_LOAD = "urlToLoad"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        args = arguments
        if (args == null) args = Bundle()


        urlToLoad = args!!.getString(URL_TO_LOAD, "")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = getActivity() as MainActivity
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_web_view, container, false)

        mWebView = view.findViewById(R.id.webview) as WebView
        loadUrlOnWebView()
        return view

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadUrlOnWebView() {

        mWebView.webViewClient = WebViewClient()

        val mWebSettings = mWebView.settings
        mWebSettings.javaScriptEnabled = true
        mWebSettings.setSupportMultipleWindows(false)
        mWebSettings.javaScriptCanOpenWindowsAutomatically = false
        mWebSettings.loadWithOverviewMode = true
        mWebSettings.useWideViewPort = true
        mWebSettings.builtInZoomControls = false
        mWebSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mWebSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebSettings.domStorageEnabled = true



        mWebView.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event?.action != KeyEvent.ACTION_DOWN)
                    return true
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (mWebView.canGoBack()) {
                        mWebView.goBack()
                    } else {
                        requireActivity().onBackPressed()
                    }
                    return true
                }
                return false
            }
        })

        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
            ): Boolean {

                if(request?.url.toString().startsWith(callbackurl)) {

                    code = Utils.getParamValFromUrlString(request?.url.toString(), "code")

                    sendInformation()
                    return true
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        mWebView.loadUrl(urlToLoad)
        Log.d("TAG", "Test6 : $urlToLoad")
    }

    private fun sendInformation(){
        activity.changeFragment(3,code)
    }

}





