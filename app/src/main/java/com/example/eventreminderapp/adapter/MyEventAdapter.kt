package com.example.eventreminderapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventreminderapp.R
import com.example.eventreminderapp.activity.MainActivity
import com.example.eventreminderapp.model.modelFaceBook.Data

class MyEventAdapter(private val modelList: List<Data>?, private val context: MainActivity) :
    RecyclerView.Adapter<MyEventAdapter.EventHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, i: Int): EventHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_event_item_row, parent, false)
        return EventHolder(view)
    }


    override fun onBindViewHolder(holder: EventHolder, i: Int) {

        holder.descriptionsTV.text= modelList?.get(i)!!.description
        holder.timeTV.text= modelList[i].updated_time

        //        loadImage(context,modelList.get(i).getImage().get(0),holder.image_row_news);


//        Log.i("SADASDSAD", "onBindViewHolder: " + modelList!![i].getStatus())
//        if (modelList[i].getStatus() === 2) {
//            holder.EditMyService.visibility = View.VISIBLE
//
//        }
//        if (!modelList[i].getImage().isEmpty()) {
//            GlideApp.with(context).load(modelList[i].getImage().get(0))
//                .placeholder(getCircularProgressDrawable(context)).into(holder.itemImg)
//        }
//        holder.itemAddProduct.setOnClickListener { v ->
//            val intent = Intent(context, Details_MainActivity::class.java)
//            intent.putExtra(DETAILS_ACTIVITY, Add_Product_Service_Fragment)
//            intent.putExtra(ITEM_ID, String.valueOf(modelList[i].getId()))
//            context.startActivity(intent)
//
//        }
//
//
//        holder.itemTitleTv.setText(modelList[i].getTitle())
//        holder.EditMyService.setOnClickListener { v ->
//            val intent = Intent(context, Details_MainActivity::class.java)
//            intent.putExtra(DETAILS_ACTIVITY, Edit_Service_Fragment)
//            intent.putExtra(ITEM_ID, String.valueOf(modelList[i].getId()))
//            context.startActivity(intent)
//        }
//
//
//        //
//        //
//        //        holder.row_news_click.setOnClickListener(v ->
//        //        {
//        ////            context
//        ////            .replaceFragmentId
//        ////            (new ()
//        ////            , modelList.get(i).getId());
//        //
//        //
//        //        });
//        holder.itemProducts.setOnClickListener { v ->
//            context.loadFragmentStackPutSerializable(
//                MyProductsService_Fragment(),
//                modelList[i].getProduct()
//            )
//        }
//
//        holder.itemDelete.setOnClickListener { v ->
//            val builder = AlertDialog.Builder(context)
//            builder.setCancelable(false)
//            builder.setTitle(context.getString(R.string.worm))
//            builder.setIcon(R.drawable.ic_warning_black_24dp)
//            builder.setMessage(context.getString(R.string.AreyousuretoDeletethisPlace))
//            builder.setPositiveButton(context.getString(R.string.ok)) { dialog, which ->
//
//
//                DELETE_Service(modelList[i].getId(), holder.adapterPosition)
//            }
//                .setNegativeButton(context.getString(R.string.cancel), { dialog, which ->
//                    Toast.makeText(
//                        context, context.getString(R.string.nochangehasbeendone),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                })
//            builder.create().show()
//
//        }

    }


    override fun getItemCount(): Int {
        return modelList?.size ?: 0

    }


    inner class EventHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        internal val descriptionsTV: TextView = itemView.findViewById(R.id.descriptionsTV)
        internal val timeTV: TextView = itemView.findViewById(R.id.timeTV)


    }
}
