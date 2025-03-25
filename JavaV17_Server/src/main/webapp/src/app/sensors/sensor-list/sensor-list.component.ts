import { Component } from '@angular/core';
import { Sensor } from '../sensor';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-sensor-list',
  imports: [ RouterLink],
  templateUrl: './sensor-list.component.html',
  styleUrl: './sensor-list.component.css'
})
export class SensorListComponent {
  sensors: Sensor[]=[];
  ngOnInit():void{
  this.sensors = [
  {id: 1, batteryStatus: 'High', lastBatteryUpdateDate:"Mar 25", location:"A building"},
  {id: 2, batteryStatus: 'Low', lastBatteryUpdateDate:"Mar 26", location:"B building"},
  {id: 3, batteryStatus: 'Low', lastBatteryUpdateDate:"Mar 27", location:"C building"},
  ];
  }
}
