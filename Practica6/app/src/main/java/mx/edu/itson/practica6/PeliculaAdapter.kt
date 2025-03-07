package mx.edu.itson.practica6

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class PeliculaAdapter: BaseAdapter {
    var peliculas = ArrayList<Pelicula>()
    var context: Context? = null

    constructor(context: Context, peliculas: ArrayList<Pelicula>) : super() {
        this.peliculas = peliculas
        this.context = context
    }

    override fun getCount(): Int {
        return peliculas.size
    }

    override fun getItem(position: Int): Any {
        return peliculas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        var pelicula = peliculas[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista = inflator.inflate(R.layout.pelicula, null)
        var image: ImageView = vista.findViewById(R.id.image_movie_cell)
        var title: TextView = vista.findViewById(R.id.movie_title_cell)


        image.setImageResource(pelicula.image)
        title.setText(pelicula.titulo)

        image.setOnClickListener{
            var seatsAvailable: Int = 20 - pelicula.seats.size
            Log.d("SEATS", "$seatsAvailable")

            val intento = Intent(context, detalle_pelicula::class.java)
            intento.putExtra("titulo", pelicula.titulo)
            intento.putExtra("imagen", pelicula.image)
            intento.putExtra("header", pelicula.header)
            intento.putExtra("sinopsis", pelicula.sinopsis)
            intento.putExtra("pos", position)
            intento.putExtra("numberSeats", seatsAvailable)
            context!!.startActivity(intento)
        }
            return vista
    }

}