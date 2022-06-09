package hms.commons.rnm.releaseNote

import hms.commons.rnm.*
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

interface ReleaseNoteService {
    fun addDevReleaseNote(data: Mono<AddDevReleaseNote>) : Mono<Any>
    fun getViewDevReleaseNoteList(tagId: Int, customerId: Int): Mono<ViewDevReleaseNoteList>
    fun editDevReleaseNote(data: Mono<EditDevReleaseNote>) : Mono<EditDevReleaseNote>
    fun addDAReleaseNote(data: Mono<AddDAReleaseNote>) : Mono<Any>
    fun getViewDAReleaseNoteList(tagId: Int, customerId: Int): Mono<ViewDAReleaseNoteList>
    fun editDAReleaseNote(data: Mono<EditDAReleaseNote>) : Mono<EditDAReleaseNote>
}

@Component
class ReleaseNoteServiceImpl(val releaseNoteRepo: ReleaseNoteRepo) : ReleaseNoteService {

    //add a release note
    override fun addDevReleaseNote(data: Mono<AddDevReleaseNote>): Mono<Any> {

        return data
                .map { req ->
                    val existing = releaseNoteRepo.isDevReleaseNoteAvailable(req.tagId, req.customerId)
                    if(existing){
                        releaseNoteRepo.addDevReleaseNote(req)
                    } else {
                        releaseNoteRepo.addToDraftDevReleaseNote(req)
                    }
                }
    }

//    private fun validate(req: AddDevReleaseNote): Boolean {
//        var validVersionPattern = Regex("""\d+.\d+.\d+""")
//        var isValidVersion = validVersionPattern.containsMatchIn(req.versionNo)
//
//        return isValidVersion
//    }

    //view a release note
    override fun getViewDevReleaseNoteList(tagId: Int, customerId: Int): Mono<ViewDevReleaseNoteList> {
        return Mono.fromCallable {
            releaseNoteRepo.getViewDevReleaseNoteList(tagId, customerId)
        }
                .map { ViewDevReleaseNoteList(it) }

    }

    //edit a release note
    override fun editDevReleaseNote(data: Mono<EditDevReleaseNote>): Mono<EditDevReleaseNote> {
        return data.map {req ->
            releaseNoteRepo.editDevReleaseNote(req)}
    }

    override fun addDAReleaseNote(data: Mono<AddDAReleaseNote>): Mono<Any> {

        return data
                .map { req ->
                    val existing = releaseNoteRepo.isDAReleaseNoteAvailable(req.tagId, req.customerId)
                    if(existing){
                        releaseNoteRepo.addDAReleaseNote(req)
                    } else {
                        releaseNoteRepo.addToDraftDAReleaseNote(req)
                    }
                }
    }

    override fun getViewDAReleaseNoteList(tagId: Int, customerId: Int): Mono<ViewDAReleaseNoteList> {
        return Mono.fromCallable {
            releaseNoteRepo.getViewDAReleaseNoteList(tagId, customerId)
        }
                .map { ViewDAReleaseNoteList(it) }
    }

    override fun editDAReleaseNote(data: Mono<EditDAReleaseNote>): Mono<EditDAReleaseNote> {
        return data.map {req ->
            releaseNoteRepo.editDAReleaseNote(req)}
    }


}
