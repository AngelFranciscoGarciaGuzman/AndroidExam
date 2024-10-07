import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenmovil.databinding.ActivityMainBinding
import com.example.examenmovil.framework.viewmodel.CharacterViewModel
import com.example.examenmovil.framework.adapters.CharacterAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var characterAdapter: CharacterAdapter
    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        characterAdapter = CharacterAdapter(emptyList())
        binding.recyclerView.adapter = characterAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Agregar el OnScrollListener
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1) {
                    // Hemos llegado al final, cargar más personajes
                    characterViewModel.fetchCharacters()
                }
            }
        })

        // Observar cambios en la lista de personajes
        characterViewModel.characters.observe(this, { characterList ->
            characterList?.let {
                characterAdapter.updateCharacters(it) // Actualiza el adaptador con la nueva lista
            }
        })

        // Cargar los primeros personajes
        characterViewModel.fetchCharacters()
    }
}
