package ory.diy.storagelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import androidx.activity.*



class MainActivity : AppCompatActivity() {

    private val taskViewModel: TaskViewModel by viewModels  {
        TaskViewModelFactory((application as TasksApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val placeholdertask = task(0, "placeholder", "placeholder")
//        tasksList.add(placeholdertask)

        val adapter = TaskAdapter(this)
        recyclerView.adapter = adapter


        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
//        taskViewModel.allTasks.observe(this, Observer { tasks ->
//            // Update the cached copy of the words in the adapter.
//            tasks?.let{adapter.setTasks(it)}
//        })

        taskViewModel.allTasks.observe(this, Observer { tasks ->
            // Update the cached copy of the words in the adapter.
            tasks?.let{adapter.submitList(it)}
        })


        button.setOnClickListener{
            val task = task("test task2", "this is a test task text")
            taskViewModel.insert(task)
        }

        button2.setOnClickListener{
            val taskRed = redTask("test task", "this is a test task text", 5, true)
            taskViewModel.insert(taskRed)
        }
    }
}