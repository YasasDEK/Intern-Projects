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

package hms.commons.rnm

import hms.commons.rnm.db.schema.enums.DeploymentLocationLocationCode
import hms.commons.rnm.db.schema.enums.ModuleDeploymentsStatus
import java.time.LocalDate

data class SimpleResponse(
        val str: String,
        val int: Int
)

data class ComplexRequest(
        val str: String
)

//@JsonSubTypes(
//        JsonSubTypes.Type(
//                value = Employee::class
//        ),
//        JsonSubTypes.Type(
//                value = Customer::class
//        )
//)
//interface User {
//    val type: String
//}
//
//data class Employee(
//        val name: String,
//        val employeeId: Int,
//        override val type: String
//): User
//
//data class Customer(
//        val name: String,
//        val email: String,
//        override val type: String
//): User
//
//data class ComplexResponse(
//        val userList: List<User>
//)

data class  Customer(val id: Int, val name: String)

data class CustomersList(val customers: List<Customer>)

data class  Module(val id: Int, val name: String, val git: String)

data class ModulesList(val modules: List<Module>)

//data class ReleaseNote(val id: Int, val module_id: Int, val version_no: String)
//
//data class ReleaseNoteList(val releaseNote: List<ReleaseNote>)
//
//data class ModuleDeployments(val id: Int, val module_id: Int, val deployment_location: String)
//
//data class ModuleDeploymentsList(val moduleDeployments: List<ModuleDeployments>)

data class VersionHistory (val module_id: Int, val version_no: String, val release_date: LocalDate , val deployment_location: String = "")

data class VersionHistoryList (val versionHistory: List<VersionHistory>)

data class DeploymentLocation(val deployment_location: DeploymentLocationLocationCode)

data class DeploymentLocationList(val deploymentLocation: List<DeploymentLocation>)

data class ModulesForLocation (val id: Int, val module_id: Int, val module_name: String, val version_no: String, val customer_id: Int, val deployment_location: String, val tagId: Int = 0, val branchName: String = "")

data class ModulesForLocationList (val modulesForLocation: List<ModulesForLocation>)

data class DeploymentTimeLine (val id: Int, val version_no: String, val applied_date: LocalDate)

data class DeploymentTimeLineList (val deploymentTimeLine: List<DeploymentTimeLine>)

data class DependentModule (val id: Int, val name: String, val versions: List<String> = emptyList())

data class DependentModuleList (val dependentModules: List<DependentModule>)

//data class DeploymentVersion (val version_no: String)
//
//data class DeploymentVersionList (val deploymentVersion: List<DeploymentVersion>)
//
//data class AllDataVersion (val id: Int, val name: String, val version: List<DeploymentVersion>)
//
//data class AllDataVersionList (val allDataVersion: List<AllDataVersion>)

data class VersionHistoryInReleaseNote (val version_no: String)

data class VersionHistoryInReleaseNoteList (val versionHistoryInReleaseNote: List<VersionHistoryInReleaseNote>)

data class AddDevReleaseNote (val tagId: Int,
                              val customerId: Int,
                              val type: String,
                              val dependentVersion: List<DependentModuleVersion>,
                              val applyOnTopOf: String,
                              val docName: String,
                              val buildInstructions: String,
                              val pdInstructions: String,
                              val featureSummary: String,
                              val knowIssues: String,
                              val fixedBugs: String,
                              val areasToBeTested: String,
                              val releaseBy: String,
                              val releaseDate: LocalDate)

data class AddDevReleaseNoteList (val addDevReleaseNote: List<AddDevReleaseNote>)

data class DependentModuleVersion (val module: String, val version: String)

data class DependentModuleVersionList (val dependentModuleVersion: List<DependentModuleVersion>)

data class ViewDevReleaseNote (
                           val customerName: String,
                           val dependentVersion: List<DependentModuleVersion>,
                           val applyOnTopOf: String,
                           val type: String,
                           val docName: String,
                           val buildInstructions: String,
                           val pdInstructions: String,
                           val featureSummary: String,
                           val knowIssues: String,
                           val fixedBugs: String,
                           val areasToBeTested: String,
                           val releaseBy: String,
                           val releaseDate: LocalDate)

data class ViewDevReleaseNoteList (val viewDevReleaseNote: List<ViewDevReleaseNote>)

data class EditDevReleaseNote (val tagId: Int,
                               val customerId: Int,
                               val type: String,
                               val dependentVersion: List<DependentModuleVersion>,
                               val applyOnTopOf: String,
                               val docName: String,
                               val buildInstructions: String,
                               val pdInstructions: String,
                               val featureSummary: String,
                               val knowIssues: String,
                               val fixedBugs: String,
                               val areasToBeTested: String,
                               val releaseBy: String,
                               val releaseDate: LocalDate)

data class EditDevReleaseNoteList (val editDevReleaseNote: List<EditDevReleaseNote>)

data class VersionForDeployToLocation (val id: Int,
                             val customerId: Int,
                             val deploymentLocation: String = "",
                             val moduleId: Int,
                             val versionNo: String = ""
//                             val appliedDate: LocalDate,
//                             val appliedBy: String = "",
//                             val comment: String = ""
//                             val previousModuleDeploymentId: Int
                            )

data class VersionForDeployToLocationList (val deployToLocationGetDataList: List<VersionForDeployToLocation>)

data class CustomerForDeployToLocation (val customerId: Int, val customerName: String)

data class CustomerForDeployToLocationList (val customerForDeployToLocation: List<CustomerForDeployToLocation>)

data class LocationForDeployToLocation(val customerId: Int, val deploymentLocation: DeploymentLocationLocationCode)

data class LocationForDeployToLocationList (val locationForDeployToLocation: List<LocationForDeployToLocation>)

data class DeployToLocation (
        val customerId: Int,
        val deploymentLocation: String,
        val moduleId: Int,
        val versionNo: String,
        val appliedDate: LocalDate,
        val appliedBy: String,
        val comments: String,
        val previousVersion: String,
        val status: ModuleDeploymentsStatus
)

data class DeployToLocationList (val deployToLocation: List<DeployToLocation>)

data class  DeploymentHistoryForReleaseNote (
        val deploymentId: Int,
        val customerId: Int,
        val customerName: String,
        val deploymentLocation: String,
        val appliedDate: LocalDate)

data class DeploymentHistoryForReleaseNoteList (val deploymentHistoryForReleaseNote: List<DeploymentHistoryForReleaseNote>)

data class AddTagData (val moduleId: Int, val versionNo: String, val branchName: String)

data class AddTagDataList (val addTagData: List<AddTagData>)

data class VersionHistoryData(val tagId: Int, val versionNo: String, val branchName: String, val details: List<VersionHistoryOtherData> = emptyList())

data class VersionHistoryDataList (val versionHistoryData: List<VersionHistoryData>)

data class VersionHistoryOtherData(val customerId: Int, val customerName: String , val date: LocalDate, val deploymentData:List<DeployedLocation> = emptyList())

data class VersionHistoryOtherDataList(val versionHistoryOtherData: List<VersionHistoryOtherData>)

data class DeployedLocation (val deploymentLocation: String)

data class DeployedLocationList (val deployedLocation: List<DeployedLocation>)

data class ViewModuleDeployment (
        val customerId: Int,
        val deploymentLocation: String,
        val moduleId: Int,
        val versionNo: String,
        val appliedBy: String,
        val appliedDate: LocalDate,
        val comments: String,
        val previousVersionId: Int,
        val status: ModuleDeploymentsStatus,
        val previousVersion: String = "")

data class ViewModuleDeploymentList (val viewModuleDeployment: List<ViewModuleDeployment>)

data class AddDAReleaseNote(
        val tagId: Int,
        val customerId: Int,
        val artifactLocation: String,
        val md5sum: String,
        val testedAreas: String,
        val newFeatures: String,
        val foundBugs: String,
        val fixedBugs: String,
        val knownBugs: String,
        val limitations: String,
        val modulesReleased: String,
        val prerequisits: String,
        val deployInstructions: String,
        val testCases: String,
        val patchInstructions: String,
        val areasToBeTested: String,
        val releasedBy: String,
        val releasedDate: LocalDate,
        val testedBy: String,
        val plannedStart: LocalDate,
        val plannedEnd: LocalDate,
        val actualStart: LocalDate,
        val actualEnd: LocalDate,
        val type: String,
        val comment: String)

data class AddDAReleaseNoteList ( val addDAReleaseNote: List<AddDAReleaseNote>)

data class ViewDAReleaseNote(
        val tagId: Int,
        val customerId: Int,
        val artifactLocation: String,
        val md5sum: String,
        val testedAreas: String,
        val newFeatures: String,
        val foundBugs: String,
        val fixedBugs: String,
        val knownBugs: String,
        val limitations: String,
        val modulesReleased: String,
        val prerequisits: String,
        val deployInstructions: String,
        val testCases: String,
        val patchInstructions: String,
        val areasToBeTested: String,
        val releasedBy: String,
        val releasedDate: LocalDate,
        val testedBy: String,
        val plannedStart: LocalDate,
        val plannedEnd: LocalDate,
        val actualStart: LocalDate,
        val actualEnd: LocalDate,
        val type: String,
        val comment: String)

data class ViewDAReleaseNoteList ( val viewDAReleaseNote: List<ViewDAReleaseNote>)

data class EditDAReleaseNote(
        val tagId: Int,
        val customerId: Int,
        val artifactLocation: String,
        val md5sum: String,
        val testedAreas: String,
        val newFeatures: String,
        val foundBugs: String,
        val fixedBugs: String,
        val knownBugs: String,
        val limitations: String,
        val modulesReleased: String,
        val prerequisits: String,
        val deployInstructions: String,
        val testCases: String,
        val patchInstructions: String,
        val areasToBeTested: String,
//        val releasedBy: String,
//        val releasedDate: LocalDate,
        val testedBy: String,
        val plannedStart: LocalDate,
        val plannedEnd: LocalDate,
        val actualStart: LocalDate,
        val actualEnd: LocalDate,
        val type: String,
        val comment: String)

data class EditDAReleaseNoteList ( val viewDAReleaseNote: List<ViewDAReleaseNote>)
