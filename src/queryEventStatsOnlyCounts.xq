
let $trajectoryCount :=  count(/ns2:MeasurementFile[id=$id]/events/event/OccupantTrajectory)
let $trajectoryPointCount :=  count(/ns2:MeasurementFile[id=$id]/events/event/OccupantTrajectory/occupantTrajectory) + count(/ns2:MeasurementFile[id=$id]/events/event/OccupantTrajectory/a)

let $occupantMovedCount := count(/ns2:MeasurementFile[id=$id]/events/event/occupantMoved)

let $equipmentUsedCount := count(/ns2:MeasurementFile[id=$id]/events/event/equipmentUsed)

let $spaceEnvironmentChangeCount := count(/ns2:MeasurementFile[id=$id]/events/event/spaceEnvironmentChange)

let $externalEnvironmentChangedCount := count(/ns2:MeasurementFile[id=$id]/events/event/externalEnvironmentChanged)

let $HVACChangeCount := count(/ns2:MeasurementFile[id=$id]/events/event/HVACChange)

let $LightingChangeCount := count(/ns2:MeasurementFile[id=$id]/events/event/LightingChange)

let $SpaceOccupancyCount := count(/ns2:MeasurementFile[id=$id]/events/event/SpaceOccupancy)

let $SpaceMovementCount := count(/ns2:MeasurementFile[id=$id]/events/event/SpaceMovement)

let $SpaceOccupantPossitionsCount := count(/ns2:MeasurementFile[id=$id]/events/event/SpaceOccupantPossitions)

let $CurrentCount := count(/ns2:MeasurementFile[id=$id]/events/event/Current)

let $VoltageCount := count(/ns2:MeasurementFile[id=$id]/events/event/Voltage)

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


let $result:= <ns2:EventsStats xmlns:ns2="http://www.adapt4ee.eu/2012/schema/cim/"> 
<allEventsNumber>{$trajectoryCount 
+ $occupantMovedCount 
+ $equipmentUsedCount 
+ $spaceEnvironmentChangeCount 
+ $externalEnvironmentChangedCount 
+ $HVACChangeCount
+ $LightingChangeCount 
+ $SpaceOccupancyCount 
+ $SpaceMovementCount 
+ $SpaceOccupantPossitionsCount
+ $CurrentCount 
+ $VoltageCount
+ $EnergyCount}
</allEventsNumber>
{$eventNumbers}
</ns2:EventsStats> 
 
 return $result