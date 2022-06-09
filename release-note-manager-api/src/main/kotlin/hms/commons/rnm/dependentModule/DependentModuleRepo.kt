package hms.commons.rnm.dependentModule

import hms.commons.rnm.DependentModule
import hms.commons.rnm.VersionHistoryInReleaseNote
import hms.commons.rnm.db.schema.Tables.MODULE_TAG
import hms.commons.rnm.db.schema.tables.Module.MODULE
import hms.commons.rnm.db.schema.tables.ModuleDependency.MODULE_DEPENDENCY
import hms.commons.rnm.db.schema.tables.ReleaseNote
import org.jooq.DSLContext
import org.springframework.stereotype.Component

interface DependentModuleRepo {
    fun getDependentModuleList(moduleId: Int): List<DependentModule>;
    fun getVersionHistoryInReleaseNoteList(moduleId: Int): List<String>;
}

@Component
class DependentModuleImpl(val jooqContext: DSLContext) : DependentModuleRepo {

    override fun getDependentModuleList(moduleId: Int): List<DependentModule> {
        val results = jooqContext
                .select(MODULE.ID,MODULE.NAME)
                .from(MODULE)
                .join(MODULE_DEPENDENCY)
                .on(MODULE.ID.eq(MODULE_DEPENDENCY.DEPENDANT_MODULE_ID))
                .where(MODULE_DEPENDENCY.MODULE_ID.eq(moduleId))
                .fetch()


        return results?.let { r ->
            r.toList().map { rm -> DependentModule(rm[MODULE.ID], rm[MODULE.NAME])}
        } ?: emptyList()
    }

    override fun getVersionHistoryInReleaseNoteList(moduleId: Int): List<String> {
        val results = jooqContext
                .select(MODULE_TAG.VERSION_NO)
                .from(MODULE_TAG)
                .where(MODULE_TAG.MODULE_ID.eq(moduleId))
                .fetch()


        return results?.let { r ->
            r.toList().map { rm -> rm[MODULE_TAG.VERSION_NO]}
        } ?: emptyList()
    }
}