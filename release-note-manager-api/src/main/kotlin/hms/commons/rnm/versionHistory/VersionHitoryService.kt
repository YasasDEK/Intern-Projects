package hms.commons.rnm.versionHistory

import hms.commons.rnm.*
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface VersionHistoryService {
    fun getVersionHistoryForReleaseNoteEditList(moduleId: Int, versionNo: String): Mono<VersionHistoryInReleaseNoteList>
    fun getVersionHistoryList(moduleId: Int): Mono<VersionHistoryList>
    fun getVersionHistoryInReleaseNoteList(moduleId: Int): Mono<VersionHistoryInReleaseNoteList>
    fun getVersionHistoryDataList(moduleId: Int): Mono<VersionHistoryDataList>
}

@Component
class VersionHistoryForServiceImpl(val versionHistoryRepo: VersionHistoryRepo) : VersionHistoryService {

    override fun getVersionHistoryForReleaseNoteEditList(moduleId: Int, versionNo: String): Mono<VersionHistoryInReleaseNoteList> {
        return Mono.fromCallable {
            versionHistoryRepo.getVersionHistoryForReleaseNoteEditList(moduleId, versionNo)
        }
                .map { VersionHistoryInReleaseNoteList(it) }
    }

    override fun getVersionHistoryList(moduleId: Int): Mono<VersionHistoryList> {
        return Mono
                .fromCallable { versionHistoryRepo.getVersionHistoryList(moduleId) }
                .flatMapMany { Flux.fromIterable(it) }
                .map {location ->
                    val deployment: String = versionHistoryRepo.getDeploymentLocationList(location.module_id, location.version_no)
                    location.copy(deployment_location = deployment)
                }
                .collectList()
                .map { VersionHistoryList(it) }
    }

    override fun getVersionHistoryInReleaseNoteList(moduleId: Int): Mono<VersionHistoryInReleaseNoteList>{
        return Mono.fromCallable {
            versionHistoryRepo.getVersionHistoryInReleaseNoteList(moduleId)
        }
                .map { VersionHistoryInReleaseNoteList(it) }
    }

    override fun getVersionHistoryDataList(moduleId: Int): Mono<VersionHistoryDataList> {
        return Mono
                .fromCallable { versionHistoryRepo.getVersionHistoryDataList(moduleId) }
                .flatMapMany { Flux.fromIterable(it) }
                .map {data ->
                    val otherData: List<VersionHistoryOtherData> = versionHistoryRepo.getVersionHistoryOtherDataList(data.tagId)

                    val fullData = otherData.map { other ->
                        val deployment: List<DeployedLocation> = versionHistoryRepo.getDeployedLocation(moduleId,data.versionNo,other.customerId)
                        other.copy(deploymentData = deployment)
                    }

                    data.copy(details = fullData)
                }
                .collectList()
                .map { VersionHistoryDataList(it) }

    }
}