package hms.commons.rnm.releaseNote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import hms.commons.rnm.*
import hms.commons.rnm.db.schema.Tables.*
import hms.commons.rnm.db.schema.tables.ReleaseNote
import org.jooq.DSLContext
import org.jooq.JSON
import org.springframework.stereotype.Component
import java.time.LocalDate

interface ReleaseNoteRepo {
    fun addDevReleaseNote(data: AddDevReleaseNote): AddDevReleaseNote
    fun addToDraftDevReleaseNote(data: AddDevReleaseNote): AddDevReleaseNote
    fun getViewDevReleaseNoteList(tagId: Int, customerId: Int): List<ViewDevReleaseNote>
    fun editDevReleaseNote(data: EditDevReleaseNote): EditDevReleaseNote
    fun isDevReleaseNoteAvailable(tagId: Int, customerId: Int): Boolean
    fun addDAReleaseNote(data: AddDAReleaseNote): AddDAReleaseNote
    fun addToDraftDAReleaseNote(data: AddDAReleaseNote): AddDAReleaseNote
    fun isDAReleaseNoteAvailable(tagId: Int, customerId: Int): Boolean
    fun getViewDAReleaseNoteList(tagId: Int, customerId: Int): List<ViewDAReleaseNote>
    fun editDAReleaseNote(data: EditDAReleaseNote): EditDAReleaseNote

}

@Component
class ReleaseNoteRepoImpl(val jooqContext: DSLContext) : ReleaseNoteRepo {

    //add a release note
    override fun addDevReleaseNote(data: AddDevReleaseNote): AddDevReleaseNote {

        val GSON: Gson = GsonBuilder().create()
        val dependentModuleVersion = JSON.valueOf(GSON.toJson(data.dependentVersion))


        val newRecord = jooqContext.newRecord(DEV_RELEASE_NOTE)
                newRecord.tagId = data.tagId
                newRecord.customerId = data.customerId
                newRecord.type = data.type
                newRecord.releaseBy = data.releaseBy
                newRecord.releaseDate = data.releaseDate
                newRecord.applyOnTopOfVersion = data.applyOnTopOf
                newRecord.dependantVersion = dependentModuleVersion
                newRecord.buildInstructions = data.buildInstructions
                newRecord.featureSummary = data.featureSummary
                newRecord.fixedBugs = data.fixedBugs
                newRecord.knownIssues = data.knowIssues
                newRecord.deploymentDetails = data.pdInstructions
                newRecord.areasToBeTested = data.areasToBeTested
                newRecord.referenceDocs = data.docName
                newRecord.store()

                return data

        }

    override fun addToDraftDevReleaseNote(data: AddDevReleaseNote): AddDevReleaseNote {
        val GSON: Gson = GsonBuilder().create()
        val dependentModuleVersion = JSON.valueOf(GSON.toJson(data.dependentVersion))

                        val updateRecord = jooqContext
                        .update(DEV_RELEASE_NOTE)
                                .set(DEV_RELEASE_NOTE.RELEASE_BY,data.releaseBy)
                                .set(DEV_RELEASE_NOTE.RELEASE_DATE,data.releaseDate)
                                .set(DEV_RELEASE_NOTE.TYPE,data.type)
                                .set(DEV_RELEASE_NOTE.APPLY_ON_TOP_OF_VERSION,data.applyOnTopOf)
                                .set(DEV_RELEASE_NOTE.DEPENDANT_VERSION,dependentModuleVersion)
                                .set(DEV_RELEASE_NOTE.BUILD_INSTRUCTIONS,data.buildInstructions)
                                .set(DEV_RELEASE_NOTE.FEATURE_SUMMARY,data.featureSummary)
                                .set(DEV_RELEASE_NOTE.FIXED_BUGS,data.fixedBugs)
                                .set(DEV_RELEASE_NOTE.KNOWN_ISSUES,data.knowIssues)
                                .set(DEV_RELEASE_NOTE.DEPLOYMENT_DETAILS,data.pdInstructions)
                                .set(DEV_RELEASE_NOTE.AREAS_TO_BE_TESTED,data.areasToBeTested)
                                .set(DEV_RELEASE_NOTE.REFERENCE_DOCS,data.docName)
                                .where(DEV_RELEASE_NOTE.TAG_ID.eq(data.tagId))
                                .and(DEV_RELEASE_NOTE.CUSTOMER_ID.eq(data.customerId))
                                .execute()

        return data
    }

    override fun isDevReleaseNoteAvailable(tagId: Int, customerId: Int): Boolean {
        val isAvailable = jooqContext
                .select(DEV_RELEASE_NOTE.TAG_ID, DEV_RELEASE_NOTE.CUSTOMER_ID)
                .from((DEV_RELEASE_NOTE))
                .where(DEV_RELEASE_NOTE.TAG_ID.eq(tagId))
                .and(DEV_RELEASE_NOTE.CUSTOMER_ID.eq(customerId))
                .fetch()

        return isAvailable.isEmpty()
    }

    //view a release note
    override fun getViewDevReleaseNoteList(tagId: Int, customerId: Int): List<ViewDevReleaseNote> {

        var DEPENDENT_VESIONS_GSON_TYPE = object : TypeToken<List<DependentModuleVersion>>() {
        }.type

        val results = jooqContext
                .select(
                        CUSTOMER.NAME,
                        DEV_RELEASE_NOTE.RELEASE_DATE,
                        DEV_RELEASE_NOTE.RELEASE_BY,
                        DEV_RELEASE_NOTE.TYPE,
                        DEV_RELEASE_NOTE.APPLY_ON_TOP_OF_VERSION,
                        DEV_RELEASE_NOTE.DEPENDANT_VERSION,
                        DEV_RELEASE_NOTE.FEATURE_SUMMARY,
                        DEV_RELEASE_NOTE.BUILD_INSTRUCTIONS,
                        DEV_RELEASE_NOTE.FIXED_BUGS,
                        DEV_RELEASE_NOTE.KNOWN_ISSUES,
                        DEV_RELEASE_NOTE.DEPLOYMENT_DETAILS,
                        DEV_RELEASE_NOTE.AREAS_TO_BE_TESTED,
                        DEV_RELEASE_NOTE.REFERENCE_DOCS
                        )
                .from(DEV_RELEASE_NOTE)
                .join(CUSTOMER)
                .on(DEV_RELEASE_NOTE.CUSTOMER_ID.eq(CUSTOMER.ID))
                .where(DEV_RELEASE_NOTE.TAG_ID.eq(tagId))
                .and(DEV_RELEASE_NOTE.CUSTOMER_ID.eq(customerId))
                .fetch()

        return results?.let { r ->
            r.toList().map { rm ->
                val GSON: Gson = GsonBuilder().create()
                val dependentModuleVersions = rm[DEV_RELEASE_NOTE.DEPENDANT_VERSION]?.let { it -> GSON.fromJson<List<DependentModuleVersion>>(it.data(), DEPENDENT_VESIONS_GSON_TYPE) }
                        ?: emptyList()


                ViewDevReleaseNote(
                        rm[CUSTOMER.NAME],
                        dependentModuleVersions,
                        rm[DEV_RELEASE_NOTE.APPLY_ON_TOP_OF_VERSION],
                        rm[DEV_RELEASE_NOTE.TYPE],
                        rm[DEV_RELEASE_NOTE.REFERENCE_DOCS],
                        rm[DEV_RELEASE_NOTE.BUILD_INSTRUCTIONS],
                        rm[DEV_RELEASE_NOTE.DEPLOYMENT_DETAILS],
                        rm[DEV_RELEASE_NOTE.FEATURE_SUMMARY],
                        rm[DEV_RELEASE_NOTE.KNOWN_ISSUES],
                        rm[DEV_RELEASE_NOTE.FIXED_BUGS],
                        rm[DEV_RELEASE_NOTE.AREAS_TO_BE_TESTED],
                        rm[DEV_RELEASE_NOTE.RELEASE_BY],
                        rm[DEV_RELEASE_NOTE.RELEASE_DATE]

            ) }
        } ?: emptyList()
    }

    //edit a release note
    override fun editDevReleaseNote(data: EditDevReleaseNote): EditDevReleaseNote {
        val GSON: Gson = GsonBuilder().create()
        val dependentModuleVersion = JSON.valueOf(GSON.toJson(data.dependentVersion))

        val updateRecord = jooqContext
                .update(DEV_RELEASE_NOTE)
                .set(DEV_RELEASE_NOTE.TYPE,data.type)
                .set(DEV_RELEASE_NOTE.RELEASE_BY,data.releaseBy)
                .set(DEV_RELEASE_NOTE.RELEASE_DATE,data.releaseDate)
                .set(DEV_RELEASE_NOTE.APPLY_ON_TOP_OF_VERSION,data.applyOnTopOf)
                .set(DEV_RELEASE_NOTE.DEPENDANT_VERSION,dependentModuleVersion)
                .set(DEV_RELEASE_NOTE.BUILD_INSTRUCTIONS,data.buildInstructions)
                .set(DEV_RELEASE_NOTE.FEATURE_SUMMARY,data.featureSummary)
                .set(DEV_RELEASE_NOTE.FIXED_BUGS,data.fixedBugs)
                .set(DEV_RELEASE_NOTE.KNOWN_ISSUES,data.knowIssues)
                .set(DEV_RELEASE_NOTE.DEPLOYMENT_DETAILS,data.pdInstructions)
                .set(DEV_RELEASE_NOTE.AREAS_TO_BE_TESTED,data.areasToBeTested)
                .set(DEV_RELEASE_NOTE.REFERENCE_DOCS,data.docName)
                .where(DEV_RELEASE_NOTE.TAG_ID.eq(data.tagId))
                .and(DEV_RELEASE_NOTE.CUSTOMER_ID.eq(data.customerId))
                .execute()

        return data
    }

    override fun addDAReleaseNote(data: AddDAReleaseNote): AddDAReleaseNote {
        val newRecord = jooqContext.newRecord(DA_RELEASE_NOTE)
        newRecord.tagId = data.tagId
        newRecord.customerId = data.customerId
        newRecord.artifactLocation = data.artifactLocation
        newRecord.mdsum = data.md5sum
        newRecord.testedAreas = data.testedAreas
        newRecord.newFeatures = data.newFeatures
        newRecord.foundBugs = data.foundBugs
        newRecord.fixedBugs = data.fixedBugs
        newRecord.knowBugs = data.knownBugs
        newRecord.limitations = data.limitations
        newRecord.modulesReleased = data.modulesReleased
        newRecord.prerequisits = data.prerequisits
        newRecord.deployInstructions = data.deployInstructions
        newRecord.testCases = data.testCases
        newRecord.patchInstructions = data.patchInstructions
        newRecord.areasToBeTested = data.areasToBeTested
        newRecord.releasedBy = data.releasedBy
        newRecord.releasedDate = data.releasedDate
        newRecord.testedBy = data.testedBy
        newRecord.plannedStart = data.plannedStart
        newRecord.plannedEnd = data.plannedEnd
        newRecord.actualStart = data.actualStart
        newRecord.actualEnd = data.actualEnd
        newRecord.comment = data.comment
        newRecord.type = data.type
        newRecord.store()

        return data
    }

    override fun addToDraftDAReleaseNote(data: AddDAReleaseNote): AddDAReleaseNote {
        val updateRecord = jooqContext
                .update(DA_RELEASE_NOTE)
                .set(DA_RELEASE_NOTE.ARTIFACT_LOCATION,data.artifactLocation)
                .set(DA_RELEASE_NOTE.MDSUM,data.md5sum)
                .set(DA_RELEASE_NOTE.TESTED_AREAS,data.testedAreas)
                .set(DA_RELEASE_NOTE.NEW_FEATURES,data.newFeatures)
                .set(DA_RELEASE_NOTE.FOUND_BUGS,data.foundBugs)
                .set(DA_RELEASE_NOTE.FIXED_BUGS,data.fixedBugs)
                .set(DA_RELEASE_NOTE.KNOW_BUGS,data.knownBugs)
                .set(DA_RELEASE_NOTE.LIMITATIONS,data.limitations)
                .set(DA_RELEASE_NOTE.MODULES_RELEASED,data.modulesReleased)
                .set(DA_RELEASE_NOTE.PREREQUISITS,data.prerequisits)
                .set(DA_RELEASE_NOTE.DEPLOY_INSTRUCTIONS,data.deployInstructions)
                .set(DA_RELEASE_NOTE.TEST_CASES,data.testCases)
                .set(DA_RELEASE_NOTE.PATCH_INSTRUCTIONS,data.patchInstructions)
                .set(DA_RELEASE_NOTE.AREAS_TO_BE_TESTED,data.areasToBeTested)
                .set(DA_RELEASE_NOTE.RELEASED_BY,data.releasedBy)
                .set(DA_RELEASE_NOTE.RELEASED_DATE,data.releasedDate)
                .set(DA_RELEASE_NOTE.TESTED_BY,data.testedBy)
                .set(DA_RELEASE_NOTE.PLANNED_START,data.plannedStart)
                .set(DA_RELEASE_NOTE.PLANNED_END,data.plannedEnd)
                .set(DA_RELEASE_NOTE.ACTUAL_START,data.actualStart)
                .set(DA_RELEASE_NOTE.ACTUAL_END,data.actualEnd)
                .set(DA_RELEASE_NOTE.COMMENT,data.comment)
                .set(DA_RELEASE_NOTE.TYPE,data.type)
                .where(DA_RELEASE_NOTE.TAG_ID.eq(data.tagId))
                .and(DA_RELEASE_NOTE.CUSTOMER_ID.eq(data.customerId))
                .execute()

        return data;
    }

    override fun isDAReleaseNoteAvailable(tagId: Int, customerId: Int): Boolean {
        val isAvailable = jooqContext
                .select(DA_RELEASE_NOTE.TAG_ID, DA_RELEASE_NOTE.CUSTOMER_ID)
                .from(DA_RELEASE_NOTE)
                .where(DA_RELEASE_NOTE.TAG_ID.eq(tagId))
                .and(DA_RELEASE_NOTE.CUSTOMER_ID.eq(customerId))
                .fetch()

        return isAvailable.isEmpty()
    }

    override fun getViewDAReleaseNoteList(tagId: Int, customerId: Int): List<ViewDAReleaseNote> {
        val results = jooqContext
                .selectFrom(DA_RELEASE_NOTE)
                .where(DA_RELEASE_NOTE.TAG_ID.eq(tagId))
                .and(DA_RELEASE_NOTE.CUSTOMER_ID.eq(customerId))
                .fetch()

        return results?.let { r ->
            r.toList().map { rm ->
                ViewDAReleaseNote(
                        rm.tagId,
                        rm.customerId,
                        rm.artifactLocation,
                        rm.mdsum,
                        rm.testedAreas,
                        rm.newFeatures,
                        rm.foundBugs,
                        rm.fixedBugs,
                        rm.knowBugs,
                        rm.limitations,
                        rm.modulesReleased,
                        rm.prerequisits,
                        rm.deployInstructions,
                        rm.testCases,
                        rm.patchInstructions,
                        rm.areasToBeTested,
                        rm.releasedBy,
                        rm.releasedDate,
                        rm.testedBy,
                        rm.plannedStart,
                        rm.plannedEnd,
                        rm.actualStart,
                        rm.actualEnd,
                        rm.type,
                        rm.comment
                ) }
        } ?: emptyList()
    }

    override fun editDAReleaseNote(data: EditDAReleaseNote): EditDAReleaseNote {

        val updateRecord = jooqContext
                .update(DA_RELEASE_NOTE)
                .set(DA_RELEASE_NOTE.ARTIFACT_LOCATION,data.artifactLocation)
                .set(DA_RELEASE_NOTE.MDSUM,data.md5sum)
                .set(DA_RELEASE_NOTE.TESTED_AREAS,data.testedAreas)
                .set(DA_RELEASE_NOTE.NEW_FEATURES,data.newFeatures)
                .set(DA_RELEASE_NOTE.FOUND_BUGS,data.foundBugs)
                .set(DA_RELEASE_NOTE.FIXED_BUGS,data.fixedBugs)
                .set(DA_RELEASE_NOTE.KNOW_BUGS,data.knownBugs)
                .set(DA_RELEASE_NOTE.LIMITATIONS,data.limitations)
                .set(DA_RELEASE_NOTE.MODULES_RELEASED,data.modulesReleased)
                .set(DA_RELEASE_NOTE.PREREQUISITS,data.prerequisits)
                .set(DA_RELEASE_NOTE.DEPLOY_INSTRUCTIONS,data.deployInstructions)
                .set(DA_RELEASE_NOTE.TEST_CASES,data.testCases)
                .set(DA_RELEASE_NOTE.PATCH_INSTRUCTIONS,data.patchInstructions)
                .set(DA_RELEASE_NOTE.AREAS_TO_BE_TESTED,data.areasToBeTested)
//                .set(DA_RELEASE_NOTE.RELEASED_BY,data.releasedBy)
//                .set(DA_RELEASE_NOTE.RELEASED_DATE,data.releasedDate)
                .set(DA_RELEASE_NOTE.TESTED_BY,data.testedBy)
                .set(DA_RELEASE_NOTE.PLANNED_START,data.plannedStart)
                .set(DA_RELEASE_NOTE.PLANNED_END,data.plannedEnd)
                .set(DA_RELEASE_NOTE.ACTUAL_START,data.actualStart)
                .set(DA_RELEASE_NOTE.ACTUAL_END,data.actualEnd)
                .set(DA_RELEASE_NOTE.COMMENT,data.comment)
                .set(DA_RELEASE_NOTE.TYPE,data.type)
                .where(DA_RELEASE_NOTE.TAG_ID.eq(data.tagId))
                .and(DA_RELEASE_NOTE.CUSTOMER_ID.eq(data.customerId))
                .execute()

        return data;
    }


}
