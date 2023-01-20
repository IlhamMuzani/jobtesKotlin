package com.ilham.jobteskotlin.ui.fragment.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.ilham.jobteskotlin.R
import com.ilham.jobteskotlin.data.model.Constant
import com.ilham.jobteskotlin.data.model.DataUser
import com.ilham.jobteskotlin.ui.admin.detail_member.Detail_memberActivity
import kotlinx.android.synthetic.main.adapter_member.view.*
import kotlin.collections.ArrayList


class MemberAdapter (val context: Context, var dataUser:ArrayList<DataUser>,
                     val clickListener: (DataUser, Int, String) -> Unit ):
        RecyclerView.Adapter<MemberAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_member, parent, false)
    )

    override fun getItemCount() = dataUser.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataUser[position])

        holder.view.crv_member.setOnClickListener {
            Constant.USER_ID = dataUser[position].id!!.toLong()
            context.startActivity(Intent(context, Detail_memberActivity::class.java ))
        }

        holder.view.txvOptionss.setOnClickListener {
            val popupMenu = PopupMenu(context, holder.view.txvOptionss)
            popupMenu.inflate(R.menu.menu_options)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_delete -> {
                        Constant.USER_ID = dataUser[position].id!!.toLong()
                        clickListener(dataUser[position], position, "Delete")
                    }
                }
                true
            }

            popupMenu.show()
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(dataUser: DataUser) {
            view.txvNamaMember.text = dataUser.nama

            if (dataUser.gender == "L"){
                view.id_lakilaki.visibility = View.VISIBLE
                view.id_perempuan.visibility = View.GONE
            }else{
                view.id_lakilaki.visibility = View.GONE
                view.id_perempuan.visibility = View.VISIBLE
            }
        }
    }

    fun setData(newDataUser: List<DataUser>) {
        dataUser.clear()
        dataUser.addAll(newDataUser)
        notifyDataSetChanged()
    }

    fun removeuser(position: Int) {
        dataUser.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataUser.size)
    }

}