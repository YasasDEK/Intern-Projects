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

package hms.commons.rnm.api

import hms.commons.rnm.*
import hms.commons.rnm.customer.CustomerService
import hms.commons.rnm.dependentModule.DependentModuleService
import hms.commons.rnm.deployment.DeploymentService
import hms.commons.rnm.module.ModuleService
import hms.commons.rnm.releaseNote.ReleaseNoteService
import hms.commons.rnm.tagData.TagDataService
import hms.commons.rnm.versionHistory.VersionHistoryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.router

//import org.keycloak.*

@RestController
@Configuration
class RouterFunctionConfig(val customerService: CustomerService,
                           val moduleService: ModuleService,
                           val versionHistoryService: VersionHistoryService,
                           val dependentModuleService: DependentModuleService,
                           val releaseNoteService: ReleaseNoteService,
                           val deploymentService: DeploymentService,
                           val addTagDataService: TagDataService) {

    //get all customers
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/customers/get",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get the list of customers in the system",
                            operationId = "Customer-List-Get",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = CustomersList::class))])]
                    )
            )/*,
        RouterOperation(
            path = "/rnm-api/customers/post",
            method = [RequestMethod.POST],
            operation = Operation(
                operationId = "Test-Post",
                requestBody = RequestBody(content = [Content(schema = Schema(implementation = ComplexRequest::class))]),
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ComplexResponse::class))])]
            )
        )*/
    )
    @Bean
    fun customerRouter() = router {
        "/rnm-api/".nest {
            GET("customers/get") {
                ok().body(customerService.getCustomerList(), CustomersList::class.java)
            }
//            POST("post") {
//                ok().bodyValue(ComplexResponse(
//                    userList = listOf()
//                ))
//            }
        }
    }

    // get all modules
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/modules/get",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get the list of modules in the system",
                            operationId = "Module-List-Get",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ModulesList::class))])]
                    )
            )
    )
    @Bean
    fun moduleRouter() = router {
        "/rnm-api/".nest {
            GET("modules/get") {
                ok().body(moduleService.getModuleList(), ModulesList::class.java)
            }
        }
    }

    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/modulesUsingId/get/{moduleId}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get the module using id in the system",
                            operationId = "Module-USING-ID-Get",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ModulesList::class))])]
                    )
            )
    )
    @Bean
    fun moduleUsingIdRouter() = router {
        "/rnm-api/".nest {
            GET("modulesUsingId/get/{moduleId}") {
                request ->
                val moduleId = request.pathVariable("moduleId").toInt()
                ok().body(moduleService.getModuleUsingId(moduleId), ModulesList::class.java)
            }
        }
    }

    //get version history
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/versions/get/{moduleId}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get the list of version history for a module in the system",
                            operationId = "Version-History-List-Get",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = VersionHistoryList::class))])]
                    )
            )
    )
    @Bean
    fun versionHistoryRouter() = router {
        "/rnm-api/".nest {
            GET("versions/get/{moduleId}") {
                request ->
                val moduleId = request.pathVariable("moduleId").toInt()
                ok().body(versionHistoryService.getVersionHistoryList(moduleId), VersionHistoryList::class.java)

            }
        }
    }

    //get deployment location
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/deployments/get/{customerId}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get the list of deployment location for a customer in the system",
                            operationId = "Version-History-List-Get",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = DeploymentLocationList::class))])]
                    )
            )
    )
    @Bean
    fun deploymentLocationRouter() = router {
        "/rnm-api/".nest {
            GET("deployments/get/{customerId}") {
                request ->
                val customerId = request.pathVariable("customerId").toInt()
                ok().body(deploymentService.getDeploymentLocationList(customerId), DeploymentLocationList::class.java)

            }
        }
    }

    //get module for location
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/modulesForLocation/get/{customerId}/{location}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get the list of modules for a deployment location for a customer in the system",
                            operationId = "Module-For-Location-List-Get",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ModulesForLocationList::class))])]
                    )
            )
    )
    @Bean
    fun modulesForLocationRouter() = router {
        "/rnm-api/".nest {
            GET("modulesForLocation/get/{customerId}/{location}") {
                request ->
                val customerId = request.pathVariable("customerId").toInt()
                val location = request.pathVariable("location")
                ok().body(moduleService.getModulesForLocationList(customerId, location), ModulesForLocationList::class.java)

            }
        }
    }

    //get deployment time line
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/timeline/get/{customerId}/{location}/{moduleId}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get the deployment time line in the system",
                            operationId = "Deployment-Time-Line-List-Get",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = DeploymentTimeLineList::class))])]
                    )
            )
    )
    @Bean
    fun deploymentTimeLineRouter() = router {
        "/rnm-api/".nest {
            GET("timeline/get/{customerId}/{location}/{moduleId}") {
                request ->
                val customerId = request.pathVariable("customerId").toInt()
                val location = request.pathVariable("location")
                val moduleId = request.pathVariable("moduleId").toInt()
                ok().body(deploymentService.getDeploymentTimeLineList(customerId, location, moduleId), DeploymentTimeLineList::class.java)

            }
        }
    }

    //get dependent modules list
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/dependent/get/{moduleId}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get the dependent module list in the system",
                            operationId = "Dependent-module-List-Get",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = DependentModuleList::class))])]
                    )
            )
    )
    @Bean
    fun dependentModulesRouter() = router {
        "/rnm-api/".nest {
            GET("dependent/get/{moduleId}") {
                request ->
                val moduleId = request.pathVariable("moduleId").toInt()
                ok().body(dependentModuleService.getDependentModuleList(moduleId), DependentModuleList::class.java)

            }
        }
    }

    //add dev release note
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/api/addDevReleaseNote",
                    method = [RequestMethod.POST],
                    operation = Operation(
                            description = "Add dev release notes to the system",
                            operationId = "DEV-RELEASE-NOTE-Add",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = AddDevReleaseNote::class))])]
                    )
            )
    )
    @Bean
    fun addDevReleaseRouter() = router {
        (accept (APPLICATION_JSON) and "/rnm-api/").nest {
            POST("api/addDevReleaseNote") {
                request ->
                    val req = request.bodyToMono(AddDevReleaseNote::class.java)
                    ok().body(releaseNoteService.addDevReleaseNote(req), AddDevReleaseNote::class.java)
            }
        }
    }

    //view dev release note
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/devReleaseNote/get/{tagId}/{customerId}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "view dev release notes to the system",
                            operationId = "DEV-RELEASE-NOTE-View",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ViewDevReleaseNoteList::class))])]
                    )
            )
    )
    @Bean
    fun viewDevReleaseNoteRouter() = router {
        "/rnm-api/".nest {
            GET("devReleaseNote/get/{tagId}/{customerId}") {
                request ->
                val tagId = request.pathVariable("tagId").toInt()
                val customerId = request.pathVariable("customerId").toInt()
                ok().body(releaseNoteService.getViewDevReleaseNoteList(tagId, customerId), ViewDevReleaseNoteList::class.java)
            }
        }
    }

    //get version no for release note edit
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/releaseNoteEdit/get/{moduleId}/{versionNo}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "get release note version to edit release notes in the system",
                            operationId = "Version-Number-List-Get",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = VersionHistoryInReleaseNoteList::class))])]
                    )
            )
    )
    @Bean
    fun getVersionNoEditRouter() = router {
        "/rnm-api/".nest {
            GET("releaseNoteEdit/get/{moduleId}/{versionNo}") {
                request ->
                val moduleId = request.pathVariable("moduleId").toInt()
                val versionNo = request.pathVariable("versionNo")
                ok().body(versionHistoryService.getVersionHistoryForReleaseNoteEditList(moduleId, versionNo), VersionHistoryInReleaseNoteList::class.java)
            }
        }
    }

    //edit dev release note
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/api/editDevReleaseNote",
                    method = [RequestMethod.POST],
                    operation = Operation(
                            description = "Edit Dev release notes to the system",
                            operationId = "Release-Dev-Note-List-Edit",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = EditDevReleaseNote::class))])]
                    )
            )
    )
    @Bean
    fun editDevReleaseNoteRouter() = router {
        (accept (APPLICATION_JSON) and "/rnm-api/").nest {
            POST("api/editDevReleaseNote") {
                request ->
                val req = request.bodyToMono(EditDevReleaseNote::class.java)
                ok().body(releaseNoteService.editDevReleaseNote(req), EditDevReleaseNote::class.java)
            }
        }
    }

    //get data for deploy to location
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/getVersionForDeployToLocation/{moduleId}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get versions for deploy to location from the system",
                            operationId = "VERSIONS-FOR-DEPLOY-TO-LOCATION-GET",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = VersionForDeployToLocationList::class))])]
                    )
            )
    )
    @Bean
    fun getVersionForDeployToLocationRouter() = router {
        "/rnm-api/".nest {
            GET("getVersionForDeployToLocation/{moduleId}") {
                request ->
                val moduleId = request.pathVariable("moduleId").toInt()
                ok().body(deploymentService.getVersionForDeployToLocation(moduleId), VersionForDeployToLocationList::class.java)
            }
        }
    }

    //get customers for deploy to location
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/getCustomerForDeployToLocation/{moduleId}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get customers for deploy to location from the system",
                            operationId = "CUSTOMERS-FOR-DEPLOY-TO-LOCATION-GET",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = CustomerForDeployToLocationList::class))])]
                    )
            )
    )
    @Bean
    fun getCustomerForDeployToLocationRouter() = router {
        "/rnm-api/".nest {
            GET("getCustomerForDeployToLocation/{moduleId}") {
                request ->
                val moduleId = request.pathVariable("moduleId").toInt()
                ok().body(deploymentService.getCustomerForDeployToLocation(moduleId), CustomerForDeployToLocationList::class.java)
            }
        }
    }

    //get location for deployment location list
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/getLocationForDeployToLocation",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get locations for deploy to location from the system",
                            operationId = "LOCATIONS-FOR-DEPLOY-TO-LOCATION-GET",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = LocationForDeployToLocationList::class))])]
                    )
            )
    )
    @Bean
    fun getLocationForDeployToLocationRouter() = router {
        "/rnm-api/".nest {
            GET("getLocationForDeployToLocation") {
                ok().body(deploymentService.getLocationForDeployToLocation(), LocationForDeployToLocationList::class.java)
            }
        }
    }

    //deploy to location
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/api/deployToLocation",
                    method = [RequestMethod.POST],
                    operation = Operation(
                            description = "Deploy to location",
                            operationId = "DEPLOY-TO-LOCATION",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = DeployToLocation::class))])]
                    )
            )
    )
    @Bean
    fun deployToLocationRouter() = router {
        (accept (APPLICATION_JSON) and "/rnm-api/").nest {
            POST("api/deployToLocation") {
                request ->
                val req = request.bodyToMono(DeployToLocation::class.java)
                ok().body(deploymentService.deployToLocation(req), DeployToLocation::class.java)
            }
        }
    }

    //deployment History
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/getDeploymentHistory/{ModuleId}/{VersionNo}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get locations for view release note from the system",
                            operationId = "LOCATIONS-FOR-VIEW-RELEASE-NOTE-GET",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = DeploymentHistoryForReleaseNoteList::class))])]
                    )
            )
    )
    @Bean
    fun getDeploymentHistoryRouter() = router {
        "/rnm-api/".nest {
            GET("getDeploymentHistory/{moduleId}/{versionNo}") {
                request ->
                val moduleId = request.pathVariable("moduleId").toInt()
                val versionNo = request.pathVariable("versionNo")
                ok().body(deploymentService.deploymentHistoryForReleaseNote(moduleId, versionNo), DeploymentHistoryForReleaseNoteList::class.java)
            }
        }
    }

    //add tag data
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/api/addTagData",
                    method = [RequestMethod.POST],
                    operation = Operation(
                            description = "Add tag data to the system",
                            operationId = "Tag-Data-Add",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = AddTagData::class))])]
                    )
            )
    )
    @Bean
    fun addTagDataRouter() = router {
        (accept (APPLICATION_JSON) and "/rnm-api/").nest {
            POST("api/addTagData") {
                request ->
                val req = request.bodyToMono(AddTagData::class.java)
                ok().body(addTagDataService.addTagData(req), AddTagData::class.java)
            }
        }
    }

    //get version history data History
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/getVersionHistoryData/{ModuleId}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get version history data from the system",
                            operationId = "VERSION-HISTORY-DATA-GET",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = VersionHistoryDataList::class))])]
                    )
            )
    )
    @Bean
    fun getVersionHistoryDataRouter() = router {
        "/rnm-api/".nest {
            GET("getVersionHistoryData/{moduleId}") {
                request ->
                val moduleId = request.pathVariable("moduleId").toInt()
                ok().body(versionHistoryService.getVersionHistoryDataList(moduleId), VersionHistoryDataList::class.java)
            }
        }
    }

    //get customers for modules
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/getCustomersForModule/{ModuleId}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get Customers from the module from the system",
                            operationId = "CUSTOMERS-FOR-MODULE-GET",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = CustomersList::class))])]
                    )
            )
    )
    @Bean
    fun getCustomersForModulesRouter() = router {
        "/rnm-api/".nest {
            GET("getCustomersForModule/{moduleId}/{tagId}") {
                request ->
                val moduleId = request.pathVariable("moduleId").toInt()
                val tagId = request.pathVariable("tagId").toInt()
                ok().body(customerService.getCustomersForModules(moduleId,tagId), CustomersList::class.java)
            }
        }
    }

    //get module deployments details
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/getModuleDeployment/{ModuleId}/{VersionNo}/{DeploymentLocation}/{CustomerId}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "Get module deployment data from the system",
                            operationId = "MODULE-DEPLOYMENT-DATA-GET",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ViewModuleDeploymentList::class))])]
                    )
            )
    )
    @Bean
    fun getModuleDeploymentRouter() = router {
        "/rnm-api/".nest {
            GET("getModuleDeployment/{ModuleId}/{VersionNo}/{DeploymentLocation}/{CustomerId}") {
                request ->
                val moduleId = request.pathVariable("ModuleId").toInt()
                val versionNo = request.pathVariable("VersionNo")
                val deployedLocation = request.pathVariable("DeploymentLocation")
                val customerId = request.pathVariable("CustomerId").toInt()
                ok().body(deploymentService.viewModuleDeployment(moduleId, versionNo, deployedLocation, customerId), ViewModuleDeploymentList::class.java)
            }
        }
    }


    //get Available Customers In Dev Release Note
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/getAvailableCustomersInDevReleaseNote/{tagId}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "get Available Customers In Dev Release Note",
                            operationId = "AVAILABLE-CUSTOMERS-IN-DEV-RELEASE-GET",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = CustomersList::class))])]
                    )
            )
    )
    @Bean
    fun getAvailableCustomersInDevReleaseNote() = router {
        "/rnm-api/".nest {
            GET("getAvailableCustomersInDevReleaseNote/{tagId}") {
                request ->
                val tagId = request.pathVariable("tagId").toInt()
                ok().body(customerService.getAvailableCustomersInDevReleaseNote(tagId), CustomersList::class.java)
            }
        }
    }

    //add DA release note
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/api/addDAReleaseNote",
                    method = [RequestMethod.POST],
                    operation = Operation(
                            description = "Add DA release note",
                            operationId = "DA-RELEASE-NOTE-Add",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = AddDAReleaseNote ::class))])]
                    )
            )
    )
    @Bean
    fun addDAReleaseNoteRouter() = router {
        (accept (APPLICATION_JSON) and "/rnm-api/").nest {
            POST("api/addDAReleaseNote") {
                request ->
                val req = request.bodyToMono(AddDAReleaseNote::class.java)
                ok().body(releaseNoteService.addDAReleaseNote(req), AddDAReleaseNote::class.java)
            }
        }
    }


    //edit da release note
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/api/editDAReleaseNote",
                    method = [RequestMethod.POST],
                    operation = Operation(
                            description = "Edit DA release notes to the system",
                            operationId = "Release-DA-Note-List-Edit",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = EditDAReleaseNote::class))])]
                    )
            )
    )
    @Bean
    fun editDAReleaseNoteRouter() = router {
        (accept (APPLICATION_JSON) and "/rnm-api/").nest {
            POST("api/editDAReleaseNote") {
                request ->
                val req = request.bodyToMono(EditDAReleaseNote::class.java)
                ok().body(releaseNoteService.editDAReleaseNote(req), EditDAReleaseNote::class.java)
            }
        }
    }

    //view da release note
    @RouterOperations(
            RouterOperation(
                    path = "/rnm-api/daReleaseNote/get/{tagId}/{customerId}",
                    method = [RequestMethod.GET],
                    operation = Operation(
                            description = "view da release notes to the system",
                            operationId = "DA-RELEASE-NOTE-View",
                            responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ViewDAReleaseNoteList::class))])]
                    )
            )
    )
    @Bean
    fun viewDAReleaseNoteRouter() = router {
        "/rnm-api/".nest {
            GET("daReleaseNote/get/{tagId}/{customerId}") {
                request ->
                val tagId = request.pathVariable("tagId").toInt()
                val customerId = request.pathVariable("customerId").toInt()
                ok().body(releaseNoteService.getViewDAReleaseNoteList(tagId, customerId), ViewDAReleaseNoteList::class.java)
            }
        }
    }



}
