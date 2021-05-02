package ory.diy.storagelab

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "red_task_table",
    foreignKeys = [
        ForeignKey(entity = BaseTask::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            deferred = true,
            onDelete = CASCADE
        )]
)
class redTask (
        var taskName: String = "task name",
        var taskText: String = "task texttttttt",
        var taskCounter: Int = 0,
        var taskStatus: Boolean = false
) : BaseTask() {

    fun increaseCount(){
        taskCounter++
        if(taskCounter > 9)
            taskCounter = 0
    }


}