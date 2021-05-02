package ory.diy.storagelab

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class TaskRepository(private val taskDao: TaskDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allTasks: Flow<List<BaseTask>> = taskDao.getAllTasks()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(task: BaseTask) {
        if(task is task){
            taskDao.insertBase(task)
            taskDao.insertRegularTask(task)
        }
        if(task is redTask){
            taskDao.insertBase(task)
            taskDao.insertRedTask(task)
        }

    }
}