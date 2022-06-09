package hms.commons.rnm.deployment

import hms.commons.rnm.*
import hms.commons.rnm.db.schema.Tables.*
import hms.commons.rnm.db.schema.tables.ModuleDeployments
import hms.commons.rnm.db.schema.tables.CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT
import org.jooq.DSLContext
import org.springframework.stereotype.Component

interface DeploymentRepo {
    fun getDeploymentLocationList(customerId: Int): List<DeploymentLocation>
    fun getDeploymentTimeLineList(customerId: Int, location: String, moduleId: Int): List<DeploymentTimeLine>
    fun getVersionForDeployToLocation (ModuleId: Int): List<VersionForDeployToLocation>
    fun getCustomerForDeployToLocation(ModuleId: Int): List<CustomerForDeployToLocation>
    fun getLocationForDeployToLocation(): List<LocationForDeployToLocation>
    fun deployToLocation(data: DeployToLocation): DeployToLocation
    fun getDeploymentHistoryForReleaseNote(ModuleId: Int, VersionNo: String): List<DeploymentHistoryForReleaseNote>
    fun viewModuleDeployment(ModuleId: Int, VersionNo: String, DeploymentLocation: String, CustomerId: Int): List<ViewModuleDeployment>
    fun getModuleDeploymentVersion(Id: Int): String
    fun isModuleDeploymentAvailable(customerId: Int, moduleId: Int, versionNo: String, deployment: String): Boolean
}

@Component
class DeploymentRepoImpl(val jooqContext: DSLContext) : DeploymentRepo {
    //get deployment location list
    override fun getDeploymentLocationList(customerId: Int): List<DeploymentLocation> {
        val results = jooqContext
                .select(DEPLOYMENT_LOCATION.LOCATION_CODE)
                .from(DEPLOYMENT_LOCATION)
                .where(DEPLOYMENT_LOCATION.CUSTOMER_ID.eq(customerId))
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> DeploymentLocation(rm[DEPLOYMENT_LOCATION.LOCATION_CODE]) }
        } ?: emptyList()
    }

    //get deployment timeline
    override fun getDeploymentTimeLineList(customerId: Int, location: String, moduleId: Int): List<DeploymentTimeLine> {
        val results = jooqContext
                .select(ModuleDeployments.MODULE_DEPLOYMENTS.ID, ModuleDeployments.MODULE_DEPLOYMENTS.VERSION_NO, ModuleDeployments.MODULE_DEPLOYMENTS.APPLIED_DATE)
                .from(ModuleDeployments.MODULE_DEPLOYMENTS)
                .where((ModuleDeployments.MODULE_DEPLOYMENTS.CUSTOMER_ID.eq(customerId)))
                .and(ModuleDeployments.MODULE_DEPLOYMENTS.DEPLOYMENT_LOCATION.eq(location))
                .and(ModuleDeployments.MODULE_DEPLOYMENTS.MODULE_ID.eq(moduleId))
                .orderBy(MODULE_DEPLOYMENTS.APPLIED_DATE.desc())
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> DeploymentTimeLine(rm[ModuleDeployments.MODULE_DEPLOYMENTS.ID]
                    ,rm[ModuleDeployments.MODULE_DEPLOYMENTS.VERSION_NO]
                    ,rm[ModuleDeployments.MODULE_DEPLOYMENTS.APPLIED_DATE]) }
        } ?: emptyList()
    }

    // get data for deployment to location page
    override fun getVersionForDeployToLocation(ModuleId: Int): List<VersionForDeployToLocation> {
        val results = jooqContext
                .selectFrom(ModuleDeployments.MODULE_DEPLOYMENTS)
                .where(ModuleDeployments.MODULE_DEPLOYMENTS.MODULE_ID.eq(ModuleId))
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> VersionForDeployToLocation(
                    rm[ModuleDeployments.MODULE_DEPLOYMENTS.ID],
                    rm[ModuleDeployments.MODULE_DEPLOYMENTS.CUSTOMER_ID],
                    rm[ModuleDeployments.MODULE_DEPLOYMENTS.DEPLOYMENT_LOCATION],
                    rm[ModuleDeployments.MODULE_DEPLOYMENTS.MODULE_ID],
                    rm[ModuleDeployments.MODULE_DEPLOYMENTS.VERSION_NO]
//                    rm[ModuleDeployments.MODULE_DEPLOYMENTS.APPLIED_DATE],
//                    rm[ModuleDeployments.MODULE_DEPLOYMENTS.APPLIED_BY],
//                    rm[ModuleDeployments.MODULE_DEPLOYMENTS.COMMENTS]
//                    rm[ModuleDeployments.MODULE_DEPLOYMENTS.PREVIOUS_MOD_DEPLOYMENT_ID]
                    ) }
        } ?: emptyList()
    }

    //get Customer For Deploy To Location
    override fun getCustomerForDeployToLocation(ModuleId: Int): List<CustomerForDeployToLocation> {
        val results = jooqContext
                .select(CUSTOMER_MODULE_DEPLOYMENT.CUSTOMER_ID, CUSTOMER.NAME)
                .from(CUSTOMER)
                .join(CUSTOMER_MODULE_DEPLOYMENT)
                .on(CUSTOMER.ID.eq(CUSTOMER_MODULE_DEPLOYMENT.CUSTOMER_ID))
                .where(CUSTOMER_MODULE_DEPLOYMENT.MODULE_ID.eq(ModuleId))
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> CustomerForDeployToLocation(
                    rm[CUSTOMER_MODULE_DEPLOYMENT.CUSTOMER_ID],
                    rm[CUSTOMER.NAME])
            }
        } ?: emptyList()
    }

    //get Location For Deployment Location
    override fun getLocationForDeployToLocation(): List<LocationForDeployToLocation> {
        val results = jooqContext
                .select(DEPLOYMENT_LOCATION.CUSTOMER_ID, DEPLOYMENT_LOCATION.LOCATION_CODE)
                .from(DEPLOYMENT_LOCATION)
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> LocationForDeployToLocation(
                    rm[DEPLOYMENT_LOCATION.CUSTOMER_ID],
                    rm[DEPLOYMENT_LOCATION.LOCATION_CODE])
            }
        } ?: emptyList()
    }

    override fun deployToLocation(data: DeployToLocation): DeployToLocation {
        val result = jooqContext
                .select(MODULE_DEPLOYMENTS.ID)
                .from(MODULE_DEPLOYMENTS)
                .where(MODULE_DEPLOYMENTS.MODULE_ID.eq(data.moduleId))
                .and(MODULE_DEPLOYMENTS.VERSION_NO.eq(data.previousVersion))
                .and((MODULE_DEPLOYMENTS.CUSTOMER_ID.eq(data.customerId)))
                .fetch()

        var previousVersionId = 0
        result.let { r -> r.map { rm -> previousVersionId = rm[MODULE_DEPLOYMENTS.ID]  }}

        val newRecord = jooqContext.newRecord(MODULE_DEPLOYMENTS)
        newRecord.customerId = data.customerId
        newRecord.deploymentLocation = data.deploymentLocation
        newRecord.moduleId = data.moduleId
        newRecord.versionNo = data.versionNo
        newRecord.appliedDate = data.appliedDate
        newRecord.appliedBy = data.appliedBy
        newRecord.comments = data.comments
        newRecord.status = data.status
        newRecord.previousModDeploymentId = previousVersionId
        newRecord.store()
        return data

    }

    override fun isModuleDeploymentAvailable(customerId: Int, moduleId: Int, versionNo: String, deployment: String): Boolean {
        val results = jooqContext
                .selectFrom(MODULE_DEPLOYMENTS)
                .where(MODULE_DEPLOYMENTS.CUSTOMER_ID.eq(customerId))
                .and(MODULE_DEPLOYMENTS.MODULE_ID.eq(moduleId))
                .and(MODULE_DEPLOYMENTS.VERSION_NO.eq(versionNo))
                .and(MODULE_DEPLOYMENTS.DEPLOYMENT_LOCATION.eq(deployment))
                .fetch()

        return results.isEmpty()
    }

    override fun getDeploymentHistoryForReleaseNote(ModuleId: Int, VersionNo: String): List<DeploymentHistoryForReleaseNote> {
        val results = jooqContext
                .select(MODULE_DEPLOYMENTS.ID, MODULE_DEPLOYMENTS.CUSTOMER_ID, CUSTOMER.NAME, MODULE_DEPLOYMENTS.DEPLOYMENT_LOCATION, MODULE_DEPLOYMENTS.APPLIED_DATE)
                .from(MODULE_DEPLOYMENTS)
                .join(CUSTOMER)
                .on(MODULE_DEPLOYMENTS.CUSTOMER_ID.eq(CUSTOMER.ID))
                .where(MODULE_DEPLOYMENTS.MODULE_ID.eq(ModuleId))
                .and(MODULE_DEPLOYMENTS.VERSION_NO.eq(VersionNo))
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> DeploymentHistoryForReleaseNote(
                    rm[MODULE_DEPLOYMENTS.ID],
                    rm[MODULE_DEPLOYMENTS.CUSTOMER_ID],
                    rm[CUSTOMER.NAME],
                    rm[MODULE_DEPLOYMENTS.DEPLOYMENT_LOCATION],
                    rm[MODULE_DEPLOYMENTS.APPLIED_DATE])
            }
        } ?: emptyList()
    }

    override fun viewModuleDeployment(ModuleId: Int, VersionNo: String, DeploymentLocation: String, CustomerId: Int): List<ViewModuleDeployment> {

        val results = jooqContext
                .selectFrom(MODULE_DEPLOYMENTS)
                .where(MODULE_DEPLOYMENTS.MODULE_ID.eq(ModuleId))
                .and(MODULE_DEPLOYMENTS.CUSTOMER_ID.eq(CustomerId))
                .and(MODULE_DEPLOYMENTS.VERSION_NO.eq(VersionNo))
                .and(MODULE_DEPLOYMENTS.DEPLOYMENT_LOCATION.eq(DeploymentLocation))
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> ViewModuleDeployment(
                    rm.customerId,
                    rm.deploymentLocation,
                    rm.moduleId,
                    rm.versionNo,
                    rm.appliedBy,
                    rm.appliedDate,
                    rm.comments,
                    rm.previousModDeploymentId,
                    rm.status)
            }
        } ?: emptyList()
    }

    override fun getModuleDeploymentVersion(Id: Int): String {
        val results = jooqContext
                .select(MODULE_DEPLOYMENTS.VERSION_NO)
                .from(MODULE_DEPLOYMENTS)
                .where(MODULE_DEPLOYMENTS.ID.eq(Id))
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> rm[MODULE_DEPLOYMENTS.VERSION_NO] }
        }[0] ?: "No previous version"
    }
}