package android.bignerdranch.alertdialogex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun alertButtonOnClick(view: View) {
        val builder  = AlertDialog.Builder(this);
        // set title for our alert
        builder.setTitle(R.string.dialogTitle)

        // now set message for it
        builder.setMessage(R.string.dialogMessage)

        // performing positive action
        builder.setPositiveButton("It's Okay"){dialog, which ->
            Toast.makeText(applicationContext,"clicked yes", Toast.LENGTH_LONG).show()
        }

        // cancel action
        builder.setNeutralButton("Cancel it"){dialog, which ->
            Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
        }

        // negative action
        builder.setNegativeButton("It's not Okay") {dialog, which ->
            Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()

        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}