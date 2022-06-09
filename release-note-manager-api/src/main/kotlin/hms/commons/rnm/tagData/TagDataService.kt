package hms.commons.rnm.tagData

import hms.commons.rnm.AddTagData
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

interface TagDataService {
    fun addTagData(data: Mono<AddTagData>) : Mono<Any>
}

@Component
class TagDataServiceImpl(val tagDataRepo: TagDataRepo) : TagDataService {
    override fun addTagData(data: Mono<AddTagData>): Mono<Any> {
        return data.filter { req -> validate(req) }
                .switchIfEmpty {
                    Mono.error(RuntimeException("Invalid Version"))
                }
                .map { req ->
                    val existing = tagDataRepo.isVersionAvailable(req.moduleId, req.versionNo)
                    if(existing){
                        "Available"
                    } else {
                        tagDataRepo.addTagData(req)
                    }
                }
    }

    private fun validate(req: AddTagData): Boolean {
        var validVersionPatternOne = Regex("""\d+.\d+.\d+$""")
        var validVersionPatternTwo = Regex("""\d+.\d+.\d+\-patch\d+$""")
        var isValidVersion = validVersionPatternOne.containsMatchIn(req.versionNo) || validVersionPatternTwo.containsMatchIn(req.versionNo)

        return isValidVersion
    }

}
