package ory.diy.storagelab

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.red_task_item.view.*
import kotlinx.android.synthetic.main.task_item.view.*
import kotlinx.android.synthetic.main.task_item.view.tv_task_text

class TaskAdapter(
    private val context: Context
//) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
): ListAdapter<BaseTask, RecyclerView.ViewHolder>(TasksComparator()) {

    companion object {
        private const val TYPE_TASK = 0
        private const val TYPE_RED_TASK = 1
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTaskText = itemView.tv_task_text
    }

    class RedTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRedTaskText = itemView.tv_red_task_text
        val rbStatus = itemView.rb_status
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        TYPE_TASK -> TaskViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.task_item, parent, false))
        TYPE_RED_TASK -> RedTaskViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.red_task_item, parent, false))
        else -> throw IllegalArgumentException()
    }

    override fun getItemViewType(position: Int): Int =
        when(getItem(position)){
            is task -> TYPE_TASK
            is redTask -> TYPE_RED_TASK
            else -> throw IllegalArgumentException()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when(holder.itemViewType) {
            TYPE_TASK -> onBindTask(holder, getItem(position) as task)
            TYPE_RED_TASK -> onBindRedTask(holder, getItem(position) as redTask)
            else -> throw IllegalArgumentException()
        }

    private fun onBindTask(holder: RecyclerView.ViewHolder, task: task){
        val myTask = holder as TaskViewHolder
        myTask.tvTaskText.text = task.taskText
    }
    private fun onBindRedTask(holder: RecyclerView.ViewHolder, task: redTask){
        val myTask = holder as RedTaskViewHolder
        myTask.tvRedTaskText.text = task.taskText
        if(task.taskStatus == true){
            myTask.rbStatus.isChecked = true
        }
        else{
            myTask.rbStatus.isChecked = false
        }
    }

//    override fun getItemViewType(position: Int): Int =
//            when (getItem(position)) {
//                is task -> TYPE_TASK
//                is redTask -> TYPE_RED_TASK
//                else -> throw IllegalArgumentException()
//            }


    class TasksComparator : DiffUtil.ItemCallback<BaseTask>() {
        override fun areItemsTheSame(oldItem: BaseTask, newItem: BaseTask): Boolean {
            return oldItem === newItem
        }


        override fun areContentsTheSame(oldItem: BaseTask, newItem: BaseTask): Boolean {
            return oldItem.id == newItem.id
        }
    }

}