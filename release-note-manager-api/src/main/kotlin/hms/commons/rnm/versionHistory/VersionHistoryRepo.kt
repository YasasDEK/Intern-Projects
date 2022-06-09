package hms.commons.rnm.versionHistory

import hms.commons.rnm.*
import hms.commons.rnm.db.schema.Tables.*
import hms.commons.rnm.db.schema.tables.ModuleDeployments
import hms.commons.rnm.db.schema.tables.ReleaseNote
import org.jooq.DSLContext
import org.springframework.stereotype.Component

interface VersionHistoryRepo {
    fun getVersionHistoryForReleaseNoteEditList(moduleId: Int, versionNo: String): List<VersionHistoryInReleaseNote>
    fun getVersionHistoryList(moduleId: Int): List<VersionHistory>;
    fun getDeploymentLocationList(moduleId: Int, versionName: String): String
    fun getVersionHistoryInReleaseNoteList(moduleId: Int): List<VersionHistoryInReleaseNote>;
    fun getVersionHistoryDataList(moduleId: Int): List<VersionHistoryData>
    fun getVersionHistoryOtherDataList(tagId: Int): List<VersionHistoryOtherData>
    fun getDeployedLocation(moduleId: Int, versionNo: String, customerId: Int): List<DeployedLocation>

}

@Component
class VersionHistoryRepoImpl(val jooqContext: DSLContext) : VersionHistoryRepo {

    override fun getVersionHistoryForReleaseNoteEditList(moduleId: Int, versionNo: String): List<VersionHistoryInReleaseNote> {
        val results = jooqContext
                .select(MODULE_TAG.VERSION_NO)
                .from(MODULE_TAG)
                .where(MODULE_TAG.MODULE_ID.eq(moduleId))
                .and(MODULE_TAG.VERSION_NO.notEqual(versionNo))
                .fetch()


        return results?.let { r ->
            r.toList().map { rm -> VersionHistoryInReleaseNote(rm[ReleaseNote.RELEASE_NOTE.VERSION_NO]) }
        } ?: emptyList()
    }

    override fun getVersionHistoryList(moduleId: Int): List<VersionHistory> {
        val results = jooqContext
                .select(ReleaseNote.RELEASE_NOTE.MODULE_ID, ReleaseNote.RELEASE_NOTE.VERSION_NO, ReleaseNote.RELEASE_NOTE.RELEASE_DATE)
                .from(ReleaseNote.RELEASE_NOTE)
                .where(ReleaseNote.RELEASE_NOTE.MODULE_ID.eq(moduleId))
                .orderBy(ReleaseNote.RELEASE_NOTE.CREATED_DATE.desc())
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> VersionHistory(rm[ReleaseNote.RELEASE_NOTE.MODULE_ID], rm[ReleaseNote.RELEASE_NOTE.VERSION_NO], rm[ReleaseNote.RELEASE_NOTE.RELEASE_DATE]) }
        } ?: emptyList()
    }

    override fun getDeploymentLocationList(moduleId: Int, verionName: String): String {

        val results = jooqContext
                .select(ModuleDeployments.MODULE_DEPLOYMENTS.DEPLOYMENT_LOCATION,CUSTOMER.NAME)
                .from(ModuleDeployments.MODULE_DEPLOYMENTS)
                .join(CUSTOMER)
                .on(ModuleDeployments.MODULE_DEPLOYMENTS.CUSTOMER_ID.eq(CUSTOMER.ID))
                .where(ModuleDeployments.MODULE_DEPLOYMENTS.MODULE_ID.eq(moduleId))
                .and(ModuleDeployments.MODULE_DEPLOYMENTS.VERSION_NO.eq((verionName)))
                .orderBy(ModuleDeployments.MODULE_DEPLOYMENTS.CREATED_DATE.desc())
                .fetch()

        return if(results.isNotEmpty) {
            results?.let { r ->
                r.toList().map { rm -> rm[CUSTOMER.NAME] + " - " +  rm[ModuleDeployments.MODULE_DEPLOYMENTS.DEPLOYMENT_LOCATION]}
            }[0] ?: "not deployed yet"
        } else {
            "not deployed yet"
        }
    }

    override fun getVersionHistoryInReleaseNoteList(moduleId: Int): List<VersionHistoryInReleaseNote> {
        val results = jooqContext
                .select(ReleaseNote.RELEASE_NOTE.VERSION_NO)
                .from(ReleaseNote.RELEASE_NOTE)
                .where(ReleaseNote.RELEASE_NOTE.MODULE_ID.eq(moduleId))
                .fetch()


        return results?.let { r ->
            r.toList().map { rm -> VersionHistoryInReleaseNote(rm[ReleaseNote.RELEASE_NOTE.VERSION_NO])}
        } ?: emptyList()
    }

    override fun getVersionHistoryDataList(moduleId: Int): List<VersionHistoryData> {
        val allVersions = jooqContext
                .select(MODULE_TAG.ID, MODULE_TAG.VERSION_NO, MODULE_TAG.BRANCH_NAME)
                .from(MODULE_TAG)
                .where(MODULE_TAG.MODULE_ID.eq(moduleId))
                .orderBy(MODULE_TAG.CREATED_DATE.desc())
                .fetch()

        return allVersions?.let { r ->
            r.toList().map { rm -> VersionHistoryData(rm[MODULE_TAG.ID], rm[MODULE_TAG.VERSION_NO], rm[MODULE_TAG.BRANCH_NAME]) }
        } ?: emptyList()

    }

    override fun getVersionHistoryOtherDataList(tagId: Int): List<VersionHistoryOtherData> {
        val otherData = jooqContext
                .select(CUSTOMER.ID,CUSTOMER.NAME, DEV_RELEASE_NOTE.RELEASE_DATE)
                .from(DEV_RELEASE_NOTE)
                .join(CUSTOMER)
                .on(DEV_RELEASE_NOTE.CUSTOMER_ID.eq(CUSTOMER.ID))
                .where(DEV_RELEASE_NOTE.TAG_ID.eq(tagId))
                .groupBy(CUSTOMER.ID)
                .orderBy(DEV_RELEASE_NOTE.CREATED_DATE.desc())
                .fetch()

        return otherData?.let { r ->
            r.toList().map { rm -> VersionHistoryOtherData(rm[CUSTOMER.ID],rm[CUSTOMER.NAME], rm[DEV_RELEASE_NOTE.RELEASE_DATE]) }
        } ?: emptyList()

    }

    override fun getDeployedLocation(moduleId: Int, versionNo: String, customerId: Int): List<DeployedLocation> {
        val results = jooqContext
                .select(ModuleDeployments.MODULE_DEPLOYMENTS.DEPLOYMENT_LOCATION)
                .from(ModuleDeployments.MODULE_DEPLOYMENTS)
                .where(ModuleDeployments.MODULE_DEPLOYMENTS.MODULE_ID.eq(moduleId))
                .and(ModuleDeployments.MODULE_DEPLOYMENTS.VERSION_NO.eq(versionNo))
                .and(ModuleDeployments.MODULE_DEPLOYMENTS.CUSTOMER_ID.eq(customerId))
                .orderBy(ModuleDeployments.MODULE_DEPLOYMENTS.CREATED_DATE.desc())
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> DeployedLocation(rm[ModuleDeployments.MODULE_DEPLOYMENTS.DEPLOYMENT_LOCATION]) }
        } ?: emptyList()
    }

}