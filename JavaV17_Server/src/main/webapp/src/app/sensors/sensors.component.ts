import { Component } from '@angular/core';
import { SensorListComponent } from './sensor-list/sensor-list.component';

@Component({
  selector: 'app-sensors',
  imports: [SensorListComponent],
  templateUrl: './sensors.component.html',
  styleUrl: './sensors.component.css'
})
export class SensorsComponent {

}
