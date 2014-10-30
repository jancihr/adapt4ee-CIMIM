

let $trajectoryMinTimeFull :=	min(ns2:MeasurementFile[id=$id]/events/event/OccupantTrajectory/occupantTrajectory/timeStamp/xs:dateTime(.))
let $trajectoryMaxTimeFull :=  max(/ns2:MeasurementFile[id=$id]/events/event/OccupantTrajectory/occupantTrajectory/timeStamp/xs:dateTime(.))
let $trajectoryMinTimeMini :=	min(ns2:MeasurementFile[id=$id]/events/event/OccupantTrajectory/a/@t/xs:dateTime(.))
let $trajectoryMaxTimeMini :=  max(/ns2:MeasurementFile[id=$id]/events/event/OccupantTrajectory/a/@t/xs:dateTime(.))

let $trajectoryMinTimeCompare := <comparetime><time>{$trajectoryMinTimeFull}</time><time>{$trajectoryMinTimeMini}</time></comparetime>
let $trajectoryMaxTimeCompare := <comparetime><time>{$trajectoryMaxTimeFull}</time><time>{$trajectoryMaxTimeMini}</time></comparetime>

let $trajectoryMinTime :=	ns2:min-non-empty-string($trajectoryMinTimeCompare/time)
let $trajectoryMaxTime :=  ns2:max-string($trajectoryMaxTimeCompare/time)

let $trajectoryCount :=  count(/ns2:MeasurementFile[id=$id]/events/event/OccupantTrajectory)
let $trajectoryPointCount :=  count(/ns2:MeasurementFile[id=$id]/events/event/OccupantTrajectory/occupantTrajectory) + count(/ns2:MeasurementFile[id=$id]/events/event/OccupantTrajectory/a)

let $occupantMovedMinTime := min(/ns2:MeasurementFile[id=$id]/events/event/occupantMoved/time/xs:dateTime(.))
let $occupantMovedMaxTime := max(/ns2:MeasurementFile[id=$id]/events/event/occupantMoved/time/xs:dateTime(.))
let $occupantMovedCount := count(/ns2:MeasurementFile[id=$id]/events/event/occupantMoved)

let $equipmentUsedMinTime := min(/ns2:MeasurementFile[id=$id]/events/event/equipmentUsed/time/xs:dateTime(.))
let $equipmentUsedMaxTime := max(/ns2:MeasurementFile[id=$id]/events/event/equipmentUsed/time/xs:dateTime(.))
let $equipmentUsedCount := count(/ns2:MeasurementFile[id=$id]/events/event/equipmentUsed)

let $spaceEnvironmentChangeMinTime := min(/ns2:MeasurementFile[id=$id]/events/event/spaceEnvironmentChange/time/xs:dateTime(.))
let $spaceEnvironmentChangeMaxTime := max(/ns2:MeasurementFile[id=$id]/events/event/spaceEnvironmentChange/time/xs:dateTime(.))
let $spaceEnvironmentChangeCount := count(/ns2:MeasurementFile[id=$id]/events/event/spaceEnvironmentChange)

let $externalEnvironmentChangedMinTime := min(/ns2:MeasurementFile[id=$id]/events/event/externalEnvironmentChanged/time/xs:dateTime(.))
let $externalEnvironmentChangedMaxTime := max(/ns2:MeasurementFile[id=$id]/events/event/externalEnvironmentChanged/time/xs:dateTime(.))
let $externalEnvironmentChangedCount := count(/ns2:MeasurementFile[id=$id]/events/event/externalEnvironmentChanged)

let $HVACChangeMinTime := min(/ns2:MeasurementFile[id=$id]/events/event/HVACChange/time/xs:dateTime(.))
let $HVACChangeMaxTime := max(/ns2:MeasurementFile[id=$id]/events/event/HVACChange/time/xs:dateTime(.))
let $HVACChangeCount := count(/ns2:MeasurementFile[id=$id]/events/event/HVACChange)

let $LightingChangeMinTime := min(/ns2:MeasurementFile[id=$id]/events/event/LightingChange/time/xs:dateTime(.))
let $LightingChangeMaxTime := max(/ns2:MeasurementFile[id=$id]/events/event/LightingChange/time/xs:dateTime(.))
let $LightingChangeCount := count(/ns2:MeasurementFile[id=$id]/events/event/LightingChange)

let $SpaceOccupancyMinTime := min(/ns2:MeasurementFile[id=$id]/events/event/SpaceOccupancy/time/xs:dateTime(.))
let $SpaceOccupancyMaxTime := max(/ns2:MeasurementFile[id=$id]/events/event/SpaceOccupancy/time/xs:dateTime(.))
let $SpaceOccupancyCount := count(/ns2:MeasurementFile[id=$id]/events/event/SpaceOccupancy)

let $SpaceMovementMinTime := min(/ns2:MeasurementFile[id=$id]/events/event/SpaceMovement/time/xs:dateTime(.))
let $SpaceMovementMaxTime := max(/ns2:MeasurementFile[id=$id]/events/event/SpaceMovement/time/xs:dateTime(.))
let $SpaceMovementCount := count(/ns2:MeasurementFile[id=$id]/events/event/SpaceMovement)

let $SpaceOccupantPossitionsMinTime := min(/ns2:MeasurementFile[id=$id]/events/event/SpaceOccupantPossitions/time/xs:dateTime(.))
let $SpaceOccupantPossitionsMaxTime := max(/ns2:MeasurementFile[id=$id]/events/event/SpaceOccupantPossitions/time/xs:dateTime(.))
let $SpaceOccupantPossitionsCount := count(/ns2:MeasurementFile[id=$id]/events/event/SpaceOccupantPossitions)

let $CurrentMinTime := min(/ns2:MeasurementFile[id=$id]/events/event/Current/time/xs:dateTime(.))
let $CurrentMaxTime := max(/ns2:MeasurementFile[id=$id]/events/event/Current/time/xs:dateTime(.))
let $CurrentCount := count(/ns2:MeasurementFile[id=$id]/events/event/Current)

let $VoltageMinTime := min(/ns2:MeasurementFile[id=$id]/events/event/Voltage/time/xs:dateTime(.))
let $VoltageMaxTime := max(/ns2:MeasurementFile[id=$id]/events/event/Voltage/time/xs:dateTime(.))
let $VoltageCount := count(/ns2:MeasurementFile[id=$id]/events/event/Voltage)

let $EnergyMinTime := min(/ns2:MeasurementFile[id=$id]/events/event/Energy/time/xs:dateTime(.))
let $EnergyMaxTime := max(/ns2:MeasurementFile[id=$id]/events/event/Energy/time/xs:dateTime(.))
let $EnergyCount := count(/ns2:MeasurementFile[id=$id]/events/event/Energy)
 
let $eventNumbers :=

<eventNumbers>
 
 <trajectories>{$trajectoryCount} </trajectories>
 <trajectoryPoints>{$trajectoryPointCount}</trajectoryPoints> 
 
<occupantMoved>
{$occupantMovedCount}
</occupantMoved>

<equipmentUsed>
{$equipmentUsedCount}
</equipmentUsed>

<spaceEnvironmentChange>
{$spaceEnvironmentChangeCount}
</spaceEnvironmentChange>

<externalEnvironmentChanged>
{$externalEnvironmentChangedCount}
</externalEnvironmentChanged>

<HVACChange>
{$HVACChangeCount}
</HVACChange>

<LightingChange>
{$LightingChangeCount}
</LightingChange>

<SpaceOccupancy>
{$SpaceOccupancyCount}
</SpaceOccupancy>


<SpaceMovement>
{$SpaceMovementCount}
</SpaceMovement>


<SpaceOccupantPossitions>
{$SpaceOccupantPossitionsCount}
</SpaceOccupantPossitions>

<Current>
{$CurrentCount}
</Current>

<Voltage>
{$VoltageCount}
</Voltage>

<Energy>
{$EnergyCount}
</Energy>
 
 </eventNumbers>

let $eventTimes := <eventTimes>

<OccupantTrajectoryTime>
<minTime>
{$trajectoryMinTime}
</minTime>
<maxTime>
{$trajectoryMaxTime}
</maxTime>
</OccupantTrajectoryTime>

<occupantMovedTime>
<minTime>
{$occupantMovedMinTime}
</minTime>
<maxTime>
{$occupantMovedMaxTime}
</maxTime>
</occupantMovedTime>

<equipmentUsedTime>
<minTime>
{$equipmentUsedMinTime}
</minTime>
<maxTime>
{$equipmentUsedMaxTime}
</maxTime>
</equipmentUsedTime>

<spaceEnvironmentChangeTime>
<minTime>
{$spaceEnvironmentChangeMinTime}
</minTime>
<maxTime>
{$spaceEnvironmentChangeMaxTime}
</maxTime>
</spaceEnvironmentChangeTime>

<externalEnvironmentChangedTime>
<minTime>
{$externalEnvironmentChangedMinTime}
</minTime>
<maxTime>
{$externalEnvironmentChangedMaxTime}
</maxTime>
</externalEnvironmentChangedTime>

<HVACChangeTime>
<minTime>
{$HVACChangeMinTime}
</minTime>
<maxTime>
{$HVACChangeMaxTime}
</maxTime>
</HVACChangeTime>

<LightingChangeTime>
<minTime>
{$LightingChangeMinTime}
</minTime>
<maxTime>
{$LightingChangeMaxTime}
</maxTime>
</LightingChangeTime>

<SpaceOccupancyTime>
<minTime>
{$SpaceOccupancyMinTime}
</minTime>
<maxTime>
{$SpaceOccupancyMaxTime}
</maxTime>
</SpaceOccupancyTime>

<SpaceMovementTime>
<minTime>
{$SpaceMovementMinTime}
</minTime>
<maxTime>
{$SpaceMovementMaxTime}
</maxTime>
</SpaceMovementTime>

<SpaceOccupantPossitionsTime>
<minTime>
{$SpaceOccupantPossitionsMinTime}
</minTime>
<maxTime>
{$SpaceOccupantPossitionsMaxTime}
</maxTime>
</SpaceOccupantPossitionsTime>

<CurrentTime>
<minTime>
{$CurrentMinTime}
</minTime>
<maxTime>
{$CurrentMaxTime}
</maxTime>
</CurrentTime>

<VoltageTime>
<minTime>
{$VoltageMinTime}
</minTime>
<maxTime>
{$VoltageMaxTime}
</maxTime>
</VoltageTime>

<EnergyTime>
<minTime>
{$EnergyMinTime}
</minTime>
<maxTime>
{$EnergyMaxTime }
</maxTime>
</EnergyTime>
</eventTimes>

let $overalMinTime := ns2:min-non-empty-string($eventTimes/*/minTime)
let $overalMaxTime := ns2:max-string($eventTimes/*/maxTime)

let $result:= <ns2:EventsStats xmlns:ns2="http://www.adapt4ee.eu/2012/schema/cim/"> 

<minTime>{$overalMinTime}</minTime>
<maxTime>{$overalMaxTime}</maxTime>

<allEventsNumber>{$trajectoryCount + $occupantMovedCount + $equipmentUsedCount + $spaceEnvironmentChangeCount + $externalEnvironmentChangedCount + $HVACChangeCount+ $LightingChangeCount +$SpaceOccupancyCount + $SpaceMovementCount + $SpaceOccupantPossitionsCount+$CurrentCount + $VoltageCount+ $EnergyCount}</allEventsNumber>

{$eventNumbers}
{$eventTimes}


 </ns2:EventsStats> 
 
 return $result