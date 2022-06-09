package hms.commons.rnm.module

import hms.commons.rnm.Module
import hms.commons.rnm.ModulesForLocation
import hms.commons.rnm.db.schema.Tables.MODULE_TAG
import hms.commons.rnm.db.schema.tables.Module.MODULE
import hms.commons.rnm.db.schema.tables.ModuleDeployments
import org.jooq.DSLContext
import org.jooq.impl.DSL.max
import org.springframework.stereotype.Component

interface ModuleRepo {
    fun getModuleList(): List<Module>
    fun getModuleUsingId(moduleId: Int): List<Module>
    fun getModulesForLocationList(customerId: Int, location: String): List<ModulesForLocation>
    fun getTag(moduleId: Int, versionNo: String): Int
    fun getBranch(moduleId: Int, versionNo: String): String
}

@Component
class ModuleRepoImpl(val jooqContext: DSLContext) : ModuleRepo {

    //view all modules
    override fun getModuleList(): List<Module> {
        val results = jooqContext
                .select(MODULE.ID, MODULE.NAME, MODULE.GIT_LOCATION)
                .from(MODULE)
                .fetch()

        return results?.let { r ->
        r.toList().map { rm -> Module(rm[MODULE.ID],rm[MODULE.NAME],rm[MODULE.GIT_LOCATION]) }
        } ?: emptyList()
    }

    override fun getModuleUsingId(moduleId: Int): List<Module> {
        val results = jooqContext
                .select(MODULE.ID, MODULE.NAME, MODULE.GIT_LOCATION)
                .from(MODULE)
                .where(MODULE.ID.eq(moduleId))
                .fetch()

        return results?.let { r ->
            r.map { rm -> Module(rm[MODULE.ID],rm[MODULE.NAME],rm[MODULE.GIT_LOCATION]) }
        } ?: emptyList()
    }

    //get modules for deployment location
    override fun getModulesForLocationList(customerId: Int, location: String): List<ModulesForLocation> {
        val results = jooqContext
                .select(ModuleDeployments.MODULE_DEPLOYMENTS.ID, ModuleDeployments.MODULE_DEPLOYMENTS.MODULE_ID, MODULE.NAME, max(ModuleDeployments.MODULE_DEPLOYMENTS.VERSION_NO), ModuleDeployments.MODULE_DEPLOYMENTS.CUSTOMER_ID, ModuleDeployments.MODULE_DEPLOYMENTS.DEPLOYMENT_LOCATION)
                .from(ModuleDeployments.MODULE_DEPLOYMENTS)
                .join(MODULE)
                .on(ModuleDeployments.MODULE_DEPLOYMENTS.MODULE_ID.eq(MODULE.ID))
                .where((ModuleDeployments.MODULE_DEPLOYMENTS.CUSTOMER_ID.eq(customerId)))
                .and(ModuleDeployments.MODULE_DEPLOYMENTS.DEPLOYMENT_LOCATION.eq(location))
                .groupBy(ModuleDeployments.MODULE_DEPLOYMENTS.MODULE_ID)
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> ModulesForLocation(rm[ModuleDeployments.MODULE_DEPLOYMENTS.ID],rm[ModuleDeployments.MODULE_DEPLOYMENTS.MODULE_ID], rm[MODULE.NAME], rm.value4() , rm[ModuleDeployments.MODULE_DEPLOYMENTS.CUSTOMER_ID], rm[ModuleDeployments.MODULE_DEPLOYMENTS.DEPLOYMENT_LOCATION] )}
        } ?: emptyList()
    }

        override fun getTag(moduleId: Int, versionNo: String): Int {
        val results = jooqContext
                .select(MODULE_TAG.ID)
                .from(MODULE_TAG)
                .where(MODULE_TAG.MODULE_ID.eq(moduleId))
                .and(MODULE_TAG.VERSION_NO.eq(versionNo))
                .fetch()

        return results?.let { r ->
                r.toList().map { rm ->rm.component1()}
            }[0] ?: 0
    }

    override fun getBranch(moduleId: Int, versionNo: String): String {
        val results = jooqContext
                .select(MODULE_TAG.BRANCH_NAME)
                .from(MODULE_TAG)
                .where(MODULE_TAG.MODULE_ID.eq(moduleId))
                .and(MODULE_TAG.VERSION_NO.eq(versionNo))
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> rm.component1()}
        }[0].toString() ?: ""
    }
}