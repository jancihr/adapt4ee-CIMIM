//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.02 at 11:00:40 PM CEST 
//


package eu.adapt4ee._2012.schema.units;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for spaceTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="spaceTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="ActiveStorage"/>
 *     &lt;enumeration value="ActiveStorageHospitalOrHealthcare"/>
 *     &lt;enumeration value="AirOrTrainOrBusBaggageArea"/>
 *     &lt;enumeration value="AirportConcourse"/>
 *     &lt;enumeration value="AtriumEachAdditionalFloor"/>
 *     &lt;enumeration value="AtriumFirstThreeFloors"/>
 *     &lt;enumeration value="AudienceOrSeatingAreaPenitentiary"/>
 *     &lt;enumeration value="AudienceOrSeatingAreaExerciseCenter"/>
 *     &lt;enumeration value="AudienceOrSeatingAreaGymnasium"/>
 *     &lt;enumeration value="AudienceOrSeatingAreaSportsArena"/>
 *     &lt;enumeration value="AudienceOrSeatingAreaConventionCenter"/>
 *     &lt;enumeration value="AudienceOrSeatingAreaMotionPictureTheatre"/>
 *     &lt;enumeration value="AudienceOrSeatingAreaPerformingArtsTheatre"/>
 *     &lt;enumeration value="AudienceOrSeatingAreaReligious"/>
 *     &lt;enumeration value="AudienceOrSeatingAreaPoliceOrFireStations"/>
 *     &lt;enumeration value="AudienceOrSeatingAreaCourtHouse"/>
 *     &lt;enumeration value="AudienceOrSeatingAreaAuditorium"/>
 *     &lt;enumeration value="BankCustomerArea"/>
 *     &lt;enumeration value="BankingActivityAreaOffice"/>
 *     &lt;enumeration value="BarberAndBeautyParlor"/>
 *     &lt;enumeration value="CardFileAndCataloguingLibrary"/>
 *     &lt;enumeration value="ClassroomOrLectureOrTrainingPenitentiary"/>
 *     &lt;enumeration value="ClassroomOrLectureOrTraining"/>
 *     &lt;enumeration value="ConfinementCellsPenitentiary"/>
 *     &lt;enumeration value="ConfinementCellsCourtHouse"/>
 *     &lt;enumeration value="ConferenceMeetingOrMultipurpose"/>
 *     &lt;enumeration value="CorridorOrTransition"/>
 *     &lt;enumeration value="CorridorOrTransitionManufacturingFacility"/>
 *     &lt;enumeration value="CorridorsWithPatientWaitingExamHospitalOrHealthcare"/>
 *     &lt;enumeration value="CourtSportsAreaSportsArena"/>
 *     &lt;enumeration value="CourtroomCourtHouse"/>
 *     &lt;enumeration value="DepartmentStoreSalesAreaRetail"/>
 *     &lt;enumeration value="DetailedManufacturingFacility"/>
 *     &lt;enumeration value="DiningArea"/>
 *     &lt;enumeration value="DiningAreaHotel"/>
 *     &lt;enumeration value="DiningAreaFamilyDining"/>
 *     &lt;enumeration value="DiningAreaLoungeOrLeisureDining"/>
 *     &lt;enumeration value="DiningAreaMotel"/>
 *     &lt;enumeration value="DiningAreaTransportation"/>
 *     &lt;enumeration value="DiningAreaPenitentiary"/>
 *     &lt;enumeration value="DiningAreaCivilServices"/>
 *     &lt;enumeration value="DormitoryBedroom"/>
 *     &lt;enumeration value="DormitoryStudyHall"/>
 *     &lt;enumeration value="DressingOrLockerOrFittingRoomGymnasium"/>
 *     &lt;enumeration value="DressingOrLockerOrFittingRoomCourtHouse"/>
 *     &lt;enumeration value="DressingOrLockerOrFittingRoomPerformingArtsTheatre"/>
 *     &lt;enumeration value="DressingOrLockerOrFittingRoomAuditorium"/>
 *     &lt;enumeration value="DressingOrLockerOrFittingRoomExerciseCenter"/>
 *     &lt;enumeration value="ElectricalOrMechanical"/>
 *     &lt;enumeration value="ElevatorLobbies"/>
 *     &lt;enumeration value="EmergencyHospitalOrHealthcare"/>
 *     &lt;enumeration value="EquipmentRoomManufacturingFacility"/>
 *     &lt;enumeration value="ExamOrTreatmentHospitalOrHealthcare"/>
 *     &lt;enumeration value="ExcerciseAreaExerciseCenter"/>
 *     &lt;enumeration value="ExcerciseAreaGymnasium"/>
 *     &lt;enumeration value="ExhibitSpaceConventionCenter"/>
 *     &lt;enumeration value="FellowshipHallReligiousBuildings"/>
 *     &lt;enumeration value="FineMaterialWarehouse"/>
 *     &lt;enumeration value="FineMerchandiseSalesAreaRetail"/>
 *     &lt;enumeration value="FireStationEngineRoomPoliceOrFireStation"/>
 *     &lt;enumeration value="FoodPreparation"/>
 *     &lt;enumeration value="GarageServiceOrRepairAutomotiveFacility"/>
 *     &lt;enumeration value="GeneralHighBayManufacturingFacility"/>
 *     &lt;enumeration value="GeneralLowBayManufacturingFacility"/>
 *     &lt;enumeration value="GeneralExhibitionMuseum"/>
 *     &lt;enumeration value="HospitalNurseryHospitalOrHealthcare"/>
 *     &lt;enumeration value="HospitalOrMedicalSuppliesHospitalOrHealthcare"/>
 *     &lt;enumeration value="HospitalOrRadiologyHospitalOrHealthcare"/>
 *     &lt;enumeration value="HotelOrConferenceCenterConferenceOrMeeting"/>
 *     &lt;enumeration value="InactiveStorage"/>
 *     &lt;enumeration value="JudgesChambersCourtHouse"/>
 *     &lt;enumeration value="LaboratoryOffice"/>
 *     &lt;enumeration value="LaundryIroningAndSorting"/>
 *     &lt;enumeration value="LaundryWashingHospitalOrHealthcare"/>
 *     &lt;enumeration value="LibraryAudioVisualLibraryAudioVisual"/>
 *     &lt;enumeration value="LivingQuartersDormitory"/>
 *     &lt;enumeration value="LivingQuartersMotel"/>
 *     &lt;enumeration value="LivingQuartersHotel"/>
 *     &lt;enumeration value="Lobby"/>
 *     &lt;enumeration value="LobbyReligiousBuildings"/>
 *     &lt;enumeration value="LobbyMotionPictureTheatre"/>
 *     &lt;enumeration value="LobbyAuditorium"/>
 *     &lt;enumeration value="LobbyPerformingArtsTheatre"/>
 *     &lt;enumeration value="LobbyPostOffice"/>
 *     &lt;enumeration value="LobbyHotel"/>
 *     &lt;enumeration value="LoungeOrRecreation"/>
 *     &lt;enumeration value="MallConcourseSalesAreaRetail"/>
 *     &lt;enumeration value="MassMerchandisingSalesAreaRetail"/>
 *     &lt;enumeration value="MediumOrBulkyMaterialWarehouse"/>
 *     &lt;enumeration value="MerchandisingSalesAreaRetail"/>
 *     &lt;enumeration value="MuseumAndGalleryStorage"/>
 *     &lt;enumeration value="NurseStationHospitalOrHealthcare"/>
 *     &lt;enumeration value="OfficeEnclosed"/>
 *     &lt;enumeration value="OfficeOpenPlan"/>
 *     &lt;enumeration value="OfficeCommonActivityAreasInactiveStorage"/>
 *     &lt;enumeration value="OperatingRoomHospitalOrHealthcare"/>
 *     &lt;enumeration value="OtherTelevisedPlayingAreaSportsArena"/>
 *     &lt;enumeration value="ParkingAreaAttendantOnlyParkingGarage"/>
 *     &lt;enumeration value="ParkingAreaPedestrianParkingGarage"/>
 *     &lt;enumeration value="PatientRoomHospitalOrHealthcare"/>
 *     &lt;enumeration value="PersonalServicesSalesAreaRetail"/>
 *     &lt;enumeration value="PharmacyHospitalOrHealthcare"/>
 *     &lt;enumeration value="PhysicalTherapyHospitalOrHealthcare"/>
 *     &lt;enumeration value="PlayingAreaGymnasium"/>
 *     &lt;enumeration value="Plenum"/>
 *     &lt;enumeration value="PoliceStationLaboratoryPoliceOrFireStations"/>
 *     &lt;enumeration value="PublicAndStaffLoungeHospitalOrHealthcare"/>
 *     &lt;enumeration value="ReadingAreaLibrary"/>
 *     &lt;enumeration value="ReceptionOrWaitingTransportation"/>
 *     &lt;enumeration value="ReceptionOrWaitingMotel"/>
 *     &lt;enumeration value="ReceptionOrWaitingHotel"/>
 *     &lt;enumeration value="RecoveryHospitalOrHealthcare"/>
 *     &lt;enumeration value="RestorationMuseum"/>
 *     &lt;enumeration value="Restrooms"/>
 *     &lt;enumeration value="RingSportsAreaSportsArena"/>
 *     &lt;enumeration value="ServerRoom"/>
 *     &lt;enumeration value="SleepingQuartersPoliceOrFireStation"/>
 *     &lt;enumeration value="SortingAreaPostOffice"/>
 *     &lt;enumeration value="SpecialtyStoreSalesAreaRetail"/>
 *     &lt;enumeration value="StacksLibrary"/>
 *     &lt;enumeration value="StairsInactive"/>
 *     &lt;enumeration value="Stairway"/>
 *     &lt;enumeration value="SupermarketSalesAreaRetail"/>
 *     &lt;enumeration value="TerminalTicketCounterTransportation"/>
 *     &lt;enumeration value="WorkshopWorkshop"/>
 *     &lt;enumeration value="WorshipPulpitChoirReligious"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "spaceTypeEnum")
@XmlEnum
public enum SpaceTypeEnum {

    @XmlEnumValue("ActiveStorage")
    ACTIVE_STORAGE("ActiveStorage"),
    @XmlEnumValue("ActiveStorageHospitalOrHealthcare")
    ACTIVE_STORAGE_HOSPITAL_OR_HEALTHCARE("ActiveStorageHospitalOrHealthcare"),
    @XmlEnumValue("AirOrTrainOrBusBaggageArea")
    AIR_OR_TRAIN_OR_BUS_BAGGAGE_AREA("AirOrTrainOrBusBaggageArea"),
    @XmlEnumValue("AirportConcourse")
    AIRPORT_CONCOURSE("AirportConcourse"),
    @XmlEnumValue("AtriumEachAdditionalFloor")
    ATRIUM_EACH_ADDITIONAL_FLOOR("AtriumEachAdditionalFloor"),
    @XmlEnumValue("AtriumFirstThreeFloors")
    ATRIUM_FIRST_THREE_FLOORS("AtriumFirstThreeFloors"),
    @XmlEnumValue("AudienceOrSeatingAreaPenitentiary")
    AUDIENCE_OR_SEATING_AREA_PENITENTIARY("AudienceOrSeatingAreaPenitentiary"),
    @XmlEnumValue("AudienceOrSeatingAreaExerciseCenter")
    AUDIENCE_OR_SEATING_AREA_EXERCISE_CENTER("AudienceOrSeatingAreaExerciseCenter"),
    @XmlEnumValue("AudienceOrSeatingAreaGymnasium")
    AUDIENCE_OR_SEATING_AREA_GYMNASIUM("AudienceOrSeatingAreaGymnasium"),
    @XmlEnumValue("AudienceOrSeatingAreaSportsArena")
    AUDIENCE_OR_SEATING_AREA_SPORTS_ARENA("AudienceOrSeatingAreaSportsArena"),
    @XmlEnumValue("AudienceOrSeatingAreaConventionCenter")
    AUDIENCE_OR_SEATING_AREA_CONVENTION_CENTER("AudienceOrSeatingAreaConventionCenter"),
    @XmlEnumValue("AudienceOrSeatingAreaMotionPictureTheatre")
    AUDIENCE_OR_SEATING_AREA_MOTION_PICTURE_THEATRE("AudienceOrSeatingAreaMotionPictureTheatre"),
    @XmlEnumValue("AudienceOrSeatingAreaPerformingArtsTheatre")
    AUDIENCE_OR_SEATING_AREA_PERFORMING_ARTS_THEATRE("AudienceOrSeatingAreaPerformingArtsTheatre"),
    @XmlEnumValue("AudienceOrSeatingAreaReligious")
    AUDIENCE_OR_SEATING_AREA_RELIGIOUS("AudienceOrSeatingAreaReligious"),
    @XmlEnumValue("AudienceOrSeatingAreaPoliceOrFireStations")
    AUDIENCE_OR_SEATING_AREA_POLICE_OR_FIRE_STATIONS("AudienceOrSeatingAreaPoliceOrFireStations"),
    @XmlEnumValue("AudienceOrSeatingAreaCourtHouse")
    AUDIENCE_OR_SEATING_AREA_COURT_HOUSE("AudienceOrSeatingAreaCourtHouse"),
    @XmlEnumValue("AudienceOrSeatingAreaAuditorium")
    AUDIENCE_OR_SEATING_AREA_AUDITORIUM("AudienceOrSeatingAreaAuditorium"),
    @XmlEnumValue("BankCustomerArea")
    BANK_CUSTOMER_AREA("BankCustomerArea"),
    @XmlEnumValue("BankingActivityAreaOffice")
    BANKING_ACTIVITY_AREA_OFFICE("BankingActivityAreaOffice"),
    @XmlEnumValue("BarberAndBeautyParlor")
    BARBER_AND_BEAUTY_PARLOR("BarberAndBeautyParlor"),
    @XmlEnumValue("CardFileAndCataloguingLibrary")
    CARD_FILE_AND_CATALOGUING_LIBRARY("CardFileAndCataloguingLibrary"),
    @XmlEnumValue("ClassroomOrLectureOrTrainingPenitentiary")
    CLASSROOM_OR_LECTURE_OR_TRAINING_PENITENTIARY("ClassroomOrLectureOrTrainingPenitentiary"),
    @XmlEnumValue("ClassroomOrLectureOrTraining")
    CLASSROOM_OR_LECTURE_OR_TRAINING("ClassroomOrLectureOrTraining"),
    @XmlEnumValue("ConfinementCellsPenitentiary")
    CONFINEMENT_CELLS_PENITENTIARY("ConfinementCellsPenitentiary"),
    @XmlEnumValue("ConfinementCellsCourtHouse")
    CONFINEMENT_CELLS_COURT_HOUSE("ConfinementCellsCourtHouse"),
    @XmlEnumValue("ConferenceMeetingOrMultipurpose")
    CONFERENCE_MEETING_OR_MULTIPURPOSE("ConferenceMeetingOrMultipurpose"),
    @XmlEnumValue("CorridorOrTransition")
    CORRIDOR_OR_TRANSITION("CorridorOrTransition"),
    @XmlEnumValue("CorridorOrTransitionManufacturingFacility")
    CORRIDOR_OR_TRANSITION_MANUFACTURING_FACILITY("CorridorOrTransitionManufacturingFacility"),
    @XmlEnumValue("CorridorsWithPatientWaitingExamHospitalOrHealthcare")
    CORRIDORS_WITH_PATIENT_WAITING_EXAM_HOSPITAL_OR_HEALTHCARE("CorridorsWithPatientWaitingExamHospitalOrHealthcare"),
    @XmlEnumValue("CourtSportsAreaSportsArena")
    COURT_SPORTS_AREA_SPORTS_ARENA("CourtSportsAreaSportsArena"),
    @XmlEnumValue("CourtroomCourtHouse")
    COURTROOM_COURT_HOUSE("CourtroomCourtHouse"),
    @XmlEnumValue("DepartmentStoreSalesAreaRetail")
    DEPARTMENT_STORE_SALES_AREA_RETAIL("DepartmentStoreSalesAreaRetail"),
    @XmlEnumValue("DetailedManufacturingFacility")
    DETAILED_MANUFACTURING_FACILITY("DetailedManufacturingFacility"),
    @XmlEnumValue("DiningArea")
    DINING_AREA("DiningArea"),
    @XmlEnumValue("DiningAreaHotel")
    DINING_AREA_HOTEL("DiningAreaHotel"),
    @XmlEnumValue("DiningAreaFamilyDining")
    DINING_AREA_FAMILY_DINING("DiningAreaFamilyDining"),
    @XmlEnumValue("DiningAreaLoungeOrLeisureDining")
    DINING_AREA_LOUNGE_OR_LEISURE_DINING("DiningAreaLoungeOrLeisureDining"),
    @XmlEnumValue("DiningAreaMotel")
    DINING_AREA_MOTEL("DiningAreaMotel"),
    @XmlEnumValue("DiningAreaTransportation")
    DINING_AREA_TRANSPORTATION("DiningAreaTransportation"),
    @XmlEnumValue("DiningAreaPenitentiary")
    DINING_AREA_PENITENTIARY("DiningAreaPenitentiary"),
    @XmlEnumValue("DiningAreaCivilServices")
    DINING_AREA_CIVIL_SERVICES("DiningAreaCivilServices"),
    @XmlEnumValue("DormitoryBedroom")
    DORMITORY_BEDROOM("DormitoryBedroom"),
    @XmlEnumValue("DormitoryStudyHall")
    DORMITORY_STUDY_HALL("DormitoryStudyHall"),
    @XmlEnumValue("DressingOrLockerOrFittingRoomGymnasium")
    DRESSING_OR_LOCKER_OR_FITTING_ROOM_GYMNASIUM("DressingOrLockerOrFittingRoomGymnasium"),
    @XmlEnumValue("DressingOrLockerOrFittingRoomCourtHouse")
    DRESSING_OR_LOCKER_OR_FITTING_ROOM_COURT_HOUSE("DressingOrLockerOrFittingRoomCourtHouse"),
    @XmlEnumValue("DressingOrLockerOrFittingRoomPerformingArtsTheatre")
    DRESSING_OR_LOCKER_OR_FITTING_ROOM_PERFORMING_ARTS_THEATRE("DressingOrLockerOrFittingRoomPerformingArtsTheatre"),
    @XmlEnumValue("DressingOrLockerOrFittingRoomAuditorium")
    DRESSING_OR_LOCKER_OR_FITTING_ROOM_AUDITORIUM("DressingOrLockerOrFittingRoomAuditorium"),
    @XmlEnumValue("DressingOrLockerOrFittingRoomExerciseCenter")
    DRESSING_OR_LOCKER_OR_FITTING_ROOM_EXERCISE_CENTER("DressingOrLockerOrFittingRoomExerciseCenter"),
    @XmlEnumValue("ElectricalOrMechanical")
    ELECTRICAL_OR_MECHANICAL("ElectricalOrMechanical"),
    @XmlEnumValue("ElevatorLobbies")
    ELEVATOR_LOBBIES("ElevatorLobbies"),
    @XmlEnumValue("EmergencyHospitalOrHealthcare")
    EMERGENCY_HOSPITAL_OR_HEALTHCARE("EmergencyHospitalOrHealthcare"),
    @XmlEnumValue("EquipmentRoomManufacturingFacility")
    EQUIPMENT_ROOM_MANUFACTURING_FACILITY("EquipmentRoomManufacturingFacility"),
    @XmlEnumValue("ExamOrTreatmentHospitalOrHealthcare")
    EXAM_OR_TREATMENT_HOSPITAL_OR_HEALTHCARE("ExamOrTreatmentHospitalOrHealthcare"),
    @XmlEnumValue("ExcerciseAreaExerciseCenter")
    EXCERCISE_AREA_EXERCISE_CENTER("ExcerciseAreaExerciseCenter"),
    @XmlEnumValue("ExcerciseAreaGymnasium")
    EXCERCISE_AREA_GYMNASIUM("ExcerciseAreaGymnasium"),
    @XmlEnumValue("ExhibitSpaceConventionCenter")
    EXHIBIT_SPACE_CONVENTION_CENTER("ExhibitSpaceConventionCenter"),
    @XmlEnumValue("FellowshipHallReligiousBuildings")
    FELLOWSHIP_HALL_RELIGIOUS_BUILDINGS("FellowshipHallReligiousBuildings"),
    @XmlEnumValue("FineMaterialWarehouse")
    FINE_MATERIAL_WAREHOUSE("FineMaterialWarehouse"),
    @XmlEnumValue("FineMerchandiseSalesAreaRetail")
    FINE_MERCHANDISE_SALES_AREA_RETAIL("FineMerchandiseSalesAreaRetail"),
    @XmlEnumValue("FireStationEngineRoomPoliceOrFireStation")
    FIRE_STATION_ENGINE_ROOM_POLICE_OR_FIRE_STATION("FireStationEngineRoomPoliceOrFireStation"),
    @XmlEnumValue("FoodPreparation")
    FOOD_PREPARATION("FoodPreparation"),
    @XmlEnumValue("GarageServiceOrRepairAutomotiveFacility")
    GARAGE_SERVICE_OR_REPAIR_AUTOMOTIVE_FACILITY("GarageServiceOrRepairAutomotiveFacility"),
    @XmlEnumValue("GeneralHighBayManufacturingFacility")
    GENERAL_HIGH_BAY_MANUFACTURING_FACILITY("GeneralHighBayManufacturingFacility"),
    @XmlEnumValue("GeneralLowBayManufacturingFacility")
    GENERAL_LOW_BAY_MANUFACTURING_FACILITY("GeneralLowBayManufacturingFacility"),
    @XmlEnumValue("GeneralExhibitionMuseum")
    GENERAL_EXHIBITION_MUSEUM("GeneralExhibitionMuseum"),
    @XmlEnumValue("HospitalNurseryHospitalOrHealthcare")
    HOSPITAL_NURSERY_HOSPITAL_OR_HEALTHCARE("HospitalNurseryHospitalOrHealthcare"),
    @XmlEnumValue("HospitalOrMedicalSuppliesHospitalOrHealthcare")
    HOSPITAL_OR_MEDICAL_SUPPLIES_HOSPITAL_OR_HEALTHCARE("HospitalOrMedicalSuppliesHospitalOrHealthcare"),
    @XmlEnumValue("HospitalOrRadiologyHospitalOrHealthcare")
    HOSPITAL_OR_RADIOLOGY_HOSPITAL_OR_HEALTHCARE("HospitalOrRadiologyHospitalOrHealthcare"),
    @XmlEnumValue("HotelOrConferenceCenterConferenceOrMeeting")
    HOTEL_OR_CONFERENCE_CENTER_CONFERENCE_OR_MEETING("HotelOrConferenceCenterConferenceOrMeeting"),
    @XmlEnumValue("InactiveStorage")
    INACTIVE_STORAGE("InactiveStorage"),
    @XmlEnumValue("JudgesChambersCourtHouse")
    JUDGES_CHAMBERS_COURT_HOUSE("JudgesChambersCourtHouse"),
    @XmlEnumValue("LaboratoryOffice")
    LABORATORY_OFFICE("LaboratoryOffice"),
    @XmlEnumValue("LaundryIroningAndSorting")
    LAUNDRY_IRONING_AND_SORTING("LaundryIroningAndSorting"),
    @XmlEnumValue("LaundryWashingHospitalOrHealthcare")
    LAUNDRY_WASHING_HOSPITAL_OR_HEALTHCARE("LaundryWashingHospitalOrHealthcare"),
    @XmlEnumValue("LibraryAudioVisualLibraryAudioVisual")
    LIBRARY_AUDIO_VISUAL_LIBRARY_AUDIO_VISUAL("LibraryAudioVisualLibraryAudioVisual"),
    @XmlEnumValue("LivingQuartersDormitory")
    LIVING_QUARTERS_DORMITORY("LivingQuartersDormitory"),
    @XmlEnumValue("LivingQuartersMotel")
    LIVING_QUARTERS_MOTEL("LivingQuartersMotel"),
    @XmlEnumValue("LivingQuartersHotel")
    LIVING_QUARTERS_HOTEL("LivingQuartersHotel"),
    @XmlEnumValue("Lobby")
    LOBBY("Lobby"),
    @XmlEnumValue("LobbyReligiousBuildings")
    LOBBY_RELIGIOUS_BUILDINGS("LobbyReligiousBuildings"),
    @XmlEnumValue("LobbyMotionPictureTheatre")
    LOBBY_MOTION_PICTURE_THEATRE("LobbyMotionPictureTheatre"),
    @XmlEnumValue("LobbyAuditorium")
    LOBBY_AUDITORIUM("LobbyAuditorium"),
    @XmlEnumValue("LobbyPerformingArtsTheatre")
    LOBBY_PERFORMING_ARTS_THEATRE("LobbyPerformingArtsTheatre"),
    @XmlEnumValue("LobbyPostOffice")
    LOBBY_POST_OFFICE("LobbyPostOffice"),
    @XmlEnumValue("LobbyHotel")
    LOBBY_HOTEL("LobbyHotel"),
    @XmlEnumValue("LoungeOrRecreation")
    LOUNGE_OR_RECREATION("LoungeOrRecreation"),
    @XmlEnumValue("MallConcourseSalesAreaRetail")
    MALL_CONCOURSE_SALES_AREA_RETAIL("MallConcourseSalesAreaRetail"),
    @XmlEnumValue("MassMerchandisingSalesAreaRetail")
    MASS_MERCHANDISING_SALES_AREA_RETAIL("MassMerchandisingSalesAreaRetail"),
    @XmlEnumValue("MediumOrBulkyMaterialWarehouse")
    MEDIUM_OR_BULKY_MATERIAL_WAREHOUSE("MediumOrBulkyMaterialWarehouse"),
    @XmlEnumValue("MerchandisingSalesAreaRetail")
    MERCHANDISING_SALES_AREA_RETAIL("MerchandisingSalesAreaRetail"),
    @XmlEnumValue("MuseumAndGalleryStorage")
    MUSEUM_AND_GALLERY_STORAGE("MuseumAndGalleryStorage"),
    @XmlEnumValue("NurseStationHospitalOrHealthcare")
    NURSE_STATION_HOSPITAL_OR_HEALTHCARE("NurseStationHospitalOrHealthcare"),
    @XmlEnumValue("OfficeEnclosed")
    OFFICE_ENCLOSED("OfficeEnclosed"),
    @XmlEnumValue("OfficeOpenPlan")
    OFFICE_OPEN_PLAN("OfficeOpenPlan"),
    @XmlEnumValue("OfficeCommonActivityAreasInactiveStorage")
    OFFICE_COMMON_ACTIVITY_AREAS_INACTIVE_STORAGE("OfficeCommonActivityAreasInactiveStorage"),
    @XmlEnumValue("OperatingRoomHospitalOrHealthcare")
    OPERATING_ROOM_HOSPITAL_OR_HEALTHCARE("OperatingRoomHospitalOrHealthcare"),
    @XmlEnumValue("OtherTelevisedPlayingAreaSportsArena")
    OTHER_TELEVISED_PLAYING_AREA_SPORTS_ARENA("OtherTelevisedPlayingAreaSportsArena"),
    @XmlEnumValue("ParkingAreaAttendantOnlyParkingGarage")
    PARKING_AREA_ATTENDANT_ONLY_PARKING_GARAGE("ParkingAreaAttendantOnlyParkingGarage"),
    @XmlEnumValue("ParkingAreaPedestrianParkingGarage")
    PARKING_AREA_PEDESTRIAN_PARKING_GARAGE("ParkingAreaPedestrianParkingGarage"),
    @XmlEnumValue("PatientRoomHospitalOrHealthcare")
    PATIENT_ROOM_HOSPITAL_OR_HEALTHCARE("PatientRoomHospitalOrHealthcare"),
    @XmlEnumValue("PersonalServicesSalesAreaRetail")
    PERSONAL_SERVICES_SALES_AREA_RETAIL("PersonalServicesSalesAreaRetail"),
    @XmlEnumValue("PharmacyHospitalOrHealthcare")
    PHARMACY_HOSPITAL_OR_HEALTHCARE("PharmacyHospitalOrHealthcare"),
    @XmlEnumValue("PhysicalTherapyHospitalOrHealthcare")
    PHYSICAL_THERAPY_HOSPITAL_OR_HEALTHCARE("PhysicalTherapyHospitalOrHealthcare"),
    @XmlEnumValue("PlayingAreaGymnasium")
    PLAYING_AREA_GYMNASIUM("PlayingAreaGymnasium"),
    @XmlEnumValue("Plenum")
    PLENUM("Plenum"),
    @XmlEnumValue("PoliceStationLaboratoryPoliceOrFireStations")
    POLICE_STATION_LABORATORY_POLICE_OR_FIRE_STATIONS("PoliceStationLaboratoryPoliceOrFireStations"),
    @XmlEnumValue("PublicAndStaffLoungeHospitalOrHealthcare")
    PUBLIC_AND_STAFF_LOUNGE_HOSPITAL_OR_HEALTHCARE("PublicAndStaffLoungeHospitalOrHealthcare"),
    @XmlEnumValue("ReadingAreaLibrary")
    READING_AREA_LIBRARY("ReadingAreaLibrary"),
    @XmlEnumValue("ReceptionOrWaitingTransportation")
    RECEPTION_OR_WAITING_TRANSPORTATION("ReceptionOrWaitingTransportation"),
    @XmlEnumValue("ReceptionOrWaitingMotel")
    RECEPTION_OR_WAITING_MOTEL("ReceptionOrWaitingMotel"),
    @XmlEnumValue("ReceptionOrWaitingHotel")
    RECEPTION_OR_WAITING_HOTEL("ReceptionOrWaitingHotel"),
    @XmlEnumValue("RecoveryHospitalOrHealthcare")
    RECOVERY_HOSPITAL_OR_HEALTHCARE("RecoveryHospitalOrHealthcare"),
    @XmlEnumValue("RestorationMuseum")
    RESTORATION_MUSEUM("RestorationMuseum"),
    @XmlEnumValue("Restrooms")
    RESTROOMS("Restrooms"),
    @XmlEnumValue("RingSportsAreaSportsArena")
    RING_SPORTS_AREA_SPORTS_ARENA("RingSportsAreaSportsArena"),
    @XmlEnumValue("ServerRoom")
    SERVER_ROOM("ServerRoom"),
    @XmlEnumValue("SleepingQuartersPoliceOrFireStation")
    SLEEPING_QUARTERS_POLICE_OR_FIRE_STATION("SleepingQuartersPoliceOrFireStation"),
    @XmlEnumValue("SortingAreaPostOffice")
    SORTING_AREA_POST_OFFICE("SortingAreaPostOffice"),
    @XmlEnumValue("SpecialtyStoreSalesAreaRetail")
    SPECIALTY_STORE_SALES_AREA_RETAIL("SpecialtyStoreSalesAreaRetail"),
    @XmlEnumValue("StacksLibrary")
    STACKS_LIBRARY("StacksLibrary"),
    @XmlEnumValue("StairsInactive")
    STAIRS_INACTIVE("StairsInactive"),
    @XmlEnumValue("Stairway")
    STAIRWAY("Stairway"),
    @XmlEnumValue("SupermarketSalesAreaRetail")
    SUPERMARKET_SALES_AREA_RETAIL("SupermarketSalesAreaRetail"),
    @XmlEnumValue("TerminalTicketCounterTransportation")
    TERMINAL_TICKET_COUNTER_TRANSPORTATION("TerminalTicketCounterTransportation"),
    @XmlEnumValue("WorkshopWorkshop")
    WORKSHOP_WORKSHOP("WorkshopWorkshop"),
    @XmlEnumValue("WorshipPulpitChoirReligious")
    WORSHIP_PULPIT_CHOIR_RELIGIOUS("WorshipPulpitChoirReligious");
    private final String value;

    SpaceTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SpaceTypeEnum fromValue(String v) {
        for (SpaceTypeEnum c: SpaceTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
