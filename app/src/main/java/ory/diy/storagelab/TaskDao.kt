package ory.diy.storagelab

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf

//@Dao
//abstract class BaseTaskDao<ITask>{
//    @Insert
//    abstract fun insert(obj: ITask)
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    abstract fun insert(vararg obj: ITask)
//
//    @Update
//    abstract fun update(obj: ITask)
//
//    @Delete
//    abstract fun delete(obj: ITask)
//
//    @Query("DELETE FROM base_task_table")
//    abstract suspend fun deleteAllTasks()
//
////    SELECT
////    ids.id AS _ids,
////    names1.names AS _names
////    FROM
////    ids INNER JOIN names1 ON names1.ida = ids.id
////    UNION SELECT
////    ids.id AS _ids,
////    names2.names AS _names
////    FROM
////    ids INNER JOIN names2 ON names2.idb = ids.id
//
//
//    @Query("SELECT base_task_table.id AS id, ")
////            "SELECT * FROM task_table UNION SELECT * FROM red_task_table ORDER BY id ASC")
//    abstract fun getAllTasks(): Flow<List<ITask>>
//}


//@Dao
//interface TaskDao {
//    @Query("SELECT * FROM task_table ORDER BY id ASC")
//    fun getTasks(): Flow<List<task>>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(task: task)
//
//    @Query("DELETE FROM task_table")
//    suspend fun deleteAll()
//}

//@Dao
//abstract class TaskDao : BaseTaskDao<task>() {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    abstract override fun insert(obj: task)
////    @Query("SELECT * FROM task_table ORDER BY id ASC")
////    abstract fun getTasks(): Flow<List<task>>
//}
//
//@Dao
//abstract class RedTaskDao : BaseTaskDao<redTask>() {
////    @Query("SELECT * FROM task_table ORDER BY id ASC")
////    abstract fun getTasks(): Flow<List<task>>
//}

//@Dao
//interface TaskDao {
////    @Query("" +
////            "SELECT base_task_table.id AS id," +
////            " task_table.taskName AS taskName," +
////            " task_table.taskText AS taskText," +
////            " task_table.taskCounter AS taskCounter" +
////            "FROM base_task_table INNER JOIN task_table ON task_table.id = base_task_table.id")
//    @Query("SELECT * FROM task_table ORDER BY id ASC")
//    fun getTasks(): Flow<List<task>>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(task: task)
//
//    @Query("DELETE FROM task_table")
//    suspend fun deleteAll()
//
//
//    @Query("SELECT * FROM red_task_table ORDER BY id ASC")
//    fun getRedTasks(): Flow<List<redTask>>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertRed(RedTask: redTask)
//
//    @Query("DELETE FROM task_table")
//    suspend fun deleteAllRed()
//
//    @Query("SELECT base_task_table.id AS id, task_table.taskName AS taskName, task_table.taskText AS taskText, task_table.taskCounter AS taskCounter FROM base_task_table INNER JOIN task_table ON task_table.id = base_task_table.id UNION SELECT base_task_table.id AS id, red_task_table.taskName AS taskName, red_task_table.taskText AS taskText, red_task_table.taskCounter AS taskCounter, red_task_table.taskStatus AS taskStatus FROM base_task_table INNER JOIN red_task_table ON red_task_table.id = base_task_table.id")
//    fun getAllTasks(): Flow<List<ITask>>
//}


@Dao
interface TaskDao {


    @Query("SELECT * FROM task_table")
    fun getRegularTasks(): Flow<List<task>>

    @Query("SELECT * FROM red_task_table")
    fun getRedTasks(): Flow<List<redTask>>

    @Query("SELECT * FROM base_task_table")
    fun getTasks(): Flow<List<BaseTask>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRegularTask(task: task)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRedTask(task: redTask)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBase(task: BaseTask)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllRegularTasks()

    @Query("DELETE FROM red_task_table")
    suspend fun deleteAllRedTasks()

    @Query("DELETE FROM red_task_table")
    suspend fun deleteAllTasks()




    fun getAllTasks() : Flow<List<BaseTask>>{
        val flowA = getRegularTasks()
        val flowB = getRedTasks()

        return flowOf(flowA, flowB).flattenMerge()
    }

}



//@Dao
//interface RedTaskDao {
//    @Query("SELECT * FROM red_task_table ORDER BY id ASC")
//    fun getRedTasks(): Flow<List<redTask>>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertRed(RedTask: redTask)
//
//    @Query("DELETE FROM task_table")
//    suspend fun deleteAllRed()
//}


