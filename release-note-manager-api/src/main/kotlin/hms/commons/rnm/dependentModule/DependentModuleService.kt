package hms.commons.rnm.dependentModule

import hms.commons.rnm.DependentModule
import hms.commons.rnm.VersionHistoryInReleaseNote
import hms.commons.rnm.DependentModuleList
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface DependentModuleService {
    fun getDependentModuleList(moduleId: Int): Mono<DependentModuleList>
}

@Component
class DependentModuleServiceImpl(val dependentModuleRepo: DependentModuleRepo) : DependentModuleService {

    override fun getDependentModuleList(moduleId: Int): Mono<DependentModuleList>{
        return Mono
                .fromCallable { dependentModuleRepo.getDependentModuleList(moduleId) }
                .flatMapMany { Flux.fromIterable(it) }
                .map {module ->
                    val versions: List<String> = dependentModuleRepo.getVersionHistoryInReleaseNoteList(module.id)
                    module.copy(versions = versions)
                }
                .collectList()
                .map {DependentModuleList(it)}
    }

//    private fun getModuleVersions(module: DependentModule): List<String> {
//        return listOf("1.1.1","2.2.2","3.3.3")
//    }

}
