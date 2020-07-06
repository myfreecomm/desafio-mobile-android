package br.com.desafio.util

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.desafio.R
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.QueueProcessingType

class Util {

    companion object{

        fun initImageLoader(context: Context){
            val config = ImageLoaderConfiguration.Builder(context)
            config.threadPriority(Thread.NORM_PRIORITY - 2)
            config.denyCacheImageMultipleSizesInMemory()
            config.diskCacheFileNameGenerator(Md5FileNameGenerator())
            config.diskCacheSize(50 * 1024 * 1024) // 50 MiB
            config.tasksProcessingOrder(QueueProcessingType.LIFO)
            config.writeDebugLogs()
            ImageLoader.getInstance().init(config.build())
        }

        fun getOptions(): DisplayImageOptions? {
            return DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_search_24px)
                .showImageForEmptyUri(R.drawable.ic_search_24px)
                .showImageOnFail(R.drawable.ic_search_24px)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build()
        }

        fun AppCompatActivity.addFragmentTo(containerId: Int, fragment: Fragment, tag: String = "") {
            supportFragmentManager.beginTransaction().add(containerId, fragment, tag).commit()
        }
    }
}