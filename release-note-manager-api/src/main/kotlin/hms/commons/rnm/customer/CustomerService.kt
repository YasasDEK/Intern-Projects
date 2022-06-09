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

import hms.commons.rnm.CustomersList
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

interface CustomerService {
    fun getCustomerList(): Mono<CustomersList>
    fun getCustomersForModules(moduleId: Int, tagId: Int) :  Mono<CustomersList>
    fun getAvailableCustomersInDevReleaseNote(tagId: Int): Mono<CustomersList>
}

@Component
class CustomerServiceImpl(val customerRepo: CustomerRepo) : CustomerService {

    override fun getCustomerList(): Mono<CustomersList> {
        return Mono.fromCallable {
            customerRepo.getCustomerList()
        }
                .map { CustomersList(it) }
    }

    override fun getCustomersForModules(moduleId: Int, tagId: Int): Mono<CustomersList> {
        return Mono.fromCallable {
            customerRepo.getCustomersForModules(moduleId,tagId)
        }
                .map { CustomersList(it) }
    }

    override fun getAvailableCustomersInDevReleaseNote(tagId: Int): Mono<CustomersList> {
        return Mono.fromCallable {
            customerRepo.getAvailableCustomersInDevReleaseNote(tagId)
        }
                .map { CustomersList(it) }
    }
}