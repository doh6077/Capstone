import { Component } from '@angular/core';
import { SensorListComponent } from './sensor-list/sensor-list.component';
import { RouterOutlet } from '@angular/router';
@Component({
  selector: 'app-sensors',
  imports: [SensorListComponent, RouterOutlet],
  templateUrl: './sensors.component.html',
  styleUrl: './sensors.component.css'
})
export class SensorsComponent {

}
