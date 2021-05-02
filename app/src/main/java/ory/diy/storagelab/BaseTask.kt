package ory.diy.storagelab

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


//@Entity(tableName = "base_task_table",
//        foreignKeys = [
//            ForeignKey(entity = task::class,
//                    parentColumns = ["id"],
//                    childColumns = ["id"],
//                    onDelete = ForeignKey.CASCADE),
//            ForeignKey(entity = redTask::class,
//                    parentColumns = ["id"],
//                    childColumns = ["id"],
//                    onDelete = ForeignKey.CASCADE)
//        ]
//
//        )
@Entity(tableName = "base_task_table")
open class BaseTask {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}