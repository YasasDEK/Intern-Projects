/*
 * (C) Copyright 2006-2020 hSenid Mobile Solutions (Pvt) Limited.
 *
 * All Rights Reserved.
 *
 * These materials are unpublished, proprietary, confidential source code of
 * hSenid Mobile Solutions (Pvt) Limited and constitute a TRADE SECRET
 * of hSenid Mobile Solutions (Pvt) Limited.
 *
 * hSenid Mobile Solutions (Pvt) Limited retains all title to and intellectual
 * property rights in these materials.
 *
 */
package hms.commons.rnm.customer

import hms.commons.rnm.Customer
import hms.commons.rnm.db.schema.Tables.CUSTOMER_MODULE_DEPLOYMENT
import hms.commons.rnm.db.schema.Tables.DEV_RELEASE_NOTE
import hms.commons.rnm.db.schema.tables.Customer.CUSTOMER
import org.jooq.DSLContext
import org.springframework.stereotype.Component

interface CustomerRepo {
    fun getCustomerList(): List<Customer>
    fun getCustomersForModules(moduleId: Int, tagId: Int): List<Customer>
    fun getAvailableCustomersInDevReleaseNote(tagId: Int): List<Customer>
}

@Component
class CustomerRepoImpl(val jooqContext: DSLContext) : CustomerRepo {

    override fun getCustomerList(): List<Customer> {
        val results = jooqContext
                .selectFrom(CUSTOMER)
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> Customer(rm.id, rm.name) }
        } ?: emptyList()
    }

    override fun getCustomersForModules(moduleId: Int, tagId: Int): List<Customer> {
        val availableCustomers = jooqContext
                .select(DEV_RELEASE_NOTE.CUSTOMER_ID)
                .from(DEV_RELEASE_NOTE)
                .join(CUSTOMER)
                .on(DEV_RELEASE_NOTE.CUSTOMER_ID.eq(CUSTOMER.ID))
                .where(DEV_RELEASE_NOTE.TAG_ID.eq(tagId))
                .fetch()

        val test =
                jooqContext
                        .select(CUSTOMER_MODULE_DEPLOYMENT.CUSTOMER_ID, CUSTOMER.NAME)
                        .from(CUSTOMER_MODULE_DEPLOYMENT)
                        .join(CUSTOMER)
                        .on(CUSTOMER_MODULE_DEPLOYMENT.CUSTOMER_ID.eq(CUSTOMER.ID))
                        .where(CUSTOMER_MODULE_DEPLOYMENT.MODULE_ID.eq(moduleId))
                        .fetch()

        val results = jooqContext
                .select(CUSTOMER_MODULE_DEPLOYMENT.CUSTOMER_ID, CUSTOMER.NAME)
                .from(CUSTOMER_MODULE_DEPLOYMENT)
                .join(CUSTOMER)
                .on(CUSTOMER_MODULE_DEPLOYMENT.CUSTOMER_ID.eq(CUSTOMER.ID))
                .where(CUSTOMER_MODULE_DEPLOYMENT.MODULE_ID.eq(moduleId))
                .and(CUSTOMER_MODULE_DEPLOYMENT.CUSTOMER_ID.notIn(availableCustomers))
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> Customer(rm[CUSTOMER_MODULE_DEPLOYMENT.CUSTOMER_ID], rm[CUSTOMER.NAME]) }
        } ?: emptyList()

    }

    override fun getAvailableCustomersInDevReleaseNote(tagId: Int): List<Customer> {
        val results = jooqContext
                .select(DEV_RELEASE_NOTE.CUSTOMER_ID, CUSTOMER.NAME)
                .from(DEV_RELEASE_NOTE)
                .join(CUSTOMER)
                .on(DEV_RELEASE_NOTE.CUSTOMER_ID.eq(CUSTOMER.ID))
                .where(DEV_RELEASE_NOTE.TAG_ID.eq(tagId))
                .fetch()

        return results?.let { r ->
            r.toList().map { rm -> Customer(rm[DEV_RELEASE_NOTE.CUSTOMER_ID], rm[CUSTOMER.NAME]) }
        } ?: emptyList()
    }
}