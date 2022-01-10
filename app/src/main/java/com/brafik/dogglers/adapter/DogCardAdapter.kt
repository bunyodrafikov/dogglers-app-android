/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.brafik.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brafik.dogglers.R
import com.brafik.dogglers.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // TODO: Initialize the data using the List found in data/DataSource
    private val dataset = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val dogImage = view?.findViewById<ImageView>(R.id.dogsImage)
        val dogName = view?.findViewById<TextView>(R.id.dogsName)
        val dogAge = view?.findViewById<TextView>(R.id.dogsAge)
        val dogHobbies = view?.findViewById<TextView>(R.id.dogsHobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val orientationId = when (layout) {
            1 -> R.layout.vertical_horizontal_list_item
            2 -> R.layout.vertical_horizontal_list_item
            else -> R.layout.grid_list_item
        }

        val adapterLayout = LayoutInflater
            .from(parent.context)
            .inflate(orientationId, parent, false)
        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val dog = dataset[position]
        val resources = context?.resources

        holder.dogImage?.setImageResource(dog.imageResourceId)

        holder.dogName?.text = dog.name
        holder.dogAge?.text = resources?.getString(R.string.dog_age, dog.age)
        holder.dogHobbies?.text = resources?.getString(R.string.dog_hobbies, dog.hobbies)
    }
}
