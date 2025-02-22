package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import scisrc.mobiledev.ecommercelayout.R
import scisrc.mobiledev.ecommercelayout.adapter.ProductAdapter
import scisrc.mobiledev.ecommercelayout.adapter.CategoryAdapter
import scisrc.mobiledev.ecommercelayout.databinding.FragmentAllProductsBinding
import scisrc.mobiledev.ecommercelayout.model.Product

class AllProductsFragment : Fragment() {
    private var _binding: FragmentAllProductsBinding? = null
    private val binding get() = _binding!!

    private val allProducts = listOf(
        Product("เสื้อฮูดดี้", 590.0, "เสื้อฮูดสีดำใส่สบาย", R.drawable.shirt1, "Shirt"),
        Product("กางเกงผ้าสีม่วง", 349.0, "กางเกงผ้าสีม่วง", R.drawable.pant2, "Pant"),
        Product("รองเท้าเท่ๆ", 2890.0, "รองเท้าอาดิดูด", R.drawable.shoe1, "Shoe"),
        Product("เสื้อยืดดำ", 199.0, "เสือยืดดำ", R.drawable.shirt2, "Shoe"),
        Product("เสื้อยืดดำขาว", 199.0, "เสือยืดขาว", R.drawable.shirt3, "Shoe"),
        Product("กางเกงผ้าสีครีม", 349.0, "กางเกงผ้าสีครีม", R.drawable.pant1, "Shoe"),
        Product("กางเกงผ้าสีดำ", 590.0, "กางเกงผ้าสีดำ", R.drawable.pant3, "Shoe"),
        Product("รองเท้าคูลๆ", 3200.0, "รองเท้าไนก้า", R.drawable.shoe2, "Shoe")
    )

    private var filteredProducts = allProducts.toMutableList()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllProductsBinding.inflate(inflater, container, false)


        productAdapter = ProductAdapter(requireContext(), filteredProducts)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = productAdapter


        val categories = listOf("All") + allProducts.map { it.category }.distinct()
        val categoryAdapter = CategoryAdapter(categories) { selectedCategory ->
            filterProductsByCategory(selectedCategory)
        }
        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.adapter = categoryAdapter

        return binding.root
    }


    private fun filterProductsByCategory(category: String) {
        filteredProducts = if (category == "All") {
            allProducts.toMutableList()
        } else {
            allProducts.filter { it.category == category }.toMutableList()
        }
        productAdapter.updateList(filteredProducts)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
