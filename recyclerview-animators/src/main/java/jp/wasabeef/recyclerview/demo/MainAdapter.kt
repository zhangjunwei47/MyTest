package jp.wasabeef.recyclerview.demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.wasabeef.recyclerview.R

/**
 * Created by Wasabeef on 2015/01/03.
 */
class MainAdapter(private val context: Context, private val dataSet: MutableList<String>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val v = LayoutInflater.from(context).inflate(R.layout.recycler_layout_list_item, parent, false)
    return ViewHolder(v)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    Glide.with(context).load(R.drawable.timg).into(holder.image)
    holder.text.text = dataSet[position]
  }

  override fun getItemCount(): Int {
    return dataSet.size
  }

  fun remove(position: Int) {
    dataSet.removeAt(position)
    notifyItemRemoved(position)
  }

  fun add(text: String, position: Int) {
    dataSet.add(position, text)
    notifyItemInserted(position)
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var image: ImageView = itemView.findViewById<View>(R.id.image) as ImageView
    var text: TextView = itemView.findViewById<View>(R.id.text) as TextView

  }
}
