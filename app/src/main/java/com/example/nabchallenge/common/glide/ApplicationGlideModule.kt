package com.example.nabchallenge.common.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule


/***
 *   Using AppGlideModule for easy customize or config Glide (disk cached size/ location strategy, integrated more libraries or register custom model loader etc...)
 *   It also provide singleton pattern for using Glide in App
 *   For more configuration: https://bumptech.github.io/glide/doc/configuration.html#avoid-appglidemodule-in-libraries
 */
@GlideModule
class ApplicationGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
    }

}