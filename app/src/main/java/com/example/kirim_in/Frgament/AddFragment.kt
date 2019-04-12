package com.example.kirim_in.Frgament

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kirim_in.Register
import kotlinx.android.synthetic.main.activity_login.*
import android.R
import com.example.kirim_in.FullCostType
import com.example.kirim_in.OtherAsktype
import com.example.kirim_in.PeopleWantedType
import kotlinx.android.synthetic.main.fragment_add.*


class AddFragment : Fragment()  {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.kirim_in.R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_full_cost_type.setOnClickListener {
            val intent = Intent(context,FullCostType::class.java)
            startActivity(intent)
        }
        btn_other_ask_type.setOnClickListener {
            val intent = Intent(context,OtherAsktype::class.java)
            startActivity(intent)
        }
        btn_people_wanted_type.setOnClickListener {
            val intent = Intent(context,PeopleWantedType::class.java)
            startActivity(intent)
        }
    }

}// Required empty public constructor