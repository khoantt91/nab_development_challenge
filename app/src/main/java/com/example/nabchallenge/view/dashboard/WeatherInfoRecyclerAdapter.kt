package com.example.nabchallenge.view.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nabchallenge.R
import com.example.nabchallenge.common.adapter.BaseRecyclerAdapter
import com.example.nabchallenge.databinding.ItemWeatherInfoBinding
import com.example.nabchallenge.model.WeatherInfo
import com.example.nabchallenge.utils.formatDateString
import com.example.nabchallenge.utils.onDebounceClick
import com.example.nabchallenge.utils.toArrayList

class WeatherInfoRecyclerAdapter(private val listener: WeatherInfoRecyclerAdapterListener? = null) : BaseRecyclerAdapter<WeatherInfo, ItemWeatherInfoBinding>() {

    override fun onCreateView(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemWeatherInfoBinding> {
        val itemBinding = ItemWeatherInfoBinding.inflate(LayoutInflater.from(context), parent, false)
        val viewHolder = BaseViewHolder(itemBinding)

        /* Register Event Handler */
        viewHolder.binding.root.onDebounceClick {
            val position = viewHolder.adapterPosition
            if (position == RecyclerView.NO_POSITION) return@onDebounceClick
            listener?.onWeatherClick(dataSet[position], position)
        }

        return viewHolder
    }

    override fun onBindView(item: WeatherInfo, position: Int, viewHolder: BaseViewHolder<ItemWeatherInfoBinding>) {
        viewHolder.binding.tvDate.text = context.getString(R.string.dashboard_weather_date_with_data, item.createdTime?.let { (it * 1000).formatDateString() })
        viewHolder.binding.tvTemperature.text = context.getString(R.string.dashboard_weather_average_temperature_with_data, item.getAverageTemp()?.toString())
        viewHolder.binding.tvPressure.text = context.getString(R.string.dashboard_weather_pressure_with_data, item.pressure?.toString())
        viewHolder.binding.tvHumidity.text = context.getString(R.string.dashboard_weather_humidity_with_data, item.humidity?.toString())
        viewHolder.binding.tvDescription.text = context.getString(R.string.dashboard_weather_description_with_data, item.description?.toString())
    }

    fun notifyChangedDiffUtil(newList: List<WeatherInfo>) {
        val oldList = dataSet
        dataSet = newList.toArrayList()
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldList[oldItemPosition]
                val newItem = newList[newItemPosition]
                return oldItem == newItem
            }

            override fun getOldListSize(): Int = oldList.size
            override fun getNewListSize(): Int = newList.size
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldList[oldItemPosition]
                val newItem = newList[newItemPosition]
                return oldItem.compareForDashboardAdapter(newItem)
            }
        })
        diff.dispatchUpdatesTo(this)
    }

    interface WeatherInfoRecyclerAdapterListener {
        fun onWeatherClick(item: WeatherInfo, position: Int)
    }

}