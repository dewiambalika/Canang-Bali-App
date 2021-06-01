package com.arisurya.jetpackpro.canangbali.ui.information.shop.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.databinding.ActivityDetailShopBinding
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory
import com.arisurya.jetpackpro.canangbali.vo.Status
import com.bumptech.glide.Glide

class DetailShopActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val EXTRA_SHOP ="extra_shop"
    }

    private lateinit var  viewModel: DetailShopViewModel
    private lateinit var detailShopBinding: ActivityDetailShopBinding
    private lateinit var telp : String
    private var menu : Menu?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailShopBinding = ActivityDetailShopBinding.inflate(layoutInflater)
        setContentView(detailShopBinding.root)
        supportActionBar?.title = "Detail Toko"

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailShopViewModel::class.java]
        val extras = intent.extras

        if(extras!=null){
            val shopId = extras.getString(EXTRA_SHOP)
            showProgressBar(true)
            if(shopId!=null){
                viewModel.setSelectedShop(shopId)
                viewModel.detailShop.observe(this, {shop->
                    if (shop!=null){
                        when(shop.status){
                            Status.LOADING -> showProgressBar(true)
                            Status.SUCCESS -> {
                                showProgressBar(false)
                                populateShop(shop.data)
                                telp = shop.data?.tlp ?: ""
                            }
                            Status.ERROR ->{
                                showProgressBar(false)
                                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }

                })

            }
        }
    }

    private fun populateShop(shop: ShopEntity?) {
        detailShopBinding.apply {
            tvShopName.text = shop?.name
            tvShopLoc.text = shop?.location
            tvShopTlp.text = shop?.tlp
            tvProductDesc.text = shop?.product
            tvDesc.text = shop?.desc
            Glide.with(this@DetailShopActivity)
                .load(shop?.imgPath)
                .into(imgShop)

            btnTlp.setOnClickListener(this@DetailShopActivity)
        }
    }

    override fun onClick(v: View?) {
       when(v){
           detailShopBinding.btnTlp ->{
               callShop()
           }
       }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_shop, menu)
        this.menu = menu

        viewModel.detailShop.observe(this, {detailShop->
            if(detailShop!=null){
                when(detailShop.status){
                    Status.SUCCESS->{
                        if(detailShop.data!=null){
                            val state = detailShop.data.bookmarked
                            setBookmarkState(state)
                        }
                    }
                }
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.nav_bookmark){
            if(viewModel.detailShop.value?.data?.bookmarked == true) showToastRemoveBookmark()
            else showToastAddBookmark()

            viewModel.setBookmarkShop()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun callShop() {
        val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+telp))
        startActivity(dialPhoneIntent)
    }

    private fun showProgressBar(state : Boolean){
        if(state){
            detailShopBinding.progressBar.visibility = View.VISIBLE
        }else{
            detailShopBinding.progressBar.visibility = View.GONE
        }
    }

    private fun setBookmarkState(state : Boolean){
        if(menu==null)return
        val menuItem = menu?.findItem(R.id.nav_bookmark)
        if(state) menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark)
        else menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_outline)
    }

    private fun showToastAddBookmark(){
        val toastView = layoutInflater.inflate(
            R.layout.toast_success, findViewById(R.id.toast_add)
        )
        with(Toast(applicationContext)){
            duration = Toast.LENGTH_SHORT
            view = toastView
            show()
        }
    }

    private fun showToastRemoveBookmark(){
        val toastView = layoutInflater.inflate(
            R.layout.toast_remove, findViewById(R.id.toast_remove)
        )
        with(Toast(applicationContext)){
            duration = Toast.LENGTH_SHORT
            view = toastView
            show()
        }
    }
}