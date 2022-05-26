package com.yashagozwan.inacure.ui.main.profile

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import com.yashagozwan.inacure.databinding.ItemMenuProfileBinding
import com.yashagozwan.inacure.model.ItemMenu
import com.yashagozwan.inacure.ui.about.AboutActivity
import com.yashagozwan.inacure.utils.Constants

class ItemMenuAdapter(private val listItemMenu: List<ItemMenu>) :
    RecyclerView.Adapter<ItemMenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ItemMenuProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItemMenu[position])
    }

    override fun getItemCount(): Int = listItemMenu.size

    inner class ViewHolder(val viewBinding: ItemMenuProfileBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(itemMenu: ItemMenu) {
            viewBinding.ivIcon.setImageResource(itemMenu.icon)
            viewBinding.tvTitle.text = itemMenu.title
            viewBinding.cvItemMenuProfile.setOnClickListener {
                when (itemMenu.activityName) {
                    Constants.aboutActivity -> {
                        val intent = Intent(it.context, AboutActivity::class.java)
                        it.context.startActivity(intent)
                    }
                }
            }
        }
    }
}