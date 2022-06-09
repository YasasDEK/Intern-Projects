package hms.commons.rnm.tagData

import hms.commons.rnm.*
import hms.commons.rnm.db.schema.Tables.MODULE_TAG
import hms.commons.rnm.db.schema.tables.ReleaseNote
import org.jooq.DSLContext
import org.springframework.stereotype.Component

interface TagDataRepo {
    fun addTagData(data: AddTagData): AddTagData
    fun isVersionAvailable(module: Int, version: String): Boolean

}

@Component
class TagDataRepoImpl(val jooqContext: DSLContext) : TagDataRepo {

    //add tag data
    override fun addTagData(data: AddTagData): AddTagData {
        val newRecord = jooqContext.newRecord(MODULE_TAG)
        newRecord.moduleId = data.moduleId
        newRecord.versionNo = data.versionNo
        newRecord.branchName = data.branchName
        newRecord.store()

        return data

    }

    override fun isVersionAvailable(module: Int, version: String): Boolean {
        val isAvailable = jooqContext
                .select(MODULE_TAG.MODULE_ID, MODULE_TAG.VERSION_NO)
                .from((MODULE_TAG))
                .where(MODULE_TAG.MODULE_ID.eq(module))
                .and(MODULE_TAG.VERSION_NO.eq(version))
                .fetch()

        return isAvailable.isNotEmpty
    }
}
