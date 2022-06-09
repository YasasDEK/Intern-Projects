package hms.commons.rnm.module

import hms.commons.rnm.Module
import hms.commons.rnm.ModulesForLocationList
import hms.commons.rnm.ModulesList
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ModuleService {
    fun getModuleList(): Mono<ModulesList>
    fun getModuleUsingId(moduleId: Int): Mono<ModulesList>
    fun getModulesForLocationList(customerId: Int, location: String): Mono<ModulesForLocationList>
}

@Component
class ModuleServiceImpl(val moduleRepo: ModuleRepo) : ModuleService {

    companion object {
        @JvmStatic
        private val Logger = LoggerFactory.getLogger(ModuleServiceImpl::class.java)
    }

    override fun getModuleList(): Mono<ModulesList> {
        Logger.info("Getting module list")
        return Mono.fromCallable {
            moduleRepo.getModuleList()
        }
            .map { ModulesList(it) }
            .doOnSuccess { ml -> Logger.info("Module list fetched [${ml}]") }
            .doOnError { err -> Logger.error("Error while getting module list", err) }
    }

    override fun getModuleUsingId(moduleId: Int): Mono<ModulesList> {
        return Mono.fromCallable {
            moduleRepo.getModuleUsingId(moduleId)
        }
            .map { ModulesList(it) }
    }

    override fun getModulesForLocationList(customerId: Int, location: String): Mono<ModulesForLocationList> {
        return Mono
            .fromCallable { moduleRepo.getModulesForLocationList(customerId, location) }
            .flatMapMany { Flux.fromIterable(it) }
            .map { data ->
                val tag: Int = moduleRepo.getTag(data.module_id, data.version_no)
                val branch: String = moduleRepo.getBranch(data.module_id, data.version_no)
                data.copy(branchName = branch, tagId = tag)

            }
            .collectList()
            .map { ModulesForLocationList(it) }
    }
}