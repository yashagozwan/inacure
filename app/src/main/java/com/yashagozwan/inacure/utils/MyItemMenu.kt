package com.yashagozwan.inacure.utils

import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.model.ItemMenu

fun myItemMenu(): List<ItemMenu> {
    val account = ItemMenu(R.drawable.ic_circle_profile, "Bind Account", "account")
    val about = ItemMenu(R.drawable.ic_about, "About", Constants.aboutActivity)
    return listOf(account, about)
}