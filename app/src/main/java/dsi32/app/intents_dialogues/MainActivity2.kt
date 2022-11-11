package dsi32.app.intents_dialogues

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import java.util.*
import javax.security.auth.Subject

class MainActivity2 : AppCompatActivity() {

    lateinit var affnomprenom : TextView
    lateinit var affemail : TextView
    lateinit var affclasse : TextView
    lateinit var affgenre : TextView
    lateinit var affdate : TextView
    lateinit var btnsahre : Button
    lateinit var btnwebview : Button
    lateinit var wbview : WebView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        affnomprenom=findViewById(R.id.affnomprenom)
        affemail=findViewById(R.id.affemail)
        affclasse=findViewById(R.id.affclasse)
        affgenre=findViewById(R.id.affgenre)
        affdate=findViewById(R.id.affdate)
        btnsahre=findViewById(R.id.btnshare)
        wbview=findViewById(R.id.wb_webview)
        btnwebview=findViewById(R.id.webview)
        val nomprenom=intent.getStringExtra("nomprenom")
        val email=intent.getStringExtra("email")
        val classe=intent.getStringExtra("classe")
        val reslist=intent.getStringExtra("reslist")
        val date=intent.getStringExtra("date")
        val url="https://www.google.com/"

        affnomprenom.text="Nom et prenom :"+nomprenom
        affemail.text="Email :"+email
        affclasse.text="Classe : "+classe
        affdate.text="Date : "+date
        affgenre.text="Genre : "+reslist


        btnsahre.setOnClickListener{
            sendEmail(email,nomprenom,classe,reslist,date)
        }

        btnwebview.setOnClickListener{
            wbview.webViewClient= WebViewClient()
            wbview.apply {
                loadUrl("https://www.google.com/")
                settings.javaScriptEnabled=true
                settings.safeBrowsingEnabled=true
                //if (wbview.canGoBack()) wbview.goBack() else println("gg")
            }
        }

    }


    private fun sendEmail(email: String?, nomprenom: String?, classe: String?,reslist:String?,date:String?) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        //intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Fiche Etudiant")
        intent.putExtra(Intent.EXTRA_TEXT,"email : "+email+"\n"+"nom & prenom : "+nomprenom+"\n"+"classe : "+classe+"\n"+"Genre : "+reslist+"\n"+"Date : "+date)
        startActivity(Intent.createChooser(intent,"choose Email..."))
    }

}