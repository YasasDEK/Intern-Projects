package hms.commons.rnm.deployment

import hms.commons.rnm.*
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

interface DeploymentService {
    fun getDeploymentLocationList(customerId: Int): Mono<DeploymentLocationList>
    fun getDeploymentTimeLineList(customerId: Int, location: String, moduleId: Int): Mono<DeploymentTimeLineList>
    fun getVersionForDeployToLocation(ModuleId: Int): Mono<VersionForDeployToLocationList>
    fun getCustomerForDeployToLocation(ModuleId: Int): Mono<CustomerForDeployToLocationList>
    fun getLocationForDeployToLocation(): Mono<LocationForDeployToLocationList>
    fun deployToLocation(data: Mono<DeployToLocation>): Mono<DeployToLocation>
    fun deploymentHistoryForReleaseNote(ModuleId: Int, VersionNo: String): Mono<DeploymentHistoryForReleaseNoteList>
    fun viewModuleDeployment(ModuleId: Int, VersionNo: String, DeploymentLocation: String, CustomerId: Int): Mono<ViewModuleDeploymentList>
}

@Component
class DeploymentServiceImpl(val deploymentRepo: DeploymentRepo) : DeploymentService {

    override fun getDeploymentLocationList(customerId: Int): Mono<DeploymentLocationList> {
        return Mono.fromCallable {
            deploymentRepo.getDeploymentLocationList(customerId)
        }
                .map { DeploymentLocationList(it) }
    }

    override fun getDeploymentTimeLineList(customerId: Int, location: String, moduleId: Int): Mono<DeploymentTimeLineList> {
        return Mono.fromCallable {
            deploymentRepo.getDeploymentTimeLineList(customerId, location, moduleId)
        }
                .map { DeploymentTimeLineList(it) }
    }

    override fun getVersionForDeployToLocation(ModuleId: Int): Mono<VersionForDeployToLocationList> {
        return  Mono.fromCallable {
            deploymentRepo.getVersionForDeployToLocation(ModuleId)
        }
                .map { VersionForDeployToLocationList(it) }
    }

    override fun getCustomerForDeployToLocation(ModuleId: Int): Mono<CustomerForDeployToLocationList> {
        return  Mono.fromCallable {
            deploymentRepo.getCustomerForDeployToLocation(ModuleId)
        }
                .map { CustomerForDeployToLocationList(it) }
    }

    override fun getLocationForDeployToLocation(): Mono<LocationForDeployToLocationList> {
        return  Mono.fromCallable {
            deploymentRepo.getLocationForDeployToLocation()
        }
                .map { LocationForDeployToLocationList(it) }
    }

    override fun deployToLocation(data: Mono<DeployToLocation>): Mono<DeployToLocation> {
        return data
                .filter{ req ->  deploymentRepo.isModuleDeploymentAvailable(req.customerId,req.moduleId,req.versionNo,req.deploymentLocation)}
                .switchIfEmpty {
//                    Mono.error(RuntimeException("Invalid Request"))
                    throw RuntimeException("available")
                }
                .map {req ->
//                    val existing = deploymentRepo.isModuleDeploymentAvailable(req.customerId,req.moduleId,req.versionNo,req.deploymentLocation)
                        deploymentRepo.deployToLocation(req)
                }
    }

    override fun deploymentHistoryForReleaseNote(ModuleId: Int, VersionNo: String): Mono<DeploymentHistoryForReleaseNoteList> {
        return  Mono.fromCallable {
            deploymentRepo.getDeploymentHistoryForReleaseNote(ModuleId, VersionNo)
        }
                .map { DeploymentHistoryForReleaseNoteList(it) }
    }

    override fun viewModuleDeployment(ModuleId: Int, VersionNo: String, DeploymentLocation: String, CustomerId: Int): Mono<ViewModuleDeploymentList> {
        return Mono
                .fromCallable { deploymentRepo.viewModuleDeployment(ModuleId, VersionNo, DeploymentLocation, CustomerId) }
                .flatMapMany { Flux.fromIterable(it) }
                .map {data ->
                    if(data.previousVersionId == 0) {
                        data.copy(previousVersion = "no previous version")
                    } else {
                        val previousVersion: String = deploymentRepo.getModuleDeploymentVersion(data.previousVersionId)
                        data.copy(previousVersion = previousVersion)
                    }
                }
                .collectList()
                .map { ViewModuleDeploymentList(it) }
    }

}