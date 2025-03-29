import { Component } from '@angular/core';
import { Sensor } from '../sensor';
import { RouterLink } from '@angular/router';
import { SensorService } from '../sensor.service';
@Component({
  selector: 'app-sensor-list',
  imports: [ RouterLink],
  templateUrl: './sensor-list.component.html',
  styleUrl: './sensor-list.component.css'
})
export class SensorListComponent {
  sensors: Sensor[]=[];
  //Update the list of students with the web service info.
  getSensors(): void{
    this.sensorService.getAll().subscribe({
    next: (data) =>{
    this.sensors=data;
    }
    });
    }
  //Connect to the Web Service
  constructor(private sensorService:SensorService){}
  ngOnInit():void{
    this.getSensors();
  }
}
