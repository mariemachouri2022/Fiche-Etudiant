package dsi32.app.intents_dialogues

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*
import android.app.ProgressDialog
import android.graphics.Color
import android.os.Handler
import android.support.design.widget.Snackbar
import android.view.View
import java.text.ParsePosition


class MainActivity : AppCompatActivity() {
    lateinit var email : EditText
    lateinit var nomprenom : EditText
    lateinit var classe : EditText
    lateinit var btnvalide : Button
    lateinit var builder : AlertDialog.Builder
    lateinit var date : TextView
    lateinit var btndate : Button
    lateinit var list : Spinner
    lateinit var reslist : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressDialog= ProgressDialog(this)
        progressDialog.setMessage("Downloading....")
        progressDialog.setCancelable(false)
        progressDialog.show()
        Handler().postDelayed({progressDialog.dismiss()},3000)

        val spinner=findViewById<Spinner>(R.id.spinner)
        val arrayAdapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,genre)
        spinner.adapter=arrayAdapter
        nomprenom=findViewById(R.id.nomprenom)
        email=findViewById(R.id.email)
        classe=findViewById(R.id.classe)
        btnvalide=findViewById(R.id.button_validate)
        builder= AlertDialog.Builder(this)
        date=findViewById(R.id.date)
        btndate=findViewById(R.id.button_date)
        list=findViewById(R.id.spinner)
        reslist=findViewById(R.id.reslist)
        val genre= arrayOf("Homme","Femme")
        list.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,genre)

        list.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                reslist.text=genre.get(position)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                reslist.text="Please slelect"
            }
        }

        btndate.setOnClickListener{
            val mycalender=Calendar.getInstance()
            val datePicker =DatePickerDialog.OnDateSetListener{ view,year,month,dayOfMonth->
                mycalender.set(Calendar.YEAR,year)
                mycalender.set(Calendar.MONTH,month)
                mycalender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateLable(mycalender)
            }
            DatePickerDialog(this,datePicker,mycalender.get(Calendar.YEAR),mycalender.get(Calendar.MONTH),
                mycalender.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnvalide.setOnClickListener{
            val champnomprenom=nomprenom.text.toString().trim()
            val champemail=email.text.toString().trim()
            val champclasse=classe.text.toString().trim()
            val champlist=reslist.text.toString().trim()
            if(champnomprenom.isEmpty()){
                builder.setTitle("User name is Required !")
                    .setMessage("il faut remplire le champ nom et prenom")
                    .setCancelable(true)
                    .setPositiveButton("OK"){dialogIterface,it->dialogIterface.cancel()}
                    .show()
                nomprenom.error="User name is Required"
                return@setOnClickListener
            }else if(champemail.isEmpty()){
                builder.setTitle("Email is Required !")
                    .setMessage("il faut remplire le champ email")
                    .setCancelable(true)
                    .setPositiveButton("OK"){dialogIterface,it->dialogIterface.cancel()}
                    .show()
                email.error="Email is Required"
                return@setOnClickListener
            }else if(champclasse.isEmpty()){
                builder.setTitle("classe is Required !")
                    .setMessage("il faut remplire le champ classe")
                    .setCancelable(true)
                    .setPositiveButton("OK"){dialogIterface,it->dialogIterface.cancel()}
                    .show()
                classe.error="Classe is Required"
                return@setOnClickListener
            }else{
                builder.setTitle("Confirmation !")
                    .setMessage("Les donnes sont enregistreé")
                    .setCancelable(true)
                    .setPositiveButton("OK"){dialogIterface,it->
                        val Intent =Intent(this,MainActivity2::class.java)
                        startActivity(Intent
                            .putExtra("email",email.text.toString())
                            .putExtra("nomprenom",nomprenom.text.toString())
                            .putExtra("classe",classe.text.toString())
                            .putExtra("reslist",reslist.text.toString())
                            .putExtra("date",date.text.toString())
                        )
                    }
                    .show()
                Toast.makeText(this, "validation complete", Toast.LENGTH_SHORT).show()
                val snackbar=
                    Snackbar.make(it,"done!!!", Snackbar.LENGTH_LONG).setAction("action",null)
                snackbar.setActionTextColor(Color.WHITE)
                val snackbarview=snackbar.view
                snackbarview.setBackgroundColor(Color.BLACK)
                snackbar.show()
            }
        }

    }


    private fun updateLable(mycalender: Calendar) {
        val myFormat="dd-MM-yyyy"
        val sdf=SimpleDateFormat(myFormat,Locale.FRANCE)
        date.setText(sdf.format(mycalender.time))
    }




}


