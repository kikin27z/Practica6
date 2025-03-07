package mx.edu.itson.practica6

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detalle_pelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)

        val iv_pelicula_image = findViewById<ImageView>(R.id.iv_pelicula_imagen)
        val tv_nombre_pelicula = findViewById<TextView>(R.id.tv_nombre_pelicula)
        val tv_pelicula_desc = findViewById<TextView>(R.id.tv_pelicula_desc)
        val buyTickets = findViewById<Button>(R.id.buyTickets)
        val seatsLeft = findViewById<TextView>(R.id.seatLeft)

        val bundle = intent.extras
        var ns = 0
        var id = -1
        var title = ""

        if(bundle != null){
            ns = bundle.getInt("numberSeats")
            title = bundle.getString("titulo")!!
            iv_pelicula_image.setImageResource(bundle.getInt("header"))
            tv_nombre_pelicula.setText(bundle.getString("titulo"))
            tv_pelicula_desc.setText(bundle.getString("sinopsis"))
            seatsLeft.setText("$ns seats available")
            id = bundle.getInt("pos")
        }

        if(ns == 0){
            buyTickets.isEnabled = false
        }else{
            buyTickets.isEnabled = true
            buyTickets.setOnClickListener {
                val intent : Intent = Intent(this, SeatSelection::class.java)

                intent.putExtra("id", id)
                intent.putExtra("name", title)

                this.startActivity(intent)
            }
        }

    }
}