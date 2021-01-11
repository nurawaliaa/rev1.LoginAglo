package fiks.agloser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import androidx.compose.ui.unit.Constraints
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseListAdapter
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_cek_stock.*
import kotlinx.android.synthetic.main.item_stock.view.*
import java.text.FieldPosition

class cekStock : AppCompatActivity() {
    private lateinit var itemList : ListView
    private lateinit var  ref : DatabaseReference
    private lateinit var stockList : MutableList<itemAdd>
    private lateinit var searchBar : SearchView
    private lateinit var FirebaseListAdapter: FirebaseListAdapter<itemAdd>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cek_stock)

        ref = FirebaseDatabase.getInstance().getReference("ItemName")
        stockList = mutableListOf()
        itemList = findViewById(R.id.lv_item)
        searchBar = findViewById(R.id.srcBar)



        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    stockList.clear()
                    for(h: DataSnapshot in snapshot.children ){
                        val ItemName= h.getValue(itemAdd::class.java)
                        if (ItemName != null) {
                            stockList.add(ItemName)
                        }
                    }
                    val adapter = itemAddAdapter (this@cekStock, R.layout.item_stock, stockList)
                    itemList.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        add_stck.setOnClickListener{
            val intent = Intent (this@cekStock, addData_Page::class.java)
            startActivity(intent)

        }




    }




}




